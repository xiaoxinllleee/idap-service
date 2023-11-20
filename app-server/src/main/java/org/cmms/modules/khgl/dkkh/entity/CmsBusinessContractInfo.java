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
 * @Description: 贷款借据表
 * @Author: jeecg-boot
 * @Date:   2022-04-11
 * @Version: V1.0
 */
@Data
@TableName("cms_business_contract_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cms_business_contract_info对象", description="贷款借据表")
public class CmsBusinessContractInfo {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**批准期限日*/
	@Excel(name = "批准期限日", width = 15)
    @ApiModelProperty(value = "批准期限日")
	private String appTermDay;
	/**基准利率,基准利率类型*/
	@Excel(name = "基准利率,基准利率类型", width = 15)
    @ApiModelProperty(value = "基准利率,基准利率类型")
	private String appRateType;
	/**浮动方式*/
	@Excel(name = "浮动方式", width = 15)
    @ApiModelProperty(value = "浮动方式")
	private String appFloatType;
	/**浮动比率,利息收入*/
	@Excel(name = "浮动比率,利息收入", width = 15)
    @ApiModelProperty(value = "浮动比率,利息收入")
	private java.math.BigDecimal appRateFloat;
	/**基准利率,利率,批准利率,签约利率,原借款利率*/
	@Excel(name = "基准利率,利率,批准利率,签约利率,原借款利率", width = 15)
    @ApiModelProperty(value = "基准利率,利率,批准利率,签约利率,原借款利率")
	private java.math.BigDecimal appRate;
	/**比例,票面金额*/
	@Excel(name = "比例,票面金额", width = 15)
    @ApiModelProperty(value = "比例,票面金额")
	private java.math.BigDecimal appPdgRatio;
	/**金额,利息收入*/
	@Excel(name = "金额,利息收入", width = 15)
    @ApiModelProperty(value = "金额,利息收入")
	private java.math.BigDecimal appPdgSum;
	/**支付方式*/
	@Excel(name = "支付方式", width = 15)
    @ApiModelProperty(value = "支付方式")
	private String appPdgPayMethod;
	/**比例,实付金额*/
	@Excel(name = "比例,实付金额", width = 15)
    @ApiModelProperty(value = "比例,实付金额")
	private java.math.BigDecimal appMfeeRatio;
	/**票面金额*/
	@Excel(name = "票面金额", width = 15)
    @ApiModelProperty(value = "票面金额")
	private java.math.BigDecimal appMfeeSum;
	/**支付方式*/
	@Excel(name = "支付方式", width = 15)
    @ApiModelProperty(value = "支付方式")
	private String appMfeePayMethod;
	/**保证金比例,利息收入*/
	@Excel(name = "保证金比例,利息收入", width = 15)
    @ApiModelProperty(value = "保证金比例,利息收入")
	private java.math.BigDecimal appBailRatio;
	/**保证金金额,实付金额*/
	@Excel(name = "保证金金额,实付金额", width = 15)
    @ApiModelProperty(value = "保证金金额,实付金额")
	private java.math.BigDecimal appBailSum;
	/**还款宽限期,张数*/
	@Excel(name = "还款宽限期,张数", width = 15)
    @ApiModelProperty(value = "还款宽限期,张数")
	private String appGp;
	/**还款期次,张数*/
	@Excel(name = "还款期次,张数", width = 15)
    @ApiModelProperty(value = "还款期次,张数")
	private String appPayTimes;
	/**还款周期*/
	@Excel(name = "还款周期", width = 15)
    @ApiModelProperty(value = "还款周期")
	private String appPayCyc;
	/**罚息利率类型*/
	@Excel(name = "罚息利率类型", width = 15)
    @ApiModelProperty(value = "罚息利率类型")
	private String fineRateType;
	/**逾期罚息利率*/
	@Excel(name = "逾期罚息利率", width = 15)
    @ApiModelProperty(value = "逾期罚息利率")
	private String fineRate;
	/**签约借款用途(66*3)*/
	@Excel(name = "签约借款用途(66*3)", width = 15)
    @ApiModelProperty(value = "签约借款用途(66*3)")
	private String actualPurpose;
	/**还款资金来源,签约还款来源*/
	@Excel(name = "还款资金来源,签约还款来源", width = 15)
    @ApiModelProperty(value = "还款资金来源,签约还款来源")
	private String paySource;
	/**还本方式,还款方式*/
	@Excel(name = "还本方式,还款方式", width = 15)
    @ApiModelProperty(value = "还本方式,还款方式")
	private String corpusPayMethod;
	/**付息方式,计息方式*/
	@Excel(name = "付息方式,计息方式", width = 15)
    @ApiModelProperty(value = "付息方式,计息方式")
	private String interestPayMethod;
	/**使用授信协议号ID*/
	@Excel(name = "使用授信协议号ID", width = 15)
    @ApiModelProperty(value = "使用授信协议号ID")
	private String creditAggreementId;
	/**货币交易合同编号,其他相关协议,原借款合同号*/
	@Excel(name = "货币交易合同编号,其他相关协议,原借款合同号", width = 15)
    @ApiModelProperty(value = "货币交易合同编号,其他相关协议,原借款合同号")
	private String relativeAgreement;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
	private String signDate;
	/**合同编号,协议编号,原借款合同号*/
	@Excel(name = "合同编号,协议编号,原借款合同号", width = 15)
    @ApiModelProperty(value = "合同编号,协议编号,原借款合同号")
	private String contractNo;
	/**约定发放日*/
	@Excel(name = "约定发放日", width = 15)
    @ApiModelProperty(value = "约定发放日")
	private String contractPutOutDate;
	/**约定到期日*/
	@Excel(name = "约定到期日", width = 15)
    @ApiModelProperty(value = "约定到期日")
	private String contractMaturity;
	/**涉及第三方*/
	@Excel(name = "涉及第三方", width = 15)
    @ApiModelProperty(value = "涉及第三方")
	private String thirdParty;
	/**第三方法人代码*/
	@Excel(name = "第三方法人代码", width = 15)
    @ApiModelProperty(value = "第三方法人代码")
	private String thirdPartyId;
	/**申请人涉及第三方所在地区和国家*/
	@Excel(name = "申请人涉及第三方所在地区和国家", width = 15)
    @ApiModelProperty(value = "申请人涉及第三方所在地区和国家")
	private String thirdPartyRegion;
	/**原借款金额*/
	@Excel(name = "原借款金额", width = 15)
    @ApiModelProperty(value = "原借款金额")
	private java.math.BigDecimal totalSum;
	/**原借款种类*/
	@Excel(name = "原借款种类", width = 15)
    @ApiModelProperty(value = "原借款种类")
	private String ourRole;
	/**有无追索权*/
	@Excel(name = "有无追索权", width = 15)
    @ApiModelProperty(value = "有无追索权")
	private String reversibility;
	/**担保方式,原担保方式*/
	@Excel(name = "担保方式,原担保方式", width = 15)
    @ApiModelProperty(value = "担保方式,原担保方式")
	private String vouchType;
	/**担保方式2*/
	@Excel(name = "担保方式2", width = 15)
    @ApiModelProperty(value = "担保方式2")
	private String vouchType1;
	/**担保方式3*/
	@Excel(name = "担保方式3", width = 15)
    @ApiModelProperty(value = "担保方式3")
	private String vouchType2;
	/**保证金方式*/
	@Excel(name = "保证金方式", width = 15)
    @ApiModelProperty(value = "保证金方式")
	private String vouchClass;
	/**展期次数*/
	@Excel(name = "展期次数", width = 15)
    @ApiModelProperty(value = "展期次数")
	private String extendTimes;
	/**借新还旧次数*/
	@Excel(name = "借新还旧次数", width = 15)
    @ApiModelProperty(value = "借新还旧次数")
	private String lngoTimes;
	/**还旧借新次数*/
	@Excel(name = "还旧借新次数", width = 15)
    @ApiModelProperty(value = "还旧借新次数")
	private String golnTimes;
	/**债务重组次数*/
	@Excel(name = "债务重组次数", width = 15)
    @ApiModelProperty(value = "债务重组次数")
	private String drTimes;
	/**首次发放日*/
	@Excel(name = "首次发放日", width = 15)
    @ApiModelProperty(value = "首次发放日")
	private String firstPutOutDate;
	/**综合风险度*/
	@Excel(name = "综合风险度", width = 15)
    @ApiModelProperty(value = "综合风险度")
	private java.math.BigDecimal riskRate;
	/**已出帐总金额*/
	@Excel(name = "已出帐总金额", width = 15)
    @ApiModelProperty(value = "已出帐总金额")
	private java.math.BigDecimal putOutTotalSum;
	/**备用1*/
	@Excel(name = "备用1", width = 15)
    @ApiModelProperty(value = "备用1")
	private String describe1;
	/**借据号码(总部发的合同号)*/
	@Excel(name = "借据号码(总部发的合同号)", width = 15)
    @ApiModelProperty(value = "借据号码(总部发的合同号)")
	private String describe2;
	/**其他审批意见*/
	@Excel(name = "其他审批意见", width = 15)
    @ApiModelProperty(value = "其他审批意见")
	private String describe3;
	/**备用4*/
	@Excel(name = "备用4", width = 15)
    @ApiModelProperty(value = "备用4")
	private String describe4;
	/**备用5*/
	@Excel(name = "备用5", width = 15)
    @ApiModelProperty(value = "备用5")
	private String describe5;
	/**备用6*/
	@Excel(name = "备用6", width = 15)
    @ApiModelProperty(value = "备用6")
	private String describe6;
	/**逾期原因*/
	@Excel(name = "逾期原因", width = 15)
    @ApiModelProperty(value = "逾期原因")
	private String describe7;
	/**预计收回时间*/
	@Excel(name = "预计收回时间", width = 15)
    @ApiModelProperty(value = "预计收回时间")
	private String describe8;
	/**抵押质押金额和保证人明细*/
	@Excel(name = "抵押质押金额和保证人明细", width = 15)
    @ApiModelProperty(value = "抵押质押金额和保证人明细")
	private String describe9;
	/**抵押质押保证人明细*/
	@Excel(name = "抵押质押保证人明细", width = 15)
    @ApiModelProperty(value = "抵押质押保证人明细")
	private String describe10;
	/**录入机构*/
	@Excel(name = "录入机构", width = 15)
    @ApiModelProperty(value = "录入机构")
	private String orgId;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String userId;
	/**输入日期*/
	@Excel(name = "输入日期", width = 15)
    @ApiModelProperty(value = "输入日期")
	private String inputDate;
	/**更改日期*/
	@Excel(name = "更改日期", width = 15)
    @ApiModelProperty(value = "更改日期")
	private String updateDate;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**还款帐号,结算账号*/
	@Excel(name = "还款帐号,结算账号", width = 15)
    @ApiModelProperty(value = "还款帐号,结算账号")
	private String dealerAccount;
	/**罚息月利率*/
	@Excel(name = "罚息月利率", width = 15)
    @ApiModelProperty(value = "罚息月利率")
	private java.math.BigDecimal punishMonthRate;
	/**钞汇标志*/
	@Excel(name = "钞汇标志", width = 15)
    @ApiModelProperty(value = "钞汇标志")
	private String bankFlag;
	/**放款日期*/
	@Excel(name = "放款日期", width = 15)
    @ApiModelProperty(value = "放款日期")
	private String appPutOutDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String appMaturityDate;
	/**是否启用授信*/
	@Excel(name = "是否启用授信", width = 15)
    @ApiModelProperty(value = "是否启用授信")
	private String isCredit;
	/**公证机构*/
	@Excel(name = "公证机构", width = 15)
    @ApiModelProperty(value = "公证机构")
	private String notaryOrg;
	/**公证人*/
	@Excel(name = "公证人", width = 15)
    @ApiModelProperty(value = "公证人")
	private String notary;
	/**公正日期*/
	@Excel(name = "公正日期", width = 15)
    @ApiModelProperty(value = "公正日期")
	private String notaryDate;
	/**是否公正*/
	@Excel(name = "是否公正", width = 15)
    @ApiModelProperty(value = "是否公正")
	private String isNotary;
	/**是否同时公正*/
	@Excel(name = "是否同时公正", width = 15)
    @ApiModelProperty(value = "是否同时公正")
	private String isTheSameNotary;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal balance;
	/**授信序号*/
	@Excel(name = "授信序号", width = 15)
    @ApiModelProperty(value = "授信序号")
	private String creditDetailId;
	/**是否利率定价*/
	@Excel(name = "是否利率定价", width = 15)
    @ApiModelProperty(value = "是否利率定价")
	private String appRateExecType;
	/**保证金币种*/
	@Excel(name = "保证金币种", width = 15)
    @ApiModelProperty(value = "保证金币种")
	private String appBailCurrecny;
	/**贷款方式*/
	@Excel(name = "贷款方式", width = 15)
    @ApiModelProperty(value = "贷款方式")
	private String loanMode;
	/**是否使用贷款证*/
	@Excel(name = "是否使用贷款证", width = 15)
    @ApiModelProperty(value = "是否使用贷款证")
	private String isUseLoanCard;
	/**是否财政贴息*/
	@Excel(name = "是否财政贴息", width = 15)
    @ApiModelProperty(value = "是否财政贴息")
	private String isFinanceIntBal;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String industryType;
	/**人行标准*/
	@Excel(name = "人行标准", width = 15)
    @ApiModelProperty(value = "人行标准")
	private String pbcStandard;
	/**本行标准*/
	@Excel(name = "本行标准", width = 15)
    @ApiModelProperty(value = "本行标准")
	private String selfStandard;
	/**合同类型, 授信方式*/
	@Excel(name = "合同类型, 授信方式", width = 15)
    @ApiModelProperty(value = "合同类型, 授信方式")
	private String contractType;
	/**逾期罚息比例*/
	@Excel(name = "逾期罚息比例", width = 15)
    @ApiModelProperty(value = "逾期罚息比例")
	private java.math.BigDecimal fineRatio;
	/**挤占拥用罚息比例*/
	@Excel(name = "挤占拥用罚息比例", width = 15)
    @ApiModelProperty(value = "挤占拥用罚息比例")
	private java.math.BigDecimal apprFineRatio;
	/**放贷人编号*/
	@Excel(name = "放贷人编号", width = 15)
    @ApiModelProperty(value = "放贷人编号")
	private String fkId;
	/**征信业务种类*/
	@Excel(name = "征信业务种类", width = 15)
    @ApiModelProperty(value = "征信业务种类")
	private String businessKind;
	/**贷款形式*/
	@Excel(name = "贷款形式", width = 15)
    @ApiModelProperty(value = "贷款形式")
	private String businessType3;
	/**借款人存款账号*/
	@Excel(name = "借款人存款账号", width = 15)
    @ApiModelProperty(value = "借款人存款账号")
	private String jbckzh;
	/**包收责任人*/
	@Excel(name = "包收责任人", width = 15)
    @ApiModelProperty(value = "包收责任人")
	private String assureUserId;
	/**审查责任人*/
	@Excel(name = "审查责任人", width = 15)
    @ApiModelProperty(value = "审查责任人")
	private String censorUserId;
	/**审批责任人*/
	@Excel(name = "审批责任人", width = 15)
    @ApiModelProperty(value = "审批责任人")
	private String examApprUserId;
	/**管理责任人*/
	@Excel(name = "管理责任人", width = 15)
    @ApiModelProperty(value = "管理责任人")
	private String managerUserId;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	@TableId(type = IdType.ASSIGN_ID)
	private String businessNo;
	/**贷款类型*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private String businessType;
	/**贷款科目一,贷款性质,业务分类,业务品种*/
	@Excel(name = "贷款科目一,贷款性质,业务分类,业务品种", width = 15)
    @ApiModelProperty(value = "贷款科目一,贷款性质,业务分类,业务品种")
	private String businessSubType;
	/**贷款分类,贷款科目二,发生类型*/
	@Excel(name = "贷款分类,贷款科目二,发生类型", width = 15)
    @ApiModelProperty(value = "贷款分类,贷款科目二,发生类型")
	private String occurType;
	/**业务流程,适用的流程*/
	@Excel(name = "业务流程,适用的流程", width = 15)
    @ApiModelProperty(value = "业务流程,适用的流程")
	private String businessFlow;
	/**业务阶段*/
	@Excel(name = "业务阶段", width = 15)
    @ApiModelProperty(value = "业务阶段")
	private String businessPhase;
	/**业务形态*/
	@Excel(name = "业务形态", width = 15)
    @ApiModelProperty(value = "业务形态")
	private String businessStatus;
	/**业务分类*/
	@Excel(name = "业务分类", width = 15)
    @ApiModelProperty(value = "业务分类")
	private String businessType1;
	/**风险类型*/
	@Excel(name = "风险类型", width = 15)
    @ApiModelProperty(value = "风险类型")
	private String businessType2;
	/**业务属性*/
	@Excel(name = "业务属性", width = 15)
    @ApiModelProperty(value = "业务属性")
	private String businessAttribute1;
	/**业务属性*/
	@Excel(name = "业务属性", width = 15)
    @ApiModelProperty(value = "业务属性")
	private String businessAttribute2;
	/**业务属性*/
	@Excel(name = "业务属性", width = 15)
    @ApiModelProperty(value = "业务属性")
	private String businessAttribute3;
	/**业务属性*/
	@Excel(name = "业务属性", width = 15)
    @ApiModelProperty(value = "业务属性")
	private String businessAttribute4;
	/**承兑申请人姓名,申请人,申请人姓名,申请展期人*/
	@Excel(name = "承兑申请人姓名,申请人,申请人姓名,申请展期人", width = 15)
    @ApiModelProperty(value = "承兑申请人姓名,申请人,申请人姓名,申请展期人")
	private String applicantName;
	/**用款计划*/
	@Excel(name = "用款计划", width = 15)
    @ApiModelProperty(value = "用款计划")
	private String planAllocation;
	/**实际用款情况*/
	@Excel(name = "实际用款情况", width = 15)
    @ApiModelProperty(value = "实际用款情况")
	private String actualAllocation;
	/**汇票期限,批准期限,签约期限,申请借新还旧期限,原借款期限*/
	@Excel(name = "汇票期限,批准期限,签约期限,申请借新还旧期限,原借款期限", width = 15)
    @ApiModelProperty(value = "汇票期限,批准期限,签约期限,申请借新还旧期限,原借款期限")
	private String appTerm;
	/**批准日期*/
	@Excel(name = "批准日期", width = 15)
    @ApiModelProperty(value = "批准日期")
	private String appDate;
	/**汇票币种,批准币种,签约币种*/
	@Excel(name = "汇票币种,批准币种,签约币种", width = 15)
    @ApiModelProperty(value = "汇票币种,批准币种,签约币种")
	private String appCurrency;
	/**汇票金额,借款金额,批准保函金额,批准金额,票面金额,签约金额,申请金额,原借款金额*/
	@Excel(name = "汇票金额,借款金额,批准保函金额,批准金额,票面金额,签约金额,申请金额,原借款金额", width = 15)
    @ApiModelProperty(value = "汇票金额,借款金额,批准保函金额,批准金额,票面金额,签约金额,申请金额,原借款金额")
	private java.math.BigDecimal appSum;
	/**张数*/
	@Excel(name = "张数", width = 15)
    @ApiModelProperty(value = "张数")
	private java.math.BigDecimal appProp;
	/**批准期限年*/
	@Excel(name = "批准期限年", width = 15)
    @ApiModelProperty(value = "批准期限年")
	private String appTermYear;
	/**批准期限月*/
	@Excel(name = "批准期限月", width = 15)
    @ApiModelProperty(value = "批准期限月")
	private String appTermMonth;
	/**贴息类型*/
	@Excel(name = "贴息类型", width = 15)
    @ApiModelProperty(value = "贴息类型")
	private String businessOther1;
	/**合同打印类型*/
	@Excel(name = "合同打印类型", width = 15)
    @ApiModelProperty(value = "合同打印类型")
	private String businessOther2;
	/**贷款其他属性3*/
	@Excel(name = "贷款其他属性3", width = 15)
    @ApiModelProperty(value = "贷款其他属性3")
	private String businessOther3;
	/**贷款其他属性4*/
	@Excel(name = "贷款其他属性4", width = 15)
    @ApiModelProperty(value = "贷款其他属性4")
	private String businessOther4;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**数据日期*/
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;

//	private Integer dtnum;
//	private Date dttime;
}
