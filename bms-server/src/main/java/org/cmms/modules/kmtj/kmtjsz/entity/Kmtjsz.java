package org.cmms.modules.kmtj.kmtjsz.entity;

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
 * @Description: 科目统计设置
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Data
@TableName("Sys_bas_cfg_tjkm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Sys_bas_cfg_tjkm对象", description="科目统计设置")
public class Kmtjsz {

	/**统计数据项标识*/
	@Excel(name = "统计数据项标识", width = 15)
    @ApiModelProperty(value = "统计数据项标识")
	private String tjbs;
	/**统计数据项名称*/
	@Excel(name = "统计数据项名称", width = 15)
    @ApiModelProperty(value = "统计数据项名称")
	private String tjmc;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String kmh;
	/**ab*/
	@Excel(name = "记账符号", width = 15,dicCode = "abs")
    @ApiModelProperty(value = "记账符号")
	@Dict(dicCode = "abs")
	private Integer ab;
}
