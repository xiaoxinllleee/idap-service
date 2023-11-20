package org.cmms.modules.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.cmms.modules.word.entity.CustomXWPFDocument;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
public class WordUtils2 {
    /**
     * 适用于word 2007
     *
     * @param param   需要替换的参数
     * @param template 模板
     */

    public static XWPFDocument generateWord(Map<String, Object> param, String template) {
        XWPFDocument doc = null;
        try {
            OPCPackage pack = POIXMLDocument.openPackage(template);//通过路径获取word模板
            doc = new CustomXWPFDocument(pack);
            //通过InputStream 获取模板，此方法适用于jar包部署
            //  doc = new CustomXWPFDocument(template);
            if (param != null && param.size() > 0) {
                //处理段落
                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                processParagraphs(paragraphList, param, doc);
                //处理表格
                Iterator<XWPFTable> it = doc.getTablesIterator();
                while (it.hasNext()) {
                    XWPFTable table = it.next();
                    List<XWPFTableRow> rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            List<XWPFParagraph> paragraphListTable = cell.getParagraphs();
                            processParagraphs(paragraphListTable, param, doc);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 处理段落
     *
     * @param paragraphList
     * @throws FileNotFoundException
     * @throws InvalidFormatException
     */
    public static void processParagraphs(List<XWPFParagraph> paragraphList, Map<String, Object> param, XWPFDocument doc) throws InvalidFormatException, FileNotFoundException {
        if (paragraphList != null && paragraphList.size() > 0) {
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (StringUtils.isNotBlank(text) && text.contains("@")) {
                        boolean isSetText = false;
                        Object key = param.get(text);
                        if (key != null) {
                            isSetText = true;
                            if (!text.contains("pic")) {//文本替换
                                text = text.replace(text, key.toString());
                            } else if (text.contains("pic")) {    //图片替换
                                if (StringUtils.isBlank(key.toString())) {
                                    text = text.replace(text, key.toString());
                                } else if (text.contains("qz")){
                                    text = text.replace(text, "");
                                    String pic = key.toString();
                                    int picType = getPictureType(pic);

                                    String[] split = pic.split(",");
                                    for (int i = 0; i < split.length; i++) {
                                        if (FileUtils.fileIsHave(split[i])){
                                            CTInline inline = run.getCTR().addNewDrawing().addNewInline();
                                            insertPicture(doc, split[i], inline, 60, 30, picType);
                                            insertPicture(doc, split[i], inline, 60, 30, picType);
                                        }else {
                                            log.info("代码运行环境找不到此照片|{}|",split[i]);
                                        }
                                    }
                                    /*if (FileUtils.fileIsHave(pic))
                                    {
                                        CTInline inline = run.getCTR().addNewDrawing().addNewInline();
                                        insertPicture(doc, pic, inline, 60, 30, picType);
                                    }else {
                                        log.info("代码运行环境找不到此照片|{}|",pic);
                                    }*/
                                }else {

                                    text = text.replace(text, "");
                                    String pic = key.toString();

                                    int width = 500;
                                    int height = 400;
                                    int picType = getPictureType(pic);
                                    if (pic.contains(",")) {
                                        String[] split = pic.split(",");
                                        for (int i = 0; i < split.length; i++) {
                                            if (FileUtils.fileIsHave(split[i])){
                                            CTInline inline = run.getCTR().addNewDrawing().addNewInline();
                                            insertPicture(doc, split[i], inline, width, height, picType);
                                            insertPicture(doc, split[i], inline, width, height, picType);
                                            }else {
                                                log.info("代码运行环境找不到此照片|{}|",split[i]);
                                            }
                                        }
                                    } else {
                                        if (FileUtils.fileIsHave(pic))
                                        {
                                        CTInline inline = run.getCTR().addNewDrawing().addNewInline();
                                        insertPicture(doc, pic, inline, width, height, picType);
                                        }else {
                                            log.info("代码运行环境找不到此照片|{}|",pic);
                                        }
                                    }
                                }
                            }
                        }
                        if (isSetText) {
                            run.setText(text, 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取图片对应类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if (picType != null) {
            if (picType.contains("png") || picType.contains("PNG")) {
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            } else if (picType.contains("dib") || picType.contains("DIB")) {
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            } else if (picType.contains("emf") || picType.contains("EMF")) {
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            } else if (picType.contains("jpg") || picType.contains("jpeg") || picType.contains("JPG") || picType.contains("JPEG")) {
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            } else if (picType.contains("wmf") || picType.contains("WMF")) {
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

    /**
     * insert Picture
     *
     * @param document
     * @param filePath
     * @param inline
     * @param width
     * @param height
     * @throws InvalidFormatException
     * @throws FileNotFoundException
     */
    private static void insertPicture(XWPFDocument document, String filePath,
                                      CTInline inline, int width,
                                      int height, int imgType) throws InvalidFormatException,
            FileNotFoundException {
        //通过流获取图片，因本人项目中，是通过流获取
        //document.addPictureData(imgFile,imgType);
        document.addPictureData(new FileInputStream(filePath), imgType);
        int id = document.getAllPictures().size() - 1;
        final int EMU = 9525;
        width *= EMU;
        height *= EMU;
        String blipId =
                document.getAllPictures().get(id).getPackageRelationship().getId();
        String picXml = getPicXml(blipId, width, height);
        XmlToken xmlToken = null;
        try {
            xmlToken = XmlToken.Factory.parse(picXml);
        } catch (XmlException xe) {
            xe.printStackTrace();
        }
        inline.set(xmlToken);
        inline.setDistT(0);
        inline.setDistB(0);
        inline.setDistL(0);
        inline.setDistR(0);
        CTPositiveSize2D extent = inline.addNewExtent();
        extent.setCx(width);
        extent.setCy(height);
        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
        docPr.setId(id);
        docPr.setName("IMG_" + id);
        docPr.setDescr("IMG_" + id);
    }

    /**
     * get the xml of the picture
     *
     * @param blipId
     * @param width
     * @param height
     * @return
     */
    private static String getPicXml(String blipId, int width, int height) {
        String picXml =
                "" + "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">" +
                        "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                        "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
                        "         <pic:nvPicPr>" + "            <pic:cNvPr id=\"" + 0 +
                        "\" name=\"Generated\"/>" + "            <pic:cNvPicPr/>" +
                        "         </pic:nvPicPr>" + "         <pic:blipFill>" +
                        "            <a:blip r:embed=\"" + blipId +
                        "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>" +
                        "            <a:stretch>" + "               <a:fillRect/>" +
                        "            </a:stretch>" + "         </pic:blipFill>" +
                        "         <pic:spPr>" + "            <a:xfrm>" +
                        "               <a:off x=\"0\" y=\"0\"/>" +
                        "               <a:ext cx=\"" + width + "\" cy=\"" + height +
                        "\"/>" + "            </a:xfrm>" +
                        "            <a:prstGeom prst=\"rect\">" +
                        "               <a:avLst/>" + "            </a:prstGeom>" +
                        "         </pic:spPr>" + "      </pic:pic>" +
                        "   </a:graphicData>" + "</a:graphic>";
        return picXml;
    }
}
