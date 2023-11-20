package org.cmms.modules.report.sgtzgl.cwbsgtz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 财务部_成本核算表
 * @Author: jeecg_boot
 * @Date:   2023_06_13
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cwb_sgtz_bwbcbhsb对象", description="财务部_成本核算表")
public class CwbSgtzBwbcbhsbFZZCVO {

	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String zcxmmc;
	/**行号*/
	@Excel(name = "行号", width = 15)
    @ApiModelProperty(value = "行号")
	private String hh1;
	/**平均余额(万元)*/
	@Excel(name = "平均余额(万元)", width = 15)
    @ApiModelProperty(value = "平均余额(万元)")
	private java.math.BigDecimal zcpjye;
	/**实收利息额(元)*/
	@Excel(name = "实收利息额(元)", width = 15)
    @ApiModelProperty(value = "实收利息额(元)")
	private java.math.BigDecimal zcshlxe;
	/**收息率(百分比)*/
	@Excel(name = "收息率(百分比)", width = 15)
    @ApiModelProperty(value = "收息率(百分比)")
	private String zcshl;
	/**比重(百分比)*/
	@Excel(name = "比重(百分比)", width = 15)
    @ApiModelProperty(value = "比重(百分比)")
	private String zcbz;
	/**执行利率加权(百分比)*/
	@Excel(name = "执行利率加权(百分比)", width = 15)
    @ApiModelProperty(value = "执行利率加权(百分比)")
	private String zczxlljq;
	/**应收利息额(元)*/
	@Excel(name = "应收利息额(元)", width = 15)
    @ApiModelProperty(value = "应收利息额(元)")
	private java.math.BigDecimal zcyslxe;


}
