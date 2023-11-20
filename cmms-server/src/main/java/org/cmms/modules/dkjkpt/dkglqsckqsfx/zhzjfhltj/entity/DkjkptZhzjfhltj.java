package org.cmms.modules.dkjkpt.dkglqsckqsfx.zhzjfhltj.entity;

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
 * @Description: 支行资金返还率统计
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Data
@TableName("Dkjkpt_zhzjfhltj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Dkjkpt_zhzjfhltj对象", description="支行资金返还率统计")
public class DkjkptZhzjfhltj {

	/**tjyf*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**jgdm*/
	@Excel(name = "机构代码", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**dkye*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**ckye*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**dkrpY*/
	@Excel(name = "贷款年日平", width = 15)
    @ApiModelProperty(value = "贷款年日平")
	private java.math.BigDecimal dkrpY;
	/**ckrpY*/
	@Excel(name = "存款年日平", width = 15)
    @ApiModelProperty(value = "存款年日平")
	private java.math.BigDecimal ckrpY;
	/**dkrpM*/
	@Excel(name = "贷款月日平", width = 15)
    @ApiModelProperty(value = "贷款月日平")
	private java.math.BigDecimal dkrpM;
	/**ckrpM*/
	@Excel(name = "存款月日平", width = 15)
    @ApiModelProperty(value = "存款月日平")
	private java.math.BigDecimal ckrpM;
	/**dkrpQ*/
	@Excel(name = "贷款季日平", width = 15)
    @ApiModelProperty(value = "贷款季日平")
	private java.math.BigDecimal dkrpQ;
	/**ckrpQ*/
	@Excel(name = "存款季日平", width = 15)
    @ApiModelProperty(value = "存款季日平")
	private java.math.BigDecimal ckrpQ;
	/**zjghlM*/
	@Excel(name = "月资金归行率%", width = 15)
    @ApiModelProperty(value = "月资金归行率")
	private java.math.BigDecimal zjghlM;
	/**zjghlQ*/
	@Excel(name = "季资金归行率%", width = 15)
    @ApiModelProperty(value = "季资金归行率%")
	private java.math.BigDecimal zjghlQ;
	/**zjghlY*/
	@Excel(name = "年资金归行率%", width = 15)
    @ApiModelProperty(value = "年资金归行率%")
	private java.math.BigDecimal zjghlY;
	/**khfgm*/
	@Excel(name = "客户覆盖面%", width = 15)
    @ApiModelProperty(value = "客户覆盖面%")
	private java.math.BigDecimal khfgm;
}
