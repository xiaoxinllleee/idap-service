package org.cmms.modules.xdgl.grdkgl.entity;

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
 * @Description: 个人贷款审批记录
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Data
@TableName("V_CAMS_ZCSX_GRXDCJXX_SPJL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="", description="个人贷款审批记录")
public class Vgrdkspjl {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;

	/**所属支行*/
	@Excel(name = "所属网点", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属网点")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;

	@Excel(name = "镇", width = 15,dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "一级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	private String yjyxdybh;
	/**二级营销单元编号*/
	@Excel(name = "村", width = 15,dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "二级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	private String ejyxdybh;
	/**三级营销单元编号*/
	@Excel(name = "组", width = 15,dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "三级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	private String sjyxdybh;


	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "所属客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String sskhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;

	/**khxm*/
    @ApiModelProperty(value = "khxm")
	private String khxm;
	/**cshy*/
    @ApiModelProperty(value = "cshy")
	private String cshy;
	/**客户品行*/
	@Excel(name = "客户品行", width = 15,dicCode = "grdk_khpx")
	@ApiModelProperty(value = "客户品行")
	private String khpx;
	/**性别*/
	@Excel(name = "性别", width = 15,dicCode ="sex")
	@ApiModelProperty(value = "性别")
	private String xb;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15 ,dicCode = "hyzk_cj")
	@ApiModelProperty(value = "婚姻状况")
	private String hyzk;

	/**从事职业*/
	@Excel(name = "从事职业", width = 15,dicCode = "cszy")
	@ApiModelProperty(value = "从事职业")
	private String cszy;


	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	@ApiModelProperty(value = "手机号码")
	private String sjhm;

	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;


	/**评定等级*/
	@Excel(name = "评定等级", width = 15,dicCode = "grdk_pddj")
	@ApiModelProperty(value = "评定等级")
	@Dict(dicCode = "grdk_pddj")
	private String pddj;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
	@ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;

	/**调查结论*/
	@Excel(name = "调查结论", width = 20)
	@ApiModelProperty(value = "调查结论")
	private String dcjl;

	@Excel(name = "风险经理意见", width = 20)
	@ApiModelProperty(value = "风险经理意见")
	private String fxjlspyj;

	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;

	@Excel(name = "确认状态", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "确认状态")
	@Dict(dicCode = "sfbz")
	private String jtspzzzt;

	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;


	/**dwdb*/
    @ApiModelProperty(value = "dwdb")
	private String dwdb;
	/**dbdx*/
    @ApiModelProperty(value = "dbdx")
	private String dbdx;
	/**qtyx*/
    @ApiModelProperty(value = "qtyx")
	private String qtyx;
	/**共同借款人*/
    @ApiModelProperty(value = "共同借款人")
	private String gtjkr;
	/**共同借款人手机*/
	@ApiModelProperty(value = "共同借款人手机")
	private String gtjkrsj;
	/**共同借款人证件号码*/
	@ApiModelProperty(value = "共同借款人证件号码")
	private String gtjkrzjhm;
	/**zdqsr*/
    @ApiModelProperty(value = "zdqsr")
	private String zdqsr;
	/**qsrsj*/
    @ApiModelProperty(value = "qsrsj")
	private String qsrsj;
	/**qsrcz*/
    @ApiModelProperty(value = "qsrcz")
	private String qsrcz;
	/**qsryx*/
    @ApiModelProperty(value = "qsryx")
	private String qsryx;
	/**qsrwx*/
    @ApiModelProperty(value = "qsrwx")
	private String qsrwx;
	/**qtqsfs*/
    @ApiModelProperty(value = "qtqsfs")
	private String qtqsfs;
	/**sfsxrhbzxr*/
    @ApiModelProperty(value = "sfsxrhbzxr")
	private String sfsxrhbzxr;
	/**sfyfxxx*/
    @ApiModelProperty(value = "sfyfxxx")
	private String sfyfxxx;
	/**sfrqksm*/
    @ApiModelProperty(value = "sfrqksm")
	private String sfrqksm;
	/**fxxxqksm*/
    @ApiModelProperty(value = "fxxxqksm")
	private String fxxxqksm;
	/**gdzcHj*/
    @ApiModelProperty(value = "gdzcHj")
	private java.math.BigDecimal gdzcHj;
	/**xjck*/
    @ApiModelProperty(value = "xjck")
	private java.math.BigDecimal xjck;
	/**ycl*/
    @ApiModelProperty(value = "ycl")
	private java.math.BigDecimal ycl;
	/**bcp*/
    @ApiModelProperty(value = "bcp")
	private java.math.BigDecimal bcp;
	/**ccp*/
    @ApiModelProperty(value = "ccp")
	private java.math.BigDecimal ccp;
	/**yszk*/
    @ApiModelProperty(value = "yszk")
	private java.math.BigDecimal yszk;
	/**yfzk*/
    @ApiModelProperty(value = "yfzk")
	private java.math.BigDecimal yfzk;
	/**ldzcHj*/
    @ApiModelProperty(value = "ldzcHj")
	private java.math.BigDecimal ldzcHj;
	/**zczeHj*/
    @ApiModelProperty(value = "zczeHj")
	private java.math.BigDecimal zczeHj;
	/**fzzeHj*/
    @ApiModelProperty(value = "fzzeHj")
	private java.math.BigDecimal fzzeHj;
	/**qtjk*/
    @ApiModelProperty(value = "qtjk")
	private java.math.BigDecimal qtjk;
	/**fzyf*/
    @ApiModelProperty(value = "fzyf")
	private java.math.BigDecimal fzyf;
	/**fzys*/
    @ApiModelProperty(value = "fzys")
	private java.math.BigDecimal fzys;
	/**jzcHj*/
    @ApiModelProperty(value = "jzcHj")
	private java.math.BigDecimal jzcHj;
	/**qncz*/
    @ApiModelProperty(value = "qncz")
	private java.math.BigDecimal qncz;
	/**xse*/
    @ApiModelProperty(value = "xse")
	private java.math.BigDecimal xse;
	/**sj*/
    @ApiModelProperty(value = "sj")
	private java.math.BigDecimal sj;
	/**jlr*/
    @ApiModelProperty(value = "jlr")
	private java.math.BigDecimal jlr;
	/**ffgz*/
    @ApiModelProperty(value = "ffgz")
	private java.math.BigDecimal ffgz;
	/**zzdcrcz*/
    @ApiModelProperty(value = "zzdcrcz")
	private java.math.BigDecimal zzdcrcz;
	/**zzdcrlr*/
    @ApiModelProperty(value = "zzdcrlr")
	private java.math.BigDecimal zzdcrlr;
	/**yjjnqncz*/
    @ApiModelProperty(value = "yjjnqncz")
	private java.math.BigDecimal yjjnqncz;
	/**yjjlr*/
    @ApiModelProperty(value = "yjjlr")
	private java.math.BigDecimal yjjlr;
	/**scqj*/
    @ApiModelProperty(value = "scqj")
	private String scqj;
	/**sqje*/
    @ApiModelProperty(value = "sqje")
	private String sqje;
	/**jkyt*/
    @ApiModelProperty(value = "jkyt")
	private String jkyt;
	/**jkqx*/
    @ApiModelProperty(value = "jkqx")
	private String jkqx;
	/**jkfs*/
    @ApiModelProperty(value = "jkfs")
	private String jkfs;
	/**qtjkfs*/
    @ApiModelProperty(value = "qtjkfs")
	private String qtjkfs;
	/**hkfs*/
    @ApiModelProperty(value = "hkfs")
	private String hkfs;
	/**hkjh*/
    @ApiModelProperty(value = "hkjh")
	private String hkjh;
	/**dyhkly*/
    @ApiModelProperty(value = "dyhkly")
	private String dyhkly;
	/**dehkly*/
    @ApiModelProperty(value = "dehkly")
	private String dehkly;
	/**dkfxdjfycs*/
    @ApiModelProperty(value = "dkfxdjfycs")
	private String dkfxdjfycs;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;

	/**sfsxdx*/
    @ApiModelProperty(value = "sfsxdx")
	private String sfsxdx;
	/**hmcId*/
    @ApiModelProperty(value = "hmcId")
	private String hmcId;

	/**ssyxdy*/
    @ApiModelProperty(value = "ssyxdy")
	private String ssyxdy;

	/**yhzgx*/
    @ApiModelProperty(value = "yhzgx")
	private String yhzgx;
	/**sfhz*/
    @ApiModelProperty(value = "sfhz")
	private String sfhz;
	/**khlx*/
    @ApiModelProperty(value = "khlx")
	private String khlx;
	/**mz*/
    @ApiModelProperty(value = "mz")
	private String mz;

	/**jgdm*/
    @ApiModelProperty(value = "jgdm")
	private String jgdm;
	/**csny*/
    @ApiModelProperty(value = "csny")
	private String csny;
	/**hjdz*/
    @ApiModelProperty(value = "hjdz")
	private String hjdz;
	/**status*/
    @ApiModelProperty(value = "status")
	private Integer status;
	/**procDefId*//*
    @ApiModelProperty(value = "procDefId")
	private String procDefId;
	*//**procInstId*//*
    @ApiModelProperty(value = "procInstId")
	private String procInstId;
	*//**bussinessId*//*
    @ApiModelProperty(value = "bussinessId")
	private String bussinessId;
	*//**title*//*
    @ApiModelProperty(value = "title")
	private String title;*/
	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;

	@ApiModelProperty(value = "定期存款余额")
	private java.math.BigDecimal dqckye;

	@ApiModelProperty(value = "当年存款日平")
	private java.math.BigDecimal cknrpye;


	@ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;

	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;

	@ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;

	@ApiModelProperty(value = "表外不良贷款")
	private java.math.BigDecimal bwbldkye;

	@ApiModelProperty(value = "手机银行")
	private java.math.BigDecimal sjyhsl;

	@ApiModelProperty(value = "网上银行")
	private java.math.BigDecimal wsyhsl;

	@ApiModelProperty(value = "社保卡")
	private java.math.BigDecimal sbksl;

	@ApiModelProperty(value = "ETC")
	private java.math.BigDecimal etcsl;

	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;



	@ApiModelProperty(value = "福农卡金额")
	private java.math.BigDecimal fnkjkje;

	@ApiModelProperty(value = "便民卡金额")
	private java.math.BigDecimal bmkjkje;

	@ApiModelProperty(value = "担保金额")
	private java.math.BigDecimal dbjkje;


	@Excel(name = "定价日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "定价日期")
	private Date djrq;
	@ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	@ApiModelProperty(value = "贷款期限")
	private String dkqx;
	@ApiModelProperty(value = "得分合计")
	private java.math.BigDecimal dfhj;
	@ApiModelProperty(value = "LPR基点")
	private java.math.BigDecimal jdbp;
	@ApiModelProperty(value = "优惠后LPR基点")
	private java.math.BigDecimal yhhjdbp;
	@ApiModelProperty(value = "对应档次LPR")
	private java.math.BigDecimal lprll;
	@ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	@ApiModelProperty(value = "优惠后执行利率")
	private java.math.BigDecimal yhhzxll;
	@ApiModelProperty(value = "上年授信的")
	private java.math.BigDecimal snsxed;
	@ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jzll;

	@ApiModelProperty(value = "上年授信总额")
	private java.math.BigDecimal snsxze;
	@ApiModelProperty(value = "上年授信总额中贷款")
	private java.math.BigDecimal snsxzezdk;
	@ApiModelProperty(value = "上年授信总额中便民卡")
	private java.math.BigDecimal snsxzezbmk;
	@ApiModelProperty(value = "上年授信总额中福农卡")
	private java.math.BigDecimal snsxzezfnk;
	@ApiModelProperty(value = "上年授信总额中其他")
	private java.math.BigDecimal snsxzezqt;
	@ApiModelProperty(value = "集体审批时间")
	private String jtspsj;
	@ApiModelProperty(value = "集体审批地点")
	private String jtspdd;
	@ApiModelProperty(value = "集体审批记录人")
	private String jtspjlr;
	@ApiModelProperty(value = "现有用信余额")
	private java.math.BigDecimal xyyxye;
	@ApiModelProperty(value = "现有贷款余额")
	private java.math.BigDecimal xydkye;
	@ApiModelProperty(value = "现有贷款到期日期")
	private String xydkdqrq;
	@ApiModelProperty(value = "现有贷款福农卡余额")
	private java.math.BigDecimal xydkfnkye;
	@ApiModelProperty(value = "现有贷款其他")
	private java.math.BigDecimal xydkqt;
	@ApiModelProperty(value = "现申请授信金额")
	private java.math.BigDecimal xsqsxje;
	@ApiModelProperty(value = "现担保方式")
	@Dict(dicCode = "grdk_dbfs")
	private java.math.BigDecimal xdbfs;
	@ApiModelProperty(value = "现抵押物价值")
	private java.math.BigDecimal xdywjz;
	@ApiModelProperty(value = "参审人员")
	private String csry;
	@ApiModelProperty(value = "审贷记录")
	private String sdjl;
	@ApiModelProperty(value = "授信总额")
	private java.math.BigDecimal sxze;
	@ApiModelProperty(value = "授信期限")
	private java.math.BigDecimal sxqx;
	@ApiModelProperty(value = "利率年期")
	private java.math.BigDecimal llnq;
	@ApiModelProperty(value = "利率基点")
	private java.math.BigDecimal lljd;
	@ApiModelProperty(value = "贷款授信额度")
	private java.math.BigDecimal dksxed;
	@ApiModelProperty(value = "便民卡授信额度")
	private java.math.BigDecimal bmksxed;
	@ApiModelProperty(value = "担保授信额度")
	private java.math.BigDecimal dbsxed;
	@ApiModelProperty(value = "福农卡授信额度")
	private java.math.BigDecimal fnksxed;
	@ApiModelProperty(value = "附加条件")
	private String fjtj;
}
