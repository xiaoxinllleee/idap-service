package org.cmms.modules.sjxf.qtxt.kqzxt.zflywzb.entity;

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
 * @Description: 支付类业务主表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Cpps_bcpmtranjnl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cpps_bcpmtranjnl对象", description="支付类业务主表")
public class Zflywzb {

	/**平台受理日期*/
	@Excel(name = "平台受理日期", width = 15)
    @ApiModelProperty(value = "平台受理日期")
	private String workdate;
	/**平台业务序号*/
	@Excel(name = "平台业务序号", width = 15)
    @ApiModelProperty(value = "平台业务序号")
	private String workseqid;
	/**平台受理时间*/
	@Excel(name = "平台受理时间", width = 15)
    @ApiModelProperty(value = "平台受理时间")
	private String worktime;
	/**平台模板代码*/
	@Excel(name = "平台模板代码", width = 15)
    @ApiModelProperty(value = "平台模板代码")
	private String templatecode;
	/**平台交易代码*/
	@Excel(name = "平台交易代码", width = 15)
    @ApiModelProperty(value = "平台交易代码")
	private String tradecode;
	/**业务类型:1-本代本;2-本代他;3-他代本*/
	@Excel(name = "业务类型:1-本代本;2-本代他;3-他代本", width = 15)
    @ApiModelProperty(value = "业务类型:1-本代本;2-本代他;3-他代本")
	private String tradetype;
	/**系统标识*/
	@Excel(name = "系统标识", width = 15)
    @ApiModelProperty(value = "系统标识")
	private String sysid;
	/**应用标识*/
	@Excel(name = "应用标识", width = 15)
    @ApiModelProperty(value = "应用标识")
	private String appid;
	/**预留标识*/
	@Excel(name = "预留标识", width = 15)
    @ApiModelProperty(value = "预留标识")
	private String resid;
	/**业务受理日期*/
	@Excel(name = "业务受理日期", width = 15)
    @ApiModelProperty(value = "业务受理日期")
	private String busidate;
	/**业务标识*/
	@Excel(name = "业务标识", width = 15)
    @ApiModelProperty(value = "业务标识")
	private String busiflag;
	/**发起渠道代码*/
	@Excel(name = "发起渠道代码", width = 15)
    @ApiModelProperty(value = "发起渠道代码")
	private String chnlcode;
	/**发起渠道交易种类*/
	@Excel(name = "发起渠道交易种类", width = 15)
    @ApiModelProperty(value = "发起渠道交易种类")
	private String chnlprocode;
	/**发起渠道交易时间*/
	@Excel(name = "发起渠道交易时间", width = 15)
    @ApiModelProperty(value = "发起渠道交易时间")
	private String chnltrantime;
	/**发起渠道日期*/
	@Excel(name = "发起渠道日期", width = 15)
    @ApiModelProperty(value = "发起渠道日期")
	private String chnldate;
	/**发起渠道时间*/
	@Excel(name = "发起渠道时间", width = 15)
    @ApiModelProperty(value = "发起渠道时间")
	private String chnltime;
	/**发起渠道流水号*/
	@Excel(name = "发起渠道流水号", width = 15)
    @ApiModelProperty(value = "发起渠道流水号")
	private String chnlseqno;
	/**发起渠道检索参考号*/
	@Excel(name = "发起渠道检索参考号", width = 15)
    @ApiModelProperty(value = "发起渠道检索参考号")
	private String chnlrefno;
	/**操作分行号*/
	@Excel(name = "操作分行号", width = 15)
    @ApiModelProperty(value = "操作分行号")
	private String zoneno;
	/**操作网点*/
	@Excel(name = "操作网点", width = 15)
    @ApiModelProperty(value = "操作网点")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brno;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String tellerno;
	/**复核柜员*/
	@Excel(name = "复核柜员", width = 15)
    @ApiModelProperty(value = "复核柜员")
	private String chktellerno;
	/**授权柜员*/
	@Excel(name = "授权柜员", width = 15)
    @ApiModelProperty(value = "授权柜员")
	private String authtellerno;
	/**出纳员*/
	@Excel(name = "出纳员", width = 15)
    @ApiModelProperty(value = "出纳员")
	private String cashier;
	/**虚拟账户*/
	@Excel(name = "虚拟账户", width = 15)
    @ApiModelProperty(value = "虚拟账户")
	private String virtualaccno;
	/**业务受理清算行号*/
	@Excel(name = "业务受理清算行号", width = 15)
    @ApiModelProperty(value = "业务受理清算行号")
	private String bankno;
	/**来往账标志:1-往账;2-来账*/
	@Excel(name = "来往账标志:1-往账;2-来账", width = 15)
    @ApiModelProperty(value = "来往账标志:1-往账;2-来账")
	private String mbflag;
	/**借贷标识:0-非借非贷;1-借;2-贷*/
	@Excel(name = "借贷标识:0-非借非贷;1-借;2-贷", width = 15)
    @ApiModelProperty(value = "借贷标识:0-非借非贷;1-借;2-贷")
	private String dcflag;
	/**现金转账标识:1-现金;2-转账;3-内部转账*/
	@Excel(name = "现金转账标识:1-现金;2-转账;3-内部转账", width = 15)
    @ApiModelProperty(value = "现金转账标识:1-现金;2-转账;3-内部转账")
	private String cashflag;
	/**交易货币*/
	@Excel(name = "交易货币", width = 15)
    @ApiModelProperty(value = "交易货币")
	private String curcode;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String amt;
	/**实际交易金额*/
	@Excel(name = "实际交易金额", width = 15)
    @ApiModelProperty(value = "实际交易金额")
	private String realtradeamt;
	/**手续费类型*/
	@Excel(name = "手续费类型", width = 15)
    @ApiModelProperty(value = "手续费类型")
	private String feeflag;
	/**手续费金额*/
	@Excel(name = "手续费金额", width = 15)
    @ApiModelProperty(value = "手续费金额")
	private String feeamt;
	/**报文类型:即外部报文编号*/
	@Excel(name = "报文类型:即外部报文编号", width = 15)
    @ApiModelProperty(value = "报文类型:即外部报文编号")
	private String msgtype;
	/**报文标识号:外部流水号,系统跟踪号等*/
	@Excel(name = "报文标识号:外部流水号,系统跟踪号等", width = 15)
    @ApiModelProperty(value = "报文标识号:外部流水号,系统跟踪号等")
	private String msgid;
	/**交易处理码*/
	@Excel(name = "交易处理码", width = 15)
    @ApiModelProperty(value = "交易处理码")
	private String processcode;
	/**交易传输时间*/
	@Excel(name = "交易传输时间", width = 15)
    @ApiModelProperty(value = "交易传输时间")
	@TableField(value = "systime")
	private String systime;
	/**源ID*/
	@Excel(name = "源ID", width = 15)
    @ApiModelProperty(value = "源ID")
	private String sourceid;
	/**目标ID*/
	@Excel(name = "目标ID", width = 15)
    @ApiModelProperty(value = "目标ID")
	private String destid;
	/**发起方类型*/
	@Excel(name = "发起方类型", width = 15)
    @ApiModelProperty(value = "发起方类型")
	private String sendtype;
	/**发起方日期*/
	@Excel(name = "发起方日期", width = 15)
    @ApiModelProperty(value = "发起方日期")
	private String senddate;
	/**发起方时间*/
	@Excel(name = "发起方时间", width = 15)
    @ApiModelProperty(value = "发起方时间")
	private String sendtime;
	/**发起方流水号*/
	@Excel(name = "发起方流水号", width = 15)
    @ApiModelProperty(value = "发起方流水号")
	private String sendseqno;
	/**发起清算机构标识码*/
	@Excel(name = "发起清算机构标识码", width = 15)
    @ApiModelProperty(value = "发起清算机构标识码")
	private String sendclearbank;
	/**发起机构标识码*/
	@Excel(name = "发起机构标识码", width = 15)
    @ApiModelProperty(value = "发起机构标识码")
	private String sendbank;
	/**接收方日期*/
	@Excel(name = "接收方日期", width = 15)
    @ApiModelProperty(value = "接收方日期")
	private String recvdate;
	/**接收方时间*/
	@Excel(name = "接收方时间", width = 15)
    @ApiModelProperty(value = "接收方时间")
	private String recvtime;
	/**接收方流水号*/
	@Excel(name = "接收方流水号", width = 15)
    @ApiModelProperty(value = "接收方流水号")
	private String recvseqno;
	/**接收清算机构标识码*/
	@Excel(name = "接收清算机构标识码", width = 15)
    @ApiModelProperty(value = "接收清算机构标识码")
	private String recvclearbank;
	/**接收机构标识码*/
	@Excel(name = "接收机构标识码", width = 15)
    @ApiModelProperty(value = "接收机构标识码")
	private String recvbank;
	/**接收方处理状态*/
	@Excel(name = "接收方处理状态", width = 15)
    @ApiModelProperty(value = "接收方处理状态")
	private String recvstatus;
	/**接收方处理码*/
	@Excel(name = "接收方处理码", width = 15)
    @ApiModelProperty(value = "接收方处理码")
	private String recverrcode;
	/**接收方处理信息*/
	@Excel(name = "接收方处理信息", width = 15)
    @ApiModelProperty(value = "接收方处理信息")
	private String recverrmsg;
	/**账务系统受理日期*/
	@Excel(name = "账务系统受理日期", width = 15)
    @ApiModelProperty(value = "账务系统受理日期")
	private String bankdate;
	/**账务系统受理流水号*/
	@Excel(name = "账务系统受理流水号", width = 15)
    @ApiModelProperty(value = "账务系统受理流水号")
	private String bankseqno;
	/**账务系统记账日期:核心记账日期*/
	@Excel(name = "账务系统记账日期:核心记账日期", width = 15)
    @ApiModelProperty(value = "账务系统记账日期:核心记账日期")
	private String bankrspdate;
	/**账务系统记账流水号:核心记账流水号*/
	@Excel(name = "账务系统记账流水号:核心记账流水号", width = 15)
    @ApiModelProperty(value = "账务系统记账流水号:核心记账流水号")
	private String bankrspseqno;
	/**核心处理状态*/
	@Excel(name = "核心处理状态", width = 15)
    @ApiModelProperty(value = "核心处理状态")
	private String bankstatus;
	/**核心处理码*/
	@Excel(name = "核心处理码", width = 15)
    @ApiModelProperty(value = "核心处理码")
	private String bankerrcode;
	/**核心处理信息*/
	@Excel(name = "核心处理信息", width = 15)
    @ApiModelProperty(value = "核心处理信息")
	private String bankerrmsg;
	/**账务系统对账状态*/
	@Excel(name = "账务系统对账状态", width = 15)
    @ApiModelProperty(value = "账务系统对账状态")
	private String bankchkflag;
	/**账务系统冲正标志*/
	@Excel(name = "账务系统冲正标志", width = 15)
    @ApiModelProperty(value = "账务系统冲正标志")
	private String bankrevflag;
	/**核心交易码*/
	@Excel(name = "核心交易码", width = 15)
    @ApiModelProperty(value = "核心交易码")
	private String bankmsgtype;
	/**核心交易处理码*/
	@Excel(name = "核心交易处理码", width = 15)
    @ApiModelProperty(value = "核心交易处理码")
	private String bankprocode;
	/**核心受理流水序号*/
	@Excel(name = "核心受理流水序号", width = 15)
    @ApiModelProperty(value = "核心受理流水序号")
	private String bankseqid;
	/**核心记账检索参考号8583格式*/
	@Excel(name = "核心记账检索参考号8583格式", width = 15)
    @ApiModelProperty(value = "核心记账检索参考号8583格式")
	private String bankrefno;
	/**第三方交易日期*/
	@Excel(name = "第三方交易日期", width = 15)
    @ApiModelProperty(value = "第三方交易日期")
	private String corpdate;
	/**第三方交易时间*/
	@Excel(name = "第三方交易时间", width = 15)
    @ApiModelProperty(value = "第三方交易时间")
	private String corptime;
	/**第三方处理状态*/
	@Excel(name = "第三方处理状态", width = 15)
    @ApiModelProperty(value = "第三方处理状态")
	private String corpstatus;
	/**第三方处理码*/
	@Excel(name = "第三方处理码", width = 15)
    @ApiModelProperty(value = "第三方处理码")
	private String corperrcode;
	/**第三方处理信息*/
	@Excel(name = "第三方处理信息", width = 15)
    @ApiModelProperty(value = "第三方处理信息")
	private String corperrmsg;
	/**第三方对账状态*/
	@Excel(name = "第三方对账状态", width = 15)
    @ApiModelProperty(value = "第三方对账状态")
	private String corpchkflag;
	/**第三方交易传输时间*/
	@Excel(name = "第三方交易传输时间", width = 15)
    @ApiModelProperty(value = "第三方交易传输时间")
	private String corptrantime;
	/**交易当前业务步骤*/
	@Excel(name = "交易当前业务步骤", width = 15)
    @ApiModelProperty(value = "交易当前业务步骤")
	private String tradebusistep;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String busistatus;
	/**业务冲正标志*/
	@Excel(name = "业务冲正标志", width = 15)
    @ApiModelProperty(value = "业务冲正标志")
	private String revflag;
	/**主账号*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String accno;
	/**子账号*/
	@Excel(name = "子账号", width = 15)
    @ApiModelProperty(value = "子账号")
	private String subaccno;
	/**主账号名称*/
	@Excel(name = "主账号名称", width = 15)
    @ApiModelProperty(value = "主账号名称")
	private String accname;
	/**主账号实际余额*/
	@Excel(name = "主账号实际余额", width = 15)
    @ApiModelProperty(value = "主账号实际余额")
	private String accbal;
	/**主账号开户机构*/
	@Excel(name = "主账号开户机构", width = 15)
    @ApiModelProperty(value = "主账号开户机构")
	private String accbrno;
	/**卡类型:00-本行借记卡;01-本行贷记卡;99-他行卡*/
	@Excel(name = "卡类型:00-本行借记卡;01-本行贷记卡;99-他行卡", width = 15)
    @ApiModelProperty(value = "卡类型:00-本行借记卡;01-本行贷记卡;99-他行卡")
	private String cardtype;
	/**卡有效期*/
	@Excel(name = "卡有效期", width = 15)
    @ApiModelProperty(value = "卡有效期")
	private String expiredate;
	/**卡序列号*/
	@Excel(name = "卡序列号", width = 15)
    @ApiModelProperty(value = "卡序列号")
	private String cardseqid;
	/**二磁道数据*/
	@Excel(name = "二磁道数据", width = 15)
    @ApiModelProperty(value = "二磁道数据")
	private String track2;
	/**三磁道数据*/
	@Excel(name = "三磁道数据", width = 15)
    @ApiModelProperty(value = "三磁道数据")
	private String track3;
	/**转出主账号*/
	@Excel(name = "转出主账号", width = 15)
    @ApiModelProperty(value = "转出主账号")
	private String outaccno;
	/**转出子账号*/
	@Excel(name = "转出子账号", width = 15)
    @ApiModelProperty(value = "转出子账号")
	private String outsubaccno;
	/**转出主账号名称*/
	@Excel(name = "转出主账号名称", width = 15)
    @ApiModelProperty(value = "转出主账号名称")
	private String outaccname;
	/**转出主账号实际余额*/
	@Excel(name = "转出主账号实际余额", width = 15)
    @ApiModelProperty(value = "转出主账号实际余额")
	private String outaccbal;
	/**转入主账号*/
	@Excel(name = "转入主账号", width = 15)
    @ApiModelProperty(value = "转入主账号")
	private String inaccno;
	/**转入子账号*/
	@Excel(name = "转入子账号", width = 15)
    @ApiModelProperty(value = "转入子账号")
	private String insubaccno;
	/**转入主账号名称*/
	@Excel(name = "转入主账号名称", width = 15)
    @ApiModelProperty(value = "转入主账号名称")
	private String inaccname;
	/**转入主账号实际余额*/
	@Excel(name = "转入主账号实际余额", width = 15)
    @ApiModelProperty(value = "转入主账号实际余额")
	private String inaccbal;
	/**服务点进入方式*/
	@Excel(name = "服务点进入方式", width = 15)
    @ApiModelProperty(value = "服务点进入方式")
	private String inputtype;
	/**服务点条件码*/
	@Excel(name = "服务点条件码", width = 15)
    @ApiModelProperty(value = "服务点条件码")
	private String condcode;
	/**服务点PIN获取码*/
	@Excel(name = "服务点PIN获取码", width = 15)
    @ApiModelProperty(value = "服务点PIN获取码")
	private String pincode;
	/**检索参考号*/
	@Excel(name = "检索参考号", width = 15)
    @ApiModelProperty(value = "检索参考号")
	private String indexrefno;
	/**银联检索参考号*/
	@Excel(name = "银联检索参考号", width = 15)
    @ApiModelProperty(value = "银联检索参考号")
	private String corprefno;
	/**终端标识*/
	@Excel(name = "终端标识", width = 15)
    @ApiModelProperty(value = "终端标识")
	private String devid;
	/**终端类型*/
	@Excel(name = "终端类型", width = 15)
    @ApiModelProperty(value = "终端类型")
	private String devtype;
	/**终端清算日期*/
	@Excel(name = "终端清算日期", width = 15)
    @ApiModelProperty(value = "终端清算日期")
	private String devcleardate;
	/**终端状态*/
	@Excel(name = "终端状态", width = 15)
    @ApiModelProperty(value = "终端状态")
	private String devstatus;
	/**终端对账状态*/
	@Excel(name = "终端对账状态", width = 15)
    @ApiModelProperty(value = "终端对账状态")
	private String devchkflag;
	/**商户类型*/
	@Excel(name = "商户类型", width = 15)
    @ApiModelProperty(value = "商户类型")
	private String merchanttype;
	/**商户号*/
	@Excel(name = "商户号", width = 15)
    @ApiModelProperty(value = "商户号")
	private String merchantno;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String merchantname;
	/**清算日期*/
	@Excel(name = "清算日期", width = 15)
    @ApiModelProperty(value = "清算日期")
	private String cleardate;
	/**清算金额*/
	@Excel(name = "清算金额", width = 15)
    @ApiModelProperty(value = "清算金额")
	private String clearamt;
	/**清算汇率*/
	@Excel(name = "清算汇率", width = 15)
    @ApiModelProperty(value = "清算汇率")
	private String clearrate;
	/**原平台受理日期*/
	@Excel(name = "原平台受理日期", width = 15)
    @ApiModelProperty(value = "原平台受理日期")
	private String origworkdate;
	/**原平台受理序号*/
	@Excel(name = "原平台受理序号", width = 15)
    @ApiModelProperty(value = "原平台受理序号")
	private String origworkseqid;
	/**原始数据*/
	@Excel(name = "原始数据", width = 15)
    @ApiModelProperty(value = "原始数据")
	private String origdata;
	/**原报文类型*/
	@Excel(name = "原报文类型", width = 15)
    @ApiModelProperty(value = "原报文类型")
	private String origmsgtype;
	/**原报文标识号*/
	@Excel(name = "原报文标识号", width = 15)
    @ApiModelProperty(value = "原报文标识号")
	private String origmsgid;
	/**原银联交易传输时间*/
	@Excel(name = "原银联交易传输时间", width = 15)
    @ApiModelProperty(value = "原银联交易传输时间")
	private String origcorptrantime;
	/**原发起清算机构标识码*/
	@Excel(name = "原发起清算机构标识码", width = 15)
    @ApiModelProperty(value = "原发起清算机构标识码")
	private String origsendclearbank;
	/**原发起机构标识码*/
	@Excel(name = "原发起机构标识码", width = 15)
    @ApiModelProperty(value = "原发起机构标识码")
	private String origsendbank;
	/**原交易金额*/
	@Excel(name = "原交易金额", width = 15)
    @ApiModelProperty(value = "原交易金额")
	private String origamt;
	/**原交易手续费*/
	@Excel(name = "原交易手续费", width = 15)
    @ApiModelProperty(value = "原交易手续费")
	private String origfeeamt;
	/**原账务系统受理日期*/
	@Excel(name = "原账务系统受理日期", width = 15)
    @ApiModelProperty(value = "原账务系统受理日期")
	private String origbankdate;
	/**原账务系统受理流水号*/
	@Excel(name = "原账务系统受理流水号", width = 15)
    @ApiModelProperty(value = "原账务系统受理流水号")
	private String origbankseqno;
	/**响应码*/
	@Excel(name = "响应码", width = 15)
    @ApiModelProperty(value = "响应码")
	private String rspcode;
	/**响应数据*/
	@Excel(name = "响应数据", width = 15)
    @ApiModelProperty(value = "响应数据")
	private String rspdata;
	/**网络管理信息码*/
	@Excel(name = "网络管理信息码", width = 15)
    @ApiModelProperty(value = "网络管理信息码")
	private String netmanagecode;
	/**凭证类型*/
	@Excel(name = "凭证类型", width = 15)
    @ApiModelProperty(value = "凭证类型")
	private String vouchtype;
	/**凭证日期*/
	@Excel(name = "凭证日期", width = 15)
    @ApiModelProperty(value = "凭证日期")
	private String vouchdate;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String vouchno;
	/**实际余额*/
	@Excel(name = "实际余额", width = 15)
    @ApiModelProperty(value = "实际余额")
	private String actualbal;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String idtype;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String idno;
	/**授权代码*/
	@Excel(name = "授权代码", width = 15)
    @ApiModelProperty(value = "授权代码")
	private String authno;
	/**授权完成码*/
	@Excel(name = "授权完成码", width = 15)
    @ApiModelProperty(value = "授权完成码")
	private String authcode;
	/**附加数据*/
	@Excel(name = "附加数据", width = 15)
    @ApiModelProperty(value = "附加数据")
	private String adddata;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
	private String verid;
	/**交易信息*/
	@Excel(name = "交易信息", width = 15)
    @ApiModelProperty(value = "交易信息")
	private String tradeinfo;
	/**附加交易信息*/
	@Excel(name = "附加交易信息", width = 15)
    @ApiModelProperty(value = "附加交易信息")
	private String addtradeinfo;
	/**第三方预留*/
	@Excel(name = "第三方预留", width = 15)
    @ApiModelProperty(value = "第三方预留")
	private String corpreserved;
	/**受理方保留*/
	@Excel(name = "受理方保留", width = 15)
    @ApiModelProperty(value = "受理方保留")
	private String sendreserved;
	/**发卡方保留*/
	@Excel(name = "发卡方保留", width = 15)
    @ApiModelProperty(value = "发卡方保留")
	private String cardreserved;
	/**消息鉴别码MAC*/
	@Excel(name = "消息鉴别码MAC", width = 15)
    @ApiModelProperty(value = "消息鉴别码MAC")
	private String mac;
	/**BSP集群标识号*/
	@Excel(name = "BSP集群标识号", width = 15)
    @ApiModelProperty(value = "BSP集群标识号")
	private String bspno;
	/**结算标识*/
	@Excel(name = "结算标识", width = 15)
    @ApiModelProperty(value = "结算标识")
	private String chkflag;
	/**交易批次号*/
	@Excel(name = "交易批次号", width = 15)
    @ApiModelProperty(value = "交易批次号")
	private String batchnum;
	/**F60自定义域*/
	@Excel(name = "F60自定义域", width = 15)
    @ApiModelProperty(value = "F60自定义域")
	@TableField(value = "custom")
	private String custom;
	/**IC卡数据*/
	@Excel(name = "IC卡数据", width = 15)
    @ApiModelProperty(value = "IC卡数据")
	private String iccarddata;
	/**IC卡锁卡状态*/
	@Excel(name = "IC卡锁卡状态", width = 15)
    @ApiModelProperty(value = "IC卡锁卡状态")
	private String lockicstatus;
	/**打印次数*/
	@Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
	private String printcnt;
	/**平台受理日期8583格式*/
	@Excel(name = "平台受理日期8583格式", width = 15)
    @ApiModelProperty(value = "平台受理日期8583格式")
	private String date8583;
	/**平台交易传输时间8583格式*/
	@Excel(name = "平台交易传输时间8583格式", width = 15)
    @ApiModelProperty(value = "平台交易传输时间8583格式")
	private String trantime8583;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**预留1*/
	@Excel(name = "预留1", width = 15)
    @ApiModelProperty(value = "预留1")
	private String reserved1;
	/**预留2*/
	@Excel(name = "预留2", width = 15)
    @ApiModelProperty(value = "预留2")
	private String reserved2;
	/**预留3*/
	@Excel(name = "预留3", width = 15)
    @ApiModelProperty(value = "预留3")
	private String reserved3;
	/**账务系统类型*/
	@Excel(name = "账务系统类型", width = 15)
    @ApiModelProperty(value = "账务系统类型")
	private String bankhosttype;
	/**最后处理时间*/
	@Excel(name = "最后处理时间", width = 15)
    @ApiModelProperty(value = "最后处理时间")
	private String lasttime;
	/**bap请求流水号*/
	@Excel(name = "bap请求流水号", width = 15)
    @ApiModelProperty(value = "bap请求流水号")
	private String bapseqno;
	/**bsp请求流水号*/
	@Excel(name = "bsp请求流水号", width = 15)
    @ApiModelProperty(value = "bsp请求流水号")
	private String bspseqno;
	/**应答*/
	@Excel(name = "应答", width = 15)
    @ApiModelProperty(value = "应答")
	private String bspseqnoa;
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
