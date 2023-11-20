package org.cmms.modules.dkjkpt.cssz.dqqkjccssz.entity;

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
 * @Description: 到期情况监测参数设置
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_DQQKJCCSSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_DQQKJCCSSZ对象", description="到期情况监测参数设置")
public class Dkjkpt_dqqkjccssz {

	/**参数编号*/
	@Excel(name = "参数编号", width = 15)
    @ApiModelProperty(value = "参数编号")
	private String csbh;
	/**参数值起*/
	@Excel(name = "参数值起", width = 15)
    @ApiModelProperty(value = "参数值起")
	private String cszq;
	/**参数值止*/
	@Excel(name = "参数值止", width = 15)
    @ApiModelProperty(value = "参数值止")
	private String cszz;
	/**调整值*/
	@Excel(name = "调整值", width = 15)
    @ApiModelProperty(value = "调整值")
	private java.math.BigDecimal tzz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
}
