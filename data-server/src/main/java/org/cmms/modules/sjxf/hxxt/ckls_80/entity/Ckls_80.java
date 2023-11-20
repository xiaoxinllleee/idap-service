package org.cmms.modules.sjxf.hxxt.ckls_80.entity;

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
 * @Description: 存款流水_80
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_inct_tran_80")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_inct_tran_80对象", description="存款流水_80")
public class Ckls_80 {

	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String instNo;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String acctNo;
	/**记录号*/
	@Excel(name = "记录号", width = 15)
    @ApiModelProperty(value = "记录号")
	private String recNo;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tranType;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stat;
	/**该条数据记录日期*/
	@Excel(name = "该条数据记录日期", width = 15)
    @ApiModelProperty(value = "该条数据记录日期")
	private Integer postDate;
	/**交易发生日期*/
	@Excel(name = "交易发生日期", width = 15)
    @ApiModelProperty(value = "交易发生日期")
	private Integer trnDate;
	/**系统日期*/
	@Excel(name = "系统日期", width = 15)
    @ApiModelProperty(value = "系统日期")
	private Integer systemDate;
	/**系统时间*/
	@Excel(name = "系统时间", width = 15)
    @ApiModelProperty(value = "系统时间")
	@TableField(value = "system_time")
	private Integer systemTime;
	/**柜员号和网点*/
	@Excel(name = "柜员号和网点", width = 15)
    @ApiModelProperty(value = "柜员号和网点")
	private Long tellAndBr;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private Integer jrnlNo;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private Integer trnCode;
	/**终端号*/
	@Excel(name = "终端号", width = 15)
    @ApiModelProperty(value = "终端号")
	private Integer brterm;
	/**渠道*/
	@Excel(name = "渠道", width = 15)
    @ApiModelProperty(value = "渠道")
	private String channel;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String deli;
	/**chqNoDays*/
	@Excel(name = "chqNoDays", width = 15)
    @ApiModelProperty(value = "chqNoDays")
	private String chqNoDays;
	/**amount*/
	@Excel(name = "amount", width = 15)
    @ApiModelProperty(value = "amount")
	private String amount;
	/**balance*/
	@Excel(name = "balance", width = 15)
    @ApiModelProperty(value = "balance")
	private String balance;
	/**batchNo*/
	@Excel(name = "batchNo", width = 15)
    @ApiModelProperty(value = "batchNo")
	private String batchNo;
	/**dayfileFlag*/
	@Excel(name = "dayfileFlag", width = 15)
    @ApiModelProperty(value = "dayfileFlag")
	private String dayfileFlag;
	/**correction*/
	@Excel(name = "correction", width = 15)
    @ApiModelProperty(value = "correction")
	private String correction;
	/**merchCountryCode*/
	@Excel(name = "merchCountryCode", width = 15)
    @ApiModelProperty(value = "merchCountryCode")
	private String merchCountryCode;
	/**deferDays*/
	@Excel(name = "deferDays", width = 15)
    @ApiModelProperty(value = "deferDays")
	private String deferDays;
	/**miscDrCrInd*/
	@Excel(name = "miscDrCrInd", width = 15)
    @ApiModelProperty(value = "miscDrCrInd")
	private String miscDrCrInd;
	/**repostTime*/
	@Excel(name = "repostTime", width = 15)
    @ApiModelProperty(value = "repostTime")
	private String repostTime;
	/**postSeq*/
	@Excel(name = "postSeq", width = 15)
    @ApiModelProperty(value = "postSeq")
	private String postSeq;
	/**chqType*/
	@Excel(name = "chqType", width = 15)
    @ApiModelProperty(value = "chqType")
	private String chqType;
	/**txnInd*/
	@Excel(name = "txnInd", width = 15)
    @ApiModelProperty(value = "txnInd")
	private String txnInd;
	/**tranSystem*/
	@Excel(name = "tranSystem", width = 15)
    @ApiModelProperty(value = "tranSystem")
	private String tranSystem;
	/**tranDesc*/
	@Excel(name = "tranDesc", width = 15)
    @ApiModelProperty(value = "tranDesc")
	private String tranDesc;
	/**tranAcct*/
	@Excel(name = "tranAcct", width = 15)
    @ApiModelProperty(value = "tranAcct")
	private String tranAcct;
	/**virtualAcct*/
	@Excel(name = "virtualAcct", width = 15)
    @ApiModelProperty(value = "virtualAcct")
	private String virtualAcct;
	/**thisDepCnt*/
	@Excel(name = "thisDepCnt", width = 15)
    @ApiModelProperty(value = "thisDepCnt")
	private String thisDepCnt;
	/**brkRuleindBefimg*/
	@Excel(name = "brkRuleindBefimg", width = 15)
    @ApiModelProperty(value = "brkRuleindBefimg")
	private String brkRuleindBefimg;
	/**updCurrBalInd*/
	@Excel(name = "updCurrBalInd", width = 15)
    @ApiModelProperty(value = "updCurrBalInd")
	private String updCurrBalInd;
	/**subAcctType*/
	@Excel(name = "subAcctType", width = 15)
    @ApiModelProperty(value = "subAcctType")
	private String subAcctType;
	/**volume*/
	@Excel(name = "volume", width = 15)
    @ApiModelProperty(value = "volume")
	private String volume;
	/**sequNum*/
	@Excel(name = "sequNum", width = 15)
    @ApiModelProperty(value = "sequNum")
	private String sequNum;
	/**withdrMeth*/
	@Excel(name = "withdrMeth", width = 15)
    @ApiModelProperty(value = "withdrMeth")
	private String withdrMeth;
	/**cloNoticeNum*/
	@Excel(name = "cloNoticeNum", width = 15)
    @ApiModelProperty(value = "cloNoticeNum")
	private String cloNoticeNum;
	/**noticeNum*/
	@Excel(name = "noticeNum", width = 15)
    @ApiModelProperty(value = "noticeNum")
	private String noticeNum;
	/**eduInd*/
	@Excel(name = "eduInd", width = 15)
    @ApiModelProperty(value = "eduInd")
	private String eduInd;
	/**maturityFlg*/
	@Excel(name = "maturityFlg", width = 15)
    @ApiModelProperty(value = "maturityFlg")
	private String maturityFlg;
	/**lostDte*/
	@Excel(name = "lostDte", width = 15)
    @ApiModelProperty(value = "lostDte")
	private Integer lostDte;
	/**lostLogNum*/
	@Excel(name = "lostLogNum", width = 15)
    @ApiModelProperty(value = "lostLogNum")
	private String lostLogNum;
	/**filler*/
	@Excel(name = "filler", width = 15)
    @ApiModelProperty(value = "filler")
	private String filler;
	/**issueDate*/
	@Excel(name = "issueDate", width = 15)
    @ApiModelProperty(value = "issueDate")
	private Integer issueDate;
	/**delayIntDays*/
	@Excel(name = "delayIntDays", width = 15)
    @ApiModelProperty(value = "delayIntDays")
	private Integer delayIntDays;
	/**trfInActTyp*/
	@Excel(name = "trfInActTyp", width = 15)
    @ApiModelProperty(value = "trfInActTyp")
	private String trfInActTyp;
	/**crSettleIntReason*/
	@Excel(name = "crSettleIntReason", width = 15)
    @ApiModelProperty(value = "crSettleIntReason")
	private String crSettleIntReason;
	/**crChangeToSuspClo*/
	@Excel(name = "crChangeToSuspClo", width = 15)
    @ApiModelProperty(value = "crChangeToSuspClo")
	private String crChangeToSuspClo;
	/**curr*/
	@Excel(name = "curr", width = 15)
    @ApiModelProperty(value = "curr")
	private String curr;
	/**promoCode*/
	@Excel(name = "promoCode", width = 15)
    @ApiModelProperty(value = "promoCode")
	private String promoCode;
	/**cshAnalyNum*/
	@Excel(name = "cshAnalyNum", width = 15)
    @ApiModelProperty(value = "cshAnalyNum")
	private String cshAnalyNum;
	/**filler1*/
	@Excel(name = "filler1", width = 15)
    @ApiModelProperty(value = "filler1")
	@TableField(value = "filler_1")
	private String filler1;
	/**origJrnl*/
	@Excel(name = "origJrnl", width = 15)
    @ApiModelProperty(value = "origJrnl")
	private String origJrnl;
	/**supervisorid*/
	@Excel(name = "supervisorid", width = 15)
    @ApiModelProperty(value = "supervisorid")
	private String supervisorid;
	/**clientContact*/
	@Excel(name = "clientContact", width = 15)
    @ApiModelProperty(value = "clientContact")
	private String clientContact;
	/**pwdlIntAmount*/
	@Excel(name = "pwdlIntAmount", width = 15)
    @ApiModelProperty(value = "pwdlIntAmount")
	private String pwdlIntAmount;
	/**filler2*/
	@Excel(name = "filler2", width = 15)
    @ApiModelProperty(value = "filler2")
	@TableField(value = "filler_2")
	private String filler2;
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
}
