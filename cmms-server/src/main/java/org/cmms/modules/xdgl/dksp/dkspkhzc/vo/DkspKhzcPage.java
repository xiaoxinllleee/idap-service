package org.cmms.modules.xdgl.dksp.dkspkhzc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.modules.xdgl.dksp.dkspdbrxx.entity.DkspDbrxx;
import org.cmms.modules.xdgl.dksp.dkspdywxx.entity.DkspDywxx;
import org.cmms.modules.xdgl.dksp.dkspkhzl.entity.DkspKhzl;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class DkspKhzcPage {
    /**唯一标识*/
    @Excel(name = "唯一标识", width = 15)
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
    /**客户类型*/
    @Excel(name = "客户类型", width = 15)
    private String khlx;
    /**手机号码*/
    @Excel(name = "手机号码", width = 15)
    private String sjhm;
    /**地址*/
    @Excel(name = "地址", width = 15)
    private String dz;
    /**信用等级*/
    @Excel(name = "信用等级", width = 15)
    private String xydj;
    /**借款用途*/
    @Excel(name = "借款用途", width = 15)
    private String jkyt;
    /**借款方式*/
    @Excel(name = "借款方式", width = 15)
    private String jkfs;
    /**贷款期限*/
    @Excel(name = "贷款期限", width = 15)
    private String dkqx;
    /**贷款金额*/
    @Excel(name = "贷款金额", width = 15)
    private java.math.BigDecimal dkje;
    /**贷款种类*/
    @Excel(name = "贷款种类", width = 15)
    private String dkzl;
    /**授信额度*/
    @Excel(name = "授信额度", width = 15)
    private String sxed;
    /**利率*/
    @Excel(name = "利率", width = 15)
    private String ll;
    /**员工工号*/
    @Excel(name = "员工工号", width = 15)
    private String yggh;
    /**授信期限起*/
    @Excel(name = "授信期限起", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sxqxBegin;
    /**授信期限止*/
    @Excel(name = "授信期限止", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sxqxEnd;
    /**最高授信额度*/
    @Excel(name = "最高授信额度", width = 15)
    private java.math.BigDecimal zgsxed;
    /**已用授信额度*/
    @Excel(name = "已用授信额度", width = 15)
    private java.math.BigDecimal yysxed;
    /**内部授信额度*/
    @Excel(name = "内部授信额度", width = 15)
    private java.math.BigDecimal nbsxed;
    /**公开授信额度*/
    @Excel(name = "公开授信额度", width = 15)
    private java.math.BigDecimal gksxed;
    /**备注*/
    @Excel(name = "备注", width = 15)
    private String bz;
    /**业务编号*/
    @Excel(name = "业务编号", width = 15)
    private String businessNumber;
    /**流程编号*/
    @Excel(name = "流程编号", width = 15)
    private String processId;
    @ExcelCollection(name = "dkspKhzlList")
    private List<DkspKhzl> dkspKhzlList;
    @ExcelCollection(name = "dkspSxzlList")
    private List<DkspKhzl> dkspSxzlList;
    @ExcelCollection(name = "dbrxxList")
    private List<DkspDbrxx> dbrxxList;
    @ExcelCollection(name = "dywxxList")
    private List<DkspDywxx> dywxxList;
    @ExcelCollection(name = "deleteFiles")
    private List<String> deleteFiles;
}
