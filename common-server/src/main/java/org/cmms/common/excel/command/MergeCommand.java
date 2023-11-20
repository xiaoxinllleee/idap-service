package org.cmms.common.excel.command;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jxls.area.Area;
import org.jxls.command.AbstractCommand;
import org.jxls.command.Command;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.common.SheetData;
import org.jxls.common.Size;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiCellData;
import org.jxls.transform.poi.PoiTransformer;

public class MergeCommand extends AbstractCommand {
    public static final String COMMAND_NAME = "merge";
    private String cols;
    private String rows;
    private String minCols;
    private String minRows;
    private Area area;

    public MergeCommand() {
    }

    @Override
    public String getName() {
        return "merge";
    }

    public String getCols() {
        return this.cols;
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    public String getRows() {
        return this.rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getMinCols() {
        return this.minCols;
    }

    public void setMinCols(String minCols) {
        this.minCols = minCols;
    }

    public String getMinRows() {
        return this.minRows;
    }

    public void setMinRows(String minRows) {
        this.minRows = minRows;
    }

    @Override
    public Command addArea(Area area) {
        if (super.getAreaList().size() >= 1) {
            throw new IllegalArgumentException("You can only add 1 area to 'mergeCells' command!");
        } else {
            this.area = area;
            return super.addArea(area);
        }
    }

    public Size applyAt(CellRef cellRef, Context context) {
        int rows = this.getVal(this.rows, context);
        int cols = this.getVal(this.cols, context);
        rows = Math.max(this.getVal(this.minRows, context), rows);
        cols = Math.max(this.getVal(this.minCols, context), cols);
        rows = rows > 0 ? rows : this.area.getSize().getHeight();
        cols = cols > 0 ? cols : this.area.getSize().getWidth();
        if (rows > 1 || cols > 1) {
            Transformer transformer = this.getTransformer();
            if (transformer instanceof PoiTransformer) {
                mergeCells(cellRef, context, (PoiTransformer) transformer, rows, cols);
            } else {
                transformer.mergeCells(cellRef, rows, cols);
            }
        }

        this.area.applyAt(cellRef, context);
        return new Size(cols, rows);
    }

    public void mergeCells(CellRef cellRef, Context context, PoiTransformer transformer, int rows, int cols) {
        Sheet sheet = transformer.getWorkbook().getSheet(cellRef.getSheetName());
        CellRangeAddress region = new CellRangeAddress(cellRef.getRow(), cellRef.getRow() + rows - 1, cellRef.getCol(), cellRef.getCol() + cols - 1);
        sheet.addMergedRegion(region);
        CellStyle cellStyle = null;

        try {
            cellStyle = this.getCellStyle(cellRef, transformer);
        } catch (Exception var11) {
        }

        if (cellStyle != null) {
            for(int i = region.getFirstRow(); i <= region.getLastRow(); ++i) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    row = sheet.createRow(i);
                }

                for(int j = region.getFirstColumn(); j <= region.getLastColumn(); ++j) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        cell = row.createCell(j);
                    }

                    if (cellStyle == null) {
                        cell.getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);
                        cell.getCellStyle().setVerticalAlignment(CellStyle.ALIGN_CENTER);
                    } else {
                        cell.setCellStyle(cellStyle);
                    }
                }
            }

        }
    }

    public CellStyle getCellStyle(CellRef cellRef, PoiTransformer transformer) {
        PoiCellData cellData = (PoiCellData) transformer.getCellData(area.getStartCellRef());
        if (cellData != null) {
            return cellData.getCellStyle();
        }
        return null;
    }
    private int getVal(String expression, Context context) {
        if (expression != null && expression.trim().length() > 0) {
            Object obj = this.getTransformationConfig().getExpressionEvaluator().evaluate(expression, context.toMap());

            try {
                return Integer.parseInt(obj.toString());
            } catch (NumberFormatException var5) {
                throw new IllegalArgumentException("Expression: " + expression + " failed to resolve");
            }
        } else {
            return 0;
        }
    }
}

//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.jxls.area.Area;
//import org.jxls.command.AbstractCommand;
//import org.jxls.command.Command;
//import org.jxls.common.CellRef;
//import org.jxls.common.Context;
//import org.jxls.common.Size;
//import org.jxls.transform.Transformer;
//import org.jxls.transform.poi.PoiCellData;
//import org.jxls.transform.poi.PoiTransformer;
//
///**
// * jxls <p>合并单元格</p>
// * jx:merge(
// *  lastCell="单元格"
// *  [, cols="合并的列数"]
// *  [, rows="合并的行数"]
// *  [, minCols="最小合并的列数"]
// *  [, minRows="最小合并的行数"]
// * )
// */
//public class MergeCommand extends AbstractCommand {
//    private String cols; //合并的列数
//    private String rows; //合并的行数
//    private String minCols; //最小合并的列数
//    private String minRows; //最小合并的行数
//    private CellStyle cellStyle;//第一个单元格的样式
//
//    private Area area;
//
//    @Override
//    public String getName() {
//        return "merge";
//    }
//
//    @Override
//    public Command addArea(Area area) {
//        if (super.getAreaList().size() >= 1) {
//            throw new IllegalArgumentException("You can add only a single area to 'merge' command");
//        }
//        this.area = area;
//        return super.addArea(area);
//    }
//
//    @Override
//    public Size applyAt(CellRef cellRef, Context context) {
//        int rows = getVal(this.rows, context);
//        int cols = getVal(this.cols, context);
//        rows = Math.max(getVal(this.minRows, context), rows);
//        cols = Math.max(getVal(this.minCols, context), cols);
//        rows = rows > 0 ? rows : area.getSize().getHeight();
//        cols = cols > 0 ? cols : area.getSize().getWidth();
//        if (rows > 1 || cols > 1) {
//            Transformer transformer = this.getTransformer();
//            if (transformer instanceof PoiTransformer) {
//                poiMerge(cellRef, context, (PoiTransformer) transformer, rows, cols);
//            } else {
//                transformer.mergeCells(cellRef, rows, cols);
//            }
//        }
//        area.applyAt(cellRef, context);
//        return new Size(cols, rows);
//    }
//
//    protected Size poiMerge(CellRef cellRef, Context context, PoiTransformer transformer, int rows, int cols) {
//        Sheet sheet = transformer.getWorkbook().getSheet(cellRef.getSheetName());
//        CellRangeAddress region = new CellRangeAddress(cellRef.getRow(), cellRef.getRow() + rows - 1, cellRef.getCol(),
//                cellRef.getCol() + cols - 1);
//        sheet.addMergedRegion(region);
//
//        //合并之后单元格样式会丢失，以下操作将合并后的单元格恢复成合并前第一个单元格的样式
//        area.applyAt(cellRef, context);
//        if (cellStyle == null) {
//            PoiCellData cellData = (PoiCellData) transformer.getCellData(area.getStartCellRef());
//            if (cellData != null) {
//                cellStyle = cellData.getCellStyle();
//            }
//        }
//        setRegionStyle(cellStyle, region, sheet);
//        return new Size(cols, rows);
//    }
//
//    private static void setRegionStyle(CellStyle cs, CellRangeAddress region, Sheet sheet) {
//        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
//            Row row = sheet.getRow(i);
//            if (row == null) {
//                row = sheet.createRow(i);
//            }
//            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
//                Cell cell = row.getCell(j);
//                if (cell == null) {
//                    cell = row.createCell(j);
//                }
//                if (cs == null) {
//                    cell.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
//                    cell.getCellStyle().setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
//                } else {
//                    cell.setCellStyle(cs);
//                }
//            }
//        }
//    }
//
//    private int getVal(String expression, Context context) {
//        if ((expression != null) && (expression.trim().length() > 0)) {
//            Object obj = getTransformationConfig().getExpressionEvaluator().evaluate(expression, context.toMap());
//            try {
//                return Integer.parseInt(obj.toString());
//            } catch (NumberFormatException e) {
//                throw new IllegalArgumentException("Expression: " + expression + " failed to resolve");
//            }
//        }
//        return 0;
//    }
//
//    public String getCols() {
//        return cols;
//    }
//
//    public void setCols(String cols) {
//        this.cols = cols;
//    }
//
//    public String getRows() {
//        return rows;
//    }
//
//    public void setRows(String rows) {
//        this.rows = rows;
//    }
//
//    public String getMinCols() {
//        return minCols;
//    }
//
//    public void setMinCols(String minCols) {
//        this.minCols = minCols;
//    }
//
//    public String getMinRows() {
//        return minRows;
//    }
//
//    public void setMinRows(String minRows) {
//        this.minRows = minRows;
//    }
//
//}