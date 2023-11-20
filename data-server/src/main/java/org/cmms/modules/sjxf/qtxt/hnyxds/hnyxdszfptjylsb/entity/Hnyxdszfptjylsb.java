package org.cmms.modules.sjxf.qtxt.hnyxds.hnyxdszfptjylsb.entity;

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
 * @Description: 湖南有限电视支付平台交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("Ibus_dsch_debtinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_dsch_debtinfo对象", description="湖南有限电视支付平台交易流水表")
public class Hnyxdszfptjylsb {
    
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String transtime;
	/**账务日期*/
	@Excel(name = "账务日期", width = 15)
    @ApiModelProperty(value = "账务日期")
	private String acctDate;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private String serialno;
	/**上核心流水号*/
	@Excel(name = "上核心流水号", width = 15)
    @ApiModelProperty(value = "上核心流水号")
	private String hostserialno;
	/**上电视流水号*/
	@Excel(name = "上电视流水号", width = 15)
    @ApiModelProperty(value = "上电视流水号")
	private String tvSerialno;
	/**前置交易代码*/
	@Excel(name = "前置交易代码", width = 15)
    @ApiModelProperty(value = "前置交易代码")
	private String qzTrxcode;
	/**操作机构代码*/
	@Excel(name = "操作机构代码", width = 15)
    @ApiModelProperty(value = "操作机构代码")
	private String operbankno;
	/**操作柜员号*/
	@Excel(name = "操作柜员号", width = 15)
    @ApiModelProperty(value = "操作柜员号")
	private String operno;
	/**授权柜员号*/
	@Excel(name = "授权柜员号", width = 15)
    @ApiModelProperty(value = "授权柜员号")
	private String powerteller;
	/**对账标志*/
	@Excel(name = "对账标志", width = 15)
    @ApiModelProperty(value = "对账标志")
	private String chkflag;
	/**交易终态状态*/
	@Excel(name = "交易终态状态", width = 15)
    @ApiModelProperty(value = "交易终态状态")
	private String tradestatus;
	/**手续费金额*/
	@Excel(name = "手续费金额", width = 15)
    @ApiModelProperty(value = "手续费金额")
	private String feeamount;
	/**缴费方式*/
	@Excel(name = "缴费方式", width = 15)
    @ApiModelProperty(value = "缴费方式")
	private String payflag;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String kzflag;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String amount;
	/**银行账号*/
	@Excel(name = "银行账号", width = 15)
    @ApiModelProperty(value = "银行账号")
	private String accountno;
	/**视友号*/
	@Excel(name = "视友号", width = 15)
    @ApiModelProperty(value = "视友号")
	private String uucode;
	/**对应服务账号*/
	@Excel(name = "对应服务账号", width = 15)
    @ApiModelProperty(value = "对应服务账号")
	private String serverno;
	/**用户名称*/
	@Excel(name = "用户名称", width = 15)
    @ApiModelProperty(value = "用户名称")
	private String username;
	/**用户有效身份号*/
	@Excel(name = "用户有效身份号", width = 15)
    @ApiModelProperty(value = "用户有效身份号")
	private String idcard;
	/**交易发起方*/
	@Excel(name = "交易发起方", width = 15)
    @ApiModelProperty(value = "交易发起方")
	private String transorig;
	/**交易落地方*/
	@Excel(name = "交易落地方", width = 15)
    @ApiModelProperty(value = "交易落地方")
	private String transhome;
	/**服务提供商编码*/
	@Excel(name = "服务提供商编码", width = 15)
    @ApiModelProperty(value = "服务提供商编码")
	private String providercode;
	/**服务编码*/
	@Excel(name = "服务编码", width = 15)
    @ApiModelProperty(value = "服务编码")
	private String servicecode;
	/**电视方响应码*/
	@Excel(name = "电视方响应码", width = 15)
    @ApiModelProperty(value = "电视方响应码")
	private String tvRspcode;
	/**电视方响应信息*/
	@Excel(name = "电视方响应信息", width = 15)
    @ApiModelProperty(value = "电视方响应信息")
	private String tvRspmsg;
	/**凭证种类*/
	@Excel(name = "凭证种类", width = 15)
    @ApiModelProperty(value = "凭证种类")
	private String prooftype;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String proofno;
	/**收据号码*/
	@Excel(name = "收据号码", width = 15)
    @ApiModelProperty(value = "收据号码")
	private String receiptNo;
	/**文件批次号*/
	@Excel(name = "文件批次号", width = 15)
    @ApiModelProperty(value = "文件批次号")
	private String fileBatchid;
	/**原交易日期*/
	@Excel(name = "原交易日期", width = 15)
    @ApiModelProperty(value = "原交易日期")
	private String origdate;
	/**原交易流水号*/
	@Excel(name = "原交易流水号", width = 15)
    @ApiModelProperty(value = "原交易流水号")
	private String origserialno;
	/**原交易上核心流水号*/
	@Excel(name = "原交易上核心流水号", width = 15)
    @ApiModelProperty(value = "原交易上核心流水号")
	private String orifhostno;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**备注3*/
	@Excel(name = "备注3", width = 15)
    @ApiModelProperty(value = "备注3")
	private String remark3;
	/**备注4*/
	@Excel(name = "备注4", width = 15)
    @ApiModelProperty(value = "备注4")
	private String remark4;
	/**电视方交易码*/
	@Excel(name = "电视方交易码", width = 15)
    @ApiModelProperty(value = "电视方交易码")
	private String tvTranscode;
	/**报文版本号*/
	@Excel(name = "报文版本号", width = 15)
    @ApiModelProperty(value = "报文版本号")
	private String transversion;
	/**账号类型：*/
	@Excel(name = "账号类型：", width = 15)
    @ApiModelProperty(value = "账号类型：")
	private String noType;
	/**自动冲正标志：*/
	@Excel(name = "自动冲正标志：", width = 15)
    @ApiModelProperty(value = "自动冲正标志：")
	private String czbz;
	/**核心交易流水号*/
	@Excel(name = "核心交易流水号", width = 15)
    @ApiModelProperty(value = "核心交易流水号")
	private String hostseqno;
	/**渠道流水*/
	@Excel(name = "渠道流水", width = 15)
    @ApiModelProperty(value = "渠道流水")
	private String chnldate;
	/**渠道日期*/
	@Excel(name = "渠道日期", width = 15)
    @ApiModelProperty(value = "渠道日期")
	private String chnlseqno;
	/**核心返回日期*/
	@Excel(name = "核心返回日期", width = 15)
    @ApiModelProperty(value = "核心返回日期")
	private String hostdate;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
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
