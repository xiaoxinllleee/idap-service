package org.cmms.modules.gzap.gzrw.entity;

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
 * @Description: 工作安排/工作任务
 * @Author: cmms
 * @Date:   2019-09-27
 * @Version: V1.0
 */
@Data
@TableName("V_GZAP_GZRW")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_GZAP_GZRW对象", description="工作安排/工作任务")
public class GzapGzrw {

    /**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;

	/**任务对象*/
	@Excel(name = "任务对象", width = 15)
    @ApiModelProperty(value = "任务对象")
	private String rwdx;

	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
	private String rwmc;

	/**开始时间*/
	@Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间")
	private Date kssj;

	/**结束时间*/
	@Excel(name = "结束时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间")
	private Date jssj;

	/**重要级别(1.普通/2.重要/3.非常重要)*/
	@Excel(name = "重要级别(1.普通/2.重要/3.非常重要)", width = 15)
    @ApiModelProperty(value = "重要级别(1.普通/2.重要/3.非常重要)")
    @Dict(dicCode = "gzap_zyjb")
	private String zyjb;

	/**任务类型(1.贷款/2.贷款笔数/3.拓展/4.回访/5.定期/6.活期)*/
	@Excel(name = "任务类型(1.贷款/2.贷款笔数/3.拓展/4.回访/5.定期/6.活期)", width = 15)
    @ApiModelProperty(value = "任务类型(1.贷款/2.贷款笔数/3.拓展/4.回访/5.定期/6.活期)")
    @Dict(dicCode = "gzap_rwlx")
	private String rwlx;

	/**任务状态(1.进行中/2.已结束)*/
	@Excel(name = "任务状态(1.进行中/2.已结束)", width = 15)
    @ApiModelProperty(value = "任务状态(1.进行中/2.已结束)")
	private String rwzt;

	/**任务值*/
	@Excel(name = "任务值", width = 15)
    @ApiModelProperty(value = "任务值")
	private Long rwz;

	/**完成值*/
	@Excel(name = "完成值", width = 15)
    @ApiModelProperty(value = "完成值")
	private Long wcz;

	/**是否达标(1.已达标/2.未达标)*/
	@Excel(name = "是否达标(1.已达标/2.未达标)", width = 15)
    @ApiModelProperty(value = "是否达标(1.已达标/2.未达标)")
    @Dict(dicCode = "gzrw_sfdb")
	private String sfdb;

	/**完成时间*/
	@Excel(name = "完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "完成时间")
	private Date wcsj;

	/**完成情况*/
	@Excel(name = "完成情况", width = 15)
    @ApiModelProperty(value = "完成情况")
	private String wcqk;

	/**完成状态(1.已完成/2.未完成)*/
	@Excel(name = "完成状态(1.已完成/2.未完成)", width = 15)
    @ApiModelProperty(value = "完成状态(1.已完成/2.未完成)")
    @Dict(dicCode = "gzap_wczt")
	private String wczt;

}
