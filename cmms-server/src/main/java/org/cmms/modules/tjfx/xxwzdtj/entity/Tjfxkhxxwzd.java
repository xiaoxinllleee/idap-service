package org.cmms.modules.tjfx.xxwzdtj.entity;

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
 * @Description: 客户信息完整度统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHXXWZD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHXXWZD对象", description="客户信息完整度统计")
public class Tjfxkhxxwzd {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**支行名称*/
	@Excel(name = "支行名称", width = 15)
    @ApiModelProperty(value = "支行名称")
	private String zhmc;
	/**营销单元*/
	@Excel(name = "营销单元", width = 15)
    @ApiModelProperty(value = "营销单元")
	private String yxdy;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String khjl;
	/**信息完整度*/
	@Excel(name = "信息完整度", width = 15)
    @ApiModelProperty(value = "信息完整度")
	private String xxwzd;
	/**人数*/
	@Excel(name = "人数", width = 15)
    @ApiModelProperty(value = "人数")
	private String rs;
	/**占比*/
	@Excel(name = "占比", width = 15)
    @ApiModelProperty(value = "占比")
	private java.math.BigDecimal zb;
}
