package org.cmms.modules.khjg.zhfyfp.entity;

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
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
@Data
@TableName("BASIC_WAGE_ZHFYFP_HZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BASIC_WAGE_ZHFYFP_HZ对象", description="支行费用分配")
public class ZhfyfpHz {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**分配月份*/
	@Excel(name = "分配月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "分配月份")
	@ExcelVerify(notNull = true)
	private java.util.Date fpyf;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private java.lang.String zzbz;
	/**费用类型*/
	@Excel(name = "费用类型", width = 15,dicCode = "fylx")
	@ExcelVerify(notNull = true)
    @ApiModelProperty(value = "费用类型")
	@Dict(dicCode = "fylx")
	private java.lang.Integer fylx;
	/**总金额*/
	@Excel(name = "总金额", width = 15)
    @ApiModelProperty(value = "总金额")
	@ExcelVerify(interHandler = true)
	private java.math.BigDecimal zje;
	/**已分配金额*/
	@Excel(name = "已分配金额", width = 15)
    @ApiModelProperty(value = "已分配金额")
	private java.math.BigDecimal yfpje;
	/**未分配金额*/
	@Excel(name = "未分配金额", width = 15)
    @ApiModelProperty(value = "未分配金额")
	private java.math.BigDecimal dfpje;
	/**分配状态（0 未分配 1 已分配）*/
	@Excel(name = "分配状态", width = 15,dicCode = "fpzt")
    @ApiModelProperty(value = "分配状态")
	@Dict(dicCode = "fpzt")
	private java.lang.Integer fpzt;
	/**提交状态（0 未提交 1 已提交）*/
	@Excel(name = "提交状态", width = 15,dicCode = "tjzt")
    @ApiModelProperty(value = "提交状态")
	@Dict(dicCode = "tjzt")
	private java.lang.Integer tjzt;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;
}
