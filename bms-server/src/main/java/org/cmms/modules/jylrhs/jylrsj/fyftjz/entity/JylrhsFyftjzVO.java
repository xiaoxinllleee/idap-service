package org.cmms.modules.jylrhs.jylrsj.fyftjz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 经营利润核算（费用分摊记账）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_fyftjz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_fyftjz对象", description="经营利润核算（费用分摊记账）")
public class JylrhsFyftjzVO {

	/**记账日期*/
	@Excel(name = "记账日期*", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ExcelVerify(notNull = true)
	private Date fiscalDate;
	/**业务机构*/
	@Excel(name = "业务机构*(组织简称)", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**记账分类*/
	@Excel(name = "记账分类*(财务收入/财务支出/其他收支)", width = 15, dicCode = "zbdl")
	@ExcelVerify(notNull = true)
	private String jzfl;
	/**记账科目*/
	@Excel(name = "记账科目*(二级科目号)", width = 15)
	@ExcelVerify(notNull = true)
	private String jzkm;
	/**金额*/
	@Excel(name = "金额(元)", width = 15)
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal je;
	/**记账部门*/
	@Excel(name = "记账部门(组织简称)", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jzbm;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ExcelVerify(notNull = false,interHandler = true)
	private String remark;
}
