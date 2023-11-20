package org.cmms.modules.common.async.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class FtpClientManager {

    public static void initClient(String rq,String ftpPath,String ok,String ip,int port,String userName,String password) {
        File file = new File(ftpPath);
        File Ok = new File(ok);
        String datePath =  File.separator+ rq + File.separator;

        // ftp
        FTPClient ftpClient = new FTPClient();
        // 设置连接使用的字符编码。必须在建立连接之前设置。
        ftpClient.setControlEncoding("UTF-8");
        try {
            // 连接服务端
            ftpClient.connect(ip,port);
            log.info("连接服务器" + ip + ":" + port);

            // ftp操作可能会返回一些响应信息，可以打印出来看看
            // showServerReply(ftpClient);

            // 尝试连接后，检查响应码以确认成功
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                disconnectQuietly(ftpClient);
                log.info("服务器拒绝连接");
                return;
            }
        } catch (IOException e) {
            disconnectQuietly(ftpClient);
            log.info("连接ftp失败");
            e.printStackTrace();
            return;
        }

        try {
            // 登录ftp
            boolean success = ftpClient.login(userName, password);
            if (!success) {
                ftpClient.logout();
                log.info("客户端登录失败");
                return;
            }
            log.info("客户端登录成功");
            // 大部分情况，上传文件时，需要设置这两项
            // 设置文件传输类型为二进制文件类型
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 设置被动模式
            ftpClient.enterLocalPassiveMode();

            boolean done = false;
            boolean done2 = false;
            try (final InputStream input = new FileInputStream(file);
                 final InputStream input2 = new FileInputStream(Ok) ) {
                //创建一个目录
                ftpClient.makeDirectory(datePath);
                log.info("创建文件"+ftpClient.makeDirectory(datePath));
                // 设置上传到ftp上使用的文件名和路径
                String remote = datePath+ file.getName();
                String remote2 = datePath+ Ok.getName();//上传ok文件
                // 上传文件
                done = ftpClient.storeFile(remote, input);
                done2 = ftpClient.storeFile(remote2, input2);
            }
            //zbjg文件
            if (done) {
                log.info("上传文件" + file.getName() + "成功");
                // ftpClient.completePendingCommand();
            } else {
                log.info("上传文件" + file.getName() + "失败");
                showServerReply(ftpClient);
            }
            //ok文件
            if (done2) {
                log.info("上传文件" + Ok.getName() + "成功");
            } else {
                log.info("上传文件" + Ok.getName() + "失败");
                showServerReply(ftpClient);
            }
            ftpClient.noop(); // check that control connection is working OK
            ftpClient.logout();
        } catch(FTPConnectionClosedException e) {
            log.info("服务端关闭连接");
            e.printStackTrace();
        } catch (IOException e) {
            log.info("客户端登录或操作失败");
            e.printStackTrace();
        } finally {

            disconnectQuietly(ftpClient);
        }
    }
    // 创建一个目录
    public boolean makeDirectory(String dir) {
        // 创建FTPClient对象
        FTPClient ftp = new FTPClient();
        boolean flag = true;
        try {
            flag = ftp.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    // 判断ftp服务器文件是否存在
    public boolean existFile(String path) throws IOException {
        // 创建FTPClient对象
        FTPClient ftp = new FTPClient();
        boolean flag = false;
        FTPFile[] ftpFileArr = ftp.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 断开ftp连接
     */
    public static void disconnectQuietly(FTPClient ftpClient) {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
            } catch (IOException ex) {
                // do nothing
            }
        }
    }

    /**
     * 打印服务器返回信息
     */
    public static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("服务端响应信息: " + aReply);
            }
        }
    }

}
