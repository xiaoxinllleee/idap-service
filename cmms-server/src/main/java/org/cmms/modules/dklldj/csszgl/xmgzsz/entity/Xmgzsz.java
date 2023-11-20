package org.cmms.modules.dklldj.csszgl.xmgzsz.entity;

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
 * @Description: 项目规则设置
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@Data
@TableName("rate_zbgzxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_zbgzxxb对象", description="项目规则设置")
public class Xmgzsz {

    /**区域代码*/
    //@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
    private String qydm;
	/**指标ID*/
	@Excel(name = "指标项目名称", width = 15, dicCode = "zbid", dictTable = "rate_zbkxxb", dicText = "zbmc", ds = "rate") // rate
    @ApiModelProperty(value = "指标ID")
    @Dict(dicCode = "zbid", dictTable = "rate_zbkxxb", dicText = "zbmc", ds = "rate") // rate
	private String zbid;
	/**指标规则ID*/
	@Excel(name = "指标规则ID", width = 15)
    @ApiModelProperty(value = "指标规则ID")
	private String zbgzid;
	/**指标规则名称*/
	@Excel(name = "指标规则名称", width = 15)
    @ApiModelProperty(value = "指标规则名称")
	private String zbgzmc;
	/**计分符合*/
	@Excel(name = "指标符号", width = 15, dicCode = "abs")
    @ApiModelProperty(value = "指标符号")
    @Dict(dicCode = "abs")
	private String zbabs;
	/**指标规则分值*/
	@Excel(name = "指标规则分值", width = 15)
    @ApiModelProperty(value = "指标规则分值")
	private java.math.BigDecimal zbgzfz;
	/**指标结果*/
	@Excel(name = "指标结果", width = 15)
    @ApiModelProperty(value = "指标结果")
	private String zbjg;
	/**是否只读*/
	@Excel(name = "是否只读", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否只读")
    @Dict(dicCode = "sfbz")
	private String readonly;
	/**排序序号*/
	//@Excel(name = "排序序号", width = 15)
    @ApiModelProperty(value = "排序序号")
	private Integer pxxh;
}
