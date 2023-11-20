package org.cmms.modules.tjfx.tpcfsctj.entity;

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
 * @Description: 行政村图片重复上传统计
 * @Author: jeecg-boot
 * @Date:   2021-09-01
 * @Version: V1.0
 */
@Data
@TableName("TJFX_XZCTPCFSCTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_XZCTPCFSCTJ对象", description="行政村图片重复上传统计")
public class Xzctpcfsctj {
    
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15, dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
    @ApiModelProperty(value = "行政村")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
	private String xzc;
	/**访问路径*/
	@Excel(name = "访问路径", width = 15)
    @ApiModelProperty(value = "访问路径")
	private String fwlj;
	/**图片摘要*/
	@Excel(name = "图片摘要", width = 15)
    @ApiModelProperty(value = "图片摘要")
	private String md5;
	/**重复上传户数*/
	@Excel(name = "重复上传户数", width = 15)
    @ApiModelProperty(value = "重复上传户数")
	private Integer cfschs;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private String lrbz;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
}
