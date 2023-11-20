package org.cmms.modules.khgl.qy.entity;

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
 * @Description: 企业客户与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_YWHYWWLXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_YWHYWWLXX对象", description="企业客户与我行业务往来信息")
public class QyYwhywwlxx {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;
	/**花名册id*/
	@Excel(name = "花名册id", width = 15)
	@ApiModelProperty(value = "花名册id")
	private String hmcId;
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
	/**不良贷款余额*/
	@Excel(name = "不良贷款余额", width = 15)
	@ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**表外不良贷款余额*/
	@Excel(name = "表外不良贷款余额", width = 15)
	@ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**最近贷款到期日期*/
	@Excel(name = "最近贷款到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "最近贷款到期日期")
	private Date zjdkdqrq;
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
	/**是否办理etc业务*/
	@Excel(name = "是否办理etc业务", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理etc业务")
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
	/**是否办理E支付*/
	@Excel(name = "是否办理E支付", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理E支付")
	@Dict(dicCode = "sfbz")
	private String sfblezf;
	/**是否办理E缴费*/
	@Excel(name = "是否办理E缴费", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理E缴费")
	@Dict(dicCode = "sfbz")
	private String sfblejf;
	/**是否办理助农终端*/
	@Excel(name = "是否办理助农终端", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理助农终端")
	@Dict(dicCode = "sfbz")
	private String sfblznzd;
	/**是否办理理财业务*/
	@Excel(name = "是否办理理财业务", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理理财业务")
	@Dict(dicCode = "sfbz")
	private String sfbllcyw;
	/**是否办理代理保险业务*/
	@Excel(name = "是否办理代理保险业务", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理代理保险业务")
	@Dict(dicCode = "sfbz")
	private String sfbldlbx;
	/**是否关注我行公众号*/
	@Excel(name = "是否关注我行公众号", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否关注我行公众号")
	@Dict(dicCode = "sfbz")
	private String sfgzgzh;
	/**是否开通信用卡*/
	@Excel(name = "是否开通信用卡", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通信用卡")
	@Dict(dicCode = "sfbz")
	private String sfktxyk;
	/**是否开通福民卡*/
	@Excel(name = "是否开通福民卡", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通福民卡")
	@Dict(dicCode = "sfbz")
	private String sfktfmk;
	/**是否开扫码付*/
	@Excel(name = "是否开扫码付", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否开扫码付")
	@Dict(dicCode = "sfbz")
	private String sfktsmf;
}
