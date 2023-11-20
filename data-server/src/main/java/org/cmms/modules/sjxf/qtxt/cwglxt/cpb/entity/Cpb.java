package org.cmms.modules.sjxf.qtxt.cwglxt.cpb.entity;

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
 * @Description: 产品表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_in_parm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_in_parm对象", description="产品表")
public class Cpb {

	/**内部产品代码*/
	@Excel(name = "内部产品代码", width = 15)
    @ApiModelProperty(value = "内部产品代码")
	private String prdtNo;
	/**科目控制字*/
	@Excel(name = "科目控制字", width = 15)
    @ApiModelProperty(value = "科目控制字")
	private String accHrt;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**产品描述*/
	@Excel(name = "产品描述", width = 15)
    @ApiModelProperty(value = "产品描述")
	private String prdtMo;
	/**借贷标志*/
	@Excel(name = "借贷标志", width = 15)
    @ApiModelProperty(value = "借贷标志")
	private String dcInd;
	/**发生额方向*/
	@Excel(name = "发生额方向", width = 15)
    @ApiModelProperty(value = "发生额方向")
	private String amtDc;
	/**启用日期*/
	@Excel(name = "启用日期", width = 15)
    @ApiModelProperty(value = "启用日期")
	private String starDate;
	/**止用日期*/
	@Excel(name = "止用日期", width = 15)
    @ApiModelProperty(value = "止用日期")
	private String stopDate;
	/**最低限额*/
	@Excel(name = "最低限额", width = 15)
    @ApiModelProperty(value = "最低限额")
	private java.math.BigDecimal minAmt;
	/**最高限额*/
	@Excel(name = "最高限额", width = 15)
    @ApiModelProperty(value = "最高限额")
	private java.math.BigDecimal maxAmt;
	/**最小期限*/
	@Excel(name = "最小期限", width = 15)
    @ApiModelProperty(value = "最小期限")
	private String minTerm;
	/**最大期限*/
	@Excel(name = "最大期限", width = 15)
    @ApiModelProperty(value = "最大期限")
	private String maxTerm;
	/**期限类型*/
	@Excel(name = "期限类型", width = 15)
    @ApiModelProperty(value = "期限类型")
	private String termType;
	/**利率编号*/
	@Excel(name = "利率编号", width = 15)
    @ApiModelProperty(value = "利率编号")
	private String rateNo;
	/**罚息利率编号*/
	@Excel(name = "罚息利率编号", width = 15)
    @ApiModelProperty(value = "罚息利率编号")
	private String fineRateNo;
	/**利率浮动比例下限*/
	@Excel(name = "利率浮动比例下限", width = 15)
    @ApiModelProperty(value = "利率浮动比例下限")
	private java.math.BigDecimal minFlotRate;
	/**利率浮动比例上限*/
	@Excel(name = "利率浮动比例上限", width = 15)
    @ApiModelProperty(value = "利率浮动比例上限")
	private java.math.BigDecimal maxFlotRate;
	/**计息天数类型*/
	@Excel(name = "计息天数类型", width = 15)
    @ApiModelProperty(value = "计息天数类型")
	private String calDayType;
	/**记息标志*/
	@Excel(name = "记息标志", width = 15)
    @ApiModelProperty(value = "记息标志")
	private String intstInd;
	/**计息类型*/
	@Excel(name = "计息类型", width = 15)
    @ApiModelProperty(value = "计息类型")
	private String intstKnd;
	/**积数计算方法*/
	@Excel(name = "积数计算方法", width = 15)
    @ApiModelProperty(value = "积数计算方法")
	private String acmType;
	/**计息月份*/
	@Excel(name = "计息月份", width = 15)
    @ApiModelProperty(value = "计息月份")
	private String intstMon;
	/**计息日*/
	@Excel(name = "计息日", width = 15)
    @ApiModelProperty(value = "计息日")
	private String intstDate;
	/**是否允许透支*/
	@Excel(name = "是否允许透支", width = 15)
    @ApiModelProperty(value = "是否允许透支")
	private String odInd;
	/**透支额度*/
	@Excel(name = "透支额度", width = 15)
    @ApiModelProperty(value = "透支额度")
	private java.math.BigDecimal odAmt;
	/**会计代码*/
	@Excel(name = "会计代码", width = 15)
    @ApiModelProperty(value = "会计代码")
	private String dcCode;
	/**是否摊销*/
	@Excel(name = "是否摊销", width = 15)
    @ApiModelProperty(value = "是否摊销")
	private String sharInd;
	/**摊销期限*/
	@Excel(name = "摊销期限", width = 15)
    @ApiModelProperty(value = "摊销期限")
	private String sharTerm;
	/**摊销期限类型*/
	@Excel(name = "摊销期限类型", width = 15)
    @ApiModelProperty(value = "摊销期限类型")
	private String sharTermType;
	/**逾期处理类型*/
	@Excel(name = "逾期处理类型", width = 15)
    @ApiModelProperty(value = "逾期处理类型")
	private String overDateType;
	/**是否允许通用记账*/
	@Excel(name = "是否允许通用记账", width = 15)
    @ApiModelProperty(value = "是否允许通用记账")
	private String invstType;
	/**预提投资收益周期*/
	@Excel(name = "预提投资收益周期", width = 15)
    @ApiModelProperty(value = "预提投资收益周期")
	private String preTerm;
	/**特殊标志*/
	@Excel(name = "特殊标志", width = 15)
    @ApiModelProperty(value = "特殊标志")
	private String speInd;
	/**是否有存期*/
	@Excel(name = "是否有存期", width = 15)
    @ApiModelProperty(value = "是否有存期")
	private String termInd;
	/**是否展示*/
	@Excel(name = "是否展示", width = 15)
    @ApiModelProperty(value = "是否展示")
	private String opInd;
	/**银行类型*/
	@Excel(name = "银行类型", width = 15)
    @ApiModelProperty(value = "银行类型")
	private String bankInd;
	/**业务类型号*/
	@Excel(name = "业务类型号", width = 15)
    @ApiModelProperty(value = "业务类型号")
	private String ywNo;
	/**应用代码*/
	@Excel(name = "应用代码", width = 15)
    @ApiModelProperty(value = "应用代码")
	private String appCod;
	/**资产负债类型*/
	@Excel(name = "资产负债类型", width = 15)
    @ApiModelProperty(value = "资产负债类型")
	private String accInd;
	/**平衡标志*/
	@Excel(name = "平衡标志", width = 15)
    @ApiModelProperty(value = "平衡标志")
	private String equaInd;
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
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;
	*//**dttime*//*
	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
