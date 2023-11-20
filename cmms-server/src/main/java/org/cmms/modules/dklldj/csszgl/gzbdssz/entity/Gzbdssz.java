package org.cmms.modules.dklldj.csszgl.gzbdssz.entity;

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
 * @Description: 规则表达式设置
 * @Author: jeecg-boot
 * @Date:   2020-03-05
 * @Version: V1.0
 */
@Data
@TableName("RATE_GZBDSXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_GZBDSXX对象", description="规则表达式设置")
public class Gzbdssz {

    /**区域代码*/
    //@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
    private String qydm;
	/**指标规则ID*/
	@Excel(name = "指标规则项目", width = 15, dicCode = "zbgzid", dictTable = "rate_zbgzxxb", dicText = "zbgzmc", ds = "rate") // rate
    @ApiModelProperty(value = "指标规则ID")
    @Dict(dicCode = "zbgzid", dictTable = "rate_zbgzxxb", dicText = "zbgzmc", ds = "rate") // rate
	private String zbgzid;
	/**表达式键值*/
	@Excel(name = "表达式键值", width = 15)
    @ApiModelProperty(value = "表达式键值")
	private Integer bdskey;
	/**表达式值*/
	@Excel(name = "表达式值", width = 15)
    @ApiModelProperty(value = "表达式值")
	private String bdsvalue;
	/**表达式分值*/
	@Excel(name = "表达式分值", width = 15)
    @ApiModelProperty(value = "表达式分值")
	private java.math.BigDecimal bdsfz;
}
