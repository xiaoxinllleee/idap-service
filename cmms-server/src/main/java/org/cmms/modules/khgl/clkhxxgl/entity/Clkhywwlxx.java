package org.cmms.modules.khgl.clkhxxgl.entity;

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
 * @Description: 与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_YWHYWWLXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_YWHYWWLXX对象", description="与我行业务往来信息")
public class Clkhywwlxx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
    /**客户ID*/
    @Excel(name = "客户ID", width = 15)
    @ApiModelProperty(value = "客户ID")
    private String khId;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;

	/**是否开通存款业务*/
	@Excel(name = "是否开通存款业务", width = 15, dicCode = "sfbz")
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
	@Excel(name = "是否开通贷款业务", width = 15, dicCode = "sfbz")
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
	/**表外不良贷款余额*/
	@Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**是否开通手机银行业务*/
	@Excel(name = "是否开通手机银行业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通手机银行业务")
    @Dict(dicCode = "sfbz")
	private String sfktsjyhyw;
	/**是否开通网上银行业务*/
	@Excel(name = "是否开通网上银行业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通网上银行业务")
    @Dict(dicCode = "sfbz")
	private String sfktwsyhyw;
	/**是否办理ETC业务*/
	@Excel(name = "是否办理ETC业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理ETC业务")
    @Dict(dicCode = "sfbz")
	private String sfbletcyw;
    /**是否开通社保卡*/
    @Excel(name = "是否开通社保卡", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通社保卡")
    @Dict(dicCode = "sfbz")
    private String sfktsbk;
    /**是否代发工资*/
    @Excel(name = "是否代发工资", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否代发工资")
    @Dict(dicCode = "sfbz")
    private String sfdfgz;
    /**是否开通POS机*/
    @Excel(name = "是否开通POS机", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通POS机")
    @Dict(dicCode = "sfbz")
    private String sfktpos;
    /**是否开通聚合支付*/
    @Excel(name = "是否开通聚合支付", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通聚合支付")
    @Dict(dicCode = "sfbz")
    private String sfktjhzf;
}
