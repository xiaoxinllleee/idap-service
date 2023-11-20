package org.cmms.modules.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 定时任务执行日志
 * @Author: liuwei
 * @Date:  2022-06-06
 * @Version: V1.0
 */
@Data
@TableName("sys_quartz_log")
public class QuartzLog implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**Job唯一标识*/
	@Excel(name="Job唯一标识",width=40)
	private String jobId;
	/**任务组名*/
	@Excel(name="任务组名",width=40)
	private String jobGroup;
	/**任务类名*/
	@Excel(name="任务类名",width=40)
	private String jobClassName;
	/**状态 0正常 1失败*/
	@Excel(name="状态",width=15)
	@Dict(dicCode = "job_status")
	private String status;
	/**任务执行信息*/
	@Excel(name="任务执行信息",width=30)
	private String info;
	/**开始时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date startTime;
	/**结束时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date endTime;
	/**下次执行时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date nextFireTime;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;

}
