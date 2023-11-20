package org.cmms.common.util;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author 龚辉
 * @date 2023/3/14 10:01 周二
 */
@Slf4j
@Component
public class SshUtil {
    private String charset = Charset.defaultCharset().toString();
    private final int TIME_OUT = 1000 * 5 * 60;

    private Connection conn;

    @Value("${common.shell.username:root}")
    private String username;
    @Value("${common.shell.password:Dell@1688}")
    private String password;
    @Value("${common.shell.ip:10.119.0.33}")
    private String ip;

    /**
     * 执行
     *
     * @param shell
     * @return
     */
    public boolean execShell(String shell) {
        try {
            System.out.println("------------------开始执行--------------------");
            int result = exec(shell);
            System.out.println("-----------------执行完成---------------------");
            System.out.println("-----------------执行状态码:" + result + "-------------------");
            return result == 0;
        } catch (Exception e) {
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
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(username, password);
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
                Session session = conn.openSession();
                session.execCommand(shell);
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                ret = session.getExitStatus() == null ? 0 : session.getExitStatus();
                System.out.println(processStdout(session.getStdout()).toString());
            } else {
                throw new Exception("登录远程机器失败" + ip);
            }
        } finally {
            if (conn != null) {
                conn.close();
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
