package org.cmms.modules.sjxf.qtxt.cwglxt.dzzcydkgldjb.entity;

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
 * @Description: 抵债资产与贷款关联登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_debt_acno_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_debt_acno_reg对象", description="抵债资产与贷款关联登记簿")
public class Dzzcydkgldjb {
    
	/**资产编号*/
	@Excel(name = "资产编号", width = 15)
    @ApiModelProperty(value = "资产编号")
	private String capNo;
	/**抵债贷款账号*/
	@Excel(name = "抵债贷款账号", width = 15)
    @ApiModelProperty(value = "抵债贷款账号")
	private String dkAcNo;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String dkAcName;
	/**本金*/
	@Excel(name = "本金", width = 15)
    @ApiModelProperty(value = "本金")
	private java.math.BigDecimal amt1;
	/**表内利息*/
	@Excel(name = "表内利息", width = 15)
    @ApiModelProperty(value = "表内利息")
	private java.math.BigDecimal amt2;
	/**表外利息*/
	@Excel(name = "表外利息", width = 15)
    @ApiModelProperty(value = "表外利息")
	private java.math.BigDecimal amt3;
	/**抵债类型*/
	@Excel(name = "抵债类型", width = 15)
    @ApiModelProperty(value = "抵债类型")
	private String type;
	/**冲本金*/
	@Excel(name = "冲本金", width = 15)
    @ApiModelProperty(value = "冲本金")
	private java.math.BigDecimal amt11;
	/**冲表内利息*/
	@Excel(name = "冲表内利息", width = 15)
    @ApiModelProperty(value = "冲表内利息")
	private java.math.BigDecimal amt22;
	/**冲表外利息*/
	@Excel(name = "冲表外利息", width = 15)
    @ApiModelProperty(value = "冲表外利息")
	private java.math.BigDecimal amt33;
	/**冲减合计*/
	@Excel(name = "冲减合计", width = 15)
    @ApiModelProperty(value = "冲减合计")
	private java.math.BigDecimal sumAmt;
	/**待定*/
	@Excel(name = "待定", width = 15)
    @ApiModelProperty(value = "待定")
	private java.math.BigDecimal devAmt;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**利息*/
	@Excel(name = "利息", width = 15)
    @ApiModelProperty(value = "利息")
	private java.math.BigDecimal accrual;
	/**还贷方式*/
	@Excel(name = "还贷方式", width = 15)
    @ApiModelProperty(value = "还贷方式")
	private String payType;
	/**付款指令号*/
	@Excel(name = "付款指令号", width = 15)
    @ApiModelProperty(value = "付款指令号")
	private String payNo;
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
/*	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
