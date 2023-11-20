package org.cmms.modules.xdgl.dksp.dkspsxsp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.cmms.modules.xdgl.dksp.dkspdbrxx.entity.DkspDbrxx;
import org.cmms.modules.xdgl.dksp.dkspdywxx.entity.DkspDywxx;
import org.cmms.modules.xdgl.dksp.dkspkhzl.entity.DkspKhzl;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.Date;
import java.util.List;

@Data
public class DkspSxspPage {
    @Excel(name= "唯一标识", width = 15)
    private String id;
    /**机构名称*/
    @Excel(name = "机构名称", width = 15, dicCode="ZZBZ",dictTable="V_HR_BAS_ORGANIZATION_CMMS",dicText="ZZJC")
    private String zzbz;
    /**客户名称*/
    @Excel(name = "客户名称", width = 15)
    private String khmc;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    private String zjhm;
    /**信用等级*/
    @Excel(name = "信用等级", width = 15)
    private String xydj;
    /**授信期限起*/
    @Excel(name = "授信期限起", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date sxqxBegin;
    /**授信期限止*/
    @Excel(name = "授信期限止", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date sxqxEnd;
    /**最高授信额度*/
    @Excel(name = "最高授信额度", width = 15)
    private java.math.BigDecimal zgsxed;
    /**已使用授信额*/
    @Excel(name = "已使用授信额", width = 15)
    private java.math.BigDecimal yysxed;
    /**内部授信额度*/
    @Excel(name = "内部授信额度", width = 15)
    private java.math.BigDecimal nbsxed;
    /**公开授信额度*/
    @Excel(name = "公开授信额度", width = 15)
    private java.math.BigDecimal gksxed;
    /**业务编号*/
    @Excel(name = "业务编号", width = 15)
    private String businessNumber;
    /**流程编号*/
    @Excel(name = "流程编号", width = 15)
    private String processId;
    @ExcelCollection(name = "dkspSxzlList")
    private List<DkspKhzl> dkspSxzlList;
    @ExcelCollection(name = "deleteFiles")
    private List<String> deleteFiles;
}
