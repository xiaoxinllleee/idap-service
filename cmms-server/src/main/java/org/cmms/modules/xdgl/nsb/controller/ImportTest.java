package org.cmms.modules.xdgl.nsb.controller;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ImportTest {
    public static void main(String[] args) {
        String ml = "c:/anquan/正式";
        List<File> files = FileUtil.loopFiles(ml);
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            System.out.println(file.getAbsolutePath());
            try {
                boolean isExcel2003 = true;
                if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
                    isExcel2003 = false;
                }
                Workbook wb = null;
                if (isExcel2003) {
                    wb = new HSSFWorkbook(new FileInputStream(file));
                } else {
                    wb = new XSSFWorkbook(new FileInputStream(file));
                }
                Sheet sheet = wb.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                System.out.println(lastRowNum);
                if (lastRowNum >= 3) {
                    /**
                     *  第一行是年审表表头
                     *  第二行是镇村组 也有的不是 或者 下面表头覆盖
                     * */


                    String zcz = null;
                    Row row = sheet.getRow(1);
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        zcz = row.getCell(1).getStringCellValue().trim();
                    } else {
                        log.info("===导入 zcz 为空===");
                    }

                    Row row1 = sheet.getRow(2);
                    Map<String, Integer> map = new HashMap<>();
                    short lastCellNum = row1.getLastCellNum();
                    for (int j = 0; j < lastCellNum; j++) {
                        if (row1.getCell(j) != null) {
                            row1.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            String biaotou = row1.getCell(j).getStringCellValue().trim();
                            map.put(biaotou, j);
                        } else {
                            log.info("===导入 zcz 为空===");
                        }
                    }

                    for (int j = 3; j < lastRowNum + 1; j++) {
                        Row row2 = sheet.getRow(j);
                        //表示最后一行了
                        if (row2.getLastCellNum() < 5) {
                            continue;
                        }
                        String khmc = null;
                        String zjhm = null;
                        String yhzgx = null;
                        String sjhm = null;
                        String cpdj = null;
                        String cpsx = null;
                        String fpdj = null;
                        String fpsx = null;
                        String cplx = null;
                        String sskhjl = null;
                        String czinfo = null;

                        Integer khmcxh = map.get("姓名");
                        if (row2.getCell(khmcxh) != null) {
                            row2.getCell(khmcxh).setCellType(Cell.CELL_TYPE_STRING);
                            khmc = row2.getCell(khmcxh).getStringCellValue().trim();
                            System.out.println(khmc);
                        } else {
                            log.info("===导入 khmc 为空===");
                        }

                        Integer xhzjhm = map.get("身份证号码");
                        if (row2.getCell(xhzjhm) != null) {
                            row2.getCell(xhzjhm).setCellType(Cell.CELL_TYPE_STRING);
                            zjhm = row2.getCell(xhzjhm).getStringCellValue().trim();
                            System.out.println(zjhm);
                        } else {
                            log.info("===导入 zjhm 为空===");
                        }

                        Integer xhyhzgx = map.get("与户主关系");
                        if (row2.getCell(xhyhzgx) != null) {
                            row2.getCell(xhyhzgx).setCellType(Cell.CELL_TYPE_STRING);
                            yhzgx = row2.getCell(xhyhzgx).getStringCellValue().trim();
                            System.out.println(yhzgx);
                        } else {
                            log.info("===导入 yhzgx 为空===");
                        }


                        Integer xhsjhm = map.get("联系电话");
                        if (row2.getCell(xhsjhm) != null) {
                            row2.getCell(xhsjhm).setCellType(Cell.CELL_TYPE_STRING);
                            sjhm = row2.getCell(xhsjhm).getStringCellValue().trim();
                            System.out.println(sjhm);
                        } else {
                            log.info("===导入 sjhm 为空===");
                        }

                        Integer xhcpdj = map.get("新初评等级");
                        if (row2.getCell(xhcpdj) != null) {
                            row2.getCell(xhcpdj).setCellType(Cell.CELL_TYPE_STRING);
                            cpdj = row2.getCell(xhcpdj).getStringCellValue().trim();
                            System.out.println(cpdj);
                        } else {
                            log.info("===导入 cpdj 为空===");
                        }

                        Integer xhfpdj = map.get("新复评等级");
                        if (row2.getCell(xhfpdj) != null) {
                            row2.getCell(xhfpdj).setCellType(Cell.CELL_TYPE_STRING);
                            fpdj = row2.getCell(xhfpdj).getStringCellValue().trim();
                            System.out.println(fpdj);
                        } else {
                            log.info("===导入 fpdj 为空===");
                        }

                        Integer xhcpsx = map.get("新初评授信");
                        if (row2.getCell(xhcpsx) != null) {
                            row2.getCell(xhcpsx).setCellType(Cell.CELL_TYPE_STRING);
                            cpsx = row2.getCell(xhcpsx).getStringCellValue().trim();
                            System.out.println(cpsx);
                        } else {
                            log.info("===导入 cpsx 为空===");
                        }

                        Integer xhfpsx = map.get("新复评授信");
                        if (row2.getCell(xhfpsx) != null) {
                            row2.getCell(xhfpsx).setCellType(Cell.CELL_TYPE_STRING);
                            fpsx = row2.getCell(xhfpsx).getStringCellValue().trim();
                            System.out.println(fpsx);
                        } else {
                            log.info("===导入 fpsx 为空===");
                        }

                        Integer xhkhjl = map.get("所属客户经理");
                        if (xhkhjl != null) {
                            if (row2.getCell(xhkhjl) != null) {
                                row2.getCell(xhkhjl).setCellType(Cell.CELL_TYPE_STRING);
                                sskhjl = row2.getCell(xhkhjl).getStringCellValue().trim();
                                System.out.println(sskhjl);
                            } else {
                                log.info("===导入 sskhjl 为空===");
                            }
                        }

                        Integer xhcplx= map.get("产品类型");
                        if (xhcplx != null) {
                            if (row2.getCell(xhcplx) != null) {
                                row2.getCell(xhcplx).setCellType(Cell.CELL_TYPE_STRING);
                                cplx = row2.getCell(xhcplx).getStringCellValue().trim();
                                System.out.println(cplx);
                            } else {
                                log.info("===导入 cplx 为空===");
                            }
                        }

                        //这里要出来镇村组数据
                        Integer xhczhen= map.get("镇");
                        if (xhczhen != null) {
                            if (row2.getCell(xhczhen) != null) {
                                row2.getCell(xhczhen).setCellType(Cell.CELL_TYPE_STRING);
                                czinfo += row2.getCell(xhczhen).getStringCellValue().trim();
                            }
                        }

                        Integer xhcun= map.get("村");
                        if (xhcun != null) {
                            if (row2.getCell(xhcun) != null) {
                                row2.getCell(xhcun).setCellType(Cell.CELL_TYPE_STRING);
                                czinfo += row2.getCell(xhcun).getStringCellValue().trim();
                            }
                        }

                        Integer xhzu= map.get("组");
                        if (xhzu != null) {
                            if (row2.getCell(xhzu) != null) {
                                row2.getCell(xhzu).setCellType(Cell.CELL_TYPE_STRING);
                                czinfo += row2.getCell(xhzu).getStringCellValue().trim();
                            }
                        }


                        Integer xhsscz= map.get("所属村组");
                        if (xhsscz != null) {
                            if (row2.getCell(xhsscz) != null) {
                                row2.getCell(xhsscz).setCellType(Cell.CELL_TYPE_STRING);
                                czinfo = row2.getCell(xhsscz).getStringCellValue().trim();
                            }
                        }

                        Integer xhdz= map.get("地址");
                        if (xhdz != null) {
                            if (row2.getCell(xhdz) != null) {
                                row2.getCell(xhdz).setCellType(Cell.CELL_TYPE_STRING);
                                czinfo = row2.getCell(xhdz).getStringCellValue().trim();
                            }
                        }

                        Integer xhzcz= map.get("镇村组");
                        if (xhzcz != null) {
                            if (row2.getCell(xhzcz) != null) {
                                row2.getCell(xhzcz).setCellType(Cell.CELL_TYPE_STRING);
                                czinfo = row2.getCell(xhzcz).getStringCellValue().trim();
                            }
                        }

                        Integer xhzc= map.get("村组");
                        if (xhzc != null) {
                            if (row2.getCell(xhzc) != null) {
                                row2.getCell(xhzc).setCellType(Cell.CELL_TYPE_STRING);
                                czinfo = row2.getCell(xhzc).getStringCellValue().trim();
                            }
                        }

                        Integer xhczdz= map.get("村组地址");
                        if (xhczdz != null) {
                            if (row2.getCell(xhczdz) != null) {
                                row2.getCell(xhczdz).setCellType(Cell.CELL_TYPE_STRING);
                                czinfo = row2.getCell(xhczdz).getStringCellValue().trim();
                            }
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
//        File[] ls = FileUtil.ls("c:/anquan/正式");
//        for (int i = 0; i < ls.length; i++) {
//            System.out.println(ls[0].getName());
//            System.out.println(ls[0].getAbsolutePath());
//        }
    }
}
