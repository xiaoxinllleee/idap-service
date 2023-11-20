package org.cmms.modules.jylrhs.jylrsj.fysjbl.entity;

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
 * @Description: 经营利润核算（费用数据补录）导入VO
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_fysjbl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_fysjbl对象", description="经营利润核算（费用数据补录）")
public class JylrhsFysjblVO {

	/**会计日期*/
	@Excel(name = "会计日期*(月末日期)", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ExcelVerify(notNull = true)
	private Date fiscalDate;
	/**业务机构*/
	@Excel(name = "业务机构*(组织简称)", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**指标ID*/
	@Excel(name = "指标ID*", width = 15)
	@ExcelVerify(notNull = true)
	private String zbid;
	/**金额*/
	@Excel(name = "金额(元)", width = 15)
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal je;
	/**调整类型*/
	@Excel(name = "调整类型(覆盖/调整)", width = 15, dicCode = "tzlx")
	@ExcelVerify(notNull = true)
	private String tzlx;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ExcelVerify(notNull = false,interHandler = true)
	private String remark;
}
