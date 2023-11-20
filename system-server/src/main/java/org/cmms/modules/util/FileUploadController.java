package org.cmms.modules.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    // 存储文件
    @RequestMapping(value = "/uploadFile",method={RequestMethod.POST})
    public Map uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest req) {
        Map result = new HashMap<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        String dateDir = df.format(new Date());// new Date()为获取当前系统时间
        File tempFile = new File("D:\\temp\\upload\\" + dateDir + File.separator
                + file.getOriginalFilename());

        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdirs();
        }
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tempFile));
                // "d:/"+file.getOriginalFilename() 指定目录
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                result.put("msg", "上传文件产生错误," + e.getMessage());
                result.put("result", false);
            } catch (IOException e) {
                e.printStackTrace();
                result.put("msg", "上传文件产生错误," + e.getMessage());
                result.put("result", false);
            }
            result.put("msg", "上传成功");
            result.put("result", true);
        } else {
            result.put("msg", "上传文件为空");
            result.put("result", false);
        }
        return result;
    }
}
