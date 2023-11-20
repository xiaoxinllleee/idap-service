package org.cmms.modules.jylrhs.csgl.khsz.entity;

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
 * @Description: 经营利润核算（考核设置）导入VO
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
public class JylrhsKhszVO {

	/**业务机构*/
	@Excel(name = "业务机构*(组织简称)", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**指标ID*/
	@Excel(name = "指标名称*", width = 15, dicCode = "zbid", dictTable = "jylrhs_zbk", dicText = "zbmc", ds = "jylrhs")
	@ExcelVerify(notNull = true)
	private String zbid;
	/**考核周期*/
	@Excel(name = "考核周期*(天/月/季/年)", width = 15, dicCode = "rqwd")
	@ExcelVerify(notNull = true)
	private String khzq;
	/**计提比率*/
	@Excel(name = "计提比率", width = 15)
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal jtbl;
	/**参考利率*/
	@Excel(name = "参考利率", width = 15)
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal ckll;
	/**调剂系数*/
	@Excel(name = "调剂系数", width = 15)
	@ExcelVerify(notNull = false,interHandler = true)
	private java.math.BigDecimal tjxs;
}
