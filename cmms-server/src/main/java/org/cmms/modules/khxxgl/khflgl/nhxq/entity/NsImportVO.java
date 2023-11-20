package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

import java.math.BigDecimal;

@Data
public class NsImportVO
{

    @Excel(name = "所属支行", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    private String sszh;
    @Excel(name = "所属网格", width = 15,dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
    private String wgbh;
    @Excel(name = "姓名", width = 15)
    private String khmc;
    @Excel(name = "身份证号码", width = 15)
    private String zjhm;
    @Excel(name = "正确身份证号码", width = 15)
    private String zjhm2;
    @Excel(name = "户号编码", width = 15)
    private String hhbm;
    @Excel(name = "与户主关系", width = 15,dicCode = "yhzgx")
    private String yhzgx;
    @Excel(name = "联系电话", width = 15)
    private String sjhm;
    @Excel(name = "新初评等级", width = 15)
    private String cpdj;
    @Excel(name = "新初评授信", width = 15)
    private BigDecimal cpdj2;
    @Excel(name = "新复评等级", width = 15)
    private String zzpddj;
    @Excel(name = "新复评授信", width = 15)
    //@ExcelVerify(interHandler = true)
    private String zzsxed;
    @Excel(name = "信用产品", width = 15,dicCode = "ly_xend_cpzl")
    private String cpzl;
    @Excel(name = "所属客户经理工号", width = 15)
    @ExcelVerify(interHandler = true)
    private String sskhjl;
    //@ExcelVerify(interHandler = true)
    //private BigDecimal zzsxed;
}
