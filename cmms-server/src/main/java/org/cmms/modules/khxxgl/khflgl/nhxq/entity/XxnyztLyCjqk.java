package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 采集情况
 * @Author: jeecg-boot
 * @Date:   2023-07-03
 * @Version: V1.0
 */
@Data
@TableName("XXNYZT_LY_CJQK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XXNYZT_LY_CJQK对象", description="采集情况")
public class XxnyztLyCjqk {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**栽种品种 或者 设备名称 或者  其他资产名称*/
	@Excel(name = "栽种品种 或者 设备名称 或者  其他资产名称", width = 15)
    @ApiModelProperty(value = "栽种品种 或者 设备名称 或者  其他资产名称")
	private String zzpz;
	/**栽种面积 或者 台数 或者 数量*/
	@Excel(name = "栽种面积 或者 台数 或者 数量", width = 15)
    @ApiModelProperty(value = "栽种面积 或者 台数 或者 数量")
	private BigDecimal zzmj;
	/**养殖品种*/
	@Excel(name = "养殖品种", width = 15)
    @ApiModelProperty(value = "养殖品种")
	private String yzpz;
	/**养殖数量（头）*/
	@Excel(name = "养殖数量（头）", width = 15)
    @ApiModelProperty(value = "养殖数量（头）")
	private BigDecimal yzsl;
	/**加工产品品种*/
	@Excel(name = "加工产品品种", width = 15)
    @ApiModelProperty(value = "加工产品品种")
	private String jgcppz;
	/**加工产值（万元） 或者价值（万元）*/
	@Excel(name = "加工产值（万元） 或者价值（万元）", width = 15)
    @ApiModelProperty(value = "加工产值（万元） 或者价值（万元）")
	private BigDecimal jgcz;
	/**经营类别  1栽种信息 2生产设备 3其他资产情况*/
	@Excel(name = "经营类别  1栽种信息 2生产设备 3其他资产情况", width = 15)
    @ApiModelProperty(value = "经营类别  1栽种信息 2生产设备 3其他资产情况")
	private String jylb;
	/**主体id*/
	@Excel(name = "主体id", width = 15)
    @ApiModelProperty(value = "主体id")
	private String ztid;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
}
