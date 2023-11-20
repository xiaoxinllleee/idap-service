package org.cmms.modules.lydp.zbgl.log.entity;

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
 * @Description: 浏阳大屏日期记录
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Data
@TableName("LOG_LYDP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LOG_LYDP对象", description="浏阳大屏日期记录")
public class LydpLog {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**状态(1成功 2失败)*/
	@Excel(name = "状态(1成功 2失败)", width = 15)
    @ApiModelProperty(value = "状态(1成功 2失败)")
	private String zt;
	/**失败信息*/
	@Excel(name = "失败信息", width = 15)
    @ApiModelProperty(value = "失败信息")
	private String msg;

}
