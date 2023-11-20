package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.entity;

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
 * @Description: 岗位资质评分
 * @Author: jeecg-boot
 * @Date:   2021-09-07
 * @Version: V1.0
 */
@Data
@TableName("GRADE_CUST_GWZZPF")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_GWZZPF对象", description="岗位资质评分")
public class Gwzzpf {
	/**组织标识*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
    @ApiModelProperty(value = "岗位标识")
	/*@Dict(dicCode = "gwbz", dictTable = "Hr_bas_post", dicText = "gwmc")*/
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "客户经理名称", width = 15,dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "客户经理名称")
	@Dict(dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	@ExcelVerify(notNull = true)
	private String yggh;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**会计证*/
	@Excel(name = "会计证", width = 15,dicCode = "ywbz")
    @ApiModelProperty(value = "会计证")
	@Dict(dicCode = "ywbz")
	private Integer kjz;
	/**银行业从业资格证*/
	@Excel(name = "银行业从业资格证", width = 15,dicCode = "ywbz")
    @ApiModelProperty(value = "银行业从业资格证")
	@Dict(dicCode = "ywbz")
	private Integer yhycyzgz;
	/**计算机操作证*/
	@Excel(name = "计算机操作证", width = 15,dicCode = "ywbz")
    @ApiModelProperty(value = "计算机操作证")
	@Dict(dicCode = "ywbz")
	@ExcelVerify(interHandler = true)
	private Integer jsjczz;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}
