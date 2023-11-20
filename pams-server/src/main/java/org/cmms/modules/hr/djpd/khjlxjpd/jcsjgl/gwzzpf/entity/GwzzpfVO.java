package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.gwzzpf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 岗位资质评分
 * @Author: jeecg-boot
 * @Date:   2021-09-07
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_GWZZPF对象", description="岗位资质评分")
public class GwzzpfVO {
	//机构名称,客户经理标识,客户经理名称,会计证,银行业从业资格证,计算机操作证
	/**组织标识*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;

	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;

	@Excel(name = "客户经理名称", width = 15,dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	@ApiModelProperty(value = "客户经理名称")
	@Dict(dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	private String yggh;
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
	private Integer jsjczz;

}
