package org.cmms.modules.sjxf.hxxt.cktzb.entity;

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
 * @Description: 存款拓展表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_inve")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_inve对象", description="存款拓展表")
public class Cktzb {

	/**主键*/
	@Excel(name = "主键", width = 15)
    @ApiModelProperty(value = "主键")
	@TableField(value = "key_1")
	private String key1;
	/**当日总存现金额*/
	@Excel(name = "当日总存现金额", width = 15)
    @ApiModelProperty(value = "当日总存现金额")
	private java.math.BigDecimal todCdepTotAmt;
	/**当日总取现金额*/
	@Excel(name = "当日总取现金额", width = 15)
    @ApiModelProperty(value = "当日总取现金额")
	private java.math.BigDecimal todCwdlTotAmt;
	/**通提密码标示*/
	@Excel(name = "通提密码标示", width = 15)
    @ApiModelProperty(value = "通提密码标示")
	private String allBrInd;
	/**通提密码*/
	@Excel(name = "通提密码", width = 15)
    @ApiModelProperty(value = "通提密码")
	private String allBrPwd;
	/**通提密码尝试次数*/
	@Excel(name = "通提密码尝试次数", width = 15)
    @ApiModelProperty(value = "通提密码尝试次数")
	private Integer allBrPswdCtr;
	/**账户关联存折状态*/
	@Excel(name = "账户关联存折状态", width = 15)
    @ApiModelProperty(value = "账户关联存折状态")
	private String pbStatus;
	/**印鉴挂失状态*/
	@Excel(name = "印鉴挂失状态", width = 15)
    @ApiModelProperty(value = "印鉴挂失状态")
	private String chopStatus;
	/**记录现金/转账的最新的业务日期*/
	@Excel(name = "记录现金/转账的最新的业务日期", width = 15)
    @ApiModelProperty(value = "记录现金/转账的最新的业务日期")
	private Integer cshBusDate;
	/**crTierInd*/
	@Excel(name = "crTierInd", width = 15)
    @ApiModelProperty(value = "crTierInd")
	private String crTierInd;
	/**拒付支票次数*/
	@Excel(name = "拒付支票次数", width = 15)
    @ApiModelProperty(value = "拒付支票次数")
	private Integer chkDishonorCnt;
	/**取消支票次数*/
	@Excel(name = "取消支票次数", width = 15)
    @ApiModelProperty(value = "取消支票次数")
	private Integer cancellChkCnt;
	/**银行存折版本号*/
	@Excel(name = "银行存折版本号", width = 15)
    @ApiModelProperty(value = "银行存折版本号")
	private String pbVerNo;
	/**chaseFlag*/
	@Excel(name = "chaseFlag", width = 15)
    @ApiModelProperty(value = "chaseFlag")
	private String chaseFlag;
	/**台湾新光人寿保险单号码*/
	@Excel(name = "台湾新光人寿保险单号码", width = 15)
    @ApiModelProperty(value = "台湾新光人寿保险单号码")
	private String skliPoid;
	/**定期账户账户层透支百分比率*/
	@Excel(name = "定期账户账户层透支百分比率", width = 15)
    @ApiModelProperty(value = "定期账户账户层透支百分比率")
	private java.math.BigDecimal tdOdCollPct;
	/**定期账户账户层定义,透支的借记利率加减点*/
	@Excel(name = "定期账户账户层定义,透支的借记利率加减点", width = 15)
    @ApiModelProperty(value = "定期账户账户层定义,透支的借记利率加减点")
	private java.math.BigDecimal tdOdMrglRte;
	/**定期账户金额冻结原因*/
	@Excel(name = "定期账户金额冻结原因", width = 15)
    @ApiModelProperty(value = "定期账户金额冻结原因")
	private String tdHoldOption;
	/**定期账户金额冻结原因*/
	@Excel(name = "定期账户金额冻结原因", width = 15)
    @ApiModelProperty(value = "定期账户金额冻结原因")
	private String tdHoldReason;
	/**支票签约类型*/
	@Excel(name = "支票签约类型", width = 15)
    @ApiModelProperty(value = "支票签约类型")
	private String chkAgrmntType;
	/**支票签约日期*/
	@Excel(name = "支票签约日期", width = 15)
    @ApiModelProperty(value = "支票签约日期")
	private String signoffDate;
	/**即交互式语音应答访问标示*/
	@Excel(name = "即交互式语音应答访问标示", width = 15)
    @ApiModelProperty(value = "即交互式语音应答访问标示")
	private String ivrAccessGrnt;
	/**手机银行访问标示*/
	@Excel(name = "手机银行访问标示", width = 15)
    @ApiModelProperty(value = "手机银行访问标示")
	private String mbAccessGrnt;
	/**网银访问准许标示*/
	@Excel(name = "网银访问准许标示", width = 15)
    @ApiModelProperty(value = "网银访问准许标示")
	private String ibAccessGrnt;
	/**定期账户财务产品码*/
	@Excel(name = "定期账户财务产品码", width = 15)
    @ApiModelProperty(value = "定期账户财务产品码")
	private String complexTd;
	/**ATM账户突出标志*/
	@Excel(name = "ATM账户突出标志", width = 15)
    @ApiModelProperty(value = "ATM账户突出标志")
	private String embossFlag;
	/**文件上传通知标志*/
	@Excel(name = "文件上传通知标志", width = 15)
    @ApiModelProperty(value = "文件上传通知标志")
	private String clctUploadNtc;
	/**文件下载关闭标志*/
	@Excel(name = "文件下载关闭标志", width = 15)
    @ApiModelProperty(value = "文件下载关闭标志")
	private String clctDownloadCls;
	/**支票黑名单日期*/
	@Excel(name = "支票黑名单日期", width = 15)
    @ApiModelProperty(value = "支票黑名单日期")
	private Integer blacklistDate;
	/**ATM卡状态*/
	@Excel(name = "ATM卡状态", width = 15)
    @ApiModelProperty(value = "ATM卡状态")
	private String atmCardStatus;
	/**变更开户行后新的分行号*/
	@Excel(name = "变更开户行后新的分行号", width = 15)
    @ApiModelProperty(value = "变更开户行后新的分行号")
	private String newBranchNo;
	/**抵消账户指示*/
	@Excel(name = "抵消账户指示", width = 15)
    @ApiModelProperty(value = "抵消账户指示")
	private String offsetAcctInd;
	/**补登最后打印的rec-no*/
	@Excel(name = "补登最后打印的rec-no", width = 15)
    @ApiModelProperty(value = "补登最后打印的rec-no")
	private Integer lastPrintRecno;
	/**最新维护日期*/
	@Excel(name = "最新维护日期", width = 15)
    @ApiModelProperty(value = "最新维护日期")
	private Integer lastHousekeepDt;
	/**Nostro BGL账户*/
	@Excel(name = "Nostro BGL账户", width = 15)
    @ApiModelProperty(value = "Nostro BGL账户")
	private Integer nostroBglAct;
	/**存折号*/
	@Excel(name = "存折号", width = 15)
    @ApiModelProperty(value = "存折号")
	private String pbNumber;
	/**存折已打印的行数（活期使用）*/
	@Excel(name = "存折已打印的行数（活期使用）", width = 15)
    @ApiModelProperty(value = "存折已打印的行数（活期使用）")
	private String pbLineNo;
	/**abandonOffsetFl*/
	@Excel(name = "abandonOffsetFl", width = 15)
    @ApiModelProperty(value = "abandonOffsetFl")
	private String abandonOffsetFl;
	/**termLstDeductdt*/
	@Excel(name = "termLstDeductdt", width = 15)
    @ApiModelProperty(value = "termLstDeductdt")
	private Integer termLstDeductdt;
	/**这个字段记录2009交易成功的最新日期*/
	@Excel(name = "这个字段记录2009交易成功的最新日期", width = 15)
    @ApiModelProperty(value = "这个字段记录2009交易成功的最新日期")
	private Integer deductSuccDate;
	/**这个字段记录2009交易成功转账的次数*/
	@Excel(name = "这个字段记录2009交易成功转账的次数", width = 15)
    @ApiModelProperty(value = "这个字段记录2009交易成功转账的次数")
	private Integer termDeductCnt;
	/**这个字段记录2009交易失败的次数*/
	@Excel(name = "这个字段记录2009交易失败的次数", width = 15)
    @ApiModelProperty(value = "这个字段记录2009交易失败的次数")
	private Integer deductFailCnt;
	/**fundType*/
	@Excel(name = "fundType", width = 15)
    @ApiModelProperty(value = "fundType")
	private String fundType;
	/**帐户开户类型*/
	@Excel(name = "帐户开户类型", width = 15)
    @ApiModelProperty(value = "帐户开户类型")
	private String actOpenType;
	/**证明验证人员证件类型*/
	@Excel(name = "证明验证人员证件类型", width = 15)
    @ApiModelProperty(value = "证明验证人员证件类型")
	private String cvoIdType;
	/**证明验证人员证件号码*/
	@Excel(name = "证明验证人员证件号码", width = 15)
    @ApiModelProperty(value = "证明验证人员证件号码")
	private String cvoIdNo;
	/**账户发起人*/
	@Excel(name = "账户发起人", width = 15)
    @ApiModelProperty(value = "账户发起人")
	private String actPromoter;
	/**actPromoterSgmt*/
	@Excel(name = "actPromoterSgmt", width = 15)
    @ApiModelProperty(value = "actPromoterSgmt")
	private String actPromoterSgmt;
	/**账户利润代码*/
	@Excel(name = "账户利润代码", width = 15)
    @ApiModelProperty(value = "账户利润代码")
	private String actProfitCode;
	/**2009交易转账金额*/
	@Excel(name = "2009交易转账金额", width = 15)
    @ApiModelProperty(value = "2009交易转账金额")
	private java.math.BigDecimal tdInstAmt;
	/**Purchase reference*/
	@Excel(name = "Purchase reference", width = 15)
    @ApiModelProperty(value = "Purchase reference")
	private String complexPurchRef;
	/**每日提转入款总额*/
	@Excel(name = "每日提转入款总额", width = 15)
    @ApiModelProperty(value = "每日提转入款总额")
	private java.math.BigDecimal tdyTrnsInTot;
	/**每日提转出款总额*/
	@Excel(name = "每日提转出款总额", width = 15)
    @ApiModelProperty(value = "每日提转出款总额")
	private java.math.BigDecimal tdyTrnsOutTot;
	/**透支应付利息*/
	@Excel(name = "透支应付利息", width = 15)
    @ApiModelProperty(value = "透支应付利息")
	private java.math.BigDecimal odIntDue;
	/**超出透支应付利息*/
	@Excel(name = "超出透支应付利息", width = 15)
    @ApiModelProperty(value = "超出透支应付利息")
	private java.math.BigDecimal odExcessIntDue;
	/**swindleStat*/
	@Excel(name = "swindleStat", width = 15)
    @ApiModelProperty(value = "swindleStat")
	private String swindleStat;
	/**srcOfFundCode*/
	@Excel(name = "srcOfFundCode", width = 15)
    @ApiModelProperty(value = "srcOfFundCode")
	private String srcOfFundCode;
	/**欠款原因代码*/
	@Excel(name = "欠款原因代码", width = 15)
    @ApiModelProperty(value = "欠款原因代码")
	private String arrReasonCode;
	/**discReasonCode*/
	@Excel(name = "discReasonCode", width = 15)
    @ApiModelProperty(value = "discReasonCode")
	private String discReasonCode;
	/**重空类型*/
	@Excel(name = "重空类型", width = 15)
    @ApiModelProperty(value = "重空类型")
	private String voucherType;
	/**重空号码*/
	@Excel(name = "重空号码", width = 15)
    @ApiModelProperty(value = "重空号码")
	private String voucherNo;
	/**存折册号*/
	@Excel(name = "存折册号", width = 15)
    @ApiModelProperty(value = "存折册号")
	private String pbVolumeNo;
	/**存折序号*/
	@Excel(name = "存折序号", width = 15)
    @ApiModelProperty(value = "存折序号")
	private String pbSequenceNo;
	/**资金来源账户*/
	@Excel(name = "资金来源账户", width = 15)
    @ApiModelProperty(value = "资金来源账户")
	private String tdFundFromAc;
	/**外币代码*/
	@Excel(name = "外币代码", width = 15)
    @ApiModelProperty(value = "外币代码")
	private String fcyCode;
	/**结算账户标示*/
	@Excel(name = "结算账户标示", width = 15)
    @ApiModelProperty(value = "结算账户标示")
	private String acctInd;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String address;
	/**电话号码1*/
	@Excel(name = "电话号码1", width = 15)
    @ApiModelProperty(value = "电话号码1")
	@TableField(value = "tel1")
	private String tel1;
	/**电话号码2*/
	@Excel(name = "电话号码2", width = 15)
    @ApiModelProperty(value = "电话号码2")
	@TableField(value = "tel2")
	private String tel2;
	/**联系人姓名*/
	@Excel(name = "联系人姓名", width = 15)
    @ApiModelProperty(value = "联系人姓名")
	private String contactName;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
	private String postCode;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
	private String contractNum;
	/**对账协议编号*/
	@Excel(name = "对账协议编号", width = 15)
    @ApiModelProperty(value = "对账协议编号")
	private String statAgrtNum;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String institution;
	/**账户管理协议编号*/
	@Excel(name = "账户管理协议编号", width = 15)
    @ApiModelProperty(value = "账户管理协议编号")
	private String actMangtAgrtNum;
	/**收支范围*/
	@Excel(name = "收支范围", width = 15)
    @ApiModelProperty(value = "收支范围")
	private String inOutScope;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**账户用法*/
	@Excel(name = "账户用法", width = 15)
    @ApiModelProperty(value = "账户用法")
	private String acctUsage;
	/**取现有效期*/
	@Excel(name = "取现有效期", width = 15)
    @ApiModelProperty(value = "取现有效期")
	private String cshWdlEndDate;
	/**累计取现限额*/
	@Excel(name = "累计取现限额", width = 15)
    @ApiModelProperty(value = "累计取现限额")
	private java.math.BigDecimal totCshWdlLmt;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private String busiType;
	/**商业号*/
	@Excel(name = "商业号", width = 15)
    @ApiModelProperty(value = "商业号")
	private String busiNum;
	/**申报标识*/
	@Excel(name = "申报标识", width = 15)
    @ApiModelProperty(value = "申报标识")
	private String declareInd;
	/**开户核准件编号*/
	@Excel(name = "开户核准件编号", width = 15)
    @ApiModelProperty(value = "开户核准件编号")
	private String apprNum;
	/**外币账户属性*/
	@Excel(name = "外币账户属性", width = 15)
    @ApiModelProperty(value = "外币账户属性")
	private String fxAcctAttr;
	/**外汇限额编号*/
	@Excel(name = "外汇限额编号", width = 15)
    @ApiModelProperty(value = "外汇限额编号")
	private String fxAmtLmtCode;
	/**外汇额度超限标识*/
	@Excel(name = "外汇额度超限标识", width = 15)
    @ApiModelProperty(value = "外汇额度超限标识")
	private String fxExcdInd;
	/**大额存款签定日期*/
	@Excel(name = "大额存款签定日期", width = 15)
    @ApiModelProperty(value = "大额存款签定日期")
	private String amtAcctDepDate;
	/**大额存款期限*/
	@Excel(name = "大额存款期限", width = 15)
    @ApiModelProperty(value = "大额存款期限")
	private String amtAcctEndDate;
	/**贷款帐号*/
	@Excel(name = "贷款帐号", width = 15)
    @ApiModelProperty(value = "贷款帐号")
	private String loadAcct;
	/**正对单周期*/
	@Excel(name = "正对单周期", width = 15)
    @ApiModelProperty(value = "正对单周期")
	private String positivePeriod;
	/**正对单打印循环*/
	@Excel(name = "正对单打印循环", width = 15)
    @ApiModelProperty(value = "正对单打印循环")
	private String positiveCircular;
	/**正对单打印日*/
	@Excel(name = "正对单打印日", width = 15)
    @ApiModelProperty(value = "正对单打印日")
	private String positiveDay;
	/**正对单份数*/
	@Excel(name = "正对单份数", width = 15)
    @ApiModelProperty(value = "正对单份数")
	private String positiveCopyNum;
	/**是否发送正对单标示*/
	@Excel(name = "是否发送正对单标示", width = 15)
    @ApiModelProperty(value = "是否发送正对单标示")
	private String positiveMailInd;
	/**可付利息*/
	@Excel(name = "可付利息", width = 15)
    @ApiModelProperty(value = "可付利息")
	private java.math.BigDecimal intPayable;
	/**人民币结算账户属性*/
	@Excel(name = "人民币结算账户属性", width = 15)
    @ApiModelProperty(value = "人民币结算账户属性")
	private String lcySetlAcctAttr;
	/**取息频率*/
	@Excel(name = "取息频率", width = 15)
    @ApiModelProperty(value = "取息频率")
	private String whdIntFreq;
	/**实存次数*/
	@Excel(name = "实存次数", width = 15)
    @ApiModelProperty(value = "实存次数")
	private Integer realDepCnt;
	/**首次存入金额*/
	@Excel(name = "首次存入金额", width = 15)
    @ApiModelProperty(value = "首次存入金额")
	private java.math.BigDecimal firstDepAmt;
	/**是否违约标示*/
	@Excel(name = "是否违约标示", width = 15)
    @ApiModelProperty(value = "是否违约标示")
	private String breakRuleInd;
	/**结息周期内的利息金额*/
	@Excel(name = "结息周期内的利息金额", width = 15)
    @ApiModelProperty(value = "结息周期内的利息金额")
	private java.math.BigDecimal intForIntDep;
	/**账户最新财务交易日期*/
	@Excel(name = "账户最新财务交易日期", width = 15)
    @ApiModelProperty(value = "账户最新财务交易日期")
	private Integer lstNorTxnDt;
	/**止取数目*/
	@Excel(name = "止取数目", width = 15)
    @ApiModelProperty(value = "止取数目")
	private Integer noOfStopWdl;
	/**止存数目*/
	@Excel(name = "止存数目", width = 15)
    @ApiModelProperty(value = "止存数目")
	private Integer noOfStopDep;
	/**国债账户标示*/
	@Excel(name = "国债账户标示", width = 15)
    @ApiModelProperty(value = "国债账户标示")
	private String ngbAcctInd;
	/**国债预开户标示*/
	@Excel(name = "国债预开户标示", width = 15)
    @ApiModelProperty(value = "国债预开户标示")
	private String ngbPrevOpenInd;
	/**国债代码*/
	@Excel(name = "国债代码", width = 15)
    @ApiModelProperty(value = "国债代码")
	private String ngbCode;
	/**开户许可证编号*/
	@Excel(name = "开户许可证编号", width = 15)
    @ApiModelProperty(value = "开户许可证编号")
	private String acctOpenPemtNo;
	/**核准号*/
	@Excel(name = "核准号", width = 15)
    @ApiModelProperty(value = "核准号")
	private String aprlNo;
	/**人行核准日期*/
	@Excel(name = "人行核准日期", width = 15)
    @ApiModelProperty(value = "人行核准日期")
	private Integer aprlDate;
	/**账户启用日期*/
	@Excel(name = "账户启用日期", width = 15)
    @ApiModelProperty(value = "账户启用日期")
	private Integer acctEfctDate;
	/**账户层边际利率百分比*/
	@Excel(name = "账户层边际利率百分比", width = 15)
    @ApiModelProperty(value = "账户层边际利率百分比")
	private java.math.BigDecimal acctPctMgnRate;
	/**部提次数*/
	@Excel(name = "部提次数", width = 15)
    @ApiModelProperty(value = "部提次数")
	private String partialWdlCnt;
	/**付息方式*/
	@Excel(name = "付息方式", width = 15)
    @ApiModelProperty(value = "付息方式")
	private java.math.BigDecimal intPaid;
	/**协定金额*/
	@Excel(name = "协定金额", width = 15)
    @ApiModelProperty(value = "协定金额")
	private java.math.BigDecimal contractAmt;
	/**协定金额*/
	@Excel(name = "协定金额", width = 15)
    @ApiModelProperty(value = "协定金额")
	private String payMethod;
	/**对公账户截止日期*/
	@Excel(name = "对公账户截止日期", width = 15)
    @ApiModelProperty(value = "对公账户截止日期")
	private Integer endDateCorpAcct;
	/**对公账户自动冻结到期日*/
	@Excel(name = "对公账户自动冻结到期日", width = 15)
    @ApiModelProperty(value = "对公账户自动冻结到期日")
	private Integer expDateAutoFrz;
	/**违约后存款日期*/
	@Excel(name = "违约后存款日期", width = 15)
    @ApiModelProperty(value = "违约后存款日期")
	private Integer depDateAfBreak;
	/**违约后总金额*/
	@Excel(name = "违约后总金额", width = 15)
    @ApiModelProperty(value = "违约后总金额")
	private java.math.BigDecimal totAmtAfBreak;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String contractNo;
	/**部提金额*/
	@Excel(name = "部提金额", width = 15)
    @ApiModelProperty(value = "部提金额")
	private java.math.BigDecimal partialWdlAmt;
	/**如无交易,是否发送印刷版对账单*/
	@Excel(name = "如无交易,是否发送印刷版对账单", width = 15)
    @ApiModelProperty(value = "如无交易,是否发送印刷版对账单")
	private String stateNoTxn;
	/**发送电子对账单标示*/
	@Excel(name = "发送电子对账单标示", width = 15)
    @ApiModelProperty(value = "发送电子对账单标示")
	private String statMailingInd;
	/**累计余额*/
	@Excel(name = "累计余额", width = 15)
    @ApiModelProperty(value = "累计余额")
	private java.math.BigDecimal defaultBal;
	/**每日累计余额*/
	@Excel(name = "每日累计余额", width = 15)
    @ApiModelProperty(value = "每日累计余额")
	private java.math.BigDecimal dailyAccuBal;
	/**累计天数*/
	@Excel(name = "累计天数", width = 15)
    @ApiModelProperty(value = "累计天数")
	private String daysAccum;
	/**可用利息*/
	@Excel(name = "可用利息", width = 15)
    @ApiModelProperty(value = "可用利息")
	private java.math.BigDecimal contractIntAvab;
	/**利息增量*/
	@Excel(name = "利息增量", width = 15)
    @ApiModelProperty(value = "利息增量")
	private java.math.BigDecimal contractIntIncr;
	/**延期储蓄利息*/
	@Excel(name = "延期储蓄利息", width = 15)
    @ApiModelProperty(value = "延期储蓄利息")
	private java.math.BigDecimal delaySavingInt;
	/**主/子账户标示*/
	@Excel(name = "主/子账户标示", width = 15)
    @ApiModelProperty(value = "主/子账户标示")
	private String mcaInd;
	/**账户总支取金额*/
	@Excel(name = "账户总支取金额", width = 15)
    @ApiModelProperty(value = "账户总支取金额")
	private java.math.BigDecimal totCshWdl;
	/**开户交易日*/
	@Excel(name = "开户交易日", width = 15)
    @ApiModelProperty(value = "开户交易日")
	private Integer acctOpenTrnDt;
	/**关闭激活原因*/
	@Excel(name = "关闭激活原因", width = 15)
    @ApiModelProperty(value = "关闭激活原因")
	private String closeActvReason;
	/**支票密码类型*/
	@Excel(name = "支票密码类型", width = 15)
    @ApiModelProperty(value = "支票密码类型")
	private String chqPasswrdType;
	/**临时户到期日*/
	@Excel(name = "临时户到期日", width = 15)
    @ApiModelProperty(value = "临时户到期日")
	private Integer tempAcctExpDt;
	/**年审日期*/
	@Excel(name = "年审日期", width = 15)
    @ApiModelProperty(value = "年审日期")
	private Integer annlReviewDate;
	/**冻结到期日*/
	@Excel(name = "冻结到期日", width = 15)
    @ApiModelProperty(value = "冻结到期日")
	private Integer freezeExpDate;
	/**Third level accounting*/
	@Excel(name = "Third level accounting", width = 15)
    @ApiModelProperty(value = "Third level accounting")
	private String thrdLvlAccount;
	/**Vostro账户最低利率*/
	@Excel(name = "Vostro账户最低利率", width = 15)
    @ApiModelProperty(value = "Vostro账户最低利率")
	private java.math.BigDecimal cccdaVostroLow;
	/**外币项目（对公使用）*/
	@Excel(name = "外币项目（对公使用）", width = 15)
    @ApiModelProperty(value = "外币项目（对公使用）")
	private String fcyItem;
	/**外币种类*/
	@Excel(name = "外币种类", width = 15)
    @ApiModelProperty(value = "外币种类")
	private String fcyClass;
	/**外币范围*/
	@Excel(name = "外币范围", width = 15)
    @ApiModelProperty(value = "外币范围")
	private String fcyRange;
	/**取消需罚息金额部分的利息*/
	@Excel(name = "取消需罚息金额部分的利息", width = 15)
    @ApiModelProperty(value = "取消需罚息金额部分的利息")
	private java.math.BigDecimal ntcCnclIntPen;
	/**阈值*/
	@Excel(name = "阈值", width = 15)
    @ApiModelProperty(value = "阈值")
	private java.math.BigDecimal nrdaThresholdAmt;
	/**外币关户号*/
	@Excel(name = "外币关户号", width = 15)
    @ApiModelProperty(value = "外币关户号")
	private String fcyClosureNumber;
	/**零存整取标示*/
	@Excel(name = "零存整取标示", width = 15)
    @ApiModelProperty(value = "零存整取标示")
	private String noFailInstal;
	/**允许支取利息*/
	@Excel(name = "允许支取利息", width = 15)
    @ApiModelProperty(value = "允许支取利息")
	private java.math.BigDecimal intAllowWithd;
	/**主账户标示*/
	@Excel(name = "主账户标示", width = 15)
    @ApiModelProperty(value = "主账户标示")
	private String primAcctFlag;
	/**存款重空日期*/
	@Excel(name = "存款重空日期", width = 15)
    @ApiModelProperty(value = "存款重空日期")
	private Integer firstDepIbdDt;
	/**页码*/
	@Excel(name = "页码", width = 15)
    @ApiModelProperty(value = "页码")
	private String pageNo;
	/**从1999-11-1至到期日利息税*/
	@Excel(name = "从1999-11-1至到期日利息税", width = 15)
    @ApiModelProperty(value = "从1999-11-1至到期日利息税")
	@TableField(value = "int_1999_to_term")
	private java.math.BigDecimal int1999ToTerm;
	/**上次使用的存折类型*/
	@Excel(name = "上次使用的存折类型", width = 15)
    @ApiModelProperty(value = "上次使用的存折类型")
	private String lastPbType;
	/**上次使用的存折号码*/
	@Excel(name = "上次使用的存折号码", width = 15)
    @ApiModelProperty(value = "上次使用的存折号码")
	private String lastPbNumber;
	/**费用累计标示*/
	@Excel(name = "费用累计标示", width = 15)
    @ApiModelProperty(value = "费用累计标示")
	private String chrAccuInd;
	/**零售贷款 -自助贷款*/
	@Excel(name = "零售贷款 -自助贷款", width = 15)
    @ApiModelProperty(value = "零售贷款 -自助贷款")
	private String selfServLoan;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String isoAcctType;
	/**账户连接卡标示*/
	@Excel(name = "账户连接卡标示", width = 15)
    @ApiModelProperty(value = "账户连接卡标示")
	private String cardLinkFlag;
	/**账户原到期日*/
	@Excel(name = "账户原到期日", width = 15)
    @ApiModelProperty(value = "账户原到期日")
	private Integer orinMatDt;
	/**绿色账户标识*/
	@Excel(name = "绿色账户标识", width = 15)
    @ApiModelProperty(value = "绿色账户标识")
	private String greenAcctInd;
	/**绿色账户到期日*/
	@Excel(name = "绿色账户到期日", width = 15)
    @ApiModelProperty(value = "绿色账户到期日")
	private Integer greenExpiryDt;
	/**重空省代码（地区码）*/
	@Excel(name = "重空省代码（地区码）", width = 15)
    @ApiModelProperty(value = "重空省代码（地区码）")
	private String ibdProvinceCode;
	/**存折更新册号*/
	@Excel(name = "存折更新册号", width = 15)
    @ApiModelProperty(value = "存折更新册号")
	private String pbRenewVolume;
	/**取现有效期*/
	@Excel(name = "取现有效期", width = 15)
    @ApiModelProperty(value = "取现有效期")
	private Integer cashWdlExpDate;
	/**特殊转账限制标示*/
	@Excel(name = "特殊转账限制标示", width = 15)
    @ApiModelProperty(value = "特殊转账限制标示")
	private String tfrRestFlag;
	/**股金存折已打印行数*/
	@Excel(name = "股金存折已打印行数", width = 15)
    @ApiModelProperty(value = "股金存折已打印行数")
	private String pbDivLineNo;
	/**maxFreezeSeqNo*/
	@Excel(name = "maxFreezeSeqNo", width = 15)
    @ApiModelProperty(value = "maxFreezeSeqNo")
	private Integer maxFreezeSeqNo;
	/**账户最近维护日期*/
	@Excel(name = "账户最近维护日期", width = 15)
    @ApiModelProperty(value = "账户最近维护日期")
	private String lastMaintDate;
	/**账户最近维护标识*/
	@Excel(name = "账户最近维护标识", width = 15)
    @ApiModelProperty(value = "账户最近维护标识")
	private String lastMaintStat;
	/**sDate*/
	@Excel(name = "sDate", width = 15)
    @ApiModelProperty(value = "sDate")
	private String sDate;
	/**eDate*/
	@Excel(name = "eDate", width = 15)
    @ApiModelProperty(value = "eDate")
	private String eDate;
	/**dataDate*/
	@Excel(name = "dataDate", width = 15)
    @ApiModelProperty(value = "dataDate")
	private String dataDate;
	/**loadDate*/
    @ApiModelProperty(value = "loadDate")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
