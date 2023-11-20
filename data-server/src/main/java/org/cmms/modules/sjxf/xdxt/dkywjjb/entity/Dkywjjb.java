package org.cmms.modules.sjxf.xdxt.dkywjjb.entity;

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
 * @Description: 贷款业务借据表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cms_business_due")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_business_due对象", description="贷款业务借据表")
public class Dkywjjb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**核心借据号*/
	@Excel(name = "核心借据号", width = 15)
    @ApiModelProperty(value = "核心借据号")
	private String hxjjh;
	/**联机交易号*/
	@Excel(name = "联机交易号", width = 15)
    @ApiModelProperty(value = "联机交易号")
	private String sid;
	/**逾期标志*/
	@Excel(name = "逾期标志", width = 15)
    @ApiModelProperty(value = "逾期标志")
	private String yqbz;
	/**记账日期*/
	@Excel(name = "记账日期", width = 15)
    @ApiModelProperty(value = "记账日期")
	private String jzDate;
	/**五级分类可疑金额*/
	@Excel(name = "五级分类可疑金额", width = 15)
    @ApiModelProperty(value = "五级分类可疑金额")
	private java.math.BigDecimal suspiciousSum;
	/**操作员ID*/
	@Excel(name = "操作员ID", width = 15)
    @ApiModelProperty(value = "操作员ID")
	private String userId;
	/**借据号*/
	@Excel(name = "借据号", width = 15)
    @ApiModelProperty(value = "借据号")
	private String voucherNo;
	/**放贷流程流水号*/
	@Excel(name = "放贷流程流水号", width = 15)
    @ApiModelProperty(value = "放贷流程流水号")
	private String voucherFlow;
	/**放贷状态*/
	@Excel(name = "放贷状态", width = 15)
    @ApiModelProperty(value = "放贷状态")
	private String voucherStatus;
	/**展期标志*/
	@Excel(name = "展期标志", width = 15)
    @ApiModelProperty(value = "展期标志")
	private String extendFlag;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String endDate;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
    @ApiModelProperty(value = "贷款期限")
	private String appTerm;
	/**贷款种类*/
	@Excel(name = "贷款种类", width = 15)
    @ApiModelProperty(value = "贷款种类")
	private String loanKind;
	/**存款帐号*/
	@Excel(name = "存款帐号", width = 15)
    @ApiModelProperty(value = "存款帐号")
	private String depositNo;
	/**贷款科目一*/
	@Excel(name = "贷款科目一", width = 15)
    @ApiModelProperty(value = "贷款科目一")
	private String businessSubject1;
	/**贷款科目二*/
	@Excel(name = "贷款科目二", width = 15)
    @ApiModelProperty(value = "贷款科目二")
	private String businessSubject2;
	/**贷款科目三*/
	@Excel(name = "贷款科目三", width = 15)
    @ApiModelProperty(value = "贷款科目三")
	private String businessSubject3;
	/**贷款科目四*/
	@Excel(name = "贷款科目四", width = 15)
    @ApiModelProperty(value = "贷款科目四")
	private String businessSubject4;
	/**贷款科目五*/
	@Excel(name = "贷款科目五", width = 15)
    @ApiModelProperty(value = "贷款科目五")
	private String businessSubject5;
	/**欠息日期*/
	@Excel(name = "欠息日期", width = 15)
    @ApiModelProperty(value = "欠息日期")
	private String calcDate;
	/**最小欠息日*/
	@Excel(name = "最小欠息日", width = 15)
    @ApiModelProperty(value = "最小欠息日")
	private String minCalcDate;
	/**实际用途*/
	@Excel(name = "实际用途", width = 15)
    @ApiModelProperty(value = "实际用途")
	private String actualPurpose;
	/**统计用途*/
	@Excel(name = "统计用途", width = 15)
    @ApiModelProperty(value = "统计用途")
	private String statiPurpose;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**是否投保*/
	@Excel(name = "是否投保", width = 15)
    @ApiModelProperty(value = "是否投保")
	private String isInsure;
	/**发生类型*/
	@Excel(name = "发生类型", width = 15)
    @ApiModelProperty(value = "发生类型")
	private String occurType;
	/**征信业务种类*/
	@Excel(name = "征信业务种类", width = 15)
    @ApiModelProperty(value = "征信业务种类")
	private String businessKind;
	/**起息日期*/
	@Excel(name = "起息日期", width = 15)
    @ApiModelProperty(value = "起息日期")
	private String qxDate;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
	/**批准利率*/
	@Excel(name = "批准利率", width = 15)
    @ApiModelProperty(value = "批准利率")
	private java.math.BigDecimal appRate;
	/**批准金额*/
	@Excel(name = "批准金额", width = 15)
    @ApiModelProperty(value = "批准金额")
	private java.math.BigDecimal appSum;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal balance;
	/**关联序号*/
	@Excel(name = "关联序号", width = 15)
    @ApiModelProperty(value = "关联序号")
	private String businessDueId;
	/**业务编号，合同号*/
	@Excel(name = "业务编号，合同号", width = 15)
    @ApiModelProperty(value = "业务编号，合同号")
	private String businessNo;
	/**交易编号*/
	@Excel(name = "交易编号", width = 15)
    @ApiModelProperty(value = "交易编号")
	private String businessPhase;
	/**贷款类型*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private String businessType;
	/**五级分类关注金额*/
	@Excel(name = "五级分类关注金额", width = 15)
    @ApiModelProperty(value = "五级分类关注金额")
	private java.math.BigDecimal concernSum;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**五级分类类型*/
	@Excel(name = "五级分类类型", width = 15)
    @ApiModelProperty(value = "五级分类类型")
	private String fiveClassType;
	/**放款人编号*/
	@Excel(name = "放款人编号", width = 15)
    @ApiModelProperty(value = "放款人编号")
	private String fkId;
	/**四级状态*/
	@Excel(name = "四级状态", width = 15)
    @ApiModelProperty(value = "四级状态")
	private String fourClassState;
	/**利息损失*/
	@Excel(name = "利息损失", width = 15)
    @ApiModelProperty(value = "利息损失")
	private java.math.BigDecimal interestlosingSum;
	/**征信提交次数*/
	@Excel(name = "征信提交次数", width = 15)
    @ApiModelProperty(value = "征信提交次数")
	private String isSubmit;
	/**五级分类损失金额*/
	@Excel(name = "五级分类损失金额", width = 15)
    @ApiModelProperty(value = "五级分类损失金额")
	private java.math.BigDecimal loseSum;
	/**五级分类正常金额*/
	@Excel(name = "五级分类正常金额", width = 15)
    @ApiModelProperty(value = "五级分类正常金额")
	private java.math.BigDecimal normalSum;
	/**部门号交易编号*/
	@Excel(name = "部门号交易编号", width = 15)
    @ApiModelProperty(value = "部门号交易编号")
	private String orgId;
	/**五级分类次级金额*/
	@Excel(name = "五级分类次级金额", width = 15)
    @ApiModelProperty(value = "五级分类次级金额")
	private java.math.BigDecimal secondarySum;
	/**放款起始日期*/
	@Excel(name = "放款起始日期", width = 15)
    @ApiModelProperty(value = "放款起始日期")
	private String startDate;
	/**24个月还款状态*/
	@Excel(name = "24个月还款状态", width = 15)
    @ApiModelProperty(value = "24个月还款状态")
	@TableField(value = "STATE_OF_24")
	private String stateOf24;
	/**借款人证件类型*/
	@Excel(name = "借款人证件类型", width = 15)
    @ApiModelProperty(value = "借款人证件类型")
	private String borrowerZjlx;
	/**借款人证件号码*/
	@Excel(name = "借款人证件号码", width = 15)
    @ApiModelProperty(value = "借款人证件号码")
	private String borrowerZjhm;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
	private String contractId;
	/**利率类型*/
	@Excel(name = "利率类型", width = 15)
    @ApiModelProperty(value = "利率类型")
	private String intRateType;
	/**基准利率*/
	@Excel(name = "基准利率", width = 15)
    @ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal benchmarkRate;
	/**浮动利率*/
	@Excel(name = "浮动利率", width = 15)
    @ApiModelProperty(value = "浮动利率")
	private java.math.BigDecimal floatingInt;
	/**逾期利率*/
	@Excel(name = "逾期利率", width = 15)
    @ApiModelProperty(value = "逾期利率")
	private java.math.BigDecimal overdueInt;
	/**上次结息余额*/
	@Excel(name = "上次结息余额", width = 15)
    @ApiModelProperty(value = "上次结息余额")
	private java.math.BigDecimal preIntsetlBal;
	/**实收本金*/
	@Excel(name = "实收本金", width = 15)
    @ApiModelProperty(value = "实收本金")
	private java.math.BigDecimal totalOverAmt;
	/**实收利息*/
	@Excel(name = "实收利息", width = 15)
    @ApiModelProperty(value = "实收利息")
	private java.math.BigDecimal totalOverIntr;
	/**实收罚息*/
	@Excel(name = "实收罚息", width = 15)
    @ApiModelProperty(value = "实收罚息")
	private java.math.BigDecimal totalOverPnintr;
	/**实收复利*/
	@Excel(name = "实收复利", width = 15)
    @ApiModelProperty(value = "实收复利")
	private java.math.BigDecimal totalOverReintr;
	/**应收本金*/
	@Excel(name = "应收本金", width = 15)
    @ApiModelProperty(value = "应收本金")
	private java.math.BigDecimal receAmt;
	/**应收利息*/
	@Excel(name = "应收利息", width = 15)
    @ApiModelProperty(value = "应收利息")
	private java.math.BigDecimal receIntr;
	/**应收罚息*/
	@Excel(name = "应收罚息", width = 15)
    @ApiModelProperty(value = "应收罚息")
	private java.math.BigDecimal recePnintr;
	/**应收复利*/
	@Excel(name = "应收复利", width = 15)
    @ApiModelProperty(value = "应收复利")
	private java.math.BigDecimal receReintr;
	/**核销本金*/
	@Excel(name = "核销本金", width = 15)
    @ApiModelProperty(value = "核销本金")
	private java.math.BigDecimal disAmt;
	/**核销利息*/
	@Excel(name = "核销利息", width = 15)
    @ApiModelProperty(value = "核销利息")
	private java.math.BigDecimal disIntr;
	/**核销罚息*/
	@Excel(name = "核销罚息", width = 15)
    @ApiModelProperty(value = "核销罚息")
	private java.math.BigDecimal disPnintr;
	/**核销复利*/
	@Excel(name = "核销复利", width = 15)
    @ApiModelProperty(value = "核销复利")
	private java.math.BigDecimal disReintr;
	/**分类时间*/
	@Excel(name = "分类时间", width = 15)
    @ApiModelProperty(value = "分类时间")
	private String fiveClassDate;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String appCurrency;
	/**展期日期*/
	@Excel(name = "展期日期", width = 15)
    @ApiModelProperty(value = "展期日期")
	private String extendDate;
	/**分类调整日期*/
	@Excel(name = "分类调整日期", width = 15)
    @ApiModelProperty(value = "分类调整日期")
	private String fiveAdjustDate;
	/**测算五级分类*/
	@Excel(name = "测算五级分类", width = 15)
    @ApiModelProperty(value = "测算五级分类")
	private String fiveTypeCalc;
	/**正常转关注*/
	@Excel(name = "正常转关注", width = 15)
    @ApiModelProperty(value = "正常转关注")
	private java.math.BigDecimal normToConc;
	/**正常转次级*/
	@Excel(name = "正常转次级", width = 15)
    @ApiModelProperty(value = "正常转次级")
	private java.math.BigDecimal normToSeco;
	/**正常转可疑*/
	@Excel(name = "正常转可疑", width = 15)
    @ApiModelProperty(value = "正常转可疑")
	private java.math.BigDecimal normToSusp;
	/**正常转损失*/
	@Excel(name = "正常转损失", width = 15)
    @ApiModelProperty(value = "正常转损失")
	private java.math.BigDecimal normToLose;
	/**关注转次级*/
	@Excel(name = "关注转次级", width = 15)
    @ApiModelProperty(value = "关注转次级")
	private java.math.BigDecimal concToSeco;
	/**关注转可疑*/
	@Excel(name = "关注转可疑", width = 15)
    @ApiModelProperty(value = "关注转可疑")
	private java.math.BigDecimal concToSusp;
	/**关注转损失*/
	@Excel(name = "关注转损失", width = 15)
    @ApiModelProperty(value = "关注转损失")
	private java.math.BigDecimal concToLose;
	/**次级转可疑*/
	@Excel(name = "次级转可疑", width = 15)
    @ApiModelProperty(value = "次级转可疑")
	private java.math.BigDecimal secoToSusp;
	/**次级转损失*/
	@Excel(name = "次级转损失", width = 15)
    @ApiModelProperty(value = "次级转损失")
	private java.math.BigDecimal secoToLose;
	/**可疑转损失*/
	@Excel(name = "可疑转损失", width = 15)
    @ApiModelProperty(value = "可疑转损失")
	private java.math.BigDecimal suspToLose;
	/**关注转正常*/
	@Excel(name = "关注转正常", width = 15)
    @ApiModelProperty(value = "关注转正常")
	private java.math.BigDecimal concToNorm;
	/**次级转关注*/
	@Excel(name = "次级转关注", width = 15)
    @ApiModelProperty(value = "次级转关注")
	private java.math.BigDecimal secoToConc;
	/**次级转正常*/
	@Excel(name = "次级转正常", width = 15)
    @ApiModelProperty(value = "次级转正常")
	private java.math.BigDecimal secoToNorm;
	/**可疑转次级*/
	@Excel(name = "可疑转次级", width = 15)
    @ApiModelProperty(value = "可疑转次级")
	private java.math.BigDecimal suspToSeco;
	/**可疑转关注*/
	@Excel(name = "可疑转关注", width = 15)
    @ApiModelProperty(value = "可疑转关注")
	private java.math.BigDecimal suspToConc;
	/**可疑转正常*/
	@Excel(name = "可疑转正常", width = 15)
    @ApiModelProperty(value = "可疑转正常")
	private java.math.BigDecimal suspToNorm;
	/**损失转可疑*/
	@Excel(name = "损失转可疑", width = 15)
    @ApiModelProperty(value = "损失转可疑")
	private java.math.BigDecimal loseToSusp;
	/**损失转次级*/
	@Excel(name = "损失转次级", width = 15)
    @ApiModelProperty(value = "损失转次级")
	private java.math.BigDecimal loseToSeco;
	/**损失转关注*/
	@Excel(name = "损失转关注", width = 15)
    @ApiModelProperty(value = "损失转关注")
	private java.math.BigDecimal loseToConc;
	/**损失转正常*/
	@Excel(name = "损失转正常", width = 15)
    @ApiModelProperty(value = "损失转正常")
	private java.math.BigDecimal loseToNorm;
	/**分类调整类型*/
	@Excel(name = "分类调整类型", width = 15)
    @ApiModelProperty(value = "分类调整类型")
	private String fiveAdjustType;
	/**五级分类(手工)*/
	@Excel(name = "五级分类(手工)", width = 15)
    @ApiModelProperty(value = "五级分类(手工)")
	private String fiveTypeManual;
	/**支付方式*/
	@Excel(name = "支付方式", width = 15)
    @ApiModelProperty(value = "支付方式")
	private String payMode;
	/**委托支付账号*/
	@Excel(name = "委托支付账号", width = 15)
    @ApiModelProperty(value = "委托支付账号")
	private String precPayAccount;
	/**总余额*/
	@Excel(name = "总余额", width = 15)
    @ApiModelProperty(value = "总余额")
	private java.math.BigDecimal totleBalance;
	/**减值日期*/
	@Excel(name = "减值日期", width = 15)
    @ApiModelProperty(value = "减值日期")
	private String devalueDate;
	/**减值金额*/
	@Excel(name = "减值金额", width = 15)
    @ApiModelProperty(value = "减值金额")
	private java.math.BigDecimal devalueSum;
	/**减值类型*/
	@Excel(name = "减值类型", width = 15)
    @ApiModelProperty(value = "减值类型")
	private String devalueType;
	/**减值模式*/
	@Excel(name = "减值模式", width = 15)
    @ApiModelProperty(value = "减值模式")
	private String devalueMode;
	/**是否减值*/
	@Excel(name = "是否减值", width = 15)
    @ApiModelProperty(value = "是否减值")
	private String isDevalue;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String purposeType;
	/**贷款投向1*/
	@Excel(name = "贷款投向1", width = 15)
    @ApiModelProperty(value = "贷款投向1")
	private String purposeType1;
	/**贷款投向2*/
	@Excel(name = "贷款投向2", width = 15)
    @ApiModelProperty(value = "贷款投向2")
	private String purposeType2;
	/**贷款投向3*/
	@Excel(name = "贷款投向3", width = 15)
    @ApiModelProperty(value = "贷款投向3")
	private String purposeType3;
	/**贷款投向4*/
	@Excel(name = "贷款投向4", width = 15)
    @ApiModelProperty(value = "贷款投向4")
	private String purposeType4;
	/**贷款投向5*/
	@Excel(name = "贷款投向5", width = 15)
    @ApiModelProperty(value = "贷款投向5")
	private String purposeType5;
	/**贷款投向6*/
	@Excel(name = "贷款投向6", width = 15)
    @ApiModelProperty(value = "贷款投向6")
	private String purposeType6;
	/**贷款投向7*/
	@Excel(name = "贷款投向7", width = 15)
    @ApiModelProperty(value = "贷款投向7")
	private String purposeType7;
	/**贷款投向8*/
	@Excel(name = "贷款投向8", width = 15)
    @ApiModelProperty(value = "贷款投向8")
	private String purposeType8;
	/**是否政府投融资平台*/
	@Excel(name = "是否政府投融资平台", width = 15)
    @ApiModelProperty(value = "是否政府投融资平台")
	private String isGovInvest;
	/**政府投融资明细*/
	@Excel(name = "政府投融资明细", width = 15)
    @ApiModelProperty(value = "政府投融资明细")
	private String govInvestDetail;
	/**是否战略性新兴产业*/
	@Excel(name = "是否战略性新兴产业", width = 15)
    @ApiModelProperty(value = "是否战略性新兴产业")
	private String isTacticRising;
	/**战略性新兴产业明细*/
	@Excel(name = "战略性新兴产业明细", width = 15)
    @ApiModelProperty(value = "战略性新兴产业明细")
	private String tacticRisingDetail;
	/**固定资产类型*/
	@Excel(name = "固定资产类型", width = 15)
    @ApiModelProperty(value = "固定资产类型")
	private String fixedType;
	/**固定资产类型1*/
	@Excel(name = "固定资产类型1", width = 15)
    @ApiModelProperty(value = "固定资产类型1")
	private String fixedType1;
	/**固定资产类型2*/
	@Excel(name = "固定资产类型2", width = 15)
    @ApiModelProperty(value = "固定资产类型2")
	private String fixedType2;
	/**固定资产类型3*/
	@Excel(name = "固定资产类型3", width = 15)
    @ApiModelProperty(value = "固定资产类型3")
	private String fixedType3;
	/**固定资产类型4*/
	@Excel(name = "固定资产类型4", width = 15)
    @ApiModelProperty(value = "固定资产类型4")
	private String fixedType4;
	/**催收登记日期*/
	@Excel(name = "催收登记日期", width = 15)
    @ApiModelProperty(value = "催收登记日期")
	private String urgedSignDate;
	/**五级分类级别(十级)*/
	@Excel(name = "五级分类级别(十级)", width = 15)
    @ApiModelProperty(value = "五级分类级别(十级)")
	private String fiveClassLevel;
	/**代理机构号*/
	@Excel(name = "代理机构号", width = 15)
    @ApiModelProperty(value = "代理机构号")
	private String agentOrgId;
	/**福祥便民卡号*/
	@Excel(name = "福祥便民卡号", width = 15)
    @ApiModelProperty(value = "福祥便民卡号")
	private String cardNo;
	/**支付类型*/
	@Excel(name = "支付类型", width = 15)
    @ApiModelProperty(value = "支付类型")
	private String precPayType;
	/**委托支付人名称*/
	@Excel(name = "委托支付人名称", width = 15)
    @ApiModelProperty(value = "委托支付人名称")
	private String precPayName;
	/**委托支付行号*/
	@Excel(name = "委托支付行号", width = 15)
    @ApiModelProperty(value = "委托支付行号")
	private String precPayBankNo;
	/**委托支付行名*/
	@Excel(name = "委托支付行名", width = 15)
    @ApiModelProperty(value = "委托支付行名")
	private String precPayBankCn;
	/**产品大类*/
	@Excel(name = "产品大类", width = 15)
    @ApiModelProperty(value = "产品大类")
	private String productType;
	/**产品子类*/
	@Excel(name = "产品子类", width = 15)
    @ApiModelProperty(value = "产品子类")
	private String productSubType;
	/**本金逾期日期*/
	@Excel(name = "本金逾期日期", width = 15)
    @ApiModelProperty(value = "本金逾期日期")
	private String overdueDate;
	/**逾期次数*/
	@Excel(name = "逾期次数", width = 15)
    @ApiModelProperty(value = "逾期次数")
	private String dueCount;
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
