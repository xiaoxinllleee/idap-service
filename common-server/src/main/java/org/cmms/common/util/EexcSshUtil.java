package org.cmms.common.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 龚辉
 * @date 2023/3/14 10:01 周二
 */
@Slf4j
@Component
public class EexcSshUtil {


    //jsch创建连接
    public static Session getJSchSession(String ip , int port, String userName, String password){
        System.out.println("==============准备连接服务器==============ip："+ip+":"+port+"/"+userName+"："+password);
        JSch jSch = new JSch();
        Session session = null;
        try {
            //创建连接
            session = jSch.getSession(userName,ip,port);
            session.setPassword(password);
            //是否使用密钥登录，一般默认为no
            session.setConfig("StrictHostKeyChecking", "no");
            //启用连接
            session.connect();
            boolean connected = session.isConnected();
            System.out.println("==============服务器连接成功==============connected:"+connected);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("==============服务器连接失败==============");
        }
        return session;
    }



    //jsch关闭连接
    public static void closeJSchSession(Session session){
        if (session != null) {
            try {
                session.disconnect();
                System.out.println("===========服务器连接关闭成功===========");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("===========服务器连接关闭失败===========");
            }
        }
    }

    //用来执行命令
//Session表示传递连接对话,commands表示传递命令集合
    public static  List<String> getCmdResult(Session session , String command){
        //用来存放命令的返回值
        List<String> cmdResult = new ArrayList<>();

        Channel channel = null;
        try {
            //创建执行通道
            channel = session.openChannel("exec");
            //设置命令
            ((ChannelExec) channel).setCommand(command);
            //连接通道
            channel.connect();
            //读取通道的输出
            InputStream in = channel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            //存放命令的执行结果，如果结果有很多行，则每次line的值不同
            String line;
            //lines用来拼接line结果
            StringBuffer lines = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                //去除头尾的空格
                line.trim();
                lines = lines.append(line);
            }
            //如果命令执行没有返回值，则直接输出没有返回值
            if (String.valueOf(lines).equals("")){
                cmdResult.add("命令["+command+"]执行成功,但没有返回值");
            }else {
                //否则将每行返回直接存入到list中
                cmdResult.add(String.valueOf(lines));
            }
            reader.close();
            channel.disconnect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cmdResult;
    }

    public static void main(String[] args) {
        Session session= getJSchSession("192.168.0.245", 22, "oracle", "RaServer.245");
        String  execShell ="source .bashrc && sh /home/oracle/csvdata/load_to_oracle.sh";
        List<String> result = getCmdResult(session, execShell);
        result.forEach((x)-> System.out.println(x));
        closeJSchSession(session);
    }
}
