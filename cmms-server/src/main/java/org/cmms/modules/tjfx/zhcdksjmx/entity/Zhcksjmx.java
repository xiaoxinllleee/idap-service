package org.cmms.modules.tjfx.zhcdksjmx.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 支行存款数据明细
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@Data
@TableName("SYSJMX_ZHCKSJMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SYSJMX_ZHCKSJMX对象", description="支行存款数据明细")
public class Zhcksjmx {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
	private java.math.BigDecimal ye;
	/**月日平*/
	@Excel(name = "月日平", width = 15)
    @ApiModelProperty(value = "月日平")
	private java.math.BigDecimal yrp;
	/**年日平*/
	@Excel(name = "年日平", width = 15)
    @ApiModelProperty(value = "年日平")
	private java.math.BigDecimal nrp;
	/**对私余额*/
	@Excel(name = "对私余额", width = 15)
    @ApiModelProperty(value = "对私余额")
	private java.math.BigDecimal dsye;
	/**对私月日平*/
	@Excel(name = "对私月日平", width = 15)
    @ApiModelProperty(value = "对私月日平")
	private java.math.BigDecimal dsyrp;
	/**对私年日平*/
	@Excel(name = "对私年日平", width = 15)
    @ApiModelProperty(value = "对私年日平")
	private java.math.BigDecimal dsnrp;
	/**对公余额*/
	@Excel(name = "对公余额", width = 15)
    @ApiModelProperty(value = "对公余额")
	private java.math.BigDecimal dgye;
	/**对公月日平*/
	@Excel(name = "对公月日平", width = 15)
    @ApiModelProperty(value = "对公月日平")
	private java.math.BigDecimal dgyrp;
	/**对公年日平*/
	@Excel(name = "对公年日平", width = 15)
    @ApiModelProperty(value = "对公年日平")
	private java.math.BigDecimal dgnrp;
	/**活期余额*/
	@Excel(name = "活期余额", width = 15)
    @ApiModelProperty(value = "活期余额")
	private java.math.BigDecimal hqye;
	/**活期月日平*/
	@Excel(name = "活期月日平", width = 15)
    @ApiModelProperty(value = "活期月日平")
	private java.math.BigDecimal hqyrq;
	/**活期年日平*/
	@Excel(name = "活期年日平", width = 15)
    @ApiModelProperty(value = "活期年日平")
	private java.math.BigDecimal hqnrp;
	/**定期余额*/
	@Excel(name = "定期余额", width = 15)
    @ApiModelProperty(value = "定期余额")
	private java.math.BigDecimal dqye;
	/**定期月日平*/
	@Excel(name = "定期月日平", width = 15)
    @ApiModelProperty(value = "定期月日平")
	private java.math.BigDecimal dqyrp;
	/**定期年日平*/
	@Excel(name = "定期年日平", width = 15)
    @ApiModelProperty(value = "定期年日平")
	private java.math.BigDecimal dqnrp;
	/**一本通余额*/
	@Excel(name = "一本通余额", width = 15)
    @ApiModelProperty(value = "一本通余额")
	private java.math.BigDecimal ybtye;
	/**一本通月日平*/
	@Excel(name = "一本通月日平", width = 15)
    @ApiModelProperty(value = "一本通月日平")
	private java.math.BigDecimal ybtyrp;
	/**一本通年日平*/
	@Excel(name = "一本通年日平", width = 15)
    @ApiModelProperty(value = "一本通年日平")
	private java.math.BigDecimal ybtnrp;
}
