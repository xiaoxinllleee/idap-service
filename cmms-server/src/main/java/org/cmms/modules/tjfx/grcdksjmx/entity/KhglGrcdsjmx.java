package org.cmms.modules.tjfx.grcdksjmx.entity;

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
 * @Description: 个人存贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2020-11-28
 * @Version: V1.0
 */
@Data
@TableName("KHGL_GRCDSJMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_GRCDSJMX对象", description="个人存贷款数据明细")
public class KhglGrcdsjmx {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**是否开通存款业务*/
	@Excel(name = "是否开通存款业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通存款业务")
	@Dict(dicCode = "sfbz")
	private String sfktckyw;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**存款年日平余额*/
	@Excel(name = "存款年日平余额", width = 15)
    @ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpye;
	/**活期存款余额*/
	@Excel(name = "活期存款余额", width = 15)
    @ApiModelProperty(value = "活期存款余额")
	private java.math.BigDecimal hqckye;
	/**定期存款余额*/
	@Excel(name = "定期存款余额", width = 15)
    @ApiModelProperty(value = "定期存款余额")
	private java.math.BigDecimal dqckye;
	/**活期存款日平余额*/
	@Excel(name = "活期存款日平余额", width = 15)
    @ApiModelProperty(value = "活期存款日平余额")
	private java.math.BigDecimal hqckrpye;
	/**定期存款日平余额*/
	@Excel(name = "定期存款日平余额", width = 15)
    @ApiModelProperty(value = "定期存款日平余额")
	private java.math.BigDecimal dqckrpye;
	/**活期存款年日平余额*/
	@Excel(name = "活期存款年日平余额", width = 15)
    @ApiModelProperty(value = "活期存款年日平余额")
	private java.math.BigDecimal hqcknrpye;
	/**定期存款年日平余额*/
	@Excel(name = "定期存款年日平余额", width = 15)
    @ApiModelProperty(value = "定期存款年日平余额")
	private java.math.BigDecimal dqcknrpye;
	/**是否开通贷款业务*/
	@Excel(name = "是否开通贷款业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通贷款业务")
	@Dict(dicCode = "sfbz")
	private String sfktdkyw;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**贷款月日平*/
	@Excel(name = "贷款月日平", width = 15)
    @ApiModelProperty(value = "贷款月日平")
	private java.math.BigDecimal dkyrp;
	/**贷款年日平*/
	@Excel(name = "贷款年日平", width = 15)
    @ApiModelProperty(value = "贷款年日平")
	private java.math.BigDecimal dknrp;
	/**最近贷款到期日期*/
	@Excel(name = "最近贷款到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最近贷款到期日期")
	private Date zjdkdqrq;
	/**不良贷款余额*/
	@Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**是否开通手机银行业务*/
	@Excel(name = "是否开通手机银行业务", width = 15)
    @ApiModelProperty(value = "是否开通手机银行业务")
	private String sfktsjyhyw;
	/**是否开通网上银行业务*/
	@Excel(name = "是否开通网上银行业务", width = 15)
    @ApiModelProperty(value = "是否开通网上银行业务")
	private String sfktwsyhyw;
	/**是否办理etc业务*/
	@Excel(name = "是否办理etc业务", width = 15)
    @ApiModelProperty(value = "是否办理etc业务")
	private String sfbletcyw;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**存款最早开户日期*/
	@Excel(name = "存款最早开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "存款最早开户日期")
	private Date ckzzkhrq;
	/**贷款最早发放日期*/
	@Excel(name = "贷款最早发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贷款最早发放日期")
	private Date dkzzffrq;
}
