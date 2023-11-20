package org.cmms.modules.khjg.gztq.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 基础数据加工日历
 * @Author: jeecg-boot
 * @Date:   2021-10-26
 * @Version: V1.0
 */
@Data
@TableName("V_DP_JDRWGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_DP_JDRWGL对象", description="基础数据加工日历")
public class VDpJdrwgl {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
	@ApiModelProperty(value = "任务名称")
	private String rwmc;
	/**任务存储过程*/
	@Excel(name = "任务存储过程", width = 15)
	@ApiModelProperty(value = "任务存储过程")
	private String rwgc;
	/**节点id*/
	@Excel(name = "节点id", width = 15)
	@ApiModelProperty(value = "节点id")
	@Dict(dicCode = "id", dictTable = "DP_JDGL", dicText = "jdmc")
	private String jdid;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
	@ApiModelProperty(value = "是否启用")
	private String sfqy;
	/**最近一次执行时间*/
	@Excel(name = "最近一次执行时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "最近一次执行时间")
	private Date zjyczxsj;
	/**最近一次执行状态*/
	@Excel(name = "最近一次执行状态", width = 15)
	@ApiModelProperty(value = "最近一次执行状态")
	@Dict(dicCode = "job_status")
	private String zjyczxzt;
	/**最近一次执行耗时*/
	@Excel(name = "最近一次执行耗时", width = 15)
	@ApiModelProperty(value = "最近一次执行耗时")
	private Integer zjyczxhs;
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
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	/**数据日期*/
	@Excel(name = "最大执行成功日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "statDate")
	private java.util.Date statDate;
	/**startTime*/
	@Excel(name = "执行开始时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "startTime")
	private java.util.Date startTime;
	/**endTime*/
	@Excel(name = "执行结束时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "endTime")
	private java.util.Date endTime;
	/**执行耗时*/
	@Excel(name = "执行耗时", width = 15)
    @ApiModelProperty(value = "zxhs")
	private java.lang.Integer zxhs;
	/**状态*/
	@Excel(name = "执行状态", width = 15,dicCode = "job_status")
    @ApiModelProperty(value = "执行状态")
	@Dict(dicCode = "job_status")
	private java.lang.String status;
}
