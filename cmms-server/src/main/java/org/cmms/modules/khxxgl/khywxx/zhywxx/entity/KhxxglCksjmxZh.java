package org.cmms.modules.khxxgl.khywxx.zhywxx.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户信息管理存款数据明细支行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_CKSJMX_ZH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_CKSJMX_ZH对象", description="客户信息管理存款数据明细支行")
public class KhxxglCksjmxZh {
    
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
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
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
	private java.math.BigDecimal hqyrp;
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
	/**年初余额*/
	@Excel(name = "年初余额", width = 15)
    @ApiModelProperty(value = "年初余额")
	private java.math.BigDecimal ncye;
	/**年初月日平*/
	@Excel(name = "年初月日平", width = 15)
    @ApiModelProperty(value = "年初月日平")
	private java.math.BigDecimal ncyrp;
	/**年初年日平*/
	@Excel(name = "年初年日平", width = 15)
    @ApiModelProperty(value = "年初年日平")
	private java.math.BigDecimal ncnrp;
	/**上月余额*/
	@Excel(name = "上月余额", width = 15)
    @ApiModelProperty(value = "上月余额")
	private java.math.BigDecimal syye;
	/**上月月日平*/
	@Excel(name = "上月月日平", width = 15)
    @ApiModelProperty(value = "上月月日平")
	private java.math.BigDecimal syyrp;
	/**上月年日平*/
	@Excel(name = "上月年日平", width = 15)
    @ApiModelProperty(value = "上月年日平")
	private java.math.BigDecimal synrp;
	/**上月活期余额*/
	@Excel(name = "上月活期余额", width = 15)
    @ApiModelProperty(value = "上月活期余额")
	private java.math.BigDecimal syhqye;
	/**年初活期余额*/
	@Excel(name = "年初活期余额", width = 15)
    @ApiModelProperty(value = "年初活期余额")
	private java.math.BigDecimal nchqye;
	/**上月定期余额*/
	@Excel(name = "上月定期余额", width = 15)
    @ApiModelProperty(value = "上月定期余额")
	private java.math.BigDecimal sydqye;
	/**年初定期余额*/
	@Excel(name = "年初定期余额", width = 15)
    @ApiModelProperty(value = "年初定期余额")
	private java.math.BigDecimal ncdqye;
}
