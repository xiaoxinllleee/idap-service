package org.cmms.modules.ywgl.djkyw.djkxxgl.entity;

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
 * @Description: 贷记卡信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-05
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DJKBLXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DJKBLXX对象", description="贷记卡信息管理")
public class Djkxxgl {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String org;
	/**推广人员编号*/
	@Excel(name = "推广人员编号", width = 15)
    @ApiModelProperty(value = "推广人员编号")
	@ExcelVerify(notNull = true)
	private String tgh;


	/*统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	@ExcelVerify(notNull = true)
	private Date tjyf;


	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String custManagerId;
	/**员工号*/
	@Excel(name = "员工号", width = 15)
	@ApiModelProperty(value = "员工号")
	private String jobnumber;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	@ExcelVerify(notNull = true)
	private String acctNo;
	/**种类*/
	@Excel(name = "种类", width = 15)
	@ApiModelProperty(value = "种类")
	private String jzkm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String ctfcCd;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String custName;
	/**余额*/
	@Excel(name = "余额", width = 15)
	@ApiModelProperty(value = "余额")
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal balance;


	/**发放日期*/
	@Excel(name = "发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发放日期")
	private Date putOutDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date maturity;



	/**录入标记*/
    @ApiModelProperty(value = "录入标记")
	private Integer lrbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**金额*/
    @ApiModelProperty(value = "金额")
	private java.math.BigDecimal putoutSum;


	/**逾期期数*/
	@Excel(name = "逾期期数", width = 15)
	@ApiModelProperty(value = "逾期期数")
	@ExcelVerify(notNull = true,interHandler = true)
	private Long yqqs;

	/**最早欠息日期*/
	@Excel(name = "最早欠息日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "最早欠息日期")
	private Date minCalcDate;
}
