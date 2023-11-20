package org.cmms.modules.office.excel;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.cmms.common.system.base.entity.AlreadyApprovalYearAuditTable;
import org.cmms.common.util.BrowserUtil;
import org.cmms.common.util.ExcelUtils;
import org.cmms.common.util.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/word")
public class TestDemo {
    public static void main(String[] args) {
        System.out.println(FileUtil.getTempFilePath("EmployeeIndexAditionnalTemplete.xls"));

    }


    @RequestMapping("/excelMethod")
    public void method(HttpServletRequest request,
                       HttpServletResponse response) throws Exception{

        List<List<String>> dataList = Lists.newArrayList();
        /*if (CollectionUtils.isNotEmpty(commonJlList)) {
            for (Vgrpjsxspjl camsZcsxGrpjsxxxSpjl : commonJlList) {
                List<String> colList = Lists.newArrayList();
                *//*
                 * 乡镇	村组名称	姓名	身份证号	联系电话	家庭人口	资产总额	负债	年收入	主要从事项目	信用等级	授信金额	审批时间
                 * *//*
                String town = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getYjyxdybh())) {
                    String yj = yjmap.get(camsZcsxGrpjsxxxSpjl.getYjyxdybh());
                    if (StringUtils.isNotBlank(yj))
                        town = yj;
                }
                colList.add(town);

                String addr = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getEjyxdybh())) {
                    String ej = ejmap.get(camsZcsxGrpjsxxxSpjl.getEjyxdybh());
                    if (StringUtils.isNotBlank(ej))
                        addr += ej;
                }

                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSjyxdybh())) {
                    String sj = ejmap.get(camsZcsxGrpjsxxxSpjl.getSjyxdybh());
                    if (StringUtils.isNotBlank(sj))
                        addr += sj;
                }
                colList.add(addr);
                colList.add(camsZcsxGrpjsxxxSpjl.getKhmc());
                colList.add(camsZcsxGrpjsxxxSpjl.getZjhm());
                colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSjhm()) ? camsZcsxGrpjsxxxSpjl.getSjhm() : StringUtils.EMPTY);
                //家庭人数
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getHhbm())) {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("HHBM", camsZcsxGrpjsxxxSpjl.getHhbm());
                    int result = khhmcxxMapper.selectCount(queryWrapper);
                    colList.add(result > 1 ? String.valueOf(result) : String.valueOf(1));
                } else {
                    colList.add("1");
                }
                *//*
                 * 资产总额	负债	年收入	主要从事项目	信用等级	授信金额	审批时间
                 * *//*
                if (camsZcsxGrpjsxxxSpjl.getJtjzc() != null) {
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getJtjzc().toString()) ? camsZcsxGrpjsxxxSpjl.getJtjzc().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                if (camsZcsxGrpjsxxxSpjl.getFzHj() != null) {
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getFzHj().toString()) ? camsZcsxGrpjsxxxSpjl.getFzHj().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }

                if (camsZcsxGrpjsxxxSpjl.getJtjsr() != null) {
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getJtjsr().toString()) ? camsZcsxGrpjsxxxSpjl.getJtjsr().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }
                //从事职业
                String zszy = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getCszy())) {
                    String zy = cszyMap.get(camsZcsxGrpjsxxxSpjl.getCszy());
                    if (StringUtils.isNotBlank(zy))
                        zszy = zy;
                }
                colList.add(zszy);

                String zzdj = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZzpddj())) {
                    String dj = pjMap.get(camsZcsxGrpjsxxxSpjl.getZzpddj());
                    if (StringUtils.isNotBlank(dj))
                        zzdj = dj;
                }
                colList.add(zzdj);
                if (camsZcsxGrpjsxxxSpjl.getZzsxed() != null) {
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZzsxed().toString()) ? camsZcsxGrpjsxxxSpjl.getZzsxed().toString() : StringUtils.EMPTY);
                } else {
                    colList.add(StringUtils.EMPTY);
                }
                if (camsZcsxGrpjsxxxSpjl.getUpdateTime() != null) {
                    colList.add(DateUtil.format(camsZcsxGrpjsxxxSpjl.getUpdateTime(), "yyyy-MM-dd"));
                } else {
                    colList.add(StringUtils.EMPTY);
                }
                dataList.add(colList);
            }
        }*/
        alreadyApprovalYearAuditTableExport(request,response,"",null);
    }

    public  void alreadyApprovalYearAuditTableExport(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       String fileName,
                                                       List<List<String>> dataList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("年审表");

        IndexSupplement alreadyApprovalYearAuditTable = new IndexSupplement(workbook, sheet);
        alreadyApprovalYearAuditTable.setTableName("湖南浏阳农村商业银行《农户贷款证》年审情况表");
        alreadyApprovalYearAuditTable.setTableHeader();
        alreadyApprovalYearAuditTable.setTableData(dataList);

        this.workbookWrite(request, response, fileName, workbook);
    }

    public void workbookWrite(HttpServletRequest request, HttpServletResponse response, String fileName, HSSFWorkbook workbook) throws IOException {
        ExcelUtils.Type type = ExcelUtils.Type.XLS;
        this.setHeader(request, response, String.format("%s_%d%s", fileName, System.currentTimeMillis(), type.getExpandedName()));
        this.setExcelContentType(response, type);
        workbook.write(response.getOutputStream());
    }

    public void setHeader(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        if ("IE".equals(BrowserUtil.getBrowser(request))) {
            fileName = new String(java.net.URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        }else {
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        }
    }

    public void setExcelContentType(HttpServletResponse response, ExcelUtils.Type type) {
        switch (type) {
            case XLS:
                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                break;
            case XLSX:
                response.setContentType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
                break;
            default:
                break;
        }
    }
}
