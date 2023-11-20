package org.cmms.modules.dklldj.lldjgl.lldjsqNy.entity;

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
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
@Data
@TableName("rate_djsqxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_djsqxx对象", description="利率定价申请")
public class RateDjsqxxNy {
	/**组织标识*/
	@Excel(name = "组织名称", width = 15, dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
	private String zzbz;
	/**定价年份*/
	@Excel(name = "定价年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "lldj_khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode="lldj_khlx")
	private Integer khlx;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
	@ApiModelProperty(value = "法人代表")
	private String frdb;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15, dicCode = "dkqx")
	@ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqx")
	private Integer dkqx;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15, dicCode = "xddkpz_one")
	@ApiModelProperty(value = "信贷贷款品种")
	@Dict(dicCode = "xddkpz_one")
	private String xddkpz;
	/**综合授信额度*/
	@Excel(name = "综合授信额度(万元)", width = 15, numFormat = ",##0.000")
	@ApiModelProperty(value = "综合授信额度")
	private java.math.BigDecimal zhsxed;
	/**其中：贷款授信+承兑敞口*/
	@Excel(name = "其中：贷款授信+承兑敞口(万元)", width = 15, numFormat = ",##0.000")
	@ApiModelProperty(value = "其中：贷款授信+承兑敞口")
	private Double cdck;
	/**上年授信额度*/
	@Excel(name = "上年授信额度(万元)", width = 15, numFormat = ",##0.000")
	@ApiModelProperty(value = "上年授信额度")
	private java.math.BigDecimal snsxed;
	/**上年执行利率*/
	@Excel(name = "上年执行利率(%)", width = 15, numFormat = "#0.0000")
	@ApiModelProperty(value = "上年执行利率")
	private java.math.BigDecimal snzxll;
	/**是否便民卡*/
	@Excel(name = "是否便民卡", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否便民卡")
	@Dict(dicCode = "sfbz")
	private Integer sfbmk;
	/**是否保证保险贷款*/
	@Excel(name = "是否保证保险贷款", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否保证保险贷款")
	@Dict(dicCode = "sfbz")
	private Integer sfbzbxdk;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "操作标识", width = 15, dicCode = "lrbz")
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;


	/**上年贷款基点(加/减)BP*/
	//@Excel(name = "上年贷款基点(加/减)BP", width = 15)
	@ApiModelProperty(value = "上年贷款基点(加/减)BP")
	private java.math.BigDecimal sndkjdbp;
	/**是否竞争性客户*/
	//@Excel(name = "是否享受`小微客户定价普惠措施`", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否享受`小微客户定价普惠措施`")
	@Dict(dicCode = "sfbz")
	private String sfjzxkh;
	/**是否高危行业*/
	//@Excel(name = "是否高危行业", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否高危行业")
	@Dict(dicCode = "sfbz")
	private Integer sfgwhy;
	/**上年贷款利率上浮幅度*/
	//@Excel(name = "上年贷款利率上浮幅度(%)", width = 15)
	@ApiModelProperty(value = "上年贷款利率上浮幅度")
	private java.math.BigDecimal sndkllsffd;
	/**还款方式(1.其他；2.等额本金；3.等额本息)*/
	//@Excel(name = "还款方式", width = 15, dicCode = "hkfs")
	@ApiModelProperty(value = "还款方式(1.其他；2.等额本金；3.等额本息)")
	@Dict(dicCode = "hkfs")
	private Integer hkfs;
}
