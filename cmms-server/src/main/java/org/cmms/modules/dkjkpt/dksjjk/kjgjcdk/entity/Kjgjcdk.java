package org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.entity;

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
 * @Description: 跨机构交叉贷款
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_JCDKTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_JCDKTJ对象", description="跨机构交叉贷款")
public class Kjgjcdk {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "YWJGDM", dictTable = "V_HR_BAS_ORGANIZATION_CMMSZH", dicText = "ZZJC")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "YWJGDM", dictTable = "V_HR_BAS_ORGANIZATION_CMMSZH", dicText = "ZZJC")
	private String brNo;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String identNo;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String acctNo;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
	@ApiModelProperty(value = "产品名称")
	private String catTypeName;
	/**便民卡卡号*/
	//@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "便民卡卡号")
	private String cardNo;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15, dicCode = "dkzl")
    @ApiModelProperty(value = "信贷贷款品种")
	@Dict(dicCode = "dkzl")
	private String businessPhase;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15, dicCode = "dkxt")
	@ApiModelProperty(value = "贷款形态")
	@Dict(dicCode = "dkxt")
	private String fiveClassType;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String custCn;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
    @ApiModelProperty(value = "借款日期")
	private String qxDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String endDate;
	/**贷款种类*/
	//@Excel(name = "贷款种类", width = 15)
    @ApiModelProperty(value = "贷款种类")
	private String loanKind;
	/**剩余天数*/
	//@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private Integer remainingDays;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal advVal;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal loanBal;
}
