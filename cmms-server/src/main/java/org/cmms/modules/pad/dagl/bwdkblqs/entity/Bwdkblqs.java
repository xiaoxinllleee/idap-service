package org.cmms.modules.pad.dagl.bwdkblqs.entity;

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
 * @Description: 表外贷款不良清收
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Data
@TableName("loan_bwdk_blqs")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="loan_bwdk_blqs对象", description="表外贷款不良清收")
public class Bwdkblqs {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**转入表外类型*/
	@Excel(name = "转入表外类型", width = 15)
    @ApiModelProperty(value = "转入表外类型")
	private Integer zrbwlx;
	/**收回类型（1：本金 2：利息 3：本金+利息）*/
	@Excel(name = "收回类型（1：本金 2：利息 3：本金+利息）", width = 15)
    @ApiModelProperty(value = "收回类型（1：本金 2：利息 3：本金+利息）")
	@Dict(dicCode = "shlx")
	private Integer shlx;
	/**收回日期*/
	@Excel(name = "收回日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "收回日期")
	private Date shrq;
	/**收回金额*/
	@Excel(name = "收回金额", width = 15)
    @ApiModelProperty(value = "收回金额")
	private java.math.BigDecimal shje;
	/**收回人工号*/
	@Excel(name = "收回人工号", width = 15)
    @ApiModelProperty(value = "收回人工号")
	private String shrgh;
	/**收回人姓名*/
	@Excel(name = "收回人姓名", width = 15)
    @ApiModelProperty(value = "收回人姓名")
	private String shrxm;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识（0：导入 1：录入 2：修改）", width = 15)
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**收回责任人工号*/
	@Excel(name = "收回责任人工号", width = 15)
    @ApiModelProperty(value = "收回责任人工号")
	private String shzrrgh;
	/**溢收利息*/
	@Excel(name = "溢收利息", width = 15)
    @ApiModelProperty(value = "溢收利息")
	private java.math.BigDecimal yslx;
	/**业务ID*/
	@Excel(name = "业务ID", width = 15)
    @ApiModelProperty(value = "业务ID")
	private Integer ywid;
	/**审批状态*/
	@Excel(name = "审批状态", width = 15)
    @ApiModelProperty(value = "审批状态")
	private Integer spzt;
	/**收回责任人姓名*/
	@Excel(name = "收回责任人姓名", width = 15)
    @ApiModelProperty(value = "收回责任人姓名")
	private String shzrrxm;
	/**收归时执行利率(%)*/
	@Excel(name = "收归时执行利率(%)", width = 15)
    @ApiModelProperty(value = "收归时执行利率(%)")
	private java.math.BigDecimal shszxll;
	/**收回利息结息日*/
	@Excel(name = "收回利息结息日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "收回利息结息日")
	private Date shlxjxr;
	/**收回利息起息日*/
	@Excel(name = "收回利息起息日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "收回利息起息日")
	private Date shlxqxr;
	/**收回方式*/
	@Excel(name = "收回方式", width = 15)
    @ApiModelProperty(value = "收回方式")
	private Integer shfs;
	/**异常收回*/
	@Excel(name = "异常收回", width = 15)
    @ApiModelProperty(value = "异常收回")
	private java.math.BigDecimal bxtz;
}
