package org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsr.vo;

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
 * @Description: 贷款利息收入 导入VO
 * @Author: Penghr
 * @Date:   2022-12-28
 * @Version: V1.0
 */
@Data
public class DklxsrImportVO {

	/**数据月份*/
	@Excel(name = "数据月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据月份")
	@ExcelVerify(notNull = true)
	private Date dataDate;
	/**机构编码*/
	//@Excel(name = "网点名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@ExcelVerify(notNull = true)
	private String jgbm;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String jgmc;

	/**1132应收贷款利息*/
	@Excel(name = "1132应收贷款利息", width = 15)
	@ApiModelProperty(value = "1132应收贷款利息")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal ysdklx;
	/**6011利息收入*/
	@Excel(name = "6011利息收入", width = 15)
	@ApiModelProperty(value = "6011利息收入")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal lxsr;

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
