package org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.vo;

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
 * @Description: 贷款欠本欠息登记薄 导入VO
 * @Author: Penghr
 * @Date:   2022-10-27
 * @Version: V1.0
 */
@Data
@TableName("yxbldk_dkqbqxdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yxbldk_dkqbqxdjb对象", description="欠本欠息明细")
public class QbqxdjbImportVO {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	@ExcelVerify(notNull = true)
	private Date dataDate;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	@ExcelVerify(notNull = false)
	private String xh;
	/**开户机构号*/
	@Excel(name = "开户机构", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "开户机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String branchNo;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	@ExcelVerify(notNull = true)
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String identNo;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	@ExcelVerify(notNull = true)
	private String acctNo;
	/**最后交易日*/
	@Excel(name = "最后交易日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最后交易日")
	@ExcelVerify(notNull = false)
	private Date lstFinDt;
	/**贷款余额（元）*/
	@Excel(name = "贷款余额（元）", width = 15)
    @ApiModelProperty(value = "贷款余额（元）")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal currBal;
	/**期数*/
	@Excel(name = "期数", width = 15)
    @ApiModelProperty(value = "期数")
	@ExcelVerify(notNull = true)
	private String qs;
	/**结欠本金（元）*/
	@Excel(name = "结欠本金（元）", width = 15)
    @ApiModelProperty(value = "结欠本金（元）")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal jqbj;
	/**结欠利息（元）*/
	@Excel(name = "结欠利息（元）", width = 15)
    @ApiModelProperty(value = "结欠利息（元）")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal jqlx;
	/**结欠罚息（元）*/
	@Excel(name = "结欠罚息（元）", width = 15)
    @ApiModelProperty(value = "结欠罚息（元）")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal jqfx;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15, dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "第一责任人")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	@ExcelVerify(notNull = false)
	private String dyzrr;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15, dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	@ExcelVerify(notNull = false)
	private String zkhjl;
	/**贷款类型*/
	//@Excel(name = "贷款类型", width = 15, dicCode = "yxbldklx")
    @ApiModelProperty(value = "贷款类型")
	@Dict(dicCode = "yxbldklx")
	//@ExcelVerify(notNull = false)
	private Integer dklx;
	/**计息起始日*/
	@Excel(name = "计息起始日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "计息起始日")
	@ExcelVerify(notNull = false)
	private Date jxqsr;
	/**计息终止日*/
	@Excel(name = "计息终止日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "计息终止日")
	@ExcelVerify(notNull = false,interHandler = true)
	private Date jxzzr;
	/**欠息次数*/
	@ApiModelProperty(value = "欠息次数")
	private Integer qxcs;
	/**状态标识*/
	@ApiModelProperty(value = "状态标识")
	@Dict(dicCode = "yxbldkqbqxzt")
	private Integer ztbs;
	/**录入标识(0 导入 1 录入 2 修改)*/
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
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
}
