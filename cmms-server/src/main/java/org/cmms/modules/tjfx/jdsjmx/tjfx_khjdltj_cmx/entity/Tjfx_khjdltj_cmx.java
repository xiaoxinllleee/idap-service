package org.cmms.modules.tjfx.jdsjmx.tjfx_khjdltj_cmx.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-20
 * @Version: V1.0
 */
@Data
@TableName("tjfx_khjdltj_cmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_khjdltj_cmx对象", description="1")
public class Tjfx_khjdltj_cmx {

	/**建档月份*/
	@Excel(name = "建档月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "建档月份")
	private Date tjyf;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	private String xzc;
	/**建档人数*/
	@Excel(name = "建档人数", width = 15)
    @ApiModelProperty(value = "建档人数")
	private Long jdrs;
	/**整村人数*/
	@Excel(name = "整村人数", width = 15)
    @ApiModelProperty(value = "整村人数")
	private Long zcrs;
	/**建档覆盖率*/
	@Excel(name = "建档覆盖率", width = 15)
    @ApiModelProperty(value = "建档覆盖率")
	private java.math.BigDecimal jdfgl;
}
