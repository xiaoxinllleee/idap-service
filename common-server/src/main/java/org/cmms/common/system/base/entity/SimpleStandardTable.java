package org.cmms.common.system.base.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import java.util.List;
import java.util.Map;

/**
 * 只有一层表头
 */
public class SimpleStandardTable {

    private HSSFWorkbook workbook;

    private HSSFSheet sheet;

    public SimpleStandardTable(HSSFWorkbook workbook, HSSFSheet sheet) {
        this.workbook = workbook;
        this.sheet = sheet;
    }

    /**
     * 设置表名
     *
     * @param tableName
     */
    public void setTableName(String tableName, int lastCol) {
        Font fontStyle = workbook.createFont();
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 16);
        fontStyle.setBold(true);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);

        this.createCellRange(sheet, 0, 0, 0, lastCol - 1, true);
        HSSFRow row = sheet.createRow(0);
        this.createCell(sheet, cellStyle, row, 0, tableName, false);
    }


    /**
     * 设置表头
     */
    public void setTableHeader(List<String> list) {
        Font fontStyle = this.getDefaultFontStyle(workbook);
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setBold(true);

        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);
        cellStyle.setWrapText(true);
        this.setCellBorderStyle(cellStyle);
        this.setCellBorderColor(cellStyle);

        HSSFRow row1 = sheet.createRow(1);
        int col = 0;
        for (String name : list) {
//            int width = 0;
//            if (col == 4 || col == 6) {
//                width = 5000;
//            }
            this.createCell(sheet, cellStyle, row1, col, name, true);
            this.createCellRange(sheet, 1, 1, col, col, true);

            col++;
        }

    }


    /**
     * 设置表数据
     *
     * @param rowList
     */
    public void setTableData(List<List<String>> rowList) {
        Font fontStyle = getDefaultFontStyle(workbook);
        HSSFCellStyle cellStyle = setCellStyle(workbook, fontStyle);
        cellStyle.setWrapText(true);
        for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) {
            List<String> colList = rowList.get(rowIndex);
            HSSFRow row = sheet.createRow(rowIndex + 2);
            for (int colIndex = 0; colIndex < colList.size(); colIndex++) {
                row.createCell(colIndex).setCellValue(colList.get(colIndex));
                setCellBorderColor(cellStyle);
                setCellBorderStyle(cellStyle);
                CellUtil.getCell(row, colIndex).setCellStyle(cellStyle);
                sheet.setColumnWidth(colIndex, getColumnDefaultWidth());
            }
        }
    }

    /**
     * 设置表数据
     *
     * @param rowList
     */
    public void setTableData(JSONArray rowList) {
        Font fontStyle = getDefaultFontStyle(workbook);
        HSSFCellStyle cellStyle = setCellStyle(workbook, fontStyle);
        cellStyle.setWrapText(true);
        for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) {
            JSONObject obj = (JSONObject) rowList.get(rowIndex);
            HSSFRow row = sheet.createRow(rowIndex + 2);
            int colIndex=0;
            for (Map.Entry<String, Object> entry : obj.entrySet()) {
                row.createCell(colIndex).setCellValue(String.valueOf(entry.getValue()));
                setCellBorderColor(cellStyle);
                setCellBorderStyle(cellStyle);
                CellUtil.getCell(row, colIndex).setCellStyle(cellStyle);
                sheet.setColumnWidth(colIndex, getColumnDefaultWidth());
                colIndex++;
            }
        }
    }
    /**
     * 创建单元格
     *
     * @param sheet
     * @param cellStyle
     * @param row
     * @param colIndex
     * @param cellValue
     * @param setColumnWidth
     */
    public void createCell(HSSFSheet sheet,
                           HSSFCellStyle cellStyle,
                           HSSFRow row,
                           int colIndex,
                           String cellValue,
                           boolean setColumnWidth, int width) {
        row.createCell(colIndex).setCellValue(cellValue);
        CellUtil.getCell(row, colIndex).setCellStyle(cellStyle);
        if (setColumnWidth) {
            if (width > 0) {
                sheet.setColumnWidth(colIndex, width);
            } else {
                sheet.setColumnWidth(colIndex, getColumnDefaultWidth());
            }
        }
    }

    public void createCell(HSSFSheet sheet,
                           HSSFCellStyle cellStyle,
                           HSSFRow row,
                           int colIndex,
                           String cellValue,
                           boolean setColumnWidth) {
        row.createCell(colIndex).setCellValue(cellValue);
        CellUtil.getCell(row, colIndex).setCellStyle(cellStyle);
        if (setColumnWidth) {
            sheet.setColumnWidth(colIndex, getColumnDefaultWidth());
        }
    }

    /**
     * 创建合并单元格
     *
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @param setBorder
     */
    public void createCellRange(HSSFSheet sheet,
                                int firstRow,
                                int lastRow,
                                int firstCol,
                                int lastCol,
                                boolean setBorder) {
        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(region);
        if (setBorder) {
            setCellRangeBorderStyle(sheet, region);
            setCellRangeBorderColor(sheet, region);
        }
    }

    /**
     * 获取列默认宽度
     *
     * @return
     */
    public int getColumnDefaultWidth() {
        return 3500;
    }

    /**
     * 设置单元格样式
     *
     * @param workbook
     * @param fontStyle
     * @return
     */
    public HSSFCellStyle setCellStyle(HSSFWorkbook workbook, Font fontStyle) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        if (fontStyle != null) {
            cellStyle.setFont(fontStyle);
        }
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return cellStyle;
    }

    /**
     * 获取默认字体样式
     *
     * @param workbook
     * @return
     */
    public Font getDefaultFontStyle(HSSFWorkbook workbook) {
        Font fontStyle = workbook.createFont();
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 11);
        return fontStyle;
    }

    /**
     * 获取单元格边框默认颜色
     *
     * @return
     */
    public short getCellBorderDefaultColor() {
        return IndexedColors.BLACK.getIndex();
    }

    /**
     * 获取单元格边框默认样式
     *
     * @return
     */
    public short getCellBorderDefaultStyle() {
        return CellStyle.BORDER_THIN;
    }

    /**
     * 设置单元格边框颜色
     *
     * @param cellStyle
     */
    public void setCellBorderColor(HSSFCellStyle cellStyle) {
        cellStyle.setLeftBorderColor(this.getCellBorderDefaultColor());
        cellStyle.setBottomBorderColor(this.getCellBorderDefaultColor());
        cellStyle.setRightBorderColor(this.getCellBorderDefaultColor());
        cellStyle.setTopBorderColor(this.getCellBorderDefaultColor());
    }

    /**
     * 设置单元格边框样式
     *
     * @param cellStyle
     */
    public void setCellBorderStyle(HSSFCellStyle cellStyle) {
        cellStyle.setBorderBottom(this.getCellBorderDefaultStyle());
        cellStyle.setBorderLeft(this.getCellBorderDefaultStyle());
        cellStyle.setBorderRight(this.getCellBorderDefaultStyle());
        cellStyle.setBorderTop(this.getCellBorderDefaultStyle());
    }

    /**
     * 设置合并单元格边框颜色
     *
     * @param sheet
     * @param region
     */
    public void setCellRangeBorderColor(HSSFSheet sheet, CellRangeAddress region) {
        RegionUtil.setBottomBorderColor(this.getCellBorderDefaultColor(), region, sheet, this.workbook);
        RegionUtil.setLeftBorderColor(this.getCellBorderDefaultColor(), region, sheet, this.workbook);
        RegionUtil.setRightBorderColor(this.getCellBorderDefaultColor(), region, sheet, this.workbook);
        RegionUtil.setTopBorderColor(this.getCellBorderDefaultColor(), region, sheet, this.workbook);
    }

    /**
     * 设置合并单元格边框样式
     *
     * @param sheet
     * @param region
     */
    public void setCellRangeBorderStyle(HSSFSheet sheet, CellRangeAddress region) {
        RegionUtil.setBorderBottom(this.getCellBorderDefaultStyle(), region, sheet, this.workbook);
        RegionUtil.setBorderLeft(this.getCellBorderDefaultStyle(), region, sheet, this.workbook);
        RegionUtil.setBorderRight(this.getCellBorderDefaultStyle(), region, sheet, this.workbook);
        RegionUtil.setBorderTop(this.getCellBorderDefaultStyle(), region, sheet, this.workbook);
    }

    public void setHSSFValidation(String[] textlist, int firstRow, int endRow, int firstCol,
                                  int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(
                regions, constraint);
        this.sheet.addValidationData(data_validation_list);
    }


    //浏阳年审表尾
    public void setLyNsTableFooter(int c1,int c2,int c3) {
        int lastRowNum = (sheet.getLastRowNum());
        int rowIndex = lastRowNum + 1;

        Font fontStyle = this.getDefaultFontStyle(workbook);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setFont(fontStyle);
        cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);

        setCellBorderStyle(cellStyle);
        setCellBorderColor(cellStyle);

        HSSFRow row = sheet.createRow(rowIndex);
        row.setHeight((short) 1200);

        row.createCell(0).setCellValue(new HSSFRichTextString(StringUtils.join("初评小组签字：", "\n\n\n",StringUtils.repeat(" ", 32), "年    月    日")));
        CellUtil.getCell(row, 0).setCellStyle(cellStyle);
        row.createCell(c1).setCellValue(new HSSFRichTextString(StringUtils.join("复评小组签字：", "\n\n\n", StringUtils.repeat(" ", 44), "年    月    日")));
        CellUtil.getCell(row, c1).setCellStyle(cellStyle);
        row.createCell(c2).setCellValue(new HSSFRichTextString(StringUtils.join("支行行长签字：", "\n\n\n", StringUtils.repeat(" ", 20), "年    月    日")));
        CellUtil.getCell(row, c2).setCellStyle(cellStyle);


        this.createCellRange(sheet, rowIndex, rowIndex, 0, c1-1, true);
        this.createCellRange(sheet, rowIndex, rowIndex, c1, c2-1, true);
        this.createCellRange(sheet, rowIndex, rowIndex, c2, c3, true);

        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setDataFormat(workbook.createDataFormat().getFormat("0"));
        sheet.setDefaultColumnStyle(0, cs);
        sheet.setDefaultColumnStyle(c1, cs);
        sheet.setDefaultColumnStyle(c2, cs);

        //sheet.setColumnWidth(1, 5000);
        //sheet.setColumnWidth(4, 6500);
        //sheet.setColumnWidth(9, 4500);
    }
}
