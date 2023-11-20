package org.cmms.modules.bigscreen.entity;

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
 * @Description: 大屏媒体数据
 * @Author: jeecg-boot
 * @Date:   2023-11-08
 * @Version: V1.0
 */
@Data
@TableName("DP_MEDIUM")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DP_MEDIUM对象", description="大屏媒体数据")
public class DpMedium {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**媒体分类*/
	@Excel(name = "媒体分类", width = 15)
    @ApiModelProperty(value = "媒体分类")
	private String type;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
	private Integer ph;
	/**文件相对路径*/
	@Excel(name = "文件相对路径", width = 15)
    @ApiModelProperty(value = "文件相对路径")
	private String wjxdlj;
	/**文件绝对路径*/
	@Excel(name = "文件绝对路径", width = 15)
    @ApiModelProperty(value = "文件绝对路径")
	private String wjjdlj;
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
	private String fileName;
	/**人员的个人简介名称*/
	@Excel(name = "人员的个人简介名称", width = 15)
    @ApiModelProperty(value = "人员的个人简介名称")
	private String username;
	/**人员的简介*/
	@Excel(name = "人员的简介", width = 15)
    @ApiModelProperty(value = "人员的简介")
	private String jj;
	/**轮播图片采集人*/
	@Excel(name = "轮播图片采集人", width = 15)
    @ApiModelProperty(value = "轮播图片采集人")
	private String cjr;
	/**轮播图片采集时间*/
	@Excel(name = "轮播图片采集时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "轮播图片采集时间")
	private Date cjsj;
	/**是否审批*/
	@Excel(name = "是否审批", width = 15)
    @ApiModelProperty(value = "是否审批")
	private String sfsp;
	/**审批人*/
	@Excel(name = "审批人", width = 15)
    @ApiModelProperty(value = "审批人")
	private String spr;
	/**审批时间*/
	@Excel(name = "审批时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "审批时间")
	private Date spsj;
}
