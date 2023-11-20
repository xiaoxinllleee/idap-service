package org.cmms.modules.tjfx.jdsjmx.tjfx_khjdltj_qh.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-20
 * @Version: V1.0
 */
@Data
@TableName("tjfx_khjdltj_qh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_khjdltj_qh对象", description="1")
public class Tjfx_khjdltj_qh {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**建档人数*/
	@Excel(name = "建档人数", width = 15)
    @ApiModelProperty(value = "建档人数")
	private Long jdrs;
	/**全行人数*/
	@Excel(name = "全行人数", width = 15)
    @ApiModelProperty(value = "全行人数")
	private Long qhrs;
	/**建档覆盖率*/
	@Excel(name = "建档覆盖率", width = 15)
    @ApiModelProperty(value = "建档覆盖率")
	private java.math.BigDecimal jdfgl;
}
