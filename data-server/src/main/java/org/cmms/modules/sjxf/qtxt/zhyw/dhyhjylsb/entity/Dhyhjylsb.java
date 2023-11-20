package org.cmms.modules.sjxf.qtxt.zhyw.dhyhjylsb.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 电话银行交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Fbuss_tel_trade_log")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Fbuss_tel_trade_log对象", description="电话银行交易流水表")
public class Dhyhjylsb {
    
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String tradedate;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String tradetime;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String seqno;
	/**内部交易码*/
	@Excel(name = "内部交易码", width = 15)
    @ApiModelProperty(value = "内部交易码")
	private String tradecode;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String busitype;
	/**渠道代码*/
	@Excel(name = "渠道代码", width = 15)
    @ApiModelProperty(value = "渠道代码")
	private String chnlcode;
	/**渠道流水*/
	@Excel(name = "渠道流水", width = 15)
    @ApiModelProperty(value = "渠道流水")
	private String chnlseqno;
	/**渠道日期*/
	@Excel(name = "渠道日期", width = 15)
    @ApiModelProperty(value = "渠道日期")
	private String chnldate;
	/**渠道时间*/
	@Excel(name = "渠道时间", width = 15)
    @ApiModelProperty(value = "渠道时间")
	private String chnltime;
	/**交易柜员号*/
	@Excel(name = "交易柜员号", width = 15)
    @ApiModelProperty(value = "交易柜员号")
	private String trantellerno;
	/**交易机构代码*/
	@Excel(name = "交易机构代码", width = 15)
    @ApiModelProperty(value = "交易机构代码")
	private String tranbranchid;
	/**终端号*/
	@Excel(name = "终端号", width = 15)
    @ApiModelProperty(value = "终端号")
	private String terminalcode;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String cardno;
	/**金额*/
	@Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
	private String amt;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String tradestate;
	/**返回码*/
	@Excel(name = "返回码", width = 15)
    @ApiModelProperty(value = "返回码")
	private String retcode;
	/**返回信息*/
	@Excel(name = "返回信息", width = 15)
    @ApiModelProperty(value = "返回信息")
	private String retmsg;
	/**平台日志ID*/
	@Excel(name = "平台日志ID", width = 15)
    @ApiModelProperty(value = "平台日志ID")
	private String logid;
	/**核心/行内返回码*/
	@Excel(name = "核心/行内返回码", width = 15)
    @ApiModelProperty(value = "核心/行内返回码")
	private String hostRetcode;
	/**核心/行内返回信息*/
	@Excel(name = "核心/行内返回信息", width = 15)
    @ApiModelProperty(value = "核心/行内返回信息")
	private String hostRetmsg;
	/**错误码*/
	@Excel(name = "错误码", width = 15)
    @ApiModelProperty(value = "错误码")
	private String errorcode;
	/**错误信息*/
	@Excel(name = "错误信息", width = 15)
    @ApiModelProperty(value = "错误信息")
	private String errormsg;
	/**原交易码*/
	@Excel(name = "原交易码", width = 15)
    @ApiModelProperty(value = "原交易码")
	private String orgtradecode;
	/**预留字段1*/
	@Excel(name = "预留字段1", width = 15)
    @ApiModelProperty(value = "预留字段1")
	private String reservedfield1;
	/**预留字段2*/
	@Excel(name = "预留字段2", width = 15)
    @ApiModelProperty(value = "预留字段2")
	private String reservedfield2;
	/**预留字段3*/
	@Excel(name = "预留字段3", width = 15)
    @ApiModelProperty(value = "预留字段3")
	private String reservedfield3;
	/**预留字段4*/
	@Excel(name = "预留字段4", width = 15)
    @ApiModelProperty(value = "预留字段4")
	private String reservedfield4;
	/**核心流水号*/
	@Excel(name = "核心流水号", width = 15)
    @ApiModelProperty(value = "核心流水号")
	private String hostseqno;
	/**核心uuid*/
	@Excel(name = "核心uuid", width = 15)
    @ApiModelProperty(value = "核心uuid")
	private String uuid;
	/**交易开始时间*/
	@Excel(name = "交易开始时间", width = 15)
    @ApiModelProperty(value = "交易开始时间")
	private String startTime;
	/**交易结束时间*/
	@Excel(name = "交易结束时间", width = 15)
    @ApiModelProperty(value = "交易结束时间")
	private String endTime;
	/**备份字段1*/
	@Excel(name = "备份字段1", width = 15)
    @ApiModelProperty(value = "备份字段1")
	private String bak1;
	/**备份字段2*/
	@Excel(name = "备份字段2", width = 15)
    @ApiModelProperty(value = "备份字段2")
	private String bak2;
	/**flag*/
	@Excel(name = "flag", width = 15)
    @ApiModelProperty(value = "flag")
	private String flag;
	/**sysflag*/
	@Excel(name = "sysflag", width = 15)
    @ApiModelProperty(value = "sysflag")
	private String sysflag;
	/**tradeseq*/
	@Excel(name = "tradeseq", width = 15)
    @ApiModelProperty(value = "tradeseq")
	private Long tradeseq;
	/**currsysseqno*/
	@Excel(name = "currsysseqno", width = 15)
    @ApiModelProperty(value = "currsysseqno")
	private String currsysseqno;
	/**uuid2*/
	@Excel(name = "uuid2", width = 15)
    @ApiModelProperty(value = "uuid2")
	private String uuid2;
	/**currsysseqno2*/
	@Excel(name = "currsysseqno2", width = 15)
    @ApiModelProperty(value = "currsysseqno2")
	private String currsysseqno2;
	/**hostseqno2*/
	@Excel(name = "hostseqno2", width = 15)
    @ApiModelProperty(value = "hostseqno2")
	private String hostseqno2;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
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
