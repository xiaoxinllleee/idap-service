package org.cmms.modules.dkjkpt.dhgzfjxx.tsxxtzs.entity;

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
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Data
@TableName("V_dkjkpt_tsfxtzs")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_dkjkpt_tsfxtzs对象", description="1")
public class vDkjkptTsfxtzs {

	/**tjyf*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjyf")
	private Date tjyf;
	/**jgdm*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "jgdm")
	private String jgdm;
	/**khmc*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**dkzh*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "dkzh")
	private String dkzh;
	/**bmkkh*/
	@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "bmkkh")
	private String bmkkh;
	/**dkje*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "dkje")
	private java.math.BigDecimal dkje;
	/**dkye*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "dkye")
	private java.math.BigDecimal dkye;
	/**jkrq*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "jkrq")
	private Date jkrq;
	/**dqrq*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dqrq")
	private Date dqrq;
	/**dkxt*/
	@Excel(name = "贷款形态", width = 15,dicCode = "dkxt")
    @ApiModelProperty(value = "dkxt")
	@Dict(dicCode = "dkxt")
	private String dkxt;
	/**wjm*/
	@Excel(name = "文件名", width = 15)
    @ApiModelProperty(value = "wjm")
	private String wjm;
}
