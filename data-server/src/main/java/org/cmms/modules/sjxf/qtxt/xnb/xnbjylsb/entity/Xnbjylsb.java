package org.cmms.modules.sjxf.qtxt.xnb.xnbjylsb.entity;

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
 * @Description: 新农保交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_xnbmtranjnl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_xnbmtranjnl对象", description="新农保交易流水表")
public class Xnbjylsb {
    
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
	/**发起渠道代码*/
	@Excel(name = "发起渠道代码", width = 15)
    @ApiModelProperty(value = "发起渠道代码")
	private String chnlcode;
	/**发起渠道流水号*/
	@Excel(name = "发起渠道流水号", width = 15)
    @ApiModelProperty(value = "发起渠道流水号")
	private String chnlseqno;
	/**发起渠道日期*/
	@Excel(name = "发起渠道日期", width = 15)
    @ApiModelProperty(value = "发起渠道日期")
	private String chnldate;
	/**发起渠道时间*/
	@Excel(name = "发起渠道时间", width = 15)
    @ApiModelProperty(value = "发起渠道时间")
	private String chnltime;
	/**操作网点*/
	@Excel(name = "操作网点", width = 15)
    @ApiModelProperty(value = "操作网点")
	private String brno;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String tellerno;
	/**授权网点*/
	@Excel(name = "授权网点", width = 15)
    @ApiModelProperty(value = "授权网点")
	private String authbrno;
	/**授权柜员*/
	@Excel(name = "授权柜员", width = 15)
    @ApiModelProperty(value = "授权柜员")
	private String authtellerno;
	/**操作终端号*/
	@Excel(name = "操作终端号", width = 15)
    @ApiModelProperty(value = "操作终端号")
	private String terminalcode;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
	private String identno;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String truename;
	/**缴费年度*/
	@Excel(name = "缴费年度", width = 15)
    @ApiModelProperty(value = "缴费年度")
	private String payyear;
	/**缴费档次*/
	@Excel(name = "缴费档次", width = 15)
    @ApiModelProperty(value = "缴费档次")
	private String paylevel;
	/**缴费身份*/
	@Excel(name = "缴费身份", width = 15)
    @ApiModelProperty(value = "缴费身份")
	private String payident;
	/**缴费金额*/
	@Excel(name = "缴费金额", width = 15)
    @ApiModelProperty(value = "缴费金额")
	private String payamt;
	/**缴款方式*/
	@Excel(name = "缴款方式", width = 15)
    @ApiModelProperty(value = "缴款方式")
	private String payway;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String cardflag;
	/**缴款卡号*/
	@Excel(name = "缴款卡号", width = 15)
    @ApiModelProperty(value = "缴款卡号")
	private String paycardno;
	/**缴款账号*/
	@Excel(name = "缴款账号", width = 15)
    @ApiModelProperty(value = "缴款账号")
	private String payacctno;
	/**存折号码*/
	@Excel(name = "存折号码", width = 15)
    @ApiModelProperty(value = "存折号码")
	private String passbookno;
	/**缴费单号*/
	@Excel(name = "缴费单号", width = 15)
    @ApiModelProperty(value = "缴费单号")
	private String payno;
	/**社保机构编码*/
	@Excel(name = "社保机构编码", width = 15)
    @ApiModelProperty(value = "社保机构编码")
	private String socialorgcode;
	/**社保险种*/
	@Excel(name = "社保险种", width = 15)
    @ApiModelProperty(value = "社保险种")
	private String socialkind;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String voucherno;
	/**打印方式*/
	@Excel(name = "打印方式", width = 15)
    @ApiModelProperty(value = "打印方式")
	private String printway;
	/**起始时间*/
	@Excel(name = "起始时间", width = 15)
    @ApiModelProperty(value = "起始时间")
	private String startdate;
	/**截止时间*/
	@Excel(name = "截止时间", width = 15)
    @ApiModelProperty(value = "截止时间")
	private String enddate;
	/**核心请求UUID*/
	@Excel(name = "核心请求UUID", width = 15)
    @ApiModelProperty(value = "核心请求UUID")
	private String requuid;
	/**核心应答UUID*/
	@Excel(name = "核心应答UUID", width = 15)
    @ApiModelProperty(value = "核心应答UUID")
	private String rspuuid;
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
	/**第三方交易时间*/
	@Excel(name = "第三方交易时间", width = 15)
    @ApiModelProperty(value = "第三方交易时间")
	private String reqtime;
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
	/**交易当前业务步骤*/
	@Excel(name = "交易当前业务步骤", width = 15)
    @ApiModelProperty(value = "交易当前业务步骤")
	private String tradebusistep;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String busistatus;
	/**原平台受理日期*/
	@Excel(name = "原平台受理日期", width = 15)
    @ApiModelProperty(value = "原平台受理日期")
	private String origworkdate;
	/**原平台业务序号*/
	@Excel(name = "原平台业务序号", width = 15)
    @ApiModelProperty(value = "原平台业务序号")
	private String origworkseqid;
	/**冲正标志*/
	@Excel(name = "冲正标志", width = 15)
    @ApiModelProperty(value = "冲正标志")
	private String revflag;
	/**BSP标识号*/
	@Excel(name = "BSP标识号", width = 15)
    @ApiModelProperty(value = "BSP标识号")
	private String bspno;
	/**业务拒绝码*/
	@Excel(name = "业务拒绝码", width = 15)
    @ApiModelProperty(value = "业务拒绝码")
	private String errcode;
	/**业务拒绝原因*/
	@Excel(name = "业务拒绝原因", width = 15)
    @ApiModelProperty(value = "业务拒绝原因")
	private String errmsg;
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
	/**核心流水*/
	@Excel(name = "核心流水", width = 15)
    @ApiModelProperty(value = "核心流水")
	private String hostreqno;
	/**核心日期*/
	@Excel(name = "核心日期", width = 15)
    @ApiModelProperty(value = "核心日期")
	private String hostdate;
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
