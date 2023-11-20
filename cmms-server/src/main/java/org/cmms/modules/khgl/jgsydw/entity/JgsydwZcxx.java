package org.cmms.modules.khgl.jgsydw.entity;

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
 * @Description: 机关事业单位资产信息
 * @Author: jeecg-boot
 * @Date:   2020-02-17
 * @Version: V1.0
 */
@Data
@TableName("KHGL_JGSYDWZCXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_JGSYDWZCXX对象", description="机关事业单位资产信息")
public class JgsydwZcxx {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**档案编号*/
	@Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
	private String dabh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**房产编号*/
	@Excel(name = "房产编号", width = 15)
    @ApiModelProperty(value = "房产编号")
	private String fcbh;
	/**房产性质*/
	@Excel(name = "房产性质", width = 15)
    @ApiModelProperty(value = "房产性质")
    @Dict(dicCode = "khgl_fcxz")
	private String fcxz;
	/**房产位置*/
	@Excel(name = "房产位置", width = 15)
    @ApiModelProperty(value = "房产位置")
	private String fcwz;
	/**房产数量*/
	@Excel(name = "房产数量", width = 15)
    @ApiModelProperty(value = "房产数量")
	private Integer fcsl;
	/**房产总面积*/
	@Excel(name = "房产总面积", width = 15)
    @ApiModelProperty(value = "房产总面积")
	private String fczmj;
	/**房产总价值(万元)*/
	@Excel(name = "房产总价值(万元)", width = 15)
    @ApiModelProperty(value = "房产总价值(万元)")
	private java.math.BigDecimal fczjz;
	/**车辆数量*/
	@Excel(name = "车辆数量", width = 15)
    @ApiModelProperty(value = "车辆数量")
	private Integer clsl;
	/**车辆品牌*/
	@Excel(name = "车辆品牌", width = 15)
    @ApiModelProperty(value = "车辆品牌")
	private String clpp;
	/**车牌号码*/
	@Excel(name = "车牌号码", width = 15)
    @ApiModelProperty(value = "车牌号码")
	private String cphm;
	/**车辆总价值(万元)*/
	@Excel(name = "车辆总价值(万元)", width = 15)
    @ApiModelProperty(value = "车辆总价值(万元)")
	private java.math.BigDecimal clzjz;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String bz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String createBy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
