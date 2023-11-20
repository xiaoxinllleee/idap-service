package org.cmms.modules.khlc.bmgpgl.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 部门挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
@Data
@TableName("Erp_assess_bmgpxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_assess_bmgpxx对象", description="部门挂片管理")
public class Bmgpgl {
	/**部门标识*/
	@Excel(name = "部门名称", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc",ds = "eweb")
    @ApiModelProperty(value = "部门名称")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc",ds = "eweb")
	@ExcelVerify(notNull = true)
	private String bmbz;
	/**组织标识*/
	@Excel(name = "挂片组织名称", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc",ds = "eweb")
    @ApiModelProperty(value = "挂片组织名称")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc",ds = "eweb")
	@ExcelVerify(notNull = true,interHandler = true)
	private String zzbz;
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
