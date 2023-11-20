package org.cmms.modules.kmtj.ydcx.entity;

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
 * @Description: 月度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
@Data
@TableName("Modm_kmye_cdk_jg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Modm_kmye_cdk_jg对象", description="月度查询")
public class KmtjYdcx {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	@TableField(exist = false)
	private String showjgdm;
	/**期初存款余额*/
	@Excel(name = "期初存款余额", width = 15)
    @ApiModelProperty(value = "期初存款余额")
	private java.math.BigDecimal qcckye;
	/**期末存款余额*/
	@Excel(name = "期末存款余额", width = 15)
    @ApiModelProperty(value = "期末存款余额")
	private java.math.BigDecimal qmckye;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**期初贷款余额*/
	@Excel(name = "期初贷款余额", width = 15)
    @ApiModelProperty(value = "期初贷款余额")
	private java.math.BigDecimal qcdkye;
	/**期末贷款余额*/
	@Excel(name = "期末贷款余额", width = 15)
    @ApiModelProperty(value = "期末贷款余额")
	private java.math.BigDecimal qmdkye;
	/**贷款日平余额*/
	@Excel(name = "贷款日平余额", width = 15)
    @ApiModelProperty(value = "贷款日平余额")
	private java.math.BigDecimal dkrpye;
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
	/**录入标识(0 导入 1 录入 2 修改)*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
}
