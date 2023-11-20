package org.cmms.modules.yxdygl.yjyxdygl.entity;

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
 * @Description: 一级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Data
@TableName("YXDYGL_YJYXDYGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXDYGL_YJYXDYGL对象", description="一级营销单元管理")
public class Yjyxdygl {
    
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**单元编号*/
	@Excel(name = "单元编号", width = 15)
	@ApiModelProperty(value = "单元编号")
	private String dybh;
	/**单元名称*/
	@Excel(name = "单元名称", width = 15)
    @ApiModelProperty(value = "单元名称")
	private String dymc;
	/**单元性质(1-农区乡镇,2-工业园区,3-城区街道)*/
	@Excel(name = "单元性质(农区乡镇,工业园区,城区街道)", width = 15, dicCode = "yjyxdyxz")
    @ApiModelProperty(value = "单元性质(1-农区乡镇,2-工业园区,3-城区街道)")
	@Dict(dicCode = "yjyxdyxz")
	private String dyxz;
	/**农户数*/
//	@Excel(name = "农户数", width = 15)
    @ApiModelProperty(value = "农户数")
	private Long nhs;
	/**农户建档数*/
//	@Excel(name = "农户建档数", width = 15)
    @ApiModelProperty(value = "农户建档数")
	private Long nhjds;
	/**农户建档覆盖率*/
//	@Excel(name = "农户建档覆盖率", width = 15)
    @ApiModelProperty(value = "农户建档覆盖率")
	private Long nhjdfgl;
	/**商户数*/
//	@Excel(name = "商户数", width = 15)
    @ApiModelProperty(value = "商户数")
	private Long shs;
	/**商户建档数*/
//	@Excel(name = "商户建档数", width = 15)
    @ApiModelProperty(value = "商户建档数")
	private Long shjds;
	/**商户建档覆盖率*/
//	@Excel(name = "商户建档覆盖率", width = 15)
    @ApiModelProperty(value = "商户建档覆盖率")
	private Long shjdfgl;
	/**城区居民*/
//	@Excel(name = "城区居民", width = 15)
    @ApiModelProperty(value = "城区居民")
	private Long cqjm;
	/**城区居民建档数*/
//	@Excel(name = "城区居民建档数", width = 15)
    @ApiModelProperty(value = "城区居民建档数")
	private Long cqjmjds;
	/**城区居民建档覆盖率*/
//	@Excel(name = "城区居民建档覆盖率", width = 15)
    @ApiModelProperty(value = "城区居民建档覆盖率")
	private Long cqjmjdfgl;
	/**企业数*/
//	@Excel(name = "企业数", width = 15)
    @ApiModelProperty(value = "企业数")
	private Long qys;
	/**企业建档数*/
//	@Excel(name = "企业建档数", width = 15)
    @ApiModelProperty(value = "企业建档数")
	private Long qyjds;
	/**企业建档覆盖率*/
//	@Excel(name = "企业建档覆盖率", width = 15)
    @ApiModelProperty(value = "企业建档覆盖率")
	private Long qyjdfgl;
	/**机构数*/
//	@Excel(name = "机构数", width = 15)
    @ApiModelProperty(value = "机构数")
	private Long jgs;
	/**机构建档数*/
//	@Excel(name = "机构建档数", width = 15)
    @ApiModelProperty(value = "机构建档数")
	private Long jgjds;
	/**机构建档覆盖率*/
//	@Excel(name = "机构建档覆盖率", width = 15)
    @ApiModelProperty(value = "机构建档覆盖率")
	private Long jgjdfgl;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建人*/
//	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
//	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
//	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**中心坐标*/
	@Excel(name = "中心坐标", width = 15)
	@ApiModelProperty(value = "中心坐标")
	private String zxzb;
	/**区域坐标*/
	@Excel(name = "区域坐标", width = 15)
	@ApiModelProperty(value = "区域坐标")
	private String qyzb;
}
