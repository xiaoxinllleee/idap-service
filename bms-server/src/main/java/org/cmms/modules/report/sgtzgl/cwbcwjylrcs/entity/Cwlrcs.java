package org.cmms.modules.report.sgtzgl.cwbcwjylrcs.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 财务部_手工台账_经营利润测算
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_CWLRCS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_CWLRCS对象", description="财务部_手工台账_经营利润测算")
public class Cwlrcs {
    
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
	private String jgdm;
	/**年初村行存款日平*/
	@Excel(name = "年初村行存款日平", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "年初村行存款日平")
	private java.math.BigDecimal ncchckrp;
	/**月末村行存款日平*/
	@Excel(name = "月末村行存款日平", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "月末村行存款日平")
	private java.math.BigDecimal ymchckrp;
	/**年初国际业务存款日平*/
	@Excel(name = "年初国际业务存款日平", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "年初国际业务存款日平")
	private java.math.BigDecimal ncgjywckrp;
	/**月末国际业务存款日平*/
	@Excel(name = "月末国际业务存款日平", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "月末国际业务存款日平")
	private java.math.BigDecimal ymgjywckrp;
	/**总部分摊手续费收入（不含税）*/
	@Excel(name = "总部分摊手续费收入（不含税）", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "总部分摊手续费收入（不含税）")
	private java.math.BigDecimal zbftsxfsrbhs;
	/**6403其他税金*/
	@Excel(name = "6403其他税金", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "6403其他税金")
	private java.math.BigDecimal qtsj;
	/**总部分摊手续费支出*/
	@Excel(name = "总部分摊手续费支出", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "总部分摊手续费支出")
	private java.math.BigDecimal zbftsxfzc;
	/**支行费用台账计算*/
	@Excel(name = "支行费用台账计算", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "支行费用台账计算")
	private java.math.BigDecimal zhfytzjs;
	/**6602其他业务支出科目*/
	@Excel(name = "6602其他业务支出科目", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "6602其他业务支出科目")
	private java.math.BigDecimal qtywzckm;
	/**6711营业外支出科目*/
	@Excel(name = "6711营业外支出科目", width = 15)
    @ApiModelProperty(value = "6711营业外支出科目")
	private java.math.BigDecimal yywzckm;
	/**报批费用*/
	@Excel(name = "报批费用", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "报批费用")
	private java.math.BigDecimal bpfy;
	/**经营利润任务*/
	@Excel(name = "经营利润任务", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "经营利润任务")
	private java.math.BigDecimal jylrrw;
	/**库存现金日均余额(外折人)*/
	@Excel(name = "库存现金日均余额(外折人)", width = 15, numFormat = "#0.000000")
    @ApiModelProperty(value = "库存现金日均余额(外折人)")
	private java.math.BigDecimal kcxjrjye;
}
