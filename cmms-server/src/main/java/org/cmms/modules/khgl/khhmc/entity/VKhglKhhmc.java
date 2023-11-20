package org.cmms.modules.khgl.khhmc.entity;

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
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-01-15
 * @Version: V1.0
 */
@Data
@TableName("V_KHGL_KHHMC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_KHGL_KHHMC对象", description="客户花名册")
public class VKhglKhhmc {

	/**ID*/
	//@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**所属支行*/
	@Excel(name = "所属网点", width = 15, dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	@ApiModelProperty(value = "所属网点")
	@Dict(dicCode="ZZBZ", dictTable="V_HR_BAS_ORGANIZATION_CMMS", dicText="ZZJC")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "归属网格", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	@ApiModelProperty(value = "归属网格")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String ssyxdy;
	/**所属乡镇*/
	/*@Excel(name = "所属乡镇", width = 15,dicCode="dybh", dictTable="YXDYGL_YJYXDYGL", dicText="dymc")
	@ApiModelProperty(value = "ssxz")
	//@Dict( dicCode="dybh", dictTable="YXDYGL_YJYXDYGL", dicText="dymc")
	private String ssxz;*/
	/**行政村*/
	/*@Excel(name = "行政村", width = 15,dicCode="dybh", dictTable="YXDYGL_EJYXDYGL", dicText="dymc")
	@ApiModelProperty(value = "xzc")
	//@Dict( dicCode="dybh", dictTable="YXDYGL_EJYXDYGL", dicText="dymc")
	private String xzc;*/
	/**行政组*/
	/*@Excel(name = "行政组", width = 15,dicCode="dybh", dictTable="YXDYGL_SJYXDYGL", dicText="dymc")
	@ApiModelProperty(value = "xzz")
	//@Dict( dicCode="dybh", dictTable="YXDYGL_SJYXDYGL", dicText="dymc")
	private String xzz;*/
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与户主关系")
    @Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否户主")
    @Dict(dicCode = "sfbz")
	private String sfhz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@TableId(value = "zjhm", type=IdType.INPUT)
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**民族*/
	@Excel(name = "民族", width = 15, dicCode = "mz")
	@ApiModelProperty(value = "民族")
	@Dict(dicCode = "mz")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15, dicCode = "hyzk")
	@ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hyzk")
	private String hyzk;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
    @ApiModelProperty(value = "原所属乡镇")
	private String yssxz;
	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
    @ApiModelProperty(value = "原行政村")
	private String yxzc;
	/**系统评定结果(1:灰名单;2:白名单;3:黑名单)*/
	@Excel(name = "系统评定结果", width = 15, dicCode = "xtpdjg")
    @ApiModelProperty(value = "系统评定结果(1:灰名单;2:白名单;3:黑名单)")
    @Dict(dicCode = "xtpdjg")
	private Integer xtpdjg;
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
    @ApiModelProperty(value = "系统评定说明")
	private String xtpdsm;
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
	/**是否办理etc业务*/
	@Excel(name = "是否办理ETC业务", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理etc业务")
	@Dict(dicCode = "sfbz")
	private String sfbletcyw;
	/**是否办理etc业务*/
	@Excel(name = "是否开通社保卡", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通社保卡")
	@Dict(dicCode = "sfbz")
	private String sfktsbk;
	@Excel(name = "是否领取社保卡", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否领取社保卡")
	@Dict(dicCode = "sfbz")
	private String sflqsbk;
	@Excel(name = "是否开通信用卡", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通信用卡")
	private String sfktxyk;
	/**是否开通福民卡*/
	@Excel(name = "是否开通福民卡", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通福民卡")
	private String sfktfmk;
	/**是否开扫码付*/
	@Excel(name = "是否开扫码付", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开扫码付")
	private String sfktsmf;
	/**是否开通POS机*/
	@Excel(name = "是否开通POS机", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通POS机")
	private String sfktpos;
	/**是否开通聚合支付*/
	@Excel(name = "是否开通聚合支付", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通聚合支付")
	private String sfktjhzf;
	/**是否办理E支付*/
	@Excel(name = "是否办理E支付", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理E支付")
	private String sfblezf;
	/**是否办理E缴费*/
	@Excel(name = "是否办理E缴费", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理E缴费")
	private String sfblejf;
	/**是否办理助农终端*/
	@Excel(name = "是否办理助农终端", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理助农终端")
	private String sfblznzd;
	/**是否办理理财业务*/
	@Excel(name = "是否办理理财业务", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理理财业务")
	private String sfbllcyw;
	/**是否办理代理保险业务*/
	@Excel(name = "是否办理代理保险业务", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理代理保险业务")
	private String sfbldlbx;
	/**是否关注我行公众号*/
	@Excel(name = "是否关注我行公众号", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否关注我行公众号")
	private String sfgzgzh;
	/**是否吸毒人员*/
	@ApiModelProperty(value = "是否吸毒人员")
	@Excel(name = "是否吸毒人员", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	private String sfxdry;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否贫困户")
	private String sfpkh;
	/**是否低保*/
	@Excel(name = "是否低保", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否低保")
	private String sfdb;
	/**是否公职人员*/
	@Excel(name = "是否公职人员", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否公职人员")
	private String sfgzry;
	/**是否非法集资*/
	@Excel(name = "是否非法集资", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否非法集资")
	private String sfffjz;
	/**患病记录*/
	@Excel(name = "患病记录", width = 15)
	@ApiModelProperty(value = "患病记录")
	private String hbjl;

	/**是否开通代发工资业务*//*
	@Excel(name = "是否开通代发工资业务", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通代发工资业务")
	@Dict(dicCode = "sfbz")
	private String sfktdfgzyw;*/
	/**录入标识*/
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
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
}
