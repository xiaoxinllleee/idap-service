package org.cmms.modules.sjxf.hxxt.qkmxdj.entity;

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
 * @Description: 欠款明细登记
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_rpyv")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_rpyv对象", description="欠款明细登记")
public class Qkmxdj {
    
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	private String instNo;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String acctNo;
	/**循环号码*/
	@Excel(name = "循环号码", width = 15)
    @ApiModelProperty(value = "循环号码")
	private String cycleNo;
	/**拖欠表的期数*/
	@Excel(name = "拖欠表的期数", width = 15)
    @ApiModelProperty(value = "拖欠表的期数")
	private String termNo;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private Integer bucketSeqNo;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txnDate;
	/**此记录当前的状态*/
	@Excel(name = "此记录当前的状态", width = 15)
    @ApiModelProperty(value = "此记录当前的状态")
	private String recordStatus;
	/**交易标识*/
	@Excel(name = "交易标识", width = 15)
    @ApiModelProperty(value = "交易标识")
	private String txnInd;
	/**调整原因码*/
	@Excel(name = "调整原因码", width = 15)
    @ApiModelProperty(value = "调整原因码")
	private String adjReasonCode;
	/**入账日期*/
	@Excel(name = "入账日期", width = 15)
    @ApiModelProperty(value = "入账日期")
	private Integer postingDate;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private Integer jrnlNo;
	/**倒退日调整金额*/
	@Excel(name = "倒退日调整金额", width = 15)
    @ApiModelProperty(value = "倒退日调整金额")
	private java.math.BigDecimal backAdjAmt;
	/**是否有因为还款重算了还款金额*/
	@Excel(name = "是否有因为还款重算了还款金额", width = 15)
    @ApiModelProperty(value = "是否有因为还款重算了还款金额")
	private String repayPrnRecalc;
	/**标识延期的计提利息*/
	@Excel(name = "标识延期的计提利息", width = 15)
    @ApiModelProperty(value = "标识延期的计提利息")
	private java.math.BigDecimal deferIntAccr;
	/**欠款桶子代码1*/
	@Excel(name = "欠款桶子代码1", width = 15)
    @ApiModelProperty(value = "欠款桶子代码1")
	private String bucketCode1;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode1;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt1;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt1;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt1;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate1;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays1;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate1;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate1;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt1;
	/**欠款桶子代码2*/
	@Excel(name = "欠款桶子代码2", width = 15)
    @ApiModelProperty(value = "欠款桶子代码2")
	private String bucketCode2;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode2;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt2;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt2;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt2;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate2;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays2;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate2;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate2;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt2;
	/**欠款桶子代码3*/
	@Excel(name = "欠款桶子代码3", width = 15)
    @ApiModelProperty(value = "欠款桶子代码3")
	private String bucketCode3;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode3;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt3;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt3;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt3;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate3;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays3;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate3;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate3;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt3;
	/**欠款桶子代码4*/
	@Excel(name = "欠款桶子代码4", width = 15)
    @ApiModelProperty(value = "欠款桶子代码4")
	private String bucketCode4;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode4;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt4;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt4;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt4;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate4;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays4;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate4;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate4;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt4;
	/**欠款桶子代码5*/
	@Excel(name = "欠款桶子代码5", width = 15)
    @ApiModelProperty(value = "欠款桶子代码5")
	private String bucketCode5;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode5;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt5;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt5;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt5;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate5;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays5;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate5;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate5;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt5;
	/**欠款桶子代码6*/
	@Excel(name = "欠款桶子代码6", width = 15)
    @ApiModelProperty(value = "欠款桶子代码6")
	private String bucketCode6;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode6;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt6;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt6;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt6;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate6;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays6;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate6;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate6;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt6;
	/**欠款桶子代码7*/
	@Excel(name = "欠款桶子代码7", width = 15)
    @ApiModelProperty(value = "欠款桶子代码7")
	private String bucketCode7;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode7;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt7;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt7;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt7;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate7;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays7;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate7;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate7;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt7;
	/**欠款桶子代码8*/
	@Excel(name = "欠款桶子代码8", width = 15)
    @ApiModelProperty(value = "欠款桶子代码8")
	private String bucketCode8;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode8;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt8;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt8;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt8;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate8;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays8;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate8;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate8;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt8;
	/**欠款桶子代码9*/
	@Excel(name = "欠款桶子代码9", width = 15)
    @ApiModelProperty(value = "欠款桶子代码9")
	private String bucketCode9;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode9;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt9;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt9;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt9;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate9;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays9;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate9;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate9;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt9;
	/**欠款桶子代码10*/
	@Excel(name = "欠款桶子代码10", width = 15)
    @ApiModelProperty(value = "欠款桶子代码10")
	private String bucketCode10;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode10;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt10;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt10;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt10;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate10;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays10;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate10;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate10;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt10;
	/**欠款桶子代码11*/
	@Excel(name = "欠款桶子代码11", width = 15)
    @ApiModelProperty(value = "欠款桶子代码11")
	private String bucketCode11;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode11;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt11;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt11;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt11;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate11;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays11;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate11;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate11;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt11;
	/**欠款桶子代码12*/
	@Excel(name = "欠款桶子代码12", width = 15)
    @ApiModelProperty(value = "欠款桶子代码12")
	private String bucketCode12;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode12;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt12;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt12;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt12;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate12;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays12;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate12;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate12;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt12;
	/**欠款桶子代码13*/
	@Excel(name = "欠款桶子代码13", width = 15)
    @ApiModelProperty(value = "欠款桶子代码13")
	private String bucketCode13;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode13;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt13;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt13;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt13;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate13;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays13;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate13;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate13;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt13;
	/**欠款桶子代码14*/
	@Excel(name = "欠款桶子代码14", width = 15)
    @ApiModelProperty(value = "欠款桶子代码14")
	private String bucketCode14;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode14;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt14;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt14;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt14;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate14;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays14;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate14;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate14;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt14;
	/**欠款桶子代码15*/
	@Excel(name = "欠款桶子代码15", width = 15)
    @ApiModelProperty(value = "欠款桶子代码15")
	private String bucketCode15;
	/**桶子子码*/
	@Excel(name = "桶子子码", width = 15)
    @ApiModelProperty(value = "桶子子码")
	private String subBucketCode15;
	/**应还金额*/
	@Excel(name = "应还金额", width = 15)
    @ApiModelProperty(value = "应还金额")
	private java.math.BigDecimal txnAmt15;
	/**已还金额*/
	@Excel(name = "已还金额", width = 15)
    @ApiModelProperty(value = "已还金额")
	private java.math.BigDecimal collectedAmt15;
	/**调整金额*/
	@Excel(name = "调整金额", width = 15)
    @ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustedAmt15;
	/**当前的基础利率*/
	@Excel(name = "当前的基础利率", width = 15)
    @ApiModelProperty(value = "当前的基础利率")
	private java.math.BigDecimal basedRate15;
	/**计算日期天数*/
	@Excel(name = "计算日期天数", width = 15)
    @ApiModelProperty(value = "计算日期天数")
	private Integer basedDays15;
	/**上一还款日，开始日*/
	@Excel(name = "上一还款日，开始日", width = 15)
    @ApiModelProperty(value = "上一还款日，开始日")
	private Integer basedDate15;
	/**交易当天日期，截止日*/
	@Excel(name = "交易当天日期，截止日", width = 15)
    @ApiModelProperty(value = "交易当天日期，截止日")
	private Integer basedToDate15;
	/**当前的本金金额*/
	@Excel(name = "当前的本金金额", width = 15)
    @ApiModelProperty(value = "当前的本金金额")
	private java.math.BigDecimal basedAmt15;
	/**最后维护日期*/
	@Excel(name = "最后维护日期", width = 15)
    @ApiModelProperty(value = "最后维护日期")
	private String lstFinUpdDt;
	/**最后维护类型U-更新I-新增*/
	@Excel(name = "最后维护类型U-更新I-新增", width = 15)
    @ApiModelProperty(value = "最后维护类型U-更新I-新增")
	private String lstFinStat;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
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
