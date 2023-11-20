package org.cmms.modules.hr.yggl.ygrggl.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.JdbcType;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 员工入岗管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Data
@TableName("HR_BAS_STAFF_POST")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HR_BAS_STAFF_POST对象", description="员工入岗管理")
public class HrBasStaffPost {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private Long id;
	/**组织标识*/
	@Excel(name = "组织简称", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15, dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	@ExcelVerify(notNull = true)
	private String yggh;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15, dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
	@ApiModelProperty(value = "岗位标识")
	@Dict(dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
	@ExcelVerify(notNull = true)
	private Integer gwbz;
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
	@ExcelVerify(notNull = true)
	private Date rgrq;
	/**离岗日期*/
	@Excel(name = "离岗日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "离岗日期")
	@TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DATE)
	private Date lgrq;
	/**是否参与考核（1：是 2：否）*/
	@Excel(name = "是否参与考核", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否参与考核（1：是 2：否）")
	@Dict(dicCode = "sfbz")
	@ExcelVerify(interHandler = true)
	private Integer sfcykh;
	/**是否临退休（1：是 2：否）*/
	@Excel(name = "是否临退休", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否临退休（1：是 2：否）")
	@Dict(dicCode = "sfbz")
	private Integer sfltx;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**删除标志*/
//	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private Integer scbz;
	/**删除时间*/
//	@Excel(name = "删除时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "删除时间")
	private Date scsj;
	/**删除操作员*/
//	@Excel(name = "删除操作员", width = 15)
    @ApiModelProperty(value = "删除操作员")
	private String scczy;
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
	private Date updateTime;
}
