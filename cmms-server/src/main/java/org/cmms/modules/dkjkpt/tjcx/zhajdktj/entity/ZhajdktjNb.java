package org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity;

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
 * @Description: 支行按揭贷款统计_年报
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_ZHAJDKTJ_NB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_ZHAJDKTJ_NB对象", description="支行按揭贷款统计_年报")
public class ZhajdktjNb {
    
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
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**不良余额*/
	@Excel(name = "不良余额", width = 15)
    @ApiModelProperty(value = "不良余额")
	private java.math.BigDecimal blye;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**不良余额比例*/
	@Excel(name = "不良余额比例", width = 15)
    @ApiModelProperty(value = "不良余额比例")
	private java.math.BigDecimal blyebl;
	/**当年到期贷款金额*/
	@Excel(name = "当年到期贷款金额", width = 15)
    @ApiModelProperty(value = "当年到期贷款金额")
	private java.math.BigDecimal dndqdkje;
	/**当年到期收回金额*/
	@Excel(name = "当年到期收回金额", width = 15)
    @ApiModelProperty(value = "当年到期收回金额")
	private java.math.BigDecimal dndqshje;
	/**当年到期收回率*/
	@Excel(name = "当年到期收回率", width = 15)
    @ApiModelProperty(value = "当年到期收回率")
	private java.math.BigDecimal dqdqshl;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date createTime;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String createBy;
	/**贷款笔数*/
	@Excel(name = "贷款笔数", width = 15)
    @ApiModelProperty(value = "贷款笔数")
	private Long dkbs;
	/**贷款户数*/
	@Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
	private Long dkhs;
}
