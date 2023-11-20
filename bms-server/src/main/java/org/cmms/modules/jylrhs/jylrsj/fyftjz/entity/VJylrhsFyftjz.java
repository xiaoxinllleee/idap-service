package org.cmms.modules.jylrhs.jylrsj.fyftjz.entity;

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
 * @Description: 费用分摊记账（视图）
 * @Author: jeecg-boot
 * @Date:   2023-09-05
 * @Version: V1.0
 */
@Data
@TableName("v_jylrhs_fyftjz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_jylrhs_fyftjz对象", description="费用分摊记账（视图）")
public class VJylrhsFyftjz {

	/**记账日期*/
	@Excel(name = "记账日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "会计日期")
	private Date fiscalDate;
	/**业务机构*/
	@Excel(name = "业务机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**记账分类*/
	@Excel(name = "记账分类", width = 15, dicCode = "zbdl")
    @ApiModelProperty(value = "记账分类")
	@Dict(dicCode = "zbdl")
	private String jzfl;
	/**一级科目编号*/
	@Excel(name = "一级科目编号", width = 15)
    @ApiModelProperty(value = "一级科目编号")
	private String yjkmbh;
	/**一级科目名称*/
	@Excel(name = "一级科目名称", width = 15)
    @ApiModelProperty(value = "一级科目名称")
	private String yjkmmc;
	/**记账科目编号*/
	@Excel(name = "记账科目编号", width = 15)
    @ApiModelProperty(value = "记账科目编号")
	private String jzkm;
	/**记账科目名称*/
	@Excel(name = "记账科目名称", width = 15)
    @ApiModelProperty(value = "记账科目名称")
	private String jzkmmc;
	/**金额*/
	@Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
	private java.math.BigDecimal je;
	/**记账部门*/
	@Excel(name = "记账部门", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "记账部门")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jzbm;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String remark;
	/**操作员*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String operator;
	/**操作类型（0 导入 1 录入 2 修改）*/
	@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "操作类型（0 导入 1 录入 2 修改）")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作时间*/
	@Excel(name = "录入/修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}
