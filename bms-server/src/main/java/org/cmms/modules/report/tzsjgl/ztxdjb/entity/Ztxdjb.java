package org.cmms.modules.report.tzsjgl.ztxdjb.entity;

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
 * @Description: 转贴现登记簿
 * @Author: jeecg-boot
 * @Date:   2023-05-10
 * @Version: V1.0
 */
@Data
@TableName("rep_tzgl_xt_ztxdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_tzgl_xt_ztxdjb对象", description="转贴现登记簿")
public class Ztxdjb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**票据介质*/
	@Excel(name = "票号", width = 15)
    @ApiModelProperty(value = "票号")
	private String ph;
	/**票据介质*/
	@Excel(name = "票据介质", width = 15)
    @ApiModelProperty(value = "票据介质")
	private String pjjz;
	/**票据类别*/
	@Excel(name = "票据类别", width = 15)
    @ApiModelProperty(value = "票据类别")
	private String pjlb;
	/**票据金额面值（元）*/
	@Excel(name = "票据金额面值（元）", width = 15)
    @ApiModelProperty(value = "票据金额面值（元）")
	private java.math.BigDecimal pjjemz;
	/**分摊数*/
	@Excel(name = "分摊数", width = 15)
	@ApiModelProperty(value = "分摊数")
	private java.math.BigDecimal fts;
	/**剩余天数*/
	@Excel(name = "期限", width = 15)
	@ApiModelProperty(value = "期限")
	private Integer syts;
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
	/**票据实际到期日*/
	@Excel(name = "票据实际到期日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "票据实际到期日")
	private Date pjsjdqr;
	/**基准日期*/
	//@Excel(name = "基准日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "基准日期")
	private Date jzrq;

	/**出票人*/
	@Excel(name = "出票人", width = 15)
    @ApiModelProperty(value = "出票人")
	private String cpr;
	/**出票人证件代码*/
	//@Excel(name = "出票人证件代码", width = 15)
    @ApiModelProperty(value = "出票人证件代码")
	private String cprzjhm;
	/**承兑人/行*/
	@Excel(name = "承兑人/行", width = 15)
    @ApiModelProperty(value = "承兑人/行")
	private String cdr;
	/**承兑人证件代码*/
	//@Excel(name = "承兑人证件代码", width = 15)
    @ApiModelProperty(value = "承兑人证件代码")
	private String cdrzjdm;
	/**贴现申请人*/
	//@Excel(name = "贴现申请人", width = 15)
    @ApiModelProperty(value = "贴现申请人")
	private String txsqr;
	/**应付利息（元）*/
	@Excel(name = "应付利息（元）", width = 15)
    @ApiModelProperty(value = "应付利息（元）")
	private java.math.BigDecimal yflx;
	/**结算金额（元）*/
	@Excel(name = "结算金额（元）", width = 15)
    @ApiModelProperty(value = "结算金额（元）")
	private java.math.BigDecimal jsje;
	/**成交日*/
	@Excel(name = "成交日", width = 15)
    @ApiModelProperty(value = "成交日")
	private String cjr;
	/**结算日*/
	@Excel(name = "结算日", width = 15)
    @ApiModelProperty(value = "结算日")
	private String jsr;
	/**基础数据票据编号*/
	//@Excel(name = "基础数据票据编号", width = 15)
    @ApiModelProperty(value = "基础数据票据编号")
	private String jcsjpjbh;
	/**贴现行*/
	@Excel(name = "贴现行", width = 15)
	@ApiModelProperty(value = "贴现行")
	private String txh;
	/**转帖利率*/
	@Excel(name = "转帖利率", width = 15)
	@ApiModelProperty(value = "转帖利率")
	private java.math.BigDecimal ztll;
}
