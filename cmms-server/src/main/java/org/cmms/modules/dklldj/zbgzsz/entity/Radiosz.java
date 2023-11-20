package org.cmms.modules.dklldj.zbgzsz.entity;

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
 * @Description: 指标类型设置(radio)
 * @Author: jeecg-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Data
@TableName("RATE_ZBGZ_RADIO")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_ZBGZ_RADIO对象", description="指标类型设置(radio)")
public class Radiosz {
    
	/**指标规则id*/
	@Excel(name = "指标规则id", width = 15)
    @ApiModelProperty(value = "指标规则id")
	private String zbgzid;
	/**可选值为 button 或不填，*/
	@Excel(name = "可选值为 button 或不填，", width = 15)
    @ApiModelProperty(value = "可选值为 button 或不填，")
	private String type;
	/**是否垂直排列，按钮样式下无效*/
	@Excel(name = "是否垂直排列，按钮样式下无效", width = 15)
    @ApiModelProperty(value = "是否垂直排列，按钮样式下无效")
	private String vertical;
	/**值*/
	@Excel(name = "值", width = 15)
    @ApiModelProperty(value = "值")
	private java.math.BigDecimal optionsValue;
	/**选择项文本*/
	@Excel(name = "选择项文本", width = 15)
    @ApiModelProperty(value = "选择项文本")
	private String optionsLable;
	/**是否禁用选项*/
	@Excel(name = "是否禁用选项", width = 15)
    @ApiModelProperty(value = "是否禁用选项")
	private String optionsDisabled;
	/**排序序号*/
	@Excel(name = "排序序号", width = 15)
    @ApiModelProperty(value = "排序序号")
	private Integer pxxh;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
}
