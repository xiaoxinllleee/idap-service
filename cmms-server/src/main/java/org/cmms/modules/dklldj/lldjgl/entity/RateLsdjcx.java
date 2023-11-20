package org.cmms.modules.dklldj.lldjgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description: 客户历史定价查询
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Data
@TableName("rate_lsdjcx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_lsdjcx对象", description="客户历史定价查询")
public class RateLsdjcx {

	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;

	/**组织标识*/
	@Excel(name = "所属组织", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**定价年份*/
	@Excel(name = "定价年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**定价ID*/
	@Excel(name = "定价编号", width = 15)
    @ApiModelProperty(value = "定价ID")
	private Long djid;
	/**定价得分*/
	@Excel(name = "定价得分", width = 15, numFormat = "#0.00")
	@ApiModelProperty(value = "定价得分")
	private java.math.BigDecimal dfhj;
	/**基准利率*/
	@Excel(name = "基准利率(%)", width = 15, numFormat = "#0.0000")
	@ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jjll;
	/**上浮幅度*/
	@Excel(name = "上浮幅度(%)", width = 15, numFormat = "#0.00")
	@ApiModelProperty(value = "上浮幅度")
	private java.math.BigDecimal sffd;
	/**执行利率*/
	@Excel(name = "执行利率(%)", width = 15, numFormat = "#0.0000")
	@ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	/**优惠后执行利率(%)*/
	@Excel(name = "优惠后执行利率(%)", width = 15, numFormat = "#0.0000")
	@ApiModelProperty(value = "优惠后执行利率(%)")
	private java.math.BigDecimal yhhzxll;
	/**基点(加/减)BP*/
	@Excel(name = "按LPR加基点(BP)", width = 15, numFormat = "#0.00")
	@ApiModelProperty(value = "基点(加/减)BP")
	private java.math.BigDecimal jdbp;
	/**优惠后LPR基点*/
	@Excel(name = "优惠后按LPR加基点(BP)", width = 15, numFormat = "#0.00")
	@ApiModelProperty(value = "优惠后LPR基点")
	private java.math.BigDecimal yhhjdbp;
	/**定价日期*/
	@Excel(name = "定价时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "定价日期")
	private Date djrq;
	/**定价标识（1：普通定价，2：特殊定价）*/
	@Excel(name = "定价类别", width = 15, dicCode = "djbs")
	@ApiModelProperty(value = "定价标识（1：普通定价，2：特殊定价）")
	@Dict(dicCode = "djbs")
	private Integer djbs;
	/**录入操作员*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;

	/**法人代表*/
	//@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
	private String frdb;
	/**修改状态*/
	//@Excel(name = "修改状态", width = 15)
    @ApiModelProperty(value = "修改状态")
	@Dict(dicCode = "lrbz")
	private String xgzt;
	/**修改时间*/
	//@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改人*/
	//@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**LPR利率*/
	//@Excel(name = "LPR利率", width = 15)
    @ApiModelProperty(value = "LPR利率")
	private java.math.BigDecimal lprll;
}
