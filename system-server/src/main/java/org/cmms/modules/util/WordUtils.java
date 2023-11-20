package org.cmms.modules.util;

import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.util.FileUtil;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Word工具
 */
public class WordUtils {

    /**
     * 信息采集Word文件目录
     */
    public static final String CREDIT_SYSTEM_INFO_COLLECT_WORD_DIRECTORY = "credit_system_info_collect_word";
    /**
     * 临时资源文件目录名称
     */
    private String tempResourceFileDirectoryName = "credit_system_word_resource";
    /**
     * wold资源文件目录名
     */
    private String resourceName = "static/files";
    /**
     * docx模板文件名称
     */
    private String docxTemplateFileName;
    /**
     * docx文件中document.xml模板文件名称
     */
    private String xmlTemplateFileName;

    private WordUtils() {
    }

    public WordUtils(String docxTemplateFileName, String xmlTemplateFileName) {
        this.docxTemplateFileName = docxTemplateFileName;
        this.xmlTemplateFileName = xmlTemplateFileName;
        String tempResourceFileDirectoryPath = this.getTempResourceFileDirectoryPath();
        this.initResourceFile(tempResourceFileDirectoryPath, this.docxTemplateFileName);
        this.initResourceFile(tempResourceFileDirectoryPath, this.xmlTemplateFileName);
    }

    /**
     * 获取源文件路径
     *
     * @param resourceName
     * @return
     */
    public String getResourcePath(String resourceName) {
        return this.getClass().getClassLoader().getResource(resourceName).getPath();
    }

    /**
     * utf-8编码
     *
     * @return
     */
    public Charset utf8() {
        return Charset.forName("UTF-8");
    }

    public Charset ISO() {
        return Charset.forName("ISO-8859-1");
    }

    public Charset gbk() {
        return Charset.forName("GBK");
    }

    /**
     * 设置Zip响应头以及文件名称
     *
     * @param response
     * @param fileName     文件名称
     * @param encodingName 编码名称
     */
    public void setZipHeader(HttpServletResponse response, String fileName, String encodingName) throws UnsupportedEncodingException {
        this.setHeader(response, "application/zip;charset=UTF-8", fileName, encodingName);
    }

    /**
     * 设置docx响应头以及文件名称
     *
     * @param response
     * @param fileName     文件名称
     * @param encodingName 编码名称
     */
    public void setDocxHeader(HttpServletResponse response, String fileName, String encodingName) throws UnsupportedEncodingException {
        this.setHeader(response, "application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=UTF-8", fileName, encodingName);
    }

    /**
     * 设置doc响应头以及文件名称
     *
     * @param response
     * @param fileName     文件名称
     * @param encodingName 编码名称
     */
    public void setDocHeader(HttpServletResponse response, String fileName, String encodingName) throws UnsupportedEncodingException {
        this.setHeader(response, "application/msword;charset=UTF-8", fileName, encodingName);
    }

    /**
     * 设置响应头
     *
     * @param response
     * @param contentType
     * @param fileName     文件名称
     * @param encodingName 编码名称
     */
    public void setHeader(HttpServletResponse response, String contentType, String fileName, String encodingName) throws UnsupportedEncodingException {
        response.setCharacterEncoding(encodingName);
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s", URLEncoder.encode(fileName, "UTF-8")));
    }

    /**
     * freemarker模板处理
     *
     * @param dataModel                数据
     * @param out                      输出流
     * @param templateLoadingDirectory 模板文件目录
     * @param templateName             模板文件名称
     * @param encodingName             编码名称
     * @throws IOException
     * @throws TemplateException
     */
    public void templateProcess(Object dataModel, Writer out, String templateLoadingDirectory, String templateName, String encodingName) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        if (encodingName != null) {
            cfg.setDefaultEncoding(encodingName);
        }
        cfg.setDirectoryForTemplateLoading(new File(templateLoadingDirectory));
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = cfg.getTemplate(templateName);
        template.process(dataModel, out);
        out.flush();
    }

    /**
     * 流复制
     *
     * @param out 输出流
     * @param in  输入流
     * @throws IOException
     */
    public void copyStream(OutputStream out, InputStream in) throws IOException {
        byte[] buf = new byte[4096];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.flush();
    }

    /**
     * 生成临时目录
     *
     * @return
     */
    public File generateTempDirectory() {
        return this.generateTempDirectory(UUID.randomUUID().toString().replace("-", StringUtils.EMPTY).toUpperCase());
    }

    /**
     * 生成临时目录
     *
     * @return
     */
    public File generateTempDirectory(String directoryName) {
        String tempDir = System.getProperty("java.io.tmpdir");
        File tempDirectory = new File(tempDir, directoryName);
        if (!tempDirectory.exists()) {
            tempDirectory.mkdirs();
        }
        return tempDirectory;
    }

    public File generateDirectory(String path) {
        File tempDirectory = new File(path);
        if (!tempDirectory.exists()) {
            tempDirectory.mkdirs();
        }
        return tempDirectory;
    }

    /**
     * 删除临时目录以及目录下的文件
     *
     * @param tempDirectory 临时目录
     */
    private void deleteTempDirectory(File tempDirectory) {
        if (tempDirectory != null) {
            File[] files = tempDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.deleteOnExit();
                }
            }
            tempDirectory.deleteOnExit();
        }
    }

    /**
     * 根据目录生成zip文件
     *
     * @param directory    目录
     * @param outputStream 输出流
     * @throws IOException
     */
    public void generateZipFileByDirectory(File directory, OutputStream outputStream) throws IOException {
        this.generateZipFileByFiles(Lists.newArrayList(directory.listFiles()), outputStream);
    }

    /**
     * 根据文件集合生成zip文件
     *
     * @param files        文件集合
     * @param outputStream 输出流
     * @throws IOException
     */
    public void generateZipFileByFiles(List<File> files, OutputStream outputStream) throws IOException {
        if (CollectionUtils.isNotEmpty(files)) {
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream, this.utf8());
            for (File file : files) {
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                this.copyStream(zipOutputStream, new FileInputStream(file));
            }
            zipOutputStream.flush();
            zipOutputStream.close();
        }
    }


    /**
     * 获取临时资源文件目录
     *
     * @return
     */
    public File getTempResourceFileDirectory() {
        return this.generateTempDirectory(this.tempResourceFileDirectoryName);
    }

    /**
     * 获取临时资源文件目录路径
     *
     * @return
     */
    public String getTempResourceFileDirectoryPath() {
        return this.getTempResourceFileDirectory().getPath();
    }

    /**
     * 初始化资源文件
     *
     * @param targetResourceFilePath
     * @param templateFileName
     * @throws IOException
     */
    private void initResourceFile(String targetResourceFilePath, String templateFileName) {
        String path = StringUtils.join(resourceName, File.separator, templateFileName);
        ClassPathResource classPathResource = new ClassPathResource(path);
        try {
            this.copyStream(new FileOutputStream(new File(targetResourceFilePath, templateFileName)), classPathResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void generateDocxFile(OutputStream outputStream, Object dataModel) throws IOException, TemplateException {
        Charset charset = this.utf8();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream,charset);

        String resourcePath = this.getTempResourceFileDirectoryPath();
        ZipFile zipFile = new ZipFile(new File(resourcePath, docxTemplateFileName));
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        ZipEntry zipEntry;
        String entryName;
        // 遍历docx文件（zip文件）内容
        while (entries.hasMoreElements()) {
            zipEntry = entries.nextElement();
            entryName = zipEntry.getName();
            zipOutputStream.putNextEntry(new ZipEntry(entryName));
            // 使用freemarker模板生成document.xml文件，该文件包含docx文件的主要内容
            if (entryName.equals("word/document.xml")) {
                this.templateProcess(
                        dataModel,
                        new OutputStreamWriter(zipOutputStream),
                        resourcePath,
                        xmlTemplateFileName,
                        charset.name()
                        //charset.name()
                );
            } else {
                this.copyStream(zipOutputStream, zipFile.getInputStream(zipEntry));
            }
        }
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    /**
     * 使用FreeMarker自动生成Word文档
     * @param dataMap   生成Word文档所需要的数据
     * @param fileName  生成Word文档的全路径名称
     */
    public static void generateWord(Map<String, Object> dataMap, String fileName, String resourceName) throws Exception {
        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
        String path = StringUtils.join("word", File.separator, resourceName);
        ClassPathResource classPathResource = new ClassPathResource(path);
        FileUtils.copyInputStreamToFile(classPathResource.getInputStream(), new File(FileUtil.getWordTempFilePath().getPath(), resourceName));

        configuration.setDirectoryForTemplateLoading(FileUtil.getWordTempFilePath());
        // 设置FreeMarker生成Word文档所需要的模板
        Template t = configuration.getTemplate(resourceName, "UTF-8");
        // 创建一个Word文档的输出流
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8"));
        //FreeMarker使用Word模板和数据生成Word文档
        t.process(dataMap, out);
        out.flush();
        out.close();
    }
}
