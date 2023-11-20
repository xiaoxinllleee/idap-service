package org.cmms.modules.yxdygl.pqqxgl.entity;

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
 * @Description: 片区权限管理V
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("v_yxdygl_pqqxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_yxdygl_pqqxgl对象", description="片区权限管理V")
public class VYxdyglPqqxgl {
    
	/**wgmc*/
	@Excel(name = "wgmc", width = 15)
    @ApiModelProperty(value = "wgmc")
	private java.lang.String wgmc;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**sszh*/
	@Excel(name = "sszh", width = 15)
    @ApiModelProperty(value = "sszh")
	private java.lang.String sszh;
	/**sfzkhjl*/
	@Excel(name = "sfzkhjl", width = 15)
    @ApiModelProperty(value = "sfzkhjl")
	@Dict(dicCode = "sfbz")
	private java.lang.String sfzkhjl;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private java.lang.String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private java.lang.String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**menuId*/
	@Excel(name = "menuId", width = 15)
    @ApiModelProperty(value = "menuId")
	private java.lang.String menuId;
	/**khjl*/
	@Excel(name = "khjl", width = 15)
    @ApiModelProperty(value = "khjl")
	private java.lang.String khjl;
	/**sjqx*/
	@Excel(name = "sjqx", width = 15)
    @ApiModelProperty(value = "sjqx")
	@Dict(dicCode = "wgsjqx")
	private java.lang.String sjqx;
	/**ygxm*/
	@Excel(name = "ygxm", width = 15)
    @ApiModelProperty(value = "ygxm")
	private java.lang.String ygxm;
}
