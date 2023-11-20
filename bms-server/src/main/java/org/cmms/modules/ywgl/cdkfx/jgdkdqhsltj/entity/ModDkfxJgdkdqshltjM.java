package org.cmms.modules.ywgl.cdkfx.jgdkdqhsltj.entity;

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
 * @Description: 机构贷款到期收回率统计
 * @Author: jeecg-boot
 * @Date:   2021-06-18
 * @Version: V1.0
 */
@Data
@TableName("MOD_DKFX_JGDKDQSHLTJ_M")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MOD_DKFX_JGDKDQSHLTJ_M对象", description="机构贷款到期收回率统计")
public class ModDkfxJgdkdqshltjM {
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date beginday;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date endday;
	/**发放应收回(元)*/
	@Excel(name = "发放应收回(元)", width = 15)
	@ApiModelProperty(value = "发放应收回(元)")
	private java.math.BigDecimal ffysh;
	/**发放未收回(元)*/
	@Excel(name = "发放未收回(元)", width = 15)
	@ApiModelProperty(value = "发放未收回(元)")
	private java.math.BigDecimal ffwsh;
	/**发放已收回(元)*/
	@Excel(name = "发放已收回(元)", width = 15)
	@ApiModelProperty(value = "发放已收回(元)")
	private java.math.BigDecimal ffyish;
	/**发放到期收回率(%)*/
	@Excel(name = "发放到期收回率(%)", width = 15)
	@ApiModelProperty(value = "发放到期收回率(%)")
	private java.math.BigDecimal ffdqshl;
	/**当年发放应收回(元)*/
	@Excel(name = "当年发放应收回(元)", width = 15)
	@ApiModelProperty(value = "当年发放应收回(元)")
	private java.math.BigDecimal dnffysh;
	/**当年发放未收回(元)*/
	@Excel(name = "当年发放未收回(元)", width = 15)
	@ApiModelProperty(value = "当年发放未收回(元)")
	private java.math.BigDecimal dnffwsh;
	/**当年发放已收回(元)*/
	@Excel(name = "当年发放已收回(元)", width = 15)
	@ApiModelProperty(value = "当年发放已收回(元)")
	private java.math.BigDecimal dnffyish;
	/**当年发放到期收回率(%)*/
	@Excel(name = "当年发放到期收回率(%)", width = 15)
	@ApiModelProperty(value = "当年发放到期收回率(%)")
	private java.math.BigDecimal dnffdqshl;
	/**以前发放应收回(元)*/
	@Excel(name = "以前发放应收回(元)", width = 15)
	@ApiModelProperty(value = "以前发放应收回(元)")
	private java.math.BigDecimal yqnffysh;
	/**以前发放未收回(元)*/
	@Excel(name = "以前发放未收回(元)", width = 15)
	@ApiModelProperty(value = "以前发放未收回(元)")
	private java.math.BigDecimal yqnffwsh;
	/**以前发放已收回(元)*/
	@Excel(name = "以前发放已收回", width = 15)
	@ApiModelProperty(value = "以前发放已收回")
	private java.math.BigDecimal yqnffyish;
	/**以前发放到期收回率(%)*/
	@Excel(name = "以前发放到期收回率(%)", width = 15)
    @ApiModelProperty(value = "以前发放到期收回率(%)")
	private java.math.BigDecimal yqnffdqshl;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**录入操作员*/
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;


}
