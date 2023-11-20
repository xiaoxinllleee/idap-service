package org.cmms.modules.pad.shxxgl.entity;

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
 * @Description: 关联人信息
 * @Author: jeecg-boot
 * @Date:   2020-09-17
 * @Version: V1.0
 */
@Data
@TableName("KHGL_SHGLRXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_SHGLRXX对象", description="关联人信息")
public class KhglShglrxx {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**商户id*/
	@Excel(name = "商户id", width = 15)
	@ApiModelProperty(value = "商户id")
	private String shId;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**性别*/
	@Excel(name = "性别", width = 15)
	@ApiModelProperty(value = "性别")
	private String xb;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
	@ApiModelProperty(value = "年龄")
	private String nl;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	@ApiModelProperty(value = "修改人")
	private String xgr;
	/**从事职业*/
	@Excel(name = "从事职业", width = 15)
	@ApiModelProperty(value = "从事职业")
	private String cszy;
	/**工作单位*/
	@Excel(name = "工作单位", width = 15)
	@ApiModelProperty(value = "工作单位")
	private String gzdw;
	/**关系描叙*/
	@Excel(name = "关系描叙", width = 15)
	@ApiModelProperty(value = "关系描叙")
	private String gxms;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
	@ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**常住或经营地址*/
	@Excel(name = "常住或经营地址", width = 15)
	@ApiModelProperty(value = "常住或经营地址")
	private String czhjydz;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
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
	/**是否开通社保卡*/
	@Excel(name = "是否开通社保卡", width = 15)
	@ApiModelProperty(value = "是否开通社保卡")
	private String sfktsbk;
	/**是否代发工资*/
	@Excel(name = "是否代发工资", width = 15)
	@ApiModelProperty(value = "是否代发工资")
	private String sfdfgz;
	/**是否开通POS机*/
	@Excel(name = "是否开通POS机", width = 15)
	@ApiModelProperty(value = "是否开通POS机")
	private String sfktpos;
	/**是否开通聚合支付*/
	@Excel(name = "是否开通聚合支付", width = 15)
	@ApiModelProperty(value = "是否开通聚合支付")
	private String sfktjhzf;
	/**是否办理E支付*/
	@Excel(name = "是否办理E支付", width = 15)
	@ApiModelProperty(value = "是否办理E支付")
	private String sfblezf;
	/**是否办理E缴费*/
	@Excel(name = "是否办理E缴费", width = 15)
	@ApiModelProperty(value = "是否办理E缴费")
	private String sfblejf;
	/**是否办理助农终端*/
	@Excel(name = "是否办理助农终端", width = 15)
	@ApiModelProperty(value = "是否办理助农终端")
	private String sfblznzd;
	/**是否办理理财业务*/
	@Excel(name = "是否办理理财业务", width = 15)
	@ApiModelProperty(value = "是否办理理财业务")
	private String sfbllcyw;
	/**是否办理代理保险业务*/
	@Excel(name = "是否办理代理保险业务", width = 15)
	@ApiModelProperty(value = "是否办理代理保险业务")
	private String sfbldlbx;
	/**是否关注我行公众号*/
	@Excel(name = "是否关注我行公众号", width = 15)
	@ApiModelProperty(value = "是否关注我行公众号")
	private String sfgzgzh;
	/**是否开通信用卡*/
	@Excel(name = "是否开通信用卡", width = 15)
	@ApiModelProperty(value = "是否开通信用卡")
	private String sfktxyk;
	/**是否开通福民卡*/
	@Excel(name = "是否开通福民卡", width = 15)
	@ApiModelProperty(value = "是否开通福民卡")
	private String sfktfmk;
	/**是否开扫码付*/
	@Excel(name = "是否开扫码付", width = 15)
	@ApiModelProperty(value = "是否开扫码付")
	private String sfktsmf;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**占股比例*/
	@Excel(name = "占股比例", width = 15)
	@ApiModelProperty(value = "占股比例")
	private java.math.BigDecimal zgbl;
}
