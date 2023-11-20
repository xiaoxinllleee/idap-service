package org.cmms.common.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * @description: pdf相关的工具类
 */
@Component
public class PdfUtils {

    /**
     * 图片转换PDF的公共接口
     *
     * @param file     SpringMVC获取的图片文件
     * @param response HttpServletResponse
     * @throws IOException       IO异常
     * @throws DocumentException PDF文档异常
     */
    public static void imageToPdf(MultipartFile file, HttpServletResponse response) throws IOException, DocumentException {
        File pdfFile = generatePdfFile(file);
        downloadPdfFile(pdfFile, response);
    }



    /**
     * 将图片转换为PDF文件
     *
     * @param file SpringMVC获取的图片文件
     * @return PDF文件
     * @throws IOException       IO异常
     * @throws DocumentException PDF文档异常
     */
    private static File generatePdfFile(MultipartFile file) throws IOException, DocumentException {
        String fileName = file.getOriginalFilename();
        String pdfFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".pdf";
        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter.getInstance(doc, new FileOutputStream(pdfFileName));
        doc.open();
        doc.newPage();
        Image image = Image.getInstance(file.getBytes());
        float height = image.getHeight();
        float width = image.getWidth();
        int percent = getPercent(height, width);
        image.setAlignment(Image.MIDDLE);
        image.scalePercent(percent);
        doc.add(image);
        doc.close();
        File pdfFile = new File(pdfFileName);
        return pdfFile;
    }

    /**
     * 将图片转换为PDF文件
     *
     * @return PDF文件
     * @throws IOException       IO异常
     * @throws DocumentException PDF文档异常
     */
    public static String ImagesGeneratePdfFile(ArrayList<String>  filepath,String savePath ,String pdfFileName) throws IOException, DocumentException {
        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
        String wzlj= savePath+"/"+pdfFileName+".pdf";
        PdfWriter.getInstance(doc, new FileOutputStream(wzlj));
        doc.open();
        for(String path :filepath){
            doc.newPage();
            Image image = Image.getInstance(path);
            float height = image.getHeight();
            float width = image.getWidth();
            int percent = getPercent(height, width);
            image.setAlignment(Image.MIDDLE);
            image.scalePercent(percent);
            doc.add(image);
        }
        doc.close();
        return wzlj;
        //File pdfFile = new File(pdfFileName);
    }

    /**
     * 将图片转换为PDF文件
     *
     * @return PDF文件
     * @throws IOException       IO异常
     * @throws DocumentException PDF文档异常
     */
    public static String  ImageOnegeneratePdfFile(String filepath,String savePath ,String pdfFileName) {
        String wzlj= null;
        Document doc=null;
        FileOutputStream fileOutputStream = null;
        try {
            wzlj = savePath+pdfFileName+"_"+System.currentTimeMillis()+".pdf";
            fileOutputStream=new FileOutputStream(wzlj);
            doc = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter.getInstance(doc,fileOutputStream);
            doc.open();
            doc.newPage();
            Image image = Image.getInstance(filepath);
            float height = image.getHeight();
            float width = image.getWidth();
            int percent = getPercent(height, width);
            image.setAlignment(Image.MIDDLE);
            image.scalePercent(percent);
            doc.add(image);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            doc.close();
            try {
                fileOutputStream.close();
            } catch (IOException e) {
            }
        }
        return wzlj;

    }


    /**
     *
     * 用于下载PDF文件
     *
     * @param pdfFile  PDF文件
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    private static void downloadPdfFile(File pdfFile, HttpServletResponse response) throws IOException {
        FileInputStream fis = new FileInputStream(pdfFile);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();

        response.reset();
        response.setHeader("Content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(pdfFile.getName(), "UTF-8"));
        OutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
    }


    /**
     * 等比压缩，获取压缩百分比
     * @return 压缩百分比
     */
/*    private static int getPercent(float height, float weight) {
        float percent = 0.0F;
        if (height > weight) {
            percent = PageSize.A4.getHeight() / height * 100;
        } else {
            percent = PageSize.A4.getWidth() / weight * 100;
        }
        return Math.round(percent);
    }*/
    public static int getPercent(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        if (h > w) {
            p2 = 297 / h * 274;
        } else {
            p2 = 210 / w * 268;
        }
        p = Math.round(p2);
        return p;
    }

    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList();

        list.add("F://zhcs/1.png");
        list.add("F://zhcs/2.png");
        list.add("F://zhcs/3.png");
        try {
           // PdfUtils.ImagesGeneratePdfFile(list,"F://zhcs","hbhpdf");
            PdfUtils.ImageOnegeneratePdfFile("F://zhcs/2.png","F://zhcs","hbhpdf2");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

