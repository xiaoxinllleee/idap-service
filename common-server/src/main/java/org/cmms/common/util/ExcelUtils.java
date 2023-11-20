package org.cmms.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cmms.common.excel.ExlsReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {

    public static Workbook getExcelWorkbook(InputStream inputStream, String fileName) {
        boolean isE2007 = false;
        if (fileName.endsWith("xlsx")) {
            isE2007 = true;
        }
        try {
            Workbook wb = null;
            if (isE2007) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                wb = new HSSFWorkbook(inputStream);
            }
            return wb;
        } catch (IOException var9) {
            var9.printStackTrace();
        }
        return null;
    }

    public static Sheet getExcelSheet(InputStream inputStream, String fileName) {
        boolean isE2007 = false;
        if (fileName.endsWith("xlsx")) {
            isE2007 = true;
        }
        try {
            Workbook wb = null;
            if (isE2007) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                wb = new HSSFWorkbook(inputStream);
            }

            Sheet sheet = ((Workbook)wb).getSheetAt(0);

            return sheet;
        } catch (IOException var9) {
            var9.printStackTrace();
        }
        return null;
    }

    /**
     * 通过单元格名称获取excel行号
     * @param cellName
     * @return
     */
    public static int getCellRow(String cellName) {
        int row=0;
        char[] c = cellName.toUpperCase().toCharArray();
        boolean startFlag = false;//保证了数字之后不能出现字母
        int index=0;
        while(index<c.length)
        {
            if(c[index]<'0'||c[index]>'9')
            {
                index++;
                if(	startFlag)
                    throw new IllegalArgumentException("单元格名称输入错误,请检查");
                continue;
            }
            row=row*10+(c[index]-'0');
            startFlag=true;
            index++;
        }
        return row-1;
    }

    public static int getCellCol(String cellName) {
        int column=0;
        char[] c = cellName.toUpperCase().toCharArray();
        int index=0;
        while(index<c.length)
        {
            if(c[index]<'A'||c[index]>'Z')
                break;

            column=column*26+(c[index]-'A'+1);
            index++;
        }
        return column-1;
    }

    /**
     * 获取工作薄
     *
     * @param type
     * @return
     */
    private static Workbook getWorkBook(Type type) {
        Workbook workbook = null;
        switch (type) {
            case XLS:
                workbook = new HSSFWorkbook();
                break;
            case XLSX:
                workbook = new SXSSFWorkbook(1);
                break;
            default:
                break;
        }
        return workbook;
    }

    /**
     * 创建头
     *
     * @param sheet
     * @param cellStyle
     * @param head
     */
    private static void createHead(Sheet sheet, CellStyle cellStyle, Set<String> head) {
        if (CollectionUtils.isNotEmpty(head)) {
            // 获取最后行数并创建行
            Row row = sheet.createRow(sheet.getLastRowNum());
            // 列数，因getLastCellNum()对于空行返回-1，所以此处不能使用该方法
            int cellNum = 0;
            Iterator<String> iterator = head.iterator();
            while (iterator.hasNext()) {
                // 创建单元格
                Cell cell = row.createCell(cellNum);
                // 为单元格赋值
                cell.setCellValue(iterator.next());
                // 样式
                cell.setCellStyle(cellStyle);
                cellNum++;
            }
        }
    }

    /**
     * 写入
     *
     * @param type
     * @param out
     * @param head
     * @param data
     * @throws IOException
     */
    public static void write(Type type, OutputStream out, LinkedHashSet<String> head,
                             LinkedList<LinkedList<CellValue>> data) throws IOException {
        if (type == null || out == null || (CollectionUtils.isEmpty(head) && CollectionUtils.isEmpty(data))) {
            return;
        }

        // 获取工作薄、创建工作表
        Workbook workBook = getWorkBook(type);
        if (workBook == null) {
            return;
        }

        CreationHelper creationHelper = workBook.getCreationHelper();
        CellStyle cellStyle = workBook.createCellStyle();
        Sheet sheet = workBook.createSheet();
        // 创建头
        if (CollectionUtils.isNotEmpty(head)) {
            createHead(sheet, cellStyle, head);
        }

        // 获取最后的行数
        int rowNum = sheet.getLastRowNum();
        // 添加数据
        if (CollectionUtils.isNotEmpty(data)) {
            // 行
            Row row;
            // 列
            Cell cell;
            // 单元格值
            CellValue cellValue;
            Object value;
            // 是否自动列宽
            Boolean autoSizeColumn;

            Iterator<LinkedList<CellValue>> listIterator = data.iterator();
            while (listIterator.hasNext()) {
                // 列数，因getLastCellNum()对于空行返回-1，所以此处不能使用该方法
                int cellNum = 0;
                row = sheet.createRow(++rowNum);
                Iterator<CellValue> iterator = listIterator.next().iterator();
                while (iterator.hasNext()) {
                    cellValue = iterator.next();
                    cell = row.createCell(cellNum);
                    value = cellValue.getValue();
                    Short fmt = null;
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else if (value instanceof Date) {
                        // 当值为日期时，需要设置样式，否则日期会转化为浮点数
                        cell.setCellValue((Date) value);
                        if (StringUtils.isNotBlank(cellValue.getPattern())) {
                            fmt = creationHelper.createDataFormat().getFormat(cellValue.getPattern());
                        }
                    } else if (value instanceof Boolean) {
                        cell.setCellValue((Boolean) value);
                    } else {
                        cell.setCellValue(value.toString());
                    }

                    if (fmt != null) {
                        cellStyle.setDataFormat(fmt);
                        cell.setCellStyle(cellStyle);
                    }
                    if (type == Type.XLS) {
                        // 单元格固定宽度
                        sheet.setColumnWidth(cellNum, 100 * 80);
                        cell.setCellStyle(cellStyle);
                    }
                    // 自动列宽
                    autoSizeColumn = cellValue.getAutoSizeColumn();
                    if (null != autoSizeColumn && autoSizeColumn) {
                        sheet.autoSizeColumn(cellNum, true);
                    }

                    cellNum++;
                }
            }
        }
        // 写入
        workBook.write(out);
    }

    public static String getCellValue(Cell cell) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss ");
        if (cell == null) return null;
        int cellType = cell.getCellType();
        String data = null;
        switch (cellType) {
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
                    data = ExlsReport.formatterDouble("0.00", dv);


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
                        data = ExlsReport.formatterDouble("0.00", dv);
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

    public enum Type {

        XLS(".xls"), XLSX(".xlsx");

        private String expandedName;

        Type(String expandedName) {
            this.expandedName = expandedName;
        }

        public String getExpandedName() {
            return expandedName;
        }
    }
}
