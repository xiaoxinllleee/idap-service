package org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.entity;

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
 * @Description: 商户走访数据明细统计_全行
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Data
@TableName("TJFX_SHZFSJMX_QH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_SHZFSJMX_QH对象", description="商户走访数据明细统计_全行")
public class Tjfx_shzfsjmx_qh {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**有效走访户数*/
	@Excel(name = "有效走访户数", width = 15)
    @ApiModelProperty(value = "有效走访户数")
	private Long yxzfhs;
	/**全行总户数*/
	@Excel(name = "全行总户数", width = 15)
    @ApiModelProperty(value = "全行总户数")
	private Long zhs;
	/**走访覆盖率*/
	@Excel(name = "走访覆盖率", width = 15)
    @ApiModelProperty(value = "走访覆盖率")
	private java.math.BigDecimal zffgl;
	/**累计走访户数*/
	@Excel(name = "累计走访户数", width = 15)
    @ApiModelProperty(value = "累计走访户数")
	private Long ljzfhs;
	/**涉及走访户数*/
	@Excel(name = "涉及走访户数", width = 15)
    @ApiModelProperty(value = "涉及走访户数")
	private Long sjzfhs;
}
