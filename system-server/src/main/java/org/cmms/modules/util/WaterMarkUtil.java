package org.cmms.modules.util;

import cn.hutool.core.io.IoUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class WaterMarkUtil {

    // 水印透明度
    private static final float alpha = 0.8f;
    // 水印横向位置
    private static int positionWidth = 100;
    // 水印纵向位置
    private static int positionHeight = 300;
    //字体大小
    private static int mainSize = 90;
    // 水印文字字体
    private static final Font font = new Font("宋体", Font.BOLD, mainSize);
    // 水印文字颜色
    private static final Color color = Color.gray;


    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度、是否循环
     *
     * @param text 文字
     * @param srcImgPath 源图片
     * @param targetPath 生成图片
     * @param degree 旋转角度
     * @param isFor 是否循环
     */
    public static void markImage(String text, String srcImgPath, String targetPath, Integer degree,boolean isFor) {
        int textLength = text.length();

        OutputStream os = null;
        try {
            // 0、图片类型
            String type = srcImgPath.substring(srcImgPath.indexOf(".") + 1, srcImgPath.length());

            // 1、源图片
            Image srcImg = ImageIO.read(new File(srcImgPath));

            int imgWidth = srcImg.getWidth(null);
            int imgHeight = srcImg.getHeight(null);

            System.out.println(imgWidth);
            System.out.println(imgHeight);

            BufferedImage buffImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            positionWidth = 50;
            positionHeight = imgHeight-30;
            if (isFor){
                int width = srcImg.getWidth(null);
                int height = srcImg.getHeight(null);
                int x = 0;
                int y = 0;
                double count = 1.5;
                while ( x < width*count ) {  // 循环添加多个水印logo
                    y = -height / 2;
                    while( y < height*count ) {
                        g.drawString(text, x, y);  // ④
                        y += mainSize * 9;
                    }
                    x += textLength*mainSize*2;
                }
            }else {
                g.drawString(text,positionWidth, positionHeight);
            }

            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(targetPath);
            // ImageIO.write(buffImg, "JPG", os);
            ImageIO.write(buffImg, type.toUpperCase(), os);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os){
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     *
     * @param text
     * @param degree
     */
    public static void markImageByIO(String text, InputStream inputStream, OutputStream outputStream,
                                     Integer degree, String typeName) {
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(inputStream);

            int imgWidth = srcImg.getWidth(null);
            int imgHeight = srcImg.getHeight(null);
            BufferedImage buffImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)

            g.drawString(text, positionWidth, positionHeight);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            ImageIO.write(buffImg, typeName.toUpperCase(), outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean markPdf(String inputFile, String outputFile, String waterMarkName) {
        File file = new File(inputFile);
        if (!file.exists()) {
            return false;
        }
        PdfReader reader = null;
        PdfStamper stamper = null;
        try{
            reader = new PdfReader(inputFile);
            stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
            //这里的字体设置比较关键，这个设置是支持中文的写法
            BaseFont base = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 使用系统字体

            PdfContentByte under;
            com.itextpdf.text.Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            //这里是透明度设置
            gs.setFillOpacity(0.2f);
            //这里是条纹不透明度
            gs.setStrokeOpacity(0.2f);
            JLabel label = new JLabel();
            FontMetrics metrics;
            int textH = 0;
            int textW = 0;
            label.setText(waterMarkName);
            metrics = label.getFontMetrics(label.getFont());
            //字符串的高,   只和字体有关
            textH = metrics.getHeight();
            //字符串的宽
            textW = metrics.stringWidth(label.getText());
            //pdf页数
            int total = reader.getNumberOfPages() + 1;
            //这个循环是确保每一张PDF都加上水印
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                // 获得PDF最顶层
                under = stamper.getOverContent(i);
                under.setGState(gs);
                under.saveState();
                under.beginText();
                under.setFontAndSize(base, 15);
                under.setTextMatrix(30, 30);
                under.setColorFill(BaseColor.GRAY);
                for (int height = textH; height < pageRect.getHeight() * 2; height = height + textH * 14) {
                    for (int width = textW; width < pageRect.getWidth() * 1.5 + textW; width = width + textW * 4) {
                        // rotation:倾斜角度
                        under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, width - textW, height - textH, 30);
                    }
                }
                under.endText();
            }
            return true;
        } catch (IOException e) {
            System.out.println("添加水印失败！错误信息为: " + e);
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            System.out.println("添加水印失败！错误信息为: " + e);
            e.printStackTrace();
            return false;
        } finally {
            //关闭流
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                reader.close();
            }
        }

    }

    public static void main(String[] args) {
        String srcImgPath = "d://3.jpg";
        String text = "这个是水印";
        String targerTextPath = "d://"+System.currentTimeMillis()+".jpg";

        System.out.println("给图片添加水印文字开始...");
        // 给图片添加水印文字
        markImage(text, srcImgPath, targerTextPath,-20,true);
        System.out.println("给图片添加水印文字结束...");

    }

}
