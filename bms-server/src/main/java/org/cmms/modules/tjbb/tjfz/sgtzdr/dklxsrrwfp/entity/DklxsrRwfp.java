package org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 手工台账：贷款利息收入任务分配
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Data
@TableName("tjbb_sgtz_dklxsrrwfp")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_sgtz_dklxsrrwfp对象", description="手工台账：贷款利息收入任务分配")
public class DklxsrRwfp {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private java.util.Date dataDate;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private java.lang.String jgbm;
	/**网点名称*/
	@Excel(name = "网点名称", width = 15)
    @ApiModelProperty(value = "网点名称")
	private java.lang.String wdmc;
	/**全年利息收入*/
	@Excel(name = "全年利息收入", width = 15)
    @ApiModelProperty(value = "全年利息收入")
	private java.math.BigDecimal qnlxsr;
	/**一季度利息收入*/
	@Excel(name = "一季度利息收入", width = 15)
    @ApiModelProperty(value = "一季度利息收入")
	private java.math.BigDecimal jdlxsrOne;
	/**二季度利息收入*/
	@Excel(name = "二季度利息收入", width = 15)
    @ApiModelProperty(value = "二季度利息收入")
	private java.math.BigDecimal jdlxsrTwo;
	/**三季度利息收入*/
	@Excel(name = "三季度利息收入", width = 15)
    @ApiModelProperty(value = "三季度利息收入")
	private java.math.BigDecimal jdlxsrThree;
	/**四季度利息收入*/
	@Excel(name = "四季度利息收入", width = 15)
    @ApiModelProperty(value = "四季度利息收入")
	private java.math.BigDecimal jdlxsrFour;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	private java.lang.Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private java.util.Date lrsj;
}
