package org.cmms.modules.ywgl.cdkfx.zhdkkhckhbl.entity;

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
 * @Description: 支行贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
@Data
@TableName("MOD_CDKFX_ZHDKKHCKHBL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MOD_CDKFX_ZHDKKHCKHBL对象", description="支行贷款客户存款回报率")
public class ModCdkfxZhdkkhckhbl {

	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计日期")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**贷款年日平*/
	@Excel(name = "贷款年日平", width = 15)
	@ApiModelProperty(value = "贷款年日平")
	private java.math.BigDecimal dknrp;
	/**存款年日平*/
	@Excel(name = "存款年日平", width = 15)
	@ApiModelProperty(value = "存款年日平")
	private java.math.BigDecimal cknrp;
	/**存款回报率(日平)*/
	@Excel(name = "存款回报率(日平)", width = 15)
	@ApiModelProperty(value = "存款回报率(日平)")
	private java.math.BigDecimal ckhbl;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款回报率(余额)*/
	@Excel(name = "存款回报率(余额)", width = 15)
	@ApiModelProperty(value = "存款回报率(余额)")
	private java.math.BigDecimal ckyehbl;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;




}
