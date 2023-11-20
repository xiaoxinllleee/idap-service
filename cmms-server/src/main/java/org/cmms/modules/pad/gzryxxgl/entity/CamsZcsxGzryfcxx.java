package org.cmms.modules.pad.gzryxxgl.entity;

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
 * @Description: 公职人员房产信息表
 * @Author: jeecg-boot
 * @Date:   2022-09-05
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GZRYFCXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GZRYFCXX对象", description="公职人员房产信息表")
public class CamsZcsxGzryfcxx {
    
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**公职人员ID*/
	@Excel(name = "公职人员ID", width = 15)
    @ApiModelProperty(value = "公职人员ID")
	private String gzryid;
	/**公职人员姓名*/
	@Excel(name = "公职人员姓名", width = 15)
    @ApiModelProperty(value = "公职人员姓名")
	private String khmc;
	/**房产位置*/
	@Excel(name = "房产位置", width = 15)
    @ApiModelProperty(value = "房产位置")
	private String fcwz;
	/**房产数量*/
	@Excel(name = "房产数量", width = 15)
    @ApiModelProperty(value = "房产数量")
	private Integer fcsl;
	/**房产面积*/
	@Excel(name = "房产面积", width = 15)
    @ApiModelProperty(value = "房产面积")
	private String fcmj;
	/**房产单价*/
	@Excel(name = "房产单价", width = 15)
    @ApiModelProperty(value = "房产单价")
	private java.math.BigDecimal fcdj;
	/**房产价值*/
	@Excel(name = "房产价值", width = 15)
    @ApiModelProperty(value = "房产价值")
	private java.math.BigDecimal fcjz;
	/**房产性质*/
	@Excel(name = "房产性质", width = 15)
    @ApiModelProperty(value = "房产性质")
	private String fcxz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
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
	private Date updataTime;
}
