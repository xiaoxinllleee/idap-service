package org.cmms.modules.xyjlcx.hmdgl.hmdgl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @Description: 黑名单管理
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@Data
@TableName("CREDIT_HMDGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value="CREDIT_HMDGL对象", description="黑名单管理")
public class Hmdgl {

	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String zjhm;

	/**登记日期*/
	@Excel(name = "登记日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "登记日期")
	private Date djrq;
	/**不良记录抹除日期*/
	@Excel(name = "不良记录抹除日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "不良记录抹除日期")
	private Date bljlmcrq;
	/**不良行为结束日期*/
	@Excel(name = "不良行为结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "不良行为结束日期")
	private Date blxwjsrq;
	/**有效标识*/
	@Excel(name = "有效标识", width = 15,dicCode = "hmdyxbz")
    @ApiModelProperty(value = "有效标识")
	@Dict(dicCode = "hmdyxbz")
	private Integer yxbz;
	/**情况说明*/
	@Excel(name = "情况说明", width = 15)
    @ApiModelProperty(value = "情况说明")
	private String qksm;
	/**审批人*/
	@Excel(name = "审批人", width = 15)
    @ApiModelProperty(value = "审批人")
	private String spr;
	/**审批时间*/
	@Excel(name = "审批时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "审批时间")
	private Date spsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
	@Excel(name = "录入标识",width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**抹除人*/
	@Excel(name = "抹除人", width = 15)
    @ApiModelProperty(value = "抹除人")
	private String mcr;
	/**抹除时间*/
	@Excel(name = "抹除时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "抹除时间")
	private Date mcsj;
	/**不良记录行为描述*/
	@Excel(name = "不良记录行为描述", width = 15,dicCode = "bljlxwms")
	@ApiModelProperty(value = "不良记录行为描述")
	@Dict(dicCode = "bljlxwms")
	//@Dict(dicCode = "bljlxwms")
	@ExcelVerify(notNull = true,interHandler = true)
	private Integer bljlxwms;

}
