package org.cmms.modules.khgl.jgsydw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 与我行相关业务
 * @Author: jeecg-boot
 * @Date:   2020-02-19
 * @Version: V1.0
 */
@Data
@TableName("KHGL_YWHXGYW")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_YWHXGYW对象", description="与我行相关业务")
public class JgsydwYwhxgyw {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**是否不良贷款户*/
	@Excel(name = "是否不良贷款户", width = 15)
    @ApiModelProperty(value = "是否不良贷款户")
	private String sfbldkh;
	/**是否开通存款业务*/
	@Excel(name = "是否开通存款业务", width = 15)
    @ApiModelProperty(value = "是否开通存款业务")
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
	/**是否开通贷款业务*/
	@Excel(name = "是否开通贷款业务", width = 15)
    @ApiModelProperty(value = "是否开通贷款业务")
	private String sfktdkyw;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**不良贷款余额*/
	@Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**表外不良贷款余额*/
	@Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
    /**是否开通手机银行*/
    @Excel(name = "是否开通手机银行", width = 15)
    @ApiModelProperty(value = "是否开通手机银行")
    private String sfktsjyhyw;
    /**是否开通网上银行*/
    @Excel(name = "是否开通网上银行", width = 15)
    @ApiModelProperty(value = "是否开通网上银行")
    private String sfktwsyhyw;
    /**是否办理ETC*/
    @Excel(name = "是否办理ETC", width = 15)
    @ApiModelProperty(value = "是否办理ETC")
    private String sfbletcyw;
    /**是否开通社保卡*/
    @Excel(name = "是否开通社保卡", width = 15)
    @ApiModelProperty(value = "是否开通社保卡")
    private String sfktsbk;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}
