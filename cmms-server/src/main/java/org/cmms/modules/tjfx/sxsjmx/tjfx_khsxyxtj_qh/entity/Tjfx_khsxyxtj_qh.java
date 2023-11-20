package org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.entity;

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
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Data
@TableName("tjfx_khsxyxtj_qh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_khsxyxtj_qh对象", description="1")
public class Tjfx_khsxyxtj_qh {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private java.util.Date tjyf;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	/**用信额度*/
	@Excel(name = "用信额度", width = 15)
    @ApiModelProperty(value = "用信额度")
	private java.math.BigDecimal yxed;
	/**预授信额度*/
	@Excel(name = "预授信额度", width = 15)
    @ApiModelProperty(value = "预授信额度")
	private java.math.BigDecimal ysxed;
	/**预授信户数*/
	@Excel(name = "预授信户数", width = 15)
	@ApiModelProperty(value = "预授信户数")
	private java.lang.Long ysxhs;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
	@ApiModelProperty(value = "用信户数")
	private java.lang.Long yxhs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
	@ApiModelProperty(value = "总户数")
	private java.lang.Long zhs;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
	@ApiModelProperty(value = "授信户数")
	private java.lang.Long  sxhs;
	/**授信工作评议人数*/
	@Excel(name = "授信工作评议人数", width = 15)
	@ApiModelProperty(value = "授信工作评议人数")
	private java.lang.Long sxgzpyrs;
}
