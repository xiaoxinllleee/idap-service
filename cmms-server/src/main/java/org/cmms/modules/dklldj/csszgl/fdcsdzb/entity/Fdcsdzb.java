package org.cmms.modules.dklldj.csszgl.fdcsdzb.entity;

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
 * @Description: 浮动查算对照表
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@Data
@TableName("Rate_fdcsdzb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rate_fdcsdzb对象", description="浮动查算对照表")
public class Fdcsdzb {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**定价得分*/
	@Excel(name = "定价得分", width = 15)
    @ApiModelProperty(value = "定价得分")
	@ExcelVerify(notNull = true)
	private Integer djdf;
	/**贷款期限(1.1年期/2.5年期)*/
	@Excel(name = "贷款期限", width = 15, dicCode = "dkqxly")
    @ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqxly")
	@ExcelVerify(notNull = true)
	private Integer dkqx;
	/**贷款授信+承兑敞口金额（起）*/
	@Excel(name = "贷款授信金额（起）", width = 15)
    @ApiModelProperty(value = "贷款授信金额（起）")
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal dksxjeBegin;
	/**贷款授信+承兑敞口金额（止）*/
	@Excel(name = "贷款授信金额（止）(含)", width = 15)
    @ApiModelProperty(value = "贷款授信金额（止）(含)")
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal dksxjeEnd;
	/**对应浮动幅度*/
	@Excel(name = "对应浮动幅度(%)", width = 15)
    @ApiModelProperty(value = "对应浮动幅度(%)")
	private java.math.BigDecimal dyfdfd;
	/**按LPR加基点（BP）*/
	@Excel(name = "按LPR加基点（BP）", width = 15)
    @ApiModelProperty(value = "按LPR加基点（BP）")
	@ExcelVerify(interHandler = true)
	private java.math.BigDecimal dyjdbp;
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
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
}
