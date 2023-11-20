package org.cmms.modules.khjg.zbmx.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
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
 * @Description: 指标明细设置
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
@Data
@TableName("erp_wage_zbmx_set")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_wage_zbmx_set对象", description="指标明细设置")
public class ZbmxSet {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**明细类型*/
	@Excel(name = "明细类型", width = 15)
    @ApiModelProperty(value = "明细类型")
	private java.lang.String mxlx;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	private java.lang.String zbid;
	/**计算sql*/
	@Excel(name = "计算sql", width = 15)
    @ApiModelProperty(value = "计算sql")
	private java.lang.String jssql;
	/**展示字段*/
	@Excel(name = "展示字段", width = 15)
    @ApiModelProperty(value = "展示字段")
	private java.lang.String zszd;
	/**跳转路由*/
	@Excel(name = "跳转路由", width = 15)
	@ApiModelProperty(value = "跳转路由")
	private java.lang.String tzly;
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

	@TableField(exist = false)
	private JSONArray zszdxx;

	@TableField(exist = false)
	private JSONArray zbmxjg;

	@TableField(exist = false)
	private Long total;
}
