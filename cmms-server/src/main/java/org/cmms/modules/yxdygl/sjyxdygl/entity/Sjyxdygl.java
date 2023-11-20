package org.cmms.modules.yxdygl.sjyxdygl.entity;

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
 * @Description: 三级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-18
 * @Version: V1.0
 */
@Data
@TableName("YXDYGL_SJYXDYGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_grkhgl对象", description="三级营销单元管理")
public class Sjyxdygl {
    
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	private String sszh;
	/**一级营销单元编号*/
	@Excel(name = "一级营销单元编号", width = 15, dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
    @ApiModelProperty(value = "一级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	private String yjyxdybh;
	/**二级营销单元编号*/
	@Excel(name = "二级营销单元编号", width = 15, dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
    @ApiModelProperty(value = "二级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	private String ejyxdybh;
	/**单元名称*/
	@Excel(name = "单元名称", width = 15)
    @ApiModelProperty(value = "单元名称")
	private String dymc;
	/**单元编号*/
	@Excel(name = "单元编号", width = 15)
    @ApiModelProperty(value = "单元编号")
	private String dybh;
	/**农户数*/
//	@Excel(name = "农户数", width = 15)
    @ApiModelProperty(value = "农户数")
	private Integer nhs;
	/**农户建档数*/
//	@Excel(name = "农户建档数", width = 15)
    @ApiModelProperty(value = "农户建档数")
	private Integer nhjds;
	/**农户建档覆盖率*/
//	@Excel(name = "农户建档覆盖率", width = 15)
    @ApiModelProperty(value = "农户建档覆盖率")
	private java.math.BigDecimal nhjdfgl;
	/**商户数*/
//	@Excel(name = "商户数", width = 15)
    @ApiModelProperty(value = "商户数")
	private Integer shs;
	/**商户建档数*/
//	@Excel(name = "商户建档数", width = 15)
    @ApiModelProperty(value = "商户建档数")
	private Integer shjds;
	/**商户建档覆盖率*/
//	@Excel(name = "商户建档覆盖率", width = 15)
    @ApiModelProperty(value = "商户建档覆盖率")
	private java.math.BigDecimal shjdfgl;
	/**城区居民*/
//	@Excel(name = "城区居民", width = 15)
    @ApiModelProperty(value = "城区居民")
	private Integer cqjm;
	/**城区居民建档数*/
//	@Excel(name = "城区居民建档数", width = 15)
    @ApiModelProperty(value = "城区居民建档数")
	private Integer cqjmjds;
	/**城区居民建档覆盖率*/
//	@Excel(name = "城区居民建档覆盖率", width = 15)
    @ApiModelProperty(value = "城区居民建档覆盖率")
	private java.math.BigDecimal cqjmjdfgl;
	/**企业数*/
//	@Excel(name = "企业数", width = 15)
    @ApiModelProperty(value = "企业数")
	private Integer qys;
	/**企业建档数*/
//	@Excel(name = "企业建档数", width = 15)
    @ApiModelProperty(value = "企业建档数")
	private Integer qyjds;
	/**企业建档覆盖率*/
//	@Excel(name = "企业建档覆盖率", width = 15)
    @ApiModelProperty(value = "企业建档覆盖率")
	private java.math.BigDecimal qyjdfgl;
	/**机构数*/
//	@Excel(name = "机构数", width = 15)
    @ApiModelProperty(value = "机构数")
	private Integer jgs;
	/**机构建档数*/
//	@Excel(name = "机构建档数", width = 15)
    @ApiModelProperty(value = "机构建档数")
	private Integer jgjds;
	/**机构建档覆盖率*/
//	@Excel(name = "机构建档覆盖率", width = 15)
    @ApiModelProperty(value = "机构建档覆盖率")
	private java.math.BigDecimal jgjdfgl;
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
}
