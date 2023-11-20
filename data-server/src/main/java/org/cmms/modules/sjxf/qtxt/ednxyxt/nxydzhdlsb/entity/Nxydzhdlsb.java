package org.cmms.modules.sjxf.qtxt.ednxyxt.nxydzhdlsb.entity;

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
 * @Description: 农信银电子汇兑流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgacs_nps_transfer")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgacs_nps_transfer对象", description="农信银电子汇兑流水表")
public class Nxydzhdlsb {
    
	/**报文编号*/
	@Excel(name = "报文编号", width = 15)
    @ApiModelProperty(value = "报文编号")
	private String packid;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String txDate;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String txTime;
	/**交易流水*/
	@Excel(name = "交易流水", width = 15)
    @ApiModelProperty(value = "交易流水")
	private Integer traceNo;
	/**发起行号*/
	@Excel(name = "发起行号", width = 15)
    @ApiModelProperty(value = "发起行号")
	private String sndNpsBrno;
	/**接收行号*/
	@Excel(name = "接收行号", width = 15)
    @ApiModelProperty(value = "接收行号")
	private String rcvNpsBrno;
	/**扣款人账号*/
	@Excel(name = "扣款人账号", width = 15)
    @ApiModelProperty(value = "扣款人账号")
	private String debitAcc;
	/**扣款户名*/
	@Excel(name = "扣款户名", width = 15)
    @ApiModelProperty(value = "扣款户名")
	private String debitName;
	/**付款账号*/
	@Excel(name = "付款账号", width = 15)
    @ApiModelProperty(value = "付款账号")
	private String payAcc;
	/**付款开户行号*/
	@Excel(name = "付款开户行号", width = 15)
    @ApiModelProperty(value = "付款开户行号")
	private String payNpsBrno;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal txAmt;
	/**发起行名*/
	@Excel(name = "发起行名", width = 15)
    @ApiModelProperty(value = "发起行名")
	private String sndNpsName;
	/**接收行名*/
	@Excel(name = "接收行名", width = 15)
    @ApiModelProperty(value = "接收行名")
	private String rcvNpsName;
	/**发送清算行号*/
	@Excel(name = "发送清算行号", width = 15)
    @ApiModelProperty(value = "发送清算行号")
	private String sndSttlBrno;
	/**接收清算行号*/
	@Excel(name = "接收清算行号", width = 15)
    @ApiModelProperty(value = "接收清算行号")
	private String rcvSttlBrno;
	/**报文标识号*/
	@Excel(name = "报文标识号", width = 15)
    @ApiModelProperty(value = "报文标识号")
	private String msgid;
	/**优先级别*/
	@Excel(name = "优先级别", width = 15)
    @ApiModelProperty(value = "优先级别")
	private String npsLevel;
	/**交易种类*/
	@Excel(name = "交易种类", width = 15)
    @ApiModelProperty(value = "交易种类")
	private String transferType;
	/**农信银日期*/
	@Excel(name = "农信银日期", width = 15)
    @ApiModelProperty(value = "农信银日期")
	private String npsDate;
	/**清算日期*/
	@Excel(name = "清算日期", width = 15)
    @ApiModelProperty(value = "清算日期")
	private String sttlDate;
	/**原交易日期（退汇时）*/
	@Excel(name = "原交易日期（退汇时）", width = 15)
    @ApiModelProperty(value = "原交易日期（退汇时）")
	private String orTxDate;
	/**原交易流水（退汇时）*/
	@Excel(name = "原交易流水（退汇时）", width = 15)
    @ApiModelProperty(value = "原交易流水（退汇时）")
	private Integer orTraceno;
	/**账务日期*/
	@Excel(name = "账务日期", width = 15)
    @ApiModelProperty(value = "账务日期")
	private String accDate;
	/**主机流水号*/
	@Excel(name = "主机流水号", width = 15)
    @ApiModelProperty(value = "主机流水号")
	private Integer accTraceno;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String cur;
	/**现转标志*/
	@Excel(name = "现转标志", width = 15)
    @ApiModelProperty(value = "现转标志")
	private String ctFlag;
	/**赔偿金金额*/
	@Excel(name = "赔偿金金额", width = 15)
    @ApiModelProperty(value = "赔偿金金额")
	private java.math.BigDecimal cpsAmt;
	/**拒付金额*/
	@Excel(name = "拒付金额", width = 15)
    @ApiModelProperty(value = "拒付金额")
	private java.math.BigDecimal rfuAmt;
	/**原托收金额*/
	@Excel(name = "原托收金额", width = 15)
    @ApiModelProperty(value = "原托收金额")
	private java.math.BigDecimal orgnlamt;
	/**支付金额*/
	@Excel(name = "支付金额", width = 15)
    @ApiModelProperty(value = "支付金额")
	private java.math.BigDecimal pmtamt;
	/**多付金额*/
	@Excel(name = "多付金额", width = 15)
    @ApiModelProperty(value = "多付金额")
	private java.math.BigDecimal oddamt;
	/**收取客户手续费*/
	@Excel(name = "收取客户手续费", width = 15)
    @ApiModelProperty(value = "收取客户手续费")
	private java.math.BigDecimal chrg;
	/**手续费标志*/
	@Excel(name = "手续费标志", width = 15)
    @ApiModelProperty(value = "手续费标志")
	private String chrgCtFlag;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String cbFlag;
	/**支取方式*/
	@Excel(name = "支取方式", width = 15)
    @ApiModelProperty(value = "支取方式")
	private String drawType;
	/**付款人地址*/
	@Excel(name = "付款人地址", width = 15)
    @ApiModelProperty(value = "付款人地址")
	private String payAddr;
	/**付款人名称*/
	@Excel(name = "付款人名称", width = 15)
    @ApiModelProperty(value = "付款人名称")
	private String payName;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String pyeAcc;
	/**收款人开户行行号*/
	@Excel(name = "收款人开户行行号", width = 15)
    @ApiModelProperty(value = "收款人开户行行号")
	private String pyeNpsBrno;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String pyeName;
	/**收款人地址*/
	@Excel(name = "收款人地址", width = 15)
    @ApiModelProperty(value = "收款人地址")
	private String pyeAddr;
	/**密押*/
	@Excel(name = "密押", width = 15)
    @ApiModelProperty(value = "密押")
	private String authWord;
	/**支付密码*/
	@Excel(name = "支付密码", width = 15)
    @ApiModelProperty(value = "支付密码")
	private String payPwd;
	/**用途*/
	@Excel(name = "用途", width = 15)
    @ApiModelProperty(value = "用途")
	private String purpose;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String attr;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private String busType;
	/**凭证种类*/
	@Excel(name = "凭证种类", width = 15)
    @ApiModelProperty(value = "凭证种类")
	private String noteType;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String noteNum;
	/**票据种类*/
	@Excel(name = "票据种类", width = 15)
    @ApiModelProperty(value = "票据种类")
	private String vouType;
	/**票据日期*/
	@Excel(name = "票据日期", width = 15)
    @ApiModelProperty(value = "票据日期")
	private String vouDate;
	/**票据号码*/
	@Excel(name = "票据号码", width = 15)
    @ApiModelProperty(value = "票据号码")
	private String vouNumber;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String idType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String idNo;
	/**退汇原因*/
	@Excel(name = "退汇原因", width = 15)
    @ApiModelProperty(value = "退汇原因")
	private String backReason;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String stat;
	/**收费单位流水号*/
	@Excel(name = "收费单位流水号", width = 15)
    @ApiModelProperty(value = "收费单位流水号")
	private String flownb;
	/**所属期间*/
	@Excel(name = "所属期间", width = 15)
    @ApiModelProperty(value = "所属期间")
	private String term;
	/**缴费类型*/
	@Excel(name = "缴费类型", width = 15)
    @ApiModelProperty(value = "缴费类型")
	private String payfeetp;
	/**收费附言*/
	@Excel(name = "收费附言", width = 15)
    @ApiModelProperty(value = "收费附言")
	private String rmk;
	/**发送落地日期*/
	@Excel(name = "发送落地日期", width = 15)
    @ApiModelProperty(value = "发送落地日期")
	private String sndDate;
	/**接收落地日期*/
	@Excel(name = "接收落地日期", width = 15)
    @ApiModelProperty(value = "接收落地日期")
	private String rcvDate;
	/**往来标志*/
	@Excel(name = "往来标志", width = 15)
    @ApiModelProperty(value = "往来标志")
	private String srFlag;
	/**对账标志*/
	@Excel(name = "对账标志", width = 15)
    @ApiModelProperty(value = "对账标志")
	private String chkFlag;
	/**中心应答码*/
	@Excel(name = "中心应答码", width = 15)
    @ApiModelProperty(value = "中心应答码")
	private String npsRespcd;
	/**主机响应码*/
	@Excel(name = "主机响应码", width = 15)
    @ApiModelProperty(value = "主机响应码")
	private String accRespcd;
	/**支付平台处理码*/
	@Excel(name = "支付平台处理码", width = 15)
    @ApiModelProperty(value = "支付平台处理码")
	private String respcd;
	/**挂账账号*/
	@Excel(name = "挂账账号", width = 15)
    @ApiModelProperty(value = "挂账账号")
	private String susAcno;
	/**交易时间HHMMSS*/
	@Excel(name = "交易时间HHMMSS", width = 15)
    @ApiModelProperty(value = "交易时间HHMMSS")
	private String sndTime;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	private String brno;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String tel;
	/**复核员*/
	@Excel(name = "复核员", width = 15)
    @ApiModelProperty(value = "复核员")
	private String chkTel;
	/**原发起行行号*/
	@Excel(name = "原发起行行号", width = 15)
    @ApiModelProperty(value = "原发起行行号")
	private String orNpsBrno;
	/**授权员*/
	@Excel(name = "授权员", width = 15)
    @ApiModelProperty(value = "授权员")
	private String authTel;
	/**二次授权员*/
	@Excel(name = "二次授权员", width = 15)
    @ApiModelProperty(value = "二次授权员")
	private String authTel2;
	/**授权标志*/
	@Excel(name = "授权标志", width = 15)
    @ApiModelProperty(value = "授权标志")
	private String authFlag;
	/**参考报文标识号*/
	@Excel(name = "参考报文标识号", width = 15)
    @ApiModelProperty(value = "参考报文标识号")
	private String orMsgid;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**原状态*/
	@Excel(name = "原状态", width = 15)
    @ApiModelProperty(value = "原状态")
	private String reservers;
	/**复核流水*/
	@Excel(name = "复核流水", width = 15)
    @ApiModelProperty(value = "复核流水")
	private Integer refTraceno;
	/**来账次数*/
	@Excel(name = "来账次数", width = 15)
    @ApiModelProperty(value = "来账次数")
	private Integer rcvTimes;
	/**预留1*/
	@Excel(name = "预留1", width = 15)
    @ApiModelProperty(value = "预留1")
	private String reserver1;
	/**挂账原因*/
	@Excel(name = "挂账原因", width = 15)
    @ApiModelProperty(value = "挂账原因")
	private String susReason;
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
