package org.cmms.modules.util;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.RedisUtil;
import org.cmms.config.EtlConfig;
import org.cmms.modules.system.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


@Component
@Slf4j
public class EtlUtil {

    public static EtlConfig etlConfig;

    public static RedisUtil redisUtil;

    public static String msg;

    @Autowired
    public void init(RedisUtil redisUtil) {
        EtlUtil.redisUtil = redisUtil;
    }

    @Autowired
    public void init(EtlConfig etlConfig) {
        EtlUtil.etlConfig = etlConfig;
    }


    public static boolean login() {
        JSONObject json = new JSONObject();
        json.put("login_name", etlConfig.getUsername());
        json.put("login_password", MD5Util.MD5(etlConfig.getPassword()).toLowerCase());
        json.put("login_type", "PASSWORD");
        String post = HttpRequest.post(etlConfig.getLoginUrl())
                .header("content-type", "application/json")
                .body(json.toJSONString())
                .execute().body();
        JSONObject jsonObject = JSONObject.parseObject(UnicodeUtil.toString(post));
        JSONObject header = (JSONObject) jsonObject.get("header");
        String code = header.get("code").toString();
        msg = header.get("msg").toString();
        if (!code.equals("0")) {
            log.info("======ETL调度平台登录失败======");
            return false;
        } else {
            System.out.println(code);
            JSONObject body = (JSONObject) jsonObject.get("body");
            String token = body.get("token").toString();
            System.out.println("token:" + token);
            //登录成功，把返回的token信息放进缓存。
            //同时设置超时时间5000s，因为调度平台超时时间默认为6000秒。这里设置5000是保证不会在失效的状态下去调用接口。
            redisUtil.set("etlToken", token);
            redisUtil.expire("etlToken", 5000);
            log.info("======ETL调度平台登录成功======");
            return true;
        }
    }

    public static boolean callEtl(String taskCode, HashMap parms, long minuteTime) {
        try {
            log.info("======开始执行ETL调度======" + taskCode);
            boolean flag = true;
            //如果缓存中没有etlToken 或者失效时间小于5分钟，则重新登录，避免执行调度接口时，登录失效导致失败。
            if (!redisUtil.hasKey("etlToken") || redisUtil.getExpire("etlToken") < 300) {
                flag = login();
            }
            if (flag) {
                JSONObject json = new JSONObject();
                json.put("task_code", taskCode);
                json.putAll(parms);
                String post = HttpRequest.post(etlConfig.getCallEtlUrl())
                        .header("content-type", "application/json")
                        .header("x-session-token", redisUtil.get("etlToken").toString())
                        .body(json.toJSONString())
                        .execute().body();
                log.info("======开始执行ETL调度请求POST======" + taskCode + "::::::::" + post);
                JSONObject jsonObject = JSONObject.parseObject(UnicodeUtil.toString(post));
                JSONObject header = (JSONObject) jsonObject.get("header");
                String code = header.get("code").toString();
                if (!code.equals("0")) {
                    log.info("======执行ETL调度失败======" + taskCode);
                    return false;
                } else {
                    JSONObject body = (JSONObject) jsonObject.get("body");
                    String processCode = body.get("process_code").toString();
                    log.info("======执行ETL调度成功======process_code:" + processCode + "::::" + taskCode);
                    //通过调度elt脚本返回的code，去查询执行状态
                    return checkEtlCountStatus(taskCode, processCode, minuteTime);
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param minuteTime 超时断开时间
     * @param sync       是否是同步表
     * @param parms      注意：一定按顺序传参，如果是同步表则传 参数1 table 参数2 schema  如果是提取则传 参数1 app  参数2 model  参数3 fiscal_date
     * @return
     */
    public static boolean SHcallEtl(long minuteTime, Boolean sync, String... parms) {
        InputStream stdOut = null;
        InputStream stdErr = null;
        Session session = null;
        Connection conn = null;
        try {
            conn = new Connection(etlConfig.getSshHost());//参数传服务器的地址
            conn.connect();

            boolean isAuthenticated = conn.authenticateWithPassword(etlConfig.getSshUser(), etlConfig.getSshPWD());//返回true代表可正常登录服务器
            if (!isAuthenticated) {
                log.error("远程连接ssh用户名或密码错误");
                throw new IOException("Authentication failed!");
            }
            session = conn.openSession();
            //执行linux指令。

            //String orderStr = "docker exec -i kanas bash -c \"/root/trunk/src/tests/kanas.sh  hr_bas_organization\"";
            StringBuffer orderStr = new StringBuffer();
            if (sync) {
                orderStr.append("docker exec -i kanas_app_1 bash -c \"/root/trunk/src/tests/table_sync.sh");
            } else {
                orderStr.append("docker exec -i kanas_app_1 bash -c \"/root/trunk/src/tests/common_init.sh");
            }
            for (String parm : parms) {
                orderStr.append(" " + parm + " ");
            }
            session.execCommand(orderStr.toString());

            //StreamGobbler的作用是把session的标准输出包装成InputStream，用于接收目标服务器上的控制台返回结果。
            stdOut = new StreamGobbler(session.getStdout());

            //正常IO流读取输入流数据的操作
            StringBuffer sb = new StringBuffer();
            byte[] bys = new byte[1024];
            int len = 0;
            while ((len = stdOut.read(bys)) != -1) sb.append(new String(bys, 0, len));
            String res = sb.toString();
            System.out.println(res);

            //这是接收日志信息
            stdErr = new StreamGobbler(session.getStderr());
            sb = new StringBuffer();
            bys = new byte[1024];
            len = 0;
            while ((len = stdErr.read(bys)) != -1) sb.append(new String(bys, 0, len));
            String err = sb.toString();
            System.out.println(err);

            //session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS , 30000);
            // 等待，除非1.连接关闭；2.输出数据传送完毕；3.进程状态为退出；4.超时
            //调用这个方法在执行linux命令时，会避免环境变量读取不全的问题，这里有许多标识可以用，比如当exit命令执行后或者超过了timeout时间,则session关闭
            session.waitForCondition(ChannelCondition.EXIT_STATUS, minuteTime * 60 * 1000);//在调用getExitStatus时，要先调用WaitForCondition方法

            //一般情况下shell脚本正常执行完毕，getExitStatus方法返回0。
            //此方法通过远程命令取得Exit Code/status。
            //但并不是每个server设计时都会返回这个值，如果没有则会返回null。
            //getExitStatus的返回值，可以认为是此次执行是否OK的标准。
            int ret = session.getExitStatus() == null ? 0 : session.getExitStatus();
            if (ret == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stdOut != null) stdOut.close();
                if (stdErr != null) stdErr.close();
                if (session != null) session.close();
                if (conn != null) conn.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }


    /**
     * @param minuteTime 超时断开时间
     * @param sync       是否是同步表
     * @param parms      注意：一定按顺序传参，如果是同步表则传 参数1 table 参数2 schema  如果是提取则传 参数1 app  参数2 model  参数3 fiscal_date
     * @return
     */
    public static boolean SHcallEtlRc(long minuteTime, Boolean sync, Boolean isEweb, Boolean isSyncSql, String... parms) {
        InputStream stdOut = null;
        InputStream stdErr = null;
        Session session = null;
        Connection conn = null;
        try {
            conn = new Connection(etlConfig.getSshHost());//参数传服务器的地址
            conn.connect();

            boolean isAuthenticated = conn.authenticateWithPassword(etlConfig.getSshUser(), etlConfig.getSshPWD());//返回true代表可正常登录服务器
            if (!isAuthenticated) {
                log.error("远程连接ssh用户名或密码错误");
                throw new IOException("Authentication failed!");
            }
            session = conn.openSession();
            //执行linux指令。

            StringBuffer orderStr = new StringBuffer();
            if (sync) {
                orderStr.append("docker exec -i kanas bash -c \"cd /root/trunk/src/tests/ && sh");
            } else {
                orderStr.append("docker exec -i kanas bash -c \"/root/trunk/src/tests/common_init.sh");
            }
            if (isEweb) {
                orderStr.append(" table_sync_eweb.sh  ");
            } else if (isSyncSql) {
                orderStr.append(" table_sync_nhgrsxmx.sh");
            } else {
                orderStr.append(" table_sync.sh ");
            }
            for (String parm : parms) {
                orderStr.append(" " + parm + " ");
            }
            orderStr.append("\"");
            session.execCommand(orderStr.toString());

            //StreamGobbler的作用是把session的标准输出包装成InputStream，用于接收目标服务器上的控制台返回结果。
            stdOut = new StreamGobbler(session.getStdout());

            //正常IO流读取输入流数据的操作
            StringBuffer sb = new StringBuffer();
            byte[] bys = new byte[1024];
            int len = 0;
            while ((len = stdOut.read(bys)) != -1) sb.append(new String(bys, 0, len));
            String res = sb.toString();
            System.out.println(res);

            //这是接收日志信息
            stdErr = new StreamGobbler(session.getStderr());
            sb = new StringBuffer();
            bys = new byte[1024];
            len = 0;
            while ((len = stdErr.read(bys)) != -1) sb.append(new String(bys, 0, len));
            String err = sb.toString();
            System.out.println(err);

            //session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS , 30000);
            // 等待，除非1.连接关闭；2.输出数据传送完毕；3.进程状态为退出；4.超时
            //调用这个方法在执行linux命令时，会避免环境变量读取不全的问题，这里有许多标识可以用，比如当exit命令执行后或者超过了timeout时间,则session关闭
            session.waitForCondition(ChannelCondition.EXIT_STATUS, minuteTime * 60 * 1000);//在调用getExitStatus时，要先调用WaitForCondition方法

            //一般情况下shell脚本正常执行完毕，getExitStatus方法返回0。
            //此方法通过远程命令取得Exit Code/status。
            //但并不是每个server设计时都会返回这个值，如果没有则会返回null。
            //getExitStatus的返回值，可以认为是此次执行是否OK的标准。
            int ret = session.getExitStatus() == null ? 0 : session.getExitStatus();
            if (ret == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stdOut != null) stdOut.close();
                if (stdErr != null) stdErr.close();
                if (session != null) session.close();
                if (conn != null) conn.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }


    /**
     * 查询状态超过minuteTime 时间自动结束。
     *
     * @param processCode
     * @param minuteTime
     * @return
     */
    public static boolean checkEtlCountStatus(String taskCode, String processCode, long minuteTime) {
        boolean flag = true;
        if (!redisUtil.hasKey("etlToken") || redisUtil.getExpire("etlToken") < 300) {
            flag = login();
        }
        if (flag) {
            long beginTime = System.currentTimeMillis();
            while (true) {
                try {
                    //每隔3秒查询一次状态
                    Thread.sleep(3000);
                    JSONObject json = new JSONObject();
                    json.put("process_code", processCode);
                    String post = HttpRequest.post(etlConfig.getEtlStatusUrl())
                            .header("content-type", "application/json")
                            .header("x-session-token", redisUtil.get("etlToken").toString())
                            .body(json.toJSONString())
                            .execute().body();
                    JSONObject jsonObject = JSONObject.parseObject(UnicodeUtil.toString(post));
                    long endTime = System.currentTimeMillis();
                    if ((endTime - beginTime) / 1000 / 60 >= minuteTime) {
                        break;
                    }
                    JSONObject header = (JSONObject) jsonObject.get("header");
                    String code = header.get("code").toString();
                    if (code.equals("0")) {
                        JSONObject body = (JSONObject) jsonObject.get("body");
                        String status = body.get("status").toString();
                        log.info("======执行ETL调度状态查询===" + taskCode + "::::::::status:" + status);
                        if (status.equals("FAILED")) {
                            return false;
                        } else if (status.equals("SUCCESS")) {
                            return true;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("======执行ETL调度状态查询======status:查询异常");
                    return false;
                }

            }
        } else {
            return false;
        }
        log.info("======执行ETL调度状态查询======status:超时结果");
        return false;
    }


   /* public static void main(String[] args) throws Exception{
     //*发送post请求并接收响应数据
        *//*采用的是一种叫链式编程的方式):
        header对应的是请求头。
        body对应的是请求体(包含参数和参数值)。
        HttpRequest里面包含Post、GET、Delete、Put等常用的RestFul方式。*//*
        String url="http://192.168.0.157:38029/api/weps_web/login";
        JSONObject json = new JSONObject();
        json.put("login_name", "admin");
        json.put("login_password", MD5Util.MD5("admin123").toLowerCase() );
        json.put("login_type", "PASSWORD");
        String post = HttpRequest.post(url)
                .header("content-type","application/json")
                //.header("x-session-token","token")
                .body(json.toJSONString())
                .execute().body();
        JSONObject jsonObject=JSONObject.parseObject(UnicodeUtil.toString(post));
        JSONObject header= (JSONObject) jsonObject.get("header");
        String code=header.get("code").toString();
        if(!code.equals("0")){
            System.out.println(code);
        }else{
            System.out.println(code);
            JSONObject body= (JSONObject) jsonObject.get("body");
            String token=body.get("token").toString();
            System.out.println("token:"+token);

        }
    }*/


    public static void main(String[] args) throws IOException {

        Connection conn = new Connection("192.168.0.222");//参数传服务器的地址
        conn.connect();

        boolean isAuthenticated = conn.authenticateWithPassword("root", "RaServer.222");//返回true代表可正常登录服务器
        if (!isAuthenticated) {
            System.out.println("用户名或密码错误");
            throw new IOException("Authentication failed!");
        }

        Session session = conn.openSession();

        //执行linux指令。
        String orderStr = "docker exec -i kanas bash -c \"/root/trunk/src/tests/kanas.sh  hr_bas_organization\"";
        session.execCommand(orderStr);

        //StreamGobbler的作用是把session的标准输出包装成InputStream，用于接收目标服务器上的控制台返回结果。
        InputStream stdOut = new StreamGobbler(session.getStdout());

        //正常IO流读取输入流数据的操作
        StringBuffer sb = new StringBuffer();
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = stdOut.read(bys)) != -1) sb.append(new String(bys, 0, len));
        String res = sb.toString();
        System.out.println(res);

        //这是接收日志信息
        InputStream stdErr = new StreamGobbler(session.getStderr());
        sb = new StringBuffer();
        bys = new byte[1024];
        len = 0;
        while ((len = stdErr.read(bys)) != -1) sb.append(new String(bys, 0, len));
        String err = sb.toString();
        System.out.println(err);

        //session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS , 30000);
        // 等待，除非1.连接关闭；2.输出数据传送完毕；3.进程状态为退出；4.超时
        //调用这个方法在执行linux命令时，会避免环境变量读取不全的问题，这里有许多标识可以用，比如当exit命令执行后或者超过了timeout时间,则session关闭
        session.waitForCondition(ChannelCondition.EXIT_STATUS, 3000);//在调用getExitStatus时，要先调用WaitForCondition方法

        //一般情况下shell脚本正常执行完毕，getExitStatus方法返回0。
        //此方法通过远程命令取得Exit Code/status。
        //但并不是每个server设计时都会返回这个值，如果没有则会返回null。
        //getExitStatus的返回值，可以认为是此次执行是否OK的标准。
        int ret = session.getExitStatus() == null ? 0 : session.getExitStatus();

        if (ret == 0) {
            System.out.println("导入成功");
        } else {
            System.out.println("导入失败");
        }

        if (stdOut != null) stdOut.close();
        if (stdErr != null) stdErr.close();
        if (session != null) session.close();
        if (conn != null) conn.close();
    }

}
