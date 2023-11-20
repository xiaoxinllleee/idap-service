package org.cmms.modules.tjfx.tjbb.wdkq.hfkqbb.entity;

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
 * @Date:   2020-04-20
 * @Version: V1.0
 */
@Data
@TableName("tjfx_tjbb_qhhfkqtj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_tjbb_qhhfkqtj对象", description="1")
public class Tjfx_tjbb_qhhfkqtj {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**回访次数*/
	@Excel(name = "回访次数", width = 15)
    @ApiModelProperty(value = "回访次数")
	private java.math.BigDecimal hfcs;
	/**有效回访次数*/
	@Excel(name = "有效回访次数", width = 15)
    @ApiModelProperty(value = "有效回访次数")
	private java.math.BigDecimal yxhfcs;
	/**有效出勤天数*/
	@Excel(name = "有效出勤天数", width = 15)
    @ApiModelProperty(value = "有效出勤天数")
	private java.math.BigDecimal yxhfts;
	/**当年有效出勤天数*/
	@Excel(name = "当年有效出勤天数", width = 15)
    @ApiModelProperty(value = "当年有效出勤天数")
	private java.math.BigDecimal dnyxhfts;
}
