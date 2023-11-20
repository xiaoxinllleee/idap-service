package org.cmms.modules.report.tzsjgl.nyhgdjb.entity;

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
 * @Description: 宁远回购登记簿
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_NY_HGDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_NY_HGDJB对象", description="宁远回购登记簿")
public class Nyhgdjb {
	/**数据日期*/
	//@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String hx;
	/**申请编号*/
	@Excel(name = "申请编号", width = 15)
    @ApiModelProperty(value = "申请编号")
	private String sqbh;
	/**内部账号*/
	@Excel(name = "内部账号", width = 15)
    @ApiModelProperty(value = "内部账号")
	private String nbzh;
	/**成交单编号*/
	@Excel(name = "成交单编号", width = 15)
    @ApiModelProperty(value = "成交单编号")
	private String cjdbh;
	/**回购方向*/
	@Excel(name = "回购方向", width = 15)
    @ApiModelProperty(value = "回购方向")
	private String hgfx;
	/**交易对手方*/
	@Excel(name = "交易对手方", width = 15)
    @ApiModelProperty(value = "交易对手方")
	private String jydsf;
	/**交易对手类别*/
	@Excel(name = "交易对手类别", width = 15)
    @ApiModelProperty(value = "交易对手类别")
	private String jydslb;
	/**交易品种*/
	@Excel(name = "交易品种", width = 15)
    @ApiModelProperty(value = "交易品种")
	private String jypz;
	/**实际占款天数（天）*/
	@Excel(name = "实际占款天数（天）", width = 15)
    @ApiModelProperty(value = "实际占款天数（天）")
	private Integer sjzkts;
	/**回购利率（%）*/
	@Excel(name = "回购利率（%）", width = 15)
    @ApiModelProperty(value = "回购利率（%）")
	private java.math.BigDecimal hgll;
	/**券面总额（万元）*/
	@Excel(name = "券面总额（万元）", width = 15)
    @ApiModelProperty(value = "券面总额（万元）")
	private java.math.BigDecimal qmze;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "交易日期")
	private Date jyrq;
	/**交易金额（元）*/
	@Excel(name = "交易金额（元）", width = 15)
    @ApiModelProperty(value = "交易金额（元）")
	private java.math.BigDecimal jyje;
	/**首次结算日*/
	@Excel(name = "首次结算日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "首次结算日")
	private Date scjsr;
	/**到期结算金额（元）*/
	@Excel(name = "到期结算金额（元）", width = 15)
    @ApiModelProperty(value = "到期结算金额（元）")
	private java.math.BigDecimal dqjsje;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private Integer syts;
	/**到期结算日*/
	@Excel(name = "到期结算日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期结算日")
	private Date dqjsr;
	/**报表日期*/
	@Excel(name = "报表日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "报表日期")
	private Date bbrq;
	/**应收/付利息金额*/
	@Excel(name = "应收/付利息金额", width = 15)
    @ApiModelProperty(value = "应收/付利息金额")
	private java.math.BigDecimal ysflxje;
	/**累计计提利息（元）*/
	@Excel(name = "累计计提利息（元）", width = 15)
    @ApiModelProperty(value = "累计计提利息（元）")
	private java.math.BigDecimal ljjtlx;
	/**累计结算金额（元）*/
	@Excel(name = "累计结算金额（元）", width = 15)
    @ApiModelProperty(value = "累计结算金额（元）")
	private java.math.BigDecimal ljjsje;
	/**到期利息（元）*/
	@Excel(name = "到期利息（元）", width = 15)
    @ApiModelProperty(value = "到期利息（元）")
	private java.math.BigDecimal dqlx;
	/**挂账本金（元）*/
	@Excel(name = "挂账本金（元）", width = 15)
    @ApiModelProperty(value = "挂账本金（元）")
	private java.math.BigDecimal gzbj;
	/**挂账利息（元）*/
	@Excel(name = "挂账利息（元）", width = 15)
    @ApiModelProperty(value = "挂账利息（元）")
	private java.math.BigDecimal gzlx;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
    @ApiModelProperty(value = "五级分类")
	private String wjfl;
	/**业务状况*/
	@Excel(name = "业务状况", width = 15)
    @ApiModelProperty(value = "业务状况")
	private String ywzk;
	/**用债券质押比例（%）*/
	@Excel(name = "用债券质押比例（%）", width = 15)
    @ApiModelProperty(value = "用债券质押比例（%）")
	private java.math.BigDecimal yzqzybl;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String jgh;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**资产等级*/
	@Excel(name = "资产等级", width = 15)
    @ApiModelProperty(value = "资产等级")
	private String zcdj;
}
