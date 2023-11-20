package org.cmms.modules.khjg.jgkhjg.entity;

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
 * @Description: 机构考核结果
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Data
@TableName("erp_wage_ygjx_mx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_wage_ygjx_mx对象", description="机构考核结果")
public class Jgkhjg {

	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**工资日期*/
	@Excel(name = "工资日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资日期")
	private Date gzrq;
	/**方案id*/
	@Excel(name = "考核项目", width = 15,dicCode = "scheme_id", dictTable = "V_PMA_A_SCHEME", dicText = "SHOW_NAME")
    @ApiModelProperty(value = "考核项目")
	@Dict(dicCode = "scheme_id", dictTable = "V_PMA_A_SCHEME", dicText = "SHOW_NAME")
	private String schemeId;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
    @ApiModelProperty(value = "岗位标志")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15,dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	private String yggh;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15, dicCode = "zbid", dictTable = "erp_bas_zbk", dicText = "zbmc")
    @ApiModelProperty(value = "指标ID")
	@Dict(dicCode = "zbid", dictTable = "erp_bas_zbk", dicText = "zbmc")
	private String zbid;
	/**指标类别*/
	@Excel(name = "指标类别", width = 15,dicCode = "zblb")
    @ApiModelProperty(value = "指标类别")
	@Dict(dicCode = "zblb")
	private String zblb;
	/**工资类别*/
	@Excel(name = "工资类别", width = 15)
    @ApiModelProperty(value = "工资类别")
	private String gzlb;
	/**指标结果*/
	@Excel(name = "指标结果", width = 15)
    @ApiModelProperty(value = "指标结果")
	private java.math.BigDecimal zbjg;
	/**指标得分*/
	@Excel(name = "指标得分", width = 15)
    @ApiModelProperty(value = "指标得分")
	private java.math.BigDecimal zbdf;
	/**指标工资*/
	@Excel(name = "指标工资", width = 15)
    @ApiModelProperty(value = "指标工资")
	private java.math.BigDecimal zbgz;
	/**当日工资*/
	@Excel(name = "当日工资", width = 15)
    @ApiModelProperty(value = "当日工资")
	private java.math.BigDecimal drgz;
	/**备用工资(返回、封顶等)*/
	@Excel(name = "备用工资", width = 15)
    @ApiModelProperty(value = "备用工资")
	private java.math.BigDecimal bygz;
	/**指标分值*/
	@Excel(name = "指标分值", width = 15)
    @ApiModelProperty(value = "指标分值")
	private java.math.BigDecimal zbfz;
	/**指标任务*/
	@Excel(name = "指标任务", width = 15)
    @ApiModelProperty(value = "指标任务")
	private java.math.BigDecimal zbrw;
	/**指标单价*/
	@Excel(name = "指标单价", width = 15, numFormat = "0.######")
    @ApiModelProperty(value = "指标单价")
	private java.math.BigDecimal zbdj;
	/**指标单位*/
	@Excel(name = "指标单位", width = 15)
    @ApiModelProperty(value = "指标单位")
	private java.math.BigDecimal zbdw;
	/**指标权重*/
	@Excel(name = "指标权重", width = 15)
    @ApiModelProperty(value = "指标权重")
	private java.math.BigDecimal zbqz;
	/**在岗天数*/
	@Excel(name = "在岗天数", width = 15)
    @ApiModelProperty(value = "在岗天数")
	private Integer zgts;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}
