package org.cmms.modules.sjxf.qtxt.cwglxt.tytzdjb.entity;

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
 * @Description: 同业台帐登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_industry_stand_book")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_industry_stand_book对象", description="同业台帐登记簿")
public class Tytzdjb {
    
	/**同业编号*/
	@Excel(name = "同业编号", width = 15)
    @ApiModelProperty(value = "同业编号")
	private String appNo;
	/**同业序号*/
	@Excel(name = "同业序号", width = 15)
    @ApiModelProperty(value = "同业序号")
	private Integer appSeqn;
	/**成交日期*/
	@Excel(name = "成交日期", width = 15)
    @ApiModelProperty(value = "成交日期")
	private Integer closingDate;
	/**交易对手*/
	@Excel(name = "交易对手", width = 15)
    @ApiModelProperty(value = "交易对手")
	private String ctpyNo;
	/**同业类型*/
	@Excel(name = "同业类型", width = 15)
    @ApiModelProperty(value = "同业类型")
	private String tradeType;
	/**成交金额(元)*/
	@Excel(name = "成交金额(元)", width = 15)
    @ApiModelProperty(value = "成交金额(元)")
	private java.math.BigDecimal industryAmt;
	/**利率(%)*/
	@Excel(name = "利率(%)", width = 15)
    @ApiModelProperty(value = "利率(%)")
	private java.math.BigDecimal rate;
	/**期限(天)*/
	@Excel(name = "期限(天)", width = 15)
    @ApiModelProperty(value = "期限(天)")
	private Integer term;
	/**首次交割日*/
	@Excel(name = "首次交割日", width = 15)
    @ApiModelProperty(value = "首次交割日")
	private Integer icDate;
	/**到期交割日*/
	@Excel(name = "到期交割日", width = 15)
    @ApiModelProperty(value = "到期交割日")
	private Integer mtrDate;
	/**利息(元)*/
	@Excel(name = "利息(元)", width = 15)
    @ApiModelProperty(value = "利息(元)")
	private java.math.BigDecimal intstAmt;
	/**还款(元)*/
	@Excel(name = "还款(元)", width = 15)
    @ApiModelProperty(value = "还款(元)")
	private java.math.BigDecimal repayAmt;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
	private java.math.BigDecimal bal;
	/**交易代码*/
	@Excel(name = "交易代码", width = 15)
    @ApiModelProperty(value = "交易代码")
	private String txCode;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txDate;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private Integer traceNo;
	/**交易操作员*/
	@Excel(name = "交易操作员", width = 15)
    @ApiModelProperty(value = "交易操作员")
	private String tlrno;
	/**操作员流水号*/
	@Excel(name = "操作员流水号", width = 15)
    @ApiModelProperty(value = "操作员流水号")
	private Integer ejfNo;
	/**所属账务部门*/
	@Excel(name = "所属账务部门", width = 15)
    @ApiModelProperty(value = "所属账务部门")
	private String dcBrNo;
	/**所属联社*/
	@Excel(name = "所属联社", width = 15)
    @ApiModelProperty(value = "所属联社")
	private String upBrNo;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String sts;
	/**交易部门*/
	@Excel(name = "交易部门", width = 15)
    @ApiModelProperty(value = "交易部门")
	private String txPartNo;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
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
