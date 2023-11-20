package org.cmms.modules.dklldj.lldjgl.lldjjsHj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 执行利率定价表
 * @Author: penghr
 * @Date:   2022-04-16
 * @Version: V1.0
 */
@Data
@TableName("rate_zxlldjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_zxlldjb对象", description="执行利率定价表")
public class RateZxlldjbHj {

	/**定价ID*/
	@Excel(name = "定价编号", width = 15)
	@ApiModelProperty(value = "定价ID")
	private Long djid;
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
	/**身份证号或机构代码证编号*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "身份证号或机构代码证编号")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15, dicCode = "dkqxhj")
	@ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqxhj")
	private String dkqx;
	/**上年授信额度*/
	//@Excel(name = "上年授信额度", width = 15)
	@ApiModelProperty(value = "上年授信额度")
	private java.math.BigDecimal snsxed;
	/**上年执行利率*/
	//@Excel(name = "上年执行利率(%)", width = 15)
	@ApiModelProperty(value = "上年执行利率")
	private java.math.BigDecimal snzxll;
	/**上年贷款利率上浮幅度*/
	//@Excel(name = "上年贷款利率上浮幅度", width = 15)
	@ApiModelProperty(value = "上年贷款利率上浮幅度")
	private java.math.BigDecimal sndkllsffd;
	/**上年贷款基点(加/减)BP*/
	//@Excel(name = "上年贷款基点(加/减)BP", width = 15)
	@ApiModelProperty(value = "上年贷款基点(加/减)BP")
	private java.math.BigDecimal sndkjdbp;
	/**综合授信额度*/
	@Excel(name = "综合授信额度(万元)", width = 15, numFormat = ",##0.00")
	@ApiModelProperty(value = "综合授信额度")
	private java.math.BigDecimal zhsxed;
	/**贷款授信+承诺敞口*/
	@Excel(name = "贷款授信+承诺敞口(万元)", width = 15, numFormat = ",##0.00")
	@ApiModelProperty(value = "贷款授信+承诺敞口")
	private java.math.BigDecimal cdck;
	/**得分合计*/
	@Excel(name = "得分合计", width = 15, numFormat = "#0.00")
	@ApiModelProperty(value = "得分合计")
	private java.math.BigDecimal dfhj;
	/**执行利率*/
	@Excel(name = "执行利率(%)", width = 15, numFormat = "#0.0000")
	@ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	/**录入操作员*/
	@Excel(name = "定价人", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "定价时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	@ApiModelProperty(value = "修改人")
	private String xgczy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "修改时间")
	private Date xgsj;

	/**基准利率*/
	//@Excel(name = "基准利率", width = 15)
	@ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jjll;
	/**报价日期*/
	//@Excel(name = "报价日期", width = 15)
	@ApiModelProperty(value = "报价日期")
	private String bjrq;
	/**LPR利率*/
	//@Excel(name = "LPR利率", width = 15)
	@ApiModelProperty(value = "LPR利率")
	private java.math.BigDecimal lprll;
	/**基点(加/减)BP*/
	//@Excel(name = "按LPR加基点(BP)", width = 15)
	@ApiModelProperty(value = "基点(加/减)BP")
	private java.math.BigDecimal jdbp;
	/**查表幅度*/
	//@Excel(name = "查表幅度", width = 15)
	@ApiModelProperty(value = "查表幅度")
	private java.math.BigDecimal cbfd;
	/**十五、十六条检验后幅度*/
	//@Excel(name = "十五、十六条检验后幅度", width = 15)
	@ApiModelProperty(value = "十五、十六条检验后幅度")
	private java.math.BigDecimal jyhfd;
	/**上浮幅度*/
	//@Excel(name = "上浮幅度", width = 15)
	@ApiModelProperty(value = "上浮幅度")
	private java.math.BigDecimal sffd;
	/**优惠后基点(加/减)BP*/
	//@Excel(name = "优惠后LPR基点", width = 15)
	@ApiModelProperty(value = "优惠后基点(加/减)BP")
	private java.math.BigDecimal yhhjdbp;
	/**优惠后执行利率*/
	@TableField(exist = false)
	@ApiModelProperty(value = "优惠后执行利率")
	private java.math.BigDecimal yhhZxll6;
	/**优惠后最终LPR基点*/
	@TableField(exist = false)
	@ApiModelProperty(value = "优惠后最终LPR基点")
	private java.math.BigDecimal yhhLprjd7;
	/**优惠后执行利率*/
	//@Excel(name = "优惠后最终执行利率(%)", width = 15)
	@ApiModelProperty(value = "优惠后执行利率")
	private java.math.BigDecimal yhhzxll;
	/**录入标志*/
	//@Excel(name = "录入标志", width = 15, dicCode = "lrbz")
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;

	@TableField(exist = false)
	private String reCalc;
}
