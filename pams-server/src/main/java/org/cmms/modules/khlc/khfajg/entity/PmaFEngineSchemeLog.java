package org.cmms.modules.khlc.khfajg.entity;

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
 * @Description: 引擎加工详细日志表
 * @Author: jeecg-boot
 * @Date:   2021-03-02
 * @Version: V1.0
 */
@Data
@TableName("PMA_F_ENGINE_SCHEME_LOG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_F_ENGINE_SCHEME_LOG对象", description="引擎加工详细日志表")
public class PmaFEngineSchemeLog {
    
	/**考核方案ID*/
	@Excel(name = "考核方案ID", width = 15)
    @ApiModelProperty(value = "考核方案ID")
	private String schemeId;
	/**机构ID*/
	@Excel(name = "机构ID", width = 15)
    @ApiModelProperty(value = "机构ID")
	private String orgId;
	/**数据日期(YYYYMMDD)*/
	@Excel(name = "数据日期(YYYYMMDD)", width = 15)
    @ApiModelProperty(value = "数据日期(YYYYMMDD)")
	private String statDate;
	/**日志类型("T0 - 正常
		T1 - 警告
		T2 - 错误")*/
	@Excel(name = "日志类型", width = 15)
    @ApiModelProperty(value = "日志类型")
	private String type;
	/**详细描述*/
	@Excel(name = "详细描述", width = 15)
    @ApiModelProperty(value = "详细描述")
	private String log;
	/**记录时间*/
	@Excel(name = "记录时间", width = 15)
    @ApiModelProperty(value = "记录时间")
	private String recordTime;
}
