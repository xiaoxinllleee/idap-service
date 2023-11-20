package org.cmms.modules.ywgl.cdkfx.khjldkzhtj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 客户经理贷款综合统计
 * @Author: jeecg-boot
 * @Date:   2021-06-28
 * @Version: V1.0
 */
@Data
@TableName("v_MOD_DKFX_JGKHJLQXNWDKQKTJ_M")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_MOD_DKFX_JGKHJLQXNWDKQKTJ_M对象", description="客户经理贷款综合统计")
public class ModDkfxJgkhlqxnwdkqktjMVo {

	@Excel(name = "支行代码",width = 15)
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private String sjywjgdm;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**权限外贷款笔数*/
	@Excel(name = "权限外贷款笔数", width = 15)
    @ApiModelProperty(value = "权限外贷款笔数")
	private java.math.BigDecimal dbqxwdkbs;
	/**dbqxwdkhs*/
    @ApiModelProperty(value = "dbqxwdkhs")
	private java.math.BigDecimal dbqxwdkhs;
	/**dhdkje*/
    @ApiModelProperty(value = "dhdkje")
	private java.math.BigDecimal dhdkje;
	/**dhdkye*/
    @ApiModelProperty(value = "dhdkye")
	private java.math.BigDecimal dhdkye;
	/**dhdkbs*/
    @ApiModelProperty(value = "dhdkbs")
	private java.math.BigDecimal dhdkbs;
	/**贷款户数*/
	@Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
	private java.math.BigDecimal dhdkhs;
	/**dhqxndkje*/
    @ApiModelProperty(value = "dhqxndkje")
	private java.math.BigDecimal dhqxndkje;
	/**dhqxndkye*/
    @ApiModelProperty(value = "dhqxndkye")
	private java.math.BigDecimal dhqxndkye;
	/**dhqxndkbs*/
    @ApiModelProperty(value = "dhqxndkbs")
	private java.math.BigDecimal dhqxndkbs;
	/**权限内贷款户数*/
	@Excel(name = "权限内贷款户数", width = 15)
    @ApiModelProperty(value = "权限内贷款户数")
	private java.math.BigDecimal dhqxndkhs;
	/**dhqxwdkje*/
    @ApiModelProperty(value = "dhqxwdkje")
	private java.math.BigDecimal dhqxwdkje;
	/**dhqxwdkye*/
    @ApiModelProperty(value = "dhqxwdkye")
	private java.math.BigDecimal dhqxwdkye;
	/**dhqxwdkbs*/
    @ApiModelProperty(value = "dhqxwdkbs")
	private java.math.BigDecimal dhqxwdkbs;
	/**权限外贷款户数*/
	@Excel(name = "权限外贷款户数", width = 15)
    @ApiModelProperty(value = "权限外贷款户数")
	private java.math.BigDecimal dhqxwdkhs;
	/**发放贷款金额(元)*/
	@Excel(name = "发放贷款金额(元)", width = 15)
    @ApiModelProperty(value = "发放贷款金额(元)")
	private java.math.BigDecimal ffdkje;
	/**发放贷款笔数*/
	@Excel(name = "发放贷款笔数", width = 15)
    @ApiModelProperty(value = "发放贷款笔数")
	private java.math.BigDecimal ffdkbs;
	/**发放权限内贷款金额(元)*/
	@Excel(name = "发放权限内贷款金额(元)", width = 15)
    @ApiModelProperty(value = "发放权限内贷款金额(元)")
	private java.math.BigDecimal ffqxndkje;
	/**发放权限内贷款笔数*/
	@Excel(name = "发放权限内贷款笔数", width = 15)
    @ApiModelProperty(value = "发放权限内贷款笔数")
	private java.math.BigDecimal ffqxndkbs;
	/**发放权限外贷款金额(元)*/
	@Excel(name = "发放权限外贷款金额(元)", width = 15)
    @ApiModelProperty(value = "发放权限外贷款金额(元)")
	private java.math.BigDecimal ffqxwdkje;
	/**发放权限外贷款笔数*/
	@Excel(name = "发放权限外贷款笔数", width = 15)
    @ApiModelProperty(value = "发放权限外贷款笔数")
	private java.math.BigDecimal ffqxwdkbs;
	/**收回利息(元)*/
	@Excel(name = "收回利息(元)", width = 15)
    @ApiModelProperty(value = "收回利息(元)")
	private java.math.BigDecimal shlx;
	/**收回本金(元)*/
	@Excel(name = "收回本金(元)", width = 15)
    @ApiModelProperty(value = "收回本金(元)")
	private java.math.BigDecimal shbj;
	/**lrsj*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**lrczy*/
    @ApiModelProperty(value = "lrczy")
	private String lrczy;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	@Dict(dicCode = "khjlbh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String custid;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date beginday;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date endday;
	/**贷款金额(元)*/
	@Excel(name = "贷款金额(元)", width = 15)
    @ApiModelProperty(value = "贷款金额(元)")
	private java.math.BigDecimal dbdkje;
	/**贷款余额(元)*/
	@Excel(name = "贷款余额(元)", width = 15)
    @ApiModelProperty(value = "贷款余额(元)")
	private java.math.BigDecimal dbdkye;
	/**贷款笔数*/
	@Excel(name = "贷款笔数", width = 15)
    @ApiModelProperty(value = "贷款笔数")
	private java.math.BigDecimal dbdkbs;
	/**dbdkhs*/
    @ApiModelProperty(value = "dbdkhs")
	private java.math.BigDecimal dbdkhs;
	/**dbqxndkje*/
    @ApiModelProperty(value = "dbqxndkje")
	private java.math.BigDecimal dbqxndkje;
	/**dbqxndkye*/
    @ApiModelProperty(value = "dbqxndkye")
	private java.math.BigDecimal dbqxndkye;
	/**权限内贷款笔数*/
	@Excel(name = "权限内贷款笔数", width = 15)
    @ApiModelProperty(value = "权限内贷款笔数")
	private java.math.BigDecimal dbqxndkbs;
	/**dbqxndkhs*/
    @ApiModelProperty(value = "dbqxndkhs")
	private java.math.BigDecimal dbqxndkhs;
	/**dbqxwdkje*/
    @ApiModelProperty(value = "dbqxwdkje")
	private java.math.BigDecimal dbqxwdkje;
	/**dbqxwdkye*/
    @ApiModelProperty(value = "dbqxwdkye")
	private java.math.BigDecimal dbqxwdkye;
}
