package org.cmms.modules.jylrhs.csgl.rwsz.entity;

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
 * @Description: 经营利润任务设置 导入VO
 * @Author: jeecg-boot
 * @Date:   2023-11-08
 * @Version: V1.0
 */
@Data
@TableName("JYLRHS_JYLR_RWSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="JYLRHS_JYLR_RWSZ对象", description="经营利润任务设置")
public class JylrhsRwszVO {

	/**任务年份*/
	@Excel(name = "任务年份*(格式：yyyy/MM/dd)", width = 15, format = "yyyy-MM-dd")
	@ExcelVerify(notNull = true)
	private Date missionYear;
	/**业务机构*/
	@Excel(name = "业务机构*(组织简称)", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**任务阈值*/
	@Excel(name = "任务阈值(万元)", width = 15)
	private java.math.BigDecimal missionValue;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ExcelVerify(notNull = false,interHandler = true)
	private String remark;
}
