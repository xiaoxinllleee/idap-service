package org.cmms.modules.dklldj.tjfxgl.llfdph.entity;

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
 * @Description: 利率浮动排行
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Data
@TableName("RATE_ZXLLCX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_ZXLLCX对象", description="利率浮动排行")
public class Zxllcx {

	/**组织标识*/
	@Excel(name = "组织标识", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String zzbz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**定价ID*/
	@Excel(name = "定价ID", width = 15)
	@ApiModelProperty(value = "定价ID")
	private Long djid;
	/**定价年份*/
	//@Excel(name = "定价年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**定价日期*/
	//@Excel(name = "定价日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "定价日期")
	private Date djrq;
	/**法人代表*/
	//@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
	private String frdb;
	/**定价得分*/
	@Excel(name = "定价得分", width = 15)
    @ApiModelProperty(value = "定价得分")
	private java.math.BigDecimal dfhj;
	/**基准利率*/
	@Excel(name = "基准利率(%)", width = 15)
    @ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jjll;
	/**上浮幅度*/
	@Excel(name = "上浮幅度(%)", width = 15)
    @ApiModelProperty(value = "上浮幅度")
	private java.math.BigDecimal sffd;
	/**执行利率*/
	@Excel(name = "执行利率(%)", width = 15)
    @ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	/**LPR利率*/
	//@Excel(name = "LPR利率", width = 15)
	@ApiModelProperty(value = "LPR利率")
	private java.math.BigDecimal lprll;
	/**基点(加/减)BP*/
	//@Excel(name = "基点(加/减)BP", width = 15)
	@ApiModelProperty(value = "基点(加/减)BP")
	private java.math.BigDecimal jdbp;
	/**录入时间*/
	//@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
	//@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**修改状态*/
	//@Excel(name = "修改状态", width = 15)
    @ApiModelProperty(value = "修改状态")
	private Integer xgzt;
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
}
