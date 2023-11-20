package org.cmms.modules.dkjkpt.tjcx.qhdktj.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 全行表内贷款统计(年报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_QHBNDKTJ_NB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_QHBNDKTJ_NB对象", description="全行表内贷款统计(年报)")
public class QhbndktjNb {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
    /**贷款户数*/
    @Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
    private Integer dkhs;
    /**贷款笔数*/
    @Excel(name = "贷款笔数", width = 15)
    @ApiModelProperty(value = "贷款笔数")
    private Integer dkbs;
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
	private Date lrsj;
    /**录入标识(0:导入,1:录入,2:修改)*/
    @Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识(0:导入,1:录入,2:修改)")
    @Dict(dicCode = "lrbz")
    private String lrbz;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrr;
}
