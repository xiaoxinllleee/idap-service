package org.cmms.modules.pad.nhxxgl.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-25
 * @Version: V1.0
 */
@Data
@TableName("cams_zcsx_nhcjxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cams_zcsx_nhcjxx对象", description="1")
public class CamsZcsxNhcjxxPad {
    
	/**是否授信对象*/
	@Excel(name = "是否授信对象", width = 15)
    @ApiModelProperty(value = "是否授信对象")
	private String sfsxdx;

	/**采集点（定位地址）*/
	@Excel(name = "采集点（定位地址）", width = 15)
    @ApiModelProperty(value = "采集点（定位地址）")
	private String gddz;
	/** ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = " ID")
	private String id;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15)
    @ApiModelProperty(value = "文化程度")
	private String whcd;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15)
    @ApiModelProperty(value = "户口性质")
	private String hkxz;
	/**住址*/
	@Excel(name = "住址", width = 15)
    @ApiModelProperty(value = "住址")
	private String zz;
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
	private String zdyb;
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
	/**从事行业工作*/
	@Excel(name = "从事行业工作", width = 15)
    @ApiModelProperty(value = "从事行业工作")
	private String cshygz;
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
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
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
	/**zdyzbm*/
	@Excel(name = "zdyzbm", width = 15)
    @ApiModelProperty(value = "zdyzbm")
	private String zdyzbm;
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
	/**upDt*/
	@Excel(name = "upDt", width = 15)
    @ApiModelProperty(value = "upDt")
	private String upDt;
	/**upTm*/
	@Excel(name = "upTm", width = 15)
    @ApiModelProperty(value = "upTm")
	private String upTm;
	/**ddpzzhpj1*/
	@Excel(name = "ddpzzhpj1", width = 15)
    @ApiModelProperty(value = "ddpzzhpj1")
	private String ddpzzhpj1;
	/**ddpzzhpj2*/
	@Excel(name = "ddpzzhpj2", width = 15)
    @ApiModelProperty(value = "ddpzzhpj2")
	private String ddpzzhpj2;
	/**khblsh*/
	@Excel(name = "khblsh", width = 15)
    @ApiModelProperty(value = "khblsh")
	private String khblsh;
	/**qzywyysj*/
	@Excel(name = "qzywyysj", width = 15)
    @ApiModelProperty(value = "qzywyysj")
	private String qzywyysj;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;
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
	/**电子签名3*/
	@Excel(name = "电子签名3", width = 15)
    @ApiModelProperty(value = "电子签名3")
	private String sign3;
	/**系统评定等级*/
//	@Excel(name = "系统评定等级", width = 15)
//    @ApiModelProperty(value = "系统评定等级")
//	private String xtpddj;
//	/**系统评定得分*/
//	@Excel(name = "系统评定得分", width = 15)
//    @ApiModelProperty(value = "系统评定得分")
//	private java.math.BigDecimal xtpddf;
//	/**系统授信额度*/
//	@Excel(name = "系统授信额度", width = 15)
//    @ApiModelProperty(value = "系统授信额度")
//	private java.math.BigDecimal xtsxed;
//	/**最终评定等级*/
//	@Excel(name = "最终评定等级", width = 15)
//    @ApiModelProperty(value = "最终评定等级")
//	private String zzpddj;
//	/**最终评定得分*/
//	@Excel(name = "最终评定得分", width = 15)
//    @ApiModelProperty(value = "最终评定得分")
//	private java.math.BigDecimal zzpddf;
//	/**最终授信额度*/
//	@Excel(name = "最终授信额度", width = 15)
//    @ApiModelProperty(value = "最终授信额度")
//	private java.math.BigDecimal zzsxed;
	/**婚姻状况*/
//	@Excel(name = "婚姻状况", width = 15)
//    @ApiModelProperty(value = "婚姻状况")
//	private String hyzk;
	/**qq*/
	@Excel(name = "qq", width = 15)
    @ApiModelProperty(value = "qq")
	private String qq;
	/**务工城市*/
	@Excel(name = "务工城市", width = 15)
    @ApiModelProperty(value = "务工城市")
	private String wgcs;
	/**是否吸毒人员*/
	@Excel(name = "是否吸毒人员", width = 15)
    @ApiModelProperty(value = "是否吸毒人员")
	private String sfxdry;
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
}
