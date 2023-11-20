package org.cmms.modules.sjxf.qtxt.wsyhxt.grkhjylsxx.entity;

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
 * @Description: 个人客户交易流水信息(当天查证)
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_pb_tranflow")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_pb_tranflow对象", description="个人客户交易流水信息(当天查证)")
public class Grkhjylsxx {
    
	/**网银交易流水号*/
	@Excel(name = "网银交易流水号", width = 15)
    @ApiModelProperty(value = "网银交易流水号")
	private String trfFlowno;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private String trfBatno;
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String trfCstno;
	/**转账类型0:行内1:他行*/
	@Excel(name = "转账类型", width = 15)
    @ApiModelProperty(value = "转账类型")
	private String trfTrantype;
	/**渠道标志注释:从数据库中统计,trf_channelflag有1:小额,2:大额,4:农信银,5:同城,空:行内五种状态*/
	@Excel(name = "渠道标志注释", width = 15)
    @ApiModelProperty(value = "渠道标志注释")
	private String trfChannelflag;
	/**用户提交时间*/
	@Excel(name = "用户提交时间", width = 15)
    @ApiModelProperty(value = "用户提交时间")
	private String trfSubtime;
	/**查证更新时间YYYYMMDDHHMISS*/
	@Excel(name = "查证更新时间", width = 15)
    @ApiModelProperty(value = "查证更新时间")
	private String trfUpdatetime;
	/**付款方账号开户行(账户下挂机构)*/
	@Excel(name = "付款方账号开户行", width = 15)
    @ApiModelProperty(value = "付款方账号开户行")
	private String trfPayaccOpennode;
	/**付款方账号*/
	@Excel(name = "付款方账号", width = 15)
    @ApiModelProperty(value = "付款方账号")
	private String trfPayacc;
	/**付款方户名*/
	@Excel(name = "付款方户名", width = 15)
    @ApiModelProperty(value = "付款方户名")
	private String trfPayname;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal trfTranamt;
	/**收款方开户行*/
	@Excel(name = "收款方开户行", width = 15)
    @ApiModelProperty(value = "收款方开户行")
	private String trfRcvbank;
	/**收款方账号*/
	@Excel(name = "收款方账号", width = 15)
    @ApiModelProperty(value = "收款方账号")
	private String trfRcvacc;
	/**收款方户名*/
	@Excel(name = "收款方户名", width = 15)
    @ApiModelProperty(value = "收款方户名")
	private String trfRcvname;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private java.math.BigDecimal trfFee2;
	/**指令状态(02:落地时被拒绝；20:交易成功；30:交易失败)*/
	@Excel(name = "指令状态", width = 15)
    @ApiModelProperty(value = "指令状态")
	private String trfStt;
	/**付款方账号开户行*/
	@Excel(name = "付款方账号开户行", width = 15)
    @ApiModelProperty(value = "付款方账号开户行")
	private String trfOpennode;
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
