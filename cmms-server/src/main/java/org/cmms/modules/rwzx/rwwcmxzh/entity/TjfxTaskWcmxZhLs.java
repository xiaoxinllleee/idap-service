package org.cmms.modules.rwzx.rwwcmxzh.entity;

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
 * @Description: 统计分析-任务完成明细_支行_蓝山
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
@Data
@TableName("TJFX_TASK_WCMX_ZH_LS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_TASK_WCMX_ZH_LS对象", description="统计分析-任务完成明细_支行_蓝山")
public class TjfxTaskWcmxZhLs {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
	private String rwmc;
	/**当日任务*/
	@Excel(name = "当日任务", width = 15)
    @ApiModelProperty(value = "当日任务")
	private Integer drrw;
	/**当日完成*/
	@Excel(name = "当日完成", width = 15)
    @ApiModelProperty(value = "当日完成")
	private Integer drwc;
	/**当日走访*/
	@Excel(name = "当日走访", width = 15)
    @ApiModelProperty(value = "当日走访")
	private Integer drzf;
	/**当日营销成功*/
	@Excel(name = "当日营销成功", width = 15)
    @ApiModelProperty(value = "当日营销成功")
	private Integer dryxcg;
	/**当日营销失败*/
	@Excel(name = "当日营销失败", width = 15)
    @ApiModelProperty(value = "当日营销失败")
	private Integer dryxsb;
	/**累计任务*/
	@Excel(name = "累计任务", width = 15)
    @ApiModelProperty(value = "累计任务")
	private Integer ljrw;
	/**累计完成*/
	@Excel(name = "累计完成", width = 15)
    @ApiModelProperty(value = "累计完成")
	private Integer ljwc;
	/**累计走访*/
	@Excel(name = "累计走访", width = 15)
    @ApiModelProperty(value = "累计走访")
	private Integer ljzf;
	/**累计营销成功*/
	@Excel(name = "累计营销成功", width = 15)
    @ApiModelProperty(value = "累计营销成功")
	private Integer ljyxcg;
	/**累计营销失败*/
	@Excel(name = "累计营销失败", width = 15)
    @ApiModelProperty(value = "累计营销失败")
	private Integer ljyxsb;
	/**客户转换率*/
	@Excel(name = "客户转换率", width = 15)
    @ApiModelProperty(value = "客户转换率")
	private java.math.BigDecimal khzhl;
}
