package org.cmms.modules.xdgl.nsb.entity;

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
 * @Description: 导入文件日志
 * @Author: jeecg-boot
 * @Date:   2022-10-17
 * @Version: V1.0
 */
@Data
@TableName("COMMMON_IMP_FILE_LOG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="COMMMON_IMP_FILE_LOG对象", description="导入文件日志")
public class CommonImpFileLog {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
	private String fileName;
	/**文件大小*/
	@Excel(name = "文件大小", width = 15)
    @ApiModelProperty(value = "文件大小")
	private Integer fileSize;
	/**文件类型*/
	@Excel(name = "文件类型", width = 15)
    @ApiModelProperty(value = "文件类型")
	private String fileType;
	/**文件行数*/
	@Excel(name = "文件行数", width = 15)
    @ApiModelProperty(value = "文件行数")
	private Integer fileNumber;
	/**导入行数*/
	@Excel(name = "导入行数", width = 15)
    @ApiModelProperty(value = "导入行数")
	private Integer impNumber;
	private Integer updateNumber;
	/**导入文件地址*/
	@Excel(name = "导入文件地址", width = 15)
    @ApiModelProperty(value = "导入文件地址")
	private String fileAddr;
	private String failReason;
}
