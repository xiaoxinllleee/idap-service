package org.cmms.common.util;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.jcraft.jsch.JSch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 龚辉
 * @date 2023/3/14 10:01 周二
 */
@Slf4j
@Component
public class SshUtil020 {
    private String charset = Charset.defaultCharset().toString();
    private final int TIME_OUT = 1000 * 5 * 60;

    private Connection conn1;
    private Connection conn2;


    @Value("${common.shell.username:root}")
    private String username1;
    @Value("${common.shell.password:root@123}")
    private String password1;
    @Value("${common.shell.ip:10.18.10.92}")//impala
    private String ip1;

    @Value("${common.shell.username:root}")
    private String username2;
    @Value("${common.shell.password:root@123}")
    private String password2;
    @Value("${common.shell.ip:10.18.10.90}")//Oracle
    private String ip2;

   @Value("${common.oracle.username:idap}")//Oracle
   private String name;
   @Value("${common.oracle.password:Ods24_db}")//Oracle
   private String pwd;
   @Value("${common.oracle.oracleUrl:jdbc:oracle:thin:@10.18.10.90:1521:ods}")//Oracle
   private String oracleUrl;


    /**
     * 执行
     *
     * @param shell
     * @return
     */
    public boolean execShell(String shell) {
        try {
            System.out.println("------------------执行命令----92----------------"+shell);
            System.out.println("------------------开始执行------92--------------");
            int result = exec(shell);
            System.out.println("-----------------执行完成-------92--------------");
            System.out.println("-----------92------执行状态码:" + result + "-------------------");
            return result == 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();

        }
        return false;
    }

    public boolean execShell2(String shell) {
        try {
            System.out.println("------------------执行命令----90----------------"+shell);
            System.out.println("------------------开始执行----90----------------");
            int result = exec2(shell);
            System.out.println("-----------------执行完成-------90--------------");
            System.out.println("----------90-------执行状态码:" + result + "-------------------");
            return result == 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 登录指远程服务器
     *
     * @return
     * @throws IOException
     */
    private boolean login() throws IOException {
        conn1 = new Connection(ip1);
        conn1.connect();
        return conn1.authenticateWithPassword(username1, password1);
    }


    /**
     * 登录指远程服务器
     *
     * @return
     * @throws IOException
     */
    private boolean login2() throws IOException {
        conn2 = new Connection("192.168.0.220");
        conn2.connect();
        return conn2.authenticateWithPassword("root", "RaServer.220");
    }

    /**
     * 调用Shell脚本
     *
     * @param shell
     * @return
     * @throws Exception
     */
    public int exec(String shell) throws Exception {
        int ret = -1;
        try {
            if (login()) {
                Session session = conn1.openSession();
                session.execCommand(shell);
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                ret = session.getExitStatus() == null ? 0 : session.getExitStatus();
                System.out.println(processStdout(session.getStdout()).toString());
            } else {
                throw new Exception("登录远程机器失败" + ip1);
            }
        } finally {
            if (conn1 != null) {
                conn1.close();
            }
        }
        return ret;
    }



    /**
     * 调用Shell脚本
     *
     * @param shell
     * @return
     * @throws Exception
     */
    public int exec2(String shell) throws Exception {
        int ret = -1;
        try {
            if (login2()) {
                Session session = conn2.openSession();
                session.execCommand(shell);
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                ret = session.getExitStatus() == null ? 0 : session.getExitStatus();
                System.out.println(processStdout(session.getStdout()).toString());
            } else {
                throw new Exception("登录远程机器失败" + ip2);
            }
        } finally {
            if (conn2 != null) {
                conn2.close();
            }
        }
        return ret;
    }



    /**
     * 输出脚本日志
     *
     * @param in
     * @return
     * @throws FileNotFoundException
     */
    public StringBuilder processStdout(InputStream in) throws FileNotFoundException {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        try {
            int length;
            while ((length = in.read(buf)) != -1) {
                sb.append(new String(buf, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

}
