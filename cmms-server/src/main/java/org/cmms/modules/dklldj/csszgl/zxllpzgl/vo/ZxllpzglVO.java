package org.cmms.modules.dklldj.csszgl.zxllpzgl.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 执行利率配置管理 导入VO
 * @Author: Penghr
 * @Date:   2022-11-08
 * @Version: V1.0
 */
@Data
@TableName("rate_lldj_properties")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_lldj_properties对象", description="执行利率配置管理")
public class ZxllpzglVO {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种（其他贷款/抵（质）押贷款）", width = 15, dicCode = "xddkpz_one")
    @ApiModelProperty(value = "信贷贷款品种")
	@Dict(dicCode = "xddkpz_one")
	@ExcelVerify(notNull = true)
	private String xddkpz;
	/**贷款期限*/
	@Excel(name = "贷款期限（一年（含）以内/一至五年（含）/五年以上）", width = 15, dicCode = "dkqx")
    @ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqx")
	@ExcelVerify(notNull = true)
	private String dkqx;
	/**定价得分起*/
	@Excel(name = "定价得分起", width = 15, numFormat = "#0.00")
    @ApiModelProperty(value = "定价得分起")
	@ExcelVerify(notNull = true)
	private String zhpcdfBegin;
	/**定价得分止*/
	@Excel(name = "定价得分止", width = 15, numFormat = "#0.00")
    @ApiModelProperty(value = "定价得分止")
	@ExcelVerify(notNull = true)
	private String zhpcdfEnd;
	/**下浮比例(%)*/
	@Excel(name = "下浮比例(%)", width = 15, numFormat = "#0.0000")
    @ApiModelProperty(value = "下浮比例(%)")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal xfbl;
	/**月执行利率(‰)*/
	@Excel(name = "月执行利率(‰)", width = 15, numFormat = "#0.0000")
    @ApiModelProperty(value = "月执行利率(‰)")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal zxllM;
	/**执行利率(%)*/
	@Excel(name = "执行利率(%)", width = 15, numFormat = "#0.0000")
    @ApiModelProperty(value = "执行利率(%)")
	@ExcelVerify(notNull = true,interHandler = true)
	private java.math.BigDecimal zxllY;
	/**状态标识(0-导入;1-录入;2-修改)*/
    @ApiModelProperty(value = "状态标识(0-导入;1-录入;2-修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**区域代码*/
    @ApiModelProperty(value = "区域代码")
	private String qydm;
}
