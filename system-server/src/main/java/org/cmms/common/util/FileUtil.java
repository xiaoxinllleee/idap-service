package org.cmms.common.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import sun.rmi.runtime.Log;

import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.xml.bind.util.JAXBSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: Erakor
 * Date: 12-6-7
 */
@Slf4j
public class FileUtil {
    public static final String EXCLE_PATH = "excel/";
    public static final String WORD_PATH = "word/";

    public static String getTempFilePath(String excelName) {
        String tempExcelPath="";
        try {
            ClassPathResource classPathResource = new ClassPathResource(EXCLE_PATH+excelName);
            log.info("=============模板路径==============:"+classPathResource.getPath());
            log.info("=============根据模板导出=============:"+excelName);
            InputStream inputStream = classPathResource.getInputStream();
            File somethingFile = File.createTempFile("excelTemplate_export_copy", ".xls");
            try {
                FileUtils.copyInputStreamToFile(inputStream, somethingFile);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
            tempExcelPath= somethingFile.getPath();
        } catch (IOException e) {
            log.info("=============根据模板导出异常=============:"+e.getMessage());
            e.printStackTrace();
        }
        return tempExcelPath;
    }

    public static File getWordTempFilePath() {
        String tempDir = System.getProperty("java.io.tmpdir");
        File tempDirectory = new File(tempDir, "word_temp");
        if (!tempDirectory.exists()) {
            tempDirectory.mkdirs();
        }
        return tempDirectory;
    }



    public static String getjpgTempFilePath(String wordName,String tempfilepath ,String tempfileName) {
        String tempWordPath="";

        try {
            ClassPathResource classPathResource = new ClassPathResource(WORD_PATH+wordName);
            InputStream inputStream = classPathResource.getInputStream();
            File somethingFile = File.createTempFile(tempfileName,".jpg",new File(tempfilepath));

            try {
                FileUtils.copyInputStreamToFile(inputStream, somethingFile);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
            tempWordPath= somethingFile.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempWordPath;
    }

    public static File getTempFilePathNy(String excelName) {
        File file=null;
        try {
            ClassPathResource classPathResource = new ClassPathResource(EXCLE_PATH+excelName);
            log.info("=============模板路径==============:"+classPathResource.getPath());
            log.info("=============根据模板导出=============:"+excelName);
            InputStream inputStream = classPathResource.getInputStream();
            File somethingFile = File.createTempFile("excelTemplate_export_copy", ".xls");
            try {
                FileUtils.copyInputStreamToFile(inputStream, somethingFile);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
            file= somethingFile;
        } catch (IOException e) {
            log.info("=============根据模板导出异常=============:"+e.getMessage());
            e.printStackTrace();
        }
        return file;
    }
}
