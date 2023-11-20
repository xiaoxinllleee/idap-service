package org.cmms.modules.xddagl.xtgl.damlgl.entity;

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
import lombok.ToString;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 档案目录管理(参数配置)
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
@ToString
@TableName("Xddagl_damlgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_damlgl对象", description="档案目录管理(参数配置)")
public class Damlgl {
    
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
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
}
