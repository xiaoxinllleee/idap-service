package org.cmms.modules.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocxUtil3 {
	
	/**
	 * 替换所有的表格
	 *
	 * @param xwpfTableList
	 * @param params
	 */
	public static void replaceInTables(List<XWPFTable> xwpfTableList, Map<String, String> params) {
	    for (XWPFTable table : xwpfTableList) {
	        replaceInTable(table, params);
	    }
	}

	/**
	 * 替换一个表格中的所有行
	 *
	 * @param xwpfTable
	 * @param params
	 */
	public static void replaceInTable(XWPFTable xwpfTable, Map<String, String> params) {
	    List<XWPFTableRow> rows = xwpfTable.getRows();
	    replaceInRows(rows, params);
	}


	/**
	 * 替换表格中的一行
	 *
	 * @param rows
	 * @param params
	 */
	public static void replaceInRows(List<XWPFTableRow> rows, Map<String, String> params) {
	    for (int i = 0; i < rows.size(); i++) {
	        XWPFTableRow row = rows.get(i);
	        replaceInCells(row.getTableCells(), params);
	    }
	}

	/**
	 * 替换一行中所有的单元格
	 *
	 * @param xwpfTableCellList
	 * @param params
	 */
	public static void replaceInCells(List<XWPFTableCell> xwpfTableCellList, Map<String, String> params) {
	    for (XWPFTableCell cell : xwpfTableCellList) {
	        replaceInCell(cell, params);
	    }
	}

	/**
	 * 替换表格中每一行中的每一个单元格中的所有段落
	 *
	 * @param cell
	 * @param params
	 */
	public static void replaceInCell(XWPFTableCell cell, Map<String, String> params) {
	    List<XWPFParagraph> cellParagraphs = cell.getParagraphs();
	    replaceInAllParagraphs(cellParagraphs, params);
	}
	
	/**
	 * 替换所有段落中的标记
	 *
	 * @param xwpfParagraphList
	 * @param params
	 */
	public static void replaceInAllParagraphs(List<XWPFParagraph> xwpfParagraphList, Map<String, String> params) {
	    for (XWPFParagraph paragraph : xwpfParagraphList) {
	        if (paragraph.getText() == null || paragraph.getText().equals("")) continue;

			for (String key : params.keySet()) {
	            if (paragraph.getText().contains(key)) {
	            	
	            	if (null == params.get(key) || "null".equals(params.get(key))) {
	            		params.put(key, "");
					}

	                replaceInParagraph(paragraph, key, params.get(key));
	            }
	        }
	    }
	}

	/**
	 * 替换段落中的字符串
	 *
	 * @param xwpfParagraph
	 * @param oldString
	 * @param newString
	 */
	public static void replaceInParagraph(XWPFParagraph xwpfParagraph, String oldString, String newString) {
	    Map<String, Integer> pos_map = findSubRunPosInParagraph(xwpfParagraph, oldString);
	    if (pos_map != null) {
	        XWPFRun xwpfRun = xwpfParagraph.insertNewRun(pos_map.get("end_pos") + 1);
				xwpfRun.setFontSize(12);
				xwpfRun.setFontFamily("宋体");
				if (oldString.contains("_"))
					xwpfRun.setUnderline(UnderlinePatterns.valueOf(1));
				xwpfRun.setText(newString);
			for (int i = pos_map.get("end_pos"); i >= pos_map.get("start_pos"); i--) {
	            xwpfParagraph.removeRun(i);
	        }
	    }
	}

	private static void insertPicture(XWPFDocument document, String filePath,
									  CTInline inline, int width,
									  int height,int imgType) throws InvalidFormatException,
			FileNotFoundException {
		//通过流获取图片，因本人项目中，是通过流获取
		//document.addPictureData(imgFile,imgType);
		document.addPictureData(new FileInputStream(filePath),imgType);
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
	 * 找到段落中子串的起始XWPFRun下标和终止XWPFRun的下标
	 *
	 * @param xwpfParagraph
	 * @param substring
	 * @return
	 */
	public static Map<String, Integer> findSubRunPosInParagraph(XWPFParagraph xwpfParagraph, String substring) {
	    List<XWPFRun> runs = xwpfParagraph.getRuns();
	    int start_pos = 0;
	    int end_pos = 0;
	    String subtemp = "";
	    for (int i = 0; i < runs.size(); i++) {
	        subtemp = "";
	        start_pos = i;
	        for (int j = i; j < runs.size(); j++) {
	            if (runs.get(j).getText(runs.get(j).getTextPosition()) == null)
	            	continue;
	            subtemp += runs.get(j).getText(runs.get(j).getTextPosition());
	            if (subtemp.equals(substring)) {
	                end_pos = j;
	                Map<String, Integer> map = new HashMap<>();
	                map.put("start_pos", start_pos);
	                map.put("end_pos", end_pos);
					return map;
	            }
	        }
	    }
	    return null;
	}


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

