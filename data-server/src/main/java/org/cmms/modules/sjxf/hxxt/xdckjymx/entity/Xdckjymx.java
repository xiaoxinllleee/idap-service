package org.cmms.modules.sjxf.hxxt.xdckjymx.entity;

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
 * @Description: 协定存款交易明细
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_incr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_incr对象", description="协定存款交易明细")
public class Xdckjymx {
    
	/**行号*/
	@Excel(name = "行号", width = 15)
    @ApiModelProperty(value = "行号")
	private String instNo;
	/**协定账号*/
	@Excel(name = "协定账号", width = 15)
    @ApiModelProperty(value = "协定账号")
	private String acctNo;
	/**协定号*/
	@Excel(name = "协定号", width = 15)
    @ApiModelProperty(value = "协定号")
	private String contractNo;
	/**协定金额*/
	@Excel(name = "协定金额", width = 15)
    @ApiModelProperty(value = "协定金额")
	private java.math.BigDecimal contractAmt;
	/**协定生效日*/
	@Excel(name = "协定生效日", width = 15)
    @ApiModelProperty(value = "协定生效日")
	private Integer contractEftDate;
	/**协定到期日*/
	@Excel(name = "协定到期日", width = 15)
    @ApiModelProperty(value = "协定到期日")
	private Integer contractMtyDate;
	/**自动延期标志*/
	@Excel(name = "自动延期标志", width = 15)
    @ApiModelProperty(value = "自动延期标志")
	private String autoExtFlag;
	/**协定状态*/
	@Excel(name = "协定状态", width = 15)
    @ApiModelProperty(value = "协定状态")
	private String contractStatus;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txDate;
	/**交易行号*/
	@Excel(name = "交易行号", width = 15)
    @ApiModelProperty(value = "交易行号")
	private Integer txInstNo;
	/**交易分行号*/
	@Excel(name = "交易分行号", width = 15)
    @ApiModelProperty(value = "交易分行号")
	private Integer txBrhnNo;
	/**交易柜员*/
	@Excel(name = "交易柜员", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private Integer txTelrNo;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private Integer txJrnlNo;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private Integer txTime;
	/**交易功能*/
	@Excel(name = "交易功能", width = 15)
    @ApiModelProperty(value = "交易功能")
	private String txFuncNo;
	/**协定合同期*/
	@Excel(name = "协定合同期", width = 15)
    @ApiModelProperty(value = "协定合同期")
	private Integer contrTermLength;
	/**基期*/
	@Excel(name = "基期", width = 15)
    @ApiModelProperty(value = "基期")
	private String contrTermBasic;
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
//	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
//    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
//	private Integer dtnum;
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
