package org.cmms.modules.system.sjbd.entity;

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
 * @Description: 数据下发结果比对
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
@Data
@TableName("SJXF_JGBD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SJXF_JGBD对象", description="数据下发结果比对")
public class SjxfJgbd {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**表名*/
	@Excel(name = "表名", width = 15)
    @ApiModelProperty(value = "表名")
	private java.lang.String tablename;
	/**impala记录数*/
	@Excel(name = "impala记录数", width = 15)
    @ApiModelProperty(value = "impala记录数")
	private java.lang.Long impalaCount;
	/**oracle记录数*/
	@Excel(name = "oracle记录数", width = 15)
    @ApiModelProperty(value = "oracle记录数")
	private java.lang.Long oracleCount;
	/**数量是否一致*/
	@Excel(name = "数量是否一致", width = 15)
    @ApiModelProperty(value = "数量是否一致")
	private java.lang.String slsfyz;
	/**impala金额和*/
	@Excel(name = "impala金额和", width = 15)
    @ApiModelProperty(value = "impala金额和")
	private java.math.BigDecimal impalaSum;
	/**oracle金额和*/
	@Excel(name = "oracle金额和", width = 15)
    @ApiModelProperty(value = "oracle金额和")
	private java.math.BigDecimal oracleSum;
	/**金额是否一致*/
	@Excel(name = "金额是否一致", width = 15)
    @ApiModelProperty(value = "金额是否一致")
	private java.lang.String jesfyz;

	@Excel(name = "info信息", width = 15)
    @ApiModelProperty(value = "info信息")
	private java.lang.String info;

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
