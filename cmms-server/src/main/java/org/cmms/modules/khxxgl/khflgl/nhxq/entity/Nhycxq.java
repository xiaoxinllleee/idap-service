package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

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
 * @Description: 农户移除表
 * @Author: jeecg-boot
 * @Date:   2023-10-12
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_KHXQ_REMOVE_NH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_REMOVE_NH对象", description="农户移除表")
public class Nhycxq {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15)
    @ApiModelProperty(value = "归属网格")
	private String wgbh;
	/**归属机构*/
	@Excel(name = "归属机构", width = 15)
    @ApiModelProperty(value = "归属机构")
	private String jgdm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**名族*/
	@Excel(name = "名族", width = 15)
    @ApiModelProperty(value = "名族")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15)
    @ApiModelProperty(value = "户口性质")
	private String hkxz;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
	private String yhzgx;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15)
    @ApiModelProperty(value = "文化程度")
	private String whcd;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
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
	@Excel(name = "是否不良贷款客户", width = 15)
    @ApiModelProperty(value = "是否不良贷款客户")
	private String sfbldkh;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15)
    @ApiModelProperty(value = "是否贫困户")
	private String sfpkh;
	/**是否低保户*/
	@Excel(name = "是否低保户", width = 15)
    @ApiModelProperty(value = "是否低保户")
	private String sfdbh;
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
	/**是否外出务工*/
	@Excel(name = "是否外出务工", width = 15)
    @ApiModelProperty(value = "是否外出务工")
	private String sfycdg;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15)
    @ApiModelProperty(value = "客户重要程度")
	private String kfyyqk;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15)
    @ApiModelProperty(value = "客户潜在业务")
	private String kcqzyw;
	/**客户授信情况*/
	@Excel(name = "客户授信情况", width = 15)
    @ApiModelProperty(value = "客户授信情况")
	private String khsxqk;
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
	/**备用号码*/
	@Excel(name = "备用号码", width = 15)
    @ApiModelProperty(value = "备用号码")
	private String byhm;
	/**陪访人*/
	@Excel(name = "陪访人", width = 15)
    @ApiModelProperty(value = "陪访人")
	private String pfr;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15)
    @ApiModelProperty(value = "客户类型1")
	private String khlx1;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15)
    @ApiModelProperty(value = "客户类型2")
	private String khlx2;
	/**有无子女*/
	@Excel(name = "有无子女", width = 15)
    @ApiModelProperty(value = "有无子女")
	private String ywzn;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
	private String jkzk;
	/**劳动能力*/
	@Excel(name = "劳动能力", width = 15)
    @ApiModelProperty(value = "劳动能力")
	private String ldnl;
	/**居住年限*/
	@Excel(name = "居住年限", width = 15)
    @ApiModelProperty(value = "居住年限")
	private String jznx;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15)
    @ApiModelProperty(value = "最高学历")
	private String zgxl;
	/**最高学位*/
	@Excel(name = "最高学位", width = 15)
    @ApiModelProperty(value = "最高学位")
	private String zgxw;
	/**道德品质综合评价*/
	@Excel(name = "道德品质综合评价", width = 15)
    @ApiModelProperty(value = "道德品质综合评价")
	private String ddpzzhpj;
	/**居住状态*/
	@Excel(name = "居住状态", width = 15)
    @ApiModelProperty(value = "居住状态")
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
	/**是否授信（1：是 2：否）*/
	@Excel(name = "是否授信（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否授信（1：是 2：否）")
	private String sfsx;
	/**评议信息有效标识：0-无效，1-有效*/
	@Excel(name = "评议信息有效标识：0-无效，1-有效", width = 15)
    @ApiModelProperty(value = "评议信息有效标识：0-无效，1-有效")
	private String pyxxFlag;
	/**客户基础信息有效标识：0-无效，1-有效*/
	@Excel(name = "客户基础信息有效标识：0-无效，1-有效", width = 15)
    @ApiModelProperty(value = "客户基础信息有效标识：0-无效，1-有效")
	private String khxxFlag;
	/**客户管理有效标识：0-无效，1-有效*/
	@Excel(name = "客户管理有效标识：0-无效，1-有效", width = 15)
    @ApiModelProperty(value = "客户管理有效标识：0-无效，1-有效")
	private String khglFlag;
	/**客户签名标识：0-无效，1-有效*/
	@Excel(name = "客户签名标识：0-无效，1-有效", width = 15)
    @ApiModelProperty(value = "客户签名标识：0-无效，1-有效")
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
	@Excel(name = "是否吸毒人员", width = 15)
    @ApiModelProperty(value = "是否吸毒人员")
	private String sfxdry;
	/**电子签名3*/
	@Excel(name = "电子签名3", width = 15)
    @ApiModelProperty(value = "电子签名3")
	private String sign3;
	/**是否授信对象*/
	@Excel(name = "是否授信对象", width = 15)
    @ApiModelProperty(value = "是否授信对象")
	private String sfsxdx;
	/**采集点（定位地址）*/
	@Excel(name = "采集点（定位地址）", width = 15)
    @ApiModelProperty(value = "采集点（定位地址）")
	private String gddz;
	/**住地邮编*/
	@Excel(name = "住地邮编", width = 15)
    @ApiModelProperty(value = "住地邮编")
	private String zdyb;
	/**不予授信情况*/
	@Excel(name = "不予授信情况", width = 15)
    @ApiModelProperty(value = "不予授信情况")
	private String bysxqx;
	/**采集状态(1:采集户 ，2:收集户， 3:不予授信,)*/
	@Excel(name = "采集状态(1:采集户 ，2:收集户， 3:不予授信,)", width = 15)
    @ApiModelProperty(value = "采集状态(1:采集户 ，2:收集户， 3:不予授信,)")
	private String cjzt;
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
	/**客户类型3*/
	@Excel(name = "客户类型3", width = 15)
    @ApiModelProperty(value = "客户类型3")
	private String khlx3;
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
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
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
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**是否领取社保卡*/
	@Excel(name = "是否领取社保卡", width = 15)
    @ApiModelProperty(value = "是否领取社保卡")
	private String sflqsbk;
	/**是否开通社保卡*/
	@Excel(name = "是否开通社保卡", width = 15)
    @ApiModelProperty(value = "是否开通社保卡")
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
	/**现担任职务*/
	@Excel(name = "现担任职务", width = 15)
    @ApiModelProperty(value = "现担任职务")
	private String xdrzw;
	/**行业类别*/
	@Excel(name = "行业类别", width = 15)
    @ApiModelProperty(value = "行业类别")
	private String hylb;
	/**客户分群*/
	@Excel(name = "客户分群", width = 15)
    @ApiModelProperty(value = "客户分群")
	private String khfq;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
	private String cszy;
	/**从业年限*/
	@Excel(name = "从业年限", width = 15)
    @ApiModelProperty(value = "从业年限")
	private String cynx;
	/**其他外部数据1*/
	@Excel(name = "其他外部数据1", width = 15)
    @ApiModelProperty(value = "其他外部数据1")
	private String qtwbsj1;
	/**其他外部数据2*/
	@Excel(name = "其他外部数据2", width = 15)
    @ApiModelProperty(value = "其他外部数据2")
	private String qtwbsj2;
	/**其他外部数据3*/
	@Excel(name = "其他外部数据3", width = 15)
    @ApiModelProperty(value = "其他外部数据3")
	private String qtwbsj3;
	/**其他外部数据4*/
	@Excel(name = "其他外部数据4", width = 15)
    @ApiModelProperty(value = "其他外部数据4")
	private String qtwbsj4;
	/**其他外部数据5*/
	@Excel(name = "其他外部数据5", width = 15)
    @ApiModelProperty(value = "其他外部数据5")
	private String qtwbsj5;
	/**手机号码（导入）*/
	@Excel(name = "手机号码（导入）", width = 15)
    @ApiModelProperty(value = "手机号码（导入）")
	private String sjhmImport;
	/**预留号码*/
	@Excel(name = "预留号码", width = 15)
    @ApiModelProperty(value = "预留号码")
	private String ylhm;
	/**我行诉讼*/
	@Excel(name = "我行诉讼", width = 15)
    @ApiModelProperty(value = "我行诉讼")
	private String whss;
	/**诈骗人员*/
	@Excel(name = "诈骗人员", width = 15)
    @ApiModelProperty(value = "诈骗人员")
	private String zpry;
	/**非法集资*/
	@Excel(name = "非法集资", width = 15)
    @ApiModelProperty(value = "非法集资")
	private String ffjz;
	/**是否服刑*/
	@Excel(name = "是否服刑", width = 15)
    @ApiModelProperty(value = "是否服刑")
	private String sffx;
	/**贷款真实性*/
	@Excel(name = "贷款真实性", width = 15)
    @ApiModelProperty(value = "贷款真实性")
	private String dkzsx;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String bz1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String bz2;
	/**是否死亡*/
	@Excel(name = "是否死亡", width = 15)
    @ApiModelProperty(value = "是否死亡")
	private String sfsw;
	/**停留时间*/
	@Excel(name = "停留时间", width = 15)
    @ApiModelProperty(value = "停留时间")
	private Long tlsj;
	/**1 实际走访 2电话营销*/
	@Excel(name = "1 实际走访 2电话营销", width = 15)
    @ApiModelProperty(value = "1 实际走访 2电话营销")
	private String zfyxfs;
	/**经营规模*/
	@Excel(name = "经营规模", width = 15)
    @ApiModelProperty(value = "经营规模")
	private String jygm;
	/**1 工资 薪金收入 2 经营收入 3劳务报酬  4 租赁收入 5 大额补助收入 6 其他*/
	@Excel(name = "1 工资 薪金收入 2 经营收入 3劳务报酬  4 租赁收入 5 大额补助收入 6 其他", width = 15)
    @ApiModelProperty(value = "1 工资 薪金收入 2 经营收入 3劳务报酬  4 租赁收入 5 大额补助收入 6 其他")
	private String zysrly;
	/**个人年收入*/
	@Excel(name = "个人年收入", width = 15)
    @ApiModelProperty(value = "个人年收入")
	private String grnsr;
	/**家庭年收入*/
	@Excel(name = "家庭年收入", width = 15)
    @ApiModelProperty(value = "家庭年收入")
	private String jtnsr;
	/**配偶姓名*/
	@Excel(name = "配偶姓名", width = 15)
    @ApiModelProperty(value = "配偶姓名")
	private String poxm;
	/**配偶证件号码*/
	@Excel(name = "配偶证件号码", width = 15)
    @ApiModelProperty(value = "配偶证件号码")
	private String pozjhm;
	/**商户/企业名称*/
	@Excel(name = "商户/企业名称", width = 15)
    @ApiModelProperty(value = "商户/企业名称")
	private String shmc;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private String tyshxydm;
	/**精准营销客户类型*/
	@Excel(name = "精准营销客户类型", width = 15)
    @ApiModelProperty(value = "精准营销客户类型")
	private String jzyxkhlx;
	/**是否完成精准营销（1：是，2：否）*/
	@Excel(name = "是否完成精准营销（1：是，2：否）", width = 15)
    @ApiModelProperty(value = "是否完成精准营销（1：是，2：否）")
	private String sfwcjzyx;
	/**完成精准营销时间*/
	@Excel(name = "完成精准营销时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "完成精准营销时间")
	private Date wcjzyxsj;
	/**就业地点*/
	@Excel(name = "就业地点", width = 15)
    @ApiModelProperty(value = "就业地点")
	private String jydd;
	/**就业地点备注*/
	@Excel(name = "就业地点备注", width = 15)
    @ApiModelProperty(value = "就业地点备注")
	private String jyddbz;
	/**永兴-客户类型2*/
	@Excel(name = "永兴-客户类型2", width = 15)
    @ApiModelProperty(value = "永兴-客户类型2")
	private String khlx2yx;
	/**精准营销完成走访人*/
	@Excel(name = "精准营销完成走访人", width = 15)
    @ApiModelProperty(value = "精准营销完成走访人")
	private String jzyxwczfr;
	/**客户经理签字*/
	@Excel(name = "客户经理签字", width = 15)
    @ApiModelProperty(value = "客户经理签字")
	private String khjlqz;
	/**风险经理签字*/
	@Excel(name = "风险经理签字", width = 15)
    @ApiModelProperty(value = "风险经理签字")
	private String fxjlqz;
	/**支行行长签字*/
	@Excel(name = "支行行长签字", width = 15)
    @ApiModelProperty(value = "支行行长签字")
	private String zhhzqz;
	/**年审分类*/
	@Excel(name = "年审分类", width = 15)
    @ApiModelProperty(value = "年审分类")
	private String nsfl;
	/**年审分类原因*/
	@Excel(name = "年审分类原因", width = 15)
    @ApiModelProperty(value = "年审分类原因")
	private String nsflyy;
}
