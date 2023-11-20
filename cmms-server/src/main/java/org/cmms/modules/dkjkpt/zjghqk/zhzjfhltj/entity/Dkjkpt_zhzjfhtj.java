package org.cmms.modules.dkjkpt.zjghqk.zhzjfhltj.entity;

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
 * @Author: jeecg-boot
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_ZHZJFHLTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_ZHZJFHLTJ对象", description="支行资金返还率统计")
public class Dkjkpt_zhzjfhtj {
    
	/**tjyf*/
	@Excel(name = "tjyf", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjyf")
	private Date tjyf;
	/**jgdm*/
	@Excel(name = "jgdm", width = 15)
    @ApiModelProperty(value = "jgdm")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**dkye*/
	@Excel(name = "dkye", width = 15)
    @ApiModelProperty(value = "dkye")
	private java.math.BigDecimal dkye;
	/**ckye*/
	@Excel(name = "ckye", width = 15)
    @ApiModelProperty(value = "ckye")
	private java.math.BigDecimal ckye;
	/**dkrpY*/
	@Excel(name = "dkrpY", width = 15)
    @ApiModelProperty(value = "dkrpY")
	private java.math.BigDecimal dkrpY;
	/**ckrpY*/
	@Excel(name = "ckrpY", width = 15)
    @ApiModelProperty(value = "ckrpY")
	private java.math.BigDecimal ckrpY;
	/**dkrpM*/
	@Excel(name = "dkrpM", width = 15)
    @ApiModelProperty(value = "dkrpM")
	private java.math.BigDecimal dkrpM;
	/**ckrpM*/
	@Excel(name = "ckrpM", width = 15)
    @ApiModelProperty(value = "ckrpM")
	private java.math.BigDecimal ckrpM;
	/**dkrpQ*/
	@Excel(name = "dkrpQ", width = 15)
    @ApiModelProperty(value = "dkrpQ")
	private java.math.BigDecimal dkrpQ;
	/**ckrpQ*/
	@Excel(name = "ckrpQ", width = 15)
    @ApiModelProperty(value = "ckrpQ")
	private java.math.BigDecimal ckrpQ;
	/**zjghlM*/
	@Excel(name = "zjghlM", width = 15)
    @ApiModelProperty(value = "zjghlM")
	private java.math.BigDecimal zjghlM;
	/**zjghlQ*/
	@Excel(name = "zjghlQ", width = 15)
    @ApiModelProperty(value = "zjghlQ")
	private java.math.BigDecimal zjghlQ;
	/**zjghlY*/
	@Excel(name = "zjghlY", width = 15)
    @ApiModelProperty(value = "zjghlY")
	private java.math.BigDecimal zjghlY;
	/**khfgm*/
	@Excel(name = "khfgm", width = 15)
    @ApiModelProperty(value = "khfgm")
	private java.math.BigDecimal khfgm;
}
