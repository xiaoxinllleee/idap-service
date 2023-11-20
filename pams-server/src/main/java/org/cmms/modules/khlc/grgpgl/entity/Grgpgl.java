package org.cmms.modules.khlc.grgpgl.entity;

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
 * @Description: 个人挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
@Data
@TableName("Erp_assess_grgpxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_assess_grgpxx对象", description="个人挂片管理")
public class Grgpgl {

	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	@ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	@ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private String gwbz;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    @ApiModelProperty(value = "员工姓名")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	private String yggh;
	@TableField(exist = false)
	private String ygxm;
	/**部门标识*/
	@Excel(name = "挂片部门名称", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "挂片部门名称")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String bmbz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
