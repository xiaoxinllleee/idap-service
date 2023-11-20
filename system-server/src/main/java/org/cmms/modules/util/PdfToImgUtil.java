package org.cmms.modules.util;


import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import sun.security.tools.PathList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date 2023/4/17
 * @Created by eran
 * 征信报告PDF转为图片
 */
@Slf4j
public class PdfToImgUtil {
    public static int getPageCount(String filePath) {
        int pageCount = 0;
        boolean file = FileUtil.isFile(filePath);
        if (file){
            InputStream is = null;
            PDDocument doc = null;
            try {
                is = new FileInputStream(filePath);
                doc = PDDocument.load(is);
                PDFRenderer renderer = new PDFRenderer(doc);
                pageCount = doc.getNumberOfPages();
            } catch (Exception e) {
                System.out.println("=== 获取pdf页数出错===");
                e.printStackTrace();
                log.error("=== 获取pdf页数出错===", e);
            }finally {
                try {
                    if(doc!=null){
                        doc.close();
                    }
                    if(is!=null){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return pageCount;
    }
    /**
     * 根据指定的页数转换PDF为图片
     * @param filePath
     * @param page
     * @return
     */
    public static String pdfToImg(String filePath, int page) {
        boolean file = FileUtil.isFile(filePath);
        String imgPath = "";
        if (file){
            // 举例说明    d://pdf/adc.pdf  生成的图片就是 d://pdf/adc0.jpg d://pdf/adc1.jpg d://pdf/adc2.jpg
            String substring = filePath.replace(".pdf","");
            //判断文件是否存在
            String parentPath = FileUtil.getParent(filePath, 1);
            String fileName = FileUtil.getPrefix(filePath);
            String imgParentPath = parentPath + File.separator + fileName + "_img";
            if (!FileUtil.exist(imgParentPath)) {
                FileUtil.mkdir(imgParentPath);
            }
            imgPath = imgParentPath + File.separator + fileName + "_" + page + ".jpg";

            if (FileUtil.exist(imgPath)) {
                //已经存在的图片
                return imgPath;
            }
            log.info("===pdf开始转换成图片===");
            InputStream is = null;
            PDDocument doc = null;
            try {
                is = new FileInputStream(filePath);
                doc = PDDocument.load(is);
                PDFRenderer renderer = new PDFRenderer(doc);
                int pageCount = doc.getNumberOfPages();
                if (page > pageCount) {
                    return null;
                }
                // dpi，图片像素点，dpi越高图片体积越大，216很清晰，105体积稳定
                BufferedImage image = renderer.renderImageWithDPI(page-1, 216);
                // 格式为JPG
                FileOutputStream fileOutputStream = new FileOutputStream(imgPath);
                ImageIO.write(image, "jpg", fileOutputStream);
                //list.add(fwpath+"/"+zjhm+i+".jpg");
                fileOutputStream.flush();
                fileOutputStream.close();
                log.info("===完成转换成图片,本次转化完成{}张图片===",pageCount);
            } catch (Exception e) {
                System.out.println("=== pdf转图片出错===");
                e.printStackTrace();
                log.error("=== pdf转图片出错===", e);
            }finally {
                try {
                    if(doc!=null){
                        doc.close();
                    }
                    if(is!=null){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else {
            log.info("=== pdfToImg {} 找不到pdf ===",filePath);
        }
        return imgPath;
    }

    public static LinkedList pdfToImg(String filePath){
        boolean file = FileUtil.isFile(filePath);
        LinkedList pathList =new LinkedList();
        if (file){
            // 举例说明    d://pdf/adc.pdf  生成的图片就是 d://pdf/adc0.jpg d://pdf/adc1.jpg d://pdf/adc2.jpg
            String substring = filePath.replace(".pdf","");
            log.info("===pdf开始转换成图片===");
            InputStream is = null;
            PDDocument doc = null;
            try {
                is = new FileInputStream(filePath);
                doc = PDDocument.load(is);
                PDFRenderer renderer = new PDFRenderer(doc);
                int pageCount = doc.getNumberOfPages();
                for (int i = 0; i < pageCount; i++) {
                    // dpi，图片像素点，dpi越高图片体积越大，216很清晰，105体积稳定
                    BufferedImage image = renderer.renderImageWithDPI(i, 216);
                    // 格式为JPG
                    pathList.add(substring + i + ".jpg");
                    FileOutputStream fileOutputStream = new FileOutputStream(substring + i + ".jpg");
                    ImageIO.write(image, "jpg", fileOutputStream);
                    //list.add(fwpath+"/"+zjhm+i+".jpg");
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                log.info("===完成转换成图片,本次转化完成{}张图片===",pageCount);
            } catch (Exception e) {
                System.out.println("=== pdf转图片出错===");
                e.printStackTrace();
            }finally {
                try {
                    if(doc!=null){
                        doc.close();
                    }
                    if(is!=null){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else {
            log.info("=== pdfToImg {} 找不到pdf ===",filePath);
        }
        return pathList;
    }

    /**
     *  拼接图片
     * @param paths
     * @param isHorizontal 是否横向拼接
     * @param targetFile
     * @throws IOException
     */
    public static void montageImages(List<String> paths, boolean isHorizontal, String targetFile) throws IOException {
        // 图片
        List<BufferedImage> pics = new ArrayList<>();
        for (String path : paths) {
            pics.add(ImageIO.read(new File(path)));
        }

        // 计算宽高
        int newWidth = isHorizontal ? pics.stream().mapToInt(BufferedImage::getWidth).sum() : pics.get(0).getWidth();
        int newHeight = isHorizontal ? pics.get(0).getHeight() : pics.stream().mapToInt(BufferedImage::getHeight).sum();

        // 图片处理
        BufferedImage imageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        AtomicInteger pixelPointsWidth = new AtomicInteger();
        AtomicInteger pixelPointsHeight = new AtomicInteger();

        pics.forEach(v -> {
            int width = v.getWidth();
            int height = v.getHeight();

            int[] image = new int[width * height];
            image = v.getRGB(0, 0, width, height, image, 0, width);

            if (isHorizontal) {
                imageNew.setRGB(pixelPointsWidth.get(), 0, width, newHeight, image, 0, width);
                pixelPointsWidth.addAndGet(width);
            } else {
                imageNew.setRGB(0, pixelPointsHeight.get(), newWidth, height, image, 0, newWidth);
                pixelPointsHeight.addAndGet(height);
            }
        });

        // 图片生成
        try {
            ImageIO.write(imageNew, targetFile.split("\\.")[1], new File(targetFile));
            System.out.println("生成成功");
        } catch (IOException e) {
            System.out.println("生成失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        LinkedList linkedList = pdfToImg("C:\\Users\\Administrator\\Desktop\\百行\\报告模版原型.pdf");
        montageImages(linkedList,false,"C:\\Users\\Administrator\\Desktop\\百行\\报告模版原型IMG.jpg");
    }
}
