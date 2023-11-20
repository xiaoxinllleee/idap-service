package org.cmms.modules.khlc.dkqjjj.entity;

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
 * @Description: 贷款区间计价
 * @Author: jeecg-boot
 * @Date:   2023-06-25
 * @Version: V1.0
 */
@Data
@TableName("Erp_bas_dkqjjj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_bas_dkqjjj对象", description="贷款区间计价")
public class Dkqjjj {
    
	/**区间编号*/
	@Excel(name = "区间编号", width = 15)
    @ApiModelProperty(value = "区间编号")
	private String qjbh;
	/**额度起*/
	@Excel(name = "额度起", width = 15)
    @ApiModelProperty(value = "额度起")
	private String edq;
	/**额度止*/
	@Excel(name = "额度止", width = 15)
    @ApiModelProperty(value = "额度止")
	private String edz;
	/**利率起*/
	@Excel(name = "利率起", width = 15)
    @ApiModelProperty(value = "利率起")
	private java.math.BigDecimal llq;
	/**利率止*/
	@Excel(name = "利率止", width = 15)
    @ApiModelProperty(value = "利率止")
	private java.math.BigDecimal llz;
	/**调整系数*/
	@Excel(name = "调整系数", width = 15)
    @ApiModelProperty(value = "调整系数")
	private java.math.BigDecimal tzxs;
	/**风险计提系数*/
	@Excel(name = "风险计提系数", width = 15)
    @ApiModelProperty(value = "风险计提系数")
	private java.math.BigDecimal fxjtxs;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建人*/
//	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
//	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
//	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
