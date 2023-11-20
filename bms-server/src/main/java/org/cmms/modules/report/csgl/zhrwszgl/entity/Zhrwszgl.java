package org.cmms.modules.report.csgl.zhrwszgl.entity;

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
 * @Description: 支行年度任务设置管理
 * @Author: jeecg-boot
 * @Date:   2023-06-05
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_csgl_zhrwszgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_csgl_zhrwszgl对象", description="支行年度任务设置管理")
public class Zhrwszgl {

	/**任务年度*/
	//@Excel(name = "任务年度", width = 15)
    @ApiModelProperty(value = "任务年度")
	private String taskYear;
	/**机构编码*/
	//@Excel(name = "机构编码", width = 15)
    @ApiModelProperty(value = "机构编码")
	private String branchNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String branchName;
	/**任务编码*/
	@Excel(name = "任务编码", width = 15)
    @ApiModelProperty(value = "任务编码")
	private String taskCode;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
	private String taskName;
	/**任务值*/
	@Excel(name = "任务值", width = 15)
    @ApiModelProperty(value = "任务值")
	private String taskValue;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remarks;
	/**创建人*/
	//@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	//@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	//@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	//@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
