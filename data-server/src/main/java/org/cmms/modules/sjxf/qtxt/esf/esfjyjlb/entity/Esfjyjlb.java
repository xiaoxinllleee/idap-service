package org.cmms.modules.sjxf.qtxt.esf.esfjyjlb.entity;

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
 * @Description: 二手房交易记录表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("Ibus_esfjy_debtinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_esfjy_debtinfo对象", description="二手房交易记录表")
public class Esfjyjlb {
    
	/**前置日期*/
	@Excel(name = "前置日期", width = 15)
    @ApiModelProperty(value = "前置日期")
	private String workdate;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String acctDate;
	/**上核心交易流水号*/
	@Excel(name = "上核心交易流水号", width = 15)
    @ApiModelProperty(value = "上核心交易流水号")
	private String hostserialno;
	/**柜面交易代码*/
	@Excel(name = "柜面交易代码", width = 15)
    @ApiModelProperty(value = "柜面交易代码")
	private String gmTrxcode;
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
	/**卖方姓名*/
	@Excel(name = "卖方姓名", width = 15)
    @ApiModelProperty(value = "卖方姓名")
	private String csfxm;
	/**卖方证件号*/
	@Excel(name = "卖方证件号", width = 15)
    @ApiModelProperty(value = "卖方证件号")
	private String csfzjhm;
	/**买方姓名*/
	@Excel(name = "买方姓名", width = 15)
    @ApiModelProperty(value = "买方姓名")
	private String zrfxm;
	/**买方证件号*/
	@Excel(name = "买方证件号", width = 15)
    @ApiModelProperty(value = "买方证件号")
	private String zrfzjhm;
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
	/**业务宗号*/
	@Excel(name = "业务宗号", width = 15)
    @ApiModelProperty(value = "业务宗号")
	private String ywzh;
	/**监管协议*/
	@Excel(name = "监管协议", width = 15)
    @ApiModelProperty(value = "监管协议")
	private String tgxy;
	/**监管金额*/
	@Excel(name = "监管金额", width = 15)
    @ApiModelProperty(value = "监管金额")
	private String tgje;
	/**到账通知*/
	@Excel(name = "到账通知", width = 15)
    @ApiModelProperty(value = "到账通知")
	private String dztz;
	/**撤销交易上核心流水号*/
	@Excel(name = "撤销交易上核心流水号", width = 15)
    @ApiModelProperty(value = "撤销交易上核心流水号")
	private String orifhostno;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**自动冲正标志*/
	@Excel(name = "自动冲正标志", width = 15)
    @ApiModelProperty(value = "自动冲正标志")
	private String zdcz;
	/**监管单位账号*/
	@Excel(name = "监管单位账号", width = 15)
    @ApiModelProperty(value = "监管单位账号")
	private String flzh;
	/**划款标志*/
	@Excel(name = "划款标志", width = 15)
    @ApiModelProperty(value = "划款标志")
	private String fkbz;
	/**阶段标志*/
	@Excel(name = "阶段标志", width = 15)
    @ApiModelProperty(value = "阶段标志")
	private String jdbz;
	/**县级编码 01长沙县*/
	@Excel(name = "县级编码 01长沙县", width = 15)
    @ApiModelProperty(value = "县级编码 01长沙县")
	private String xjbm;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String zt;
	/**标志*/
	@Excel(name = "标志", width = 15)
    @ApiModelProperty(value = "标志")
	private String flag;
	/**渠道流水号*/
	@Excel(name = "渠道流水号", width = 15)
    @ApiModelProperty(value = "渠道流水号")
	private String chnlseqno;
	/**渠道日期*/
	@Excel(name = "渠道日期", width = 15)
    @ApiModelProperty(value = "渠道日期")
	private String chnldate;
	/**核心流水号*/
	@Excel(name = "核心流水号", width = 15)
    @ApiModelProperty(value = "核心流水号")
	private String hostseqno;
	/**核心日期*/
	@Excel(name = "核心日期", width = 15)
    @ApiModelProperty(value = "核心日期")
	private String hostdate;
	/**账户序号*/
	@Excel(name = "账户序号", width = 15)
    @ApiModelProperty(value = "账户序号")
	private String zhxh;
	/**原系统标识*/
	@Excel(name = "原系统标识", width = 15)
    @ApiModelProperty(value = "原系统标识")
	private String sourceId;
	/**加载类型*/
	@Excel(name = "加载类型", width = 15)
    @ApiModelProperty(value = "加载类型")
	private String loadType;
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
