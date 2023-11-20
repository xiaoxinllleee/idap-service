package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.entity;

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
 * @Description: 支行存款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Data
@TableName("REP_YWBB_ZHCKJGQKB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_YWBB_ZHCKJGQKB对象", description="支行存款结构情况表")
public class RepYwbbZhckjgqkbImport {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private String tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**存款总余额*/
	@Excel(name = "存款总余额", width = 15)
    @ApiModelProperty(value = "存款总余额")
	private java.math.BigDecimal ckzye;
	/**有余额户数*/
	@Excel(name = "有余额户数", width = 15)
    @ApiModelProperty(value = "有余额户数")
	private String yyehs;
	/**储蓄户数*/
	@Excel(name = "储蓄户数", width = 15)
    @ApiModelProperty(value = "储蓄户数")
	private String cxhs;
	/**储蓄余额*/
	@Excel(name = "储蓄余额", width = 15)
    @ApiModelProperty(value = "储蓄余额")
	private java.math.BigDecimal cxye;
	/**对公户数*/
	@Excel(name = "对公户数", width = 15)
    @ApiModelProperty(value = "对公户数")
	private String dghs;
	/**对公余额*/
	@Excel(name = "对公余额", width = 15)
    @ApiModelProperty(value = "对公余额")
	private java.math.BigDecimal dgye;
	/**30岁以下户数*/
	@Excel(name = "30岁以下户数", width = 15)
    @ApiModelProperty(value = "30岁以下户数")
	private String sssyxhs;
	/**30岁以下余额*/
	@Excel(name = "30岁以下余额", width = 15)
    @ApiModelProperty(value = "30岁以下余额")
	private java.math.BigDecimal sssyxye;
	/**30-50岁户数*/
	@Excel(name = "30-50岁户数", width = 15)
    @ApiModelProperty(value = "30-50岁户数")
	private String sszwsshs;
	/**30-50岁余额*/
	@Excel(name = "30-50岁余额", width = 15)
    @ApiModelProperty(value = "30-50岁余额")
	private java.math.BigDecimal sszwssye;
	/**50-65岁户数*/
	@Excel(name = "50-65岁户数", width = 15)
    @ApiModelProperty(value = "50-65岁户数")
	private String wszlsshs;
	/**50-65岁余额*/
	@Excel(name = "50-65岁余额", width = 15)
    @ApiModelProperty(value = "50-65岁余额")
	private java.math.BigDecimal wszlssye;
	/**65岁以上户数*/
	@Excel(name = "65岁以上户数", width = 15)
    @ApiModelProperty(value = "65岁以上户数")
	private String lswsyshs;
	/**65岁以上余额*/
	@Excel(name = "65岁以上余额", width = 15)
    @ApiModelProperty(value = "65岁以上余额")
	private java.math.BigDecimal lswsysye;
	/**1万元至5万元户数*/
	@Excel(name = "1万元至5万元户数", width = 15)
    @ApiModelProperty(value = "1万元至5万元户数")
	private String ywzwwyhs;
	/**1万元至5万元余额*/
	@Excel(name = "1万元至5万元余额", width = 15)
    @ApiModelProperty(value = "1万元至5万元余额")
	private java.math.BigDecimal ywzwwyye;
	/**5万元至10万元户数*/
	@Excel(name = "5万元至10万元户数", width = 15)
    @ApiModelProperty(value = "5万元至10万元户数")
	private String wwzswyhs;
	/**5万元至10万元余额*/
	@Excel(name = "5万元至10万元余额", width = 15)
    @ApiModelProperty(value = "5万元至10万元余额")
	private java.math.BigDecimal wwzswyye;
	/**10万元至50万元户数*/
	@Excel(name = "10万元至50万元户数", width = 15)
    @ApiModelProperty(value = "10万元至50万元户数")
	private String swzwswyhs;
	/**10万元至50万元余额*/
	@Excel(name = "10万元至50万元余额", width = 15)
    @ApiModelProperty(value = "10万元至50万元余额")
	private java.math.BigDecimal swzwswyye;
	/**50万元以上户数*/
	@Excel(name = "50万元以上户数", width = 15)
    @ApiModelProperty(value = "50万元以上户数")
	private String wswyyshs;
	/**50万元以上余额*/
	@Excel(name = "50万元以上余额", width = 15)
    @ApiModelProperty(value = "50万元以上余额")
	private java.math.BigDecimal wswyysye;
	/**1万元以下户数*/
	@Excel(name = "1万元以下户数", width = 15)
    @ApiModelProperty(value = "1万元以下户数")
	private String ywyyxhs;
	/**1万元以下余额*/
	@Excel(name = "1万元以下余额", width = 15)
    @ApiModelProperty(value = "1万元以下余额")
	private java.math.BigDecimal ywyyxye;
}
