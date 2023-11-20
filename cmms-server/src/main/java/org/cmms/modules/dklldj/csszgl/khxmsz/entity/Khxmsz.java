package org.cmms.modules.dklldj.csszgl.khxmsz.entity;

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
 * @Description: 考核项目设置
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@Data
@TableName("rate_zbkxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_zbkxxb对象", description="考核项目设置")
public class Khxmsz {

    /**区域代码*/
    //@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
    private String qydm;
    /**指标ID (主键)*/
    @Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
    private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
    @ApiModelProperty(value = "指标名称")
	private String zbmc;
	/**录入方式(1-单选/2-文本框/3-多选)*/
	@Excel(name = "录入方式", width = 15, dicCode = "dklldj_lrfs")
    @ApiModelProperty(value = "录入方式")
    @Dict(dicCode = "dklldj_lrfs")
	private String llfs;
}
