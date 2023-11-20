package org.cmms.modules.ywgl.dkyw.lsdksjgl.entity;

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
import lombok.ToString;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 历史贷款数据管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_LSDKSJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_LSDKSJ对象", description="历史贷款数据管理")
@ToString
public class Lsdksjgl {

	/**jobnumber*/
    @ApiModelProperty(value = "工号")
	private String jobnumber;
	/**贷款投向2*/
    @ApiModelProperty(value = "贷款投向2")
	private String purposeType2;
	/**贷款投向3*/
    @ApiModelProperty(value = "贷款投向3")
	private String purposeType3;
	/**贷款投向4*/
    @ApiModelProperty(value = "贷款投向4")
	private String purposeType4;
	/**贷款投向5*/
    @ApiModelProperty(value = "贷款投向5")
	private String purposeType5;
	/**贷款投向6*/
    @ApiModelProperty(value = "贷款投向6")
	private String purposeType6;
	/**贷款投向7*/
    @ApiModelProperty(value = "贷款投向7")
	private String purposeType7;
	/**业务编号*/
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**业务种类*/
    @ApiModelProperty(value = "业务种类")
	private String purposeType;
	/**cardNo*/
    @ApiModelProperty(value = "cardNo")
	private String cardNo;





	/**客户经理标识*/
	@Excel(name = "客户经理", width = 15, dicCode = "khjlbh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "客户经理标识")
	@Dict(dicCode = "khjlbh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	@ExcelVerify(notNull = true)
	private String custManagerId;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	@ApiModelProperty(value = "机构代码")
	private String org;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String finInsName;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	@ExcelVerify(notNull = true)
	private String acctNo;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String ctfcCd;
	/**科目号*/
	@Excel(name = "记账科目", width = 15)
	@ApiModelProperty(value = "记账科目")
	private String jzkm;
	/**贷款发放日期*/
	@Excel(name = "贷款发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款发放日期")
	@ExcelVerify(notNull = true)
	private Date putOutDate;
	/**贷款到期日期*/
	@Excel(name = "贷款到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款到期日期")
	@ExcelVerify(notNull = true)
	private Date maturity;
	/**最早欠息日期*/
	@Excel(name="最早欠息日期", width = 15)
	@ApiModelProperty(value = "最早欠息日期")
	private String minCalcDate;
	/**贷款发放金额*/
	@Excel(name = "贷款发放金额", width = 15)
	@ApiModelProperty(value = "贷款发放金额")
	@ExcelVerify( interHandler = true)
	private java.math.BigDecimal putoutSum;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal balance;

	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入操作员*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	@ApiModelProperty(value = "修改人")
	private String xgczy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date xgsj;

	/**最早欠息日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "最早欠息日期")
	private Date qxDate;
	/**impfileday*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "impfileday")
	private Date impfileday;
	/**impfileline*/
    @ApiModelProperty(value = "impfileline")
	private Long impfileline;
	/**impfileid*/
    @ApiModelProperty(value = "impfileid")
	private Long impfileid;
	/**imptime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "imptime")
	private Date imptime;
	/**impuser*/
    @ApiModelProperty(value = "impuser")
	private String impuser;
	/**借据号*/
    @ApiModelProperty(value = "借据号")
	private String voucherNo;
	/**合同金额*/
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal appSum;
	/**贷款金额/累计发放金额*/
	/**核心余额*/
    @ApiModelProperty(value = "核心余额")
	private java.math.BigDecimal totleBalance;
	/**月利率‰*/
    @ApiModelProperty(value = "月利率‰")
	private java.math.BigDecimal appRate;
	/**客户经理(调查责任人)/信贷员*/
    @ApiModelProperty(value = "客户经理(调查责任人)/信贷员")
	private String oprCustCn;
	/**第一责任人*/
    @ApiModelProperty(value = "第一责任人")
	private String firstCustCn;
	/**担保方式/借款方式/贷款方式*/
    @ApiModelProperty(value = "担保方式/借款方式/贷款方式")
	private String vouchType;
	/**五级分类*/
    @ApiModelProperty(value = "五级分类")
	private String fiveClassType;
	/**测算五级分类*/
    @ApiModelProperty(value = "测算五级分类")
	private String fiveTypeCalc;
	/**借款用途*/
    @ApiModelProperty(value = "借款用途")
	private String actualPurpose;
	/**业务种类/贷款分类/业务品种*/
    @ApiModelProperty(value = "业务种类/贷款分类/业务品种")
	private String businessPhase;
	/**地址*/
    @ApiModelProperty(value = "地址")
	private String custBusadd;
	/**电话*/
    @ApiModelProperty(value = "电话")
	private String custTel;
	/**支付方式（自主、受托）*/
    @ApiModelProperty(value = "支付方式（自主、受托）")
	private String payMode;
	/**主客户经理（管户责任人）*/
    @ApiModelProperty(value = "主客户经理（管户责任人）")
	private String custCn;
	/**审批责任人*/
    @ApiModelProperty(value = "审批责任人")
	private String custCn1;
	/**逾期利率*/
    @ApiModelProperty(value = "逾期利率")
	private java.math.BigDecimal yqll;
	/**币种*/
    @ApiModelProperty(value = "币种")
	private String currency;
	/**客户号?*/
    @ApiModelProperty(value = "客户号?")
	private Long custId;
	/**期限*/
    @ApiModelProperty(value = "期限")
	private Long appTerm;
	/**年利率*/
    @ApiModelProperty(value = "年利率")
	private java.math.BigDecimal rate;
	/**合同号*/
    @ApiModelProperty(value = "合同号")
	private String contractNo;
	/**拓展客户经理号?*/
    @ApiModelProperty(value = "拓展客户经理号?")
	private String custCn2;

	/**账户标识?*//*
	@Excel(name = "账户标识?", width = 15)
    @ApiModelProperty(value = "账户标识?")
	private String custType;
	*//**账户标识1*//*
	@Excel(name = "账户标识1", width = 15)
    @ApiModelProperty(value = "账户标识1")
	private String custType1;
	*//**账户标识2*//*
	@Excel(name = "账户标识2", width = 15)
    @ApiModelProperty(value = "账户标识2")
	private String custType2;
	*//**账户标识3*//*
	@Excel(name = "账户标识3", width = 15)
    @ApiModelProperty(value = "账户标识3")
	private String custType3;
	*//**贷款投向1*//*
	@Excel(name = "贷款投向1", width = 15)
    @ApiModelProperty(value = "贷款投向1")
	private String purposeType1;*/
}
