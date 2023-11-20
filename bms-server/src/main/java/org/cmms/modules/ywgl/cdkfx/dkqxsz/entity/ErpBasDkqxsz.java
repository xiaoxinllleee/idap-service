package org.cmms.modules.ywgl.cdkfx.dkqxsz.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: Erp_bas_dkqxsz
 * @Author: jeecg-boot
 * @Date:   2021-06-08
 * @Version: V1.0
 */
@Data
@TableName("Erp_bas_dkqxsz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_bas_dkqxsz对象", description="Erp_bas_dkqxsz")
public class ErpBasDkqxsz {

	/**业务机构代码*/
//	@Excel(name = "业务机构代码", width = 15)
	@ApiModelProperty(value = "业务机构代码")
	private String ywjgdm;
	/**组织标志*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织标志")
	@ExcelVerify(notNull = true)
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	/**单笔贷款限额*/
	@Excel(name = "单笔贷款限额", width = 15)
    @ApiModelProperty(value = "单笔贷款限额")
	@ExcelVerify(notNull = true)
	private Integer dbdkxe;
	/**单户贷款限额*/
	@Excel(name = "单户贷款限额", width = 15)
    @ApiModelProperty(value = "单户贷款限额")
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal dhdkxe;

	/**lrbz*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**lrsj*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**lrr*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;

	/**有效标识*/
	@Excel(name = "有效标识", width = 15,dicCode = "yxbz")
	@ApiModelProperty(value = "有效标识")
	@Dict(dicCode = "yxbz")
	@ExcelVerify(notNull = true,interHandler = true)
	private Integer yxbz;
}
