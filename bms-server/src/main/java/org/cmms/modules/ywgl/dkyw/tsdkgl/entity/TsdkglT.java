package org.cmms.modules.ywgl.dkyw.tsdkgl.entity;

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
 * @Description: 特殊贷款管理（绩效考核/初始设置）
 * @Author: penghr
 * @Date:   2023-03-29
 * @Version: V1.0
 */
@Data
@TableName("erp_bas_tsdkgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_bas_tsdkgl对象", description="特殊贷款管理（绩效考核/初始设置）")
public class TsdkglT {

	/**机构代码*/
//	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**证件号码*/
//	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String zjhm;
	/**客户姓名*/
//	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	@ExcelVerify(notNull = false)
	private String custName;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	@ExcelVerify(notNull = true)
	private String dkzh;
	/**贷款金额*/
//	@Excel(name = "贷款金额", width = 15)
	@ApiModelProperty(value = "贷款金额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal dkje;
	/**发放日期*/
//	@Excel(name = "发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "借款日期")
	@ExcelVerify(notNull = false)
	private Date jkrq;
	/**到期日期*/
//	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期日期")
	@ExcelVerify(notNull = false)
	private Date dqrq;
	/**贷款标签*/
	@Excel(name = "贷款标签", width = 15, dicCode = "dkbq")
	@ApiModelProperty(value = "贷款标签")
	@Dict(dicCode = "dkbq")
	@ExcelVerify(notNull = true, interHandler = true)
	private String dkbq;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	@ExcelVerify(notNull = false)
	private String bz;
	/**录入人*/
	// @Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
	// @Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	// @Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**修改人*/
	// @Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改时间*/
	// @Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
}
