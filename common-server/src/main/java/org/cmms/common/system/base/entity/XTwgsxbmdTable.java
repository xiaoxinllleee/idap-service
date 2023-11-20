package org.cmms.common.system.base.entity;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import java.util.List;

/**
 * @Date 2022/7/4
 * @Created by eran
 * 新田农商银行“无感授信”基础信息收集操作表（第一轮）白名单
 */
public class XTwgsxbmdTable {
    private HSSFWorkbook workbook;

    private HSSFSheet sheet;

    public XTwgsxbmdTable(HSSFWorkbook workbook, HSSFSheet sheet) {
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
        fontStyle.setBold(true);
        fontStyle.setFontHeightInPoints((short) 16);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);

        this.createCellRange(sheet, 0, 0, 0, 20, true);
        HSSFRow row = sheet.createRow(0);
        this.createCell(sheet, cellStyle, row, 0, tableName, false);

       /* this.createCellRange(sheet, 0, 0, 0, 2, true);
        HSSFRow row2 = sheet.createRow(1);
        this.createCell(sheet, cellStyle, row2, 0, "行政村名称", false);*/
    }

    public void setRowTwoNam(String tableName,String name2,String name3) {
        Font fontStyle = workbook.createFont();
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setBold(true);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);

        //this.createCellRange(sheet, 1, 1, 0, 20, false);
        HSSFRow row = sheet.createRow(1);
        this.createCell(sheet, cellStyle, row, 0, "行政村：", false);
        this.createCell(sheet, cellStyle, row, 2, tableName, false);
        this.createCell(sheet, cellStyle, row, 7, "评议员姓名：", false);
        this.createCell(sheet, cellStyle, row, 9, name2, false);
        this.createCell(sheet, cellStyle, row, 17, "单位：元", false);
        this.createCell(sheet, cellStyle, row, 19, name3, false);
        this.createCellRange(sheet, 1, 1, 0, 1, true);
        this.createCellRange(sheet, 1, 1, 2, 6, true);
        this.createCellRange(sheet, 1, 1, 7, 8, true);
        this.createCellRange(sheet, 1, 1, 9, 10, true);
        this.createCellRange(sheet, 1, 1, 17, 18, true);
    }


    /**
     * 设置表头
     */
    public void setTableHeader() {
        Font fontStyle = this.getDefaultFontStyle(workbook);
        fontStyle.setFontName("宋体");
        fontStyle.setBold(true);
        fontStyle.setFontHeightInPoints((short) 10);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);
        cellStyle.setWrapText(true);
        this.setCellBorderStyle(cellStyle);
        this.setCellBorderColor(cellStyle);

        HSSFRow row1 = sheet.createRow(2);
        List<String> list = Lists.newArrayList(
                "户号", "户主基础信息", "身份证号码"
                , "手机号码", "居住地址", "是否了解情况", "不符合授信原因", "原因备注"
                , "信用状况好可授信", "农村房产情况", "城区有无房产", "有无车辆", "是否在本地"
                , "长期居住地", "主营项目", "收入", "基础模型测算", "是否外出务工"
        );
        int col = 0;
        for (String name : list) {
            this.createCell(sheet, cellStyle, row1, col, name, true);

            if (col == 1){
                this.createCellRange(sheet, 2, 2, col, col+2, true);
                col++;
                col++;
            }else {
                this.createCellRange(sheet, 2, 3, col, col, true);
            }
            col++;
        }
        HSSFRow row3 = sheet.getRow(3);
        this.createCell(sheet, cellStyle, row3, 1, "与户主关系", true);
        this.createCell(sheet, cellStyle, row3, 2, "姓名", true);
        this.createCell(sheet, cellStyle, row3, 3, "年龄", true);
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
            HSSFRow row = sheet.createRow(rowIndex + 4);
//            for (int colIndex = 0; colIndex < colList.size(); colIndex++) {
//                row.createCell(colIndex + 1).setCellValue(colList.get(colIndex));
//                setCellBorderColor(cellStyle);
//                setCellBorderStyle(cellStyle);
//                CellUtil.getCell(row, colIndex + 1).setCellStyle(cellStyle);
//                sheet.setColumnWidth(colIndex + 1, getColumnDefaultWidth());
//            }

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
}
