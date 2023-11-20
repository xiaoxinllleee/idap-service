package org.cmms.modules.jylrhs.csgl.kmsz.entity;

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
 * @Description: 经营利润核算（科目设置）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_kmsz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_kmsz对象", description="经营利润核算（科目设置）")
public class JylrhsKmszVO {

	/**收支分类*/
	@Excel(name = "收支分类(财务收入/财务支出/其他收支)", width = 15, dicCode = "zbdl")
	@ExcelVerify(notNull = false)
	private String szfl;
	/**统计分类*/
	@Excel(name = "统计分类(数据字典值：zbxl)", width = 15, dicCode = "zbxl")
	@ExcelVerify(notNull = false)
	private String tjfl;
	/**一级科目号*/
	@Excel(name = "一级科目号*", width = 15)
	@ExcelVerify(notNull = true)
	private String subjectNo1;
	/**一级科目名*/
	@Excel(name = "一级科目名", width = 15)
	@ExcelVerify(notNull = false)
	private String subjectName1;
	/**二级科目号*/
	@Excel(name = "二级科目号*", width = 15)
	@ExcelVerify(notNull = true)
	private String subjectNo2;
	/**二级科目名*/
	@Excel(name = "二级科目名", width = 15)
	@ExcelVerify(notNull = false)
	private String subjectName2;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ExcelVerify(notNull = false,interHandler = true)
	private String remark;
}
