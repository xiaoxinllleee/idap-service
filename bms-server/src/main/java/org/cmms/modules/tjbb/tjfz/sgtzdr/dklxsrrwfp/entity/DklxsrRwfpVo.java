package org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.entity;

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
 * @Description: 手工台账：贷款利息收入任务分配
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Data
public class DklxsrRwfpVo {

	/**机构代码*/
	//@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgbm;
	/**网点名称*/
	@Excel(name = "网点名称", width = 15)
    @ApiModelProperty(value = "网点名称")
	@ExcelVerify(notNull = true)
	private String wdmc;
	/**全年利息收入*/
	@Excel(name = "全年利息收入", width = 15)
    @ApiModelProperty(value = "全年利息收入")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal qnlxsr;
	/**一季度利息收入*/
	@Excel(name = "一季度利息收入", width = 15)
    @ApiModelProperty(value = "一季度利息收入")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal jdlxsrOne;
	/**二季度利息收入*/
	@Excel(name = "二季度利息收入", width = 15)
    @ApiModelProperty(value = "二季度利息收入")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal jdlxsrTwo;
	/**三季度利息收入*/
	@Excel(name = "三季度利息收入", width = 15)
    @ApiModelProperty(value = "三季度利息收入")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal jdlxsrThree;
	/**四季度利息收入*/
	@Excel(name = "四季度利息收入", width = 15)
    @ApiModelProperty(value = "四季度利息收入")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal jdlxsrFour;
}
