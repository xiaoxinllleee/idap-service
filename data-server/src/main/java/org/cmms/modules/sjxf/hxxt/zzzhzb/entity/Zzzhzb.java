package org.cmms.modules.sjxf.hxxt.zzzhzb.entity;

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
 * @Description: 总账账户主表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbs_gldm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_gldm对象", description="总账账户主表")
public class Zzzhzb {

	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	@TableField(value = "KEY_1")
	private String key1;
	/**表明最近的GLDM记录通常值为0*/
	@Excel(name = "表明最近的GLDM记录通常值为0", width = 15)
    @ApiModelProperty(value = "表明最近的GLDM记录通常值为0")
	private String gldvst;
	/**该字段现在不用值始终为0*/
	@Excel(name = "该字段现在不用值始终为0", width = 15)
    @ApiModelProperty(value = "该字段现在不用值始终为0")
	private String noTrans;
	/**系统名此处值为GEN*/
	@Excel(name = "系统名此处值为GEN", width = 15)
    @ApiModelProperty(value = "系统名此处值为GEN")
	private String syst;
	/**内部帐所属分行*/
	@Excel(name = "内部帐所属分行", width = 15)
    @ApiModelProperty(value = "内部帐所属分行")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String branch;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String status;
	/**产品类型*/
	@Excel(name = "产品类型", width = 15)
    @ApiModelProperty(value = "产品类型")
	private String prodType;
	/**产品子类*/
	@Excel(name = "产品子类", width = 15)
    @ApiModelProperty(value = "产品子类")
	private String prodCat;
	/**供未来使用*/
	@Excel(name = "供未来使用", width = 15)
    @ApiModelProperty(value = "供未来使用")
	private Integer relationship;
	/**供未来使用*/
	@Excel(name = "供未来使用", width = 15)
    @ApiModelProperty(value = "供未来使用")
	private Integer allocation;
	/**40位长账户名供查询使用*/
	@Excel(name = "40位长账户名供查询使用", width = 15)
    @ApiModelProperty(value = "40位长账户名供查询使用")
	private String ledgerName;
	/**30位长其他语言账户名供查询使用*/
	@Excel(name = "30位长其他语言账户名供查询使用", width = 15)
    @ApiModelProperty(value = "30位长其他语言账户名供查询使用")
	private String othCurrLedgName;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currency;
	/**当前账户余额*/
	@Excel(name = "当前账户余额", width = 15)
    @ApiModelProperty(value = "当前账户余额")
	private java.math.BigDecimal cumCurrVal;
	/**开户余额通常为零*/
	@Excel(name = "开户余额通常为零", width = 15)
    @ApiModelProperty(value = "开户余额通常为零")
	private java.math.BigDecimal openVal;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_01")
	private java.math.BigDecimal monthVal01;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_02")
	private java.math.BigDecimal monthVal02;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_03")
	private java.math.BigDecimal monthVal03;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_04")
	private java.math.BigDecimal monthVal04;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_05")
	private java.math.BigDecimal monthVal05;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_06")
	private java.math.BigDecimal monthVal06;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_07")
	private java.math.BigDecimal monthVal07;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_08")
	private java.math.BigDecimal monthVal08;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_09")
	private java.math.BigDecimal monthVal09;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_10")
	private java.math.BigDecimal monthVal10;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_11")
	private java.math.BigDecimal monthVal11;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_12")
	private java.math.BigDecimal monthVal12;
	/**月实际余额*/
	@Excel(name = "月实际余额", width = 15)
    @ApiModelProperty(value = "月实际余额")
	@TableField(value = "month_val_13")
	private java.math.BigDecimal monthVal13;
	/**累计预算金额*/
	@Excel(name = "累计预算金额", width = 15)
    @ApiModelProperty(value = "累计预算金额")
	private java.math.BigDecimal bdgtCumVal;
	/**预算起始金额*/
	@Excel(name = "预算起始金额", width = 15)
    @ApiModelProperty(value = "预算起始金额")
	private java.math.BigDecimal bdgtOpenVal;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_01")
	private java.math.BigDecimal bdgtMonthVal01;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_02")
	private java.math.BigDecimal bdgtMonthVal02;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_03")
	private java.math.BigDecimal bdgtMonthVal03;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_04")
	private java.math.BigDecimal bdgtMonthVal04;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_05")
	private java.math.BigDecimal bdgtMonthVal05;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_06")
	private java.math.BigDecimal bdgtMonthVal06;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_07")
	private java.math.BigDecimal bdgtMonthVal07;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_08")
	private java.math.BigDecimal bdgtMonthVal08;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_09")
	private java.math.BigDecimal bdgtMonthVal09;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_10")
	private java.math.BigDecimal bdgtMonthVal10;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_11")
	private java.math.BigDecimal bdgtMonthVal11;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_12")
	private java.math.BigDecimal bdgtMonthVal12;
	/**月预算余额*/
	@Excel(name = "月预算余额", width = 15)
    @ApiModelProperty(value = "月预算余额")
	@TableField(value = "bdgt_month_val_13")
	private java.math.BigDecimal bdgtMonthVal13;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private Integer jrnlseq;
	/**项目号当前未用*/
	@Excel(name = "项目号当前未用", width = 15)
    @ApiModelProperty(value = "项目号当前未用")
	private String projCode;
	/**上一本汇票前缀*/
	@Excel(name = "上一本汇票前缀", width = 15)
    @ApiModelProperty(value = "上一本汇票前缀")
	private String lastChqAlpha;
	/**上一本汇票号码*/
	@Excel(name = "上一本汇票号码", width = 15)
    @ApiModelProperty(value = "上一本汇票号码")
	private String lastChqSeq;
	/**本账户签发本汇票数量当前未用*/
	@Excel(name = "本账户签发本汇票数量当前未用", width = 15)
    @ApiModelProperty(value = "本账户签发本汇票数量当前未用")
	private Integer chqsIssTday;
	/**本账户签发本汇票总金额当前未用*/
	@Excel(name = "本账户签发本汇票总金额当前未用", width = 15)
    @ApiModelProperty(value = "本账户签发本汇票总金额当前未用")
	private java.math.BigDecimal chqsIssTdayAmt;
	/**本账户已解付本汇票数量目前未用*/
	@Excel(name = "本账户已解付本汇票数量目前未用", width = 15)
    @ApiModelProperty(value = "本账户已解付本汇票数量目前未用")
	private Integer chqsPaidTday;
	/**本账户已解付本汇票金额目前未用*/
	@Excel(name = "本账户已解付本汇票金额目前未用", width = 15)
    @ApiModelProperty(value = "本账户已解付本汇票金额目前未用")
	private java.math.BigDecimal chqsPaidTdayAmt;
	/**本账户未结清本汇票数量目前未用*/
	@Excel(name = "本账户未结清本汇票数量目前未用", width = 15)
    @ApiModelProperty(value = "本账户未结清本汇票数量目前未用")
	private Integer unclChqs;
	/**本账户已冻结本汇票数量目前未用*/
	@Excel(name = "本账户已冻结本汇票数量目前未用", width = 15)
    @ApiModelProperty(value = "本账户已冻结本汇票数量目前未用")
	private Integer stoppedChqs;
	/**未记录的跨货币交易总金额通常为0目前未用*/
	@Excel(name = "未记录的跨货币交易总金额通常为0目前未用", width = 15)
    @ApiModelProperty(value = "未记录的跨货币交易总金额通常为0目前未用")
	private java.math.BigDecimal crossCurrVal;
	/**未记录的跨分行交易总金额通常为0目前未用*/
	@Excel(name = "未记录的跨分行交易总金额通常为0目前未用", width = 15)
    @ApiModelProperty(value = "未记录的跨分行交易总金额通常为0目前未用")
	private java.math.BigDecimal interBrVal;
	/**非交易跨货币金额通常为0目前未用*/
	@Excel(name = "非交易跨货币金额通常为0目前未用", width = 15)
    @ApiModelProperty(value = "非交易跨货币金额通常为0目前未用")
	private java.math.BigDecimal nonTxCrCurrVal;
	/**本账户已冻结本汇票金额目前未用*/
	@Excel(name = "本账户已冻结本汇票金额目前未用", width = 15)
    @ApiModelProperty(value = "本账户已冻结本汇票金额目前未用")
	private java.math.BigDecimal chqsStoppedAmt;
	/**状态最后修改日期*/
	@Excel(name = "状态最后修改日期", width = 15)
    @ApiModelProperty(value = "状态最后修改日期")
	private Integer lastStatDate;
	/**安全校验标志位*/
	@Excel(name = "安全校验标志位", width = 15)
    @ApiModelProperty(value = "安全校验标志位")
	private String secAccessInd;
	/**特殊账户标识*/
	@Excel(name = "特殊账户标识", width = 15)
    @ApiModelProperty(value = "特殊账户标识")
	private String specialAcctInd;
	/**该账户已取消本汇票数量目前不用*/
	@Excel(name = "该账户已取消本汇票数量目前不用", width = 15)
    @ApiModelProperty(value = "该账户已取消本汇票数量目前不用")
	private Integer chqsCancelledCtr;
	/**该账户已取消本汇票金额目前不用*/
	@Excel(name = "该账户已取消本汇票金额目前不用", width = 15)
    @ApiModelProperty(value = "该账户已取消本汇票金额目前不用")
	private java.math.BigDecimal chqsCancelledAmt;
	/**上一支票顺序号*/
	@Excel(name = "上一支票顺序号", width = 15)
    @ApiModelProperty(value = "上一支票顺序号")
	private Integer lastChqDupNo;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String customerNo;
	/**对账单产生频率产品层对账单产生频率*/
	@Excel(name = "对账单产生频率产品层对账单产生频率", width = 15)
    @ApiModelProperty(value = "对账单产生频率产品层对账单产生频率")
	private String stmntFreq;
	/**对账单格式*/
	@Excel(name = "对账单格式", width = 15)
    @ApiModelProperty(value = "对账单格式")
	private String stmntForm;
	/**对账单日期历法*/
	@Excel(name = "对账单日期历法", width = 15)
    @ApiModelProperty(value = "对账单日期历法")
	private String stmntDateIndic;
	/**对账单借方总金额*/
	@Excel(name = "对账单借方总金额", width = 15)
    @ApiModelProperty(value = "对账单借方总金额")
	private java.math.BigDecimal totDr;
	/**对账单贷方总金额*/
	@Excel(name = "对账单贷方总金额", width = 15)
    @ApiModelProperty(value = "对账单贷方总金额")
	private java.math.BigDecimal totCr;
	/**供未来使用*/
	@Excel(name = "供未来使用", width = 15)
    @ApiModelProperty(value = "供未来使用")
	private String consolidateFlag;
	/**账户上一交易会计日*/
	@Excel(name = "账户上一交易会计日", width = 15)
    @ApiModelProperty(value = "账户上一交易会计日")
	private Integer lstFinDt;
	/**账户对账单数量*/
	@Excel(name = "账户对账单数量", width = 15)
    @ApiModelProperty(value = "账户对账单数量")
	private Integer noOfStmnt;
	/**账户未发送电子邮件数*/
	@Excel(name = "账户未发送电子邮件数", width = 15)
    @ApiModelProperty(value = "账户未发送电子邮件数")
	private Integer msgsPending;
	/**账户总电子邮件数*/
	@Excel(name = "账户总电子邮件数", width = 15)
    @ApiModelProperty(value = "账户总电子邮件数")
	private Integer noOfMessages;
	/**对账单周期*/
	@Excel(name = "对账单周期", width = 15)
    @ApiModelProperty(value = "对账单周期")
	private String statementCycle;
	/**对账单日期*/
	@Excel(name = "对账单日期", width = 15)
    @ApiModelProperty(value = "对账单日期")
	private String statementDay;
	/**账户开户日期*/
	@Excel(name = "账户开户日期", width = 15)
    @ApiModelProperty(value = "账户开户日期")
	private Integer acctOpenDate;
	/**GLPP产品参数中的ProductRestriction32种的转账限制*/
	@Excel(name = "GLPP产品参数中的ProductRestriction32种的转账限制", width = 15)
    @ApiModelProperty(value = "GLPP产品参数中的ProductRestriction32种的转账限制")
	private String postingMask;
	/**32种报告限制目前不用*/
	@Excel(name = "32种报告限制目前不用", width = 15)
    @ApiModelProperty(value = "32种报告限制目前不用")
	private String reportMask;
	/**GLCCGL分类代码代表了客户在企业总帐控制的账户*/
	@Excel(name = "GLCCGL分类代码代表了客户在企业总帐控制的账户", width = 15)
    @ApiModelProperty(value = "GLCCGL分类代码代表了客户在企业总帐控制的账户")
	private String glClassCode;
	/**段码原用于公司总帐出报表时区分GL类型现不用*/
	@Excel(name = "段码原用于公司总帐出报表时区分GL类型现不用", width = 15)
    @ApiModelProperty(value = "段码原用于公司总帐出报表时区分GL类型现不用")
	private String glSegment;
	/**对账单份数*/
	@Excel(name = "对账单份数", width = 15)
    @ApiModelProperty(value = "对账单份数")
	private String statementCopies;
	/**上一次产品类型修改日期*/
	@Excel(name = "上一次产品类型修改日期", width = 15)
    @ApiModelProperty(value = "上一次产品类型修改日期")
	private Integer lastAcctTypeChg;
	/**该账户是否涉及预约转账*/
	@Excel(name = "该账户是否涉及预约转账", width = 15)
    @ApiModelProperty(value = "该账户是否涉及预约转账")
	private String sweepAcctFlag;
	/**是否更新外汇头寸账户*/
	@Excel(name = "是否更新外汇头寸账户", width = 15)
    @ApiModelProperty(value = "是否更新外汇头寸账户")
	private String exchgPosnFlag;
	/**该账户最早的倒退日交易日期*/
	@Excel(name = "该账户最早的倒退日交易日期", width = 15)
    @ApiModelProperty(value = "该账户最早的倒退日交易日期")
	private Integer oldestBdateDate;
	/**销帐码标识*/
	@Excel(name = "销帐码标识", width = 15)
    @ApiModelProperty(value = "销帐码标识")
	private String srnInd;
	/**销帐码借贷标识*/
	@Excel(name = "销帐码借贷标识", width = 15)
    @ApiModelProperty(value = "销帐码借贷标识")
	private String srnCrdrInd;
	/**预约转账标识*/
	@Excel(name = "预约转账标识", width = 15)
    @ApiModelProperty(value = "预约转账标识")
	private String sweepInd;
	/**推迟转账日预约转账时推迟的费用上划天数*/
	@Excel(name = "推迟转账日预约转账时推迟的费用上划天数", width = 15)
    @ApiModelProperty(value = "推迟转账日预约转账时推迟的费用上划天数")
	private String delayDays;
	/**预约转账的转入账户*/
	@Excel(name = "预约转账的转入账户", width = 15)
    @ApiModelProperty(value = "预约转账的转入账户")
	private String motherAcctno;
	/**指明向母账户中上划资金的方式*/
	@Excel(name = "指明向母账户中上划资金的方式", width = 15)
    @ApiModelProperty(value = "指明向母账户中上划资金的方式")
	private String pushPostingFlag;
	/**交易用借贷标识*/
	@Excel(name = "交易用借贷标识", width = 15)
    @ApiModelProperty(value = "交易用借贷标识")
	private String drCrFlag;
	/**交易总借方金额*/
	@Excel(name = "交易总借方金额", width = 15)
    @ApiModelProperty(value = "交易总借方金额")
	private java.math.BigDecimal cshadvTotDr;
	/**交易总贷方金额*/
	@Excel(name = "交易总贷方金额", width = 15)
    @ApiModelProperty(value = "交易总贷方金额")
	private java.math.BigDecimal cshadvTotCr;
	/**收益代码从前用于出报表统计*/
	@Excel(name = "收益代码从前用于出报表统计", width = 15)
    @ApiModelProperty(value = "收益代码从前用于出报表统计")
	private String profitCode;
	/**关户标识用于关户交易*/
	@Excel(name = "关户标识用于关户交易", width = 15)
    @ApiModelProperty(value = "关户标识用于关户交易")
	private String closureFlag;
	/**库存限额目前仅存储开户页面传来的值无相应处理*/
	@Excel(name = "库存限额目前仅存储开户页面传来的值无相应处理", width = 15)
    @ApiModelProperty(value = "库存限额目前仅存储开户页面传来的值无相应处理")
	private java.math.BigDecimal vaultLimitAmt;
	/**免税种类用于免除该账户的特定费用*/
	@Excel(name = "免税种类用于免除该账户的特定费用", width = 15)
    @ApiModelProperty(value = "免税种类用于免除该账户的特定费用")
	private Integer chrExemProfile;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_01")
	private Integer chrCnt01;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_01")
	private java.math.BigDecimal chrVal01;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_02")
	private Integer chrCnt02;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_02")
	private java.math.BigDecimal chrVal02;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_03")
	private Integer chrCnt03;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_03")
	private java.math.BigDecimal chrVal03;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_04")
	private Integer chrCnt04;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_04")
	private java.math.BigDecimal chrVal04;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_05")
	private Integer chrCnt05;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_05")
	private java.math.BigDecimal chrVal05;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_06")
	private Integer chrCnt06;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_06")
	private java.math.BigDecimal chrVal06;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_07")
	private Integer chrCnt07;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_07")
	private java.math.BigDecimal chrVal07;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_08")
	private Integer chrCnt08;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_08")
	private java.math.BigDecimal chrVal08;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_09")
	private Integer chrCnt09;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_09")
	private java.math.BigDecimal chrVal09;
	/**收费次数*/
	@Excel(name = "收费次数", width = 15)
    @ApiModelProperty(value = "收费次数")
	@TableField(value = "chr_cnt_10")
	private Integer chrCnt10;
	/**收费金额*/
	@Excel(name = "收费金额", width = 15)
    @ApiModelProperty(value = "收费金额")
	@TableField(value = "chr_val_10")
	private java.math.BigDecimal chrVal10;
	/**是否允许bancslink柜员访问的产品*/
	@Excel(name = "是否允许bancslink柜员访问的产品", width = 15)
    @ApiModelProperty(value = "是否允许bancslink柜员访问的产品")
	private String allowBl;
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
