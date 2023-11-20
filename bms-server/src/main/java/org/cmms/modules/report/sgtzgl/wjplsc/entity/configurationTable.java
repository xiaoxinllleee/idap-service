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
 * @Description: dwd
 * @Author: jeecg-boot
 * @Date:   2022-10-27
 * @Version: V1.0
 */
@Data
@TableName("CONFIGURATION_TABLE")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CONFIGURATION_TABLE对象", description="dwd")
public class configurationTable {

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**表中文名称*/
	@Excel(name = "表中文名称", width = 15)
    @ApiModelProperty(value = "表中文名称")
	private String tableName;
	/**表名*/
	@Excel(name = "表名", width = 15)
    @ApiModelProperty(value = "表名")
	private String tableCode;


	/**日期*/
	@Excel(name = "日期名称", width = 15)
	@ApiModelProperty(value = "日期名称")
	private String daysName;


	/**实体类路径*/
	@Excel(name = "实体类路径", width = 15)
	@ApiModelProperty(value = "实体类路径")
	private String entityPath;


	/**实体类路径*/
	@Excel(name = "service路径", width = 15)
	@ApiModelProperty(value = "service路径")
	private String servicePath;

	/**标题的行*/
	@Excel(name = "标题的行", width = 15)
	@ApiModelProperty(value = "标题的行")
	private Integer titleRow;


	/**文件对应的日期*/
	@Excel(name = "头部的行", width = 15)
	@ApiModelProperty(value = "头部的行")
	private Integer headRow;

	/**是否单一sheet*/
	@Excel(name = "是否单一sheet", width = 15)
	@ApiModelProperty(value = "是否单一sheet")
	private String singleSheet;
}
