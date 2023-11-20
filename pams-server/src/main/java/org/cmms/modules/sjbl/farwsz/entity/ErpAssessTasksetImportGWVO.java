package org.cmms.modules.sjbl.farwsz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class ErpAssessTasksetImportGWVO {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**方案ID*/
	@Excel(name = "考核项目", width = 15, dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
    @ApiModelProperty(value = "考核项目")
	@Dict(dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	private String schemeId;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	@ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工姓名")
	@Dict(dicCode="yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private String yggh;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	@Dict(dicCode = "zbid", dictTable = "ERP_BAS_ZBK", dicText = "zbmc")
	private String zbid;
	/**考核维度*/
	@Excel(name = "考核维度", width = 15, dicCode = "zbwd")
    @ApiModelProperty(value = "考核维度")
	@Dict(dicCode = "zbwd")
	private String khwd;
	/**考核方式*/
	@Excel(name = "考核方式", width = 15, dicCode = "khfs")
	@ApiModelProperty(value = "考核方式")
	@Dict(dicCode = "khfs")
	private Integer khfs;
	/**考核时间*/
	@Excel(name = "考核月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "考核月份")
	private Date khsj;

	/**指标任务*/
	@Excel(name = "指标任务", width = 15)
    @ApiModelProperty(value = "指标任务")
	private java.math.BigDecimal zbrw;
}
