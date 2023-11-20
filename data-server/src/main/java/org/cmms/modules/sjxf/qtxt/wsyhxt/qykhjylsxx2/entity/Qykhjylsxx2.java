package org.cmms.modules.sjxf.qtxt.wsyhxt.qykhjylsxx2.entity;

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
 * @Description: 企业客户交易流水信息(当天查证)
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_cb_tranflow")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_cb_tranflow对象", description="企业客户交易流水信息(当天查证)")
public class Qykhjylsxx2 {
    
	/**网银指令流水号*/
	@Excel(name = "网银指令流水号", width = 15)
    @ApiModelProperty(value = "网银指令流水号")
	private String tflFlowno;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private String tflBatchno;
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String tflCstno;
	/**转账类型*/
	@Excel(name = "转账类型", width = 15)
    @ApiModelProperty(value = "转账类型")
	private String tflType;
	/**转账提交时间*/
	@Excel(name = "转账提交时间", width = 15)
    @ApiModelProperty(value = "转账提交时间")
	private String tflSubmittime;
	/**交易发送主机时间*/
	@Excel(name = "交易发送主机时间", width = 15)
    @ApiModelProperty(value = "交易发送主机时间")
	private String tflSendtime;
	/**查证更新时间*/
	@Excel(name = "查证更新时间", width = 15)
    @ApiModelProperty(value = "查证更新时间")
	private String tflUpdatetime;
	/**付款方网点*/
	@Excel(name = "付款方网点", width = 15)
    @ApiModelProperty(value = "付款方网点")
	private String tflPaynode;
	/**付款方账号*/
	@Excel(name = "付款方账号", width = 15)
    @ApiModelProperty(value = "付款方账号")
	private String tflPayacc;
	/**付款方户名*/
	@Excel(name = "付款方户名", width = 15)
    @ApiModelProperty(value = "付款方户名")
	private String tflPayname;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal tflTranamt;
	/**收款方账号开户行*/
	@Excel(name = "收款方账号开户行", width = 15)
    @ApiModelProperty(value = "收款方账号开户行")
	private String tflRcvbank;
	/**收款方账号*/
	@Excel(name = "收款方账号", width = 15)
    @ApiModelProperty(value = "收款方账号")
	private String tflRcvacc;
	/**收款方户名*/
	@Excel(name = "收款方户名", width = 15)
    @ApiModelProperty(value = "收款方户名")
	private String tflRcvname;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private java.math.BigDecimal tflFee1;
	/**指令状态*/
	@Excel(name = "指令状态", width = 15)
    @ApiModelProperty(value = "指令状态")
	private String tflStt;
	/**开户机构号(客户)*/
	@Excel(name = "开户机构号(客户)", width = 15)
    @ApiModelProperty(value = "开户机构号(客户)")
	private String tflMemberbankid;
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
