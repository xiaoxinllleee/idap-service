package org.cmms.modules.rwzx.rwwcqk.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 任务完成情况
 * @Author: jeecg-boot
 * @Date:   2023-07-19
 * @Version: V1.0
 */
@Data
@TableName("v_task_rwwcqk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_task_rwwcqk对象", description="任务完成情况")
public class VtaskRwwcqk {


	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
	@ApiModelProperty(value = "任务名称")
	private String title;

	/**任务类型*/
	@Excel(name = "任务类型", width = 15,dicCode ="marketing_type1" )
	@ApiModelProperty(value = "任务类型")
	@Dict(dicCode = "marketing_type1")
	private String rwlx;


	/**营销类型*/
	@Excel(name = "营销类型", width = 15,dicCode = "marketing_type")
	@ApiModelProperty(value = "营销类型")
	@Dict(dicCode = "marketing_type")
	private String marketingType;


	/**优先级*/
	@Excel(name = "优先级", width = 15,dicCode = "yxj")
	@ApiModelProperty(value = "优先级")
	@Dict(dicCode = "yxj")
	private String yxj;



	/**开始时间*/
	@Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	/**结束时间*/
	@Excel(name = "结束时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "结束时间")
	private Date endTime;

	/**数量*/
	@Excel(name = "数量", width = 15)
	@ApiModelProperty(value = "数量")
	private BigDecimal rs;


	/**对象类型*/
	@Excel(name = "对象类型", width = 15,dicCode = "dxlx")
	@ApiModelProperty(value = "对象类型")
	@Dict(dicCode = "dxlx")
	private String dxlx;

	/**对象ID*/
	@Excel(name = "对象ID", width = 15)
	@ApiModelProperty(value = "对象ID")
	private String dxid;

	/**状态*/
	@Excel(name = "状态", width = 15)
	@ApiModelProperty(value = "状态")
	@Dict(dicCode = "task_create_status")
	private String status;

	/**创建人*/
	private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
	private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	/**任务数*/
	@Excel(name = "任务数", width = 15)
    @ApiModelProperty(value = "任务数")
	private java.lang.Integer rws;
	/**wcs*/
	@Excel(name = "完成数", width = 15)
    @ApiModelProperty(value = "完成数")
	private java.lang.Integer wcs;
}
