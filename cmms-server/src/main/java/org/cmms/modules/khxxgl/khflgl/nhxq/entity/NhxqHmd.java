package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 农户信息_黑名单
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_KHXQ_NH_HMD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_NH对象", description="农户信息")
public class NhxqHmd {

	@Excel(name = "是否死亡", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否死亡")
	@Dict(dicCode = "sfbz")
	private String sfsw;
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	@ApiModelProperty(value = "归属网格")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String wgbh;
	/**归属机构*/
	@Excel(name = "归属机构", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "归属机构")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;

	/**所属支行*/
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;

	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "clkhlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "clkhlx")
	private String khlx;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	@ExcelVerify(notNull = true)
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String zjhm;
	/**性别*/
	@Excel(name = "性别", width = 15)
	@ApiModelProperty(value = "性别")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
	@ApiModelProperty(value = "年龄")
	private String nl;

	/**民族*/
	@Excel(name = "民族", width = 15,dicCode = "mz")
	@ApiModelProperty(value = "民族")
	@Dict(dicCode = "mz")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15,dicCode = "hyzk")
	@ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hyzk")
	private String hyzk;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15,dicCode = "khgl_hkxz")
	@ApiModelProperty(value = "户口性质")
	@Dict(dicCode = "khgl_hkxz")
	private String hkxz;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15,dicCode = "yhzgx")
	@ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15,dicCode = "whcd")
	@ApiModelProperty(value = "文化程度")
	@Dict(dicCode = "whcd")
	private String whcd;
	/**职业*/
	@Excel(name = "职业", width = 15,dicCode = "cszy")
	@ApiModelProperty(value = "职业")
	@Dict(dicCode = "cszy")
	private String cshygz;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String sjhm;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;
	/**常住地址*/
	@Excel(name = "常住地址", width = 15)
    @ApiModelProperty(value = "常住地址")
	private String zz;
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
	/**住址1*/
	@Excel(name = "住址1", width = 15)
    @ApiModelProperty(value = "住址1")
	private String zz1;
	/**住址2*/
	@Excel(name = "住址2", width = 15)
    @ApiModelProperty(value = "住址2")
	private String zz2;
	/**是否不良贷款客户*/
	@Excel(name = "是否不良贷款客户", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否不良贷款客户")
	@Dict(dicCode = "sfbz")
	private String sfbldkh;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否贫困户")
	@Dict(dicCode = "sfbz")
	private String sfpkh;
	/**是否低保户*/
	@Excel(name = "是否低保户", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否低保户")
	@Dict(dicCode = "sfbz")
	private String sfdbh;
	/**是否领取社保卡（1：是 2：否）*/
	@Excel(name = "是否领取社保卡（1：是 2：否）", width = 15)
	@ApiModelProperty(value = "是否领取社保卡（1：是 2：否）")
	private String sflqsbk;
	/**是否开通社保卡（1：是 2：否）*/
	@Excel(name = "是否开通社保卡（1：是 2：否）", width = 15)
	@ApiModelProperty(value = "是否开通社保卡（1：是 2：否）")
	private String sfktsbk;
	/**患病记录*/
	@Excel(name = "患病记录", width = 15)
	@ApiModelProperty(value = "患病记录")
	private String hbjl;
	/**是否非法集资*/
	@Excel(name = "是否非法集资", width = 15)
	@ApiModelProperty(value = "是否非法集资")
	private String sfffjz;
	/**是否公职人员*/
	@Excel(name = "是否公职人员", width = 15)
	@ApiModelProperty(value = "是否公职人员")
	private String sfgzry;
	/**是否外出务工*/
	@Excel(name = "是否外出务工", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否外出务工")
	@Dict(dicCode = "sfbz")
	private String sfycdg;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15,dicCode = "khgl_khzycd")
    @ApiModelProperty(value = "客户重要程度")
	@Dict(dicCode = "khgl_khzycd")
	private String kfyyqk;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15,dicCode = "kcqzyw")
    @ApiModelProperty(value = "客户潜在业务")
	@Dict(dicCode = "kcqzyw")
	private String kcqzyw;
	/**客户授信情况*/
	@Excel(name = "客户授信情况", width = 15,dicCode = "khgl_sxqk")
    @ApiModelProperty(value = "客户授信情况")
	@Dict(dicCode = "khgl_sxqk")
	private String khsxqk;
	/**备用号码*/
	@Excel(name = "备用号码", width = 15)
	@ApiModelProperty(value = "备用号码")
	private String byhm;
	/**陪访人*/
	@Excel(name = "陪访人", width = 15)
	@ApiModelProperty(value = "陪访人")
	private String pfr;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15,dicCode = "khlx1")
	@ApiModelProperty(value = "客户类型1")
	@Dict(dicCode = "khlx1")
	private String khlx1;

	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15,dicCode = "khlx2")
	@ApiModelProperty(value = "客户类型2")
	@Dict(dicCode = "khlx2")
	private String khlx2;

	/**客户类型3*/
	@Excel(name = "客户类型3", width = 15,dicCode = "khlx3")
	@ApiModelProperty(value = "客户类型3")
	@Dict(dicCode = "khlx3")
	private String khlx3;

	/**有无子女*/
	@Excel(name = "有无子女", width = 15,dicCode = "ywbz")
	@ApiModelProperty(value = "有无子女")
	@Dict(dicCode = "ywbz")
	private String ywzn;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15,dicCode = "jkzk")
	@ApiModelProperty(value = "健康状况")
	@Dict(dicCode = "jkzk")
	private String jkzk;
	/**劳动能力*/
	@Excel(name = "劳动能力", width = 15,dicCode = "ldnl")
	@ApiModelProperty(value = "劳动能力")
	@Dict(dicCode = "ldnl")
	private String ldnl;
	/**居住年限*/
	@Excel(name = "居住年限", width = 15,dicCode = "jznx")
	@ApiModelProperty(value = "居住年限")
	@Dict(dicCode = "jznx")
	private String jznx;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15,dicCode = "xl")
	@ApiModelProperty(value = "最高学历")
	@Dict(dicCode = "xl")
	private String zgxl;
	/**最高学位*/
	@Excel(name = "最高学位", width = 15,dicCode = "zgxw")
	@ApiModelProperty(value = "最高学位")
	@Dict(dicCode = "zgxw")
	private String zgxw;

	/**道德品质综合评价*/
	@Excel(name = "道德品质综合评价", width = 15,dicCode = "ddpzzhpj")
	@ApiModelProperty(value = "道德品质综合评价")
	@Dict(dicCode = "ddpzzhpj")
	private String ddpzzhpj;
	/**居住状态*/
	@Excel(name = "居住状态", width = 15,dicCode = "jzzk")
	@ApiModelProperty(value = "居住状态")
	@Dict(dicCode = "jzzk")
	private String jzzt;
	/**证件签发日期*/
	@Excel(name = "证件签发日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "证件签发日期")
	private Date zjqfrq;
	/**证件到期日期*/
	@Excel(name = "证件到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "证件到期日期")
	private Date zjdqrq;
	/**返乡时间*/
	@Excel(name = "返乡时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "返乡时间")
	private Date fxsj;
	/**是否授信*/
	@Excel(name = "是否授信", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否授信")
	@Dict(dicCode = "sfbz")
	private String sfsx;
	/**行业类别*/
	@Excel(name = "行业类别", width = 15,dicCode = "app_hylb")
	@ApiModelProperty(value = "行业类别")
	@Dict(dicCode = "app_hylb")
	private String hylb;
	/**评议信息有效标识：0-无效，1-有效*/
	@Excel(name = "评议信息有效标识", width = 15,dicCode = "yxbz")
	@ApiModelProperty(value = "评议信息有效标识：0-无效，1-有效")
	@Dict(dicCode = "yxbz")
	private String pyxxFlag;

	/**客户基础信息有效标识：0-无效，1-有效*/
	@Excel(name = "客户基础信息有效标识", width = 15,dicCode = "yxbz")
	@ApiModelProperty(value = "客户基础信息有效标识")
	@Dict(dicCode = "yxbz")
	private String khxxFlag;
	/**客户管理有效标识：0-无效，1-有效*/
	@Excel(name = "客户管理有效标识", width = 15,dicCode = "yxbz")
	@ApiModelProperty(value = "客户管理有效标识")
	@Dict(dicCode = "yxbz")
	private String khglFlag;
	/**客户签名标识：0-无效，1-有效*/
	@Excel(name = "客户签名标识", width = 15,dicCode = "yxbz")
	@ApiModelProperty(value = "客户签名标识")
	@Dict(dicCode = "yxbz")
	private String signFlag;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15)
	@ApiModelProperty(value = "所属客户经理")
	private String sskhjl;
	/**工作单位*/
	@Excel(name = "工作单位", width = 15)
	@ApiModelProperty(value = "工作单位")
	private String gzdw;
	/**工作单位地址*/
	@Excel(name = "工作单位地址", width = 15)
	@ApiModelProperty(value = "工作单位地址")
	private String gzdwdz;
	/**腾讯QQ号码*/
	@Excel(name = "腾讯QQ号码", width = 15)
	@ApiModelProperty(value = "腾讯QQ号码")
	private String qq;
	/**务工城市*/
	@Excel(name = "务工城市", width = 15)
	@ApiModelProperty(value = "务工城市")
	private String wgcs;
	/**是否吸毒人员*/
	@Excel(name = "是否吸毒人员", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否吸毒人员")
	@Dict(dicCode = "sfbz")
	private String sfxdry;
	/**电子签名3*/
	@Excel(name = "电子签名3", width = 15)
	@ApiModelProperty(value = "电子签名3")
	private String sign3;
	/**是否授信对象*/
	@Excel(name = "是否授信对象", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否授信对象")
	@Dict(dicCode = "sfbz")
	private String sfsxdx;
	/**采集点（定位地址）*/
	@Excel(name = "采集点（定位地址）", width = 15)
	@ApiModelProperty(value = "采集点（定位地址）")
	private String gddz;
	/**住地邮编*/
	@Excel(name = "住地邮编", width = 15)
	@ApiModelProperty(value = "住地邮编")
	private String zdyb;

	/**收集户原因*/
	@Excel(name = "收集户原因", width = 15)
	@ApiModelProperty(value = "收集户原因")
	private String sjhyy;
	/**上传状态*/
	@Excel(name = "上传状态", width = 15)
	@ApiModelProperty(value = "上传状态")
	private String cjwczt;
	/**具体从事经营项目*/
	@Excel(name = "具体从事经营项目", width = 15)
	@ApiModelProperty(value = "具体从事经营项目")
	private String jtcsxm;
	/**务工地区*/
	@Excel(name = "务工地区", width = 15)
	@ApiModelProperty(value = "务工地区")
	private String wgdq;
	/**务工类型*/
	@Excel(name = "务工类型", width = 15)
	@ApiModelProperty(value = "务工类型")
	private String wglx;



	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
	private String latitude;
	/**电子签名1*/
	@Excel(name = "电子签名1", width = 15)
    @ApiModelProperty(value = "电子签名1")
	private String sign1;
	/**电子签名2*/
	@Excel(name = "电子签名2", width = 15)
    @ApiModelProperty(value = "电子签名2")
	private String sign2;


	/**不予授信情况*/
	@Excel(name = "不予授信情况", width = 15)
	@ApiModelProperty(value = "不予授信情况")
	private String bysxqx;
	/**采集状态(1:采集户 ，2:收集户， 3:不予授信,)*/
	@Excel(name = "采集状态(1:采集户 ，2:收集户， 3:不予授信,)", width = 15)
	@ApiModelProperty(value = "采集状态(1:采集户 ，2:收集户， 3:不予授信,)")
	private String cjzt;

	/**系统评定结果：1-灰名单，2-白名单，3-黑名单*/
	@Excel(name = "系统评定结果：1-灰名单，2-白名单，3-黑名单", width = 15)
	@ApiModelProperty(value = "系统评定结果：1-灰名单，2-白名单，3-黑名单")
	private String xtpdjg;
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
	@ApiModelProperty(value = "系统评定说明")
	private String xtpdsm;
	/**关键人评定结果*/
	@Excel(name = "关键人评定结果", width = 15)
	@ApiModelProperty(value = "关键人评定结果")
	private String gjrpdjg;
	/**关键人评定说明*/
	@Excel(name = "关键人评定说明", width = 15)
	@ApiModelProperty(value = "关键人评定说明")
	private String gjrpdsm;
	/**最终评定结果*/
	@Excel(name = "最终评定结果", width = 15)
	@ApiModelProperty(value = "最终评定结果")
	private String zzpdjg;
	/**最终评定说明*/
	@Excel(name = "最终评定说明", width = 15)
	@ApiModelProperty(value = "最终评定说明")
	private String zzpdsm;


	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;

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
	/**upDt*/
	@Excel(name = "upDt", width = 15)
    @ApiModelProperty(value = "upDt")
	private String upDt;
	/**upTm*/
	@Excel(name = "upTm", width = 15)
    @ApiModelProperty(value = "upTm")
	private String upTm;
	/**zdyzbm*/
	@Excel(name = "zdyzbm", width = 15)
    @ApiModelProperty(value = "zdyzbm")
	private String zdyzbm;
	/**khblsh*/
	@Excel(name = "khblsh", width = 15)
    @ApiModelProperty(value = "khblsh")
	private String khblsh;
	/**ddpzzhpj1*/
	@Excel(name = "ddpzzhpj1", width = 15)
    @ApiModelProperty(value = "ddpzzhpj1")
	private String ddpzzhpj1;
	/**ddpzzhpj2*/
	@Excel(name = "ddpzzhpj2", width = 15)
    @ApiModelProperty(value = "ddpzzhpj2")
	private String ddpzzhpj2;
	/**qzywyysj*/
	@Excel(name = "qzywyysj", width = 15)
    @ApiModelProperty(value = "qzywyysj")
	private String qzywyysj;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
	@ApiModelProperty(value = "是否户主")
	private String sfhz;

	@Excel(name = "现担任职务", width = 15,dicCode = "app_xdrzw")
	@ApiModelProperty(value = "现担任职务")
	@Dict(dicCode = "app_xdrzw")
	private String xdrzw;

	@Excel(name = "客户分群", width = 15,dicCode = "app_khfq")
	@ApiModelProperty(value = "客户分群")
	@Dict(dicCode = "app_khfq")
	private String khfq;

	@Excel(name = "职业", width = 15,dicCode = "app_zc")
	@ApiModelProperty(value = "职业")
	@Dict(dicCode = "app_zc")
	private String cszy;

	@Excel(name = "从业年限", width = 15,dicCode = "cynx")
	@ApiModelProperty(value = "从业年限")
	@Dict(dicCode = "cynx")
	private String cynx;

	@Excel(name = "其他外部数据1", width = 15)
	@ApiModelProperty(value = "其他外部数据1")
	@TableField(value = "QTWBSJ1")
	private String qtwbsj1;

	@Excel(name = "其他外部数据2", width = 15)
	@ApiModelProperty(value = "其他外部数据2")
	@TableField(value = "QTWBSJ2")
	private String qtwbsj2;

	@Excel(name = "其他外部数据3", width = 15)
	@ApiModelProperty(value = "其他外部数据3")
	@TableField(value = "QTWBSJ3")
	private String qtwbsj3;

	@Excel(name = "其他外部数据4", width = 15)
	@ApiModelProperty(value = "其他外部数据4")
	@TableField(value = "QTWBSJ4")
	private String qtwbsj4;

	@Excel(name = "其他外部数据5", width = 15)
	@ApiModelProperty(value = "其他外部数据5")
	@TableField(value = "QTWBSJ5")
	private String qtwbsj5;

	@Excel(name = "手机号码(导入)", width = 15)
	@ApiModelProperty(value = "手机号码(导入)")
	@TableField(value = "sjhm_import")
	private String sjhmImport;

	@Excel(name = "预留号码", width = 15)
	@ApiModelProperty(value = "预留号码")
	@TableField(value = "YLHM")
	private String ylhm;

	@Excel(name = "我行诉讼", width = 15)
	@ApiModelProperty(value = "我行诉讼")
	@TableField(value = "WHSS")
	private String whss;

	@Excel(name = "诈骗人员", width = 15)
	@ApiModelProperty(value = "诈骗人员")
	@TableField(value = "ZPRY")
	private String zpry;

	@Excel(name = "非法集资", width = 15)
	@ApiModelProperty(value = "非法集资")
	@TableField(value = "FFJZ")
	private String ffjz;

	@Excel(name = "是否服刑", width = 15)
	@ApiModelProperty(value = "是否服刑")
	@TableField(value = "SFFX")
	@Dict(dicCode = "sfbz")
	private String sffx;

}
