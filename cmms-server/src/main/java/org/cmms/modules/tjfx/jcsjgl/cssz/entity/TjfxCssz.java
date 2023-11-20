package org.cmms.modules.tjfx.jcsjgl.cssz.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
@Data
@TableName("TJFX_CSSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_CSSZ对象", description="参数设置")
public class TjfxCssz {

	/**参数编码*/
	@Excel(name = "参数编码", width = 15)
    @ApiModelProperty(value = "参数编码")
	@ExcelVerify(notNull = true)
	private String csbm;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
	@ExcelVerify(notNull = true)
	private String csmc;
	/**参数值*/
	@Excel(name = "参数值", width = 15)
    @ApiModelProperty(value = "参数值")
	@ExcelVerify(notNull = true)
	private String csz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	@ExcelVerify(interHandler = true)
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}
