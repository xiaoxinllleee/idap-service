package org.cmms.modules.tjfx.xdgtzytjbb.nshbe.entity;

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
 * @Description: 农商行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Data
@TableName("TJFX_NSHBE")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_NSHBE对象", description="农商行统计报表二")
public class TjfxNshbe {
    
	/**当前ETC户数*/
	@Excel(name = "当前ETC户数", width = 15)
    @ApiModelProperty(value = "当前ETC户数")
	private String dyetchs;
	/**ETC增加户数*/
	@Excel(name = "ETC增加户数", width = 15)
    @ApiModelProperty(value = "ETC增加户数")
	private String etchsjz;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**挂片领导工号*/
	@Excel(name = "挂片领导工号", width = 15,dicCode="yggh",dictTable="HR_BAS_STAFF",dicText="ygxm")
    @ApiModelProperty(value = "挂片领导工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String gpldgh;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
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
}
