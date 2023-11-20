package org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.entity;

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
 * @Description: 数据字段管理配置
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Data
@TableName("Xddagl_sjzdgl_item")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_sjzdgl_item对象", description="数据字段管理配置")
public class Sjzdglpz {
    
	/**字典编码*/
	@Excel(name = "字典编码", width = 15)
    @ApiModelProperty(value = "字典编码")
	private String dictCode;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
	private String dictKey;
	/**数据值*/
	@Excel(name = "数据值", width = 15)
    @ApiModelProperty(value = "数据值")
	private Integer dictValue;
	/**关联的DICT_CODE*/
	@Excel(name = "关联的DICT_CODE", width = 15)
    @ApiModelProperty(value = "关联的DICT_CODE")
	private String glCode;
	/**关联的DICT_CODE---value*/
	@Excel(name = "关联的DICT_CODE---value", width = 15)
    @ApiModelProperty(value = "关联的DICT_CODE---value")
	private Integer glValue;
}
