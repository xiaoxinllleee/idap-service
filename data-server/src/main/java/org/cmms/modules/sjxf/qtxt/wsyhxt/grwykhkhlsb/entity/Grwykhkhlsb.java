package org.cmms.modules.sjxf.qtxt.wsyhxt.grwykhkhlsb.entity;

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
 * @Description: 个人网银跨行快汇流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_pb_payment_flow")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_pb_payment_flow对象", description="个人网银跨行快汇流水表")
public class Grwykhkhlsb {
    
	/**开户机构号*/
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	private String ppfOpennode;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private String ppfFlowno;
	/**个人客户号*/
	@Excel(name = "个人客户号", width = 15)
    @ApiModelProperty(value = "个人客户号")
	private String ppfCstno;
	/**交易代码*/
	@Excel(name = "交易代码", width = 15)
    @ApiModelProperty(value = "交易代码")
	private String ppfBsncode;
	/**付款账号*/
	@Excel(name = "付款账号", width = 15)
    @ApiModelProperty(value = "付款账号")
	private String ppfPayacc;
	/**付款行*/
	@Excel(name = "付款行", width = 15)
    @ApiModelProperty(value = "付款行")
	private String ppfPaybank;
	/**收款账号*/
	@Excel(name = "收款账号", width = 15)
    @ApiModelProperty(value = "收款账号")
	private String ppfRcvacc;
	/**收款方户名*/
	@Excel(name = "收款方户名", width = 15)
    @ApiModelProperty(value = "收款方户名")
	private String ppfRcvname;
	/**收款行*/
	@Excel(name = "收款行", width = 15)
    @ApiModelProperty(value = "收款行")
	private String ppfRcvbank;
	/**提交时间*/
	@Excel(name = "提交时间", width = 15)
    @ApiModelProperty(value = "提交时间")
	private String ppfSubtime;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal ppfTranamt;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String ppfStt;
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
