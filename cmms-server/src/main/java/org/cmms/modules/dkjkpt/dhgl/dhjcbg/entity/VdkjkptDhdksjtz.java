package org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity;

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
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-10-15
 * @Version: V1.0
 */
@Data
@TableName("V_dkjkpt_dhdksjtz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_dkjkpt_dhdksjtz对象", description="贷后检查报告")
public class VdkjkptDhdksjtz {
    
	/**tjyf*/
	@Excel(name = "tjyf", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjyf")
	private Date tjyf;
	/**jgdm*/
	@Excel(name = "jgdm", width = 15)
    @ApiModelProperty(value = "jgdm")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**khmc*/
	@Excel(name = "khmc", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**dhdkje*/
	@Excel(name = "dhdkje", width = 15)
    @ApiModelProperty(value = "dhdkje")
	private Integer dhdkje;
	/**dhdkye*/
	@Excel(name = "dhdkye", width = 15)
    @ApiModelProperty(value = "dhdkye")
	private Integer dhdkye;
	/**zxjkrq*/
	@Excel(name = "zxjkrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "zxjkrq")
	private Date zxjkrq;
}
