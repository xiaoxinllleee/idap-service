package org.cmms.modules.dkjkpt.ydqkjk.entity;

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
 * @Description: 逾期贷款监控
 * @Author: cmms
 * @Date:   2019-09-12
 * @Version: V1.0
 */
@Data
@TableName("V_DKJKPT_YQDKJK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_DKJKPT_YQDKJK对象", description="逾期贷款监控")
public class VdkjkptYqdkjk {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode="ywjgdm", dictTable="hr_bas_organization", dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**便民卡卡号*/
	@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "便民卡卡号")
	private String bmkkh;
    /**客户类型*/
    @Excel(name = "客户类型", width = 15, dicCode = "dkjkpt_khlx")
    @ApiModelProperty(value = "客户类型")
    @Dict(dicCode = "dkjkpt_khlx")
    private String khlx;
    /**地址*/
    @Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String dz;
    /**电话号码*/
    @Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
    private String dhhm;
    /**信贷贷款品种*/
    @Excel(name = "信贷贷款品种", width = 15, dicCode = "dkzl")
    @ApiModelProperty(value = "信贷贷款品种")
    @Dict(dicCode = "dkzl")
    private String xddkpz;
    /**客户经理员工工号*/
    @Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理员工工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    private String khjlyggh;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private Date jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**起息日*/
	@Excel(name = "起息日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起息日")
	private Date qxr;
	/**结息日*/
	@Excel(name = "结息日", width = 15)
    @ApiModelProperty(value = "结息日")
	private Integer jxr;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private Integer syts;
}
