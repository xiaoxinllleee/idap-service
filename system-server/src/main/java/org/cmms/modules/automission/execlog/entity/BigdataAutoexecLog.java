package org.cmms.modules.automission.execlog.entity;

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
 * @Description: 大数据应用平台每日调度日志信息
 * @Author: jeecg-boot
 * @Date:   2021-12-31
 * @Version: V1.0
 */
@Data
@TableName("BIGDATA_AUTOEXEC_LOG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BIGDATA_AUTOEXEC_LOG对象", description="大数据应用平台每日调度日志信息")
public class BigdataAutoexecLog {

	/**日志ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "日志ID")
	private String id;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
	private String missionName;
	/**任务类名*/
	@Excel(name = "任务类名", width = 15)
    @ApiModelProperty(value = "任务类名")
	private String missionClassName;
	/**执行时间*/
	@Excel(name = "执行时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "执行时间")
	private Date execTime;
	/**完成时间*/
	@Excel(name = "完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "完成时间")
	private Date completeTime;
	/**任务是否成功 0 未成功 1 成功*/
	@Excel(name = "任务是否成功 0 未成功 1 成功", width = 15)
    @ApiModelProperty(value = "任务是否成功 0 未成功 1 成功")
	private Integer missionSuccess;
	/**失败ETL任务名称*/
	@Excel(name = "失败ETL任务名称", width = 15)
	@ApiModelProperty(value = "失败ETL任务名称")
	private String failedEtlName;
	/**失败原因*/
	@Excel(name = "失败原因", width = 15)
    @ApiModelProperty(value = "失败原因")
	private String reasonForFailure;
	/**日志写入时间*/
	@Excel(name = "日志写入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "日志写入时间")
	private Date createTime;
}
