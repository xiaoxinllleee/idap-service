package org.cmms.modules.yxdygl.yxdyglmain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
 * @Description: 网格基本信息
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Data
@TableName("YXDYGL_MAIN")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXDYGL_MAIN对象", description="网格基本信息")
public class YxdyglMain {
    
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**网格名称*/
	@Excel(name = "网格名称", width = 15)
    @ApiModelProperty(value = "网格名称")
	private String wgmc;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**单元性质(1-镇,2-村,3-组,4-城区街道，5社区，6商圈)*/
	@Excel(name = "网格性质", width = 15, dicCode = "wgxz")
    @ApiModelProperty(value = "网格性质")
	@Dict(dicCode = "wgxz")
	private String wgxz;
	/**父id*/
	@Excel(name = "上级网格", width = 15, dicCode = "id",dictTable = "YXDYGL_MAIN",dicText = "wgmc")
	@ApiModelProperty(value = "父id")
	@Dict(dicCode = "id",dictTable = "YXDYGL_MAIN",dicText = "wgmc")
	private String parentId;
	@Excel(name = "所属支行", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**网格面积*/
	@Excel(name = "网格面积", width = 15)
    @ApiModelProperty(value = "网格面积")
	private Integer wgmj;
	/**网格情况*/
	@Excel(name = "网格情况", width = 15)
    @ApiModelProperty(value = "网格情况")
	private String wgqk;
	/**网格痛点分析*/
	@Excel(name = "网格痛点分析", width = 15)
    @ApiModelProperty(value = "网格痛点分析")
	private String wgtdfx;
	/**网格位置*/
	@Excel(name = "网格位置", width = 15)
    @ApiModelProperty(value = "网格位置")
	private String wgwz;
	/**网格关键人1*/
	@Excel(name = "网格关键人1", width = 15)
    @ApiModelProperty(value = "网格关键人1")
	private String wggjr1;
	/**网格关键人1联系方式*/
	@Excel(name = "网格关键人1联系方式", width = 15)
    @ApiModelProperty(value = "网格关键人1联系方式")
	private String gjrlxfs1;
	/**网格关键人2*/
	@Excel(name = "网格关键人2", width = 15)
    @ApiModelProperty(value = "网格关键人2")
	private String wggjr2;
	/**网格关键人2联系方式*/
	@Excel(name = "网格关键人2联系方式", width = 15)
    @ApiModelProperty(value = "网格关键人2联系方式")
	private String gjrlxfs2;
	/**网格关键人3*/
	@Excel(name = "网格关键人3", width = 15)
    @ApiModelProperty(value = "网格关键人3")
	private String wggjr3;
	/**网格关键人3联系方式*/
	@Excel(name = "网格关键人3联系方式", width = 15)
    @ApiModelProperty(value = "网格关键人3联系方式")
	private String gjrlxfs3;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;

	/**备注*/
//	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz1;
	/**经度*/
//	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
	private String longitude;
	/**维度*/
//	@Excel(name = "维度", width = 15)
	@ApiModelProperty(value = "维度")
	private String latitude;
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
	private String zrbs;
	private String zrld;
	@TableField(exist=false)
	private List<YxdyglMain> children;
	@TableField(exist = false)
	private String level;
	/**权限网格编号*/
	@TableField(exist = false)
	private String qxwgbh;
	@TableField(exist = false)
	private String wgbhShow;
	@TableField(exist = false)
	private Boolean disabled;
	@TableField(exist = false)
	private Boolean isLeaf;
	private BigDecimal top;
	private BigDecimal left;
}
