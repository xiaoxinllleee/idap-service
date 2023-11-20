package org.cmms.modules.ygjx.entity;

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
 * @Description: 基本工资发放明细表
 * @Author: jeecg-boot
 * @Date:   2022-12-03
 * @Version: V1.0
 */
@Data
@TableName("Pr_Tb_Ygjbgzmxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Pr_Tb_Ygjbgzmxb对象", description="基本工资发放明细表")
public class PrTbYgjbgzmxb {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**职务*/
	@Excel(name = "职务", width = 15)
    @ApiModelProperty(value = "职务")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private String zw;
	/**岗位工资*/
	@Excel(name = "岗位工资", width = 15)
    @ApiModelProperty(value = "岗位工资")
	private java.math.BigDecimal gwgz;
	/**职称津贴*/
	@Excel(name = "职称津贴", width = 15)
    @ApiModelProperty(value = "职称津贴")
	private java.math.BigDecimal zcjt;
	/**年功津贴*/
	@Excel(name = "年功津贴", width = 15)
    @ApiModelProperty(value = "年功津贴")
	private java.math.BigDecimal ngjt;
	/**卫生费*/
	@Excel(name = "卫生费", width = 15)
    @ApiModelProperty(value = "卫生费")
	private java.math.BigDecimal wsf;
	/**特殊岗位津贴*/
	@Excel(name = "特殊岗位津贴", width = 15)
    @ApiModelProperty(value = "特殊岗位津贴")
	private java.math.BigDecimal tsgwjt;
	/**特殊地区津贴*/
	@Excel(name = "特殊地区津贴", width = 15)
    @ApiModelProperty(value = "特殊地区津贴")
	private java.math.BigDecimal tsdqjt;
	/**领导任职年限津贴*/
	@Excel(name = "领导任职年限津贴", width = 15)
    @ApiModelProperty(value = "领导任职年限津贴")
	private java.math.BigDecimal ldrznxjt;
	/**津贴小计*/
	@Excel(name = "津贴小计", width = 15)
    @ApiModelProperty(value = "津贴小计")
	private java.math.BigDecimal jtxj;
	/**应发总计*/
	@Excel(name = "应发总计", width = 15)
    @ApiModelProperty(value = "应发总计")
	private java.math.BigDecimal yfzj;
	/**公积金*/
	@Excel(name = "公积金", width = 15)
    @ApiModelProperty(value = "公积金")
	private java.math.BigDecimal gjj;
	/**养老金*/
	@Excel(name = "养老金", width = 15)
    @ApiModelProperty(value = "养老金")
	private java.math.BigDecimal ylj;
	/**失业保险*/
	@Excel(name = "失业保险", width = 15)
    @ApiModelProperty(value = "失业保险")
	private java.math.BigDecimal sybx;
	/**医疗保险*/
	@Excel(name = "医疗保险", width = 15)
    @ApiModelProperty(value = "医疗保险")
	private java.math.BigDecimal ylbx;
	/**企业年金*/
	@Excel(name = "企业年金", width = 15)
    @ApiModelProperty(value = "企业年金")
	private java.math.BigDecimal qynj;
	/**扣除小计*/
	@Excel(name = "扣除小计", width = 15)
    @ApiModelProperty(value = "扣除小计")
	private java.math.BigDecimal kcxj;
	/**实发合计*/
	@Excel(name = "实发合计", width = 15)
    @ApiModelProperty(value = "实发合计")
	private java.math.BigDecimal sfhj;
	/**信息状态*/
	@Excel(name = "信息状态", width = 15)
    @ApiModelProperty(value = "信息状态")
	private Integer xxzt;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**组织机构*/
	@Excel(name = "组织机构", width = 15)
    @ApiModelProperty(value = "组织机构")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**空项*/
	@Excel(name = "空项", width = 15)
    @ApiModelProperty(value = "空项")
	private java.math.BigDecimal kx;
	/**公积金扣补*/
	@Excel(name = "公积金扣补", width = 15)
    @ApiModelProperty(value = "公积金扣补")
	private java.math.BigDecimal gjjkb;
	/**养老金扣补*/
	@Excel(name = "养老金扣补", width = 15)
    @ApiModelProperty(value = "养老金扣补")
	private java.math.BigDecimal yljkb;
	/**失业保险扣补*/
	@Excel(name = "失业保险扣补", width = 15)
    @ApiModelProperty(value = "失业保险扣补")
	private java.math.BigDecimal sybxkb;
	/**医疗保险扣补*/
	@Excel(name = "医疗保险扣补", width = 15)
    @ApiModelProperty(value = "医疗保险扣补")
	private java.math.BigDecimal ylbxkb;
	/**企业年金扣补*/
	@Excel(name = "企业年金扣补", width = 15)
    @ApiModelProperty(value = "企业年金扣补")
	private java.math.BigDecimal qynjkb;
	/**津补贴小计*/
	@TableField(exist = false)
	private java.math.BigDecimal jbtxj;
	/**扣除小计*/
	@TableField(exist = false)
	private java.math.BigDecimal kcxj2;
}
