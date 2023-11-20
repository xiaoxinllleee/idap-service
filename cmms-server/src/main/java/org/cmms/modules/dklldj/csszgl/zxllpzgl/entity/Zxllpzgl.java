package org.cmms.modules.dklldj.csszgl.zxllpzgl.entity;

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
 * @Description: 执行利率配置管理
 * @Author: Penghr
 * @Date:   2022-11-08
 * @Version: V1.0
 */
@Data
@TableName("rate_lldj_properties")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_lldj_properties对象", description="执行利率配置管理")
public class Zxllpzgl {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15, dicCode = "xddkpz_one")
    @ApiModelProperty(value = "信贷贷款品种")
	@Dict(dicCode = "xddkpz_one")
	private String xddkpz;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15, dicCode = "dkqx")
    @ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqx")
	private String dkqx;
	/**定价得分_起*/
	@Excel(name = "定价得分_起", width = 15)
    @ApiModelProperty(value = "定价得分_起")
	private String zhpcdfBegin;
	/**定价得分_止*/
	@Excel(name = "定价得分_止", width = 15)
    @ApiModelProperty(value = "定价得分_止")
	private String zhpcdfEnd;
	/**下浮比例(%)*/
	@Excel(name = "下浮比例(%)", width = 15, numFormat = "#0.0000")
    @ApiModelProperty(value = "下浮比例(%)")
	private java.math.BigDecimal xfbl;
	/**月执行利率(‰)*/
	@Excel(name = "月执行利率(‰)", width = 15, numFormat = "#0.0000")
    @ApiModelProperty(value = "月执行利率(‰)")
	private java.math.BigDecimal zxllM;
	/**执行利率(%)*/
	@Excel(name = "执行利率(%)", width = 15, numFormat = "#0.0000")
    @ApiModelProperty(value = "执行利率(%)")
	private java.math.BigDecimal zxllY;
	/**状态标识(0-导入;1-录入;2-修改)*/
	@Excel(name = "状态标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "状态标识(0-导入;1-录入;2-修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**区域代码*/
	//@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
}
