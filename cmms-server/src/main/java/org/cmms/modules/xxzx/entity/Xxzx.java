package org.cmms.modules.xxzx.entity;

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
 * @Description: 消息中心
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Data
@TableName("XXZX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XXZX对象", description="消息中心")
public class Xxzx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**消息标题*/
	@Excel(name = "消息标题", width = 15)
    @ApiModelProperty(value = "消息标题")
	private String title;
	/**消息内容*/
	@Excel(name = "消息内容", width = 15)
    @ApiModelProperty(value = "消息内容")
	private String content;
	/**跳转路由*/
	@Excel(name = "跳转路由", width = 15)
    @ApiModelProperty(value = "跳转路由")
	private String path;
	/**查询参数*/
	@Excel(name = "查询参数", width = 15)
    @ApiModelProperty(value = "查询参数")
	private String params;
	/**消息分类 1 消息提醒 2 快速开始/便捷导航 */
	@Excel(name = "消息分类 1 消息提醒 2 快速开始/便捷导航 ", width = 15)
    @ApiModelProperty(value = "消息分类 1 消息提醒 2 快速开始/便捷导航 ")
	private String xxzl;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	private String khjl;
	private String sjtjsj;
}
