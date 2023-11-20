package org.cmms.modules.dklldj.lldjgl.tslldjNy.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.dynamic.datasource.annotation.DS;
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
 * @Description: dwdw
 * @Author: jeecg-boot
 * @Date:   2022-09-14
 * @Version: V1.0
 */
@Data
@TableName("RATE_TSZXLLDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_TSZXLLDJB对象", description="dwdw")
public class RateTszxlldjb {

	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**定价年份*/
	@Excel(name = "定价年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
	private String frdb;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
    @ApiModelProperty(value = "贷款期限")
	private Integer dkqx;
	/**贷款类型*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private Integer dklx;
	/**贷款类型详情*/
	@Excel(name = "贷款类型详情", width = 15)
    @ApiModelProperty(value = "贷款类型详情")
	private Integer dklxxq;
	/**浮动幅度*/
	@Excel(name = "浮动幅度", width = 15)
    @ApiModelProperty(value = "浮动幅度")
	private java.math.BigDecimal fdfd;
	/**执行利率*/
	@Excel(name = "执行利率", width = 15)
    @ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**审批状态*/
	@Excel(name = "审批状态", width = 15)
    @ApiModelProperty(value = "审批状态")
	@Dict(dicCode = "nx_spzt")
	private Integer spzt;
	/**审批人*/
	@Excel(name = "审批人", width = 15)
    @ApiModelProperty(value = "审批人")
	private String spr;
	/**审批时间*/
	@Excel(name = "审批时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "审批时间")
	private Date spsj;
	/**情况说明*/
	@Excel(name = "情况说明", width = 15)
    @ApiModelProperty(value = "情况说明")
	private String qksm;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String note;
	/**定价编号*/
	@Excel(name = "定价编号", width = 15)
    @ApiModelProperty(value = "定价编号")
	private Long djid;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改操作员*/
	@Excel(name = "修改操作员", width = 15)
    @ApiModelProperty(value = "修改操作员")
	private String xgczy;
	/**修改状态*/
	@Excel(name = "修改状态", width = 15)
    @ApiModelProperty(value = "修改状态")
	@Dict(dicCode = "xgzt")
	private Integer xgzt;
	/**基准利率*/
	@Excel(name = "基准利率", width = 15)
    @ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jzll;
	/**基点(加/减)BP*/
	@Excel(name = "基点(加/减)BP", width = 15)
    @ApiModelProperty(value = "基点(加/减)BP")
	private java.math.BigDecimal jdbp;
	/**LPR利率*/
	@Excel(name = "LPR利率", width = 15)
    @ApiModelProperty(value = "LPR利率")
	private java.math.BigDecimal lprll;
	/**建议授信额度*/
	@Excel(name = "建议授信额度", width = 15)
    @ApiModelProperty(value = "建议授信额度")
	private java.math.BigDecimal jysxed;
	/**报价日期*/
	@Excel(name = "报价日期", width = 15)
    @ApiModelProperty(value = "报价日期")
	private String bjrq;
}
