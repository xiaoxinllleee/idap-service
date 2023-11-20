package org.cmms.modules.sjxf.qtxt.edzfxt.xezfjyzb.entity;

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
 * @Description: 小额支付交易主表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgps_lv_pkgreg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgps_lv_pkgreg对象", description="小额支付交易主表")
public class Xezfjyzb {
    
	/**报文类型*/
	@Excel(name = "报文类型", width = 15)
    @ApiModelProperty(value = "报文类型")
	private String msgtype;
	/**交易机构*/
	@Excel(name = "交易机构", width = 15)
    @ApiModelProperty(value = "交易机构")
	private String brNo;
	/**核心记账日期*/
	@Excel(name = "核心记账日期", width = 15)
    @ApiModelProperty(value = "核心记账日期")
	private String jzDate;
	/**核心记账流水号*/
	@Excel(name = "核心记账流水号", width = 15)
    @ApiModelProperty(value = "核心记账流水号")
	private Integer hostno;
	/**挂账标志*/
	@Excel(name = "挂账标志", width = 15)
    @ApiModelProperty(value = "挂账标志")
	private String susflag;
	/**入账帐号*/
	@Excel(name = "入账帐号", width = 15)
    @ApiModelProperty(value = "入账帐号")
	private String enteracc;
	/**入账帐户名称*/
	@Excel(name = "入账帐户名称", width = 15)
    @ApiModelProperty(value = "入账帐户名称")
	private String enteraccnm;
	/**入账账号开户机构*/
	@Excel(name = "入账账号开户机构", width = 15)
    @ApiModelProperty(value = "入账账号开户机构")
	private String enteropenbrn;
	/**手工入账日期/借记来帐处理日期*/
	@Excel(name = "手工入账日期/借记来帐处理日期", width = 15)
    @ApiModelProperty(value = "手工入账日期/借记来帐处理日期")
	private String enterdate;
	/**手工入账流水/借记来账处理流水*/
	@Excel(name = "手工入账流水/借记来账处理流水", width = 15)
    @ApiModelProperty(value = "手工入账流水/借记来账处理流水")
	private Integer entertrace;
	/**手工入账柜员/借记来账处理柜员*/
	@Excel(name = "手工入账柜员/借记来账处理柜员", width = 15)
    @ApiModelProperty(value = "手工入账柜员/借记来账处理柜员")
	private String entertel;
	/**手工入账平台流水/借记来账处理平台流水*/
	@Excel(name = "手工入账平台流水/借记来账处理平台流水", width = 15)
    @ApiModelProperty(value = "手工入账平台流水/借记来账处理平台流水")
	private Integer enterswno;
	/**平台日期*/
	@Excel(name = "平台日期", width = 15)
    @ApiModelProperty(value = "平台日期")
	private String txDate;
	/**委托日期*/
	@Excel(name = "委托日期", width = 15)
    @ApiModelProperty(value = "委托日期")
	private String wtDate;
	/**小额日期*/
	@Excel(name = "小额日期", width = 15)
    @ApiModelProperty(value = "小额日期")
	private String bepsdate;
	/**支付交易序号*/
	@Excel(name = "支付交易序号", width = 15)
    @ApiModelProperty(value = "支付交易序号")
	private Integer orderno;
	/**回执支付序号*/
	@Excel(name = "回执支付序号", width = 15)
    @ApiModelProperty(value = "回执支付序号")
	private Integer respno;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String txTime;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String txtype;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private String txkind;
	/**发起行行号*/
	@Excel(name = "发起行行号", width = 15)
    @ApiModelProperty(value = "发起行行号")
	private String orBrNo;
	/**发起清算行行号*/
	@Excel(name = "发起清算行行号", width = 15)
    @ApiModelProperty(value = "发起清算行行号")
	private String payQsNo;
	/**接收行行号*/
	@Excel(name = "接收行行号", width = 15)
    @ApiModelProperty(value = "接收行行号")
	private String acBrNo;
	/**接收清算行行号*/
	@Excel(name = "接收清算行行号", width = 15)
    @ApiModelProperty(value = "接收清算行行号")
	private String cashQsNo;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal txAmt;
	/**借贷标志*/
	@Excel(name = "借贷标志", width = 15)
    @ApiModelProperty(value = "借贷标志")
	private String cdflag;
	/**凭证种类*/
	@Excel(name = "凭证种类", width = 15)
    @ApiModelProperty(value = "凭证种类")
	private String noteType;
	/**冠字号码*/
	@Excel(name = "冠字号码", width = 15)
    @ApiModelProperty(value = "冠字号码")
	private String notePreno;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String noteNo;
	/**支取方式*/
	@Excel(name = "支取方式", width = 15)
    @ApiModelProperty(value = "支取方式")
	private String drawtype;
	/**密码/证件号码/支付密码*/
	@Excel(name = "密码/证件号码/支付密码", width = 15)
    @ApiModelProperty(value = "密码/证件号码/支付密码")
	private String drawval;
	/**付款人开户行*/
	@Excel(name = "付款人开户行", width = 15)
    @ApiModelProperty(value = "付款人开户行")
	private String payOpnBrNo;
	/**付款人开户行名称*/
	@Excel(name = "付款人开户行名称", width = 15)
    @ApiModelProperty(value = "付款人开户行名称")
	private String payOpnBrNm;
	/**付款人账号*/
	@Excel(name = "付款人账号", width = 15)
    @ApiModelProperty(value = "付款人账号")
	private String payAcNo;
	/**付款人名称*/
	@Excel(name = "付款人名称", width = 15)
    @ApiModelProperty(value = "付款人名称")
	private String payName;
	/**付款人地址*/
	@Excel(name = "付款人地址", width = 15)
    @ApiModelProperty(value = "付款人地址")
	private String payAddr;
	/**收款人开户行*/
	@Excel(name = "收款人开户行", width = 15)
    @ApiModelProperty(value = "收款人开户行")
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
	/**附言*/
	@Excel(name = "附言", width = 15)
    @ApiModelProperty(value = "附言")
	private String lvBrf;
	/**附言2*/
	@Excel(name = "附言2", width = 15)
    @ApiModelProperty(value = "附言2")
	private String lvBrf2;
	/**回执期限*/
	@Excel(name = "回执期限", width = 15)
    @ApiModelProperty(value = "回执期限")
	private Integer returndays;
	/**多方协议号*/
	@Excel(name = "多方协议号", width = 15)
    @ApiModelProperty(value = "多方协议号")
	private String protno;
	/**NPC转发日期*/
	@Excel(name = "NPC转发日期", width = 15)
    @ApiModelProperty(value = "NPC转发日期")
	private String trandate;
	/**来往标识*/
	@Excel(name = "来往标识", width = 15)
    @ApiModelProperty(value = "来往标识")
	private String lwInd;
	/**回执日期*/
	@Excel(name = "回执日期", width = 15)
    @ApiModelProperty(value = "回执日期")
	private String respDate;
	/**业务回执状态*/
	@Excel(name = "业务回执状态", width = 15)
    @ApiModelProperty(value = "业务回执状态")
	private String rcpstat;
	/**业务拒绝码*/
	@Excel(name = "业务拒绝码", width = 15)
    @ApiModelProperty(value = "业务拒绝码")
	private String rejectcode;
	/**业务拒绝原因*/
	@Excel(name = "业务拒绝原因", width = 15)
    @ApiModelProperty(value = "业务拒绝原因")
	private String rejectreason;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String inputTel;
	/**复核操作员*/
	@Excel(name = "复核操作员", width = 15)
    @ApiModelProperty(value = "复核操作员")
	private String checkTel;
	/**授权操作员*/
	@Excel(name = "授权操作员", width = 15)
    @ApiModelProperty(value = "授权操作员")
	private String authTel;
	/**发送操作员*/
	@Excel(name = "发送操作员", width = 15)
    @ApiModelProperty(value = "发送操作员")
	private String sendTel;
	/**回执操作员*/
	@Excel(name = "回执操作员", width = 15)
    @ApiModelProperty(value = "回执操作员")
	private String respTel;
	/**复核平台流水*/
	@Excel(name = "复核平台流水", width = 15)
    @ApiModelProperty(value = "复核平台流水")
	private Integer checkTraceNo;
	/**接收平台流水*/
	@Excel(name = "接收平台流水", width = 15)
    @ApiModelProperty(value = "接收平台流水")
	private Integer rcvTraceNo;
	/**错账处理操作员*/
	@Excel(name = "错账处理操作员", width = 15)
    @ApiModelProperty(value = "错账处理操作员")
	private String dealTel;
	/**错账处理日期*/
	@Excel(name = "错账处理日期", width = 15)
    @ApiModelProperty(value = "错账处理日期")
	private String dealDate;
	/**错账处理平台流水/冻结流水*/
	@Excel(name = "错账处理平台流水/冻结流水", width = 15)
    @ApiModelProperty(value = "错账处理平台流水/冻结流水")
	private Integer dealSwno;
	/**错账处理流水号*/
	@Excel(name = "错账处理流水号", width = 15)
    @ApiModelProperty(value = "错账处理流水号")
	private Integer dealTraceNo;
	/**交易状态(可参考附录)*/
	@Excel(name = "交易状态(可参考附录)", width = 15)
    @ApiModelProperty(value = "交易状态(可参考附录)")
	private String lvSts;
	/**打印次数*/
	@Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
	private Integer prtTimes;
	/**手续费标志*/
	@Excel(name = "手续费标志", width = 15)
    @ApiModelProperty(value = "手续费标志")
	private String txChrgInd;
	/**手续费金额*/
	@Excel(name = "手续费金额", width = 15)
    @ApiModelProperty(value = "手续费金额")
	private java.math.BigDecimal chrgAmt;
	/**人行对账检查状态*/
	@Excel(name = "人行对账检查状态", width = 15)
    @ApiModelProperty(value = "人行对账检查状态")
	private String checkflag;
	/**行内对账检查状态*/
	@Excel(name = "行内对账检查状态", width = 15)
    @ApiModelProperty(value = "行内对账检查状态")
	private String hostflag;
	/**包委托日期*/
	@Excel(name = "包委托日期", width = 15)
    @ApiModelProperty(value = "包委托日期")
	private String packDate;
	/**包序号*/
	@Excel(name = "包序号", width = 15)
    @ApiModelProperty(value = "包序号")
	private Integer packno;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String filler;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String filler2;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String accType;
	/**记账账号*/
	@Excel(name = "记账账号", width = 15)
    @ApiModelProperty(value = "记账账号")
	private String accno;
	/**记账账户名称*/
	@Excel(name = "记账账户名称", width = 15)
    @ApiModelProperty(value = "记账账户名称")
	private String accnm;
	/**报文优先级*/
	@Excel(name = "报文优先级", width = 15)
    @ApiModelProperty(value = "报文优先级")
	private String mesgpriority;
	/**销账编号*/
	@Excel(name = "销账编号", width = 15)
    @ApiModelProperty(value = "销账编号")
	private String chargeoff;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String kzFlag;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15)
    @ApiModelProperty(value = "渠道类型")
	private String chanType;
	/**挂账机构/入账机构*/
	@Excel(name = "挂账机构/入账机构", width = 15)
    @ApiModelProperty(value = "挂账机构/入账机构")
	private String susBrno;
	/**挂账账号*/
	@Excel(name = "挂账账号", width = 15)
    @ApiModelProperty(value = "挂账账号")
	private String susAcno;
	/**挂账原因*/
	@Excel(name = "挂账原因", width = 15)
    @ApiModelProperty(value = "挂账原因")
	private String susReason;
	/**代收付标志*/
	@Excel(name = "代收付标志", width = 15)
    @ApiModelProperty(value = "代收付标志")
	private Integer dsfFlag;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String batch;
	/**处理标识*/
	@Excel(name = "处理标识", width = 15)
    @ApiModelProperty(value = "处理标识")
	private String dealflag;
	/**渠道ID*/
	@Excel(name = "渠道ID", width = 15)
    @ApiModelProperty(value = "渠道ID")
	private String chnlid;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**数据加载日期*/
    @ApiModelProperty(value = "数据加载日期")
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
