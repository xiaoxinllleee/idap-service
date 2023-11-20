package org.cmms.modules.sjxf.xdxt.ywxxb.entity;

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
 * @Description: 业务信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_business_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_business_info对象", description="业务信息表")
public class Ywxxb {
    
	/**dataFlag*/
	@Excel(name = "dataFlag", width = 15)
    @ApiModelProperty(value = "dataFlag")
	private String dataFlag;
	/**业务受理号*/
	@Excel(name = "业务受理号", width = 15)
    @ApiModelProperty(value = "业务受理号")
	private String busCreCode;
	/**使用授信协议号ID*/
	@Excel(name = "使用授信协议号ID", width = 15)
    @ApiModelProperty(value = "使用授信协议号ID")
	private String creditAggreementId;
	/**授信序号*/
	@Excel(name = "授信序号", width = 15)
    @ApiModelProperty(value = "授信序号")
	private String creditDetailId;
	/**前台客户号*/
	@Excel(name = "前台客户号", width = 15)
    @ApiModelProperty(value = "前台客户号")
	private String custFrontCode;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**债务重组次数*/
	@Excel(name = "债务重组次数", width = 15)
    @ApiModelProperty(value = "债务重组次数")
	private String drTimes;
	/**展期时间*/
	@Excel(name = "展期时间", width = 15)
    @ApiModelProperty(value = "展期时间")
	private String extendTimes;
	/**逾期罚息利率*/
	@Excel(name = "逾期罚息利率", width = 15)
    @ApiModelProperty(value = "逾期罚息利率")
	private String fineRate;
	/**罚息利率类型*/
	@Excel(name = "罚息利率类型", width = 15)
    @ApiModelProperty(value = "罚息利率类型")
	private String fineRateType;
	/**首次还款日期*/
	@Excel(name = "首次还款日期", width = 15)
    @ApiModelProperty(value = "首次还款日期")
	private String firstReturnDate;
	/**放款人编号*/
	@Excel(name = "放款人编号", width = 15)
    @ApiModelProperty(value = "放款人编号")
	private String fkId;
	/**展期后总期限*/
	@Excel(name = "展期后总期限", width = 15)
    @ApiModelProperty(value = "展期后总期限")
	private String golnTimes;
	/**公证书编号*/
	@Excel(name = "公证书编号", width = 15)
    @ApiModelProperty(value = "公证书编号")
	private String guarantyNdNo;
	/**原借款利率*/
	@Excel(name = "原借款利率", width = 15)
    @ApiModelProperty(value = "原借款利率")
	private java.math.BigDecimal guarantyRate;
	/**担保总价值*/
	@Excel(name = "担保总价值", width = 15)
    @ApiModelProperty(value = "担保总价值")
	private java.math.BigDecimal guarantyValue;
	/**输入日期*/
	@Excel(name = "输入日期", width = 15)
    @ApiModelProperty(value = "输入日期")
	private String inputDate;
	/**付息方式,计息方式*/
	@Excel(name = "付息方式,计息方式", width = 15)
    @ApiModelProperty(value = "付息方式,计息方式")
	private String interestPayMethod;
	/**利息比例*/
	@Excel(name = "利息比例", width = 15)
    @ApiModelProperty(value = "利息比例")
	private String interestScal;
	/**是否启用授信*/
	@Excel(name = "是否启用授信", width = 15)
    @ApiModelProperty(value = "是否启用授信")
	private String isCredit;
	/**借新还旧次数*/
	@Excel(name = "借新还旧次数", width = 15)
    @ApiModelProperty(value = "借新还旧次数")
	private String lngoTimes;
	/**贷款帐号*/
	@Excel(name = "贷款帐号", width = 15)
    @ApiModelProperty(value = "贷款帐号")
	private String loanAccount;
	/**贷款分类,贷款科目二,发生类型*/
	@Excel(name = "贷款分类,贷款科目二,发生类型", width = 15)
    @ApiModelProperty(value = "贷款分类,贷款科目二,发生类型")
	private String occurType;
	/**录入机构*/
	@Excel(name = "录入机构", width = 15)
    @ApiModelProperty(value = "录入机构")
	private String orgId;
	/**原借款种类*/
	@Excel(name = "原借款种类", width = 15)
    @ApiModelProperty(value = "原借款种类")
	private String ourRole;
	/**还款资金来源,签约还款来源*/
	@Excel(name = "还款资金来源,签约还款来源", width = 15)
    @ApiModelProperty(value = "还款资金来源,签约还款来源")
	private String paySource;
	/**归还比例*/
	@Excel(name = "归还比例", width = 15)
    @ApiModelProperty(value = "归还比例")
	private String paymentScal;
	/**货币交易合同编号,其他相关协议,原借款合同号*/
	@Excel(name = "货币交易合同编号,其他相关协议,原借款合同号", width = 15)
    @ApiModelProperty(value = "货币交易合同编号,其他相关协议,原借款合同号")
	private String relativeAgreement;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**有无追索权*/
	@Excel(name = "有无追索权", width = 15)
    @ApiModelProperty(value = "有无追索权")
	private String reversibility;
	/**综合风险度*/
	@Excel(name = "综合风险度", width = 15)
    @ApiModelProperty(value = "综合风险度")
	private java.math.BigDecimal riskRate;
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
	/**更改日期*/
	@Excel(name = "更改日期", width = 15)
    @ApiModelProperty(value = "更改日期")
	private String updateDate;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String userId;
	/**保证金方式*/
	@Excel(name = "保证金方式", width = 15)
    @ApiModelProperty(value = "保证金方式")
	private String vouchClass;
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
	/**warrantorId*/
	@Excel(name = "warrantorId", width = 15)
    @ApiModelProperty(value = "warrantorId")
	private String warrantorId;
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
	/**逾期罚息比例*/
	@Excel(name = "逾期罚息比例", width = 15)
    @ApiModelProperty(value = "逾期罚息比例")
	private java.math.BigDecimal fineRatio;
	/**挤占拥用罚息比例*/
	@Excel(name = "挤占拥用罚息比例", width = 15)
    @ApiModelProperty(value = "挤占拥用罚息比例")
	private java.math.BigDecimal apprFineRatio;
	/**征信业务种类*/
	@Excel(name = "征信业务种类", width = 15)
    @ApiModelProperty(value = "征信业务种类")
	private String businessKind;
	/**贷款形式*/
	@Excel(name = "贷款形式", width = 15)
    @ApiModelProperty(value = "贷款形式")
	private String businessType3;
	/**付息账号*/
	@Excel(name = "付息账号", width = 15)
    @ApiModelProperty(value = "付息账号")
	private String jbckzh;
	/**合同类型*/
	@Excel(name = "合同类型", width = 15)
    @ApiModelProperty(value = "合同类型")
	private String contractType;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String industryType;
	/**联络员*/
	@Excel(name = "联络员", width = 15)
    @ApiModelProperty(value = "联络员")
	private String contactUserId;
	/**包收责任人*/
	@Excel(name = "包收责任人", width = 15)
    @ApiModelProperty(value = "包收责任人")
	private String assureUserId;
	/**相片路径*/
	@Excel(name = "相片路径", width = 15)
    @ApiModelProperty(value = "相片路径")
	private String photo;
	/**前台结算帐号*/
	@Excel(name = "前台结算帐号", width = 15)
    @ApiModelProperty(value = "前台结算帐号")
	private String accountNo;
	/**保证金币种*/
	@Excel(name = "保证金币种", width = 15)
    @ApiModelProperty(value = "保证金币种")
	private String appBailCurrecny;
	/**利率执行方式*/
	@Excel(name = "利率执行方式", width = 15)
    @ApiModelProperty(value = "利率执行方式")
	private String appRateExecType;
	/**申请书号*/
	@Excel(name = "申请书号", width = 15)
    @ApiModelProperty(value = "申请书号")
	private String applicantId;
	/**承兑申请人姓名,申请人,申请人姓名,申请展期人*/
	@Excel(name = "承兑申请人姓名,申请人,申请人姓名,申请展期人", width = 15)
    @ApiModelProperty(value = "承兑申请人姓名,申请人,申请人姓名,申请展期人")
	private String applicantName;
	/**保证金比例,利息收入*/
	@Excel(name = "保证金比例,利息收入", width = 15)
    @ApiModelProperty(value = "保证金比例,利息收入")
	private java.math.BigDecimal applyBailRatio;
	/**保证金金额,欠息金额,实付金额*/
	@Excel(name = "保证金金额,欠息金额,实付金额", width = 15)
    @ApiModelProperty(value = "保证金金额,欠息金额,实付金额")
	private java.math.BigDecimal applyBailSum;
	/**申请币种*/
	@Excel(name = "申请币种", width = 15)
    @ApiModelProperty(value = "申请币种")
	private String applyCurrency;
	/**签定货币交易合同日期,申请日期*/
	@Excel(name = "签定货币交易合同日期,申请日期", width = 15)
    @ApiModelProperty(value = "签定货币交易合同日期,申请日期")
	private String applyDate;
	/**申请浮动方式, 申请利率浮动方式*/
	@Excel(name = "申请浮动方式, 申请利率浮动方式", width = 15)
    @ApiModelProperty(value = "申请浮动方式, 申请利率浮动方式")
	private String applyFloatType;
	/**还款宽限期,原借款期限,张数*/
	@Excel(name = "还款宽限期,原借款期限,张数", width = 15)
    @ApiModelProperty(value = "还款宽限期,原借款期限,张数")
	private String applyGp;
	/**原担保方式,支付方式*/
	@Excel(name = "原担保方式,支付方式", width = 15)
    @ApiModelProperty(value = "原担保方式,支付方式")
	private String applyMfeePayMethod;
	/**管理费比例,实付金额*/
	@Excel(name = "管理费比例,实付金额", width = 15)
    @ApiModelProperty(value = "管理费比例,实付金额")
	private java.math.BigDecimal applyMfeeRatio;
	/**本次偿还金额,管理费金额,票面金额*/
	@Excel(name = "本次偿还金额,管理费金额,票面金额", width = 15)
    @ApiModelProperty(value = "本次偿还金额,管理费金额,票面金额")
	private java.math.BigDecimal applyMfeeSum;
	/**还款周期*/
	@Excel(name = "还款周期", width = 15)
    @ApiModelProperty(value = "还款周期")
	private String applyPayCyc;
	/**还款期次,张数*/
	@Excel(name = "还款期次,张数", width = 15)
    @ApiModelProperty(value = "还款期次,张数")
	private String applyPayTimes;
	/**支付方式*/
	@Excel(name = "支付方式", width = 15)
    @ApiModelProperty(value = "支付方式")
	private String applyPdgPayMethod;
	/**票面金额,手续费比例*/
	@Excel(name = "票面金额,手续费比例", width = 15)
    @ApiModelProperty(value = "票面金额,手续费比例")
	private java.math.BigDecimal applyPdgRatio;
	/**利息收入,手续费金额*/
	@Excel(name = "利息收入,手续费金额", width = 15)
    @ApiModelProperty(value = "利息收入,手续费金额")
	private java.math.BigDecimal applyPdgSum;
	/**张数*/
	@Excel(name = "张数", width = 15)
    @ApiModelProperty(value = "张数")
	private java.math.BigDecimal applyProp;
	/**申请贷款用途*/
	@Excel(name = "申请贷款用途", width = 15)
    @ApiModelProperty(value = "申请贷款用途")
	private String applyPurpose;
	/**申请执行利率,实付金额*/
	@Excel(name = "申请执行利率,实付金额", width = 15)
    @ApiModelProperty(value = "申请执行利率,实付金额")
	private java.math.BigDecimal applyRate;
	/**申请浮动比例, 申请浮动比率, 申请利息收入*/
	@Excel(name = "申请浮动比例, 申请浮动比率, 申请利息收入", width = 15)
    @ApiModelProperty(value = "申请浮动比例, 申请浮动比率, 申请利息收入")
	private java.math.BigDecimal applyRateFloat;
	/**申请利率类型*/
	@Excel(name = "申请利率类型", width = 15)
    @ApiModelProperty(value = "申请利率类型")
	private String applyRateType;
	/**票面金额,申请承兑汇票金额,申请金额,申请贴现金额*/
	@Excel(name = "票面金额,申请承兑汇票金额,申请金额,申请贴现金额", width = 15)
    @ApiModelProperty(value = "票面金额,申请承兑汇票金额,申请金额,申请贴现金额")
	private java.math.BigDecimal applySum;
	/**汇票张数,申请期限*/
	@Excel(name = "汇票张数,申请期限", width = 15)
    @ApiModelProperty(value = "汇票张数,申请期限")
	private String applyTerm;
	/**申请期限日*/
	@Excel(name = "申请期限日", width = 15)
    @ApiModelProperty(value = "申请期限日")
	private String applyTermDay;
	/**申请期限月*/
	@Excel(name = "申请期限月", width = 15)
    @ApiModelProperty(value = "申请期限月")
	private String applyTermMonth;
	/**申请期限年*/
	@Excel(name = "申请期限年", width = 15)
    @ApiModelProperty(value = "申请期限年")
	private String applyTermYear;
	/**保证金支付帐号,支付帐号*/
	@Excel(name = "保证金支付帐号,支付帐号", width = 15)
    @ApiModelProperty(value = "保证金支付帐号,支付帐号")
	private String bailAccount;
	/**基期信用等级*/
	@Excel(name = "基期信用等级", width = 15)
    @ApiModelProperty(value = "基期信用等级")
	private String baseEvaluateResult;
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
	/**业务流程,适用的流程*/
	@Excel(name = "业务流程,适用的流程", width = 15)
    @ApiModelProperty(value = "业务流程,适用的流程")
	private String businessFlow;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**业务阶段*/
	@Excel(name = "业务阶段", width = 15)
    @ApiModelProperty(value = "业务阶段")
	private String businessPhase;
	/**业务形态*/
	@Excel(name = "业务形态", width = 15)
    @ApiModelProperty(value = "业务形态")
	private String businessStatus;
	/**贷款科目一,贷款性质,业务分类,业务品种*/
	@Excel(name = "贷款科目一,贷款性质,业务分类,业务品种", width = 15)
    @ApiModelProperty(value = "贷款科目一,贷款性质,业务分类,业务品种")
	private String businessSubType;
	/**贷款类型*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private String businessType;
	/**业务分类*/
	@Excel(name = "业务分类", width = 15)
    @ApiModelProperty(value = "业务分类")
	private String businessType1;
	/**风险类型*/
	@Excel(name = "风险类型", width = 15)
    @ApiModelProperty(value = "风险类型")
	private String businessType2;
	/**还本方式,还款方式*/
	@Excel(name = "还本方式,还款方式", width = 15)
    @ApiModelProperty(value = "还本方式,还款方式")
	private String corpusPayMethod;
	/**联机交易号*/
	@Excel(name = "联机交易号", width = 15)
    @ApiModelProperty(value = "联机交易号")
	private String sid;
	/**利率类型*/
	@Excel(name = "利率类型", width = 15)
    @ApiModelProperty(value = "利率类型")
	private String rateType;
	/**产品代码*/
	@Excel(name = "产品代码", width = 15)
    @ApiModelProperty(value = "产品代码")
	private String productType;
	/**还款周期类型*/
	@Excel(name = "还款周期类型", width = 15)
    @ApiModelProperty(value = "还款周期类型")
	private String repyCycTp;
	/**还款方式类型*/
	@Excel(name = "还款方式类型", width = 15)
    @ApiModelProperty(value = "还款方式类型")
	private String repyMthTp;
	/**浮动利率生效类型*/
	@Excel(name = "浮动利率生效类型", width = 15)
    @ApiModelProperty(value = "浮动利率生效类型")
	private String fltRateEffTp;
	/**自动扣款类型*/
	@Excel(name = "自动扣款类型", width = 15)
    @ApiModelProperty(value = "自动扣款类型")
	private String atmDdtTp;
	/**足额扣款类型*/
	@Excel(name = "足额扣款类型", width = 15)
    @ApiModelProperty(value = "足额扣款类型")
	private String fullDdtTp;
	/**提前还款后的重算方式*/
	@Excel(name = "提前还款后的重算方式", width = 15)
    @ApiModelProperty(value = "提前还款后的重算方式")
	private String advRepyAfRtrMth;
	/**首次还款日标识*/
	@Excel(name = "首次还款日标识", width = 15)
    @ApiModelProperty(value = "首次还款日标识")
	private String frstRepyDtTp;
	/**委托资金协议号*/
	@Excel(name = "委托资金协议号", width = 15)
    @ApiModelProperty(value = "委托资金协议号")
	private String entrstFndAgrmtNo;
	/**浮动周期*/
	@Excel(name = "浮动周期", width = 15)
    @ApiModelProperty(value = "浮动周期")
	private String fltCycTp;
	/**浮动周期基数*/
	@Excel(name = "浮动周期基数", width = 15)
    @ApiModelProperty(value = "浮动周期基数")
	private String fltCycBscTp;
	/**首次浮动日*/
	@Excel(name = "首次浮动日", width = 15)
    @ApiModelProperty(value = "首次浮动日")
	private String rateFrstFltDt;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
	private String sDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人编号*/
	@Excel(name = "法人编号", width = 15)
    @ApiModelProperty(value = "法人编号")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
/*	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
/*	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
	/**lprFlag*/
	@Excel(name = "lprFlag", width = 15)
    @ApiModelProperty(value = "lprFlag")
	private String lprFlag;
	/**lprRateId*/
	@Excel(name = "lprRateId", width = 15)
    @ApiModelProperty(value = "lprRateId")
	private String lprRateId;
}
