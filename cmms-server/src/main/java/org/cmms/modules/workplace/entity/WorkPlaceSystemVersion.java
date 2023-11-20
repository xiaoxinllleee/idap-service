package org.cmms.modules.workplace.entity;

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
 * @Description: 系统版本号
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Data
@TableName("system_version")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="system_version对象", description="系统版本号")
public class WorkPlaceSystemVersion {
    
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
	private Date updateDate;
	/**更新内容*/
	@Excel(name = "更新内容", width = 15)
    @ApiModelProperty(value = "更新内容")
	private String content;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
	private String version;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
	@ApiModelProperty(value = "区域代码")
	private String qydm;
	/**系统标识*/
	@Excel(name = "系统标识", width = 15)
	@ApiModelProperty(value = "系统标识")
	private String sysflag;
}
