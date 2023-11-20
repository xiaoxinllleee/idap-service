package org.cmms.modules.rwzx.rwwcmx.entity;

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
 * @Description: 统计分析-任务完成明细
 * @Author: jeecg-boot
 * @Date:   2023-08-09
 * @Version: V1.0
 */
@Data
@TableName("TJFX_TASK_WCMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_TASK_WCMX对象", description="统计分析-任务完成明细")
public class TjfxTaskWcmx {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private java.util.Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private java.lang.String sszh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private java.lang.String ygxm;
	/**任务类型*/
	@Excel(name = "任务类型", width = 15,dicCode ="marketing_type1" )
    @ApiModelProperty(value = "任务类型")
	@Dict(dicCode = "marketing_type1")
	private java.lang.String rwlx;
	/**当日任务*/
	@Excel(name = "当日任务", width = 15)
    @ApiModelProperty(value = "当日任务")
	private java.lang.Integer drrw;
	/**当日完成*/
	@Excel(name = "当日完成", width = 15)
    @ApiModelProperty(value = "当日完成")
	private java.lang.Integer drwc;
	/**当日走访*/
	@Excel(name = "当日走访", width = 15)
    @ApiModelProperty(value = "当日走访")
	private java.lang.Integer drzf;
	/**当日营销成功*/
	@Excel(name = "当日营销成功", width = 15)
    @ApiModelProperty(value = "当日营销成功")
	private java.lang.Integer dryxcg;
	/**当日营销失败*/
	@Excel(name = "当日营销失败", width = 15)
    @ApiModelProperty(value = "当日营销失败")
	private java.lang.Integer dryxsb;
	/**累计任务*/
	@Excel(name = "累计任务", width = 15)
    @ApiModelProperty(value = "累计任务")
	private java.lang.Integer ljrw;
	/**累计完成*/
	@Excel(name = "累计完成", width = 15)
    @ApiModelProperty(value = "累计完成")
	private java.lang.Integer ljwc;
	/**累计走访*/
	@Excel(name = "累计走访", width = 15)
    @ApiModelProperty(value = "累计走访")
	private java.lang.Integer ljzf;
	/**累计营销成功*/
	@Excel(name = "累计营销成功", width = 15)
    @ApiModelProperty(value = "累计营销成功")
	private java.lang.Integer ljyxcg;
	/**累计营销失败*/
	@Excel(name = "累计营销失败", width = 15)
    @ApiModelProperty(value = "累计营销失败")
	private java.lang.Integer ljyxsb;
	/**客户转换率*/
	@Excel(name = "客户转换率", width = 15)
    @ApiModelProperty(value = "客户转换率")
	private java.math.BigDecimal khzhl;
}
