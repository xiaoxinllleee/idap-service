package org.cmms.common.excel;


import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.cmms.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mkeasyx220 on 14-7-19.
 */
@Component
public class ExlsReport {
    private HSSFFormulaEvaluator eval;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss ");

    public String loadReport(String filePath, int rnum, int cnum) {
        try {
            if (!new File(filePath).exists()) {
                return "文件不存在！";
            }
            try {
                String html = buildHtmlString(filePath, rnum, cnum);
                return html;
            } catch (Throwable tx) {
                tx.printStackTrace();
                return "加载报表失败！" + tx.getMessage();
            }
        } catch (Throwable tx1) {
            tx1.printStackTrace();
            return "加载失败！" + tx1.getMessage();
        }
    }


    private String buildHtmlString(String fileName, int rnum, int cnum) throws Exception {

        HSSFWorkbook book = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(fileName)));
        eval = new HSSFFormulaEvaluator(book);

        boolean isAllowEdit = true;
        String neditR = ""; //禁止编辑行号
        String neditH = ""; //禁止编辑列号
        String yeditC = ""; //允许编辑单元格

        int brnum = 0;
        int ernum = 0;
        int bhnum = 0;
        int ehnum = 0;

        HSSFSheet sheet = book.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        if (rnum > 0) {
            lastRowNum = rnum - 1;
        }

        ArrayList<CellRangeAddress> cellRangeList = new ArrayList<CellRangeAddress>();
        getCombineCell(sheet, cellRangeList);

        StringBuilder htmlBuilder = new StringBuilder();
        for (int i = 0; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            int lastCellNum = row.getLastCellNum();
            if (cnum > 0) {
                lastCellNum = cnum - 1;
            }

            htmlBuilder.append("<tr>");
            for (int j = 0; j <= lastCellNum; j++) {
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }

                //单元格名称（XLS格式）
                String cellName = XlsDic.getCellName(i, j);
                String cellFormulaStr = getCellFormulaStr(cell);
                String cellFormatStr = "";


                HSSFCellStyle cellStyle = cell.getCellStyle();
                String styleStr = "";


                if ((brnum > 0 && i < brnum)
                        || (ernum > 0 && i > ernum)
                        || (bhnum > 0 && j < bhnum)
                        || (ehnum > 0 && j > ehnum)) {
                    styleStr = styleStr + "display:none;";
                }

                if (cellStyle != null) {

                    cellFormatStr = StringUtils.nvl(cellStyle.getDataFormatString());
                    if (cellFormatStr.endsWith("_")) {
                        cellFormatStr = cellFormatStr.substring(0, cellFormatStr.length() - 1);
                    }

                    //单元格对齐方式
                    short vAlignment = cellStyle.getVerticalAlignment();
                    short alignment = cellStyle.getAlignment();
                    if (vAlignment == HSSFCellStyle.VERTICAL_CENTER) {
                        //vertical-align:middle ;
                        styleStr = styleStr + "vertical-align:middle ;";
                    }
                    if (alignment == HSSFCellStyle.ALIGN_CENTER) {
                        //vertical-align:middle ;text-align: center;
                        styleStr = styleStr + "text-align: center;";
                    } else if (alignment == HSSFCellStyle.ALIGN_RIGHT) {
                        styleStr = styleStr + "text-align: right;";
                    }
                    //单元格背景颜色
                    String bgCol = "";
                    HSSFColor hssfColor = cellStyle.getFillForegroundColorColor();
                    if (hssfColor != null) {
                        short[] sc = hssfColor.getTriplet();
                        if (sc != null) {
                            bgCol = toHex(sc[0]);
                            bgCol += toHex(sc[1]);
                            bgCol += toHex(sc[2]);
                        }
                        if (!bgCol.equalsIgnoreCase("") && !bgCol.equalsIgnoreCase("000000")) {
                            styleStr = styleStr + "background-color: #" + bgCol + ";";
                        }
                    }

                    //单元格线条
//                    styleStr += "border-left: " + cellStyle.getBorderLeft() + "px solid #000000;";
//                    styleStr += "border-top: " + cellStyle.getBorderTop() + "px solid #000000;";
//                    styleStr += "border-right: " + cellStyle.getBorderRight() + "px solid #000000;";
//                    styleStr += "border-bottom: " + cellStyle.getBorderBottom() + "px solid #000000;";
//                    int bl = cellStyle.getBorderLeft();
//                    int bt = cellStyle.getBorderTop();
//                    int br = cellStyle.getBorderRight();
//                    int bb = cellStyle.getBorderBottom();
//                    String borderStr = "";

                    //字体
                    HSSFFont hssfFont = cellStyle.getFont(book);
                    if (hssfFont != null) {
                        if (hssfFont.getBoldweight() != HSSFFont.BOLDWEIGHT_NORMAL) {
                            styleStr += "font-weight:bolder;";
                        }
                        //字体大小
                        styleStr += "font-size:" + hssfFont.getFontHeightInPoints() + "px;";
                        //字体颜色
                        hssfColor = hssfFont.getHSSFColor(book);
                        if (hssfColor != null) {
                            short[] sc = hssfColor.getTriplet();
                            if (sc != null) {
                                bgCol = toHex(sc[0]);
                                bgCol += toHex(sc[1]);
                                bgCol += toHex(sc[2]);
                                if (!bgCol.equalsIgnoreCase("000000")) {
                                    styleStr += "color:#" + bgCol + ";";
                                }
                            }
                        }
                    }

                }

                String value = StringUtils.nvl(readCellData(cell));

                //单元格高度
                String cssHeight = " height=\"" + xls2htmlHeight(row.getHeight()) + "px\"";
                //单元格宽度
                String cssWidth = " width=\"" + xls2htmlWidth(sheet.getColumnWidth(j)) + "px\"";

                //允许编辑
                String editStr = isFormula(cell) ? " " : " contenteditable=true ";

                String tmpValue = value;
                if (tmpValue.contains(",")) {
                    tmpValue = tmpValue.replaceAll(",", "");
                }
                if (
                        (tmpValue.contains("填表人")
                                || tmpValue.contains("复核人")
                                || tmpValue.contains("负责人")
                                || tmpValue.contains("报表日期")
                                || tmpValue.contains("制表单位")
                                || tmpValue.contains("制表日期")
                                || tmpValue.contains("制表")) ||

                                yeditC.contains(","+cellName.toLowerCase()+",") ||

                                (isAllowEdit && !isFormula(cell) && !neditH.contains("," + j + ",") && !neditR.contains("," + i + ",")

                                )) {
                    editStr = " contenteditable=true ";
                } else {
                    editStr = " ";
                }


                String colSpanStr = "";
                String rowSpanStr = "";

                //合并单元格信息
                CellRangeAddress cra = getCellRangeAddress(cellRangeList, cell, sheet);
                if (cra != null) {

                    int colNum = (cra.getLastColumn() - cra.getFirstColumn()) + 1;
                    int hNum = (cra.getLastRow() - cra.getFirstRow()) + 1;
                    if (cra.getFirstColumn() != cell.getColumnIndex()) continue;
                    if (cra.getFirstRow() != row.getRowNum()) continue;
                    j += (colNum - 1);
                    if (colNum > 1) {
                        cssWidth = "";
                        colSpanStr = " colspan=\"" + colNum + "\" ";
                    }
                    if (hNum > 1) {
                        rowSpanStr = " rowspan=\"" + hNum + "\" ";
                    }


                }


                htmlBuilder.append("<td style=\"").append(styleStr).append("\" ")
                        .append(cssHeight).append(cssWidth)
                        .append(colSpanStr).append(rowSpanStr)
                        .append(editStr)
                        .append(" cn=\"").append(cellName).append("\" ")//单元格位置
                        .append(" fn=\"").append(cellFormulaStr).append("\" ")//公式
                        .append(" ft=\"").append(cellFormatStr).append("\" ")//格式字符串
                        .append(" val=\"").append(value).append("\" ")//真实的值
                        .append(" ov=\"").append("").append("\" ")//单元格激活时，保存临时的值
                        .append(">");
                htmlBuilder.append(value);
                htmlBuilder.append("</td>");
            }

            htmlBuilder.append("</tr>");
        }
//        htmlBuilder.append("</table>");
//        htmlBuilder.append("</body>");
//        htmlBuilder.append("</html>");

//        FileWriter fw = new FileWriter("d:/t1.html");
//        fw.write(htmlBuilder.toString());
//        fw.close();

        //copy(book);
        return htmlBuilder.toString();
    }


    public static boolean isFormula(HSSFCell cell) {
        return cell != null && cell.getCellType() == Cell.CELL_TYPE_FORMULA;
    }

    public static String getCellFormulaStr(HSSFCell cell) {
        if (cell != null && cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return cell.getCellFormula();
        } else {
            return "";
        }
    }

    private String readCellData(HSSFCell cell) {
        if (cell == null) return null;
        int cellType = cell.getCellType();
        String data = null;
        switch (cellType) {
            case Cell.CELL_TYPE_FORMULA:
//                data = cell.getCellFormula();
//                cell.setCellType(Cell.CELL_TYPE_STRING);
//                data = cell.getStringCellValue();
//                if (data != null) {
//                    data = data.toString().replaceAll("#N/A", "").trim();
//                }
//                if (NumberUtils.isNumber((String) data)) {
//                    double dv = NumberUtils.createDouble((String) data);
//                    data = formatterDouble(cell.getCellStyle().getDataFormatString(), dv);
//                }

                eval.evaluateFormulaCell(cell);
                try {
                    double dv1 = cell.getNumericCellValue();
                    data = formatterDouble("0.00", dv1);
                } catch (Throwable t) {
                    try {
                        data = cell.getStringCellValue();
                    } catch (Throwable t1) {
//                        try {
//                            data = cell.getCellFormula();
//                        } catch (Throwable t2) {
//                            data = "";
//                        }
                        data = "#N/A";
                    }
                }
                // data = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是在Date类型，则取得该Cell的Date值
                    Date date = cell.getDateCellValue();
                    if (date != null) {
                        data = dateFormat.format(date);
                    }
                } else {
                    // 如果是纯数字
                    // 取得当前cell的数值
//                if (isNeedDecimal) {
                    double dv = cell.getNumericCellValue();
//                }else {
//                    data = (int)cell.getNumericCellValue();
//                }
                    data = formatterDouble("0.00", dv);


                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                data = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                data = cell.getStringCellValue();
                if (NumberUtils.isNumber((String) data)) {
                    //modify by liuwei 2017-12-19 15:13:22
                    //解决证件号码为L结尾时报错的问题
                    try {
                        double dv = NumberUtils.createDouble((String) data);
                        data = formatterDouble("0.00", dv);
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                data = "";
                break;
            default:
                data = "";
                break;
        }
        return data;
    }


    /**
     * 合并单元格处理--加入list
     *
     * @param sheet
     * @return
     */
    public void getCombineCell(HSSFSheet sheet, List<CellRangeAddress> list) {
        // 获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        // 遍历合并单元格
        for (int i = 0; i < sheetmergerCount; i++) {
            // 获得合并单元格加入list中
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
    }

    /**
     * 判断单元格是否为合并单元格
     *
     * @param listCombineCell 存放合并单元格的list
     * @param cell            需要判断的单元格
     * @param sheet           sheet
     * @return
     */
    public static Boolean isCombineCell(List<CellRangeAddress> listCombineCell,
                                        HSSFCell cell, HSSFSheet sheet) {
        return getCellRangeAddress(listCombineCell, cell, sheet) != null;
    }

    public static CellRangeAddress getCellRangeAddress(List<CellRangeAddress> listCombineCell,
                                                       HSSFCell cell, HSSFSheet sheet) {
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        for (CellRangeAddress ca : listCombineCell) {
// 获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if (cell.getColumnIndex() <= lastC && cell.getColumnIndex() >= firstC) {
                if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
                    return ca;
                }
            }
        }
        return null;
    }

    public static int xls2htmlWidth(int w) {
        return w / 35;
    }

    public static int xls2htmlHeight(int h) {
        return h / 15;
    }

    public static String toHex(int i) {
        String s = Integer.toHexString(i);
        if (s.length() < 2) {
            s = "0" + s;
        }
        return s.toUpperCase();
    }

    /**
     * 格式化数字的显示
     *
     * @param formatStr
     * @param dv
     * @return
     */
    public static String formatterDouble(String formatStr, double dv) {
        formatStr = formatStr.trim();
        if ("General".equalsIgnoreCase(formatStr) || "@".equalsIgnoreCase(formatStr)) {
            if (dv == (int) dv) {
                return String.valueOf((int) dv);
            } else {
                return String.valueOf(dv);
            }
        }
        String data;
        //cell.getCellStyle().getDataFormatString()

//        if ("#,##0.00_".equalsIgnoreCase(formatStr)) {
        try {
            char[] chars = formatStr.toCharArray();
            char[] nchars = new char[chars.length];
            int ni = 0;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c == '(' || c == ')' || c == '_') continue;
                nchars[ni++] = c;
            }
            formatStr = new String(nchars);
            if (formatStr.endsWith("_")) {
                formatStr = formatStr.substring(0, formatStr.length() - 1);
            }
            DecimalFormat decimalFormat = new DecimalFormat(formatStr);
            return decimalFormat.format(dv);
//        }
        } catch (Throwable ex) {
            if (dv == (int) dv) {
                data = String.valueOf((int) dv);
            } else {
                data = String.valueOf(dv);
            }

            return data;
        }
    }
}
