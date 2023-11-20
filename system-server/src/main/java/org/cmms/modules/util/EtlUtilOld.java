package org.cmms.modules.util;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.util.RedisUtil;
import org.cmms.config.EtlConfig;
import org.cmms.modules.system.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
@Slf4j
public class EtlUtilOld {

    public static EtlConfig etlConfig;

    public static RedisUtil redisUtil;

    public static String msg;

    @Autowired
    public void init(RedisUtil redisUtil){
        EtlUtilOld.redisUtil=redisUtil;
    }

    @Autowired
    public void init(EtlConfig etlConfig){
        EtlUtilOld.etlConfig=etlConfig;
    }



    public static boolean login(){
        JSONObject json = new JSONObject();
        json.put("login_name", etlConfig.getUsername());
        json.put("login_password", MD5Util.MD5(etlConfig.getPassword()).toLowerCase());
        json.put("login_type", "PASSWORD");
        String post = HttpRequest.post(etlConfig.getLoginOldUrl())
                .header("content-type","application/json")
                .body(json.toJSONString())
                .execute().body();
        JSONObject jsonObject=JSONObject.parseObject(UnicodeUtil.toString(post));
        JSONObject header= (JSONObject) jsonObject.get("header");
        String code=header.get("code").toString();
        msg=header.get("msg").toString();
        if(!code.equals("0")){
            log.info("======ETL调度平台登录失败======");
            return false;
        }else{
            System.out.println(code);
            JSONObject body= (JSONObject) jsonObject.get("body");
            String token=body.get("token").toString();
            System.out.println("token:"+token);
            //登录成功，把返回的token信息放进缓存。
            //同时设置超时时间5000s，因为调度平台超时时间默认为6000秒。这里设置5000是保证不会在失效的状态下去调用接口。
            redisUtil.set("etlToken",token);
            redisUtil.expire("etlToken",5000);
            log.info("======ETL调度平台登录成功======");
            return true;
        }
    }

    public static boolean callEtl(String taskCode, HashMap parms,long minuteTime){
        log.info("======开始执行ETL调度======"+taskCode);
        boolean flag=true;
        //如果缓存中没有etlToken 或者失效时间小于5分钟，则重新登录，避免执行调度接口时，登录失效导致失败。
        if(!redisUtil.hasKey("etlToken")||redisUtil.getExpire("etlToken")<300){
            flag=login();
        }
        if(flag){
            JSONObject json = new JSONObject();
            json.put("task_code", taskCode);
            json.putAll(parms);
            String post = HttpRequest.post(etlConfig.getCalloldEtlUrl())
                    .header("content-type","application/json")
                    .header("x-session-token",redisUtil.get("etlToken").toString())
                    .body(json.toJSONString())
                    .execute().body();
            log.info("======开始执行ETL调度请求POST======"+taskCode+"::::::::"+post);
            JSONObject jsonObject=JSONObject.parseObject(UnicodeUtil.toString(post));
            JSONObject header= (JSONObject) jsonObject.get("header");
            String code=header.get("code").toString();
            if(!code.equals("0")){
                log.info("======执行ETL调度失败======"+taskCode);
                return  false;
            }else{
                JSONObject body= (JSONObject) jsonObject.get("body");
                String processCode=body.get("process_code").toString();
                log.info("======执行ETL调度成功======process_code:"+processCode+"::::"+taskCode);
                //通过调度elt脚本返回的code，去查询执行状态
                return checkEtlCountStatus(taskCode,processCode,minuteTime);
            }
        }else{
            return  false;
        }
    }

    /**
     * 查询状态超过minuteTime 时间自动结束。
     * @param processCode
     * @param minuteTime
     * @return
     */
    public static boolean checkEtlCountStatus(String taskCode,String processCode,long minuteTime){
        boolean flag=true;
        if(!redisUtil.hasKey("etlToken")||redisUtil.getExpire("etlToken")<300){
            flag=login();
        }
        if(flag){
            long beginTime =System.currentTimeMillis();
            while (true){
                try {
                    //每隔3秒查询一次状态
                    Thread.sleep(3000);
                    JSONObject json = new JSONObject();
                    json.put("process_code", processCode);
                    String post = HttpRequest.post(etlConfig.getEtlStatusOldUrl())
                            .header("content-type","application/json")
                            .header("x-session-token",redisUtil.get("etlToken").toString())
                            .body(json.toJSONString())
                            .execute().body();
                    JSONObject jsonObject=JSONObject.parseObject(UnicodeUtil.toString(post));
                    long endTime =System.currentTimeMillis();
                    if((endTime-beginTime)/1000/60>=minuteTime){
                        break;
                    }
                    JSONObject header= (JSONObject) jsonObject.get("header");
                    String code=header.get("code").toString();
                    if(code.equals("0")){
                        JSONObject body= (JSONObject) jsonObject.get("body");
                        String status=body.get("status").toString();
                        log.info("======执行ETL调度状态查询==="+taskCode+"::::::::status:"+status);
                        if(status.equals("FAILED")){
                            return false;
                        }else if(status.equals("SUCCESS")){
                            return true;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("======执行ETL调度状态查询======status:查询异常");
                    return  false;
                }

            }
        }else{
            return  false;
        }
        log.info("======执行ETL调度状态查询======status:超时结果");
        return false;
    }


    public static void main(String[] args) throws Exception{
     //*发送post请求并接收响应数据
        /*采用的是一种叫链式编程的方式):
        header对应的是请求头。
        body对应的是请求体(包含参数和参数值)。
        HttpRequest里面包含Post、GET、Delete、Put等常用的RestFul方式。*/
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
    }
}
