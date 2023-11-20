package org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Data
@TableName("rate_zxllcx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_zxllcx对象", description="客户执行利率查询")
public class RateZxllcxHj {

	/**组织标识*/
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**证件号码*/
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**定价年份*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**定价ID*/
	@ApiModelProperty(value = "定价ID")
	private Long djid;
	/**基准利率*/
	@ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jjll;
	/**上浮幅度*/
	@ApiModelProperty(value = "上浮幅度")
	private java.math.BigDecimal sffd;
	/**执行利率*/
	@ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	/**优惠后执行利率*/
	@ApiModelProperty(value = "优惠后执行利率")
	private java.math.BigDecimal yhhzxll;
	/**基点(加/减)BP*/
	@ApiModelProperty(value = "基点(加/减)BP")
	private java.math.BigDecimal jdbp;
	/**优惠后LPR基点*/
	@ApiModelProperty(value = "优惠后LPR基点")
	private java.math.BigDecimal yhhjdbp;
	/**录入操作员*/
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;

	/**LPR利率*/
    @ApiModelProperty(value = "LPR利率")
	private java.math.BigDecimal lprll;
	/**定价日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "定价日期")
	private Date djrq;
	/**法人代表*/
    @ApiModelProperty(value = "法人代表")
	private String frdb;
	/**定价得分*/
    @ApiModelProperty(value = "定价得分")
	private java.math.BigDecimal dfhj;
	/**修改状态*/
    @ApiModelProperty(value = "修改状态")
	@Dict(dicCode = "lrbz")
	private String xgzt;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
