package org.cmms.modules.report.sgtzgl.wjplsc.entity;

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
 * @Description: 导入sheet配置
 * @Author: jeecg-boot
 * @Date:   2023-06-12
 * @Version: V1.0
 */
@Data
@TableName("CONFIGURATION_TABLE_SHEET")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CONFIGURATION_TABLE_SHEET对象", description="导入sheet配置")
public class ConfigurationTableSheet {
    
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private java.lang.String id;
	/**表中文名称*/
	@Excel(name = "表中文名称", width = 15)
    @ApiModelProperty(value = "表中文名称")
	private java.lang.String tableName;
	/**sheet名*/
	@Excel(name = "sheet名", width = 15)
    @ApiModelProperty(value = "sheet名")
	private java.lang.String sheetName;
	/**表名*/
	@Excel(name = "表名", width = 15)
    @ApiModelProperty(value = "表名")
	private java.lang.String tableCode;
	/**日期名称*/
	@Excel(name = "日期名称", width = 15)
    @ApiModelProperty(value = "日期名称")
	private java.lang.String daysName;
	/**对应表实体类全路径名称*/
	@Excel(name = "对应表实体类全路径名称", width = 15)
    @ApiModelProperty(value = "对应表实体类全路径名称")
	private java.lang.String entityPath;
	/**sevice路径*/
	@Excel(name = "sevice路径", width = 15)
    @ApiModelProperty(value = "sevice路径")
	private java.lang.String servicePath;
	/**标题的行*/
	@Excel(name = "标题的行", width = 15)
    @ApiModelProperty(value = "标题的行")
	private java.lang.Integer titleRow;
	/**头部的行*/
	@Excel(name = "头部的行", width = 15)
    @ApiModelProperty(value = "头部的行")
	private java.lang.Integer headRow;
}
