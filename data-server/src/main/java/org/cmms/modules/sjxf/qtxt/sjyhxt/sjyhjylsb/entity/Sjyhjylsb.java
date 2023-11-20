package org.cmms.modules.sjxf.qtxt.sjyhxt.sjyhjylsb.entity;

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
 * @Description: 交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Mbs_mb_tranflow")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Mbs_mb_tranflow对象", description="交易流水表")
public class Sjyhjylsb {
    
	/**渠道流水号(主键)*/
	@Excel(name = "渠道流水号", width = 15)
    @ApiModelProperty(value = "渠道流水号")
	private String flowno;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String cstno;
	/**交易代码*/
	@Excel(name = "交易代码", width = 15)
    @ApiModelProperty(value = "交易代码")
	private String bsncode;
	/**付款方账号*/
	@Excel(name = "付款方账号", width = 15)
    @ApiModelProperty(value = "付款方账号")
	private String payacc;
	/**付款方户名*/
	@Excel(name = "付款方户名", width = 15)
    @ApiModelProperty(value = "付款方户名")
	private String payname;
	/**收款方账号*/
	@Excel(name = "收款方账号", width = 15)
    @ApiModelProperty(value = "收款方账号")
	private String rcvacc;
	/**收款方户名*/
	@Excel(name = "收款方户名", width = 15)
    @ApiModelProperty(value = "收款方户名")
	private String rcvname;
	/**收款方开户行*/
	@Excel(name = "收款方开户行", width = 15)
    @ApiModelProperty(value = "收款方开户行")
	private String rcvbank;
	/**用户提交时间*/
	@Excel(name = "用户提交时间", width = 15)
    @ApiModelProperty(value = "用户提交时间")
	private String subtime;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String tranamt;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private String fee;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stt;
	/**交易渠道*/
	@Excel(name = "交易渠道", width = 15)
    @ApiModelProperty(value = "交易渠道")
	private String channel;
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
