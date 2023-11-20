package org.cmms.modules.ywgl.dkyw.dkzhzy.entity;

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
 * @Description: 贷款账号转移
 * @Author: jeecg-boot
 * @Date:   2021-09-27
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DKYEB_CUST")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DKYEB_CUST对象", description="贷款账号转移")
public class DkzhzyImportVo {


	/**机构代码*/
//	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String org;
	/**客户经理标识*/
//	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	private String custManagerId;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	@ExcelVerify(notNull = true)
	private String jobnumber;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	@ExcelVerify(notNull = true,interHandler = true)
	private String acctNo;
	/**客户名称*/
//	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String custName;
	/**证件号码*/
//	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String ctfcCd;
	/**贷款发放日期*/
//	@Excel(name = "贷款发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款发放日期")
	private Date putOutDate;
	/**贷款到期日期*/
//	@Excel(name = "贷款到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款到期日期")
	private Date maturity;
	/**最早欠息日期*/
//	@Excel(name = "最早欠息日期", width = 15)
	@ApiModelProperty(value = "最早欠息日期")
	private String minCalcDate;
	/**贷款发放金额*/
//	@Excel(name = "贷款发放金额", width = 15)
	@ApiModelProperty(value = "贷款发放金额")
	private java.math.BigDecimal putoutSum;
	/**贷款余额*/
//	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal balance;
	/**操作员*/
//	@Excel(name = "操作员", width = 15)
	@ApiModelProperty(value = "操作员")
	private String czy;
	/**操作时间*/
//	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "操作时间")
	private Date czsj;


	/**impkmmc*/
	//@Excel(name = "impkmmc", width = 15)
	@ApiModelProperty(value = "impkmmc")
	private String impkmmc;
	/**impfileday*/
	//@Excel(name = "impfileday", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "impfileday")
	private Date impfileday;
	/**impfileid*/
	//@Excel(name = "impfileid", width = 15)
	@ApiModelProperty(value = "impfileid")
	private Long impfileid;
	/**imptime*/
	//@Excel(name = "imptime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "imptime")
	private Date imptime;
	/**impuser*/
	//@Excel(name = "impuser", width = 15)
	@ApiModelProperty(value = "impuser")
	private String impuser;
	/**finInsName*/
	//@Excel(name = "finInsName", width = 15)
	@ApiModelProperty(value = "finInsName")
	private String finInsName;
	/**voucherNo*/
	//@Excel(name = "voucherNo", width = 15)
	@ApiModelProperty(value = "voucherNo")
	private String voucherNo;
	/**appSum*/
	//@Excel(name = "贷款发放金额", width = 15)
	@ApiModelProperty(value = "贷款发放金额")
	private java.math.BigDecimal appSum;
	/**totleBalance*/
	//@Excel(name = "totleBalance", width = 15)
	@ApiModelProperty(value = "totleBalance")
	private java.math.BigDecimal totleBalance;
	/**appRate*/
	//@Excel(name = "appRate", width = 15)
	@ApiModelProperty(value = "appRate")
	private java.math.BigDecimal appRate;
	/**qxDate*/
	//@Excel(name = "最早欠息日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "最早欠息日期")
	private Date qxDate;
	/**oprCustCn*/
	//@Excel(name = "oprCustCn", width = 15)
	@ApiModelProperty(value = "oprCustCn")
	private String oprCustCn;
	/**firstCustCn*/
	//@Excel(name = "firstCustCn", width = 15)
	@ApiModelProperty(value = "firstCustCn")
	private String firstCustCn;
	/**vouchType*/
	//@Excel(name = "vouchType", width = 15)
	@ApiModelProperty(value = "vouchType")
	private String vouchType;
	/**fiveClassType*/
	//@Excel(name = "fiveClassType", width = 15)
	@ApiModelProperty(value = "fiveClassType")
	private String fiveClassType;
	/**fiveTypeCalc*/
	//@Excel(name = "fiveTypeCalc", width = 15)
	@ApiModelProperty(value = "fiveTypeCalc")
	private String fiveTypeCalc;
	/**actualPurpose*/
	//@Excel(name = "actualPurpose", width = 15)
	@ApiModelProperty(value = "actualPurpose")
	private String actualPurpose;
	/**businessPhase*/
	//@Excel(name = "businessPhase", width = 15)
	@ApiModelProperty(value = "businessPhase")
	private String businessPhase;
	/**custBusadd*/
	//@Excel(name = "custBusadd", width = 15)
	@ApiModelProperty(value = "custBusadd")
	private String custBusadd;
	/**custTel*/
	//@Excel(name = "custTel", width = 15)
	@ApiModelProperty(value = "custTel")
	private String custTel;
	/**payMode*/
	//@Excel(name = "payMode", width = 15)
	@ApiModelProperty(value = "payMode")
	private String payMode;
	/**custCn*/
	//@Excel(name = "custCn", width = 15)
	@ApiModelProperty(value = "custCn")
	private String custCn;
	/**custCn1*/
	//@Excel(name = "custCn1", width = 15)
	@ApiModelProperty(value = "custCn1")
	private String custCn1;
	/**jzkm*/
	//@Excel(name = "jzkm", width = 15)
	@ApiModelProperty(value = "jzkm")
	private String jzkm;
	/**yqll*/
	//@Excel(name = "yqll", width = 15)
	@ApiModelProperty(value = "yqll")
	private java.math.BigDecimal yqll;
	/**currency*/
	//@Excel(name = "currency", width = 15)
	@ApiModelProperty(value = "currency")
	private String currency;
	/**custId*/
	//@Excel(name = "custId", width = 15)
	@ApiModelProperty(value = "custId")
	private Long custId;
	/**appTerm*/
	//@Excel(name = "appTerm", width = 15)
	@ApiModelProperty(value = "appTerm")
	private Long appTerm;
	/**rate*/
	//@Excel(name = "rate", width = 15)
	@ApiModelProperty(value = "rate")
	private java.math.BigDecimal rate;
	/**contractNo*/
	//@Excel(name = "contractNo", width = 15)
	@ApiModelProperty(value = "contractNo")
	private String contractNo;
	/**custCn2*/
	//@Excel(name = "custCn2", width = 15)
	@ApiModelProperty(value = "custCn2")
	private String custCn2;
	/**custType*/
	//@Excel(name = "custType", width = 15)
	@ApiModelProperty(value = "custType")
	private String custType;
	/**custType1*/
	//@Excel(name = "custType1", width = 15)
	@ApiModelProperty(value = "custType1")
	private String custType1;
	/**custType2*/
	//@Excel(name = "custType2", width = 15)
	@ApiModelProperty(value = "custType2")
	private String custType2;
	/**custType3*/
	//@Excel(name = "custType3", width = 15)
	@ApiModelProperty(value = "custType3")
	private String custType3;
	/**purposeType1*/
	//@Excel(name = "purposeType1", width = 15)
	@ApiModelProperty(value = "purposeType1")
	private String purposeType1;
	/**purposeType2*/
	//@Excel(name = "purposeType2", width = 15)
	@ApiModelProperty(value = "purposeType2")
	private String purposeType2;
	/**purposeType3*/
	//@Excel(name = "purposeType3", width = 15)
	@ApiModelProperty(value = "purposeType3")
	private String purposeType3;
	/**purposeType4*/
	//@Excel(name = "purposeType4", width = 15)
	@ApiModelProperty(value = "purposeType4")
	private String purposeType4;
	/**purposeType5*/
	//@Excel(name = "purposeType5", width = 15)
	@ApiModelProperty(value = "purposeType5")
	private String purposeType5;
	/**purposeType6*/
	//@Excel(name = "purposeType6", width = 15)
	@ApiModelProperty(value = "purposeType6")
	private String purposeType6;
	/**purposeType7*/
	//@Excel(name = "purposeType7", width = 15)
	@ApiModelProperty(value = "purposeType7")
	private String purposeType7;
	/**businessNo*/
	//@Excel(name = "businessNo", width = 15)
	@ApiModelProperty(value = "businessNo")
	private String businessNo;
	/**purposeType*/
	//@Excel(name = "purposeType", width = 15)
	@ApiModelProperty(value = "purposeType")
	private String purposeType;
	/**cardNo*/
	//@Excel(name = "cardNo", width = 15)
	@ApiModelProperty(value = "cardNo")
	private String cardNo;
	/**impjgjc*/
	//@Excel(name = "impjgjc", width = 15)
	@ApiModelProperty(value = "impjgjc")
	private String impjgjc;

}
