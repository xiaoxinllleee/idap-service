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
 * @Description: 指标规则设置
 * @Author: jeecg-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Data
@TableName("rate_zbgzxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_zbgzxxb对象", description="指标规则设置")
public class Zbgzsz {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	private String zbid;
	/**指标规则ID*/
	@Excel(name = "指标规则ID", width = 15)
    @ApiModelProperty(value = "指标规则ID")
	private String zbgzid;
	/**指标规则名称*/
	@Excel(name = "指标规则名称", width = 15)
    @ApiModelProperty(value = "指标规则名称")
	private String zbgzmc;
	/**默认值*/
	@Excel(name = "默认值", width = 15)
    @ApiModelProperty(value = "默认值")
	private String value;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**排序序号*/
	@Excel(name = "排序序号", width = 15)
    @ApiModelProperty(value = "排序序号")
	private Integer pxxh;
	/**input-输入框，radio-单选，select-下拉，checkbox-多选,DatePicker-日期*/
	@Excel(name = "指标类型", width = 15)
    @ApiModelProperty(value = "指标类型")
	private String zblx;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
    @ApiModelProperty(value = "是否启用")
	private String sfqy;
}
