package org.cmms.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 印章与签名重叠
 * @author 龚辉
 * @date 2023/9/22 9:18 周五
 */
@Slf4j
public class ImageOverlapUtil {


    /**
     * 将2张图片重叠在一起
     * @param signetPath 印章图片
     * @param signaturePath 签名图片
     * @param savePath 图片保存路径
     * @param dirPath  图片保存目录路径
     */
    public static void getTwoImageOverlap(String signetPath, String signaturePath,String savePath,String dirPath) {
        try {
            //检查图片保存目录路径是否存在，不存在就创建
            Path path= Paths.get(dirPath);
            if (!Files.exists(path)){
                Files.createDirectories(path);
            }
            //创建图片流，设置图片大小
            //部门图片
            File file1 = new File(signetPath);
            BufferedImage image1 = ImageIO.read(file1);
//            BufferedImage image1 = resizeImagePng(256,256,ImageIO.read(file1));
            //用户签名图片
            File file2 = new File(signaturePath);
//            BufferedImage image2 = resizeImagePng(150,150,ImageIO.read(file2));
            BufferedImage image2 = ImageIO.read(file2);

            //创建背景画布，大小和印章图片一样
            BufferedImage background = new BufferedImage(image1.getWidth(), image1.getHeight(), BufferedImage.TYPE_INT_RGB);
            // 设置背景色
            Graphics2D g2d = background.createGraphics();
            // 设置背景颜色
            g2d.setColor(Color.WHITE);
            // 填充
            g2d.fillRect(0, 0, background.getWidth(), background.getHeight());
            //设置印章图片
            g2d.drawImage(image1, 0,0,image1.getWidth(), image1.getHeight(), null);
            g2d.dispose();

            // 用户签名图片
            Graphics2D graphics = background.createGraphics();
            // 设置图片透明度
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.75f));
            int alpha = 0;
            for (int j1 = image2.getMinY(); j1 < image2.getHeight(); j1++) {
                for (int j2 = image2.getMinX(); j2 < image2.getWidth(); j2++) {
                    int rgb = image2.getRGB(j2, j1);
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    }
                    image2.setRGB(j2, j1, rgb);
                }
            }
            graphics.drawImage(image2,60,300,null);
            graphics.dispose();

            // 取掉白色背景  设置为透明图片
            ImageIcon imageIcon = new ImageIcon(background);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2DTwo = (Graphics2D) bufferedImage.getGraphics();
            g2DTwo.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
            g2DTwo.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            g2DTwo.dispose();
            ImageIO.write(bufferedImage, "PNG", new File(savePath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置图片的大小
     */
    public static BufferedImage resizeImagePng(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().drawImage(
                bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }

    public static void main(String[] args) {
        getTwoImageOverlap("C:\\Users\\Administrator\\Desktop\\yz.png","C:\\Users\\Administrator\\Desktop\\12.png","C:\\Users\\Administrator\\Desktop\\end.png","C:\\Users\\Administrator\\Desktop");
    }

}
