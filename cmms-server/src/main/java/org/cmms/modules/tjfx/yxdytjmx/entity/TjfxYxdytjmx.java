package org.cmms.modules.tjfx.yxdytjmx.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-05-30
 * @Version: V1.0
 */
@Data
@TableName("TJFX_YXDYTJMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_YXDYTJMX对象", description="1")
public class TjfxYxdytjmx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
	@Dict( dicCode="wgbh", dictTable="YXDYGL_MAIN", dicText="wgmc")
	private String ssyxdy;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	@Dict(dicCode = "sfbz")
	private String sfhz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "yxkhlx")
	private String khlx;
	/**客户级别*/
	@Excel(name = "客户级别", width = 15)
	@ApiModelProperty(value = "客户级别")
	@Dict(dicCode = "yxkhdj")
	private String khjb;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hyzk")
	private String hyzk;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
    @ApiModelProperty(value = "原所属乡镇")
	private String yssxz;
	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
    @ApiModelProperty(value = "原行政村")
	private String yxzc;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**系统评定结果（1：灰名单 2：白名单 3：黑名单）*/
	@Excel(name = "系统评定结果（1：灰名单 2：白名单 3：黑名单）", width = 15)
    @ApiModelProperty(value = "系统评定结果（1：灰名单 2：白名单 3：黑名单）")
	private Integer xtpdjg;
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
    @ApiModelProperty(value = "系统评定说明")
	private String xtpdsm;
	/**开通业务*/
	@Excel(name = "开通业务", width = 15)
    @ApiModelProperty(value = "开通业务")
	@Dict(dicCode = "yxktyw")
	private String ktyw;
}