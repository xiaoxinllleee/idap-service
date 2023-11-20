package org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.entity;

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
 * @Description: 资金返还情况监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Data
@TableName("Dkjkpt_dkglckqsfxb_ajfp")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Dkjkpt_dkglckqsfxb_ajfp对象", description="资金返还情况监测（按揭、扶贫）")
public class DkjkptDkglckqsfxbAjfp {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "dkjkpt_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkjkpt_khlx")
	private String khlx;
	/**客户地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "客户地址")
	private String khdz;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**资金归行率%*/
	@Excel(name = "资金归行率(%)", width = 15)
    @ApiModelProperty(value = "资金归行率%")
	private java.math.BigDecimal zjghl;
	/**贷款日平*/
	@Excel(name = "贷款日平", width = 15)
    @ApiModelProperty(value = "贷款日平")
	private java.math.BigDecimal dkrp;
	/**存款日平*/
	@Excel(name = "存款日平", width = 15)
    @ApiModelProperty(value = "存款日平")
	private java.math.BigDecimal ckrp;
	/**贷款品种*/
	@Excel(name = "贷款品种", width = 15, dicCode = "dkzl")
    @ApiModelProperty(value = "贷款品种")
    @Dict(dicCode = "dkzl")
	private String dkpz;
	/**贷款月日平*/
	@Excel(name = "贷款月日平", width = 15)
    @ApiModelProperty(value = "贷款月日平")
	private java.math.BigDecimal dkrpM;
	/**存款月日平*/
	@Excel(name = "存款月日平", width = 15)
    @ApiModelProperty(value = "存款月日平")
	private java.math.BigDecimal ckrpM;
    /**月资金归行率*/
    @Excel(name = "月资金归行率(%)", width = 15)
    @ApiModelProperty(value = "月资金归行率")
    private java.math.BigDecimal zjghlM;
	/**贷款季日平*/
	@Excel(name = "贷款季日平", width = 15)
    @ApiModelProperty(value = "贷款季日平")
	private java.math.BigDecimal dkrpQ;
	/**存款季日平*/
	@Excel(name = "存款季日平", width = 15)
    @ApiModelProperty(value = "存款季日平")
	private java.math.BigDecimal ckrpQ;
	/**zjghlQ*/
	@Excel(name = "季资金归行率(%)", width = 15)
    @ApiModelProperty(value = "季资金归行率")
	private java.math.BigDecimal zjghlQ;
}
