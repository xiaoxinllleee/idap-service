package org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpdMx.entity;

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
 * @Description: 客户经理等级评定明细
 * @Author: jeecg-boot
 * @Date:   2023-01-16
 * @Version: V1.0
 */
@Data
@TableName("Grade_cust_mx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Grade_cust_mx对象", description="客户经理等级评定明细")
public class KhjldjpdMx {

	/**评定周期*/
	@Excel(name = "评定周期", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
	@Dict(dicCode = "rqwd")
	private String pdzq;
	/**评定日期*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	private Date pdrq;
	/**zzbz*/
	@Excel(name = "支行名称", width = 15,dicCode = "zzbz", dictTable = "v_hr_bas_organization", dicText = "sjzzjc")
	@ApiModelProperty(value = "支行名称")
	@Dict(dicCode = "zzbz", dictTable = "v_hr_bas_organization", dicText = "sjzzjc")
	@TableField(exist = false)
	private String zhmc;
	/**组织标识*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
    @ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "客户经理名称", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "客户经理名称")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String yggh;
	/**指标ID*/
	@Excel(name = "考核ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	private String zbid;
	/**指标名称*/
	@Excel(name = "考核名称", width = 15)
	@ApiModelProperty(value = "指标名称")
	private String zbmc;
	/**指标分值*/
	@Excel(name = "考核分值", width = 15)
	@ApiModelProperty(value = "指标分值")
	private java.math.BigDecimal zbfz;
	/**指标结果*/
	@Excel(name = "考核实绩", width = 15)
    @ApiModelProperty(value = "考核实绩")
	private java.math.BigDecimal zbjg;
	/**指标任务*/
	@Excel(name = "考核任务", width = 15)
    @ApiModelProperty(value = "指标任务")
	private java.math.BigDecimal zbrw;
	/**指标得分*/
	@Excel(name = "考核得分", width = 15)
    @ApiModelProperty(value = "指标得分")
	private java.math.BigDecimal zbdf;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;

	/**业务机构性质*/
	//@Excel(name = "业务机构性质", width = 15)
    @ApiModelProperty(value = "业务机构性质")
	private Integer ywjgxz;
	/**所在区域*/
	//@Excel(name = "所在区域", width = 15)
    @ApiModelProperty(value = "所在区域")
	private Integer szqy;
	/**个人指标结果*/
	//@Excel(name = "个人指标结果", width = 15)
    @ApiModelProperty(value = "个人指标结果")
	private java.math.BigDecimal grzbjg;
	/**考核指标标准值*/
//	@Excel(name = "考核指标标准值", width = 15)
    @ApiModelProperty(value = "考核指标标准值")
	private java.math.BigDecimal khzbbzz;
}
