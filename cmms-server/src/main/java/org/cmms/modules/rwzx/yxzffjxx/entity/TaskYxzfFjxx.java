package org.cmms.modules.rwzx.yxzffjxx.entity;

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
 * @Description: 营销走访附件信息
 * @Author: jeecg-boot
 * @Date:   2023-07-17
 * @Version: V1.0
 */
@Data
@TableName("TASK_YXZF_FJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TASK_YXZF_FJXX对象", description="营销走访附件信息")
public class TaskYxzfFjxx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**rwid*/
	@Excel(name = "zfyxmxid", width = 15)
	@ApiModelProperty(value = "zfyxmxid")
	private String zfyxmxid;
	/**rwid*/
	@Excel(name = "rwid", width = 15)
    @ApiModelProperty(value = "rwid")
	private String rwid;
	/**mxsjid*/
	@Excel(name = "mxsjid", width = 15)
    @ApiModelProperty(value = "mxsjid")
	private String mxsjid;
	/**附件类型(1：营销附件，2.营销录音，3走访附件)*/
	@Excel(name = "附件类型(1：营销附件，2.营销录音，3走访附件)", width = 15)
    @ApiModelProperty(value = "附件类型(1：营销附件，2.营销录音，3走访附件)")
	private String fjlx;
	/**上传人*/
	@Excel(name = "上传人", width = 15)
    @ApiModelProperty(value = "上传人")
	private String scr;
	/**附件大小*/
	@Excel(name = "附件大小", width = 15)
    @ApiModelProperty(value = "附件大小")
	private java.math.BigDecimal fjdx;
	/**附件路径*/
	@Excel(name = "附件路径", width = 15)
    @ApiModelProperty(value = "附件路径")
	private String fjlj;
	/**附件访问路径*/
	@Excel(name = "附件访问路径", width = 15)
    @ApiModelProperty(value = "附件访问路径")
	private String fjfwlj;
	/**上传时间*/
	@Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上传时间")
	private Date scsj;
}
