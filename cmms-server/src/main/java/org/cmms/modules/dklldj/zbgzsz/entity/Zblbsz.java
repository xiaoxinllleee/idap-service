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
 * @Description: 指标类别设置
 * @Author: jeecg-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Data
@TableName("RATE_ZBLB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_ZBLB对象", description="指标类别设置")
public class Zblbsz {
    
	/**类别ID*/
	@Excel(name = "类别ID", width = 15)
    @ApiModelProperty(value = "类别ID")
	private String zbid;
	/**类别名称*/
	@Excel(name = "类别名称", width = 15)
    @ApiModelProperty(value = "类别名称")
	private String zbmc;
	/**排序序号*/
	@Excel(name = "排序序号", width = 15)
    @ApiModelProperty(value = "排序序号")
	private Integer pxxh;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**是否否用*/
	@Excel(name = "是否否用", width = 15)
    @ApiModelProperty(value = "是否否用")
	private String sfqy;
}
