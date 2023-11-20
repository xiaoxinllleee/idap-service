package org.cmms.modules.sjxf.qtxt.edzfxt.dezfywzb.entity;

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
 * @Description: 大额支付业务主表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgps_hv_zf")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgps_hv_zf对象", description="大额支付业务主表")
public class Dezfywzb {
    
	/**CMT编号/报文类型*/
	@Excel(name = "CMT编号/报文类型", width = 15)
    @ApiModelProperty(value = "CMT编号/报文类型")
	private String cmtno;
	/**通信级标识号*/
	@Excel(name = "通信级标识号", width = 15)
    @ApiModelProperty(value = "通信级标识号")
	private String commid;
	/**通信级参考号*/
	@Excel(name = "通信级参考号", width = 15)
    @ApiModelProperty(value = "通信级参考号")
	private String refcommid;
	/**报文标识号*/
	@Excel(name = "报文标识号", width = 15)
    @ApiModelProperty(value = "报文标识号")
	private String msgno;
	/**发送网点*/
	@Excel(name = "发送网点", width = 15)
    @ApiModelProperty(value = "发送网点")
	private String sndBrNo;
	/**接收网点*/
	@Excel(name = "接收网点", width = 15)
    @ApiModelProperty(value = "接收网点")
	private String rcvBrNo;
	/**平台日期*/
	@Excel(name = "平台日期", width = 15)
    @ApiModelProperty(value = "平台日期")
	private String txDate;
	/**委托日期*/
	@Excel(name = "委托日期", width = 15)
    @ApiModelProperty(value = "委托日期")
	private String wtDate;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String txTime;
	/**人行的交易序号*/
	@Excel(name = "人行的交易序号", width = 15)
    @ApiModelProperty(value = "人行的交易序号")
	private Integer orderno;
	/**货币符号*/
	@Excel(name = "货币符号", width = 15)
    @ApiModelProperty(value = "货币符号")
	private String curNo;
	/**凭证类型*/
	@Excel(name = "凭证类型", width = 15)
    @ApiModelProperty(value = "凭证类型")
	private String noteType;
	/**冠子号码*/
	@Excel(name = "冠子号码", width = 15)
    @ApiModelProperty(value = "冠子号码")
	private String notePreno;
	/**凭证号*/
	@Excel(name = "凭证号", width = 15)
    @ApiModelProperty(value = "凭证号")
	private String noteNo;
	/**支取方式*/
	@Excel(name = "支取方式", width = 15)
    @ApiModelProperty(value = "支取方式")
	private String drawtype;
	/**密码/证件号码*/
	@Excel(name = "密码/证件号码", width = 15)
    @ApiModelProperty(value = "密码/证件号码")
	private String drawval;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal txAmt;
	/**手续费标志*/
	@Excel(name = "手续费标志", width = 15)
    @ApiModelProperty(value = "手续费标志")
	private String txChrgInd;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private java.math.BigDecimal chrgAmt;
	/**发起清算行行号*/
	@Excel(name = "发起清算行行号", width = 15)
    @ApiModelProperty(value = "发起清算行行号")
	private String payQsNo;
	/**发起行行号*/
	@Excel(name = "发起行行号", width = 15)
    @ApiModelProperty(value = "发起行行号")
	private String orBrNo;
	/**发起行行名*/
	@Excel(name = "发起行行名", width = 15)
    @ApiModelProperty(value = "发起行行名")
	private String orBrNm;
	/**付款人开户行行号*/
	@Excel(name = "付款人开户行行号", width = 15)
    @ApiModelProperty(value = "付款人开户行行号")
	private String payOpnBrNo;
	/**付款人开户行行名*/
	@Excel(name = "付款人开户行行名", width = 15)
    @ApiModelProperty(value = "付款人开户行行名")
	private String payOpnBrNm;
	/**付款人账号*/
	@Excel(name = "付款人账号", width = 15)
    @ApiModelProperty(value = "付款人账号")
	private String payAcNo;
	/**付款人姓名*/
	@Excel(name = "付款人姓名", width = 15)
    @ApiModelProperty(value = "付款人姓名")
	private String payName;
	/**付款人地址*/
	@Excel(name = "付款人地址", width = 15)
    @ApiModelProperty(value = "付款人地址")
	private String payAddr;
	/**接收行清算行号*/
	@Excel(name = "接收行清算行号", width = 15)
    @ApiModelProperty(value = "接收行清算行号")
	private String cashQsNo;
	/**接收行行号*/
	@Excel(name = "接收行行号", width = 15)
    @ApiModelProperty(value = "接收行行号")
	private String acBrNo;
	/**接收行行名*/
	@Excel(name = "接收行行名", width = 15)
    @ApiModelProperty(value = "接收行行名")
	private String acBrNm;
	/**收款人开户行行号*/
	@Excel(name = "收款人开户行行号", width = 15)
    @ApiModelProperty(value = "收款人开户行行号")
	private String cashOpnBrNo;
	/**收款人开户行行名*/
	@Excel(name = "收款人开户行行名", width = 15)
    @ApiModelProperty(value = "收款人开户行行名")
	private String cashOpnBrNm;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String cashAcNo;
	/**收款人姓名*/
	@Excel(name = "收款人姓名", width = 15)
    @ApiModelProperty(value = "收款人姓名")
	private String cashName;
	/**收款人地址*/
	@Excel(name = "收款人地址", width = 15)
    @ApiModelProperty(value = "收款人地址")
	private String cashAddr;
	/**中介机构1*/
	@Excel(name = "中介机构1", width = 15)
    @ApiModelProperty(value = "中介机构1")
	private String mediary1;
	/**中介机构1名称*/
	@Excel(name = "中介机构1名称", width = 15)
    @ApiModelProperty(value = "中介机构1名称")
	private String mediaryname1;
	/**中介机构2*/
	@Excel(name = "中介机构2", width = 15)
    @ApiModelProperty(value = "中介机构2")
	private String mediary2;
	/**中介机构2名称*/
	@Excel(name = "中介机构2名称", width = 15)
    @ApiModelProperty(value = "中介机构2名称")
	private String mediaryname2;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String ywKind;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private String ywType;
	/**一二代标志*/
	@Excel(name = "一二代标志", width = 15)
    @ApiModelProperty(value = "一二代标志")
	private String begSts;
	/**优先级别*/
	@Excel(name = "优先级别", width = 15)
    @ApiModelProperty(value = "优先级别")
	private String operlevel;
	/**端到端标识号*/
	@Excel(name = "端到端标识号", width = 15)
    @ApiModelProperty(value = "端到端标识号")
	private String endtoendid;
	/**清算日期*/
	@Excel(name = "清算日期", width = 15)
    @ApiModelProperty(value = "清算日期")
	private String clsdate;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String hvSts;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String tel;
	/**核心记账日期*/
	@Excel(name = "核心记账日期", width = 15)
    @ApiModelProperty(value = "核心记账日期")
	private String hostDate;
	/**核心记账流水号*/
	@Excel(name = "核心记账流水号", width = 15)
    @ApiModelProperty(value = "核心记账流水号")
	private Integer traceNo;
	/**挂账标志*/
	@Excel(name = "挂账标志", width = 15)
    @ApiModelProperty(value = "挂账标志")
	private String susflag;
	/**入账账号*/
	@Excel(name = "入账账号", width = 15)
    @ApiModelProperty(value = "入账账号")
	private String enteracc;
	/**入账账户名称*/
	@Excel(name = "入账账户名称", width = 15)
    @ApiModelProperty(value = "入账账户名称")
	private String enteraccnm;
	/**入账账号开户机构*/
	@Excel(name = "入账账号开户机构", width = 15)
    @ApiModelProperty(value = "入账账号开户机构")
	private String enteropenbrn;
	/**手工入账核心日期*/
	@Excel(name = "手工入账核心日期", width = 15)
    @ApiModelProperty(value = "手工入账核心日期")
	private String enterdate;
	/**手工入账核心流水*/
	@Excel(name = "手工入账核心流水", width = 15)
    @ApiModelProperty(value = "手工入账核心流水")
	private Integer entertrace;
	/**手工入账柜员*/
	@Excel(name = "手工入账柜员", width = 15)
    @ApiModelProperty(value = "手工入账柜员")
	private String entertel;
	/**手工入账平台流水*/
	@Excel(name = "手工入账平台流水", width = 15)
    @ApiModelProperty(value = "手工入账平台流水")
	private Integer enterswno;
	/**发送时核心记账日期*/
	@Excel(name = "发送时核心记账日期", width = 15)
    @ApiModelProperty(value = "发送时核心记账日期")
	private String hostDateSnd;
	/**发送时核心记账流水号*/
	@Excel(name = "发送时核心记账流水号", width = 15)
    @ApiModelProperty(value = "发送时核心记账流水号")
	private Integer traceNoSnd;
	/**原支付来账交易状态*/
	@Excel(name = "原支付来账交易状态", width = 15)
    @ApiModelProperty(value = "原支付来账交易状态")
	private String hvOsts;
	/**人行对账检查状态*/
	@Excel(name = "人行对账检查状态", width = 15)
    @ApiModelProperty(value = "人行对账检查状态")
	private String checkflag;
	/**行内对账检查状态*/
	@Excel(name = "行内对账检查状态", width = 15)
    @ApiModelProperty(value = "行内对账检查状态")
	private String hostflag;
	/**打印次数*/
	@Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
	private Integer hvPrtInd;
	/**拒绝重发标志*/
	@Excel(name = "拒绝重发标志", width = 15)
    @ApiModelProperty(value = "拒绝重发标志")
	private String resendInd;
	/**拒绝重新发送日期*/
	@Excel(name = "拒绝重新发送日期", width = 15)
    @ApiModelProperty(value = "拒绝重新发送日期")
	private String resendDate;
	/**来往标识*/
	@Excel(name = "来往标识", width = 15)
    @ApiModelProperty(value = "来往标识")
	private String lwInd;
	/**借贷标志*/
	@Excel(name = "借贷标志", width = 15)
    @ApiModelProperty(value = "借贷标志")
	private String cdFlag;
	/**接收平台流水号*/
	@Excel(name = "接收平台流水号", width = 15)
    @ApiModelProperty(value = "接收平台流水号")
	private Integer rcvSwno;
	/**授权操作员*/
	@Excel(name = "授权操作员", width = 15)
    @ApiModelProperty(value = "授权操作员")
	private String authTel;
	/**授权操作员流水号*/
	@Excel(name = "授权操作员流水号", width = 15)
    @ApiModelProperty(value = "授权操作员流水号")
	private Integer authTraceNo;
	/**复核操作员*/
	@Excel(name = "复核操作员", width = 15)
    @ApiModelProperty(value = "复核操作员")
	private String chk;
	/**复核操作员流水号*/
	@Excel(name = "复核操作员流水号", width = 15)
    @ApiModelProperty(value = "复核操作员流水号")
	private Integer chkTraceNo;
	/**发送操作员*/
	@Excel(name = "发送操作员", width = 15)
    @ApiModelProperty(value = "发送操作员")
	private String sendTel;
	/**发送操作员流水号*/
	@Excel(name = "发送操作员流水号", width = 15)
    @ApiModelProperty(value = "发送操作员流水号")
	private Integer sendTraceNo;
	/**错账处理日期*/
	@Excel(name = "错账处理日期", width = 15)
    @ApiModelProperty(value = "错账处理日期")
	private String dealerrDate;
	/**错账处理柜员*/
	@Excel(name = "错账处理柜员", width = 15)
    @ApiModelProperty(value = "错账处理柜员")
	private String dealerrTel;
	/**错账处理主机流水*/
	@Excel(name = "错账处理主机流水", width = 15)
    @ApiModelProperty(value = "错账处理主机流水")
	private Integer dealerrHostno;
	/**错账处理平台流水*/
	@Excel(name = "错账处理平台流水", width = 15)
    @ApiModelProperty(value = "错账处理平台流水")
	private Integer dealerrSwno;
	/**处理状态*/
	@Excel(name = "处理状态", width = 15)
    @ApiModelProperty(value = "处理状态")
	private String procstat;
	/**处理码*/
	@Excel(name = "处理码", width = 15)
    @ApiModelProperty(value = "处理码")
	private String respcode;
	/**应答信息*/
	@Excel(name = "应答信息", width = 15)
    @ApiModelProperty(value = "应答信息")
	private String rspinfo;
	/**业务处理参与机构*/
	@Excel(name = "业务处理参与机构", width = 15)
    @ApiModelProperty(value = "业务处理参与机构")
	private String procarty;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String hvBrf;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String hvBrf2;
	/**附言*/
	@Excel(name = "附言", width = 15)
    @ApiModelProperty(value = "附言")
	private String remark;
	/**附言2*/
	@Excel(name = "附言2", width = 15)
    @ApiModelProperty(value = "附言2")
	private String remark2;
	/**记账账号*/
	@Excel(name = "记账账号", width = 15)
    @ApiModelProperty(value = "记账账号")
	private String accno;
	/**记账账号名称*/
	@Excel(name = "记账账号名称", width = 15)
    @ApiModelProperty(value = "记账账号名称")
	private String accnm;
	/**销账编号*/
	@Excel(name = "销账编号", width = 15)
    @ApiModelProperty(value = "销账编号")
	private String chargeoff;
	/**卡折标识*/
	@Excel(name = "卡折标识", width = 15)
    @ApiModelProperty(value = "卡折标识")
	private String kzFlag;
	/**授权标志*/
	@Excel(name = "授权标志", width = 15)
    @ApiModelProperty(value = "授权标志")
	private String sqbz;
	/**入账机构/挂账机构*/
	@Excel(name = "入账机构/挂账机构", width = 15)
    @ApiModelProperty(value = "入账机构/挂账机构")
	private String susBrno;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15)
    @ApiModelProperty(value = "渠道类型")
	private String chanType;
	/**挂账账号*/
	@Excel(name = "挂账账号", width = 15)
    @ApiModelProperty(value = "挂账账号")
	private String susAcno;
	/**挂账原因*/
	@Excel(name = "挂账原因", width = 15)
    @ApiModelProperty(value = "挂账原因")
	private String susReason;
	/**补发标志*/
	@Excel(name = "补发标志", width = 15)
    @ApiModelProperty(value = "补发标志")
	private String resndflg;
	/**渠道ID*/
	@Excel(name = "渠道ID", width = 15)
    @ApiModelProperty(value = "渠道ID")
	private String chnlid;
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
