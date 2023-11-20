package org.cmms.modules.wggl.wgzbxx.entity;

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
 * @Description: 网格坐标信息
 * @Author: jeecg-boot
 * @Date:   2021-11-27
 * @Version: V1.0
 */
@Data
@TableName("wghgl_wgzbxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wghgl_wgzbxx对象", description="网格坐标信息")
public class Wgzbxx {
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "唯一标识")
	private String id;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
	@ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**网格名称*/
	@Excel(name = "网格名称", width = 15)
    @ApiModelProperty(value = "网格名称")
	private String wgmc;
	/**网格类型(1:地区 2:机构 3:乡镇 4:片区)*/
	@Excel(name = "网格类型", width = 15, dicCode = "wglx")
    @ApiModelProperty(value = "网格类型")
	@Dict(dicCode = "wglx")
	private String wglx;
	/**网格中心点经度*/
	@Excel(name = "网格中心点经度", width = 15)
    @ApiModelProperty(value = "网格中心点经度")
	private String longitude;
	/**网格中心点纬度*/
	@Excel(name = "网格中心点纬度", width = 15)
    @ApiModelProperty(value = "网格中心点纬度")
	private String latitude;
	/**网格边界*/
    @ApiModelProperty(value = "网格边界")
	private String polygon;
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
