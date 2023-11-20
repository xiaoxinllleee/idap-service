package org.cmms.modules.ywgl.dkyw.dksjzrgl.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DKZHKHZR")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DKZHKHZR对象", description="贷款数据责任管理")
public class DksjzrglImport {

	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	@ExcelVerify(notNull = true)
	private String dkzh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	private String custid;
	/**账号类型1贷款余额表2贴现信息表*/
//	@Excel(name = "账号类型1贷款余额表2贴现信息表", width = 15)
	@ApiModelProperty(value = "账号类型1贷款余额表2贴现信息表")
	private Integer zhlx;


	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String jobnumber;

	/**责任人姓名*/
	@Excel(name = "责任人工号", width = 15)
	@ApiModelProperty(value = "责任人姓名")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String jobnumberzr;
	/**责任客户经理标识*/
	@Excel(name = "责任客户经理标识", width = 15)
	@ApiModelProperty(value = "责任客户经理标识")
	private String custidzr;
	/**是否异议不接收*/
	@Excel(name = "是否异议不接收", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否异议不接收")
	@Dict(dicCode = "sfbz")
	private Integer sfyybjs;
	/**追责标记*/
	@Excel(name = "追责标记", width = 15,dicCode = "zzbs")
	@ApiModelProperty(value = "追责标记")
	@Dict(dicCode = "zzbs")
	private String zzbz;

	/**证件号码*/
	@ApiModelProperty(value = "证件号码")
	private String zjhm;

	/**dflag*/
//	@Excel(name = "dflag", width = 15)
    @ApiModelProperty(value = "dflag")
	private Integer dflag;

	/**追责操作员*/
//	@Excel(name = "追责操作员", width = 15)
    @ApiModelProperty(value = "追责操作员")
	private String zzczy;


	/**zbks*/
//	@Excel(name = "zbks", width = 15)
    @ApiModelProperty(value = "zbks")
	private Integer zbks;
	/**关联责任比率*/
//	@Excel(name = "关联责任比率", width = 15)
    @ApiModelProperty(value = "关联责任比率")
	private java.math.BigDecimal glzrbl;

	@Excel(name = "统计月份(格式:yyyy-MM)", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
	@ExcelVerify(notNull = true)
	private Date tjyf;

	/**追责日期*/
	@Excel(name = "追责日期(格式:yyyy-MM-dd)", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "追责日期")
	@ExcelVerify(interHandler = true)
	private Date zzrq;

	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;

}
