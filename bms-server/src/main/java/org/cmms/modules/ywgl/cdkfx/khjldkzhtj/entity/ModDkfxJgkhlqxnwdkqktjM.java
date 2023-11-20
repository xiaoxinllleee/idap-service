package org.cmms.modules.ywgl.cdkfx.khjldkzhtj.entity;

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
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户经理贷款综合统计
 * @Author: jeecg-boot
 * @Date:   2021-06-28
 * @Version: V1.0
 */
@Data
@TableName("MOD_DKFX_JGKHJLQXNWDKQKTJ_M")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MOD_DKFX_JGKHJLQXNWDKQKTJ_M对象", description="客户经理贷款综合统计")
public class ModDkfxJgkhlqxnwdkqktjM {

	@TableField(exist=false)
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private String zhjgdm;

	/**权限外贷款笔数*/
	@Excel(name = "权限外贷款笔数", width = 15)
    @ApiModelProperty(value = "权限外贷款笔数")
	private java.math.BigDecimal dbqxwdkbs;
	/**dbqxwdkhs*/
	@Excel(name = "dbqxwdkhs", width = 15)
    @ApiModelProperty(value = "dbqxwdkhs")
	private java.math.BigDecimal dbqxwdkhs;
	/**dhdkje*/
	@Excel(name = "dhdkje", width = 15)
    @ApiModelProperty(value = "dhdkje")
	private java.math.BigDecimal dhdkje;
	/**dhdkye*/
	@Excel(name = "dhdkye", width = 15)
    @ApiModelProperty(value = "dhdkye")
	private java.math.BigDecimal dhdkye;
	/**dhdkbs*/
	@Excel(name = "dhdkbs", width = 15)
    @ApiModelProperty(value = "dhdkbs")
	private java.math.BigDecimal dhdkbs;
	/**贷款户数*/
	@Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
	private java.math.BigDecimal dhdkhs;
	/**dhqxndkje*/
	@Excel(name = "dhqxndkje", width = 15)
    @ApiModelProperty(value = "dhqxndkje")
	private java.math.BigDecimal dhqxndkje;
	/**dhqxndkye*/
	@Excel(name = "dhqxndkye", width = 15)
    @ApiModelProperty(value = "dhqxndkye")
	private java.math.BigDecimal dhqxndkye;
	/**dhqxndkbs*/
	@Excel(name = "dhqxndkbs", width = 15)
    @ApiModelProperty(value = "dhqxndkbs")
	private java.math.BigDecimal dhqxndkbs;
	/**权限内贷款户数*/
	@Excel(name = "权限内贷款户数", width = 15)
    @ApiModelProperty(value = "权限内贷款户数")
	private java.math.BigDecimal dhqxndkhs;
	/**dhqxwdkje*/
	@Excel(name = "dhqxwdkje", width = 15)
    @ApiModelProperty(value = "dhqxwdkje")
	private java.math.BigDecimal dhqxwdkje;
	/**dhqxwdkye*/
	@Excel(name = "dhqxwdkye", width = 15)
    @ApiModelProperty(value = "dhqxwdkye")
	private java.math.BigDecimal dhqxwdkye;
	/**dhqxwdkbs*/
	@Excel(name = "dhqxwdkbs", width = 15)
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
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**lrczy*/
	@Excel(name = "lrczy", width = 15)
    @ApiModelProperty(value = "lrczy")
	private String lrczy;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
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
	@Excel(name = "dbdkhs", width = 15)
    @ApiModelProperty(value = "dbdkhs")
	private java.math.BigDecimal dbdkhs;
	/**dbqxndkje*/
	@Excel(name = "dbqxndkje", width = 15)
    @ApiModelProperty(value = "dbqxndkje")
	private java.math.BigDecimal dbqxndkje;
	/**dbqxndkye*/
	@Excel(name = "dbqxndkye", width = 15)
    @ApiModelProperty(value = "dbqxndkye")
	private java.math.BigDecimal dbqxndkye;
	/**权限内贷款笔数*/
	@Excel(name = "权限内贷款笔数", width = 15)
    @ApiModelProperty(value = "权限内贷款笔数")
	private java.math.BigDecimal dbqxndkbs;
	/**dbqxndkhs*/
	@Excel(name = "dbqxndkhs", width = 15)
    @ApiModelProperty(value = "dbqxndkhs")
	private java.math.BigDecimal dbqxndkhs;
	/**dbqxwdkje*/
	@Excel(name = "dbqxwdkje", width = 15)
    @ApiModelProperty(value = "dbqxwdkje")
	private java.math.BigDecimal dbqxwdkje;
	/**dbqxwdkye*/
	@Excel(name = "dbqxwdkye", width = 15)
    @ApiModelProperty(value = "dbqxwdkye")
	private java.math.BigDecimal dbqxwdkye;
}
