package org.cmms.modules.jylrhs.csgl.zbk.entity;

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
 * @Description: 经营利润核算（指标库）导入VO
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
public class JylrhsZbkVO {

	/**指标ID*/
	@Excel(name = "指标ID*", width = 15)
	@ExcelVerify(notNull = true)
	private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
	@ExcelVerify(notNull = true)
	private String zbmc;
	/**指标大类*/
	@Excel(name = "指标大类(财务收入/财务支出/其他收支)", width = 15, dicCode = "zbdl")
	@ExcelVerify(notNull = true)
	private String zbdl;
	/**指标小类*/
	@Excel(name = "指标小类(数据字典值：zbxl)", width = 15, dicCode = "zbxl")
	@ExcelVerify(notNull = true)
	private String zbxl;
	/**数据来源*/
	@Excel(name = "数据来源(系统取数/人工录入)", width = 15, dicCode = "data_source")
	@ExcelVerify(notNull = true,interHandler = true)
	private Integer sjly;
	/**说明*/
	@Excel(name = "说明", width = 15)
	@ExcelVerify(notNull = false)
	private String remark;
}
