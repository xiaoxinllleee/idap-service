package org.cmms.modules.sjbl.farwsz.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 方案任务设置
 * @Author: jeecg-boot
 * @Date:   2023-03-22
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_TASKSET")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_TASKSET对象", description="方案任务设置")
public class ErpAssessTaskset {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;
	/**方案ID*/
	@Excel(name = "考核项目", width = 15, dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	@ApiModelProperty(value = "考核项目")
	@Dict(dicCode = "SCHEME_ID", dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	@ExcelVerify(notNull = true)
	private String schemeId;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	@ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode="yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private String yggh;
	@Excel(name = "指标ID", width = 15)
	@TableField(exist = false)
	private java.lang.String zbmc;

	public String getZbmc() {
		return zbid;
	}
	/**指标ID*/
	@Excel(name = "指标名称", width = 15, dicCode = "zbid", dictTable = "ERP_BAS_ZBK", dicText = "zbmc")
	@ApiModelProperty(value = "指标名称")
	@Dict(dicCode = "zbid", dictTable = "ERP_BAS_ZBK", dicText = "zbmc")
	@ExcelVerify(notNull = true)
	private String zbid;
	/**考核维度*/
	@Excel(name = "考核维度", width = 15, dicCode = "zbwd")
	@ApiModelProperty(value = "考核维度")
	@Dict(dicCode = "zbwd")
	@ExcelVerify(notNull = true)
	private String khwd;
	@Excel(name = "考核月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "考核月份")
	private Date khsj;
	/**考核方式*/
	@Excel(name = "考核方式", width = 15, dicCode = "khfs")
	@ApiModelProperty(value = "考核方式")
	@Dict(dicCode = "khfs")
	@ExcelVerify(notNull = true)
	private Integer khfs;
	/**指标任务*/
	@Excel(name = "指标任务", width = 15)
	@ApiModelProperty(value = "指标任务")
	private java.math.BigDecimal zbrw;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	@ExcelVerify(interHandler = true)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
