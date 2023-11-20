package org.cmms.modules.khxxgl.khflgl.shxq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;

/**
 * @Description: 商户户采集信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_KHXQ_SH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_SH对象", description="商户户采集信息")
public class Shxq {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15,dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
    @ApiModelProperty(value = "归属网格")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private java.lang.String wgbh;
	/**归属机构*/
	@Excel(name = "归属机构", width = 15,dicCode ="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "归属机构")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private java.lang.String jgdm;

	/**所属支行*/
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;

	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private java.lang.String shmc;
	/**法定代表人*/
	@Excel(name = "法定代表人", width = 15)
    @ApiModelProperty(value = "法定代表人")
	private java.lang.String fddbr;
	/**法人证件号码*/
	@Excel(name = "法人证件号码", width = 15)
    @ApiModelProperty(value = "法人证件号码")
	private java.lang.String frzjhm;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	@ExcelVerify(notNull = true)
	private java.lang.String tyshxydm;
	/**营业执照*/
	@Excel(name = "营业执照", width = 15)
    @ApiModelProperty(value = "营业执照")
	private java.lang.String yyzz;
	/**纳税人识别号*/
	@Excel(name = "纳税人识别号", width = 15)
    @ApiModelProperty(value = "纳税人识别号")
	private java.lang.String nsrsbh;
	/**组织机构代码*/
	@Excel(name = "组织机构代码", width = 15)
    @ApiModelProperty(value = "组织机构代码")
	private java.lang.String zzjgdm;
	/**注册号*/
	@Excel(name = "注册号", width = 15)
    @ApiModelProperty(value = "注册号")
	private java.lang.String zch;
	/**经营状态*/
	@Excel(name = "经营状态", width = 15,dicCode = "jyzt")
    @ApiModelProperty(value = "经营状态")
	@Dict(dicCode = "jyzt")
	private java.lang.String jyzt;
	/**注册资本*/
	@Excel(name = "注册资本", width = 15)
    @ApiModelProperty(value = "注册资本")
	private java.lang.String zczb;
	/**成立日期*/
	@Excel(name = "成立日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "成立日期")
	private java.util.Date clrq;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private java.lang.String lxfs;
	/**更多联系方式*/
	@Excel(name = "更多联系方式", width = 15)
    @ApiModelProperty(value = "更多联系方式")
	private java.lang.String gdlxfs;
	/**邮箱地址*/
	@Excel(name = "邮箱地址", width = 15)
    @ApiModelProperty(value = "邮箱地址")
	private java.lang.String yxdz;
	/**更多邮箱地址*/
	@Excel(name = "更多邮箱地址", width = 15)
    @ApiModelProperty(value = "更多邮箱地址")
	private java.lang.String gdyxdz;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private java.lang.String dz;
	/**是否走访*/
	@Excel(name = "是否走访", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否走访")
	@Dict(dicCode = "sfbz")
	private String sfzf;

	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;
	/**xgr*/
	@Excel(name = "走访人", width = 15, dicCode = "username", dictTable = "sys_user", dicText = "realname")
	@ApiModelProperty(value = "走访人")
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String xgr;
	/**xgsj*/
	@Excel(name = "走访时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "走访时间")
	private Date xgsj;
	/**参保人数*/
	@Excel(name = "参保人数", width = 15)
    @ApiModelProperty(value = "参保人数")
	private java.lang.Integer cbrs;
	/**商户类型*/
	@Excel(name = "商户类型", width = 15,dicCode = "qylx")
    @ApiModelProperty(value = "商户类型")
	@Dict(dicCode = "qylx")
	private java.lang.String shlx;
	/**所属行业*/
	@Excel(name = "所属行业", width = 15)
    @ApiModelProperty(value = "所属行业")
	//@Dict(dicCode = "cshyfl")
	private java.lang.String sshy;
	/**曾用名*/
	@Excel(name = "曾用名", width = 15)
    @ApiModelProperty(value = "曾用名")
	private java.lang.String cym;
	/**网址*/
	@Excel(name = "网址", width = 15)
    @ApiModelProperty(value = "网址")
	private java.lang.String wz;
	/**经营范围*/
	@Excel(name = "经营范围", width = 15)
    @ApiModelProperty(value = "经营范围")
	private java.lang.String jyfw;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String bz;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
//	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
//	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**档案编号*/
	@Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
	private java.lang.String dabh;


	/**系统评定结果*/
	@Excel(name = "系统评定结果", width = 15)
	@ApiModelProperty(value = "系统评定结果")
	private java.lang.String xtpdjg;


	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
	@ApiModelProperty(value = "系统评定说明")
	private java.lang.String xtpdsm;

	/**建档完整度*/
	@Excel(name = "建档完整度", width = 15)
	@ApiModelProperty(value = "建档完整度")
	private java.math.BigDecimal infoRate;



	/**经度*/
	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
	@ApiModelProperty(value = "纬度")
	private String latitude;
	/**不予授信情形*/
	@Excel(name = "不予授信情形", width = 15)
	@ApiModelProperty(value = "不予授信情形")
	private String bysxqx;
	/**授信对象证件号*/
	@Excel(name = "授信对象证件号", width = 15)
	@ApiModelProperty(value = "授信对象证件号")
	private String sxdxzjh;
	/**授信对象名称*/
	@Excel(name = "授信对象名称", width = 15)
	@ApiModelProperty(value = "授信对象名称")
	private String sxdxmc;
	/**资产类型*/
	@Excel(name = "资产类型", width = 15)
	@ApiModelProperty(value = "资产类型")
	private String zclx;
	/**资产评估价*/
	@Excel(name = "资产评估价", width = 15)
	@ApiModelProperty(value = "资产评估价")
	private String zcpgj;
	/**lrsj*/
//	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrr*/
//	@Excel(name = "lrr", width = 15)
	@ApiModelProperty(value = "lrr")
	private String lrr;

	/**总行陪访人*/
	@Excel(name = "总行陪访人", width = 15)
	@ApiModelProperty(value = "总行陪访人")
	private String zhpfr;
	/**陪访人*/
	@Excel(name = "陪访人", width = 15)
	@ApiModelProperty(value = "陪访人")
	private String pfr;
	/**服务跟踪人*/
	@Excel(name = "服务跟踪人", width = 15)
	@ApiModelProperty(value = "服务跟踪人")
	private String fwgzr;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15)
	@ApiModelProperty(value = "客户潜在业务")
	private String khqzyw;

	/**成立日期*/
	@Excel(name = "有效日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "有效日期")
	private java.util.Date yxrq;

	/**贷款需求*/
	@Excel(name = "贷款需求", width = 15, dicCode = "ywbz")
	@ApiModelProperty(value = "贷款需求")
	@Dict(dicCode = "ywbz")
	private String dkxq;

	/**贷款需求额度(万元)*/
	@Excel(name = "贷款需求额度(万元)", width = 15)
	@ApiModelProperty(value = "贷款需求额度(万元)")
	private java.math.BigDecimal dkxqed;
	/**商户规模人数*/
	@Excel(name = "商户规模人数", width = 15)
	@ApiModelProperty(value = "商户规模人数")
	private java.lang.Integer shgmrs;
	/**年营收入(万元)*/
	@Excel(name = "年营收入(万元)", width = 15)
	@ApiModelProperty(value = "年营收入(万元)")
	private java.math.BigDecimal nysr;
	/**商户分类*/
	@Excel(name = "商户分类", width = 15,dicCode = "shgl_shfl")
	@ApiModelProperty(value = "商户分类")
	@Dict(dicCode = "shgl_shfl")
	private String shfl;
	/**停留时间*/
//	@Excel(name = "停留时间", width = 15)
	@ApiModelProperty(value = "停留时间")
	private Integer tlsj;

	/**永兴新增*/
	/**是否法人经营*/
	@Excel(name = "是否法人经营", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否法人经营")
	@Dict(dicCode = "sfbz")
	private String sffrjy;
	/**实际经营者*/
	@Excel(name = "实际经营者", width = 15)
	@ApiModelProperty(value = "实际经营者")
	private String sjjyz;
	/**实际经营者证件号码*/
	@Excel(name = "实际经营者证件号码", width = 15)
	@ApiModelProperty(value = "实际经营者证件号码")
	private String sjjyzzjhm;
	/**实际经营者手机号码*/
	@Excel(name = "实际经营者手机号码", width = 15)
	@ApiModelProperty(value = "实际经营者手机号码")
	private String sjjyzsjhm;
	/**实际经营状态*/
	@Excel(name = "实际经营状态", width = 15,dicCode = "jyzt")
	@ApiModelProperty(value = "实际经营状态")
	@Dict(dicCode = "jyzt")
	private String sjjyzt;
	/**可授信额度*/
	@Excel(name = "可授信额度", width = 15)
	@ApiModelProperty(value = "可授信额度")
	private String ksxed;
	/**采集人*/
	@Excel(name = "采集人", width = 15)
	@ApiModelProperty(value = "采集人")
	private String cjr;
	/**采集时间*/
	@Excel(name = "采集时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "采集时间")
	private java.util.Date cjsj;
	/**双峰-第一走访人*/
	@Excel(name = "第一走访人", width = 15)
	@ApiModelProperty(value = "第一走访人")
	private String dyzfr;
	/**双峰-第二走访人*/
	@Excel(name = "第二走访人", width = 15)
	@ApiModelProperty(value = "第二走访人")
	private String dezfr;
	/**永兴-有效日期是否长期*/
	@ApiModelProperty(value = "有效日期是否长期")
	private String yxrqsfcq;
	/**其他贷款信息*/
	@ApiModelProperty(value = "其他贷款信息")
	private String qtdkxx;
	/**其他贷款备注*/
	@ApiModelProperty(value = "其他贷款备注")
	private String qtdkbz;
}
