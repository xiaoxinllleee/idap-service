package org.cmms.modules.ckjkpt.jcyj.qhckpldgl.entity;

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
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Data
@TableName("CKJKPT_QHCKPLDYJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CKJKPT_QHCKPLDYJ对象", description="1")
public class CkjkptQhckpldgl {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "支行名称", width = 15,dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**存款类型*/
	@Excel(name = "存款类型", width = 15,dicCode = "pldcklx")
    @ApiModelProperty(value = "存款类型")
	@Dict(dicCode = "pldcklx")
	private String tjbs;
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
	/**偏离度（月）*/
	@Excel(name = "偏离度（月）", width = 15)
    @ApiModelProperty(value = "偏离度（月）")
	private java.math.BigDecimal ypld;
	/**偏离度（年）*/
	@Excel(name = "偏离度（年）", width = 15)
    @ApiModelProperty(value = "偏离度（年）")
	private java.math.BigDecimal npld;
}
