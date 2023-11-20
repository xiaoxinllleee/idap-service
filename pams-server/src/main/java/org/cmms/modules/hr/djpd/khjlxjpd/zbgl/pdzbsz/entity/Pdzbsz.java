package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.entity;

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
 * @Description: 评定指标设置
 * @Author: jeecg-boot
 * @Date:   2021-09-10
 * @Version: V1.0
 */
@Data
@TableName("GRADE_CUST_ZBSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_ZBSZ对象", description="评定指标设置")
public class Pdzbsz {

	/**组织标识*/
	//@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**岗位标志*/
	//@Excel(name = "岗位标志", width = 15)
    @ApiModelProperty(value = "岗位标志")
	private Integer gwbz;
	/**评定周期*/
	@Excel(name = "评定周期", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
	@Dict(dicCode = "rqwd")
	private String pdzq;
	/**评定日期*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	private Date pdrq;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
    @ApiModelProperty(value = "指标名称")
	private String zbmc;
	/**指标分值*/
	@Excel(name = "指标分值", width = 15)
    @ApiModelProperty(value = "指标分值")
	private java.math.BigDecimal zbfz;
	/**加减标识*/
	@Excel(name = "加减标识", width = 15,dicCode = "zjbs")
    @ApiModelProperty(value = "加减标识")
	@Dict(dicCode = "zjbs")
	private Integer zbabs;
	/**加权比率*/
	@Excel(name = "加权比率", width = 15)
    @ApiModelProperty(value = "加权比率")
	private java.math.BigDecimal jqbl;
	/**加权分值*/
	@Excel(name = "加权分值", width = 15)
    @ApiModelProperty(value = "加权分值")
	private java.math.BigDecimal jqfz;
	/**加权限制分值*/
	@Excel(name = "加权限制分值", width = 15)
    @ApiModelProperty(value = "加权限制分值")
	private java.math.BigDecimal jqxzfz;
	/**减权比率*/
	@Excel(name = "减权比率", width = 15)
    @ApiModelProperty(value = "减权比率")
	private java.math.BigDecimal kqbl;
	/**减权分值*/
	@Excel(name = "减权分值", width = 15)
    @ApiModelProperty(value = "减权分值")
	private java.math.BigDecimal kqfz;
	/**减权限制分值*/
	@Excel(name = "减权限制分值", width = 15)
    @ApiModelProperty(value = "减权限制分值")
	private java.math.BigDecimal kqxzfz;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
}
