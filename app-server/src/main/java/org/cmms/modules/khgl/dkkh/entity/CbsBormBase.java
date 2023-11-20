package org.cmms.modules.khgl.dkkh.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷款主档宽表
 * @Author: jeecg-boot
 * @Date:   2022-04-11
 * @Version: V1.0
 */
@Data
@TableName("cbs_borm_base")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cbs_borm_base对象", description="贷款主档宽表")
public class CbsBormBase {
	
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String custId;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	private String brNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String brName;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String custName;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String identType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**贷款账号(17位)*/
	@Excel(name = "贷款账号(17位)", width = 15)
    @ApiModelProperty(value = "贷款账号(17位)")
	private String acctNo;
	/**信贷拮据号*/
	@Excel(name = "信贷拮据号", width = 15)
    @ApiModelProperty(value = "信贷拮据号")
	private String voucherNo;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String businessNo;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
    @ApiModelProperty(value = "借款日期")
	private String qxDate;
	/**到期日*/
	@Excel(name = "到期日", width = 15)
    @ApiModelProperty(value = "到期日")
	private String endDate;
	/**贷款种类*/
	@Excel(name = "贷款种类", width = 15)
    @ApiModelProperty(value = "贷款种类")
	private String loanKind;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private Integer remainingDays;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal intRate;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal applicAmount;
	/**放款金额*/
	@Excel(name = "放款金额", width = 15)
    @ApiModelProperty(value = "放款金额")
	private java.math.BigDecimal advVal;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal loanBal;
	/**起息日*/
	@Excel(name = "起息日", width = 15)
    @ApiModelProperty(value = "起息日")
	private String intStrtDate;
	/**结息日*/
	@Excel(name = "结息日", width = 15)
    @ApiModelProperty(value = "结息日")
	private String repayDay;
	/**产品大类*/
	@Excel(name = "产品大类", width = 15)
    @ApiModelProperty(value = "产品大类")
	private String tfMcaSubAcctTp;
	/**产品小类*/
	@Excel(name = "产品小类", width = 15)
    @ApiModelProperty(value = "产品小类")
	private String tfMcaSubIntCat;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String catTypeName;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String custType;
	/**客户所属行业类型*/
	@Excel(name = "客户所属行业类型", width = 15)
    @ApiModelProperty(value = "客户所属行业类型")
	private String mainIndustry;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
    @ApiModelProperty(value = "企业规模")
	private String entScale;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String purposeType;
	/**扣款户名*/
	@Excel(name = "扣款户名", width = 15)
    @ApiModelProperty(value = "扣款户名")
	private String payCustName;
	/**还款账户余额*/
	@Excel(name = "还款账户余额", width = 15)
    @ApiModelProperty(value = "还款账户余额")
	private java.math.BigDecimal payAcctBal;
	/**五级分类调整日期*/
	@Excel(name = "五级分类调整日期", width = 15)
    @ApiModelProperty(value = "五级分类调整日期")
	private String fiveAdjustDate;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15)
    @ApiModelProperty(value = "贷款形态")
	private String fiveClassType;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private String vouchType;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String custCn;
	/**最终审批人*/
	@Excel(name = "最终审批人", width = 15)
    @ApiModelProperty(value = "最终审批人")
	private String dutyCustId;
	/**最终审批人name*/
	@Excel(name = "最终审批人name", width = 15)
    @ApiModelProperty(value = "最终审批人name")
	private String employeeName;
	/**客户电话*/
	@Excel(name = "客户电话", width = 15)
    @ApiModelProperty(value = "客户电话")
	private String linkmanTel;
	/**客户地址*/
	@Excel(name = "客户地址", width = 15)
    @ApiModelProperty(value = "客户地址")
	private String addr;
	/**贷款账户欠息*/
	@Excel(name = "贷款账户欠息", width = 15)
    @ApiModelProperty(value = "贷款账户欠息")
	private java.math.BigDecimal oweInterest;
	/**放款账号*/
	@Excel(name = "放款账号", width = 15)
    @ApiModelProperty(value = "放款账号")
	private String trfAcctNo;
	/**便民卡卡号*/
	@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "便民卡卡号")
	private String cardNo;
	/**还款方式*/
	@Excel(name = "还款方式", width = 15)
    @ApiModelProperty(value = "还款方式")
	private String repaySched;
	/**贷款总期数*/
	@Excel(name = "贷款总期数", width = 15)
    @ApiModelProperty(value = "贷款总期数")
	private Integer loanTrm;
	/**不良贷款标识*/
	@Excel(name = "不良贷款标识", width = 15)
    @ApiModelProperty(value = "不良贷款标识")
	private String badDebtInd;
	/**产品代码*/
	@Excel(name = "产品代码", width = 15)
    @ApiModelProperty(value = "产品代码")
	private String actType;
	/**桶子[P],未还本金13523拖欠本金*/
	@Excel(name = "桶子[P],未还本金13523拖欠本金", width = 15)
    @ApiModelProperty(value = "桶子[P],未还本金13523拖欠本金")
	private java.math.BigDecimal unpdPrinBal;
	/**桶子[I],拖欠利息结息交易13509*/
	@Excel(name = "桶子[I],拖欠利息结息交易13509", width = 15)
    @ApiModelProperty(value = "桶子[I],拖欠利息结息交易13509")
	private java.math.BigDecimal capUnpdInt;
	/**桶子[A],FINE2利息罚息结息交易11721*/
	@Excel(name = "桶子[A],FINE2利息罚息结息交易11721", width = 15)
    @ApiModelProperty(value = "桶子[A],FINE2利息罚息结息交易11721")
	private java.math.BigDecimal unpdArrsIntBal;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curr;
	/**利息计提,每日增加一个增量,结息清零*/
	@Excel(name = "利息计提,每日增加一个增量,结息清零", width = 15)
    @ApiModelProperty(value = "利息计提,每日增加一个增量,结息清零")
	private java.math.BigDecimal intAccr;
	/**拖欠本金*/
	@Excel(name = "拖欠本金", width = 15)
    @ApiModelProperty(value = "拖欠本金")
	private java.math.BigDecimal unpdArrPrnBal;
	/**本金罚息计提*/
	@Excel(name = "本金罚息计提", width = 15)
    @ApiModelProperty(value = "本金罚息计提")
	private java.math.BigDecimal arrIntAccr;
	/**利息罚息计提*/
	@Excel(name = "利息罚息计提", width = 15)
    @ApiModelProperty(value = "利息罚息计提")
	private java.math.BigDecimal fine2IntAccr;
	/**欠本罚息*/
	@Excel(name = "欠本罚息", width = 15)
    @ApiModelProperty(value = "欠本罚息")
	private java.math.BigDecimal unpdIntArrPrn;
	/**复利应计利息,结息时加入到桶子[E]bois.unpd_int_arr_prn*/
	@Excel(name = "复利应计利息,结息时加入到桶子[E]bois.unpd_int_arr_prn", width = 15)
    @ApiModelProperty(value = "复利应计利息,结息时加入到桶子[E]bois.unpd_int_arr_prn")
	private java.math.BigDecimal fine4IntAccr;
	/**本金核算码*/
	@Excel(name = "本金核算码", width = 15)
    @ApiModelProperty(value = "本金核算码")
	private String glClassCode;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal appSum;
	/**应计利息*/
	@Excel(name = "应计利息", width = 15)
    @ApiModelProperty(value = "应计利息")
	private java.math.BigDecimal intIncr;
	/**是否停止计息*/
	@Excel(name = "是否停止计息", width = 15)
    @ApiModelProperty(value = "是否停止计息")
	private String stopAccrual;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String subjNo;
	/**科目号名称*/
	@Excel(name = "科目号名称", width = 15)
    @ApiModelProperty(value = "科目号名称")
	private String subjName;
	/**最后金融交易日期*/
	@Excel(name = "最后金融交易日期", width = 15)
    @ApiModelProperty(value = "最后金融交易日期")
	private String lstFinDate;
	/**指该贷款账户的建立是由哪个或有账户来的(垫款贷款)*/
	@Excel(name = "指该贷款账户的建立是由哪个或有账户来的(垫款贷款)", width = 15)
    @ApiModelProperty(value = "指该贷款账户的建立是由哪个或有账户来的(垫款贷款)")
	private String ctaReference;
	/**贷款账号(16位)*/
	@Excel(name = "贷款账号(16位)", width = 15)
    @ApiModelProperty(value = "贷款账号(16位)")
	private String accNo;
	/**展期前产品子类*/
	@Excel(name = "展期前产品子类", width = 15)
    @ApiModelProperty(value = "展期前产品子类")
	private String currIntCat;
	/**展期后产品子类*/
	@Excel(name = "展期后产品子类", width = 15)
    @ApiModelProperty(value = "展期后产品子类")
	private String newIntCat;
	/**展期后产品代码*/
	@Excel(name = "展期后产品代码", width = 15)
    @ApiModelProperty(value = "展期后产品代码")
	private String newAcctType;
	/**展期前产品代码*/
	@Excel(name = "展期前产品代码", width = 15)
    @ApiModelProperty(value = "展期前产品代码")
	private String currAcctType;
	/**展期前到期日*/
	@Excel(name = "展期前到期日", width = 15)
    @ApiModelProperty(value = "展期前到期日")
	private String currExpDate;
	/**展期后到期日*/
	@Excel(name = "展期后到期日", width = 15)
    @ApiModelProperty(value = "展期后到期日")
	private String newExpDate;
	/**重整批准日期,交易当天*/
	@Excel(name = "重整批准日期,交易当天", width = 15)
    @ApiModelProperty(value = "重整批准日期,交易当天")
	private String approveDt;
	/**展期原利率*/
	@Excel(name = "展期原利率", width = 15)
    @ApiModelProperty(value = "展期原利率")
	private java.math.BigDecimal oldRate;
	/**展期标志*/
	@Excel(name = "展期标志", width = 15)
    @ApiModelProperty(value = "展期标志")
	private String expFlag;
	/**核销本金*/
//	@Excel(name = "核销本金", width = 15)
//    @ApiModelProperty(value = "核销本金")
//	private java.math.BigDecimal glBucketDue01;
//	/**核销利息*/
//	@Excel(name = "核销利息", width = 15)
//    @ApiModelProperty(value = "核销利息")
//	private java.math.BigDecimal glBucketDue02;
//	/**核销罚息3*/
//	@Excel(name = "核销罚息3", width = 15)
//    @ApiModelProperty(value = "核销罚息3")
//	private java.math.BigDecimal glBucketDue03;
//	/**核销罚息4*/
//	@Excel(name = "核销罚息4", width = 15)
//    @ApiModelProperty(value = "核销罚息4")
//	private java.math.BigDecimal glBucketDue04;
//	/**核销罚息5*/
//	@Excel(name = "核销罚息5", width = 15)
//    @ApiModelProperty(value = "核销罚息5")
//	private java.math.BigDecimal glBucketDue05;
	/**放款日期*/
	@Excel(name = "放款日期", width = 15)
    @ApiModelProperty(value = "放款日期")
	private String appPutOutDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String appMaturityDate;
	/**置换旧账号*/
	@Excel(name = "置换旧账号", width = 15)
    @ApiModelProperty(value = "置换旧账号")
	private String reOldAcctNo;
	/**本金置换日期*/
	@Excel(name = "本金置换日期", width = 15)
    @ApiModelProperty(value = "本金置换日期")
	private Integer prnReDate;
	/**利息置换日期*/
	@Excel(name = "利息置换日期", width = 15)
    @ApiModelProperty(value = "利息置换日期")
	private Integer intReDate;
	/**本金置换金额*/
	@Excel(name = "本金置换金额", width = 15)
    @ApiModelProperty(value = "本金置换金额")
	private java.math.BigDecimal prnReAmount;
	/**利息置换金额*/
	@Excel(name = "利息置换金额", width = 15)
    @ApiModelProperty(value = "利息置换金额")
	private java.math.BigDecimal intReAmount;
	/**贷款还款金额*/
	@Excel(name = "贷款还款金额", width = 15)
    @ApiModelProperty(value = "贷款还款金额")
	private java.math.BigDecimal dueAmt;
	/**原贷款还款金额*/
	@Excel(name = "原贷款还款金额", width = 15)
    @ApiModelProperty(value = "原贷款还款金额")
	private java.math.BigDecimal oldDueAmt;
	/**原贷款总期数*/
	@Excel(name = "原贷款总期数", width = 15)
    @ApiModelProperty(value = "原贷款总期数")
	private Integer oldLoanTrm;
	/**资金协议号*/
	@Excel(name = "资金协议号", width = 15)
    @ApiModelProperty(value = "资金协议号")
	private String fundAgrNo;
	/**核销日期*/
	@Excel(name = "核销日期", width = 15)
    @ApiModelProperty(value = "核销日期")
	private String badDebtTrfDte;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
	private String signDate;
	/**合同约定发放日*/
	@Excel(name = "合同约定发放日", width = 15)
    @ApiModelProperty(value = "合同约定发放日")
	private String contractPutOutDate;
	/**合同约定到期日*/
	@Excel(name = "合同约定到期日", width = 15)
    @ApiModelProperty(value = "合同约定到期日")
	private String contractMaturity;
	/**合同批准日期*/
	@Excel(name = "合同批准日期", width = 15)
    @ApiModelProperty(value = "合同批准日期")
	private String appDate;
	/**录入机构*/
	@Excel(name = "录入机构", width = 15)
    @ApiModelProperty(value = "录入机构")
	private String orgId;
	/**申请币种*/
	@Excel(name = "申请币种", width = 15)
    @ApiModelProperty(value = "申请币种")
	private String applyCurrency;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
    @ApiModelProperty(value = "贷款期限")
	private String appTerm;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15)
    @ApiModelProperty(value = "信贷贷款品种")
	private String businessPhase;
	/**五级分类调整日期*/
	@Excel(name = "五级分类调整日期", width = 15)
    @ApiModelProperty(value = "五级分类调整日期")
	private String fiveClassDate;
	/**剩余期数*/
	@Excel(name = "剩余期数", width = 15)
    @ApiModelProperty(value = "剩余期数")
	private Integer remRepays;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String stat;
	/**拖欠本金*/
	@Excel(name = "拖欠本金", width = 15)
    @ApiModelProperty(value = "拖欠本金")
	private String oweAmtP;
	/**拖欠利息*/
	@Excel(name = "拖欠利息", width = 15)
    @ApiModelProperty(value = "拖欠利息")
	private String oweAmtI;
	/**本金罚息*/
	@Excel(name = "本金罚息", width = 15)
    @ApiModelProperty(value = "本金罚息")
	private String oweAmtW;
	/**利息罚息*/
	@Excel(name = "利息罚息", width = 15)
    @ApiModelProperty(value = "利息罚息")
	private String oweAmtA;
	/**拖欠复利*/
	@Excel(name = "拖欠复利", width = 15)
    @ApiModelProperty(value = "拖欠复利")
	private String oweAmtE;
	/**应计本金*/
	@Excel(name = "应计本金", width = 15)
    @ApiModelProperty(value = "应计本金")
	private String bucketCdeP;
	/**应计本金的利息*/
	@Excel(name = "应计本金的利息", width = 15)
    @ApiModelProperty(value = "应计本金的利息")
	private String bucketCdeI;
	/**应计本金的罚息*/
	@Excel(name = "应计本金的罚息", width = 15)
    @ApiModelProperty(value = "应计本金的罚息")
	private String bucketCdeW;
	/**应计利息的罚息*/
	@Excel(name = "应计利息的罚息", width = 15)
    @ApiModelProperty(value = "应计利息的罚息")
	private String bucketCdeA;
	/**应计利息的复利*/
	@Excel(name = "应计利息的复利", width = 15)
    @ApiModelProperty(value = "应计利息的复利")
	private String bucketCdeE;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
/*	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**autoDbtAcctNo*/
	@Excel(name = "autoDbtAcctNo", width = 15)
    @ApiModelProperty(value = "autoDbtAcctNo")
	private String autoDbtAcctNo;
	/**dttime*/
//	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "dttime")
//	private Date dttime;
}
