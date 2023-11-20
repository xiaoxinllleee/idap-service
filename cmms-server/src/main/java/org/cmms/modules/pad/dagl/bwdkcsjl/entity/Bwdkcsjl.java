package org.cmms.modules.pad.dagl.bwdkcsjl.entity;

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
 * @Description: 表外贷款催收记录
 * @Author: jeecg-boot
 * @Date:   2023-07-12
 * @Version: V1.0
 */
@Data
@TableName("loan_bwdk_csjl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="loan_bwdk_csjl对象", description="表外贷款催收记录")
public class Bwdkcsjl {
    
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
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**借据名称*/
	@Excel(name = "借据名称", width = 15)
    @ApiModelProperty(value = "借据名称")
	private String jjmc;
	/**贷款日期*/
	@Excel(name = "贷款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贷款日期")
	private Date dkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**核心余额*/
	@Excel(name = "核心余额", width = 15)
    @ApiModelProperty(value = "核心余额")
	private java.math.BigDecimal hxye;
	/**催收日期*/
	@Excel(name = "催收日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "催收日期")
	private Date csrq;
	/**催收人工号*/
	@Excel(name = "催收人工号", width = 15)
    @ApiModelProperty(value = "催收人工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String csrgh;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**附件业务ID*/
	@Excel(name = "附件业务ID", width = 15)
    @ApiModelProperty(value = "附件业务ID")
	private Integer ywid;
	/**审批状态*/
	@Excel(name = "审批状态", width = 15)
    @ApiModelProperty(value = "审批状态")
	private Integer spzt;
	/**类型(0.催收;1.清收)*/
	@Excel(name = "类型(0.催收;1.清收)", width = 15)
    @ApiModelProperty(value = "类型(0.催收;1.清收)")
	private Integer lx;
	/**催收类型*/
	@Excel(name = "催收类型", width = 15)
    @ApiModelProperty(value = "催收类型")
	@Dict(dicCode = "cslx")
	private String cslx;
	/**还款期限*/
	@Excel(name = "还款期限", width = 15)
    @ApiModelProperty(value = "还款期限")
	private String hkqx;
	/**还款本金*/
	@Excel(name = "还款本金", width = 15)
    @ApiModelProperty(value = "还款本金")
	private java.math.BigDecimal hkbj;
	/**还款利息*/
	@Excel(name = "还款利息", width = 15)
    @ApiModelProperty(value = "还款利息")
	private java.math.BigDecimal hklx;
	/**催收详情*/
	@Excel(name = "催收详情", width = 15)
    @ApiModelProperty(value = "催收详情")
	private String csxq;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
	private String latitude;
}
