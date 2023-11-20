package org.cmms.modules.xdgl.grdkgl.entity;

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
 * @Description: RateGzbdsxx
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Data
@TableName("Rate_gzbdsxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rate_gzbdsxx对象", description="RateGzbdsxx")
public class RateGzbdsxx {

	/**指标规则ID*/
	@Excel(name = "指标规则ID", width = 15)
    @ApiModelProperty(value = "指标规则ID")
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
	private Double bdsfz;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**客户类型(1.个人/2.企业)*/
	@Excel(name = "客户类型(1.个人/2.企业)", width = 15)
    @ApiModelProperty(value = "客户类型(1.个人/2.企业)")
	private String khlx;
}
