package org.cmms.modules.gzap.gzjh.entity;

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
 * @Description: 工作安排/工作计划
 * @Author: cmms
 * @Date:   2019-09-27
 * @Version: V1.0
 */
@Data
@TableName("V_GZAP_GZJH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_GZAP_GZJH对象", description="工作安排/工作计划")
public class VGzapGzjh {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;

	/**计划对象*/
	@Excel(name = "计划对象", width = 15)
    @ApiModelProperty(value = "计划对象")
	private String jhdx;

	/**计划概述*/
	@Excel(name = "计划概述", width = 15)
    @ApiModelProperty(value = "计划概述")
	private String jhgs;

	/**计划开始时间*/
	@Excel(name = "计划开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "计划开始时间")
	private Date jhkssj;

	/**计划结束时间*/
	@Excel(name = "计划结束时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "计划结束时间")
	private Date jhjssj;

	/**计划类型(1.周计划/2.月计划)*/
	@Excel(name = "计划类型(1.周计划/2.月计划)", width = 15)
    @ApiModelProperty(value = "计划类型(1.周计划/2.月计划)")
    @Dict(dicCode = "gzap_gzlx")
	private String jhlx;

	/**计划内容*/
	@Excel(name = "计划内容", width = 15)
    @ApiModelProperty(value = "计划内容")
	private String jhnr;

	/**实际完成日期*/
	@Excel(name = "实际完成日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "实际完成日期")
	private Date sjwcrq;

	/**实际完成情况*/
	@Excel(name = "实际完成情况", width = 15)
    @ApiModelProperty(value = "实际完成情况")
	private String sjwcqk;

	/**完成状态(1.已完成/2.未完成)*/
	@Excel(name = "完成状态(1.已完成/2.未完成)", width = 15)
    @ApiModelProperty(value = "完成状态(1.已完成/2.未完成)")
    @Dict(dicCode = "gzap_wczt")
	private String wczt;

}
