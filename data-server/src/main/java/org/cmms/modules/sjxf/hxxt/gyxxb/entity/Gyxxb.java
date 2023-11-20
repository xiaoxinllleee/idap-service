package org.cmms.modules.sjxf.hxxt.gyxxb.entity;

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
 * @Description: 柜员信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_telm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_telm对象", description="柜员信息表")
public class Gyxxb {
    
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String socNo;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String tellerNo;
	/**主银行号*/
	@Excel(name = "主银行号", width = 15)
    @ApiModelProperty(value = "主银行号")
	private String primInst;
	/**最近的消息序号*/
	@Excel(name = "最近的消息序号", width = 15)
    @ApiModelProperty(value = "最近的消息序号")
	private String lastMsgSeq;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String brchNo;
	/**主机构号*/
	@Excel(name = "主机构号", width = 15)
    @ApiModelProperty(value = "主机构号")
	private String primBranch;
	/**终端号*/
	@Excel(name = "终端号", width = 15)
    @ApiModelProperty(value = "终端号")
	private String termInBranch;
	/**最近签到机构*/
	@Excel(name = "最近签到机构", width = 15)
    @ApiModelProperty(value = "最近签到机构")
	private String lastBranchSignon;
	/**银行内的终端号*/
	@Excel(name = "银行内的终端号", width = 15)
    @ApiModelProperty(value = "银行内的终端号")
	private String termNo;
	/**终端类型 0–分行柜员终端类型 1–ATM终端类型*/
	@Excel(name = "终端类型 0–分行柜员终端类型 1–ATM终端类型", width = 15)
    @ApiModelProperty(value = "终端类型 0–分行柜员终端类型 1–ATM终端类型")
	private String termType;
	/**签到日期*/
	@Excel(name = "签到日期", width = 15)
    @ApiModelProperty(value = "签到日期")
	private String signOnDate;
	/**二进制日期*/
	@Excel(name = "二进制日期", width = 15)
    @ApiModelProperty(value = "二进制日期")
	private Integer signOnDateBin;
	/**记账日期*/
	@Excel(name = "记账日期", width = 15)
    @ApiModelProperty(value = "记账日期")
	private Integer postDate;
	/**签到标志*/
	@Excel(name = "签到标志", width = 15)
    @ApiModelProperty(value = "签到标志")
	private String signonFlag;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stat;
	/**语言代码*/
	@Excel(name = "语言代码", width = 15)
    @ApiModelProperty(value = "语言代码")
	private String preferredLang;
	/**能力级别*/
	@Excel(name = "能力级别", width = 15)
    @ApiModelProperty(value = "能力级别")
	private String capable;
	/**主级别*/
	@Excel(name = "主级别", width = 15)
    @ApiModelProperty(value = "主级别")
	private String primCap;
	/**组别*/
	@Excel(name = "组别", width = 15)
    @ApiModelProperty(value = "组别")
	private String grpNo;
	/**主组别*/
	@Excel(name = "主组别", width = 15)
    @ApiModelProperty(value = "主组别")
	private String primGrp;
	/**电子邮件模块安全级别*/
	@Excel(name = "电子邮件模块安全级别", width = 15)
    @ApiModelProperty(value = "电子邮件模块安全级别")
	private String elmSec;
	/**电子邮件模块基本安全级别*/
	@Excel(name = "电子邮件模块基本安全级别", width = 15)
    @ApiModelProperty(value = "电子邮件模块基本安全级别")
	private String primElmSec;
	/**最近活动日期*/
	@Excel(name = "最近活动日期", width = 15)
    @ApiModelProperty(value = "最近活动日期")
	private Integer dteLastActive;
	/**主币种*/
	@Excel(name = "主币种", width = 15)
    @ApiModelProperty(value = "主币种")
	private String primCurrency;
	/**外币1*/
	@Excel(name = "外币1", width = 15)
    @ApiModelProperty(value = "外币1")
	@TableField(value = "FOREIGN_CURR_01")
	private String foreignCurr01;
	/**外币2*/
	@Excel(name = "外币2", width = 15)
    @ApiModelProperty(value = "外币2")
	@TableField(value = "FOREIGN_CURR_02")
	private String foreignCurr02;
	/**外币3*/
	@Excel(name = "外币3", width = 15)
    @ApiModelProperty(value = "外币3")
	@TableField(value = "FOREIGN_CURR_03")
	private String foreignCurr03;
	/**外币4*/
	@Excel(name = "外币4", width = 15)
    @ApiModelProperty(value = "外币4")
	@TableField(value = "FOREIGN_CURR_04")
	private String foreignCurr04;
	/**外币5*/
	@Excel(name = "外币5", width = 15)
    @ApiModelProperty(value = "外币5")
	@TableField(value = "FOREIGN_CURR_05")
	private String foreignCurr05;
	/**有效的消息号码*/
	@Excel(name = "有效的消息号码", width = 15)
    @ApiModelProperty(value = "有效的消息号码")
	private String noActMsgs;
	/**待阅信息数*/
	@Excel(name = "待阅信息数", width = 15)
    @ApiModelProperty(value = "待阅信息数")
	private Integer msgsPending;
	/**柜员名称*/
	@Excel(name = "柜员名称", width = 15)
    @ApiModelProperty(value = "柜员名称")
	private String tellerName;
	/**柜员密码*/
	@Excel(name = "柜员密码", width = 15)
    @ApiModelProperty(value = "柜员密码")
	private String tellerPword;
	/**错误密码尝试次数*/
	@Excel(name = "错误密码尝试次数", width = 15)
    @ApiModelProperty(value = "错误密码尝试次数")
	private String tellerPwordRetry;
	/**访问安全代码*/
	@Excel(name = "访问安全代码", width = 15)
    @ApiModelProperty(value = "访问安全代码")
	private String accessSecurCode;
	/**基本安全代码*/
	@Excel(name = "基本安全代码", width = 15)
    @ApiModelProperty(value = "基本安全代码")
	private String primSecCode;
	/**密码变更日期*/
	@Excel(name = "密码变更日期", width = 15)
    @ApiModelProperty(value = "密码变更日期")
	private Integer changePwordDate;
	/**授权号*/
	@Excel(name = "授权号", width = 15)
    @ApiModelProperty(value = "授权号")
	private String tellerAuthority;
	/**最近十次使用过的密码1*/
	@Excel(name = "最近十次使用过的密码1", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码1")
	@TableField(value = "PWORD_AREA_01")
	private String pwordArea01;
	/**最近十次使用过的密码2*/
	@Excel(name = "最近十次使用过的密码2", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码2")
	@TableField(value = "PWORD_AREA_02")
	private String pwordArea02;
	/**最近十次使用过的密码3*/
	@Excel(name = "最近十次使用过的密码3", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码3")
	@TableField(value = "PWORD_AREA_03")
	private String pwordArea03;
	/**最近十次使用过的密码4*/
	@Excel(name = "最近十次使用过的密码4", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码4")
	@TableField(value = "PWORD_AREA_04")
	private String pwordArea04;
	/**最近十次使用过的密码5*/
	@Excel(name = "最近十次使用过的密码5", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码5")
	@TableField(value = "PWORD_AREA_05")
	private String pwordArea05;
	/**最近十次使用过的密码6*/
	@Excel(name = "最近十次使用过的密码6", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码6")
	@TableField(value = "PWORD_AREA_06")
	private String pwordArea06;
	/**最近十次使用过的密码7*/
	@Excel(name = "最近十次使用过的密码7", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码7")
	@TableField(value = "PWORD_AREA_07")
	private String pwordArea07;
	/**最近十次使用过的密码8*/
	@Excel(name = "最近十次使用过的密码8", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码8")
	@TableField(value = "PWORD_AREA_08")
	private String pwordArea08;
	/**最近十次使用过的密码9*/
	@Excel(name = "最近十次使用过的密码9", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码9")
	@TableField(value = "PWORD_AREA_09")
	private String pwordArea09;
	/**最近十次使用过的密码10*/
	@Excel(name = "最近十次使用过的密码10", width = 15)
    @ApiModelProperty(value = "最近十次使用过的密码10")
	@TableField(value = "PWORD_AREA_10")
	private String pwordArea10;
	/**当前交易的账号*/
	@Excel(name = "当前交易的账号", width = 15)
    @ApiModelProperty(value = "当前交易的账号")
	private Long currentAc;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private Integer currentTran;
	/**ACC_INVOLVED*/
	@Excel(name = "ACC_INVOLVED", width = 15)
    @ApiModelProperty(value = "ACC_INVOLVED")
	private String accInvolved;
	/**处理标志*/
	@Excel(name = "处理标志", width = 15)
    @ApiModelProperty(value = "处理标志")
	private String progressFlag;
	/**需要授权交易的错误码1*/
	@Excel(name = "需要授权交易的错误码1", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码1")
	@TableField(value = "ERROR_CODE_01")
	private Integer errorCode01;
	/**授权值1*/
	@Excel(name = "授权值1", width = 15)
    @ApiModelProperty(value = "授权值1")
	@TableField(value = "ERROR_OVERRIDE_01")
	private String errorOverride01;
	/**需要授权交易的错误码2*/
	@Excel(name = "需要授权交易的错误码2", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码2")
	@TableField(value = "ERROR_CODE_02")
	private Integer errorCode02;
	/**授权值2*/
	@Excel(name = "授权值2", width = 15)
    @ApiModelProperty(value = "授权值2")
	@TableField(value = "ERROR_OVERRIDE_02")
	private String errorOverride02;
	/**需要授权交易的错误码3*/
	@Excel(name = "需要授权交易的错误码3", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码3")
	@TableField(value = "ERROR_CODE_03")
	private Integer errorCode03;
	/**授权值3*/
	@Excel(name = "授权值3", width = 15)
    @ApiModelProperty(value = "授权值3")
	@TableField(value = "ERROR_OVERRIDE_03")
	private String errorOverride03;
	/**需要授权交易的错误码4*/
	@Excel(name = "需要授权交易的错误码4", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码4")
	@TableField(value = "ERROR_CODE_04")
	private Integer errorCode04;
	/**授权值4*/
	@Excel(name = "授权值4", width = 15)
    @ApiModelProperty(value = "授权值4")
	@TableField(value = "ERROR_OVERRIDE_04")
	private String errorOverride04;
	/**需要授权交易的错误码5*/
	@Excel(name = "需要授权交易的错误码5", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码5")
	@TableField(value = "ERROR_CODE_05")
	private Integer errorCode05;
	/**授权值5*/
	@Excel(name = "授权值5", width = 15)
    @ApiModelProperty(value = "授权值5")
	@TableField(value = "ERROR_OVERRIDE_05")
	private String errorOverride05;
	/**需要授权交易的错误码6*/
	@Excel(name = "需要授权交易的错误码6", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码6")
	@TableField(value = "ERROR_CODE_06")
	private Integer errorCode06;
	/**授权值6*/
	@Excel(name = "授权值6", width = 15)
    @ApiModelProperty(value = "授权值6")
	@TableField(value = "ERROR_OVERRIDE_06")
	private String errorOverride06;
	/**需要授权交易的错误码7*/
	@Excel(name = "需要授权交易的错误码7", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码7")
	@TableField(value = "ERROR_CODE_07")
	private Integer errorCode07;
	/**授权值7*/
	@Excel(name = "授权值7", width = 15)
    @ApiModelProperty(value = "授权值7")
	@TableField(value = "ERROR_OVERRIDE_07")
	private String errorOverride07;
	/**需要授权交易的错误码8*/
	@Excel(name = "需要授权交易的错误码8", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码8")
	@TableField(value = "ERROR_CODE_08")
	private Integer errorCode08;
	/**授权值8*/
	@Excel(name = "授权值8", width = 15)
    @ApiModelProperty(value = "授权值8")
	@TableField(value = "ERROR_OVERRIDE_08")
	private String errorOverride08;
	/**需要授权交易的错误码9*/
	@Excel(name = "需要授权交易的错误码9", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码9")
	@TableField(value = "ERROR_CODE_09")
	private Integer errorCode09;
	/**授权值9*/
	@Excel(name = "授权值9", width = 15)
    @ApiModelProperty(value = "授权值9")
	@TableField(value = "ERROR_OVERRIDE_09")
	private String errorOverride09;
	/**需要授权交易的错误码10*/
	@Excel(name = "需要授权交易的错误码10", width = 15)
    @ApiModelProperty(value = "需要授权交易的错误码10")
	@TableField(value = "ERROR_CODE_10")
	private Integer errorCode10;
	/**授权值10*/
	@Excel(name = "授权值10", width = 15)
    @ApiModelProperty(value = "授权值10")
	@TableField(value = "ERROR_OVERRIDE_10")
	private String errorOverride10;
	/**往来目的标识*/
	@Excel(name = "往来目的标识", width = 15)
    @ApiModelProperty(value = "往来目的标识")
	private String corrDestination;
	/**柜员缺席原因      0-Tellerpresent未缺席      1-Tellersick病假      2-Telleronannualleave年假      9-Otherreason其他原因*/
	@Excel(name = "柜员缺席原因      0-Tellerpresent未缺席      1-Tellersick病假      2-Telleronannualleave年假      9-Otherreason其他原因", width = 15)
    @ApiModelProperty(value = "柜员缺席原因      0-Tellerpresent未缺席      1-Tellersick病假      2-Telleronannualleave年假      9-Otherreason其他原因")
	private String absReasCode;
	/**授权柜员号*/
	@Excel(name = "授权柜员号", width = 15)
    @ApiModelProperty(value = "授权柜员号")
	private String supTeller;
	/**消息数*/
	@Excel(name = "消息数", width = 15)
    @ApiModelProperty(value = "消息数")
	private Integer noOfMessages;
	/**SECTION部门*/
	@Excel(name = "SECTION部门", width = 15)
    @ApiModelProperty(value = "SECTION部门")
	private String section;
	/**最近的终端消息*/
	@Excel(name = "最近的终端消息", width = 15)
    @ApiModelProperty(value = "最近的终端消息")
	private String lastTerminalMsg;
	/**用户类型      001-Teller柜面柜员      002-Dealer       03-BackOffice       04-Manager       05-Administration       06-CreditOfficer       07-Treasurer       08-Management       09-FinancialControl       10-Audit       11-RiskManagement       012–AccountOffice       013–AccountOfficeManager       014-RET*/
	@Excel(name = "用户类型      001-Teller柜面柜员      002-Dealer       03-BackOffice       04-Manager       05-Administration       06-CreditOfficer       07-Treasurer       08-Management       09-FinancialControl       10-Audit       11-RiskManagement       012–AccountOffice       013–AccountOfficeManager       014-RET", width = 15)
    @ApiModelProperty(value = "用户类型      001-Teller柜面柜员      002-Dealer       03-BackOffice       04-Manager       05-Administration       06-CreditOfficer       07-Treasurer       08-Management       09-FinancialControl       10-Audit       11-RiskManagement       012–AccountOffice       013–AccountOfficeManager       014-RET")
	private String userType;
	/**额度监控标志 Y-MonitorLimit柜员交易金额被监控额度 N-NonMonitorLimit柜员交易金额不被监控（农信全为’N’）*/
	@Excel(name = "额度监控标志 Y-MonitorLimit柜员交易金额被监控额度 N-NonMonitorLimit柜员交易金额不被监控（农信全为’N’）", width = 15)
    @ApiModelProperty(value = "额度监控标志 Y-MonitorLimit柜员交易金额被监控额度 N-NonMonitorLimit柜员交易金额不被监控（农信全为’N’）")
	private String limitMonitorFlag;
	/**货币对模板代码*/
	@Excel(name = "货币对模板代码", width = 15)
    @ApiModelProperty(value = "货币对模板代码")
	private String curpTemplateCode;
	/**未在FF中找到*/
	@Excel(name = "未在FF中找到", width = 15)
    @ApiModelProperty(value = "未在FF中找到")
	private String aoGroup;
	/**会计主管经理号*/
	@Excel(name = "会计主管经理号", width = 15)
    @ApiModelProperty(value = "会计主管经理号")
	private String aoManagerNo;
	/**柜员交易限额*/
	@Excel(name = "柜员交易限额", width = 15)
    @ApiModelProperty(value = "柜员交易限额")
	private java.math.BigDecimal tellerLimits;
	/**员工号*/
	@Excel(name = "员工号", width = 15)
    @ApiModelProperty(value = "员工号")
	private String employeeId;
	/**日终轧账检查标志0-未轧账(签到时更新为0) 1-已轧账(用户轧账交易9015会设置为1)*/
	@Excel(name = "日终轧账检查标志0-未轧账(签到时更新为0) 1-已轧账(用户轧账交易9015会设置为1)", width = 15)
    @ApiModelProperty(value = "日终轧账检查标志0-未轧账(签到时更新为0) 1-已轧账(用户轧账交易9015会设置为1)")
	private String eodBalInd;
	/**用户部门*/
	@Excel(name = "用户部门", width = 15)
    @ApiModelProperty(value = "用户部门")
	private String userDepart;
	/**授权柜员组号*/
	@Excel(name = "授权柜员组号", width = 15)
    @ApiModelProperty(value = "授权柜员组号")
	private String authGroupNo;
	/**临时值班机构号*/
	@Excel(name = "临时值班机构号", width = 15)
    @ApiModelProperty(value = "临时值班机构号")
	private String tempBranchNo;
	/**临时值班用户组*/
	@Excel(name = "临时值班用户组", width = 15)
    @ApiModelProperty(value = "临时值班用户组")
	private String tempUserTeamNo;
	/**临时值班用户级别*/
	@Excel(name = "临时值班用户级别", width = 15)
    @ApiModelProperty(value = "临时值班用户级别")
	private String tempUserLevel;
	/**临时值班用户类型*/
	@Excel(name = "临时值班用户类型", width = 15)
    @ApiModelProperty(value = "临时值班用户类型")
	private String tempUserType;
	/**临时值班开始日期*/
	@Excel(name = "临时值班开始日期", width = 15)
    @ApiModelProperty(value = "临时值班开始日期")
	private Integer tempStartDate;
	/**临时值班结束日期*/
	@Excel(name = "临时值班结束日期", width = 15)
    @ApiModelProperty(value = "临时值班结束日期")
	private Integer tempEndDate;
	/**填充*/
	@Excel(name = "填充", width = 15)
    @ApiModelProperty(value = "填充")
	private String fil01;
	/**上次交易日期*/
	@Excel(name = "上次交易日期", width = 15)
    @ApiModelProperty(value = "上次交易日期")
	private String lastMaintDate;
	/**上次交易状态*/
	@Excel(name = "上次交易状态", width = 15)
    @ApiModelProperty(value = "上次交易状态")
	private String lastMaintStat;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
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
