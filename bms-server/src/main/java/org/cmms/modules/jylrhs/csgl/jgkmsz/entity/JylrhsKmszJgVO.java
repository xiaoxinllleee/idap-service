package org.cmms.modules.jylrhs.csgl.jgkmsz.entity;

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
 * @Description: 机构科目设置 导入VO
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
public class JylrhsKmszJgVO {

	/**业务机构*/
	@Excel(name = "业务机构*(组织简称)", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**一级科目号*/
	@Excel(name = "一级科目号*", width = 15)/*, dicCode = "subject_no1", dictTable = "jylrhs_kmsz", dicText = "subject_name1", ds = "jylrhs"*/
	@ExcelVerify(notNull = true)
	private String subjectNo1;
	/**二级科目号*/
	@Excel(name = "二级科目号*", width = 15)/*, dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds = "jylrhs"*/
	@ExcelVerify(notNull = true)
	private String subjectNo2;
	/**上限额度*/
	@Excel(name = "上限额度(元)", width = 15)
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal sxed;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ExcelVerify(notNull = false,interHandler = true)
	private String remark;
}
