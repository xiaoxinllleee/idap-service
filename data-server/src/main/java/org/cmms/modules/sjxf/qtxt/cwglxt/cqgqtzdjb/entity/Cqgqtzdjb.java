package org.cmms.modules.sjxf.qtxt.cwglxt.cqgqtzdjb.entity;

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
 * @Description: 长期股权投资登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_mo_invest_obj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_mo_invest_obj对象", description="长期股权投资登记簿")
public class Cqgqtzdjb {
    
	/**投资项目编号*/
	@Excel(name = "投资项目编号", width = 15)
    @ApiModelProperty(value = "投资项目编号")
	private String objNo;
	/**投资对象名称*/
	@Excel(name = "投资对象名称", width = 15)
    @ApiModelProperty(value = "投资对象名称")
	private String objName;
	/**投资对象企业性质*/
	@Excel(name = "投资对象企业性质", width = 15)
    @ApiModelProperty(value = "投资对象企业性质")
	private String objAttr;
	/**投资对象股金总额*/
	@Excel(name = "投资对象股金总额", width = 15)
    @ApiModelProperty(value = "投资对象股金总额")
	private java.math.BigDecimal stockAmt;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private java.math.BigDecimal lvlFive;
	/**票面成本*/
	@Excel(name = "票面成本", width = 15)
    @ApiModelProperty(value = "票面成本")
	private java.math.BigDecimal parAmt;
	/**应收股金*/
	@Excel(name = "应收股金", width = 15)
    @ApiModelProperty(value = "应收股金")
	private java.math.BigDecimal stockIntst;
	/**实付金额*/
	@Excel(name = "实付金额", width = 15)
    @ApiModelProperty(value = "实付金额")
	private java.math.BigDecimal realPayAmt;
	/**公允价值*/
	@Excel(name = "公允价值", width = 15)
    @ApiModelProperty(value = "公允价值")
	private java.math.BigDecimal objFairValue;
	/**投资成本*/
	@Excel(name = "投资成本", width = 15)
    @ApiModelProperty(value = "投资成本")
	private java.math.BigDecimal investCost;
	/**损益调整余额*/
	@Excel(name = "损益调整余额", width = 15)
    @ApiModelProperty(value = "损益调整余额")
	private java.math.BigDecimal earnAdjust;
	/**其他权益变动余额*/
	@Excel(name = "其他权益变动余额", width = 15)
    @ApiModelProperty(value = "其他权益变动余额")
	private java.math.BigDecimal oEarnAdj;
	/**股本溢价*/
	@Excel(name = "股本溢价", width = 15)
    @ApiModelProperty(value = "股本溢价")
	private java.math.BigDecimal overCapital;
	/**任意盈余公积*/
	@Excel(name = "任意盈余公积", width = 15)
    @ApiModelProperty(value = "任意盈余公积")
	private java.math.BigDecimal anySurplus;
	/**预计负债*/
	@Excel(name = "预计负债", width = 15)
    @ApiModelProperty(value = "预计负债")
	private String preDebt;
	/**公允价值变动*/
	@Excel(name = "公允价值变动", width = 15)
    @ApiModelProperty(value = "公允价值变动")
	private String fairValueChag;
	/**减值准备金额*/
	@Excel(name = "减值准备金额", width = 15)
    @ApiModelProperty(value = "减值准备金额")
	private java.math.BigDecimal devalue;
	/**处置比例*/
	@Excel(name = "处置比例", width = 15)
    @ApiModelProperty(value = "处置比例")
	private java.math.BigDecimal dealPro;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private Integer sts;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String tel;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String txDate;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private String traceNo;
	/**交易笔次*/
	@Excel(name = "交易笔次", width = 15)
    @ApiModelProperty(value = "交易笔次")
	private Integer tdCnt;
	/**所属账务机构*/
	@Excel(name = "所属账务机构", width = 15)
    @ApiModelProperty(value = "所属账务机构")
	private String txBrNo;
	/**股权投资成本法对应账号*/
	@Excel(name = "股权投资成本法对应账号", width = 15)
    @ApiModelProperty(value = "股权投资成本法对应账号")
	private String costAcNo;
	/**股权投资权益法对应账号*/
	@Excel(name = "股权投资权益法对应账号", width = 15)
    @ApiModelProperty(value = "股权投资权益法对应账号")
	private String equityAcNo;
	/**业务编码*/
	@Excel(name = "业务编码", width = 15)
    @ApiModelProperty(value = "业务编码")
	private String ywNo;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**投资对象净资产公允价值*/
	@Excel(name = "投资对象净资产公允价值", width = 15)
    @ApiModelProperty(value = "投资对象净资产公允价值")
	private String objFairAmt;
	/**购入股本单价*/
	@Excel(name = "购入股本单价", width = 15)
    @ApiModelProperty(value = "购入股本单价")
	private java.math.BigDecimal buyPrice;
	/**购入股本单位面值*/
	@Excel(name = "购入股本单位面值", width = 15)
    @ApiModelProperty(value = "购入股本单位面值")
	private String unitAmt;
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
	/**objCorp*/
	@Excel(name = "投资对象法人名称", width = 15)
    @ApiModelProperty(value = "投资对象法人名称")
	private String objCorp;
	/**投资对象经营场所*/
	@Excel(name = "投资对象经营场所", width = 15)
    @ApiModelProperty(value = "投资对象经营场所")
	private String objAddr;
	/**投资对象是否上市*/
	@Excel(name = "投资对象是否上市", width = 15)
    @ApiModelProperty(value = "投资对象是否上市")
	private String isStock;
	/**投资对象所有者权益账面价值*/
	@Excel(name = "投资对象所有者权益账面价值", width = 15)
    @ApiModelProperty(value = "投资对象所有者权益账面价值")
	private java.math.BigDecimal objEarnAmt;
	/**投资比例(%)*/
	@Excel(name = "投资比例(%)", width = 15)
    @ApiModelProperty(value = "投资比例(%)")
	private java.math.BigDecimal investPro;
	/**初始投资日期*/
	@Excel(name = "初始投资日期", width = 15)
    @ApiModelProperty(value = "初始投资日期")
	private String investSDate;
	/**取得长期股权投资的方法*/
	@Excel(name = "取得长期股权投资的方法", width = 15)
    @ApiModelProperty(value = "取得长期股权投资的方法")
	private String gainWay;
	/**对被投单位的影响*/
	@Excel(name = "对被投单位的影响", width = 15)
    @ApiModelProperty(value = "对被投单位的影响")
	private String infctLvl;
	/**计量方法*/
	@Excel(name = "计量方法", width = 15)
    @ApiModelProperty(value = "计量方法")
	private String accMethod;
	/**投资限制备注*/
	@Excel(name = "投资限制备注", width = 15)
    @ApiModelProperty(value = "投资限制备注")
	private String investRes;
	/**投资对象组织机构代码证*/
	@Excel(name = "投资对象组织机构代码证", width = 15)
    @ApiModelProperty(value = "投资对象组织机构代码证")
	private String objBrNo;
	/**法定盈余公积*/
	@Excel(name = "法定盈余公积", width = 15)
    @ApiModelProperty(value = "法定盈余公积")
	private java.math.BigDecimal lawSurplus;
	/**未非配利润*/
	@Excel(name = "未非配利润", width = 15)
    @ApiModelProperty(value = "未非配利润")
	private java.math.BigDecimal unpayProfit;
	/**其他应收款*/
	@Excel(name = "其他应收款", width = 15)
    @ApiModelProperty(value = "其他应收款")
	private java.math.BigDecimal accRec;
	/**减值损失金额*/
	@Excel(name = "减值损失金额", width = 15)
    @ApiModelProperty(value = "减值损失金额")
	private java.math.BigDecimal devalueLoss;
	/**处置原因*/
	@Excel(name = "处置原因", width = 15)
    @ApiModelProperty(value = "处置原因")
	private String dealReason;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String filler;
	/**购入股本数量*/
	@Excel(name = "购入股本数量", width = 15)
    @ApiModelProperty(value = "购入股本数量")
	private java.math.BigDecimal buyNum;
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
