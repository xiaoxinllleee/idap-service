package org.cmms.modules.tjfx.tjbb.khxxbb.entity;

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
 * @Description: 客户信息报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHXXBB_QH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHXXBB_QH对象", description="客户信息报表(全行)")
public class Qhkhxxbb {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**村社数*/
	@Excel(name = "村社数", width = 15)
    @ApiModelProperty(value = "村社数")
	private Integer csl;
	/**总客户数*/
	@Excel(name = "总客户数", width = 15)
    @ApiModelProperty(value = "总客户数")
	private Integer zkhs;
	/**信息完善客户数*/
	@Excel(name = "信息完善客户数", width = 15)
    @ApiModelProperty(value = "信息完善客户数")
	private Integer xxwskhs;
	/**信息采集客户数*/
	@Excel(name = "信息采集客户数", width = 15)
    @ApiModelProperty(value = "信息采集客户数")
	private Integer xxcjkhs;
	/**累计授信客户数*/
	@Excel(name = "累计授信客户数", width = 15)
    @ApiModelProperty(value = "累计授信客户数")
	private java.math.BigDecimal ljsxkhs;
	/**本月授信客户数*/
	@Excel(name = "本月授信客户数", width = 15)
    @ApiModelProperty(value = "本月授信客户数")
	private java.math.BigDecimal bysxkhs;
	/**累计用信客户数*/
	@Excel(name = "累计用信客户数", width = 15)
    @ApiModelProperty(value = "累计用信客户数")
	private java.math.BigDecimal ljyxkhs;
	/**本月用信客户数*/
	@Excel(name = "本月用信客户数", width = 15)
    @ApiModelProperty(value = "本月用信客户数")
	private java.math.BigDecimal byyxkhs;
}
