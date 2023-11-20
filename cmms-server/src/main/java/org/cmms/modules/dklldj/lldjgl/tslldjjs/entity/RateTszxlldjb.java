package org.cmms.modules.dklldj.lldjgl.tslldjjs.entity;

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
 * @Description: 特殊利率定价
 * @Author: jeecg-boot
 * @Date:   2021-04-02
 * @Version: V1.0
 */
@Data
@TableName("rate_tszxlldjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_tszxlldjb对象", description="特殊利率定价")
public class RateTszxlldjb {

	/**组织标识*/
	@Excel(name = "所属组织", width = 15, dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	/**定价年份*/
	@Excel(name = "定价年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**定价编号*/
	@Excel(name = "定价编号", width = 15)
	@ApiModelProperty(value = "定价编号")
	@TableId(type = IdType.INPUT)
	private Long djid;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**法人代表*/
	//@Excel(name = "法人代表", width = 15)
	@ApiModelProperty(value = "法人代表")
	private String frdb;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15, dicCode = "dkqx")
	@ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqx")
	private Integer dkqx;
	/**基准利率*/
	@Excel(name = "基准利率(%)", width = 15, numFormat = "#0.0000")
	@ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jzll;
	/**浮动幅度*/
	@Excel(name = "利率浮动幅度(%)", width = 15, numFormat = "#0.00")
	@ApiModelProperty(value = "浮动幅度")
	private java.math.BigDecimal fdfd;
	/**执行利率*/
	@Excel(name = "执行利率(%)", width = 15, numFormat = "#0.0000")
	@ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	/**录入操作员*/
	@Excel(name = "定价人", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "定价时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**审批状态*/
	@Excel(name = "确认状态", width = 15, dicCode = "confirm_status")
	@ApiModelProperty(value = "审批状态")
	@Dict(dicCode = "confirm_status")
	private Integer spzt;
	/**审批人*/
	@Excel(name = "确认人", width = 15)
	@ApiModelProperty(value = "审批人")
	private String spr;
	/**审批时间*/
	@Excel(name = "确认时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "审批时间")
	private Date spsj;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String note;
	/**修改状态*/
	@Excel(name = "修改状态", width = 15, dicCode = "modify_status")
	@ApiModelProperty(value = "修改状态")
	@Dict(dicCode = "modify_status")
	private Integer xgzt;
	/**修改操作员*/
	@Excel(name = "修改操作员", width = 15)
	@ApiModelProperty(value = "修改操作员")
	private String xgczy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "修改时间")
	private Date xgsj;

	/**建议授信额度*/
	//@Excel(name = "建议授信额度", width = 15)
    @ApiModelProperty(value = "建议授信额度")
	private java.math.BigDecimal jysxed;
	/**报价日期*/
	//@Excel(name = "报价日期", width = 15)
    @ApiModelProperty(value = "报价日期")
	private String bjrq;
	/**贷款类型*/
	//@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private Integer dklx;
	/**贷款类型详情*/
	//@Excel(name = "贷款类型详情", width = 15)
    @ApiModelProperty(value = "贷款类型详情")
	private Integer dklxxq;
	/**录入标志*/
	//@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**情况说明*/
	//@Excel(name = "情况说明", width = 15)
    @ApiModelProperty(value = "情况说明")
	private String qksm;
	/**基点(加/减)BP*/
	//@Excel(name = "基点(加/减)BP", width = 15)
    @ApiModelProperty(value = "基点(加/减)BP")
	private java.math.BigDecimal jdbp;
	/**LPR利率*/
	//@Excel(name = "LPR利率", width = 15)
    @ApiModelProperty(value = "LPR利率")
	private java.math.BigDecimal lprll;
}
