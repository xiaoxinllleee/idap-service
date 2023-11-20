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
 * @Description: 指标类型设置(input)
 * @Author: jeecg-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Data
@TableName("RATE_ZBGZ_INPUT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_ZBGZ_INPUT对象", description="指标类型设置(input)")
public class Inputsz {
    
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**zbgzid*/
	@Excel(name = "zbgzid", width = 15)
    @ApiModelProperty(value = "zbgzid")
	private String zbgzid;
	/**输入框类型，可选值为 text、password、textarea、url、email、date*/
	@Excel(name = "输入框类型，可选值为 text、password、textarea、url、email、date", width = 15)
    @ApiModelProperty(value = "输入框类型，可选值为 text、password、textarea、url、email、date")
	private String type;
	/**设置输入框为禁用状态*/
	@Excel(name = "设置输入框为禁用状态", width = 15)
    @ApiModelProperty(value = "设置输入框为禁用状态")
	private String disabled;
	/**设置输入框为只读*/
	@Excel(name = "设置输入框为只读", width = 15)
    @ApiModelProperty(value = "设置输入框为只读")
	private String readonly;
	/**占位提示文本*/
	@Excel(name = "占位提示文本", width = 15)
    @ApiModelProperty(value = "占位提示文本")
	private String placeholder;
	/**设置是否必填*/
	@Excel(name = "设置是否必填", width = 15)
    @ApiModelProperty(value = "设置是否必填")
	private String required;
	/**必填规则设置*/
	@Excel(name = "必填规则设置", width = 15)
    @ApiModelProperty(value = "必填规则设置")
	private String validateRequired;
	/**必填提示信息true*/
	@Excel(name = "必填提示信息true", width = 15)
    @ApiModelProperty(value = "必填提示信息true")
	private String validateMessage;
	/**必填警告文本颜色：blur*/
	@Excel(name = "必填警告文本颜色：blur", width = 15)
    @ApiModelProperty(value = "必填警告文本颜色：blur")
	private String validateTrigger;
}
