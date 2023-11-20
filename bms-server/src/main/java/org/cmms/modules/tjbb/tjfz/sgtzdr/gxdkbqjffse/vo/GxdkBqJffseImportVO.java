package org.cmms.modules.tjbb.tjfz.sgtzdr.gxdkbqjffse.vo;

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
 * @Description: 各项贷款本期借方发生额
 * @Author: Penghr
 * @Date:   2022-12-28
 * @Version: V1.0
 */
@Data
public class GxdkBqJffseImportVO {

	/**数据月份*/
	@Excel(name = "数据月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据月份")
	@ExcelVerify(notNull = true)
	private Date dataDate;
	/**机构编码*/
	//@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@ExcelVerify(notNull = true)
	private String jgbm;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String jgmc;

	/**各项贷款本期借方发生额*/
	@Excel(name = "各项贷款本期借方发生额", width = 15)
	@ApiModelProperty(value = "各项贷款本期借方发生额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal gxdkBqjffse;

	/**录入标识(0 导入 1 录入 2 修改)*/
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
