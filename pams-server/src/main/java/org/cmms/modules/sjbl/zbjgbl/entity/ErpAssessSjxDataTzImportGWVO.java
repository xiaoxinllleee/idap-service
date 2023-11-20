package org.cmms.modules.sjbl.zbjgbl.entity;

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
 * @Description: 指标结果补录
 * @Author: jeecg-boot
 * @Date:   2023-03-23
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_SJX_DATA_TZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_SJX_DATA_TZ对象", description="指标结果补录")
public class ErpAssessSjxDataTzImportGWVO {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**方案ID*/
	@Excel(name = "考核项目", width = 15, dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	@ApiModelProperty(value = "考核项目")
	@Dict(dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	private String schemeId;
	/**统计月份*/
	@Excel(name = "考核月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "考核月份")
	private Date tjyf;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15, dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	@ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**指标id*/
	@Excel(name = "指标id", width = 15)
    @ApiModelProperty(value = "指标id")
	private String zbid;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15, dicCode = "zbwd")
	@ApiModelProperty(value = "指标维度")
	@Dict(dicCode = "zbwd")
	private String zbwd;
	/**调整类型( 1 覆盖 2更新)*/
	@Excel(name = "调整类型", width = 15, dicCode = "jx_tzlx")
	@ApiModelProperty(value = "调整类型")
	@Dict(dicCode = "jx_tzlx")
	private Integer tzlx;
	/**调整值*/
	@Excel(name = "调整值", width = 15)
    @ApiModelProperty(value = "调整值")
	private java.math.BigDecimal tzz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
}
