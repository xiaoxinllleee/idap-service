package org.cmms.modules.sjxf.qtxt.dssf.sfdsywjylsb.entity;

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
 * @Description: 水费代收业务交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_dssf_debtinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_dssf_debtinfo对象", description="水费代收业务交易流水表")
public class Sfdsywjylsb {
    
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String workdate;
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
	/**冲正标识*/
	@Excel(name = "冲正标识", width = 15)
    @ApiModelProperty(value = "冲正标识")
	private String czbz;
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
	/**银行卡号*/
	@Excel(name = "银行卡号", width = 15)
    @ApiModelProperty(value = "银行卡号")
	private String cardno;
	/**用户编号*/
	@Excel(name = "用户编号", width = 15)
    @ApiModelProperty(value = "用户编号")
	private String userNo;
	/**用户名称*/
	@Excel(name = "用户名称", width = 15)
    @ApiModelProperty(value = "用户名称")
	private String username;
	/**用户地址*/
	@Excel(name = "用户地址", width = 15)
    @ApiModelProperty(value = "用户地址")
	private String useraddr;
	/**供水单位*/
	@Excel(name = "供水单位", width = 15)
    @ApiModelProperty(value = "供水单位")
	private String powerUnit;
	/**凭证种类*/
	@Excel(name = "凭证种类", width = 15)
    @ApiModelProperty(value = "凭证种类")
	private String prooftype;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String proofno;
	/**货币号*/
	@Excel(name = "货币号", width = 15)
    @ApiModelProperty(value = "货币号")
	private String currcode;
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
	/**备注1(开账日期)*/
	@Excel(name = "备注1(开账日期)", width = 15)
    @ApiModelProperty(value = "备注1(开账日期)")
	private String remark1;
	/**备注2(接水点编号)*/
	@Excel(name = "备注2(接水点编号)", width = 15)
    @ApiModelProperty(value = "备注2(接水点编号)")
	private String remark2;
	/**备注3(批扣每笔结果)*/
	@Excel(name = "备注3(批扣每笔结果)", width = 15)
    @ApiModelProperty(value = "备注3(批扣每笔结果)")
	private String remark3;
	/**备注4(账户名称)*/
	@Excel(name = "备注4(账户名称)", width = 15)
    @ApiModelProperty(value = "备注4(账户名称)")
	private String remark4;
	/**批扣序号*/
	@Excel(name = "批扣序号", width = 15)
    @ApiModelProperty(value = "批扣序号")
	private String serialNum;
	/**核心返回流水号*/
	@Excel(name = "核心返回流水号", width = 15)
    @ApiModelProperty(value = "核心返回流水号")
	private String hostseqno;
	/**柜面流水号*/
	@Excel(name = "柜面流水号", width = 15)
    @ApiModelProperty(value = "柜面流水号")
	private String chnlseqno;
	/**柜面日期*/
	@Excel(name = "柜面日期", width = 15)
    @ApiModelProperty(value = "柜面日期")
	private String chnldate;
	/**核心日期*/
	@Excel(name = "核心日期", width = 15)
    @ApiModelProperty(value = "核心日期")
	private String hostdate;
	/**账户序号*/
	@Excel(name = "账户序号", width = 15)
    @ApiModelProperty(value = "账户序号")
	private String zhxh;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**导入日期*/
    @ApiModelProperty(value = "导入日期")
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
