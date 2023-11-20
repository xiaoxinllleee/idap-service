package org.cmms.modules.sjxf.qtxt.czzb.ywjylsb.entity;

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
 * @Description: 财政直补业务交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_czzb_debtinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_czzb_debtinfo对象", description="财政直补业务交易流水表")
public class Ywjylsb {
    
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
	/**财政方交易代码*/
	@Excel(name = "财政方交易代码", width = 15)
    @ApiModelProperty(value = "财政方交易代码")
	private String czTrxcode;
	/**柜面交易代码*/
	@Excel(name = "柜面交易代码", width = 15)
    @ApiModelProperty(value = "柜面交易代码")
	private String gmTrxcode;
	/**前置交易代码*/
	@Excel(name = "前置交易代码", width = 15)
    @ApiModelProperty(value = "前置交易代码")
	private String qzTrxcode;
	/**对账标志*/
	@Excel(name = "对账标志", width = 15)
    @ApiModelProperty(value = "对账标志")
	private String chkflag;
	/**交易终态状态*/
	@Excel(name = "交易终态状态", width = 15)
    @ApiModelProperty(value = "交易终态状态")
	private String tradestatus;
	/**核心返回码*/
	@Excel(name = "核心返回码", width = 15)
    @ApiModelProperty(value = "核心返回码")
	private String hostcode;
	/**交易信息*/
	@Excel(name = "交易信息", width = 15)
    @ApiModelProperty(value = "交易信息")
	private String errmsg;
	/**手续费账号*/
	@Excel(name = "手续费账号", width = 15)
    @ApiModelProperty(value = "手续费账号")
	private String feeaccount;
	/**手续费金额*/
	@Excel(name = "手续费金额", width = 15)
    @ApiModelProperty(value = "手续费金额")
	private String feeamount;
	/**文件批次号*/
	@Excel(name = "文件批次号", width = 15)
    @ApiModelProperty(value = "文件批次号")
	private String fileBatchid;
	/**县级行政区划编码*/
	@Excel(name = "县级行政区划编码", width = 15)
    @ApiModelProperty(value = "县级行政区划编码")
	private String xjxzqbm;
	/**直补个人编号*/
	@Excel(name = "直补个人编号", width = 15)
    @ApiModelProperty(value = "直补个人编号")
	private String zbgrbh;
	/**客户账号*/
	@Excel(name = "客户账号", width = 15)
    @ApiModelProperty(value = "客户账号")
	private String khzh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户身份证号码*/
	@Excel(name = "客户身份证号码", width = 15)
    @ApiModelProperty(value = "客户身份证号码")
	private String khsfz;
	/**直补种类编码*/
	@Excel(name = "直补种类编码", width = 15)
    @ApiModelProperty(value = "直补种类编码")
	private String zbzlbm;
	/**直补种类名称*/
	@Excel(name = "直补种类名称", width = 15)
    @ApiModelProperty(value = "直补种类名称")
	private String zbzlmc;
	/**直补金额*/
	@Excel(name = "直补金额", width = 15)
    @ApiModelProperty(value = "直补金额")
	private String zbje;
	/**直补摘要*/
	@Excel(name = "直补摘要", width = 15)
    @ApiModelProperty(value = "直补摘要")
	private String zbzy;
	/**分录账号*/
	@Excel(name = "分录账号", width = 15)
    @ApiModelProperty(value = "分录账号")
	private String accountno;
	/**打印次数*/
	@Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
	private String prtNum;
	/**货币号*/
	@Excel(name = "货币号", width = 15)
    @ApiModelProperty(value = "货币号")
	private String currcode;
	/**自动冲正标志*/
	@Excel(name = "自动冲正标志", width = 15)
    @ApiModelProperty(value = "自动冲正标志")
	private String zdcz;
	/**主办行*/
	@Excel(name = "主办行", width = 15)
    @ApiModelProperty(value = "主办行")
	private String zbhjgm;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**核心返回流水号?*/
	@Excel(name = "核心返回流水号?", width = 15)
    @ApiModelProperty(value = "核心返回流水号?")
	private String hostseqno;
	/**缴费人员顺序*/
	@Excel(name = "缴费人员顺序", width = 15)
    @ApiModelProperty(value = "缴费人员顺序")
	private String seqno;
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
