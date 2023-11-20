package org.cmms.modules.jylrhs.csgl.jgzfyxz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 机构费用上限 导入VO
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
public class JylrhsZfyxzJgVO {

	/**业务机构*/
	@Excel(name = "业务机构*(组织简称)", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**记账年份*/
	@Excel(name = "记账年份*(格式：20230101或2023-1-1)", width = 15, format = "yyyy-MM-dd")
	@ExcelVerify(notNull = true)
	private Date jznf;
	/**上限额度*/
	@Excel(name = "上限额度(万元)", width = 15)
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal sxed;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ExcelVerify(notNull = false,interHandler = true)
	private String remark;
}
