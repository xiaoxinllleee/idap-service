package org.cmms.modules.sgtz.sjtb.entity;

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
 * @Description: ETL手工台账数据同步
 * @Author: Penghr
 * @Date:   2022-10-19
 * @Version: V1.0
 */
@Data
@TableName("ETL_SGTZ_SJTB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ETL_SGTZ_SJTB对象", description="ETL手工台账数据同步")
public class EtlSgtzSjtb {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**etl编码*/
	@Excel(name = "etl编码", width = 15)
    @ApiModelProperty(value = "etl编码")
	private String etlCode;
	/**etl名称*/
	@Excel(name = "etl名称", width = 15)
    @ApiModelProperty(value = "etl名称")
	private String etlName;
	/**结果表*/
	@Excel(name = "结果表", width = 15)
    @ApiModelProperty(value = "结果表")
	private String resTablename;
	/**关联手工台账表*/
	@Excel(name = "关联手工台账表", width = 15)
    @ApiModelProperty(value = "关联手工台账表")
	private String sgtzTablename;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**报表部门*/
	@Excel(name = "报表部门", width = 15)
    @ApiModelProperty(value = "报表部门")
	private String ssbm;
	/**是否要进行提取日期判断提示标识字段：1：是，2：否*/
	@Excel(name = "是否要进行提取日期判断提示标识字段：1：是，2：否", width = 15)
	@ApiModelProperty(value = "是否要进行提取日期判断提示标识字段：1：是，2：否")
	private String tqrqpdtsbs;
}
