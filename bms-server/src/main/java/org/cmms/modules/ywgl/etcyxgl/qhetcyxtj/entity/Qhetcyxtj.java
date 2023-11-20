package org.cmms.modules.ywgl.etcyxgl.qhetcyxtj.entity;

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
 * @Description: 全行etc营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_QHETCYXTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_QHETCYXTJ对象", description="全行etc营销统计")
public class Qhetcyxtj {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**ETC总户数*/
	@Excel(name = "ETC总户数", width = 15)
    @ApiModelProperty(value = "ETC总户数")
	private java.math.BigDecimal etczhs;
	/**ETC开户数*/
	@Excel(name = "ETC开户数", width = 15)
    @ApiModelProperty(value = "ETC开户数")
	private java.math.BigDecimal etckhs;
	/**ETC销户数*/
	@Excel(name = "ETC销户数", width = 15)
    @ApiModelProperty(value = "ETC销户数")
	private java.math.BigDecimal etcxhs;
	/**ETC净增数*/
	@Excel(name = "ETC净增数", width = 15)
    @ApiModelProperty(value = "ETC净增数")
	private java.math.BigDecimal etcjzs;
	/**ETC期初户数*/
	@Excel(name = "ETC期初户数", width = 15)
    @ApiModelProperty(value = "ETC期初户数")
	private java.math.BigDecimal etcqchs;
	/**ETC期末户数*/
	@Excel(name = "ETC期末户数", width = 15)
    @ApiModelProperty(value = "ETC期末户数")
	private java.math.BigDecimal etcqmhs;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrczy;
}
