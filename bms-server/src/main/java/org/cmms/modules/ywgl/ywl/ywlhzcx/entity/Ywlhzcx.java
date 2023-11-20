package org.cmms.modules.ywgl.ywl.ywlhzcx.entity;

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
 * @Description: 业务量汇总查询
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_YWLMX_YG_HZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_YWLMX_YG_HZ对象", description="业务量汇总查询")
public class Ywlhzcx {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode = "zzbz", dictTable = "Hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "Hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String yggh;
	/**交易笔数*/
	@Excel(name = "交易笔数", width = 15)
    @ApiModelProperty(value = "交易笔数")
	private java.math.BigDecimal jybs;
	/**折算后交易笔数*/
	@Excel(name = "折算后交易笔数", width = 15)
    @ApiModelProperty(value = "折算后交易笔数")
	private java.math.BigDecimal zshjybs;
	/**lrr*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**lrsj*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**lrbz*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
}
