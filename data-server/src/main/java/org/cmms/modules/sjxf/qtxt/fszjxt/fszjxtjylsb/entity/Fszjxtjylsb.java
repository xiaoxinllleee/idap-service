package org.cmms.modules.sjxf.qtxt.fszjxt.fszjxtjylsb.entity;

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
 * @Description: 房税自缴系统交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_spmsh_debtinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_spmsh_debtinfo对象", description="房税自缴系统交易流水表")
public class Fszjxtjylsb {
    
	/**前置日期*/
	@Excel(name = "前置日期", width = 15)
    @ApiModelProperty(value = "前置日期")
	private String workdate;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String acctDate;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private String serialno;
	/**上核心交易流水号*/
	@Excel(name = "上核心交易流水号", width = 15)
    @ApiModelProperty(value = "上核心交易流水号")
	private String hostserialno;
	/**柜面交易代码*/
	@Excel(name = "柜面交易代码", width = 15)
    @ApiModelProperty(value = "柜面交易代码")
	private String gmTrxcode;
	/**核心交易代码*/
	@Excel(name = "核心交易代码", width = 15)
    @ApiModelProperty(value = "核心交易代码")
	private String hxTrxcode;
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
	/**手续费账号*/
	@Excel(name = "手续费账号", width = 15)
    @ApiModelProperty(value = "手续费账号")
	private String feeaccount;
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
	/**契税实缴金额*/
	@Excel(name = "契税实缴金额", width = 15)
    @ApiModelProperty(value = "契税实缴金额")
	private String qs;
	/**印花税实缴金额*/
	@Excel(name = "印花税实缴金额", width = 15)
    @ApiModelProperty(value = "印花税实缴金额")
	private String yhs;
	/**银行账号*/
	@Excel(name = "银行账号", width = 15)
    @ApiModelProperty(value = "银行账号")
	private String accountno;
	/**自缴ID*/
	@Excel(name = "自缴ID", width = 15)
    @ApiModelProperty(value = "自缴ID")
	private String zjid;
	/**业务宗号*/
	@Excel(name = "业务宗号", width = 15)
    @ApiModelProperty(value = "业务宗号")
	private String ywzh;
	/**货币号*/
	@Excel(name = "货币号", width = 15)
    @ApiModelProperty(value = "货币号")
	private String currcode;
	/**凭证种类*/
	@Excel(name = "凭证种类", width = 15)
    @ApiModelProperty(value = "凭证种类")
	private String prooftype;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String proofno;
	/**原交易上核心流水号*/
	@Excel(name = "原交易上核心流水号", width = 15)
    @ApiModelProperty(value = "原交易上核心流水号")
	private String orifhostno;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**备注1经办机构名称*/
	@Excel(name = "备注1经办机构名称", width = 15)
    @ApiModelProperty(value = "备注1经办机构名称")
	private String remark1;
	/**自动冲正标志*/
	@Excel(name = "自动冲正标志", width = 15)
    @ApiModelProperty(value = "自动冲正标志")
	private String zdcz;
	/**入账账号（分录账号）*/
	@Excel(name = "入账账号（分录账号）", width = 15)
    @ApiModelProperty(value = "入账账号（分录账号）")
	private String flzh;
	/**购房人姓名*/
	@Excel(name = "购房人姓名", width = 15)
    @ApiModelProperty(value = "购房人姓名")
	private String username;
	/**税务机关*/
	@Excel(name = "税务机关", width = 15)
    @ApiModelProperty(value = "税务机关")
	private String swjg;
	/**收据编号*/
	@Excel(name = "收据编号", width = 15)
    @ApiModelProperty(value = "收据编号")
	private String sjbh;
	/**核心流水号*/
	@Excel(name = "核心流水号", width = 15)
    @ApiModelProperty(value = "核心流水号")
	private String hostseqno;
	/**渠道流水号*/
	@Excel(name = "渠道流水号", width = 15)
    @ApiModelProperty(value = "渠道流水号")
	private String chnlseqno;
	/**渠道时间*/
	@Excel(name = "渠道时间", width = 15)
    @ApiModelProperty(value = "渠道时间")
	private String chnldate;
	/**主机时间*/
	@Excel(name = "主机时间", width = 15)
    @ApiModelProperty(value = "主机时间")
	private String hostdate;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**错误码*/
	@Excel(name = "错误码", width = 15)
    @ApiModelProperty(value = "错误码")
	private String errcode;
	/**错误信息*/
	@Excel(name = "错误信息", width = 15)
    @ApiModelProperty(value = "错误信息")
	private String errmsg;
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
/*	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
