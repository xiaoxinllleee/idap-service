package org.cmms.modules.report.tzsjgl.xtyhcdhptz.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 湘潭银行承兑汇票台账
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_XT_YHCDHPTZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_XT_YHCDHPTZ对象", description="湘潭银行承兑汇票台账")
public class XtYhcdhptz {

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
	/**台账类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
	private String tzlx;
	/**出票人行业分类*/
	@Excel(name = "出票人行业分类", width = 15)
    @ApiModelProperty(value = "出票人行业分类")
	private String cprhyfl;
	/**票号*/
	@Excel(name = "票号", width = 15)
    @ApiModelProperty(value = "票号")
	private String ph;
	/**出票人*/
	@Excel(name = "出票人", width = 15)
    @ApiModelProperty(value = "出票人")
	private String cpr;
	/**收款人*/
	@Excel(name = "收款人", width = 15)
    @ApiModelProperty(value = "收款人")
	private String skr;
	/**出票日期*/
	@Excel(name = "出票日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出票日期")
	private Date cprq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**出票金额*/
	@Excel(name = "出票金额", width = 15)
	@ApiModelProperty(value = "出票金额")
	private java.math.BigDecimal cpje;
	/**余额（元）*/
	@Excel(name = "余额", width = 15)
	@ApiModelProperty(value = "余额")
	private java.math.BigDecimal ye;
	/**解付金额（元）*/
	@Excel(name = "解付金额", width = 15)
	@ApiModelProperty(value = "解付金额")
	private java.math.BigDecimal jfje;
	/**解付日期*/
	@Excel(name = "解付日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "解付日期 ")
	private Date jfrq;
	/**保证金比例*/
	@Excel(name = "保证金比例", width = 15)
	@ApiModelProperty(value = "保证金比例")
	private String bzjbl;
	/**保证金金额（元）*/
	@Excel(name = "保证金金额", width = 15)
	@ApiModelProperty(value = "保证金金额")
	private java.math.BigDecimal bzjje;
	/**申请支行*/
	@Excel(name = "申请支行", width = 15)
	@ApiModelProperty(value = "申请支行")
	private String sqzh;
	/**行业*/
	@Excel(name = "行业", width = 15)
	@ApiModelProperty(value = "行业")
	private String hy;
	/**控股类型*/
	@Excel(name = "控股类型", width = 15)
	@ApiModelProperty(value = "控股类型")
	private String kglx;

	/**规模*/
	@Excel(name = "规模", width = 15)
	@ApiModelProperty(value = "规模")
	private String gm;
	/**基准日期*/
	@Excel(name = "基准日期", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "基准日期")
	private Date jzrq;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private String syts;

}
