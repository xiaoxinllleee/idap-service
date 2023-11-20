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
 * @Description: 系统操作/批处理/ETL处理日志
 * @Author: Penghr
 * @Date:   2022-05-19
 * @Version: V1.0
 */
@Data
@TableName("SYS_EXEINFO_LOG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SYS_EXEINFO_LOG对象", description="系统操作/批处理/ETL处理日志")
public class SysExeinfoLog {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**日志类型(1 登录日志/2 操作日志)*/
	@Excel(name = "日志类型(1 登录日志/2 操作日志)", width = 15)
    @ApiModelProperty(value = "日志类型(1 登录日志/2 操作日志)")
	private Integer logType;
	/**日志内容*/
	@Excel(name = "日志内容", width = 15)
    @ApiModelProperty(value = "日志内容")
	private String logContent;
	/**操作员账号*/
	@Excel(name = "操作员账号", width = 15)
    @ApiModelProperty(value = "操作员账号")
	private String oprUserNo;
	/**操作员姓名*/
	@Excel(name = "操作员姓名", width = 15)
    @ApiModelProperty(value = "操作员姓名")
	private String oprUserName;
	/**操作员IP地址*/
	@Excel(name = "操作员IP地址", width = 15)
    @ApiModelProperty(value = "操作员IP地址")
	private String oprUserIp;
	/**Java方法*/
	@Excel(name = "Java方法", width = 15)
    @ApiModelProperty(value = "Java方法")
	private String method;
	/**执行开始时间*/
	@Excel(name = "执行开始时间", width = 15)
    @ApiModelProperty(value = "执行开始时间")
	private String exeStartTime;
	/**执行结束时间*/
	@Excel(name = "执行结束时间", width = 15)
    @ApiModelProperty(value = "执行结束时间")
	private String exeEndTime;
	/**执行耗时(s)*/
	@Excel(name = "执行耗时(s)", width = 15)
    @ApiModelProperty(value = "执行耗时(s)")
	private String costTime;
	/**执行状态(true 成功/false 失败)*/
	@Excel(name = "执行状态(true 成功/false 失败)", width = 15)
    @ApiModelProperty(value = "执行状态(true 成功/false 失败)")
	private String exeStatus;
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
	/**日志信息*/
	//@Excel(name = "日志信息", width = 15)
	//@ApiModelProperty(value = "日志信息")
	//private String message;
}
