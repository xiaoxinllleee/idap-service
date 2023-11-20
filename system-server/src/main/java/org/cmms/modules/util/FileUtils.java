package org.cmms.modules.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Slf4j
public class FileUtils {

    /**
     * SIZE
     */
    private final int size = 1024;


    public static void deleteFolder(File file) {
        //获取文件对象数组
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                //是文件，删除
                f.delete();
            } else {
                //是文件夹
                //采用递归
                deleteFolder(f);
            }
        }
        //递归回调的时候删除空文件夹
        file.delete();
    }

    public static boolean fileIsHave(String path){
        File file = new File(path);
        if (file.exists())
            return true;
        return false;
    }
    /**
     * File url
     *
     * @param urlAddress url address
     * @param localFileName local file name
     * @param destinationDir destination dir
     */
    public static void fileUrl(String urlAddress, String localFileName, String destinationDir,String hz) {
        OutputStream outputStream = null;
        URLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(urlAddress);
            outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(destinationDir + "\\" + localFileName + "."+hz)));
            urlConnection = url.openConnection();
            inputStream = urlConnection.getInputStream();

            byte[] buf = new byte[1024];
            int byteRead, byteWritten = 0;
            while ((byteRead = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, byteRead);
                outputStream.flush();
                byteWritten += byteRead;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * File download
     *
     * @param urlAddress url address
     * @param destinationDir destination dir
     */
    public static void fileDownload(String urlAddress, String destinationDir,String hz) {
        int slashIndex = destinationDir.lastIndexOf('/');
        int periodIndex = destinationDir.lastIndexOf('.');

        String fileName = destinationDir.substring(slashIndex + 1, periodIndex);

        if (periodIndex >= 1 && slashIndex >= 0 && slashIndex < urlAddress.length() - 1) {
            fileUrl(urlAddress, fileName, destinationDir,hz);
        } else {
            System.err.println("path or file name.");
        }
    }
    public static String generateImage(String base64) {
        // 解密
        try {
            String savePath = "D://ydyxpt/code/";
            // 图片分类路径+图片名+图片后缀
            String imgClassPath = "yzm.jpg";

            // 去掉base64前缀 data:image/jpeg;base64,
            base64 = base64.substring(base64.indexOf(",", 1) + 1);
            // 解密，解密的结果是一个byte数组
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] imgbytes = decoder.decode(base64);
            for (int i = 0; i < imgbytes.length; ++i) {
                if (imgbytes[i] < 0) {
                    imgbytes[i] += 256;
                }
            }

            // 保存图片
            String imgSavePath = savePath.concat(imgClassPath);
            OutputStream out = new FileOutputStream(imgSavePath);
            out.write(imgbytes);
            out.flush();
            out.close();
            // 返回图片的相对路径 = 图片分类路径+图片名+图片后缀
            return imgSavePath;
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
      //  System.out.println(FileUtils.fileIsHave("D://temp//upload\\static/imgs/annex/20200907/02491599492342002.jpg"));

       //fileUrl("https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF","aaaa.gif","D:");
        String base64 ="data:image/gif;base64,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";
        String imagePath = generateImage(base64);
        System.out.println(imagePath);

    }
}
