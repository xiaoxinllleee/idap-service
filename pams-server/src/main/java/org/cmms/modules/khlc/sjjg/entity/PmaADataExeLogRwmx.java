package org.cmms.modules.khlc.sjjg.entity;

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
 * @Description: 任务数据加工明细日志
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
@Data
@TableName("PMA_A_DATA_EXE_LOG_RWMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_A_DATA_EXE_LOG_RWMX对象", description="任务数据加工明细日志")
public class PmaADataExeLogRwmx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**任务编号*/
	@Excel(name = "任务编号", width = 15,dicCode = "rwbh", dictTable = "PMA_A_DATA_EXE", dicText = "rwmc")
    @ApiModelProperty(value = "任务编号")
	@Dict(dicCode = "rwbh", dictTable = "PMA_A_DATA_EXE", dicText = "rwmc")
	private java.lang.String rwbh;
	/**子任务名称*/
	@Excel(name = "子任务名称", width = 15)
    @ApiModelProperty(value = "子任务名称")
	private java.lang.String zrwmc;
	/**子任务编号*/
	@Excel(name = "子任务编号", width = 15)
    @ApiModelProperty(value = "子任务编号")
	private java.lang.String zrwbh;
	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "zxzt")
    @ApiModelProperty(value = "状态")
	@Dict(dicCode = "zxzt")
	private java.lang.String zt;
	/**执行信息*/
	@Excel(name = "执行信息", width = 15)
    @ApiModelProperty(value = "执行信息")
	private java.lang.String zxxx;
	/**开始日期*/
    @ApiModelProperty(value = "开始日期")
	private java.util.Date kssj;
	/**结束日期*/
    @ApiModelProperty(value = "结束日期")
	private java.util.Date jssj;
	/**耗时*/
	@Excel(name = "耗时", width = 15)
    @ApiModelProperty(value = "耗时")
	private java.lang.Long hs;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;
}
