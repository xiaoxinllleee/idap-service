package org.cmms.modules.jylrhs.csgl.csgl.entity;

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
 * @Description: 经营利润核算（参数管理）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_csgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_csgl对象", description="经营利润核算（参数管理）")
public class JylrhsCsgl {

	/**参数编号*/
	@Excel(name = "参数编号", width = 15)
    @ApiModelProperty(value = "参数编号")
	private String paramNo;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
	private String paramName;
	/**数值1*/
	@Excel(name = "数值1", width = 15)
    @ApiModelProperty(value = "数值1")
	private String paramValue1;
	/**数值2*/
	@Excel(name = "数值2", width = 15)
    @ApiModelProperty(value = "数值2")
	private String paramValue2;
	/**文本参数*/
	@Excel(name = "文本参数", width = 15)
    @ApiModelProperty(value = "文本参数")
	private String textParam;
	/**操作类型*/
	@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}
