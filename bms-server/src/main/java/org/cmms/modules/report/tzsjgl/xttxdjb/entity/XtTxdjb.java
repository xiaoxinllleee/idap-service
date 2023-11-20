package org.cmms.modules.report.tzsjgl.xttxdjb.entity;

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
 * @Description: 贴现登记簿
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_XT_TXDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_XT_TXDJB对象", description="贴现登记簿")
public class XtTxdjb {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**贴现日期*/
	@Excel(name = "贴现日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贴现日期 ")
	private java.util.Date txrq;
	/**申请单位*/
	@Excel(name = "申请单位", width = 15)
    @ApiModelProperty(value = "申请单位 ")
	private java.lang.String sqdw;
	/**承兑行*/
	@Excel(name = "票据号码", width = 15)
    @ApiModelProperty(value = "票据号码 ")
	private java.lang.String cdh;
	/**出票日*/
	@Excel(name = "出票日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出票日 ")
	private java.util.Date cpr;
	/**到期日*/
	@Excel(name = "到期日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日 ")
	private java.util.Date dqr;
	/**贴现金额*/
	@Excel(name = "贴现金额", width = 15)
    @ApiModelProperty(value = "贴现金额 ")
	private java.math.BigDecimal txje;
	/**贴现利率*/
	@Excel(name = "贴现利率（%）", width = 15)
    @ApiModelProperty(value = "贴现利率（%） ")
	private java.math.BigDecimal txll;
	/**贴现余额*/
	@Excel(name = "贴现余额", width = 15)
    @ApiModelProperty(value = "贴现余额 ")
	private java.math.BigDecimal txye;
	/**申请支行*/
	@Excel(name = "申请支行", width = 15)
    @ApiModelProperty(value = "申请支行 ")
	private java.lang.String sqzh;
	/**行业*/
	@Excel(name = "行业", width = 15)
    @ApiModelProperty(value = "行业 ")
	private java.lang.String hy;
	/**规模*/
	@Excel(name = "规模", width = 15)
    @ApiModelProperty(value = "规模 ")
	private java.lang.String gm;
	/**控股类型*/
	@Excel(name = "控股类型", width = 15)
    @ApiModelProperty(value = "控股类型 ")
	private java.lang.String kglx;
	/**是否录入票交所*/
	//@Excel(name = "是否录入票交所", width = 15)
    @ApiModelProperty(value = "是否录入票交所 ")
	private java.lang.String sflrpjs;
	/**分摊后金额*/
	@Excel(name = "分摊后金额", width = 15)
    @ApiModelProperty(value = "分摊后金额 ")
	private java.math.BigDecimal fthje;
	/**基准日期*/
	@Excel(name = "基准日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "基准日期 ")
	private java.util.Date jzrq;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
	@ApiModelProperty(value = "剩余天数 ")
	private java.lang.String syts;


}
