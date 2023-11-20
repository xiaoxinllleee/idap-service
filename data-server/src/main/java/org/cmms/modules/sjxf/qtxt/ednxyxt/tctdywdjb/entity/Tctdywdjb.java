package org.cmms.modules.sjxf.qtxt.ednxyxt.tctdywdjb.entity;

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
 * @Description: 通存通兑业务登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgacs_nps_realtran")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgacs_nps_realtran对象", description="通存通兑业务登记簿")
public class Tctdywdjb {
    
	/**报文编号*/
	@Excel(name = "报文编号", width = 15)
    @ApiModelProperty(value = "报文编号")
	private String packid;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String prtry;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private String busType;
	/**委托日期*/
	@Excel(name = "委托日期", width = 15)
    @ApiModelProperty(value = "委托日期")
	private String txDate;
	/**农信银日期*/
	@Excel(name = "农信银日期", width = 15)
    @ApiModelProperty(value = "农信银日期")
	private String npsDate;
	/**发送时间*/
	@Excel(name = "发送时间", width = 15)
    @ApiModelProperty(value = "发送时间")
	private String sndTime;
	/**账务日期*/
	@Excel(name = "账务日期", width = 15)
    @ApiModelProperty(value = "账务日期")
	private String accDate;
	/**清算日期*/
	@Excel(name = "清算日期", width = 15)
    @ApiModelProperty(value = "清算日期")
	private String sttlDate;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brno;
	/**发起行号*/
	@Excel(name = "发起行号", width = 15)
    @ApiModelProperty(value = "发起行号")
	private String sndNpsBrno;
	/**发起行名*/
	@Excel(name = "发起行名", width = 15)
    @ApiModelProperty(value = "发起行名")
	private String sndNpsName;
	/**发起成员行号*/
	@Excel(name = "发起成员行号", width = 15)
    @ApiModelProperty(value = "发起成员行号")
	private String sndSttlBrno;
	/**接收行号*/
	@Excel(name = "接收行号", width = 15)
    @ApiModelProperty(value = "接收行号")
	private String rcvNpsBrno;
	/**接受行名*/
	@Excel(name = "接受行名", width = 15)
    @ApiModelProperty(value = "接受行名")
	private String rcvNpsName;
	/**接收成员行号*/
	@Excel(name = "接收成员行号", width = 15)
    @ApiModelProperty(value = "接收成员行号")
	private String rcvSttlBrno;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private Integer traceNo;
	/**原交易流水号*/
	@Excel(name = "原交易流水号", width = 15)
    @ApiModelProperty(value = "原交易流水号")
	private Integer orTraceNo;
	/**主机流水号*/
	@Excel(name = "主机流水号", width = 15)
    @ApiModelProperty(value = "主机流水号")
	private Integer accTraceNo;
	/**止付编号*/
	@Excel(name = "止付编号", width = 15)
    @ApiModelProperty(value = "止付编号")
	private String stopNo;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String cur;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private Long amt;
	/**现转标志*/
	@Excel(name = "现转标志", width = 15)
    @ApiModelProperty(value = "现转标志")
	private String ctFlag;
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
	/**付款人账号*/
	@Excel(name = "付款人账号", width = 15)
    @ApiModelProperty(value = "付款人账号")
	private String payAcc;
	/**付款账户类型*/
	@Excel(name = "付款账户类型", width = 15)
    @ApiModelProperty(value = "付款账户类型")
	private String payAccType;
	/**付款人名称*/
	@Excel(name = "付款人名称", width = 15)
    @ApiModelProperty(value = "付款人名称")
	private String payName;
	/**付款人开户行行号*/
	@Excel(name = "付款人开户行行号", width = 15)
    @ApiModelProperty(value = "付款人开户行行号")
	private String payOpnBrno;
	/**付款行行号*/
	@Excel(name = "付款行行号", width = 15)
    @ApiModelProperty(value = "付款行行号")
	private String payNpsBrno;
	/**付款人开户清算行*/
	@Excel(name = "付款人开户清算行", width = 15)
    @ApiModelProperty(value = "付款人开户清算行")
	private String paySttlBrno;
	/**付款人地址*/
	@Excel(name = "付款人地址", width = 15)
    @ApiModelProperty(value = "付款人地址")
	private String payAddr;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String pyeAcc;
	/**收款账户类型*/
	@Excel(name = "收款账户类型", width = 15)
    @ApiModelProperty(value = "收款账户类型")
	private String pyeAccType;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String pyeName;
	/**收款人地址*/
	@Excel(name = "收款人地址", width = 15)
    @ApiModelProperty(value = "收款人地址")
	private String pyeAddr;
	/**收款人开户行行号*/
	@Excel(name = "收款人开户行行号", width = 15)
    @ApiModelProperty(value = "收款人开户行行号")
	private String pyeOpnBrno;
	/**收款行行号*/
	@Excel(name = "收款行行号", width = 15)
    @ApiModelProperty(value = "收款行行号")
	private String pyeNpsBrno;
	/**收款人开户清算行*/
	@Excel(name = "收款人开户清算行", width = 15)
    @ApiModelProperty(value = "收款人开户清算行")
	private String pyeSttlBrno;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String certType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String certNo;
	/**付款人凭证号码*/
	@Excel(name = "付款人凭证号码", width = 15)
    @ApiModelProperty(value = "付款人凭证号码")
	private String payVouNo;
	/**存折余额*/
	@Excel(name = "存折余额", width = 15)
    @ApiModelProperty(value = "存折余额")
	private Long payVouBal;
	/**收款人凭证号码*/
	@Excel(name = "收款人凭证号码", width = 15)
    @ApiModelProperty(value = "收款人凭证号码")
	private String pyeVouNo;
	/**存折余额*/
	@Excel(name = "存折余额", width = 15)
    @ApiModelProperty(value = "存折余额")
	private Long pyeVouBal;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String ps;
	/**附言*/
	@Excel(name = "附言", width = 15)
    @ApiModelProperty(value = "附言")
	private String remark;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String stat;
	/**存款确认标志*/
	@Excel(name = "存款确认标志", width = 15)
    @ApiModelProperty(value = "存款确认标志")
	private String affiFlag;
	/**往来标志*/
	@Excel(name = "往来标志", width = 15)
    @ApiModelProperty(value = "往来标志")
	private String srFlag;
	/**对账标志*/
	@Excel(name = "对账标志", width = 15)
    @ApiModelProperty(value = "对账标志")
	private String chkFlag;
	/**处理码*/
	@Excel(name = "处理码", width = 15)
    @ApiModelProperty(value = "处理码")
	private String respcd;
	/**主机响应码*/
	@Excel(name = "主机响应码", width = 15)
    @ApiModelProperty(value = "主机响应码")
	private String accRespcd;
	/**挂账账号*/
	@Excel(name = "挂账账号", width = 15)
    @ApiModelProperty(value = "挂账账号")
	private String susAcno;
	/**NCC响应码*/
	@Excel(name = "NCC响应码", width = 15)
    @ApiModelProperty(value = "NCC响应码")
	private String npsRespcd;
	/**回执业务应答码*/
	@Excel(name = "回执业务应答码", width = 15)
    @ApiModelProperty(value = "回执业务应答码")
	private String rcvRespcd;
	/**拒绝码*/
	@Excel(name = "拒绝码", width = 15)
    @ApiModelProperty(value = "拒绝码")
	private String rejRespcd;
	/**拒绝信息*/
	@Excel(name = "拒绝信息", width = 15)
    @ApiModelProperty(value = "拒绝信息")
	private String rejReason;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String txTime;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String tel;
	/**复核员*/
	@Excel(name = "复核员", width = 15)
    @ApiModelProperty(value = "复核员")
	private String chkTel;
	/**授权员*/
	@Excel(name = "授权员", width = 15)
    @ApiModelProperty(value = "授权员")
	private String authTel;
	/**报文标识号*/
	@Excel(name = "报文标识号", width = 15)
    @ApiModelProperty(value = "报文标识号")
	private String msgid;
	/**发送报文标识号*/
	@Excel(name = "发送报文标识号", width = 15)
    @ApiModelProperty(value = "发送报文标识号")
	private String sndMsgid;
	/**发送报文编号*/
	@Excel(name = "发送报文编号", width = 15)
    @ApiModelProperty(value = "发送报文编号")
	private String sndPackid;
	/**参考报文标识号*/
	@Excel(name = "参考报文标识号", width = 15)
    @ApiModelProperty(value = "参考报文标识号")
	private String refMsgid;
	/**明细标识号*/
	@Excel(name = "明细标识号", width = 15)
    @ApiModelProperty(value = "明细标识号")
	private String txid;
	/**端到端标识号*/
	@Excel(name = "端到端标识号", width = 15)
    @ApiModelProperty(value = "端到端标识号")
	private String endid;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15)
    @ApiModelProperty(value = "渠道类型")
	private String channel;
	/**预留*/
	@Excel(name = "预留", width = 15)
    @ApiModelProperty(value = "预留")
	private String reservers;
	/**卡序列号*/
	@Excel(name = "卡序列号", width = 15)
    @ApiModelProperty(value = "卡序列号")
	private String cardseqid;
	/**存款冲销记录原状态*/
	@Excel(name = "存款冲销记录原状态", width = 15)
    @ApiModelProperty(value = "存款冲销记录原状态")
	private String sreserve;
	/**保留*/
	@Excel(name = "保留", width = 15)
    @ApiModelProperty(value = "保留")
	private Long dreserve;
	/**预留1*/
	@Excel(name = "预留1", width = 15)
    @ApiModelProperty(value = "预留1")
	private String reserver1;
	/**扣款人账号*/
	@Excel(name = "扣款人账号", width = 15)
    @ApiModelProperty(value = "扣款人账号")
	private String debitAcc;
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
