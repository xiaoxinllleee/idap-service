package org.cmms.modules.dklldj.lldjgl.lldjsq.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.xdgl.grdkgl.entity.RateDbxxgl;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 利率定价申请详情
 * @Author: jeecg-boot
 * @Date:   2021-03-25
 * @Version: V1.0
 */
@Data
@TableName("RATE_DJSQXQ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_DJSQXQ对象", description="利率定价申请详情")
public class RateDjsqxq {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;

	/**组织标志*/
	@Excel(name = "所属组织", width = 15, dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
	@ApiModelProperty(value = "组织标志")
	@Dict(dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
	private String zzbz;
	/**定价年份*/
	@Excel(name = "定价年份", width = 15)
    @ApiModelProperty(value = "定价年份")
	private String djnf;
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
	@Dict(dicCode = "lldj_khlx")
	private String khlx;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
	private String frdb;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15, dicCode = "dkqxly")
	@ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqxly")
	private Integer dkqx;
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
	/**是否竞争性客户*/
	@Excel(name = "是否享受`小微客户定价普惠措施`", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否享受`小微客户定价普惠措施`")
	@Dict(dicCode = "sfbz")
	private java.lang.Integer sfjzxkh;
	/**是否高危行业*/
	@Excel(name = "是否高危行业", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否高危行业")
	@Dict(dicCode = "sfbz")
	private java.lang.Integer sfgwhy;

	/**农村三权抵（质）押贷款*/
	@Excel(name = "农村三权抵（质）押贷款", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "农村三权抵（质）押贷款")
	@Dict(dicCode = "sfbz")
	private java.lang.Integer ncsqdzydk;
	/**是否为花炮企业*/
	@Excel(name = "是否为花炮企业", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否为花炮企业")
	@Dict(dicCode = "sfbz")
	private java.lang.Integer sfhpqy;

	/**上年执行利率*/
	//@Excel(name = "上年执行利率(%)", width = 15)
	@ApiModelProperty(value = "上年执行利率")
	private java.math.BigDecimal snzxll;
	/**上年授信额度*/
	@Excel(name = "上年授信额度(万元)", width = 15)
    @ApiModelProperty(value = "上年授信额度")
	private java.math.BigDecimal snsxed = new BigDecimal(0.00);
	/**上年贷款利率上浮幅度*/
	//@Excel(name = "上年贷款利率上浮幅度(%)", width = 15)
    @ApiModelProperty(value = "上年贷款利率上浮幅度")
	private java.math.BigDecimal sndkllsffd = new BigDecimal(0.00);
	/**综合授信额度*/
	@Excel(name = "综合授信额度", width = 15)
    @ApiModelProperty(value = "综合授信额度")
	private java.math.BigDecimal zhsxed;
	/**其中：贷款授信+承兑敞口(万元)*/
	@Excel(name = "其中：贷款授信+承兑敞口(万元)", width = 15)
    @ApiModelProperty(value = "其中：贷款授信+承兑敞口(万元)")
	private java.math.BigDecimal cdck;
	/**信贷贷款品种*/
	//@Excel(name = "信贷贷款品种", width = 15)
	@ApiModelProperty(value = "信贷贷款品种")
	private java.lang.String xddkpz;
	/**还款方式(1.其他；2.等额本金；3.等额本息)*/
	//@Excel(name = "还款方式", width = 15, dicCode = "hkfs")
	@ApiModelProperty(value = "还款方式(1.其他；2.等额本金；3.等额本息)")
	@Dict(dicCode = "hkfs")
	private java.lang.Integer hkfs;
	/**上年贷款基点(加/减)BP*/
	//@Excel(name = "上年贷款基点(加/减)BP", width = 15)
	@ApiModelProperty(value = "上年贷款基点(加/减)BP")
	private java.math.BigDecimal sndkjdbp;

	/**信用等级*/
	@Excel(name = "信用等级", width = 15)
	@ApiModelProperty(value = "信用等级")
	@JsonProperty("KH00001")
	private String KH00001;

	/**经营期限*/
	@Excel(name = "经营期限", width = 15)
	@ApiModelProperty(value = "信用等级")
	@JsonProperty("KH00004")
	private String KH00004;

	/**借款人（含法人代表、主要股东及配偶）征信有不良记录*/
	@Excel(name = "GZ00009", width = 15)
	@ApiModelProperty(value = "GZ00009")
	@JsonProperty("GZ00009")
	private String GZ00009;
	/**借款人上年在本行贷款（含贷记卡）未按期清息、还款次数*/
	@Excel(name = "GZ00010", width = 15)
	@ApiModelProperty(value = "GZ00010")
	@JsonProperty("GZ00010")
	private String GZ00010;

	/**资产总额*/
	@Excel(name = "GZ00013", width = 15)
	@ApiModelProperty(value = "GZ00013")
	@JsonProperty("GZ00013")
	private String GZ00013;
	/**负债总额*/
	@Excel(name = "GZ00014", width = 15)
	@ApiModelProperty(value = "GZ00014")
	@JsonProperty("GZ00014")
	private String GZ00014;
	/**资产负债率(%)*/
	@Excel(name = "GZ00015", width = 15)
	@ApiModelProperty(value = "GZ00015")
	@JsonProperty("GZ00015")
	private String GZ00015;

	/**销售收入*/
	@Excel(name = "GZ00021", width = 15)
	@ApiModelProperty(value = "GZ00021")
	@JsonProperty("GZ00021")
	private String GZ00021;
	/**流动负债*/
	@Excel(name = "GZ00022", width = 15)
	@ApiModelProperty(value = "GZ00022")
	@JsonProperty("GZ00022")
	private String GZ00022;
	/**倍数*/
	@Excel(name = "GZ00023", width = 15)
	@ApiModelProperty(value = "GZ00023")
	@JsonProperty("GZ00023")
	private String GZ00023;

	/**开户基本账户往来年限*/
	@Excel(name = "GZ00031", width = 15)
	@ApiModelProperty(value = "GZ00031")
	@JsonProperty("GZ00031")
	private String GZ00031;
	/**前三年第一个年度存款日平*/
	@Excel(name = "GZ00033", width = 15)
	@ApiModelProperty(value = "GZ00033")
	@JsonProperty("GZ00033")
	private String GZ00033;
	/**前三年第二个年度存款日平*/
	@Excel(name = "GZ00034", width = 15)
	@ApiModelProperty(value = "GZ00034")
	@JsonProperty("GZ00034")
	private String GZ00034;
	/**前三年第三个年度存款日平*/
	@Excel(name = "GZ00035", width = 15)
	@ApiModelProperty(value = "GZ00035")
	@JsonProperty("GZ00035")
	private String GZ00035;
	/**日平存款占贷款比例(%)*/
	@Excel(name = "GZ00032", width = 15)
	@ApiModelProperty(value = "GZ00032")
	@JsonProperty("GZ00032")
	private String GZ00032;
	/**客户上一个年度在其他银行存款日平*/
	@Excel(name = "GZ00036", width = 15)
	@ApiModelProperty(value = "GZ00036")
	@JsonProperty("GZ00036")
	private String GZ00036;
	/**客户上一个年度在其他银行定期存款日平*/
	@Excel(name = "GZ00037", width = 15)
	@ApiModelProperty(value = "GZ00037")
	@JsonProperty("GZ00037")
	private String GZ00037;
	/**定价存款日平合计*/
	@Excel(name = "GZ00038", width = 15)
	@ApiModelProperty(value = "GZ00038")
	@JsonProperty("GZ00038")
	private String GZ00038;

	/**上年执行利率（‰）*/
	@Excel(name = "GZ00039", width = 15)
	@ApiModelProperty(value = "GZ00039")
	@JsonProperty("GZ00039")
	private String GZ00039;
	/**本行资金成本率(%)*/
	@Excel(name = "GZ00040", width = 15)
	@ApiModelProperty(value = "GZ00040")
	@JsonProperty("GZ00040")
	private String GZ00040;
	/**上年贷款日平*/
	@Excel(name = "GZ00041", width = 15)
	@ApiModelProperty(value = "GZ00041")
	@JsonProperty("GZ00041")
	private String GZ00041;
	/**收益贡献*/
	@Excel(name = "GZ00042", width = 15)
	@ApiModelProperty(value = "GZ00042")
	@JsonProperty("GZ00042")
	private String GZ00042;

	/**本年在本行代发工资人数*/
	@Excel(name = "GZ00043", width = 15)
	@ApiModelProperty(value = "GZ00043")
	@JsonProperty("GZ00043")
	private String GZ00043;
	/**高危行业财产保险本年应保或第一受益人不是本行*/
	@Excel(name = "GZ00044", width = 15)
	@ApiModelProperty(value = "GZ00044")
	@JsonProperty("GZ00044")
	private String GZ00044;
	/**高危行业财产保险本年应保不全*/
	@Excel(name = "GZ00045", width = 15)
	@ApiModelProperty(value = "GZ00045")
	@JsonProperty("GZ00045")
	private String GZ00045;
	/**保险到期未能如期续保且第一受益人不是本行、不能覆盖定价周期*/
	@Excel(name = "GZ00046", width = 15)
	@ApiModelProperty(value = "GZ00046")
	@JsonProperty("GZ00046")
	private String GZ00046;

	/**实际控制企业有国际贸易业务外汇结算量（美元）*/
	@Excel(name = "GZ00047", width = 15)
	@ApiModelProperty(value = "GZ00047")
	@JsonProperty("GZ00047")
	private String GZ00047;
	/**其他银行存款日平占本行存款日平比例(%)*/
	@Excel(name = "GZ00048", width = 15)
	@ApiModelProperty(value = "GZ00048")
	@JsonProperty("GZ00048")
	private String GZ00048;
	/**基本账户未在我行或在我行但资金归行不走基本账户*/
	@Excel(name = "GZ00049", width = 15)
	@ApiModelProperty(value = "GZ00049")
	@JsonProperty("GZ00049")
	private String GZ00049;
	/**客户能够开立我行手机银行、口袋零钱及其他第三方支付绑定我行卡但未开通的，或开通未使用的，每项扣2分*/
	@Excel(name = "GZ00057", width = 15)
	@ApiModelProperty(value = "GZ00057")
	@JsonProperty("GZ00057")
	private String GZ00057;

	/**贷款一年（含）以内基准利率(%)*/
	@Excel(name = "GZ00050", width = 15)
	@ApiModelProperty(value = "GZ00050")
	@JsonProperty("GZ00050")
	private String GZ00050;
	/**贷款一至五年（含）基准利率(%)*/
	@Excel(name = "GZ00051", width = 15)
	@ApiModelProperty(value = "GZ00051")
	@JsonProperty("GZ00051")
	private String GZ00051;
	/**贷款五年以上基准利率(%)*/
	@Excel(name = "GZ00052", width = 15)
	@ApiModelProperty(value = "GZ00052")
	@JsonProperty("GZ00052")
	private String GZ00052;

	/**本行资金成本率(%)*/
	@Excel(name = "GZ00053", width = 15)
	@ApiModelProperty(value = "GZ00053")
	@JsonProperty("GZ00053")
	private String GZ00053;

	@TableField(exist = false)
	private List<RateDbxxgl> RateDbxxgls;
}
