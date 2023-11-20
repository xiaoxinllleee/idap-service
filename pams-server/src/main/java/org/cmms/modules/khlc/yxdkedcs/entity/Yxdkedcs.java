package org.cmms.modules.khlc.yxdkedcs.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 营销贷款额度参数
 * @Author: jeecg-boot
 * @Date:   2023-03-29
 * @Version: V1.0
 */
@Data
@TableName("khgxgl_yxdkedcs")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgxgl_yxdkedcs对象", description="营销贷款额度参数")
public class Yxdkedcs {

	/**参数编号*/
	@Excel(name = "参数编号", width = 15)
    @ApiModelProperty(value = "参数编号")
	@ExcelVerify(notNull = true)
	private String csbh;
	/**参数值起*/
	@Excel(name = "参数值起", width = 15)
    @ApiModelProperty(value = "参数值起")
	@ExcelVerify(notNull = false)
	private String cszq;
	/**参数值止*/
	@Excel(name = "参数值止", width = 15)
    @ApiModelProperty(value = "参数值止")
	@ExcelVerify(notNull = false)
	private String cszz;
	/**调整系数*/
	@Excel(name = "调整系数", width = 15)
    @ApiModelProperty(value = "调整系数")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal tzxs;
	/**风险计提系数*/
	@Excel(name = "风险计提系数", width = 15)
	@ApiModelProperty(value = "风险计提系数")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal fxjtxs;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	@ExcelVerify(notNull = false)
	private String bz;
	/**lrr*/
//	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**lrsj*/
//	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
//	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
}
