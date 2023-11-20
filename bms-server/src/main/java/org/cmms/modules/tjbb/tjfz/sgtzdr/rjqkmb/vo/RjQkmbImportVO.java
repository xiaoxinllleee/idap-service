package org.cmms.modules.tjbb.tjfz.sgtzdr.rjqkmb.vo;

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
 * @Description: 日均全科目表
 * @Author: Penghr
 * @Date:   2022-12-28
 * @Version: V1.0
 */
@Data
@TableName("tjbb_sgtz_qkmb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_sgtz_qkmb对象", description="日均全科目表")
public class RjQkmbImportVO {

	/**数据日期*/
	//@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	@ExcelVerify(notNull = true)
	private Date dataDate;
	/**项目代号*/
	@Excel(name = "项目代号", width = 15)
    @ApiModelProperty(value = "项目代号")
	@ExcelVerify(notNull = true)
	private String xmdh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	@ExcelVerify(notNull = false)
	private String xmmc;
	/**币种*/
	//@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	//@ExcelVerify(notNull = false)
	private String bz;
	/**期末借方年日均余额*/
	@Excel(name = "期末借方年日均余额", width = 15)
    @ApiModelProperty(value = "期末借方年日均余额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal qmjfNrjye;
	/**期末贷方年日均余额*/
	@Excel(name = "期末贷方年日均余额", width = 15)
    @ApiModelProperty(value = "期末贷方年日均余额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal qmdfNrjye;
	/**项目代号*/
	@Excel(name = "项目代号", width = 15)
    @ApiModelProperty(value = "项目代号")
	@ExcelVerify(notNull = false)
	private String xmdhT;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
