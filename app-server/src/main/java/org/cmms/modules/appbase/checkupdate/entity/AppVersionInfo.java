package org.cmms.modules.appbase.checkupdate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: APP版本信息
 * @Author: jeecg-boot
 * @Date:   2022-03-28
 * @Version: V1.0
 */
@Data
@TableName("APP_VERSION_INFO")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="APP_VERSION_INFO对象", description="APP版本信息")
public class AppVersionInfo {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private java.lang.String creator;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private java.lang.String updator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
	private java.lang.String version;
	/**版本更新内容*/
	@Excel(name = "版本更新内容", width = 15)
    @ApiModelProperty(value = "版本更新内容")
	private java.lang.String content;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
	private java.lang.String title;
	/**访问路径*/
	@Excel(name = "访问路径", width = 15)
    @ApiModelProperty(value = "访问路径")
	private java.lang.String fwlj;
	/**物理路径*/
	@Excel(name = "物理路径", width = 15)
    @ApiModelProperty(value = "物理路径")
	private java.lang.String wulj;
	private java.lang.String url;
	private java.lang.Integer code;
	private BigDecimal fileSize;
}
