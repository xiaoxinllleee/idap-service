package org.cmms.modules.tjbb.tjfz.sgtzdr.dkyejzrwfp.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 贷款余额净增任务分配
 * @Author: Penghr
 * @Date:   2022-12-15
 * @Version: V1.0
 */
@Data
@TableName("tjbb_sgtz_dkyejzrwfp")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_sgtz_dkyejzrwfp对象", description="贷款余额净增任务分配")
public class Dkyejzrwfp {

	/**数据日期*/
	@Excel(name = "任务年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**机构编码*/
	@Excel(name = "机构编码", width = 15)
    @ApiModelProperty(value = "机构编码")
	private String jgbm;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**全年贷款净投放任务*/
	@Excel(name = "全年贷款净投放任务", width = 15)
    @ApiModelProperty(value = "全年贷款净投放任务")
	private java.math.BigDecimal qndkjtfrw;
	/**开门红贷款净投放（一季度）*/
	@Excel(name = "开门红贷款净投放（一季度）", width = 15)
    @ApiModelProperty(value = "开门红贷款净投放（一季度）")
	private java.math.BigDecimal kmhdkjtfQone;
	/**二季度任务*/
	@Excel(name = "二季度任务", width = 15)
    @ApiModelProperty(value = "二季度任务")
	private java.math.BigDecimal kmhdkjtfQtwo;
	/**二季度序时任务：四月份*/
	@Excel(name = "4月份", width = 15, groupName = "二季度序时任务")
    @ApiModelProperty(value = "二季度序时任务：四月份")
	private java.math.BigDecimal xsrwOneQtwo;
	/**二季度序时任务：五月份*/
	@Excel(name = "5月份", width = 15, groupName = "二季度序时任务")
    @ApiModelProperty(value = "二季度序时任务：五月份")
	private java.math.BigDecimal xsrwTwoQtwo;
	/**二季度序时任务：六月份*/
	@Excel(name = "6月份", width = 15, groupName = "二季度序时任务")
    @ApiModelProperty(value = "二季度序时任务：六月份")
	private java.math.BigDecimal xsrwThrQtwo;
	/**三季度任务*/
	@Excel(name = "三季度任务", width = 15)
    @ApiModelProperty(value = "三季度任务")
	private java.math.BigDecimal kmhdkjtfQthr;
	/**三季度序时任务：七月份*/
	@Excel(name = "7月份", width = 15, groupName = "三季度序时任务")
	@ApiModelProperty(value = "三季度序时任务：七月份")
	private java.math.BigDecimal xsrwOneQthr;
	/**三季度序时任务：八月份*/
	@Excel(name = "8月份", width = 15, groupName = "三季度序时任务")
    @ApiModelProperty(value = "三季度序时任务：八月份")
	private java.math.BigDecimal xsrwTwoQthr;
	/**三季度序时任务：九月份*/
	@Excel(name = "9月份", width = 15, groupName = "三季度序时任务")
    @ApiModelProperty(value = "三季度序时任务：九月份")
	private java.math.BigDecimal xsrwThrQthr;
	/**四季度任务*/
	@Excel(name = "四季度任务", width = 15)
    @ApiModelProperty(value = "四季度任务")
	private java.math.BigDecimal kmhdkjtfQfou;
	/**四季度序时任务：十月份*/
	@Excel(name = "10月份", width = 15, groupName = "四季度序时任务")
    @ApiModelProperty(value = "四季度序时任务：十月份")
	private java.math.BigDecimal xsrwOneQfou;
	/**四季度序时任务：十一月份*/
	@Excel(name = "11月份", width = 15, groupName = "四季度序时任务")
    @ApiModelProperty(value = "四季度序时任务：十一月份")
	private java.math.BigDecimal xsrwTwoQfou;
	/**四季度序时任务：十二月份*/
	@Excel(name = "12月份", width = 15, groupName = "四季度序时任务")
    @ApiModelProperty(value = "四季度序时任务：十二月份")
	private java.math.BigDecimal xsrwThrQfou;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "操作标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "操作人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
//	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
