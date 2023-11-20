package org.cmms.modules.appbase.datatime.entity;

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
 * @Description: 数据入库
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
@Data
@TableName("BAS_DATA_JOB_DAYS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BAS_DATA_JOB_DAYS对象", description="数据入库")
public class BasDataJobDays {
    
	/**数据抽取唯一标识*/
	@Excel(name = "数据抽取唯一标识", width = 15)
    @ApiModelProperty(value = "数据抽取唯一标识")
	private String datajobid;
	/**法人行社标识*/
	@Excel(name = "法人行社标识", width = 15)
    @ApiModelProperty(value = "法人行社标识")
	private String bno;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date extday;
	/**统计状态（0 未开始、1 正在统计、2统计结束、3统计失败）*/
	@Excel(name = "统计状态（0 未开始、1 正在统计、2统计结束、3统计失败）", width = 15)
    @ApiModelProperty(value = "统计状态（0 未开始、1 正在统计、2统计结束、3统计失败）")
	private Integer jobstat;
	/**统计结果*/
	@Excel(name = "统计结果", width = 15)
    @ApiModelProperty(value = "统计结果")
	private String jobret;
	/**统计开始时间*/
	@Excel(name = "统计开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计开始时间")
	private Date bjobday;
	/**统计结束时间*/
	@Excel(name = "统计结束时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计结束时间")
	private Date ejobday;
}
