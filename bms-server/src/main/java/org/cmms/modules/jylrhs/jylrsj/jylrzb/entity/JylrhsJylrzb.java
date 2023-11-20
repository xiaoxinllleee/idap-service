package org.cmms.modules.jylrhs.jylrsj.jylrzb.entity;

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
 * @Description: 经营利润核算（经营利润指标）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_jylrzb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_jylrzb对象", description="经营利润核算（经营利润指标）")
public class JylrhsJylrzb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date fiscalDate;
	/**业务机构*/
	@Excel(name = "业务机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**指标ID*/
	@Excel(name = "指标", width = 15, dicCode = "zbid", dictTable = "jylrhs_zbk", dicText = "zbmc", ds = "jylrhs")
    @ApiModelProperty(value = "指标ID")
	@Dict(dicCode = "zbid", dictTable = "jylrhs_zbk", dicText = "zbmc", ds = "jylrhs")
	private String zbid;
	/**指标大类*/
	@Excel(name = "指标大类", width = 15, dicCode = "zbdl")
    @ApiModelProperty(value = "指标大类")
	@Dict(dicCode = "zbdl")
	private String zbdl;
	/**业务数据*/
	@Excel(name = "指标结果", width = 15)
    @ApiModelProperty(value = "业务数据")
	private java.math.BigDecimal ywsj;
	/**模拟利润*/
	//@Excel(name = "业务收支", width = 15)
    @ApiModelProperty(value = "模拟利润")
	private java.math.BigDecimal mnlr;
	/**记账类型*/
	//@Excel(name = "记账类型", width = 15)
    @ApiModelProperty(value = "记账类型")
	private String jzfl;
	/**统计时间*/
	@Excel(name = "统计时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统计时间")
	private Date tjsj;
	/**备注*/
	//@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**操作类型*/
	//@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
	@ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	//@Excel(name = "操作员", width = 15)
	@ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	//@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}
