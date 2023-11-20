package org.cmms.modules.hr.yggl.yggwxxytj.entity;

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
 * @Description: 员工岗位信息月统计
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Data
@TableName("HR_BAS_STAFFPOST_M")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HR_BAS_STAFFPOST_M对象", description="员工岗位信息月统计")
public class HrBasStaffpostM {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private Long id;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15, dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
    @ApiModelProperty(value = "岗位标识")
	@Dict(dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15, dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	private String yggh;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
	@ApiModelProperty(value = "柜员号")
	private String gyh;
	/**入岗类型*/
	@Excel(name = "入岗类型", width = 15, dicCode = "rglx")
    @ApiModelProperty(value = "入岗类型")
	@Dict(dicCode = "rglx")
	private Integer rglx;
	/**入岗日期*/
	@Excel(name = "入岗日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入岗日期")
	private Date rgrq;
	/**离岗日期*/
	@Excel(name = "离岗日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "离岗日期")
	private Date lgrq;
	/**是否参与考核（1：是 2：否）*/
	@Excel(name = "是否参与考核", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否参与考核（1：是 2：否）")
	@Dict(dicCode = "sfbz")
	private Integer sfcykh;
	/**是否临退休（1：是 2：否）*/
	@Excel(name = "是否临退休", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否临退休（1：是 2：否）")
	@Dict(dicCode = "sfbz")
	private Integer sfltx;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
