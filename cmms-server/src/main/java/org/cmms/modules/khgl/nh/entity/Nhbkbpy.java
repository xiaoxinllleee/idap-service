package org.cmms.modules.khgl.nh.entity;

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
 * @Description: 农户背靠背评议
 * @Author: cmms
 * @Version: V1.0 * @Date:   2019-12-02
 */
@Data
@TableName("CAMS_ZCSX_NHBKBPY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHBKBPY对象", description="农户背靠背评议")
public class Nhbkbpy {
    /**主键ID*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15,dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "区域代码")
    @Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private Integer nl;
	/**性别(1.男；2.女)*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
    @ApiModelProperty(value = "性别(1.男；2.女)")
    @Dict(dicCode = "sex")
	private String xb;
	/**经营项目*/
	@Excel(name = "经营项目", width = 15)
    @ApiModelProperty(value = "经营项目")
	private String jyxm;
	/**家庭收入*/
	@Excel(name = "家庭收入", width = 15,dicCode = "bkbpy_sr")
    @ApiModelProperty(value = "家庭收入")
    //@Dict(dicCode = "bkbpy_sr")
    @Dict(dicCode = "bkbpy_sr_xt")
	private String jtsr;
	/**资产情况*/
	@Excel(name = "资产情况", width = 15)
    @ApiModelProperty(value = "资产情况")
	private String zcqk;
	/**是否有稳定收入*/
	@Excel(name = "是否有稳定收入", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否有稳定收入")
    @Dict(dicCode = "sfbz")
	private String sfywdsr;
	/**诚信度(1.很好；2.较好；3.一般；4.差)*/
	@Excel(name = "诚信度", width = 15, dicCode = "bkbpy_xydj")
    @ApiModelProperty(value = "诚信度(1.很好；2.较好；3.一般；4.差)")
    @Dict(dicCode = "bkbpy_xydj")
	private String cxd;
	/**是否建议授信*/
	@Excel(name = "是否建议授信", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否建议授信")
    @Dict(dicCode = "sfbz")
	private String sfjysx;
	/**不予授信情形*/
	@Excel(name = "不予授信情形", width = 15, dicCode = "py_bysxqx")
    @ApiModelProperty(value = "不予授信情形")
    //@Dict(dicCode = "py_bysxqx_ny")
    @Dict(dicCode = "py_bysxqx_ls", dicCodeQybm = "405=py_bysxqx_ny|310=py_bysxqx_sf|320=py_bysxqx_sf|095=py_bysxqx_ty")
	private String bysxqx;
	/**建议授信额度*/
	@Excel(name = "建议授信额度", width = 15)
    @ApiModelProperty(value = "建议授信额度")
	private java.math.BigDecimal jysxed;
	/**评议员姓名*/
	@Excel(name = "评议员姓名", width = 15)
    @ApiModelProperty(value = "评议员姓名")
	private String pyyxm;
	/**评议员证件号码*/
	@Excel(name = "评议员证件号码", width = 15)
    @ApiModelProperty(value = "评议员证件号码")
	private String pyyzjhm;
	/**评议时间*/
	@Excel(name = "评议时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评议时间")
	private Date pysj;
    /**评议类型*/
    @Excel(name = "评议类型", width = 15, dicCode = "bkbpy_pylx")
    @ApiModelProperty(value = "评议类型")
    @Dict(dicCode = "bkbpy_pylx")
    private String pylx;

    @TableField(exist = false)
    private String pylxVal;
    /**评议得分*/
    @Excel(name = "评议得分", width = 15)
    @ApiModelProperty(value = "评议得分")
    private String pydf;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**电子签名1*/
    @ApiModelProperty(value = "电子签名1")
	private String sign1;
	/**电子签名2*/
    @ApiModelProperty(value = "电子签名2")
	private String sign2;
	/**婚姻状况(1.未婚；2.已婚无子女；3.已婚有子女)*/
	@Excel(name = "婚姻状况", width = 15, dicCode = "bkbpy_hyzk")
    @ApiModelProperty(value = "婚姻状况(1.未婚；2.已婚无子女；3.已婚有子女)")
    @Dict(dicCode = "bkbpy_hyzk")
	private String hyzk;
	/**年龄情况(1.46-60岁；2.18-30岁；3.31-45岁)*/
	@Excel(name = "年龄情况", width = 15, dicCode = "bkbpy_nlqk")
    @ApiModelProperty(value = "年龄情况(1.46-60岁；2.18-30岁；3.31-45岁)")
    @Dict(dicCode = "bkbpy_nlqk")
	private String nnqk;
	/**健康状态情况(1.较差；2.一般；3.良好)*/
	@Excel(name = "健康状态情况", width = 15, dicCode = "bkbpy_qkms")
    @ApiModelProperty(value = "健康状态情况(1.较差；2.一般；3.良好)")
    @Dict(dicCode = "bkbpy_qkms")
	private String jkztqk;
	/**经营能力(1.较差；2.一般；3.良好)*/
	@Excel(name = "经营能力", width = 15,dicCode = "bkbpy_qkms")
    @ApiModelProperty(value = "经营能力(1.较差；2.一般；3.良好)")
    @Dict(dicCode = "bkbpy_qkms")
	private String jynl;
	/**房屋价值情况*/
	@Excel(name = "房屋价值情况", width = 15, dicCode = "bkbpy_fwjzqk")
    @ApiModelProperty(value = "房屋价值情况")
    @Dict(dicCode = "bkbpy_fwjzqk")
	private String fwjzqk;
    /**大宗耐用消费品情况*/
    @Excel(name = "大宗耐用消费品情况", width = 15, dicCode = "bkbpy_dznyxfpqk")
    @ApiModelProperty(value = "大宗耐用消费品情况")
    @Dict(dicCode = "bkbpy_dznyxfpqk")
    private String dznyxfpqk;
    /**农机具情况*/
    @Excel(name = "农机具情况", width = 15, dicCode = "bkbpy_njjqk")
    @ApiModelProperty(value = "农机具情况")
    @Dict(dicCode = "bkbpy_njjqk")
    private String njjqk;
    /**经营情况*/
    @Excel(name = "经营情况", width = 15, dicCode = "bkbpy_jyqk")
    @ApiModelProperty(value = "经营情况")
    @Dict(dicCode = "bkbpy_jyqk")
    private String jyqk;
    /**家庭纯收入情况*/
    @Excel(name = "家庭纯收入情况", width = 15, dicCode = "bkbpy_jtcsrqk")
    @ApiModelProperty(value = "家庭纯收入情况")
    @Dict(dicCode = "bkbpy_jtcsrqk")
    private String jtcsrqk;
    /**家庭总收入*/
    @Excel(name = "家庭总收入", width = 15, dicCode = "bkbpy_jtzsrzczb")
    @ApiModelProperty(value = "家庭总收入")
    @Dict(dicCode = "bkbpy_jtzsrzczb")
    private String jtzsr;
    /**借款日还款意愿(1.较差；2.一般；3.较强)*/
    @Excel(name = "借款日还款意愿", width = 15, dicCode = "bkbpy_jkrhkyy")
    @ApiModelProperty(value = "借款日还款意愿(1.较差；2.一般；3.较强)")
    @Dict(dicCode = "bkbpy_jkrhkyy")
    private String jkrhkyy;
    /**借款人本期逾期(1.有本金逾期；2.无本金逾期)*/
    @Excel(name = "借款人本期逾期", width = 15, dicCode = "bkbpy_jkrbjyq")
    @ApiModelProperty(value = "借款人本期逾期(1.有本金逾期；2.无本金逾期)")
    @Dict(dicCode = "bkbpy_jkrbjyq")
    private String jkrbjyq;
    /**借款人利息逾期(1.有利息逾期；2.无利息逾期)*/
    @Excel(name = "借款人利息逾期", width = 15, dicCode = "bkbpy_jkrlxyq")
    @ApiModelProperty(value = "借款人利息逾期(1.有利息逾期；2.无利息逾期)")
    @Dict(dicCode = "bkbpy_jkrlxyq")
    private String jkrlxyq;
    /**存款业务往来*/
    @Excel(name = "存款业务往来", width = 15, dicCode = "bkbpy_ckywwlqk")
    @ApiModelProperty(value = "存款业务往来")
    @Dict(dicCode = "bkbpy_ckywwlqk")
    private String ckywwl;
    /**职业情况(1.普通村民；2.多种经营者；3.村干部及有固定工作者)*/
    @Excel(name = "职业情况", width = 15, dicCode = "bkbpy_zyqk")
    @ApiModelProperty(value = "职业情况(1.普通村民；2.多种经营者；3.村干部及有固定工作者)")
    @Dict(dicCode = "bkbpy_zyqk")
    private String zwqk;
    /**家庭成员关系(1.不和睦，矛盾冲突；2.基本团结，无突出矛盾；3。团结和睦)*/
    @Excel(name = "家庭成员关系", width = 15, dicCode = "bkbpy_jtcygx")
    @ApiModelProperty(value = "家庭成员关系(1.不和睦，矛盾冲突；2.基本团结，无突出矛盾；3。团结和睦)")
    @Dict(dicCode = "bkbpy_jtcygx")
    private String jtcygx;
    /**家庭劳动力人数情况(1.2人及以下；2.3人；3.4人及以上)*/
    @Excel(name = "家庭劳动力人数情况", width = 15, dicCode = "bkbpy_jtldlrsqk")
    @ApiModelProperty(value = "家庭劳动力人数情况(1.2人及以下；2.3人；3.4人及以上)")
    @Dict(dicCode = "bkbpy_jtldlrsqk")
    private String jtndlrsqk;
    /**家庭人口素质和技能*/
    @Excel(name = "家庭人口素质和技能", width = 15, dicCode = "bkbpy_jtrkszhjn")
    @ApiModelProperty(value = "家庭人口素质和技能")
    @Dict(dicCode = "bkbpy_jtrkszhjn")
    private String jtrkszhjn;
    /**信誉状况(1.较差；2.一般；3.良好)*/
    @Excel(name = "信誉状况", width = 15, dicCode = "bkbpy_qkms")
    @ApiModelProperty(value = "信誉状况(1.较差；2.一般；3.良好)")
    @Dict(dicCode = "bkbpy_qkms")
    private String xyzk;
    /**社会声望及荣誉*/
    @Excel(name = "社会声望及荣誉", width = 15, dicCode = "bkbpy_shswjry")
    @ApiModelProperty(value = "社会声望及荣誉")
    @Dict(dicCode = "bkbpy_shswjry")
    private String shswjry;
    /**社会关系状况*/
    @Excel(name = "社会关系状况", width = 15, dicCode = "bkbpy_shgxzk")
    @ApiModelProperty(value = "社会关系状况")
    @Dict(dicCode = "bkbpy_shgxzk")
    private String shgxzk;
    /**生活习惯情况*/
    @Excel(name = "生活习惯情况", width = 15, dicCode = "bkbpy_shxgqk")
    @ApiModelProperty(value = "生活习惯情况")
    @Dict(dicCode = "bkbpy_shxgqk")
    private String shxgqk;
    /**交通运输工具情况*/
    @Excel(name = "交通运输工具情况", width = 15, dicCode = "bkbpy_jtysgjqk")
    @ApiModelProperty(value = "交通运输工具情况")
    @Dict(dicCode = "bkbpy_jtysgjqk")
    private String jtysgjqk;
    /**在民间高利监控(1.在民间有高息借款；2.在民间无高息借款)*/
    @Excel(name = "在民间高利监控", width = 15, dicCode = "bkbpy_zmjgxjkqk")
    @ApiModelProperty(value = "在民间高利监控(1.在民间有高息借款；2.在民间无高息借款)")
    @Dict(dicCode = "bkbpy_zmjgxjkqk")
    private String zmjgljk;
    /**金融机构贷款情况(1.其他金融机构有贷款；2.其他金融机构无贷款)*/
    @Excel(name = "金融机构贷款情况", width = 15, dicCode = "bkbpy_qtjgdkqk")
    @ApiModelProperty(value = "金融机构贷款情况(1.其他金融机构有贷款；2.其他金融机构无贷款)")
    @Dict(dicCode = "bkbpy_qtjgdkqk")
    private String jrjgdkqk;
    /**家庭负债情况(1.无负债；2.少量负债；3.较高负债)*/
    @Excel(name = "家庭负债情况", width = 15, dicCode = "py_jtfzqk")
    @ApiModelProperty(value = "家庭负债情况(1.无负债；2.少量负债；3.较高负债)")
    @Dict(dicCode = "py_jtfzqk")
    private String jtfzqk;
    @Excel(name = "先锋党员和公职人员", width = 15)
    @ApiModelProperty(value = "先锋党员和公职人员")
    private String xfdyhgzry;

    @Excel(name = "书香家族", width = 15,dicCode = "sxjz")
    @ApiModelProperty(value = "书香家族")
    @Dict(dicCode = "sxjz")
    private String sxjz;

    @Excel(name = "乡村振兴家庭", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "乡村振兴家庭")
    @Dict(dicCode = "sfbz")
    private String xczxjt;

    @Excel(name = "遵纪守法户", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "遵纪守法户")
    @Dict(dicCode = "sfbz")
    private String zjsfh;

    @Excel(name = "和谐家庭", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "和谐家庭")
    @Dict(dicCode = "sfbz")
    private String hxjt;

    @Excel(name = "手工录入", width = 15)
    @ApiModelProperty(value = "手工录入")
    private String sglr;

    @Excel(name = "不予授信类型", width = 15)
    @ApiModelProperty(value = "不予授信类型")
    private String bysxlx;
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

    /**评议轮数*/
    @Excel(name = "评议轮数", width = 15)
    @ApiModelProperty(value = "评议轮数")
    private Integer pyls;

    @TableField(exist = false)
    private String pylsVal;

    @Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
    @TableField(exist = false)
    private String sjhm;

    /**是否了解情况*/
    @Excel(name = "是否了解情况", width = 15,dicCode = "sfljqk")
    @ApiModelProperty(value = "是否了解情况")
    @Dict(dicCode = "sfljqk")
    private String sfljqk;

    /**农村房产情况*/
    @Excel(name = "农村房产情况", width = 15,dicCode = "ywbz")
    @ApiModelProperty(value = "农村房产情况")
    @Dict(dicCode = "ywbz")
    private String ncfcqk;
    /**农村房产情况备注*/
    @Excel(name = "农村房产情况备注", width = 15)
    @ApiModelProperty(value = "农村房产情况备注")
    private String ncfcqkBz;
    /**城区有无房产*/
    @Excel(name = "城区有无房产", width = 15,dicCode = "ywbz")
    @ApiModelProperty(value = "城区有无房产")
    @Dict(dicCode = "ywbz")
    private String cqywfc;
    /**城区有无房产备注*/
    @Excel(name = "城区有无房产备注", width = 15)
    @ApiModelProperty(value = "城区有无房产备注")
    private String cqywfcBz;
    /**有无车辆*/
    @Excel(name = "有无车辆", width = 15,dicCode = "ywbz")
    @Dict(dicCode = "ywbz")
    @ApiModelProperty(value = "有无车辆")
    private String ywcl;
    /**有无车辆备注*/
    @Excel(name = "有无车辆备注", width = 15)
    @ApiModelProperty(value = "有无车辆备注")
    private String ywclBz;
    /**收入*/
    @Excel(name = "收入", width = 15,dicCode = "bkbpy_sr")
    @ApiModelProperty(value = "收入")
    @Dict(dicCode = "bkbpy_sr")
    private Integer sr;
    /**工作类型*/
    @Excel(name = "工作类型", width = 15,dicCode = "gzlx")
    @ApiModelProperty(value = "工作类型")
    @Dict(dicCode = "gzlx")
    private String gzlx;
    /**长期居住地*/
    @Excel(name = "长期居住地", width = 15,dicCode = "sfzbd")
    @ApiModelProperty(value = "长期居住地")
    @Dict(dicCode = "sfzbd")
    private String cqjzd;
    /**长期居住地备注*/
    @Excel(name = "长期居住地备注", width = 15)
    @ApiModelProperty(value = "长期居住地备注")
    private String cqjzdbz;
    /**主营项目*/
    @Excel(name = "主营项目", width = 15)
    @ApiModelProperty(value = "主营项目")
    @Dict(dicCode = "zyxm_xt")
    private String zyxm;
    /**基础模型测算*/
    @Excel(name = "基础模型测算", width = 15)
    @ApiModelProperty(value = "基础模型测算")
    private java.math.BigDecimal jcmxcs;
    /**是否在本地*/
    @Excel(name = "是否在本地", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否在本地")
    @Dict(dicCode = "sfbz")
    private String sfzbd;
    /**评议员建议额度*/
    @Excel(name = "评议员建议额度", width = 15)
    @ApiModelProperty(value = "评议员建议额度")
    private java.math.BigDecimal pyyjyed;
    /**家庭存款日平合计*/
    @Excel(name = "家庭存款日平合计", width = 15)
    @ApiModelProperty(value = "家庭存款日平合计")
    private java.math.BigDecimal jtckrphj;

    @Dict(dicCode = "tdcbjyq_xt")
    private String tdcbjyq;
    /**家庭有无大学生*/
    @Excel(name = "家庭有无大学生", width = 15)
    @ApiModelProperty(value = "家庭有无大学生")
    @Dict(dicCode = "ywbz")
    private String jtywdxs;
    /**城区房产位置*/
    @Excel(name = "城区房产位置", width = 15,dicCode = "ty_cqfcwz")
    @ApiModelProperty(value = "城区房产位置")
    @Dict(dicCode = "ty_cqfcwz")
    private String cqfcwz;
    /**客户分群*/
    @Excel(name = "客户分群", width = 15)
    @ApiModelProperty(value = "客户分群")
    @Dict(dicCode = "ty_khfl")
    private String khfq;
    /**其他项目*/
    @Excel(name = "其他项目", width = 15)
    @ApiModelProperty(value = "其他项目")
    private String qtxm;
    /**配偶姓名*/
    @Excel(name = "配偶姓名", width = 15)
    @ApiModelProperty(value = "配偶姓名")
    private String poxm;
    /**配偶证件号码*/
    @Excel(name = "配偶证件号码", width = 15)
    @ApiModelProperty(value = "配偶证件号码")
    private String pozjhm;
    /**就业分类*/
    @Excel(name = "就业分类", width = 15)
    @ApiModelProperty(value = "就业分类")
    @Dict(dicCode = "ty_jyfl")
    private String jyfl;
    /**行业分类*/
    @Excel(name = "行业分类", width = 15)
    @ApiModelProperty(value = "行业分类")
    @Dict(dicCode = "ty_hyfl")
    private String hyfl;
    /**就业地点*/
    @Excel(name = "就业地点", width = 15)
    @ApiModelProperty(value = "就业地点")
    @Dict(dicCode = "ty_jydd")
    private String jydd;
    /**长期居住地详情*/
    @Excel(name = "长期居住地详情", width = 15)
    @ApiModelProperty(value = "长期居住地详情")
    private String cqjzdxq;
    /**就业地点备注*/
    @Excel(name = "就业地点备注", width = 15)
    @ApiModelProperty(value = "就业地点备注")
    private String jyddbz;
    /**评议流程*/
    @Excel(name = "评议流程", width = 15)
    @ApiModelProperty(value = "评议流程")
    private String pylc;
    /**评议员建议额度*/
    @Excel(name = "支行审定额度", width = 15)
    @ApiModelProperty(value = "支行审定额度")
    private java.math.BigDecimal zhsded;
    /**支行审定备注*/
    @Excel(name = "支行审定备注", width = 15)
    @ApiModelProperty(value = "支行审定备注")
    private String zhsdbz;
}
