package org.cmms.modules.report.cwbb.zcfzb.entity;

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
 * @Description: 财务报表资产负债表
 * @Author: Penghr
 * @Date:   2022-12-28
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_cwbb_zcfzb对象", description="财务报表资产负债表")
public class CwbbZcfzbVO {

	/**资产*/
	@Excel(name = "资产", width = 15)
    @ApiModelProperty(value = "资产")
	private String zc;
	/**行次*/
	@Excel(name = "行次", width = 15)
    @ApiModelProperty(value = "行次")
	private String hc1;
	/**期末余额*/
	@Excel(name = "期末余额", width = 15)
    @ApiModelProperty(value = "期末余额")
	private java.math.BigDecimal zcqmye;
	/**年初余额*/
	@Excel(name = "年初余额", width = 15)
    @ApiModelProperty(value = "年初余额")
	private java.math.BigDecimal zcncye;
	/**负债和所有者权益(或股东权益)*/
	@Excel(name = "负债和所有者权益(或股东权益)", width = 15)
    @ApiModelProperty(value = "负债和所有者权益(或股东权益)")
	private String fzhsyzqy;
	/**行次*/
	@Excel(name = "行次", width = 15)
    @ApiModelProperty(value = "行次")
	private String hc2;
	/**期末余额*/
	@Excel(name = "期末余额", width = 15)
    @ApiModelProperty(value = "期末余额")
	private java.math.BigDecimal fzqmye;
	/**年初余额*/
	@Excel(name = "年初余额", width = 15)
    @ApiModelProperty(value = "年初余额")
	private java.math.BigDecimal fzncye;
}
