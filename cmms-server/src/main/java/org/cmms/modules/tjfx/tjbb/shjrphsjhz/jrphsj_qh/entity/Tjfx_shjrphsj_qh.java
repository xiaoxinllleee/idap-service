package org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.entity;

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
 * @Description: 全行金融普惠数据
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
@Data
@TableName("TJFX_SHJRPHSJ_qh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_SHJRPHSJ_qh对象", description="全行金融普惠数据")
public class Tjfx_shjrphsj_qh {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**走访户数*/
	@Excel(name = "走访户数", width = 15)
    @ApiModelProperty(value = "走访户数")
	private Long zfhs;
	/**其中有效户数*/
	@Excel(name = "其中有效户数", width = 15)
    @ApiModelProperty(value = "其中有效户数")
	private Long qzyxzfhs;
	/**预授信户数*/
	@Excel(name = "预授信户数", width = 15)
    @ApiModelProperty(value = "预授信户数")
	private Long ysxhs;
	/**预授信金额*/
	@Excel(name = "预授信金额", width = 15)
    @ApiModelProperty(value = "预授信金额")
	private java.math.BigDecimal ysxje;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Long yxhs;
	/**用信金额*/
	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;
}
