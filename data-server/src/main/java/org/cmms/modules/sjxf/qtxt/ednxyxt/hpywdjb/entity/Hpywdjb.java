package org.cmms.modules.sjxf.qtxt.ednxyxt.hpywdjb.entity;

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
 * @Description: 汇票业务登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgacs_nps_draft")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgacs_nps_draft对象", description="汇票业务登记簿")
public class Hpywdjb {
    
	/**报文编号*/
	@Excel(name = "报文编号", width = 15)
    @ApiModelProperty(value = "报文编号")
	private String packid;
	/**委托日期*/
	@Excel(name = "委托日期", width = 15)
    @ApiModelProperty(value = "委托日期")
	private String txDate;
	/**账务日期*/
	@Excel(name = "账务日期", width = 15)
    @ApiModelProperty(value = "账务日期")
	private String accDate;
	/**农信银日期*/
	@Excel(name = "农信银日期", width = 15)
    @ApiModelProperty(value = "农信银日期")
	private String npsDate;
	/**清算日期*/
	@Excel(name = "清算日期", width = 15)
    @ApiModelProperty(value = "清算日期")
	private String sttlDate;
	/**发起机构码*/
	@Excel(name = "发起机构码", width = 15)
    @ApiModelProperty(value = "发起机构码")
	private String sndBrno;
	/**发起行号*/
	@Excel(name = "发起行号", width = 15)
    @ApiModelProperty(value = "发起行号")
	private String sndNpsBrno;
	/**发起行名*/
	@Excel(name = "发起行名", width = 15)
    @ApiModelProperty(value = "发起行名")
	private String sndNpsName;
	/**发起成员行号（清算行号）*/
	@Excel(name = "发起成员行号（清算行号）", width = 15)
    @ApiModelProperty(value = "发起成员行号（清算行号）")
	private String sndSttlBrno;
	/**接收机构码(行内系统)*/
	@Excel(name = "接收机构码(行内系统)", width = 15)
    @ApiModelProperty(value = "接收机构码(行内系统)")
	private String rcvBrno;
	/**接收行号*/
	@Excel(name = "接收行号", width = 15)
    @ApiModelProperty(value = "接收行号")
	private String rcvNpsBrno;
	/**接收行名*/
	@Excel(name = "接收行名", width = 15)
    @ApiModelProperty(value = "接收行名")
	private String rcvNpsName;
	/**接收成员行号（清算行号）*/
	@Excel(name = "接收成员行号（清算行号）", width = 15)
    @ApiModelProperty(value = "接收成员行号（清算行号）")
	private String rcvSttlBrno;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private Integer traceNo;
	/**主机流水号*/
	@Excel(name = "主机流水号", width = 15)
    @ApiModelProperty(value = "主机流水号")
	private Integer accTraceNo;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String cur;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private Long txAmt;
	/**现转标志*/
	@Excel(name = "现转标志", width = 15)
    @ApiModelProperty(value = "现转标志")
	private String cfFlag;
	/**出票金额*/
	@Excel(name = "出票金额", width = 15)
    @ApiModelProperty(value = "出票金额")
	private Long draftAmt;
	/**剩余金额*/
	@Excel(name = "剩余金额", width = 15)
    @ApiModelProperty(value = "剩余金额")
	private Long remainAmt;
	/**收取客户手续费*/
	@Excel(name = "收取客户手续费", width = 15)
    @ApiModelProperty(value = "收取客户手续费")
	private Long cusChrg;
	/**手续费现转标志*/
	@Excel(name = "手续费现转标志", width = 15)
    @ApiModelProperty(value = "手续费现转标志")
	private String chrgFlag;
	/**付给NCC手续费*/
	@Excel(name = "付给NCC手续费", width = 15)
    @ApiModelProperty(value = "付给NCC手续费")
	private Long npsChrg;
	/**支取方式*/
	@Excel(name = "支取方式", width = 15)
    @ApiModelProperty(value = "支取方式")
	private String drawType;
	/**核押标志*/
	@Excel(name = "核押标志", width = 15)
    @ApiModelProperty(value = "核押标志")
	private String testFlag;
	/**付款账号*/
	@Excel(name = "付款账号", width = 15)
    @ApiModelProperty(value = "付款账号")
	private String payAcc;
	/**付款人名称*/
	@Excel(name = "付款人名称", width = 15)
    @ApiModelProperty(value = "付款人名称")
	private String payName;
	/**付款人地址*/
	@Excel(name = "付款人地址", width = 15)
    @ApiModelProperty(value = "付款人地址")
	private String payAddr;
	/**付款行号*/
	@Excel(name = "付款行号", width = 15)
    @ApiModelProperty(value = "付款行号")
	private String payNpsBrno;
	/**付款清算行行号*/
	@Excel(name = "付款清算行行号", width = 15)
    @ApiModelProperty(value = "付款清算行行号")
	private String paySttlBrno;
	/**持票人账号*/
	@Excel(name = "持票人账号", width = 15)
    @ApiModelProperty(value = "持票人账号")
	private String holdAcc;
	/**持票人名称*/
	@Excel(name = "持票人名称", width = 15)
    @ApiModelProperty(value = "持票人名称")
	private String holdName;
	/**持票人行号*/
	@Excel(name = "持票人行号", width = 15)
    @ApiModelProperty(value = "持票人行号")
	private String holdNpsBrno;
	/**卡着标志*/
	@Excel(name = "卡着标志", width = 15)
    @ApiModelProperty(value = "卡着标志")
	private String cbFlag;
	/**扣款人账号*/
	@Excel(name = "扣款人账号", width = 15)
    @ApiModelProperty(value = "扣款人账号")
	private String debitAcc;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String pyeAcc;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String pyeName;
	/**收款人地址*/
	@Excel(name = "收款人地址", width = 15)
    @ApiModelProperty(value = "收款人地址")
	private String pyeAddr;
	/**收款人开户行*/
	@Excel(name = "收款人开户行", width = 15)
    @ApiModelProperty(value = "收款人开户行")
	private String pyeNpsBrno;
	/**收款人清算行*/
	@Excel(name = "收款人清算行", width = 15)
    @ApiModelProperty(value = "收款人清算行")
	private String pyeSttlBrno;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String stat;
	/**汇票日期（票据日期）*/
	@Excel(name = "汇票日期（票据日期）", width = 15)
    @ApiModelProperty(value = "汇票日期（票据日期）")
	private String draftDate;
	/**汇票号码（票据号码）*/
	@Excel(name = "汇票号码（票据号码）", width = 15)
    @ApiModelProperty(value = "汇票号码（票据号码）")
	private String draftNo;
	/**票据版本号*/
	@Excel(name = "票据版本号", width = 15)
    @ApiModelProperty(value = "票据版本号")
	private String version;
	/**兑付方式*/
	@Excel(name = "兑付方式", width = 15)
    @ApiModelProperty(value = "兑付方式")
	private String payWay;
	/**复核发送日期*/
	@Excel(name = "复核发送日期", width = 15)
    @ApiModelProperty(value = "复核发送日期")
	private String sndDate;
	/**接收落地日期*/
	@Excel(name = "接收落地日期", width = 15)
    @ApiModelProperty(value = "接收落地日期")
	private String rcvDate;
	/**对账标志*/
	@Excel(name = "对账标志", width = 15)
    @ApiModelProperty(value = "对账标志")
	private String chkFlag;
	/**往来标志*/
	@Excel(name = "往来标志", width = 15)
    @ApiModelProperty(value = "往来标志")
	private String srFlag;
	/**密押*/
	@Excel(name = "密押", width = 15)
    @ApiModelProperty(value = "密押")
	private String seal;
	/**支付密码*/
	@Excel(name = "支付密码", width = 15)
    @ApiModelProperty(value = "支付密码")
	private String payPwd;
	/**用途*/
	@Excel(name = "用途", width = 15)
    @ApiModelProperty(value = "用途")
	private String purpose;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**主机响应码*/
	@Excel(name = "主机响应码", width = 15)
    @ApiModelProperty(value = "主机响应码")
	private String accRespcd;
	/**NCC响应码*/
	@Excel(name = "NCC响应码", width = 15)
    @ApiModelProperty(value = "NCC响应码")
	private String npsRespcd;
	/**打印次数*/
	@Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
	private Integer prtTimes;
	/**交易时间HHMMSS*/
	@Excel(name = "交易时间HHMMSS", width = 15)
    @ApiModelProperty(value = "交易时间HHMMSS")
	private String txTime;
	/**操作员(交易控制/统计用字段)*/
	@Excel(name = "操作员(交易控制/统计用字段)", width = 15)
    @ApiModelProperty(value = "操作员(交易控制/统计用字段)")
	private String tel;
	/**复核员(交易控制/统计用字段)*/
	@Excel(name = "复核员(交易控制/统计用字段)", width = 15)
    @ApiModelProperty(value = "复核员(交易控制/统计用字段)")
	private String chkTel;
	/**授权员(交易控制/统计用字段)*/
	@Excel(name = "授权员(交易控制/统计用字段)", width = 15)
    @ApiModelProperty(value = "授权员(交易控制/统计用字段)")
	private String authTel;
	/**有无第三联标志*/
	@Excel(name = "有无第三联标志", width = 15)
    @ApiModelProperty(value = "有无第三联标志")
	private String billflg;
	/**报文标识号*/
	@Excel(name = "报文标识号", width = 15)
    @ApiModelProperty(value = "报文标识号")
	private String msgid;
	/**报文标识号(针对来帐应到填写此字段)*/
	@Excel(name = "报文标识号(针对来帐应到填写此字段)", width = 15)
    @ApiModelProperty(value = "报文标识号(针对来帐应到填写此字段)")
	private String sndMsgid;
	/**报文编码(针对来帐应到填写此字段)*/
	@Excel(name = "报文编码(针对来帐应到填写此字段)", width = 15)
    @ApiModelProperty(value = "报文编码(针对来帐应到填写此字段)")
	private String sndPackid;
	/**参考报文标识号*/
	@Excel(name = "参考报文标识号", width = 15)
    @ApiModelProperty(value = "参考报文标识号")
	private String refMsgid;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private String doType;
	/**退票原因*/
	@Excel(name = "退票原因", width = 15)
    @ApiModelProperty(value = "退票原因")
	private String reason;
	/**挂账账号*/
	@Excel(name = "挂账账号", width = 15)
    @ApiModelProperty(value = "挂账账号")
	private String susAcno;
	/**复核流水号*/
	@Excel(name = "复核流水号", width = 15)
    @ApiModelProperty(value = "复核流水号")
	private Integer refTraceno;
	/**预留1*/
	@Excel(name = "预留1", width = 15)
    @ApiModelProperty(value = "预留1")
	private String reserver1;
	/**预留2*/
	@Excel(name = "预留2", width = 15)
    @ApiModelProperty(value = "预留2")
	private String reserver2;
	/**起始日期*/
	@Excel(name = "起始日期", width = 15)
    @ApiModelProperty(value = "起始日期")
	private String sDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
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
