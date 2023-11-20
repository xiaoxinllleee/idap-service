package org.cmms.modules.report.tzsjgl.xtxjtzqkb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 湘潭现券投资情况表
 * @Author: jeecg-boot
 * @Date:   2022-05-07
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_tzgl_xt_xqtzqkb对象", description="湘潭现券投资情况表")
public class XtxjtzqkbVO {
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**债券代码*/
	@Excel(name = "债券代码", width = 15)
	@ApiModelProperty(value = "债券代码 ")
	private java.lang.String zjdm;
	/**债券名称*/
	@Excel(name = "债券名称", width = 15)
	@ApiModelProperty(value = "债券名称 ")
	private java.lang.String zjmc;
	/**债券票面利率*/
	@Excel(name = "债券票面利率", width = 15)
	@ApiModelProperty(value = "债券票面利率 ")
	private java.lang.String zjpmll;
	/**票面金额（元）*/
	@Excel(name = "票面金额（元）", width = 15)
	@ApiModelProperty(value = "票面金额（元） ")
	private java.math.BigDecimal pmjey;
	/**票面金额（万元）*/
	//@Excel(name = "票面金额（万元）", width = 15)
	@ApiModelProperty(value = "票面金额（万元） ")
	private java.math.BigDecimal pmjewy;
	/**当前账面余额（元)*/
	@Excel(name = "当前账面余额（元)", width = 15)
	@ApiModelProperty(value = "当前账面余额（元) ")
	private java.math.BigDecimal dqzmjey;
	/**当前账面余额（万元)*/
	//@Excel(name = "当前账面余额（万元)", width = 15)
	@ApiModelProperty(value = "当前账面余额（万元) ")
	private java.math.BigDecimal dqzmjewy;
	/**计提应收利息余额（元）*/
	@Excel(name = "计提应收利息余额（元）", width = 15)
	@ApiModelProperty(value = "计提应收利息余额（元） ")
	private java.math.BigDecimal jtyslxyey;
	/**计提应收利息余额（万元）*/
	//@Excel(name = "计提应收利息余额（万元）", width = 15)
	@ApiModelProperty(value = "计提应收利息余额（万元） ")
	private java.math.BigDecimal jtyslxyewy;
	/**当前账面余额（万元)+计提应收利息余额（万元）*/
	//@Excel(name = "当前账面余额（万元)+计提应收利息余额（万元）", width = 15)
	@ApiModelProperty(value = "当前账面余额（万元)+计提应收利息余额（万元）")
	private java.math.BigDecimal hjs1;
	/**当前账面余额（万元)+应计提减值准备（万元）*/
	//@Excel(name = "当前账面余额（万元)+应计提减值准备（万元） ", width = 15)
	@ApiModelProperty(value = "当前账面余额（万元)+应计提减值准备（万元）")
	private java.math.BigDecimal hjs2;
	/**计提减值准备（元）*/
	@Excel(name = "计提减值准备（元）", width = 15)
	@ApiModelProperty(value = "计提减值准备（元）")
	private java.math.BigDecimal jzzb;
	/**应计提减值准备（万元）*/
	@Excel(name = "应计提减值准备（万元）", width = 15)
	@ApiModelProperty(value = "应计提减值准备（万元）")
	private java.math.BigDecimal yjtjzzb;
	/**已计提减值*/
	@Excel(name = "已计提减值", width = 15)
	@ApiModelProperty(value = "已计提减值 ")
	private java.math.BigDecimal yjtjz;
	/**账面余额合计（元）*/
	@Excel(name = "账面余额合计（元）", width = 15)
	@ApiModelProperty(value = "账面余额合计（元）")
	private java.math.BigDecimal zmyehjy;
	/**账面余额合计（万元）*/
	@Excel(name = "账面余额合计（万元）", width = 15)
	@ApiModelProperty(value = "账面余额合计（万元）")
	private java.math.BigDecimal zmyehjwy;
	/**债券类别*/
	@Excel(name = "债券类别", width = 15)
	@ApiModelProperty(value = "债券类别")
	private java.lang.String zjlb;
	/**发行主体1*/
	@Excel(name = "发行主体1", width = 15)
	@ApiModelProperty(value = "发行主体1")
	private java.lang.String fhzt1;
	/**发行主体2*/
	@Excel(name = "发行主体2", width = 15)
	@ApiModelProperty(value = "发行主体2")
	private java.lang.String fhzt2;
	/**会计分类*/
	@Excel(name = "会计分类", width = 15)
	@ApiModelProperty(value = "会计分类")
	private java.lang.String kjfl;
	/**买入日*/
	@Excel(name = "买入日", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "买入日")
	private Date mrr;
	/**交易对手方（季度填写，报银监）*/
	@Excel(name = "交易对手方（季度填写，报银监）", width = 15)
	@ApiModelProperty(value = "交易对手方（季度填写，报银监）")
	private java.lang.String jydsf;
	/**基准日*/
	@Excel(name = "基准日", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "基准日")
	private java.lang.String jzr;
	/**到期日*/
	@Excel(name = "到期日", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期日")
	private java.lang.String dqr;
	/**剩余期限（天）*/
	@Excel(name = "剩余期限（天）", width = 15)
	@ApiModelProperty(value = "剩余期限（天）")
	private java.lang.String syxq;
	/**剩余年数*/
	@Excel(name = "剩余年数", width = 15)
	@ApiModelProperty(value = "剩余年数")
	private java.lang.String syns;
	/**剩余月数*/
	@Excel(name = "剩余月数", width = 15)
	@ApiModelProperty(value = "剩余月数")
	private java.lang.String syys;
	/**评级*/
	//@Excel(name = "评级", width = 15)
	@ApiModelProperty(value = "评级")
	private java.lang.String pj;
	/**湖南地区*/
	//@Excel(name = "湖南地区", width = 15)
	@ApiModelProperty(value = "湖南地区")
	private java.lang.String dq;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
	@ApiModelProperty(value = "五级分类 ")
	private java.lang.String wjfl;
	/**是否涉政*/
	//@Excel(name = "是否涉政", width = 15)
	@ApiModelProperty(value = "是否涉政")
	private java.lang.String shsz;






}
