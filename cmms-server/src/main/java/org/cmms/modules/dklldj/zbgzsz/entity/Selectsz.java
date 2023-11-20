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
 * @Description: 指标类型设置(select)
 * @Author: jeecg-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Data
@TableName("RATE_ZBGZ_SELECT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_ZBGZ_SELECT对象", description="指标类型设置(select)")
public class Selectsz {
    
	/**zbgzid*/
	@Excel(name = "zbgzid", width = 15)
    @ApiModelProperty(value = "zbgzid")
	private String zbgzid;
	/**是否支持多选*/
	@Excel(name = "是否支持多选", width = 15)
    @ApiModelProperty(value = "是否支持多选")
	private String multiple;
	/**是否可以清空选项，只在单选时有效*/
	@Excel(name = "是否可以清空选项，只在单选时有效", width = 15)
    @ApiModelProperty(value = "是否可以清空选项，只在单选时有效")
	private String clearable;
	/**是否支持搜索*/
	@Excel(name = "是否支持搜索", width = 15)
    @ApiModelProperty(value = "是否支持搜索")
	private String filterable;
	/**选择框默认文字*/
	@Excel(name = "选择框默认文字", width = 15)
    @ApiModelProperty(value = "选择框默认文字")
	private String placeholder;
	/**当下拉列表为空时显示的内*/
	@Excel(name = "当下拉列表为空时显示的内", width = 15)
    @ApiModelProperty(value = "当下拉列表为空时显示的内")
	private String notfoundtext;
	/**弹窗的展开方向，可选值为 bottom 和 top*/
	@Excel(name = "弹窗的展开方向，可选值为 bottom 和 top", width = 15)
    @ApiModelProperty(value = "弹窗的展开方向，可选值为 bottom 和 top")
	private String placement;
	/**设置为禁用状态*/
	@Excel(name = "设置为禁用状态", width = 15)
    @ApiModelProperty(value = "设置为禁用状态")
	private String disabled;
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
