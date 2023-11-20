package org.cmms.modules.report.tzsjgl.xtpjdjp.entity;

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
 * @Description: 票据登记蒲
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_XT_PJDJP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_XT_PJDJP对象", description="票据登记蒲")
public class XtPjdjp {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**票据介质*/
	@Excel(name = "票据介质", width = 15)
    @ApiModelProperty(value = "票据介质")
	private String pjjz;
	/**票据类型*/
	@Excel(name = "票据类型", width = 15)
	@ApiModelProperty(value = "票据类型")
	private String pjlx;
	/**票据金额面值（元）*/
	@Excel(name = "票据金额面值（元）", width = 15)
    @ApiModelProperty(value = "票据金额面值（元）")
	private java.math.BigDecimal pjjemz;
	/**出票日期*/
	@Excel(name = "出票日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出票日期 ")
	private java.util.Date cprq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期 ")
	private java.util.Date dqrq;
	/**票据实际到期日*/
	@Excel(name = "票据实际到期日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "票据实际到期日 ")
	private java.util.Date pjsjdqr;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数 ")
	private java.lang.String syts;
	/**出票人*/
	@Excel(name = "出票人", width = 15)
    @ApiModelProperty(value = "出票人 ")
	private java.lang.String cpr;
	/**出票人证件代码*/
	@Excel(name = "出票人证件代码", width = 15)
    @ApiModelProperty(value = "出票人证件代码 ")
	private java.lang.String cprzjhm;
	/**承兑人/行*/
	@Excel(name = "承兑人/行", width = 15)
    @ApiModelProperty(value = "承兑人/行 ")
	private java.lang.String cdr;
	/**承兑人证件代码*/
	@Excel(name = "承兑人证件代码", width = 15)
    @ApiModelProperty(value = "承兑人证件代码 ")
	private java.lang.String cdrzjhm;
	/**贴现申请人*/
	@Excel(name = "贴现申请人", width = 15)
    @ApiModelProperty(value = "贴现申请人 ")
	private java.lang.String zxsqr;
	/**贴现申请人证件代码*/
	@Excel(name = "贴现申请人证件代码", width = 15)
    @ApiModelProperty(value = "贴现申请人证件代码 ")
	private java.lang.String zxsqrzjhm;
	/**贴现申请人行业*/
	@Excel(name = "贴现申请人行业", width = 15)
    @ApiModelProperty(value = "贴现申请人行业 ")
	private java.lang.String zxsqrhy;
	/**贴现申请人地区代码*/
	@Excel(name = "贴现申请人地区代码", width = 15)
    @ApiModelProperty(value = "贴现申请人地区代码 ")
	private java.lang.String zxsqrdqdm;
	/**应付利息（元）*/
	@Excel(name = "应付利息（元）", width = 15)
    @ApiModelProperty(value = "应付利息（元） ")
	private java.math.BigDecimal yflx;
	/**结算金额（元）*/
	@Excel(name = "结算金额（元）", width = 15)
    @ApiModelProperty(value = "结算金额（元） ")
	private java.math.BigDecimal jsje;
	/**成交日*/
	@Excel(name = "成交日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "成交日 ")
	private java.util.Date cjr;
	/**结算日*/
	@Excel(name = "结算日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结算日 ")
	private java.util.Date jsr;
	/**数据日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
}
