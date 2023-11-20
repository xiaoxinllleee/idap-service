package org.cmms.modules.lydp.zbgl.lydpZbtqrzcx.entity;

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
 * @Description: 浏阳大屏指标提取日志查询
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Data
@TableName("REP_INDEX_EXECUTE_LOG_LYDP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_INDEX_EXECUTE_LOG_LYDP对象", description="浏阳大屏指标提取日志查询")
public class LydpZbtqrzcx {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**指标id*/
	@Excel(name = "指标id", width = 15)
	@ApiModelProperty(value = "指标id")
	private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
	@ApiModelProperty(value = "指标名称")
	private String zbmc;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15,dicCode = "zbwd")
	@ApiModelProperty(value = "指标维度")
	@Dict(dicCode = "zbwd")
	private String zbwd;
	/**指标类型:0全行,1机构*/
	@Excel(name = "指标类型", width = 15,dicCode = "zblx")
	@ApiModelProperty(value = "指标类型:1单值,2多维")
	@Dict(dicCode = "zblx")
	private String zblx;
	/**执行时间*/
	@Excel(name = "执行时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "执行时间")
	private Date etime;
	/**执行状态(0 未执行 1 执行成功 2 执行失败 3 存在人行数据跳过)*/
	@Excel(name = "执行状态", width = 15,dicCode = "zbzt")
	@ApiModelProperty(value = "执行状态(0 未执行 1 执行成功 2 执行失败 3 存在人行数据跳过)")
	@Dict(dicCode = "zbzt")
	private String estat;
	/**计算sql*/
	@Excel(name = "计算sql", width = 15)
	@ApiModelProperty(value = "计算sql")
	private String jssql;
	/**数据项结果*/
	@Excel(name = "数据项结果", width = 15)
	@ApiModelProperty(value = "数据项结果")
	private java.math.BigDecimal sjxjg;
	/**执行耗时*/
	@Excel(name = "执行耗时", width = 15)
	@ApiModelProperty(value = "执行耗时")
	private Integer usetime;
	/**执行日志*/
	@Excel(name = "执行日志", width = 15)
	@ApiModelProperty(value = "执行日志")
	private String einfo;
	/**日志时间*/
	@Excel(name = "日志时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "日志时间")
	private Date itime;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
