package org.cmms.modules.xyjlcx.bwdkgl.bwdkshtj.entity;

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
 * @Description: 表外贷款收回统计
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
@Data
@TableName("Credit_bwdkshtj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_bwdkshtj对象", description="表外贷款收回统计")
public class Bwdkshtj {
    
	/**入账网点*/
	@Excel(name = "入账网点", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "入账网点")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String rzwd;
	/**统计日期起*/
	@Excel(name = "统计日期起", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期起")
	private Date tjrqq;
	/**统计日期止*/
	@Excel(name = "统计日期止", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期止")
	private Date tjrqz;
	/**收回本息金额*/
	@Excel(name = "收回本息金额", width = 15)
    @ApiModelProperty(value = "收回本息金额")
	private java.math.BigDecimal shbxje;
	/**收回报表本金*/
	@Excel(name = "收回报表本金", width = 15)
    @ApiModelProperty(value = "收回报表本金")
	private java.math.BigDecimal shbbbj;
	/**收回核销本金*/
	@Excel(name = "收回核销本金", width = 15)
    @ApiModelProperty(value = "收回核销本金")
	private java.math.BigDecimal shhxbj;
	/**收回核销利息*/
	@Excel(name = "收回核销利息", width = 15)
    @ApiModelProperty(value = "收回核销利息")
	private java.math.BigDecimal shhxlx;
	/**收回利息*/
	@Excel(name = "收回利息", width = 15)
    @ApiModelProperty(value = "收回利息")
	private java.math.BigDecimal shlx;
	/**应入账核销贷款利息*/
	@Excel(name = "应入账核销贷款利息", width = 15)
    @ApiModelProperty(value = "应入账核销贷款利息")
	private java.math.BigDecimal yrzhxdklx;
	/**校验标识*/
	@Excel(name = "校验标识", width = 15,dicCode = "jybz")
    @ApiModelProperty(value = "校验标识")
	@Dict(dicCode = "jybz")
	private Integer jybz;
	/**统计人*/
	@Excel(name = "统计人", width = 15)
    @ApiModelProperty(value = "统计人")
	private String lrr;
	/**统计时间*/
	@Excel(name = "统计时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统计时间")
	private Date lrsj;
	/**统计类型*/
    @ApiModelProperty(value = "统计类型")
	private Integer tjlx;
}
