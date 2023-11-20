package org.cmms.modules.sjxf.qtxt.tipsxt.jyls.entity;

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
 * @Description: TIPS交易流水
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ibus_tipmtaxjnl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_tipmtaxjnl对象", description="TIPS交易流水")
public class Jyls {
    
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
	/**系统标识*/
	@Excel(name = "系统标识", width = 15)
    @ApiModelProperty(value = "系统标识")
	private String sysid;
	/**应用标识*/
	@Excel(name = "应用标识", width = 15)
    @ApiModelProperty(value = "应用标识")
	private String appid;
	/**预留系统标识*/
	@Excel(name = "预留系统标识", width = 15)
    @ApiModelProperty(value = "预留系统标识")
	private String resid;
	/**发起渠道代码*/
	@Excel(name = "发起渠道代码", width = 15)
    @ApiModelProperty(value = "发起渠道代码")
	private String chnlcode;
	/**发起渠道日期*/
	@Excel(name = "发起渠道日期", width = 15)
    @ApiModelProperty(value = "发起渠道日期")
	private String chnldate;
	/**发起渠道流水号*/
	@Excel(name = "发起渠道流水号", width = 15)
    @ApiModelProperty(value = "发起渠道流水号")
	private String chnlseqno;
	/**操作网点*/
	@Excel(name = "操作网点", width = 15)
    @ApiModelProperty(value = "操作网点")
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
	/**操作终端号*/
	@Excel(name = "操作终端号", width = 15)
    @ApiModelProperty(value = "操作终端号")
	private String terminalno;
	/**来往账标志*/
	@Excel(name = "来往账标志", width = 15)
    @ApiModelProperty(value = "来往账标志")
	private String mbflag;
	/**商业银行行号*/
	@Excel(name = "商业银行行号", width = 15)
    @ApiModelProperty(value = "商业银行行号")
	private String bankno;
	/**委托日期*/
	@Excel(name = "委托日期", width = 15)
    @ApiModelProperty(value = "委托日期")
	private String busidate;
	/**应答报文标识号*/
	@Excel(name = "应答报文标识号", width = 15)
    @ApiModelProperty(value = "应答报文标识号")
	private String rspmsgid;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String busistatus;
	/**交易当前业务步骤*/
	@Excel(name = "交易当前业务步骤", width = 15)
    @ApiModelProperty(value = "交易当前业务步骤")
	private String tradebusistep;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
	private String ver;
	/**发起节点代码*/
	@Excel(name = "发起节点代码", width = 15)
    @ApiModelProperty(value = "发起节点代码")
	private String src;
	/**接收节点代码*/
	@Excel(name = "接收节点代码", width = 15)
    @ApiModelProperty(value = "接收节点代码")
	private String des;
	/**应用名称*/
	@Excel(name = "应用名称", width = 15)
    @ApiModelProperty(value = "应用名称")
	private String app;
	/**报文编号*/
	@Excel(name = "报文编号", width = 15)
    @ApiModelProperty(value = "报文编号")
	private String msgtype;
	/**报文标识号*/
	@Excel(name = "报文标识号", width = 15)
    @ApiModelProperty(value = "报文标识号")
	private String msgid;
	/**报文参考号*/
	@Excel(name = "报文参考号", width = 15)
    @ApiModelProperty(value = "报文参考号")
	private String msgref;
	/**工作日期*/
	@Excel(name = "工作日期", width = 15)
    @ApiModelProperty(value = "工作日期")
	private String cleardate;
	/**预留字段*/
	@Excel(name = "预留字段", width = 15)
    @ApiModelProperty(value = "预留字段")
	private String reserve;
	/**实时批量标志*/
	@Excel(name = "实时批量标志", width = 15)
    @ApiModelProperty(value = "实时批量标志")
	private String realflag;
	/**征收机关代码*/
	@Excel(name = "征收机关代码", width = 15)
    @ApiModelProperty(value = "征收机关代码")
	private String taxorgcode;
	/**包流水号*/
	@Excel(name = "包流水号", width = 15)
    @ApiModelProperty(value = "包流水号")
	private String packno;
	/**交易流水号/申报序号*/
	@Excel(name = "交易流水号/申报序号", width = 15)
    @ApiModelProperty(value = "交易流水号/申报序号")
	private String trano;
	/**外部申报电子序号*/
	@Excel(name = "外部申报电子序号", width = 15)
    @ApiModelProperty(value = "外部申报电子序号")
	private String outerlevyno;
	/**申报状态*/
	@Excel(name = "申报状态", width = 15)
    @ApiModelProperty(value = "申报状态")
	private String levystatus;
	/**经收类别*/
	@Excel(name = "经收类别", width = 15)
    @ApiModelProperty(value = "经收类别")
	private String handletype;
	/**收款行行号*/
	@Excel(name = "收款行行号", width = 15)
    @ApiModelProperty(value = "收款行行号")
	private String payeebankno;
	/**收款单位代码*/
	@Excel(name = "收款单位代码", width = 15)
    @ApiModelProperty(value = "收款单位代码")
	private String payeeorgcode;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String payeeacct;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String payeename;
	/**付款行行号*/
	@Excel(name = "付款行行号", width = 15)
    @ApiModelProperty(value = "付款行行号")
	private String paybkcode;
	/**付款开户行行号*/
	@Excel(name = "付款开户行行号", width = 15)
    @ApiModelProperty(value = "付款开户行行号")
	private String payopbkcode;
	/**付款账户*/
	@Excel(name = "付款账户", width = 15)
    @ApiModelProperty(value = "付款账户")
	private String payacct;
	/**缴款单位名称*/
	@Excel(name = "缴款单位名称", width = 15)
    @ApiModelProperty(value = "缴款单位名称")
	private String handorgname;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String traamt;
	/**税票号码/费票号码*/
	@Excel(name = "税票号码/费票号码", width = 15)
    @ApiModelProperty(value = "税票号码/费票号码")
	private String taxvouno;
	/**税种/险种条数*/
	@Excel(name = "税种/险种条数", width = 15)
    @ApiModelProperty(value = "税种/险种条数")
	private String taxtypenum;
	/**开票日期*/
	@Excel(name = "开票日期", width = 15)
    @ApiModelProperty(value = "开票日期")
	private String billdate;
	/**税务登记号*/
	@Excel(name = "税务登记号", width = 15)
    @ApiModelProperty(value = "税务登记号")
	private String taxregistno;
	/**纳税人代码*/
	@Excel(name = "纳税人代码", width = 15)
    @ApiModelProperty(value = "纳税人代码")
	private String taxpaycode;
	/**纳税人名称/缴费单位名称*/
	@Excel(name = "纳税人名称/缴费单位名称", width = 15)
    @ApiModelProperty(value = "纳税人名称/缴费单位名称")
	private String taxpayname;
	/**协议书号*/
	@Excel(name = "协议书号", width = 15)
    @ApiModelProperty(value = "协议书号")
	private String protocolno;
	/**国库代码*/
	@Excel(name = "国库代码", width = 15)
    @ApiModelProperty(value = "国库代码")
	private String trecode;
	/**企业代码*/
	@Excel(name = "企业代码", width = 15)
    @ApiModelProperty(value = "企业代码")
	private String corpcode;
	/**预算种类*/
	@Excel(name = "预算种类", width = 15)
    @ApiModelProperty(value = "预算种类")
	private String budgettype;
	/**整理期标志*/
	@Excel(name = "整理期标志", width = 15)
    @ApiModelProperty(value = "整理期标志")
	private String trimsign;
	/**企业注册类型*/
	@Excel(name = "企业注册类型", width = 15)
    @ApiModelProperty(value = "企业注册类型")
	private String corptype;
	/**打印付款凭证标志*/
	@Excel(name = "打印付款凭证标志", width = 15)
    @ApiModelProperty(value = "打印付款凭证标志")
	private String printvousign;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**回执处理结果*/
	@Excel(name = "回执处理结果", width = 15)
    @ApiModelProperty(value = "回执处理结果")
	private String result;
	/**回执附言*/
	@Excel(name = "回执附言", width = 15)
    @ApiModelProperty(value = "回执附言")
	private String addword;
	/**扣税日期/扣费日期*/
	@Excel(name = "扣税日期/扣费日期", width = 15)
    @ApiModelProperty(value = "扣税日期/扣费日期")
	private String taxdate;
	/**扣税/扣费处理结果*/
	@Excel(name = "扣税/扣费处理结果", width = 15)
    @ApiModelProperty(value = "扣税/扣费处理结果")
	private String payresult;
	/**扣税/扣费附言*/
	@Excel(name = "扣税/扣费附言", width = 15)
    @ApiModelProperty(value = "扣税/扣费附言")
	private String payaddword;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String accbrno;
	/**账务系统受理日期*/
	@Excel(name = "账务系统受理日期", width = 15)
    @ApiModelProperty(value = "账务系统受理日期")
	private String bankdate;
	/**账务系统受理时间*/
	@Excel(name = "账务系统受理时间", width = 15)
    @ApiModelProperty(value = "账务系统受理时间")
	private String banktime;
	/**账务系统受理流水号*/
	@Excel(name = "账务系统受理流水号", width = 15)
    @ApiModelProperty(value = "账务系统受理流水号")
	private String bankseqno;
	/**账务系统记账日期*/
	@Excel(name = "账务系统记账日期", width = 15)
    @ApiModelProperty(value = "账务系统记账日期")
	private String bankrspdate;
	/**账务系统记账流水号*/
	@Excel(name = "账务系统记账流水号", width = 15)
    @ApiModelProperty(value = "账务系统记账流水号")
	private String bankrspseqno;
	/**账务系统处理状态*/
	@Excel(name = "账务系统处理状态", width = 15)
    @ApiModelProperty(value = "账务系统处理状态")
	private String bankstatus;
	/**账务系统处理错误码*/
	@Excel(name = "账务系统处理错误码", width = 15)
    @ApiModelProperty(value = "账务系统处理错误码")
	private String bankerrcode;
	/**账务系统处理错误信息*/
	@Excel(name = "账务系统处理错误信息", width = 15)
    @ApiModelProperty(value = "账务系统处理错误信息")
	private String bankerrmsg;
	/**账务系统对账状态*/
	@Excel(name = "账务系统对账状态", width = 15)
    @ApiModelProperty(value = "账务系统对账状态")
	private String bankchkflag;
	/**对账类型*/
	@Excel(name = "对账类型", width = 15)
    @ApiModelProperty(value = "对账类型")
	private String chkaccttype;
	/**对账日期*/
	@Excel(name = "对账日期", width = 15)
    @ApiModelProperty(value = "对账日期")
	private String chkdate;
	/**对账批次*/
	@Excel(name = "对账批次", width = 15)
    @ApiModelProperty(value = "对账批次")
	private String chkacctord;
	/**对账状态*/
	@Excel(name = "对账状态", width = 15)
    @ApiModelProperty(value = "对账状态")
	private String chkflag;
	/**撤销标志*/
	@Excel(name = "撤销标志", width = 15)
    @ApiModelProperty(value = "撤销标志")
	private String backflag;
	/**撤销流水号*/
	@Excel(name = "撤销流水号", width = 15)
    @ApiModelProperty(value = "撤销流水号")
	private String backno;
	/**撤销日期*/
	@Excel(name = "撤销日期", width = 15)
    @ApiModelProperty(value = "撤销日期")
	private String backdate;
	/**撤销原因*/
	@Excel(name = "撤销原因", width = 15)
    @ApiModelProperty(value = "撤销原因")
	private String backreason;
	/**冲正标志*/
	@Excel(name = "冲正标志", width = 15)
    @ApiModelProperty(value = "冲正标志")
	private String revflag;
	/**冲正申请序号*/
	@Excel(name = "冲正申请序号", width = 15)
    @ApiModelProperty(value = "冲正申请序号")
	private String cancelno;
	/**冲正原因*/
	@Excel(name = "冲正原因", width = 15)
    @ApiModelProperty(value = "冲正原因")
	private String cancelreason;
	/**MQID*/
	@Excel(name = "MQID", width = 15)
    @ApiModelProperty(value = "MQID")
	private String mqid;
	/**打印次数*/
	@Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
	private String printcnt;
	/**数据来源*/
	@Excel(name = "数据来源", width = 15)
    @ApiModelProperty(value = "数据来源")
	private String datasource;
	/**对账处理标志*/
	@Excel(name = "对账处理标志", width = 15)
    @ApiModelProperty(value = "对账处理标志")
	private String dealflag;
	/**预留字段1*/
	@Excel(name = "预留字段1", width = 15)
    @ApiModelProperty(value = "预留字段1")
	private String reserved1;
	/**预留字段2*/
	@Excel(name = "预留字段2", width = 15)
    @ApiModelProperty(value = "预留字段2")
	private String reserved2;
	/**预留字段3*/
	@Excel(name = "预留字段3", width = 15)
    @ApiModelProperty(value = "预留字段3")
	private String reserved3;
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
