package org.cmms.common.system.base.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/8/7
 * @Created by eran
 */
public class NsbErrorTable {
    private HSSFWorkbook workbook;

    private HSSFSheet sheet;

    public NsbErrorTable(HSSFWorkbook workbook, HSSFSheet sheet) {
        this.workbook = workbook;
        this.sheet = sheet;
    }

    /**
     * 设置表名
     *
     * @param tableName
     */
    public void setTableName(String tableName) {
        Font fontStyle = workbook.createFont();
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 18);
        fontStyle.setBold(true);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);

        this.createCellRange(sheet, 0, 0, 0, 10, true);
        HSSFRow row = sheet.createRow(0);
        this.createCell(sheet, cellStyle, row, 0, tableName, false);
    }

    public void setRowTwoNam(String yxdy) {
        Font fontStyle = workbook.createFont();
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 14);
        //kfontStyle.setBold(true);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);

        //this.createCellRange(sheet, 1, 1, 0, 20, false);
        HSSFRow row = sheet.createRow(1);
        this.createCell(sheet, cellStyle, row, 0, "镇村组：", false);
        this.createCell(sheet, cellStyle, row, 1, yxdy, false);
        this.createCell(sheet, cellStyle, row, 8, "单位：万元", false);
        this.createCellRange(sheet, 1, 1, 0, 0, true);
        this.createCellRange(sheet, 1, 1, 1, 7, true);
        this.createCellRange(sheet, 1, 1, 8, 10, true);
    }

    /**
     * 设置表头
     */
    public void setTableHeader() {
        Font fontStyle = this.getDefaultFontStyle(workbook);
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 10);
        //fontStyle.setBold(true);

        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);
        cellStyle.setWrapText(true);
        this.setCellBorderStyle(cellStyle);
        this.setCellBorderColor(cellStyle);

        HSSFRow row1 = sheet.createRow(2);
        List<String> list = new ArrayList<>();
        list.add("姓名");
        list.add("身份证号码");
        list.add("与户主关系");
        list.add("联系电话");
        list.add("新初评等级");
        list.add("新初评授信");
        list.add("新复评等级");
        list.add("新复评授信");
        list.add("产品类型");
        list.add("所属客户经理");
        list.add("镇村组");
        int col = 0;
        for (String name : list) {
            this.createCell(sheet, cellStyle, row1, col, name, true);
            col++;
        }
    }

    /**
     * 设置表尾
     */
    public void setTableFooter() {
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
        row.createCell(4).setCellValue(new HSSFRichTextString(StringUtils.join("复评小组签字：", "\n\n\n", StringUtils.repeat(" ", 44), "年    月    日")));
        CellUtil.getCell(row, 4).setCellStyle(cellStyle);
        row.createCell(7).setCellValue(new HSSFRichTextString(StringUtils.join("支行行长签字：", "\n\n\n", StringUtils.repeat(" ", 20), "年    月    日")));
        CellUtil.getCell(row, 7).setCellStyle(cellStyle);


        this.createCellRange(sheet, rowIndex, rowIndex, 0, 3, true);
        this.createCellRange(sheet, rowIndex, rowIndex, 4, 6, true);
        this.createCellRange(sheet, rowIndex, rowIndex, 7, 10, true);

        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setDataFormat(workbook.createDataFormat().getFormat("0"));
        sheet.setDefaultColumnStyle(0, cs);
        sheet.setDefaultColumnStyle(4, cs);
        sheet.setDefaultColumnStyle(10, cs);

        //sheet.setColumnWidth(1, 5000);
        //sheet.setColumnWidth(4, 6500);
        //sheet.setColumnWidth(9, 4500);
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
            HSSFRow row = sheet.createRow(rowIndex + 3);
            for (int colIndex = 0; colIndex < colList.size(); colIndex++) {
                row.createCell(colIndex).setCellValue(colList.get(colIndex));
                setCellBorderColor(cellStyle);
                setCellBorderStyle(cellStyle);
                CellUtil.getCell(row, colIndex ).setCellStyle(cellStyle);
                sheet.setColumnWidth(colIndex, getColumnDefaultWidth());
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
        HSSFDataFormat format = workbook.createDataFormat();
        //cellStyle.setDataFormat(format.getFormat("@"));
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
        RegionUtil.setBottomBorderColor(this.getCellBorderDefaultColor(), region, sheet,this.workbook);
        RegionUtil.setLeftBorderColor(this.getCellBorderDefaultColor(), region, sheet,this.workbook);
        RegionUtil.setRightBorderColor(this.getCellBorderDefaultColor(), region, sheet,this.workbook);
        RegionUtil.setTopBorderColor(this.getCellBorderDefaultColor(), region, sheet,this.workbook);
    }

    /**
     * 设置合并单元格边框样式
     *
     * @param sheet
     * @param region
     */
    public void setCellRangeBorderStyle(HSSFSheet sheet, CellRangeAddress region) {
        RegionUtil.setBorderBottom(this.getCellBorderDefaultStyle(), region, sheet,this.workbook);
        RegionUtil.setBorderLeft(this.getCellBorderDefaultStyle(), region, sheet,this.workbook);
        RegionUtil.setBorderRight(this.getCellBorderDefaultStyle(), region, sheet,this.workbook);
        RegionUtil.setBorderTop(this.getCellBorderDefaultStyle(), region, sheet,this.workbook);
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
}
