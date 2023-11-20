package org.cmms.modules.tjbb.tjfz.sgtzdr.dkhxdjb.vo;

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
 * @Description: 贷款核销登记薄 导入VO
 * @Author: Penghr
 * @Date:   2022-12-17
 * @Version: V1.0
 */
@Data
public class DkhxdjbImportVO {

	/**数据日期*/
	//@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
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
	/**开户机构编号*/
	@Excel(name = "开户机构", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构编号")
	@ExcelVerify(notNull = true)
	private String jgbm;
	/**贷款户名*/
	@Excel(name = "贷款户名", width = 15)
    @ApiModelProperty(value = "贷款户名")
	@ExcelVerify(notNull = false)
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String identNo;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	@ExcelVerify(notNull = false)
	private String dkzh;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	@ExcelVerify(notNull = false)
	private Date jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	@ExcelVerify(notNull = false)
	private Date dqrq;
	/**贷款余额（元）*/
	@Excel(name = "贷款余额（元）", width = 15)
	@ApiModelProperty(value = "贷款余额（元）")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal dkye;
	/**核销日期*/
	@Excel(name = "核销日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "核销日期")
	@ExcelVerify(notNull = false)
	private Date hxrq;
	/**核销本金（元）*/
	@Excel(name = "核销本金（元）", width = 15)
	@ApiModelProperty(value = "核销本金（元）")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal hxbj;
	/**核销利息（元）*/
	@Excel(name = "核销利息（元）", width = 15)
    @ApiModelProperty(value = "核销利息（元）")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal hxlx;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	@ExcelVerify(notNull = false)
	private String dyzrr;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
