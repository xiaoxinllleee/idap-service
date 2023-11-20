package org.cmms.modules.pad.shxxgl.entity;

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
 * @Description: 商户房产信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_SHFCXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_SHFCXX对象", description="商户房产信息")
public class CamsZcsxShfcxx {
    
	/**fcbm*/
	@Excel(name = "fcbm", width = 15)
    @ApiModelProperty(value = "fcbm")
	private String fcbm;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**商户ID*/
	@Excel(name = "商户ID", width = 15)
    @ApiModelProperty(value = "商户ID")
	private String shid;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String shmc;
	/**房产位置*/
	@Excel(name = "房产位置", width = 15)
    @ApiModelProperty(value = "房产位置")
	private String fcwz;
	/**房产数量*/
	@Excel(name = "房产数量", width = 15)
    @ApiModelProperty(value = "房产数量")
	private Integer fcsl;
	/**房产面积*/
	@Excel(name = "房产面积", width = 15)
    @ApiModelProperty(value = "房产面积")
	private String fcmj;
	/**房产单价*/
	@Excel(name = "房产单价", width = 15)
    @ApiModelProperty(value = "房产单价")
	private java.math.BigDecimal fcdj;
	/**房产价值*/
	@Excel(name = "房产价值", width = 15)
    @ApiModelProperty(value = "房产价值")
	private java.math.BigDecimal fcjz;
	/**房产性质*/
	@Excel(name = "房产性质", width = 15)
    @ApiModelProperty(value = "房产性质")
	private String fcxz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
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
