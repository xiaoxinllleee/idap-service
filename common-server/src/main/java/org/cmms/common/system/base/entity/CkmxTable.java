package org.cmms.common.system.base.entity;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import java.util.List;

/**
 * 小额农贷（已审批）年审表
 */
public class CkmxTable {

    private HSSFWorkbook workbook;

    private HSSFSheet sheet;

    public CkmxTable(HSSFWorkbook workbook, HSSFSheet sheet) {
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
        fontStyle.setFontHeightInPoints((short) 16);
        fontStyle.setBold(true);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);

        this.createCellRange(sheet, 0, 0, 0, 10, true);
        HSSFRow row = sheet.createRow(0);
        this.createCell(sheet, cellStyle, row, 0, tableName, false);
    }


    /**
     * 设置表头
     */
    public void setTableHeader() {
        Font fontStyle = this.getDefaultFontStyle(workbook);
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setBold(true);

        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);
        cellStyle.setWrapText(true);
        this.setCellBorderStyle(cellStyle);
        this.setCellBorderColor(cellStyle);

        HSSFRow row1 = sheet.createRow(1);
        List<String> list = Lists.newArrayList(
                "客户姓名" ,"证件号码"
        ,"存款累计(单位：元)"
        ,"活期存款(单位：元)"
        ,"定期存款(单位：元)" );
        int col = 0;
        for (String name : list) {
//            if (col == 1){
//                this.createCellRange(sheet, 2, 2, 1, 3, true);
//                this.createCellRange(sheet, 3, 3, 1, 1, true);
//                this.createCell(sheet, cellStyle, row1, col, "户籍基础信息", true);
//                this.createCell(sheet, cellStyle, row1, col, name, true);
//            }else {
                this.createCell(sheet, cellStyle, row1, col, name, true);

                if (col == 2 || col == 5 || col == 8){
                    this.createCellRange(sheet, 1, 1, col, col+2, true);
                    col++;
                    col++;
                }else {
                    this.createCellRange(sheet, 1, 2, col, col, true);
                }
            //}
            col++;
        }

        HSSFRow row3 = sheet.getRow(2);
        this.createCell(sheet, cellStyle, row3, 2, "余额", true);
        this.createCell(sheet, cellStyle, row3, 3, "月日平", true);
        this.createCell(sheet, cellStyle, row3, 4, "年日平", true);

        this.createCell(sheet, cellStyle, row3, 5, "余额", true);
        this.createCell(sheet, cellStyle, row3, 6, "月日平", true);
        this.createCell(sheet, cellStyle, row3, 7, "年日平", true);

        this.createCell(sheet, cellStyle, row3, 8, "余额", true);
        this.createCell(sheet, cellStyle, row3, 9, "月日平", true);
        this.createCell(sheet, cellStyle, row3, 10, "年日平", true);

       /* HSSFRow row4 = sheet.getRow(3);
        this.createCell(sheet, cellStyle, row4, 1, "余额", true);
        this.createCell(sheet, cellStyle, row4, 2, "月日平", true);
        this.createCell(sheet, cellStyle, row4, 3, "年日平", true);

        HSSFRow row5 = sheet.getRow(4);
        this.createCell(sheet, cellStyle, row5, 1, "余额", true);
        this.createCell(sheet, cellStyle, row5, 2, "月日平", true);
        this.createCell(sheet, cellStyle, row5, 3, "年日平", true);*/
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
                row.createCell(colIndex ).setCellValue(colList.get(colIndex));
                setCellBorderColor(cellStyle);
                setCellBorderStyle(cellStyle);
                CellUtil.getCell(row, colIndex ).setCellStyle(cellStyle);
                sheet.setColumnWidth(colIndex , getColumnDefaultWidth());
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
