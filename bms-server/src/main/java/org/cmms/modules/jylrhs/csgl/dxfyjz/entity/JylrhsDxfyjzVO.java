package org.cmms.modules.jylrhs.csgl.dxfyjz.entity;

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
 * @Description: 经营利润核算（单项费用记账）导入VO
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
@Data
public class JylrhsDxfyjzVO {

	/**业务机构*/
	@Excel(name = "业务机构(组织简称)*", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**记账日期*/
	@Excel(name = "记账日期(格式：yyyy/MM/dd)*", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ExcelVerify(notNull = true)
	private Date jzrq;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
	@ExcelVerify(notNull = false)
	private String zy;
	/**金额*/
	@Excel(name = "金额*", width = 15)
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal je;
	/**列账子目（6601科目下辖二级科目）*/
	@Excel(name = "列账子目*(6601科目下辖二级科目)", width = 15, dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds = "jylrhs")
	@ExcelVerify(notNull = true, interHandler = true)
	private String lzzm;

}
