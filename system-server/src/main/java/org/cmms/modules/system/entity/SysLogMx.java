package org.cmms.modules.system.entity;

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
 * @Description: 系统日志明细表
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
@Data
@TableName("sys_log_mx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_log_mx对象", description="系统日志明细表")
public class SysLogMx {

	/**创建时间*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "日期")
	private Date rq;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**操作用户账号*/
	@Excel(name = "操作用户账号", width = 15)
    @ApiModelProperty(value = "操作用户账号")
	private String userid;
	/**操作用户名称*/
	@Excel(name = "操作用户名称", width = 15)
    @ApiModelProperty(value = "操作用户名称")
	private String username;
	/**IP*/
	@Excel(name = "IP", width = 15)
    @ApiModelProperty(value = "IP")
	private String ip;
	/**登录次数*/
	@Excel(name = "登录次数", width = 15)
    @ApiModelProperty(value = "登录次数")
	private Long numberOfLogins;
	/**登录时常(分钟)*/
	@Excel(name = "登录时常(分钟)", width = 15)
    @ApiModelProperty(value = "登录时常(分钟)")
	private String loginFrequently;
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
	/**退出时间*/
	@Excel(name = "退出时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "退出时间")
	private Date exitTime;
	/**登录设备(1:PC,2:PAD)*/
	@Excel(name = "登录设备(1:PC,2:PAD)", width = 15)
	@ApiModelProperty(value = "登录设备(1:PC,2:PAD)")
	private String loginDevice;
}
