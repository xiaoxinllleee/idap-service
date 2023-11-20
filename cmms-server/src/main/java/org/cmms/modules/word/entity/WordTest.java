package org.cmms.modules.word.entity;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.cmms.common.constant.WordConstant;
import org.cmms.modules.util.DocxUtil2;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.util.WordUtils2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class WordTest{

    public static void main(String[] args) throws Exception{
        GrsxsqbDTO grsxsqbDTO = new GrsxsqbDTO();
        grsxsqbDTO.setKhmc("刘鑫");
        grsxsqbDTO.setZjhm("43070319941009685X");
        grsxsqbDTO.setMName("关于 刘鑫");
        DocxUtil2.exportX2Doc(grsxsqbDTO, "C:/temp/upload/moban/grdk.docx", "C:/temp/upload/grdk/test.docx");

    }

    public static void method()throws Exception{
        CustomXWPFDocument document = new CustomXWPFDocument();
        String path = "D://测试";
        FileOutputStream out = new FileOutputStream(new File(path + ".doc"));

        // 添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        // 设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setText("Java操作word文档，插入文字与图片");
        titleRun.setFontSize(20);
        titleRun.setFontFamily("宋体");
        titleRun.setBold(true);

        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun firstRun = firstParagraph.createRun();
        firstRun.setText("具体操作方式：");
        firstRun.setFontFamily("仿宋");
        firstRun.setFontSize(11);
        //换行
        firstParagraph.setWordWrapped(true);

        XWPFParagraph twoParagraph = document.createParagraph();
        twoParagraph.setIndentationFirstLine(500);
        XWPFRun twoRun = twoParagraph.createRun();
        twoRun.setFontFamily("仿宋");
        twoRun.setFontSize(11);
        twoRun.setText("继承POI操作Word中类XWPFDocument。");

        FileInputStream in = new FileInputStream("D://logo.jpg");
        byte[] ba = new byte[in.available()];
        in.read(ba);
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(ba);
        XWPFParagraph picture = document.createParagraph();
        //添加图片
        document.addPictureData(byteInputStream, CustomXWPFDocument.PICTURE_TYPE_JPEG);
        //图片大小、位置
        document.createPicture(document.getAllPictures().size() - 1, 100, 100, picture);

        document.write(out);
        out.close();


    }
}
