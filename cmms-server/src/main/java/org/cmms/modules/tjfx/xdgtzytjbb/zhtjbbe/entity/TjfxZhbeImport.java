package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 支行统计报表二
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
@Data
@TableName("TJFX_ZHBE")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_ZHBE对象", description="支行统计报表二")
public class TjfxZhbeImport {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private String tjyf;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zrre;
	/**行政村（居委会）*/
	@Excel(name = "行政村（居委会）", width = 15)
    @ApiModelProperty(value = "行政村（居委会）")
	private String xzc;
	/**存量存款余额*/
	@Excel(name = "存量存款余额", width = 15)
    @ApiModelProperty(value = "存量存款余额")
	private java.math.BigDecimal clckye;
	/**当期存款余额*/
	@Excel(name = "当期存款余额", width = 15)
    @ApiModelProperty(value = "当期存款余额")
	private java.math.BigDecimal dqckye;
	/**存款净增金额*/
	@Excel(name = "存款净增金额", width = 15)
    @ApiModelProperty(value = "存款净增金额")
	private java.math.BigDecimal ckjzje;
	/**存量存款客户数*/
	@Excel(name = "存量存款客户数", width = 15)
    @ApiModelProperty(value = "存量存款客户数")
	private String clckkhs;
	/**当期贷款余额*/
	@Excel(name = "当期贷款余额", width = 15)
    @ApiModelProperty(value = "当期贷款余额")
	private java.math.BigDecimal dqdkye;
	/**贷款净增金额*/
	@Excel(name = "贷款净增金额", width = 15)
    @ApiModelProperty(value = "贷款净增金额")
	private java.math.BigDecimal dkjzje;
	/**当前存款客户数*/
	@Excel(name = "当前存款客户数", width = 15)
    @ApiModelProperty(value = "当前存款客户数")
	private String dqckkhs;
	/**当期不良贷款余额*/
	@Excel(name = "当期不良贷款余额", width = 15)
    @ApiModelProperty(value = "当期不良贷款余额")
	private java.math.BigDecimal dqbldkye;
	/**存量贷款余额*/
	@Excel(name = "存量贷款余额", width = 15)
    @ApiModelProperty(value = "存量贷款余额")
	private java.math.BigDecimal cldkye;
	/**存量不良贷款余额*/
	@Excel(name = "存量不良贷款余额", width = 15)
    @ApiModelProperty(value = "存量不良贷款余额")
	private java.math.BigDecimal clbldkye;
	/**不良下降金额*/
	@Excel(name = "不良下降金额", width = 15)
    @ApiModelProperty(value = "不良下降金额")
	private java.math.BigDecimal blxjye;
	/**表外不良下降金额*/
	@Excel(name = "表外不良下降金额", width = 15)
    @ApiModelProperty(value = "表外不良下降金额")
	private java.math.BigDecimal bwblxjje;
	/**存量贷款客户*/
	@Excel(name = "存量贷款客户", width = 15)
    @ApiModelProperty(value = "存量贷款客户")
	private String cldkhs;
	/**当前贷款客户*/
	@Excel(name = "当前贷款客户", width = 15)
    @ApiModelProperty(value = "当前贷款客户")
	private String dydkhs;
	/**存款客户增加数*/
	@Excel(name = "存款客户增加数", width = 15)
    @ApiModelProperty(value = "存款客户增加数")
	private String ckkhzjs;
	/**贷款客户增加数*/
	@Excel(name = "贷款客户增加数", width = 15)
    @ApiModelProperty(value = "贷款客户增加数")
	private String dkkhjzs;
	/**存量表外不良贷款余额*/
	@Excel(name = "存量表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "存量表外不良贷款余额")
	private java.math.BigDecimal clbwbldkye;
	/**当期表外不良余额*/
	@Excel(name = "当期表外不良余额", width = 15)
    @ApiModelProperty(value = "当期表外不良余额")
	private java.math.BigDecimal dybwbldkye;
	/**存量手机银行户数*/
	@Excel(name = "存量手机银行户数", width = 15)
    @ApiModelProperty(value = "存量手机银行户数")
	private String clsjyhhs;
	/**当前手机银行户数*/
	@Excel(name = "当前手机银行户数", width = 15)
    @ApiModelProperty(value = "当前手机银行户数")
	private String dysjyhhs;
	/**手机银行增加户数*/
	@Excel(name = "手机银行增加户数", width = 15)
    @ApiModelProperty(value = "手机银行增加户数")
	private String sjyhjzhs;
	/**存量ETC户数*/
	@Excel(name = "存量ETC户数", width = 15)
    @ApiModelProperty(value = "存量ETC户数")
	private String cletchs;
	/**当前ETC户数*/
	@Excel(name = "当前ETC户数", width = 15)
    @ApiModelProperty(value = "当前ETC户数")
	private String dyetchs;
	/**ETC增加户数*/
	@Excel(name = "ETC增加户数", width = 15)
    @ApiModelProperty(value = "ETC增加户数")
	private String etchsjz;
	/**组*/
	@Excel(name = "组", width = 15)
    @ApiModelProperty(value = "组")
	@Dict(dicCode="qybm",dictTable="yxdygl_czxxgl",dicText="organize")
	private String zxx;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
}
