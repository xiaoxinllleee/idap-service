package org.cmms.modules.ygjx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 员工绩效明细
 * @Author: jeecg-boot
 * @Date:   2022-02-28
 * @Version: V1.0
 */
@Data
public class ErpWageYgjxMxVO {
    
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**工资日期*/
	@Excel(name = "工资日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资日期")
	private Date gzrq;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	@Dict(dicCode = "zbid",dicText = "zbmc",dictTable = "erp_bas_zbk")
	private String zbid;
	//erp_bas_zbk 的bz字段
	private String bz;
	private String zbkbz;
	/**指标单价*/
	@Excel(name = "指标单价", width = 15, numFormat = "0.######")
    @ApiModelProperty(value = "指标单价")
	private java.math.BigDecimal zbdj;
	private java.math.BigDecimal zbdw;
	/**指标结果*/
	@Excel(name = "指标结果", width = 15)
    @ApiModelProperty(value = "指标结果")
	private java.math.BigDecimal zbjg;
	/**指标工资*/
	@Excel(name = "指标工资", width = 15)
    @ApiModelProperty(value = "指标工资")
	private java.math.BigDecimal zbgz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	private String sfyg;
	/**指标类别*/
	@Excel(name = "指标类别", width = 15)
    @ApiModelProperty(value = "指标类别")
	@Dict(dicCode = "zblb")
	private Integer zblb;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标识(0 导入 1 录入 2 修改)*/
	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**考核维度*/
	@Excel(name = "考核维度", width = 15)
    @ApiModelProperty(value = "考核维度")
	private String khwd;
	/**折算后考核结果*/
	@Excel(name = "折算后考核结果", width = 15)
    @ApiModelProperty(value = "折算后考核结果")
	private java.math.BigDecimal zshkhjg;
	/**折算后累计考核结果*/
	@Excel(name = "折算后累计考核结果", width = 15)
    @ApiModelProperty(value = "折算后累计考核结果")
	private java.math.BigDecimal zshljkhjg;
	/**指标工资（当月累计）*/
	@Excel(name = "指标工资（当月累计）", width = 15)
    @ApiModelProperty(value = "指标工资（当月累计）")
	private java.math.BigDecimal ljzbgz;
	/**累计结果（当月累计-原始）*/
	@Excel(name = "累计结果（当月累计-原始）", width = 15)
    @ApiModelProperty(value = "累计结果（当月累计-原始）")
	private java.math.BigDecimal ljkhjg;
	/**期初结果*/
	@Excel(name = "期初结果", width = 15)
    @ApiModelProperty(value = "期初结果")
	private java.math.BigDecimal qcjg;
	/**期末结果*/
	@Excel(name = "期末结果", width = 15)
    @ApiModelProperty(value = "期末结果")
	private java.math.BigDecimal qmjg;
	/**当月考勤天数*/
	@Excel(name = "当月考勤天数", width = 15)
    @ApiModelProperty(value = "当月考勤天数")
	private Long kqts;
	/**非系数指标工资*/
	@Excel(name = "非系数指标工资", width = 15)
    @ApiModelProperty(value = "非系数指标工资")
	private java.math.BigDecimal fxszbgz;
	/**系数指标工资*/
	@Excel(name = "系数指标工资", width = 15)
    @ApiModelProperty(value = "系数指标工资")
	private java.math.BigDecimal xszbgz;
}
