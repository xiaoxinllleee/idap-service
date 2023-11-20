package org.cmms.modules.report.cwbb.xyssyb.entity;

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
 * @Description: 信用社损益表
 * @Author: jeecg-boot
 * @Date:   2022-05-10
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_cwbb_xyssyb对象", description="信用社损益表")
public class XyssybVo {

	/**科目号*/
	@Excel(name = "行次", width = 15)
    @ApiModelProperty(value = "行次")
	private String kmh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**上年同期数*/
	@Excel(name = "上年同期数", width = 15)
    @ApiModelProperty(value = "上年同期数")
	private java.math.BigDecimal sntqs;
	/**本年累计数*/
	@Excel(name = "本年累计数", width = 15)
    @ApiModelProperty(value = "本年累计数")
	private java.math.BigDecimal bnljs;
	/**数据日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;
}
