package org.cmms.modules.report.csgl.zhrwszgl.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 支行年度任务设置管理 导入
 * @Author: penghr
 * @Date:   2023-06-05
 * @Version: V1.0
 */
@Data
public class ZhrwszglVO {

	/**任务年度*/
	//@Excel(name = "任务年度", width = 15)
	//@ExcelVerify(notNull = true)
	private String taskYear;
	/**机构编码*/
	private String branchNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
	@ExcelVerify(notNull = true)
	private String branchName;
	/**任务编码*/
	@Excel(name = "任务编码", width = 15)
	@ExcelVerify(notNull = true)
	private String taskCode;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
	@ExcelVerify(notNull = true)
	private String taskName;
	/**任务值*/
	@Excel(name = "任务值", width = 15)
	private String taskValue;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private String remarks;

	/**创建人*/
	private String createBy;
	/**创建时间*/
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	private Date updateTime;
}
