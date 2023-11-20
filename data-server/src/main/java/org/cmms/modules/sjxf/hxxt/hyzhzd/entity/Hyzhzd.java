package org.cmms.modules.sjxf.hxxt.hyzhzd.entity;

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
 * @Description: 或有账户主档
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbs_ctam")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_ctam对象", description="或有账户主档")
public class Hyzhzd {
    
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String instNo;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String accNo;
	/**CTAVST*/
	@Excel(name = "CTAVST", width = 15)
    @ApiModelProperty(value = "CTAVST")
	private String ctavst;
	/**NO_TX*/
	@Excel(name = "NO_TX", width = 15)
    @ApiModelProperty(value = "NO_TX")
	private String noTx;
	/**NO_PER_TX*/
	@Excel(name = "NO_PER_TX", width = 15)
    @ApiModelProperty(value = "NO_PER_TX")
	private String noPerTx;
	/**记录号*/
	@Excel(name = "记录号", width = 15)
    @ApiModelProperty(value = "记录号")
	private String recNo;
	/**NO_NON_FIN*/
	@Excel(name = "NO_NON_FIN", width = 15)
    @ApiModelProperty(value = "NO_NON_FIN")
	private String noNonFin;
	/**系统模块*/
	@Excel(name = "系统模块", width = 15)
    @ApiModelProperty(value = "系统模块")
	private String system;
	/**客户所在的机构*/
	@Excel(name = "客户所在的机构", width = 15)
    @ApiModelProperty(value = "客户所在的机构")
	private String branch;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String status;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String acctType;
	/**产品细类*/
	@Excel(name = "产品细类", width = 15)
    @ApiModelProperty(value = "产品细类")
	private String intCat;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currency;
	/**最近的维护交易日*/
	@Excel(name = "最近的维护交易日", width = 15)
    @ApiModelProperty(value = "最近的维护交易日")
	private Integer lastMaintDt;
	/**最近的金融交易日*/
	@Excel(name = "最近的金融交易日", width = 15)
    @ApiModelProperty(value = "最近的金融交易日")
	private Integer lastFinDt;
	/**GL分类代码*/
	@Excel(name = "GL分类代码", width = 15)
    @ApiModelProperty(value = "GL分类代码")
	private String glClassCode;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String customerNo;
	/**当前账户金额*/
	@Excel(name = "当前账户金额", width = 15)
    @ApiModelProperty(value = "当前账户金额")
	private java.math.BigDecimal currentBalance;
	/**访问码*/
	@Excel(name = "访问码", width = 15)
    @ApiModelProperty(value = "访问码")
	private Integer accessCode;
	/**进入机构号*/
	@Excel(name = "进入机构号", width = 15)
    @ApiModelProperty(value = "进入机构号")
	private Integer accessBranchNo;
	/**进入柜员号*/
	@Excel(name = "进入柜员号", width = 15)
    @ApiModelProperty(value = "进入柜员号")
	private Integer accessTellerNo;
	/**票据类型*/
	@Excel(name = "票据类型", width = 15)
    @ApiModelProperty(value = "票据类型")
	private String instrumentType;
	/**FEE_BEARER*/
	@Excel(name = "FEE_BEARER", width = 15)
    @ApiModelProperty(value = "FEE_BEARER")
	private String feeBearer;
	/**申请日期*/
	@Excel(name = "申请日期", width = 15)
    @ApiModelProperty(value = "申请日期")
	private Integer applicationDate;
	/**申请金额*/
	@Excel(name = "申请金额", width = 15)
    @ApiModelProperty(value = "申请金额")
	private java.math.BigDecimal applicationAmt;
	/**原国家*/
	@Excel(name = "原国家", width = 15)
    @ApiModelProperty(value = "原国家")
	private String originCountry;
	/**目标国家*/
	@Excel(name = "目标国家", width = 15)
    @ApiModelProperty(value = "目标国家")
	private String destCountry;
	/**可信用银行*/
	@Excel(name = "可信用银行", width = 15)
    @ApiModelProperty(value = "可信用银行")
	private String creditAvailWith;
	/**是否有信用*/
	@Excel(name = "是否有信用", width = 15)
    @ApiModelProperty(value = "是否有信用")
	private String creditAvailBy;
	/**适用地区*/
	@Excel(name = "适用地区", width = 15)
    @ApiModelProperty(value = "适用地区")
	private String placeOfExpiry;
	/**到期日*/
	@Excel(name = "到期日", width = 15)
    @ApiModelProperty(value = "到期日")
	private Integer dateOfExpiry;
	/**支付免除文件*/
	@Excel(name = "支付免除文件", width = 15)
    @ApiModelProperty(value = "支付免除文件")
	private String chargeExProfile;
	/**核准日*/
	@Excel(name = "核准日", width = 15)
    @ApiModelProperty(value = "核准日")
	private Integer approvalDate;
	/**核准金额*/
	@Excel(name = "核准金额", width = 15)
    @ApiModelProperty(value = "核准金额")
	private java.math.BigDecimal approvalAmount;
	/**最新变更账户类型日期*/
	@Excel(name = "最新变更账户类型日期", width = 15)
    @ApiModelProperty(value = "最新变更账户类型日期")
	private Integer lastAcctTypeChg;
	/**释放交易金额*/
	@Excel(name = "释放交易金额", width = 15)
    @ApiModelProperty(value = "释放交易金额")
	private java.math.BigDecimal releaseAmount;
	/**担保费率*/
	@Excel(name = "担保费率", width = 15)
    @ApiModelProperty(value = "担保费率")
	private java.math.BigDecimal feeRate;
	/**保证金比例*/
	@Excel(name = "保证金比例", width = 15)
    @ApiModelProperty(value = "保证金比例")
	private java.math.BigDecimal marginRate;
	/**商业交易ID*/
	@Excel(name = "商业交易ID", width = 15)
    @ApiModelProperty(value = "商业交易ID")
	private String busSalesId;
	/**交易部门监督人*/
	@Excel(name = "交易部门监督人", width = 15)
    @ApiModelProperty(value = "交易部门监督人")
	private String busDepSpvsr;
	/**存档文件序列号*/
	@Excel(name = "存档文件序列号", width = 15)
    @ApiModelProperty(value = "存档文件序列号")
	private String fldDocSrlNo;
	/**取款日期*/
	@Excel(name = "取款日期", width = 15)
    @ApiModelProperty(value = "取款日期")
	private Integer drwdownDate;
	/**第一次下载长度*/
	@Excel(name = "第一次下载长度", width = 15)
    @ApiModelProperty(value = "第一次下载长度")
	private String frstDrwdownLen;
	/**利息偿还通知天数*/
	@Excel(name = "利息偿还通知天数", width = 15)
    @ApiModelProperty(value = "利息偿还通知天数")
	private String intRpyNtcDays;
	/**还款收据*/
	@Excel(name = "还款收据", width = 15)
    @ApiModelProperty(value = "还款收据")
	private String rpayReceipt;
	/**存储状态*/
	@Excel(name = "存储状态", width = 15)
    @ApiModelProperty(value = "存储状态")
	private String storeStatus;
	/**利润费用*/
	@Excel(name = "利润费用", width = 15)
    @ApiModelProperty(value = "利润费用")
	private java.math.BigDecimal marginFee;
	/**账单接收号码*/
	@Excel(name = "账单接收号码", width = 15)
    @ApiModelProperty(value = "账单接收号码")
	private String baNo;
	/**L/C即付标识*/
	@Excel(name = "L/C即付标识", width = 15)
    @ApiModelProperty(value = "L/C即付标识")
	private String lcSightInd;
	/**PRE_RECEIVABLE*/
	@Excel(name = "PRE_RECEIVABLE", width = 15)
    @ApiModelProperty(value = "PRE_RECEIVABLE")
	private java.math.BigDecimal preReceivable;
	/**CTA的期数*/
	@Excel(name = "CTA的期数", width = 15)
    @ApiModelProperty(value = "CTA的期数")
	private Integer term;
	/**期数单位*/
	@Excel(name = "期数单位", width = 15)
    @ApiModelProperty(value = "期数单位")
	private String termBasis;
	/**IBP号码*/
	@Excel(name = "IBP号码", width = 15)
    @ApiModelProperty(value = "IBP号码")
	private String ibpNo;
	/**收款人姓名*/
	@Excel(name = "收款人姓名", width = 15)
    @ApiModelProperty(value = "收款人姓名")
	private String benefName;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String contractNo;
	/**合同类型*/
	@Excel(name = "合同类型", width = 15)
    @ApiModelProperty(value = "合同类型")
	private String contractType;
	/**出票人帐号*/
	@Excel(name = "出票人帐号", width = 15)
    @ApiModelProperty(value = "出票人帐号")
	private String escrowAcct;
	/**核准等级*/
	@Excel(name = "核准等级", width = 15)
    @ApiModelProperty(value = "核准等级")
	private String apprvalLevel;
	/**保证金帐号*/
	@Excel(name = "保证金帐号", width = 15)
    @ApiModelProperty(value = "保证金帐号")
	private String guarrantyNo;
	/**假日处理*/
	@Excel(name = "假日处理", width = 15)
    @ApiModelProperty(value = "假日处理")
	private String holProcess;
	/**到期自动扣款方式*/
	@Excel(name = "到期自动扣款方式", width = 15)
    @ApiModelProperty(value = "到期自动扣款方式")
	private String deductMethod;
	/**收款人姓名*/
	@Excel(name = "收款人姓名", width = 15)
    @ApiModelProperty(value = "收款人姓名")
	private String drawerName;
	/**逾期利率*/
	@Excel(name = "逾期利率", width = 15)
    @ApiModelProperty(value = "逾期利率")
	private java.math.BigDecimal overdueIntRate;
	/**收款行地址*/
	@Excel(name = "收款行地址", width = 15)
    @ApiModelProperty(value = "收款行地址")
	private String payeeBankNo;
	/**收款人帐号*/
	@Excel(name = "收款人帐号", width = 15)
    @ApiModelProperty(value = "收款人帐号")
	private String payeeAcctNo;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String relManager;
	/**HOLD_JRN_NO*/
	@Excel(name = "HOLD_JRN_NO", width = 15)
    @ApiModelProperty(value = "HOLD_JRN_NO")
	private String holdJrnNo;
	/**票据号码*/
	@Excel(name = "票据号码", width = 15)
    @ApiModelProperty(value = "票据号码")
	private String draftNumber;
	/**BGD所在的机构*/
	@Excel(name = "BGD所在的机构", width = 15)
    @ApiModelProperty(value = "BGD所在的机构")
	private String bgdBranch;
	/**收款行的名称*/
	@Excel(name = "收款行的名称", width = 15)
    @ApiModelProperty(value = "收款行的名称")
	private String payeeBankName;
	/**收款行地址1*/
	@Excel(name = "收款行地址1", width = 15)
    @ApiModelProperty(value = "收款行地址1")
	private String payeeBankAdd1;
	/**收款行地址2*/
	@Excel(name = "收款行地址2", width = 15)
    @ApiModelProperty(value = "收款行地址2")
	private String payeeBankAdd2;
	/**收款行地址3*/
	@Excel(name = "收款行地址3", width = 15)
    @ApiModelProperty(value = "收款行地址3")
	private String payeeBankAdd3;
	/**一本通子账户类型*/
	@Excel(name = "一本通子账户类型", width = 15)
    @ApiModelProperty(value = "一本通子账户类型")
	private String escrowMcaSubTyp;
	/**填充*/
	@Excel(name = "填充", width = 15)
    @ApiModelProperty(value = "填充")
	private String fil01;
	/**最后的维护日期*/
	@Excel(name = "最后的维护日期", width = 15)
    @ApiModelProperty(value = "最后的维护日期")
	private String lastMaintDate;
	/**最后的维护状态*/
	@Excel(name = "最后的维护状态", width = 15)
    @ApiModelProperty(value = "最后的维护状态")
	private String lastMaintStat;
	/**最后维护日期*/
	@Excel(name = "最后维护日期", width = 15)
    @ApiModelProperty(value = "最后维护日期")
	private String lstFinUpdDt;
	/**最后维护类型*/
	@Excel(name = "最后维护类型", width = 15)
    @ApiModelProperty(value = "最后维护类型")
	private String lstFinStat;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
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
