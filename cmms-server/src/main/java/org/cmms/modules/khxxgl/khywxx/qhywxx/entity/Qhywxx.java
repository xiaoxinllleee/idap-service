package org.cmms.modules.khxxgl.khywxx.qhywxx.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 全行业务信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_YWHYWWLXX_DAY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_YWXX对象", description="全行业务信息")
public class Qhywxx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;

	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**是否开通存款业务*/
	@Excel(name = "是否开通存款业务", width = 15)
    @ApiModelProperty(value = "是否开通存款业务")
	private java.lang.String sfktckyw;
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
	@Excel(name = "是否开通贷款业务", width = 15)
    @ApiModelProperty(value = "是否开通贷款业务")
	private java.lang.String sfktdkyw;
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
	private java.util.Date zjdkdqrq;
	/**不良贷款余额*/
	@Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**表外不良贷款余额*/
	@Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**是否开通手机银行业务*/
	@Excel(name = "是否开通手机银行业务", width = 15)
    @ApiModelProperty(value = "是否开通手机银行业务")
	private java.lang.String sfktsjyhyw;
	/**是否开通网上银行业务*/
	@Excel(name = "是否开通网上银行业务", width = 15)
    @ApiModelProperty(value = "是否开通网上银行业务")
	private java.lang.String sfktwsyhyw;
	/**是否办理etc业务*/
	@Excel(name = "是否办理etc业务", width = 15)
    @ApiModelProperty(value = "是否办理etc业务")
	private java.lang.String sfbletcyw;
	/**是否开通社保卡*/
	@Excel(name = "是否开通社保卡", width = 15)
    @ApiModelProperty(value = "是否开通社保卡")
	private java.lang.String sfktsbk;
	/**是否代发工资*/
	@Excel(name = "是否代发工资", width = 15)
    @ApiModelProperty(value = "是否代发工资")
	private java.lang.String sfdfgz;
	/**是否开通POS机*/
	@Excel(name = "是否开通POS机", width = 15)
    @ApiModelProperty(value = "是否开通POS机")
	private java.lang.String sfktpos;
	/**是否开通聚合支付*/
	@Excel(name = "是否开通聚合支付", width = 15)
    @ApiModelProperty(value = "是否开通聚合支付")
	private java.lang.String sfktjhzf;
	/**是否办理E支付*/
	@Excel(name = "是否办理E支付", width = 15)
    @ApiModelProperty(value = "是否办理E支付")
	private java.lang.String sfblezf;
	/**是否办理E缴费*/
	@Excel(name = "是否办理E缴费", width = 15)
    @ApiModelProperty(value = "是否办理E缴费")
	private java.lang.String sfblejf;
	/**是否办理助农终端*/
	@Excel(name = "是否办理助农终端", width = 15)
    @ApiModelProperty(value = "是否办理助农终端")
	private java.lang.String sfblznzd;
	/**是否办理理财业务*/
	@Excel(name = "是否办理理财业务", width = 15)
    @ApiModelProperty(value = "是否办理理财业务")
	private java.lang.String sfbllcyw;
	/**是否办理代理保险业务*/
	@Excel(name = "是否办理代理保险业务", width = 15)
    @ApiModelProperty(value = "是否办理代理保险业务")
	private java.lang.String sfbldlbx;
	/**是否关注我行公众号*/
	@Excel(name = "是否关注我行公众号", width = 15)
    @ApiModelProperty(value = "是否关注我行公众号")
	private java.lang.String sfgzgzh;
	/**是否开通信用卡*/
	@Excel(name = "是否开通信用卡", width = 15)
    @ApiModelProperty(value = "是否开通信用卡")
	private java.lang.String sfktxyk;
	/**是否开通福民卡*/
	@Excel(name = "是否开通福民卡", width = 15)
    @ApiModelProperty(value = "是否开通福民卡")
	private java.lang.String sfktfmk;
	/**是否开扫码付*/
	@Excel(name = "是否开扫码付", width = 15)
    @ApiModelProperty(value = "是否开扫码付")
	private java.lang.String sfktsmf;


	/**存款最早开户日期*/
	@Excel(name = "存款最早开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "存款最早开户日期")
	private java.util.Date ckzzkhrq;
	/**贷款最早发放日期*/
	@Excel(name = "贷款最早发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贷款最早发放日期")
	private java.util.Date dkzzffrq;
	/**利息*/
	@Excel(name = "利息", width = 15)
    @ApiModelProperty(value = "利息")
	private java.math.BigDecimal lx;
}
