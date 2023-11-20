package org.cmms.modules.yxdygl.cwhfjl.entity;

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
 * @Description: 村委回访附件
 * @Author: jeecg-boot
 * @Date:   2023-06-27
 * @Version: V1.0
 */
@Data
@TableName("YXGL_CWHFJL_FJXXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXGL_CWHFJL_FJXXB对象", description="村委回访附件")
public class CwhfjlFjxx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**回访ID*/
	@Excel(name = "回访ID", width = 15)
    @ApiModelProperty(value = "回访ID")
	private java.lang.String hfId;
	/**附件类型（1：图片/文件，2：视频 3：音频）*/
	@Excel(name = "附件类型（1：图片/文件，2：视频 3：音频）", width = 15)
    @ApiModelProperty(value = "附件类型（1：图片/文件，2：视频 3：音频）")
	private java.lang.String fjlx;
	/**附件名称*/
	@Excel(name = "附件名称", width = 15)
    @ApiModelProperty(value = "附件名称")
	private java.lang.String fjmc;
	/**上传时间*/
	@Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上传时间")
	private java.util.Date scsj;
	/**上传人*/
	@Excel(name = "上传人", width = 15)
    @ApiModelProperty(value = "上传人")
	private java.lang.String scr;
	/**附件大小（单位KB）*/
	@Excel(name = "附件大小（单位KB）", width = 15)
    @ApiModelProperty(value = "附件大小（单位KB）")
	private java.math.BigDecimal fjdx;
	/**附件路径*/
	@Excel(name = "附件路径", width = 15)
    @ApiModelProperty(value = "附件路径")
	private java.lang.String fjlj;
	/**访问路径*/
	@Excel(name = "访问路径", width = 15)
    @ApiModelProperty(value = "访问路径")
	private java.lang.String fwlj;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
}
