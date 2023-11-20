package org.cmms.modules.sjxf.qtxt.djkxt.zhzlb.entity;

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
 * @Description: 账户资料表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ccd_acct")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ccd_acct对象", description="账户资料表")
public class Zhzlb {
    
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String cardNbr;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String xaccount;
	/**最近地址修改日期*/
	@Excel(name = "最近地址修改日期", width = 15)
    @ApiModelProperty(value = "最近地址修改日期")
	private String addChgday;
	/**帐单地址类型*/
	@Excel(name = "帐单地址类型", width = 15)
    @ApiModelProperty(value = "帐单地址类型")
	private String addrType;
	/**卡片产品种类*/
	@Excel(name = "卡片产品种类", width = 15)
    @ApiModelProperty(value = "卡片产品种类")
	private String product;
	/**分行编号*/
	@Excel(name = "分行编号", width = 15)
    @ApiModelProperty(value = "分行编号")
	private String branch;
	/**公司编号*/
	@Excel(name = "公司编号", width = 15)
    @ApiModelProperty(value = "公司编号")
	private String business;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String dayOpened;
	/**信用额度*/
	@Excel(name = "信用额度", width = 15)
    @ApiModelProperty(value = "信用额度")
	private java.math.BigDecimal credLimit;
	/**第二币种信用额度*/
	@Excel(name = "第二币种信用额度", width = 15)
    @ApiModelProperty(value = "第二币种信用额度")
	private java.math.BigDecimal credlimX;
	/**分期付款信用额度*/
	@Excel(name = "分期付款信用额度", width = 15)
    @ApiModelProperty(value = "分期付款信用额度")
	private java.math.BigDecimal mpLimit;
	/**授权未请款金额*/
	@Excel(name = "授权未请款金额", width = 15)
    @ApiModelProperty(value = "授权未请款金额")
	private java.math.BigDecimal authsAmt;
	/**还款截止日期*/
	@Excel(name = "还款截止日期", width = 15)
    @ApiModelProperty(value = "还款截止日期")
	private String cutoffDay;
	/**上次还款日期*/
	@Excel(name = "上次还款日期", width = 15)
    @ApiModelProperty(value = "上次还款日期")
	private String lastpayday;
	/**已结算付款额*/
	@Excel(name = "已结算付款额", width = 15)
    @ApiModelProperty(value = "已结算付款额")
	private java.math.BigDecimal paymtClrd;
	/**本期消费净额*/
	@Excel(name = "本期消费净额", width = 15)
    @ApiModelProperty(value = "本期消费净额")
	private java.math.BigDecimal purchases;
	/**本期消费净额(正负号)*/
	@Excel(name = "本期消费净额(正负号)", width = 15)
    @ApiModelProperty(value = "本期消费净额(正负号)")
	private String purchasesFlag;
	/**本期年费金额*/
	@Excel(name = "本期年费金额", width = 15)
    @ApiModelProperty(value = "本期年费金额")
	private java.math.BigDecimal cardFees;
	/**本期预借现金费用总额*/
	@Excel(name = "本期预借现金费用总额", width = 15)
    @ApiModelProperty(value = "本期预借现金费用总额")
	private java.math.BigDecimal cashAdfee;
	/**本期预借现金总额*/
	@Excel(name = "本期预借现金总额", width = 15)
    @ApiModelProperty(value = "本期预借现金总额")
	private java.math.BigDecimal cashAdvce;
	/**本期其它费用的总额*/
	@Excel(name = "本期其它费用的总额", width = 15)
    @ApiModelProperty(value = "本期其它费用的总额")
	private java.math.BigDecimal otherFees;
	/**本期预收罚金*/
	@Excel(name = "本期预收罚金", width = 15)
    @ApiModelProperty(value = "本期预收罚金")
	private java.math.BigDecimal penchgAcc;
	/**逾期金额*/
	@Excel(name = "逾期金额", width = 15)
    @ApiModelProperty(value = "逾期金额")
	private java.math.BigDecimal stmOverdu;
	/**逾期天数*/
	@Excel(name = "逾期天数", width = 15)
    @ApiModelProperty(value = "逾期天数")
	private String dayOdue;
	/**争议金额*/
	@Excel(name = "争议金额", width = 15)
    @ApiModelProperty(value = "争议金额")
	private java.math.BigDecimal queryAmt;
	/**本期借记调整金额*/
	@Excel(name = "本期借记调整金额", width = 15)
    @ApiModelProperty(value = "本期借记调整金额")
	private java.math.BigDecimal debitAdj;
	/**本期贷记调帐金额*/
	@Excel(name = "本期贷记调帐金额", width = 15)
    @ApiModelProperty(value = "本期贷记调帐金额")
	private java.math.BigDecimal credAdj;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String closeCode;
	/**账户状态日期*/
	@Excel(name = "账户状态日期", width = 15)
    @ApiModelProperty(value = "账户状态日期")
	private String closeChdy;
	/**昨日消费余额*/
	@Excel(name = "昨日消费余额", width = 15)
    @ApiModelProperty(value = "昨日消费余额")
	private java.math.BigDecimal balFree;
	/**昨日预借现金余额*/
	@Excel(name = "昨日预借现金余额", width = 15)
    @ApiModelProperty(value = "昨日预借现金余额")
	private java.math.BigDecimal balInt;
	/**昨日预借现金余额符号*/
	@Excel(name = "昨日预借现金余额符号", width = 15)
    @ApiModelProperty(value = "昨日预借现金余额符号")
	private String balIntFlag;
	/**昨日费用余额*/
	@Excel(name = "昨日费用余额", width = 15)
    @ApiModelProperty(value = "昨日费用余额")
	private java.math.BigDecimal balNoint;
	/**昨日利息余额*/
	@Excel(name = "昨日利息余额", width = 15)
    @ApiModelProperty(value = "昨日利息余额")
	private java.math.BigDecimal balOrint;
	/**帐户进入催收队列的日期*/
	@Excel(name = "帐户进入催收队列的日期", width = 15)
    @ApiModelProperty(value = "帐户进入催收队列的日期")
	private String octDayin;
	/**货币代码*/
	@Excel(name = "货币代码", width = 15)
    @ApiModelProperty(value = "货币代码")
	private String currNum;
	/**最近一次计息日期*/
	@Excel(name = "最近一次计息日期", width = 15)
    @ApiModelProperty(value = "最近一次计息日期")
	private String intUptody;
	/**应收利息*/
	@Excel(name = "应收利息", width = 15)
    @ApiModelProperty(value = "应收利息")
	private java.math.BigDecimal intChgd;
	/**本帐期的还款笔数*/
	@Excel(name = "本帐期的还款笔数", width = 15)
    @ApiModelProperty(value = "本帐期的还款笔数")
	private String nbrPaymnt;
	/**本帐期的一般消费笔数*/
	@Excel(name = "本帐期的一般消费笔数", width = 15)
    @ApiModelProperty(value = "本帐期的一般消费笔数")
	private String nbrPurch;
	/**本帐期的所有交易笔数*/
	@Excel(name = "本帐期的所有交易笔数", width = 15)
    @ApiModelProperty(value = "本帐期的所有交易笔数")
	private String nbrTrans;
	/**当期调整的红利积分*/
	@Excel(name = "当期调整的红利积分", width = 15)
    @ApiModelProperty(value = "当期调整的红利积分")
	private String pointAdj1;
	/**当期兑换的红利积分*/
	@Excel(name = "当期兑换的红利积分", width = 15)
    @ApiModelProperty(value = "当期兑换的红利积分")
	private String pointClm;
	/**累计的红利积分*/
	@Excel(name = "累计的红利积分", width = 15)
    @ApiModelProperty(value = "累计的红利积分")
	private String pointCum;
	/**当期新增的红利积分*/
	@Excel(name = "当期新增的红利积分", width = 15)
    @ApiModelProperty(value = "当期新增的红利积分")
	private String pointEar;
	/**自动扣款代码*/
	@Excel(name = "自动扣款代码", width = 15)
    @ApiModelProperty(value = "自动扣款代码")
	private String repayCode1;
	/**最近一期帐单的不计息余额*/
	@Excel(name = "最近一期帐单的不计息余额", width = 15)
    @ApiModelProperty(value = "最近一期帐单的不计息余额")
	private java.math.BigDecimal stmNoint;
	/**最近一期帐单的消费余额*/
	@Excel(name = "最近一期帐单的消费余额", width = 15)
    @ApiModelProperty(value = "最近一期帐单的消费余额")
	private java.math.BigDecimal stmBalfre;
	/**最近一期帐单的利息余额*/
	@Excel(name = "最近一期帐单的利息余额", width = 15)
    @ApiModelProperty(value = "最近一期帐单的利息余额")
	private java.math.BigDecimal stmBalori;
	/**最近一期帐单的预借现金余额*/
	@Excel(name = "最近一期帐单的预借现金余额", width = 15)
    @ApiModelProperty(value = "最近一期帐单的预借现金余额")
	private java.math.BigDecimal stmBalint;
	/**最近一期帐单的预借现金符号*/
	@Excel(name = "最近一期帐单的预借现金符号", width = 15)
    @ApiModelProperty(value = "最近一期帐单的预借现金符号")
	private String stmBalintSign;
	/**最近一期帐单金额*/
	@Excel(name = "最近一期帐单金额", width = 15)
    @ApiModelProperty(value = "最近一期帐单金额")
	private java.math.BigDecimal stmBalnce;
	/**最近一期帐单的最低应缴款*/
	@Excel(name = "最近一期帐单的最低应缴款", width = 15)
    @ApiModelProperty(value = "最近一期帐单的最低应缴款")
	private java.math.BigDecimal stmMindue;
	/**帐单代码*/
	@Excel(name = "帐单代码", width = 15)
    @ApiModelProperty(value = "帐单代码")
	private String stmCode;
	/**逾期金额1*/
	@Excel(name = "逾期金额1", width = 15)
    @ApiModelProperty(value = "逾期金额1")
	private java.math.BigDecimal ageingA1;
	/**逾期金额2*/
	@Excel(name = "逾期金额2", width = 15)
    @ApiModelProperty(value = "逾期金额2")
	private java.math.BigDecimal ageingA2;
	/**逾期金额3*/
	@Excel(name = "逾期金额3", width = 15)
    @ApiModelProperty(value = "逾期金额3")
	private java.math.BigDecimal ageingA3;
	/**逾期金额4*/
	@Excel(name = "逾期金额4", width = 15)
    @ApiModelProperty(value = "逾期金额4")
	private java.math.BigDecimal ageingA4;
	/**逾期金额5*/
	@Excel(name = "逾期金额5", width = 15)
    @ApiModelProperty(value = "逾期金额5")
	private java.math.BigDecimal ageingA5;
	/**逾期金额6*/
	@Excel(name = "逾期金额6", width = 15)
    @ApiModelProperty(value = "逾期金额6")
	private java.math.BigDecimal ageingA6;
	/**逾期最高期数*/
	@Excel(name = "逾期最高期数", width = 15)
    @ApiModelProperty(value = "逾期最高期数")
	private String mthsOdue;
	/**复利余额*/
	@Excel(name = "复利余额", width = 15)
    @ApiModelProperty(value = "复利余额")
	private java.math.BigDecimal balCmpint;
	/**当期发生退货金额*/
	@Excel(name = "当期发生退货金额", width = 15)
    @ApiModelProperty(value = "当期发生退货金额")
	private java.math.BigDecimal credVouch;
	/**帐单日*/
	@Excel(name = "帐单日", width = 15)
    @ApiModelProperty(value = "帐单日")
	private String cycleNbr;
	/**购汇功能选项*/
	@Excel(name = "购汇功能选项", width = 15)
    @ApiModelProperty(value = "购汇功能选项")
	private String exchCode1;
	/**分期付款授权金额*/
	@Excel(name = "分期付款授权金额", width = 15)
    @ApiModelProperty(value = "分期付款授权金额")
	private java.math.BigDecimal mpAuths;
	/**分期付款剩余总额,包含利息、本金和费用*/
	@Excel(name = "分期付款剩余总额,包含利息、本金和费用", width = 15)
    @ApiModelProperty(value = "分期付款剩余总额,包含利息、本金和费用")
	private java.math.BigDecimal mpBal;
	/**帐单地址栏位1*/
	@Excel(name = "帐单地址栏位1", width = 15)
    @ApiModelProperty(value = "帐单地址栏位1")
	private String address1;
	/**帐单地址栏位2*/
	@Excel(name = "帐单地址栏位2", width = 15)
    @ApiModelProperty(value = "帐单地址栏位2")
	private String address2;
	/**帐单地址栏位3*/
	@Excel(name = "帐单地址栏位3", width = 15)
    @ApiModelProperty(value = "帐单地址栏位3")
	private String address3;
	/**帐单地址栏位4*/
	@Excel(name = "帐单地址栏位4", width = 15)
    @ApiModelProperty(value = "帐单地址栏位4")
	private String address4;
	/**帐单地址栏位5*/
	@Excel(name = "帐单地址栏位5", width = 15)
    @ApiModelProperty(value = "帐单地址栏位5")
	private String address5;
	/**帐单地址邮编*/
	@Excel(name = "帐单地址邮编", width = 15)
    @ApiModelProperty(value = "帐单地址邮编")
	private String postCode;
	/**昨日分期余额*/
	@Excel(name = "昨日分期余额", width = 15)
    @ApiModelProperty(value = "昨日分期余额")
	private java.math.BigDecimal balMp;
	/**上期帐单分期余额*/
	@Excel(name = "上期帐单分期余额", width = 15)
    @ApiModelProperty(value = "上期帐单分期余额")
	private java.math.BigDecimal stmBalmp;
	/**关联还款账号1*/
	@Excel(name = "关联还款账号1", width = 15)
    @ApiModelProperty(value = "关联还款账号1")
	private String bankacctA1;
	/**关联还款账号2*/
	@Excel(name = "关联还款账号2", width = 15)
    @ApiModelProperty(value = "关联还款账号2")
	private String bankacctA2;
	/**关联还款账号3*/
	@Excel(name = "关联还款账号3", width = 15)
    @ApiModelProperty(value = "关联还款账号3")
	private String bankacctA3;
	/**关联还款账号4*/
	@Excel(name = "关联还款账号4", width = 15)
    @ApiModelProperty(value = "关联还款账号4")
	private String bankacctA4;
	/**关联账号代码1*/
	@Excel(name = "关联账号代码1", width = 15)
    @ApiModelProperty(value = "关联账号代码1")
	private String bankcodeA1;
	/**关联账号代码1*/
	@Excel(name = "关联账号代码1", width = 15)
    @ApiModelProperty(value = "关联账号代码1")
	private String bankcodeA2;
	/**关联账号代码1*/
	@Excel(name = "关联账号代码1", width = 15)
    @ApiModelProperty(value = "关联账号代码1")
	private String bankcodeA3;
	/**关联账号代码1*/
	@Excel(name = "关联账号代码1", width = 15)
    @ApiModelProperty(value = "关联账号代码1")
	private String bankcodeA4;
	/**人民币自扣还款标志*/
	@Excel(name = "人民币自扣还款标志", width = 15)
    @ApiModelProperty(value = "人民币自扣还款标志")
	@TableField(value = "REPAY_CODE_2")
	private String repayCode2;
	/**美元自扣还款标志*/
	@Excel(name = "美元自扣还款标志", width = 15)
    @ApiModelProperty(value = "美元自扣还款标志")
	private String repayCodx;
	/**购汇功能选项*/
	@Excel(name = "购汇功能选项", width = 15)
    @ApiModelProperty(value = "购汇功能选项")
	private String exchFlag;
	/**购汇金额选项*/
	@Excel(name = "购汇金额选项", width = 15)
    @ApiModelProperty(value = "购汇金额选项")
	@TableField(value = "EXCH_CODE_2")
	private String exchCode2;
	/**最近一期帐单处理日*/
	@Excel(name = "最近一期帐单处理日", width = 15)
    @ApiModelProperty(value = "最近一期帐单处理日")
	private String stmClosdy;
	/**当期调整红利积分符号*/
	@Excel(name = "当期调整红利积分符号", width = 15)
    @ApiModelProperty(value = "当期调整红利积分符号")
	@TableField(value = "POINT_ADJ_2")
	private String pointAdj2;
	/**累计总积分符号*/
	@Excel(name = "累计总积分符号", width = 15)
    @ApiModelProperty(value = "累计总积分符号")
	@TableField(value = "POINT_CUM_2")
	private String pointCum02;
	/**账户征信代码_职务*/
	@Excel(name = "账户征信代码_职务", width = 15)
    @ApiModelProperty(value = "账户征信代码_职务")
	@TableField(value = "CREDCODE_1")
	private String credcode1;
	/**账户征信代码_项目*/
	@Excel(name = "账户征信代码_项目", width = 15)
    @ApiModelProperty(value = "账户征信代码_项目")
	@TableField(value = "CREDCODE_2")
	private String credcode2;
	/**帐户标志位*/
	@Excel(name = "帐户标志位", width = 15)
    @ApiModelProperty(value = "帐户标志位")
	private String acctFlag;
	/**帐户预借现金比例*/
	@Excel(name = "帐户预借现金比例", width = 15)
    @ApiModelProperty(value = "帐户预借现金比例")
	private java.math.BigDecimal caPcnt;
	/**有无分行推荐函*/
	@Excel(name = "有无分行推荐函", width = 15)
    @ApiModelProperty(value = "有无分行推荐函")
	private String brnchIntr;
	/**分行建议额度*/
	@Excel(name = "分行建议额度", width = 15)
    @ApiModelProperty(value = "分行建议额度")
	private String brnchcrlmt;
	/**核实内容*/
	@Excel(name = "核实内容", width = 15)
    @ApiModelProperty(value = "核实内容")
	private String vrfCntnt;
	/**账户级短信费免收标志*/
	@Excel(name = "账户级短信费免收标志", width = 15)
    @ApiModelProperty(value = "账户级短信费免收标志")
	private String smsFreeyn;
	/**临时额度*/
	@Excel(name = "临时额度", width = 15)
    @ApiModelProperty(value = "临时额度")
	private String tempLimit;
	/**临时额度失效日期*/
	@Excel(name = "临时额度失效日期", width = 15)
    @ApiModelProperty(value = "临时额度失效日期")
	private String tlmtEnd;
	/**临时额度生效日期*/
	@Excel(name = "临时额度生效日期", width = 15)
    @ApiModelProperty(value = "临时额度生效日期")
	private String tlmtBeg;
	/**预借积分额度*/
	@Excel(name = "预借积分额度", width = 15)
    @ApiModelProperty(value = "预借积分额度")
	private String paLimit;
	/**已全额还款标志*/
	@Excel(name = "已全额还款标志", width = 15)
    @ApiModelProperty(value = "已全额还款标志")
	private String payFlag;
	/**应收复利*/
	@Excel(name = "应收复利", width = 15)
    @ApiModelProperty(value = "应收复利")
	private java.math.BigDecimal intChdcmp;
	/**上期应计利息*/
	@Excel(name = "上期应计利息", width = 15)
    @ApiModelProperty(value = "上期应计利息")
	private java.math.BigDecimal intNotion;
	/**当期应计利息*/
	@Excel(name = "当期应计利息", width = 15)
    @ApiModelProperty(value = "当期应计利息")
	private java.math.BigDecimal intCunot;
	/**当期应计复利*/
	@Excel(name = "当期应计复利", width = 15)
    @ApiModelProperty(value = "当期应计复利")
	private java.math.BigDecimal intCurcmp;
	/**应计复利利息*/
	@Excel(name = "应计复利利息", width = 15)
    @ApiModelProperty(value = "应计复利利息")
	private java.math.BigDecimal intCmpond;
	/**账户质押办卡/调额标志*/
	@Excel(name = "账户质押办卡/调额标志", width = 15)
    @ApiModelProperty(value = "账户质押办卡/调额标志")
	private String impFlag;
	/**不上报征信数据原因*/
	@Excel(name = "不上报征信数据原因", width = 15)
    @ApiModelProperty(value = "不上报征信数据原因")
	private String ncredRsn;
	/**监控代码*/
	@Excel(name = "监控代码", width = 15)
    @ApiModelProperty(value = "监控代码")
	private String montrCode;
	/**影子额度*/
	@Excel(name = "影子额度", width = 15)
    @ApiModelProperty(value = "影子额度")
	private String credLmt2;
	/**影子额度标志*/
	@Excel(name = "影子额度标志", width = 15)
    @ApiModelProperty(value = "影子额度标志")
	private String infoFlag;
	/**本期费用01*/
	@Excel(name = "本期费用01", width = 15)
    @ApiModelProperty(value = "本期费用01")
	private java.math.BigDecimal balNint01;
	/**本期费用02*/
	@Excel(name = "本期费用02", width = 15)
    @ApiModelProperty(value = "本期费用02")
	private java.math.BigDecimal balNint02;
	/**本期费用03*/
	@Excel(name = "本期费用03", width = 15)
    @ApiModelProperty(value = "本期费用03")
	private java.math.BigDecimal balNint03;
	/**本期费用04*/
	@Excel(name = "本期费用04", width = 15)
    @ApiModelProperty(value = "本期费用04")
	private java.math.BigDecimal balNint04;
	/**本期费用05*/
	@Excel(name = "本期费用05", width = 15)
    @ApiModelProperty(value = "本期费用05")
	private java.math.BigDecimal balNint05;
	/**本期费用06*/
	@Excel(name = "本期费用06", width = 15)
    @ApiModelProperty(value = "本期费用06")
	private java.math.BigDecimal balNint06;
	/**本期费用07*/
	@Excel(name = "本期费用07", width = 15)
    @ApiModelProperty(value = "本期费用07")
	private java.math.BigDecimal balNint07;
	/**本期费用08*/
	@Excel(name = "本期费用08", width = 15)
    @ApiModelProperty(value = "本期费用08")
	private java.math.BigDecimal balNint08;
	/**本期费用09*/
	@Excel(name = "本期费用09", width = 15)
    @ApiModelProperty(value = "本期费用09")
	private java.math.BigDecimal balNint09;
	/**本期费用10*/
	@Excel(name = "本期费用10", width = 15)
    @ApiModelProperty(value = "本期费用10")
	private java.math.BigDecimal balNint10;
	/**上期费用01*/
	@Excel(name = "上期费用01", width = 15)
    @ApiModelProperty(value = "上期费用01")
	private java.math.BigDecimal stmNint01;
	/**上期费用02*/
	@Excel(name = "上期费用02", width = 15)
    @ApiModelProperty(value = "上期费用02")
	private java.math.BigDecimal stmNint02;
	/**上期费用03*/
	@Excel(name = "上期费用03", width = 15)
    @ApiModelProperty(value = "上期费用03")
	private java.math.BigDecimal stmNint03;
	/**上期费用04*/
	@Excel(name = "上期费用04", width = 15)
    @ApiModelProperty(value = "上期费用04")
	private java.math.BigDecimal stmNint04;
	/**上期费用05*/
	@Excel(name = "上期费用05", width = 15)
    @ApiModelProperty(value = "上期费用05")
	private java.math.BigDecimal stmNint05;
	/**上期费用06*/
	@Excel(name = "上期费用06", width = 15)
    @ApiModelProperty(value = "上期费用06")
	private java.math.BigDecimal stmNint06;
	/**上期费用07*/
	@Excel(name = "上期费用07", width = 15)
    @ApiModelProperty(value = "上期费用07")
	private java.math.BigDecimal stmNint07;
	/**上期费用08*/
	@Excel(name = "上期费用08", width = 15)
    @ApiModelProperty(value = "上期费用08")
	private java.math.BigDecimal stmNint08;
	/**上期费用09*/
	@Excel(name = "上期费用09", width = 15)
    @ApiModelProperty(value = "上期费用09")
	private java.math.BigDecimal stmNint09;
	/**上期费用10*/
	@Excel(name = "上期费用10", width = 15)
    @ApiModelProperty(value = "上期费用10")
	private java.math.BigDecimal stmNint10;
	/**核销标志*/
	@Excel(name = "核销标志", width = 15)
    @ApiModelProperty(value = "核销标志")
	private String wrofFlag;
	/**核销原因代码*/
	@Excel(name = "核销原因代码", width = 15)
    @ApiModelProperty(value = "核销原因代码")
	private String canclResn;
	/**停收利息*/
	@Excel(name = "停收利息", width = 15)
    @ApiModelProperty(value = "停收利息")
	private java.math.BigDecimal shadowInt;
	/**停收复利*/
	@Excel(name = "停收复利", width = 15)
    @ApiModelProperty(value = "停收复利")
	private java.math.BigDecimal shadowCmp;
	/**停收滞纳金*/
	@Excel(name = "停收滞纳金", width = 15)
    @ApiModelProperty(value = "停收滞纳金")
	private java.math.BigDecimal shadowPen;
	/**表内转表外费用*/
	@Excel(name = "表内转表外费用", width = 15)
    @ApiModelProperty(value = "表内转表外费用")
	private java.math.BigDecimal balCmpfee;
	/**消费免息标志*/
	@Excel(name = "消费免息标志", width = 15)
    @ApiModelProperty(value = "消费免息标志")
	private String fpFlag;
	/**消费免息额度固定值*/
	@Excel(name = "消费免息额度固定值", width = 15)
    @ApiModelProperty(value = "消费免息额度固定值")
	private java.math.BigDecimal freeLimit;
	/**消费免息额度比率*/
	@Excel(name = "消费免息额度比率", width = 15)
    @ApiModelProperty(value = "消费免息额度比率")
	private java.math.BigDecimal freePcnt;
	/**消费免息额度最大值*/
	@Excel(name = "消费免息额度最大值", width = 15)
    @ApiModelProperty(value = "消费免息额度最大值")
	private java.math.BigDecimal freeMax;
	/**消费免息额度最小值*/
	@Excel(name = "消费免息额度最小值", width = 15)
    @ApiModelProperty(value = "消费免息额度最小值")
	private java.math.BigDecimal freeMin;
	/**分期剩余本金*/
	@Excel(name = "分期剩余本金", width = 15)
    @ApiModelProperty(value = "分期剩余本金")
	private java.math.BigDecimal mpRemPpl;
	/**预设临额*/
	@Excel(name = "预设临额", width = 15)
    @ApiModelProperty(value = "预设临额")
	private String credLmt3;
	/**自动分期计划相关字段*/
	@Excel(name = "自动分期计划相关字段", width = 15)
    @ApiModelProperty(value = "自动分期计划相关字段")
	private String mpautoGw;
	/**利率代码*/
	@Excel(name = "利率代码", width = 15)
    @ApiModelProperty(value = "利率代码")
	private String intCode;
	/**上次信用额度更改日期*/
	@Excel(name = "上次信用额度更改日期", width = 15)
    @ApiModelProperty(value = "上次信用额度更改日期")
	private String credChday;
	/**不可调整额度账户*/
	@Excel(name = "不可调整额度账户", width = 15)
    @ApiModelProperty(value = "不可调整额度账户")
	private String adjFlag;
	/**照会结论*/
	@Excel(name = "照会结论", width = 15)
    @ApiModelProperty(value = "照会结论")
	private String noteRst;
	/**照会原因*/
	@Excel(name = "照会原因", width = 15)
    @ApiModelProperty(value = "照会原因")
	private String noteRsn;
	/**关帐详细原因*/
	@Excel(name = "关帐详细原因", width = 15)
    @ApiModelProperty(value = "关帐详细原因")
	private String reclaCode;
	/**宽限期新增积分*/
	@Excel(name = "宽限期新增积分", width = 15)
    @ApiModelProperty(value = "宽限期新增积分")
	private String pointCum2;
	/**被折算掉积分*/
	@Excel(name = "被折算掉积分", width = 15)
    @ApiModelProperty(value = "被折算掉积分")
	private String pointRev;
	/**未结清还款金额*/
	@Excel(name = "未结清还款金额", width = 15)
    @ApiModelProperty(value = "未结清还款金额")
	private java.math.BigDecimal paymtUncl;
	/**争议代码*/
	@Excel(name = "争议代码", width = 15)
    @ApiModelProperty(value = "争议代码")
	private String queryCode;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
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
