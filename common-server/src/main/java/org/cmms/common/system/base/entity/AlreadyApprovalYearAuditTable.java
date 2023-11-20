package org.cmms.common.system.base.entity;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import java.util.List;

/**
 * 小额农贷（已审批）年审表
 */
public class AlreadyApprovalYearAuditTable {

    private HSSFWorkbook workbook;

    private HSSFSheet sheet;

    public AlreadyApprovalYearAuditTable(HSSFWorkbook workbook, HSSFSheet sheet) {
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
        fontStyle.setFontName("黑体");
        fontStyle.setFontHeightInPoints((short) 18);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);

        this.createCellRange(sheet, 1, 1, 1, 15, false);
        HSSFRow row = sheet.createRow(1);
        this.createCell(sheet, cellStyle, row, 1, tableName, false);
    }

    /**
     * 设置表头
     */
    public void setTableHeader() {
        Font fontStyle = this.getDefaultFontStyle(workbook);
        HSSFCellStyle cellStyle = this.setCellStyle(workbook, fontStyle);
        cellStyle.setWrapText(true);
        this.setCellBorderStyle(cellStyle);
        this.setCellBorderColor(cellStyle);

        HSSFRow row1 = sheet.createRow(2);
        List<String> list = Lists.newArrayList(
                "乡镇", "村组名称", "姓名", "身份证号", "联系电话", "家庭人口", "资产总额", "负债", "年收入", "主要从事项目", "信用等级", "授信金额", "审批时间"
                ,"复评信用等级","复评授信金额"
        );
        int col = 1;
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
        row.setHeight((short) 1000);

        row.createCell(1).setCellValue(new HSSFRichTextString(StringUtils.join("支行贷审小组（签  名）：", "\n\n", StringUtils.repeat(" ", 89), "年   月   日")));
        CellUtil.getCell(row, 1).setCellStyle(cellStyle);

        row.createCell(9).setCellValue(new HSSFRichTextString(StringUtils.join("支行行长（签  名）：", "\n\n", StringUtils.repeat(" ", 58), "年   月   日")));
        CellUtil.getCell(row, 9).setCellStyle(cellStyle);

        this.createCellRange(sheet, rowIndex, rowIndex, 1, 8, true);
        this.createCellRange(sheet, rowIndex, rowIndex, 9, 15, true);

        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setDataFormat(workbook.createDataFormat().getFormat("0"));
        sheet.setDefaultColumnStyle(3, cs);
        sheet.setDefaultColumnStyle(4, cs);

        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(3, 6500);
        sheet.setColumnWidth(4, 4500);
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
                row.createCell(colIndex + 1).setCellValue(colList.get(colIndex));
                setCellBorderColor(cellStyle);
                setCellBorderStyle(cellStyle);
                CellUtil.getCell(row, colIndex + 1).setCellStyle(cellStyle);
                sheet.setColumnWidth(colIndex + 1, getColumnDefaultWidth());
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
