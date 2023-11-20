package org.cmms.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Date 2022/3/30
 * @Created by eran
 */
@Slf4j
public class Base64Util {

    public static void toImage(String base64Str,String imgPath){
        log.info("===开始base64转png===");
        if (StringUtils.isNotBlank(base64Str)){
            base64Str = StringUtils.substringAfter(base64Str,";base64,");
            byte[] bytes = Base64.decodeBase64(base64Str);

            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0){
                    bytes[i] += 256;
                }
            }

            try{
                OutputStream outputStream = new FileOutputStream(imgPath);
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
                log.info("===base64转png结束,文件地址为{}===",imgPath);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
