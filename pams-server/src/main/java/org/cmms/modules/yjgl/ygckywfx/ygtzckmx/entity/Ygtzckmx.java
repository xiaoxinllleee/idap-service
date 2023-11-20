package org.cmms.modules.yjgl.ygckywfx.ygtzckmx.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 员工拓展存款明细
 * @Author: jeecg-boot
 * @Date:   2023-07-11
 * @Version: V1.0
 */
@Data
@TableName("Tb_ck_ygtzcksjmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tb_ck_ygtzcksjmx对象", description="员工拓展存款明细")
public class Ygtzckmx {

	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**机构号*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**子账号*/
	@Excel(name = "子账号", width = 15)
    @ApiModelProperty(value = "子账号")
	private String ckzh;
	/**主账号*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String mastAcct;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String khrq;
	/**本期起息日*/
	@Excel(name = "本期起息日", width = 15)
    @ApiModelProperty(value = "本期起息日")
	private String intFrmDt;
	/**本期截止日*/
	@Excel(name = "本期截止日", width = 15)
    @ApiModelProperty(value = "本期截止日")
	private String intToDt;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**员工工号*/
	@Excel(name = "拓展员工姓名", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**管户比例*/
	@Excel(name = "拓展比例", width = 15)
	@ApiModelProperty(value = "拓展比例")
	private java.math.BigDecimal ghbl;
	/**账户组别*/
	@Excel(name = "账户组别", width = 15, dicCode = "zhzb")
    @ApiModelProperty(value = "账户组别")
	@Dict(dicCode = "zhzb")
	private String acctGrp;
	/**账户处理标志*/
	@Excel(name = "账户处理标志", width = 15, dicCode = "zhclbz")
    @ApiModelProperty(value = "账户处理标志")
	@Dict(dicCode = "zhclbz")
	private String acctDesc;
	/**到期日*/
	@Excel(name = "到期日", width = 15)
    @ApiModelProperty(value = "到期日")
	private String endDate;
	/**定期最大存期*/
	@Excel(name = "定期最大存期", width = 15)
    @ApiModelProperty(value = "定期最大存期")
	private String cq;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private String ll;
	/**期末余额*/
	@Excel(name = "期末余额", width = 15)
    @ApiModelProperty(value = "期末余额")
	private java.math.BigDecimal qmye;
	/**存量余额*/
	@Excel(name = "存量余额", width = 15)
    @ApiModelProperty(value = "存量余额")
	private java.math.BigDecimal clye;
	/**存量月日平1*/
	@Excel(name = "存量月日平1", width = 15)
    @ApiModelProperty(value = "存量月日平1")
	private java.math.BigDecimal clYrp1;
	/**存量月日平2*/
	@Excel(name = "存量月日平2", width = 15)
    @ApiModelProperty(value = "存量月日平2")
	private java.math.BigDecimal clYrp2;
	/**存量月日平3*/
	@Excel(name = "存量月日平3", width = 15)
    @ApiModelProperty(value = "存量月日平3")
	private java.math.BigDecimal clYrp3;
	/**月日平1*/
	@Excel(name = "月日平1", width = 15)
    @ApiModelProperty(value = "月日平1")
	private java.math.BigDecimal yrp1;
	/**月日平2*/
	@Excel(name = "月日平2", width = 15)
    @ApiModelProperty(value = "月日平2")
	private java.math.BigDecimal yrp2;
	/**月日平3*/
	@Excel(name = "月日平3", width = 15)
    @ApiModelProperty(value = "月日平3")
	private java.math.BigDecimal yrp3;
	/**存量季日平1*/
//	@Excel(name = "存量季日平1", width = 15)
    @ApiModelProperty(value = "存量季日平1")
	private java.math.BigDecimal clJrp1;
	/**存量季日平2*/
//	@Excel(name = "存量季日平2", width = 15)
    @ApiModelProperty(value = "存量季日平2")
	private java.math.BigDecimal clJrp2;
	/**存量季日平3*/
//	@Excel(name = "存量季日平3", width = 15)
    @ApiModelProperty(value = "存量季日平3")
	private java.math.BigDecimal clJrp3;
	/**季日平1*/
//	@Excel(name = "季日平1", width = 15)
    @ApiModelProperty(value = "季日平1")
	private java.math.BigDecimal jrp1;
	/**季日平2*/
//	@Excel(name = "季日平2", width = 15)
    @ApiModelProperty(value = "季日平2")
	private java.math.BigDecimal jrp2;
	/**季日平3*/
//	@Excel(name = "季日平3", width = 15)
    @ApiModelProperty(value = "季日平3")
	private java.math.BigDecimal jrp3;
	/**存量年日平1*/
	@Excel(name = "存量年日平1", width = 15)
    @ApiModelProperty(value = "存量年日平1")
	private java.math.BigDecimal clNrp1;
	/**存量年日平2*/
	@Excel(name = "存量年日平2", width = 15)
    @ApiModelProperty(value = "存量年日平2")
	private java.math.BigDecimal clNrp2;
	/**存量年日平3*/
	@Excel(name = "存量年日平3", width = 15)
    @ApiModelProperty(value = "存量年日平3")
	private java.math.BigDecimal clNrp3;
	/**年日平1*/
	@Excel(name = "年日平1", width = 15)
    @ApiModelProperty(value = "年日平1")
	private java.math.BigDecimal nrp1;
	/**年日平2*/
	@Excel(name = "年日平2", width = 15)
    @ApiModelProperty(value = "年日平2")
	private java.math.BigDecimal nrp2;
	/**年日平3*/
	@Excel(name = "年日平3", width = 15)
    @ApiModelProperty(value = "年日平3")
	private java.math.BigDecimal nrp3;
	/**模拟利润*/
//	@Excel(name = "模拟利润", width = 15)
    @ApiModelProperty(value = "模拟利润")
	private java.math.BigDecimal mnlr;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String currStatus;
	/**推荐人*/
	@Excel(name = "推荐人", width = 15)
    @ApiModelProperty(value = "推荐人")
	private String acNo;

	/**上月余额*/
	@Excel(name = "上月余额", width = 15)
    @ApiModelProperty(value = "上月余额")
	private java.math.BigDecimal syye;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String zhlx;
	/**营销类型*/
	@Excel(name = "营销类型", width = 15,dicCode = "yxlx")
    @ApiModelProperty(value = "营销类型")
	@Dict(dicCode = "yxlx")
	private Integer yxlx;
	/**成本率*/
//	@Excel(name = "成本率", width = 15)
    @ApiModelProperty(value = "成本率")
	private java.math.BigDecimal cbl;
	/**利率差*/
//	@Excel(name = "利率差", width = 15)
    @ApiModelProperty(value = "利率差")
	private java.math.BigDecimal llc;
	/**日利率差*/
//	@Excel(name = "日利率差", width = 15)
    @ApiModelProperty(value = "日利率差")
	private java.math.BigDecimal rllc;
	/**cpdl*/
	//@Excel(name = "cpdl", width = 15)
    @ApiModelProperty(value = "cpdl")
	private String cpdl;
	/**cpzl*/
	//@Excel(name = "cpzl", width = 15)
    @ApiModelProperty(value = "cpzl")
	private String cpzl;
	/**cpdm*/
	//@Excel(name = "cpdm", width = 15)
    @ApiModelProperty(value = "cpdm")
	private String cpdm;
	/**录入标志*/
//	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
