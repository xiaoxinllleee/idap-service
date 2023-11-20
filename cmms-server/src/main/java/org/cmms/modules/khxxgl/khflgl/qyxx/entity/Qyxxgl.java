package org.cmms.modules.khxxgl.khflgl.qyxx.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 企业信息
 * @Author: jeecg-boot
 * @Date:   2022-11-02
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_QYXQ_QY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_QYXQ_QY对象", description="企业信息")
public class Qyxxgl {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**归属网格*/
	@Excel(name = "所属网格", width = 15,dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	@ApiModelProperty(value = "所属网格")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private java.lang.String wgbh;
	/**归属机构*/
	@Excel(name = "归属机构", width = 15,dicCode ="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "归属机构")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private java.lang.String jgdm;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;

	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
	private String qymc;
	/**法定代表人*/
	@Excel(name = "法定代表人/负责人姓名", width = 15)
    @ApiModelProperty(value = "法定代表人/负责人姓名")
	private String fddbr;
	/**法人证件号码*/
	@Excel(name = "法人证件号码", width = 15)
    @ApiModelProperty(value = "法人证件号码")
	private String frzjhm;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private String tyshxydm;
	/**营业执照*/
	@Excel(name = "营业执照", width = 15)
    @ApiModelProperty(value = "营业执照")
	private String yyzz;
	/**纳税人识别号*/
	@Excel(name = "纳税人识别号", width = 15)
    @ApiModelProperty(value = "纳税人识别号")
	private String nsrsbh;
	/**组织机构代码*/
	@Excel(name = "组织机构代码", width = 15)
    @ApiModelProperty(value = "组织机构代码")
	private String zzjgdm;
	/**注册号*/
	@Excel(name = "注册号", width = 15)
    @ApiModelProperty(value = "注册号")
	private String zch;
	/**经营状态*/
	@Excel(name = "状态", width = 15,dicCode = "jyzt_qd")
    @ApiModelProperty(value = "状态")
	@Dict(dicCode = "jyzt_qd")
	private String jyzt;
	/**住所*/
	@Excel(name = "住所", width = 15)
	@ApiModelProperty(value = "住所")
	private String zs;
	/**联络员姓名*/
	@Excel(name = "联络员姓名", width = 15)
	@ApiModelProperty(value = "联络员姓名")
	private String llyxm;
	/**联络员证件号码*/
	@Excel(name = "联络员证件号码", width = 15)
	@ApiModelProperty(value = "联络员证件号码")
	private String llyzjhm;
	/**注册资本*/
	@Excel(name = "注册资本(万元)", width = 15)
    @ApiModelProperty(value = "注册资本(万元)")
	private String zczb;
	/**营业期限*/
	@Excel(name = "营业期限", width = 15)
	@ApiModelProperty(value = "营业期限")
	private String yyqx;
	/**登记机关*/
	@Excel(name = "登记机关", width = 15)
	@ApiModelProperty(value = "登记机关")
	private String djjg;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
	@ApiModelProperty(value = "邮政编码")
	private String yzbm;
	/**监管单位*/
	@Excel(name = "监管单位", width = 15)
	@ApiModelProperty(value = "监管单位")
	private String jgdw;
	/**行业代码*/
	@Excel(name = "行业代码", width = 15)
	@ApiModelProperty(value = "行业代码")
	private String hydm;
	/**注册时间*/
	@Excel(name = "成立日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "成立日期")
	private Date zcsj;
	/**注册到期日*/
	@Excel(name = "核准日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "核准日期")
	private Date hzrq;
	/**企业联系电话*/
	@Excel(name = "企业联系电话", width = 15)
    @ApiModelProperty(value = "企业联系电话")
	private String qylxdh;
	/**法人联系电话*/
	@Excel(name = "法人联系电话", width = 15)
    @ApiModelProperty(value = "法人联系电话")
	private String frlxdh;
	/**邮箱地址*/
	@Excel(name = "邮箱地址", width = 15)
    @ApiModelProperty(value = "邮箱地址")
	private String yxdz;
	/**更多邮箱地址*/
	@Excel(name = "更多邮箱地址", width = 15)
    @ApiModelProperty(value = "更多邮箱地址")
	private String gdyxdz;
	/**注册地址*/
	@Excel(name = "注册地址", width = 15)
    @ApiModelProperty(value = "注册地址")
	private String zcdz;
	/**参保人数*/
	@Excel(name = "参保人数", width = 15)
    @ApiModelProperty(value = "参保人数")
	private Integer cbrs;
	/**登记类别*/
	@Excel(name = "登记类别", width = 15)
	@ApiModelProperty(value = "登记类别")
	private String djlb;

	/**企业类型*/
	@Excel(name = "企业类型", width = 15,dicCode = "qylx_qd")
    @ApiModelProperty(value = "企业类型")
	@Dict(dicCode = "qylx_qd")
	private String qylx;
	/**所属行业*/
	@Excel(name = "所属行业", width = 15)
    @ApiModelProperty(value = "所属行业")
	private String sshy;
	/**曾用名*/
	@Excel(name = "曾用名", width = 15)
    @ApiModelProperty(value = "曾用名")
	private String cym;
	/**网址*/
	@Excel(name = "网址", width = 15)
    @ApiModelProperty(value = "网址")
	private String wz;
	/**经营范围*/
	@Excel(name = "经营范围", width = 15)
    @ApiModelProperty(value = "经营范围")
	private String jyfw;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**档案编号*/
	@Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
	private String dabh;
	/**建档完整度*/
	@Excel(name = "建档完整度", width = 15)
    @ApiModelProperty(value = "建档完整度")
	private java.math.BigDecimal infoRate;
	/**系统评定结果（1：灰名单 2：白名单 3：黑名单）*/
	@Excel(name = "系统评定结果（1：灰名单 2：白名单 3：黑名单）", width = 15)
    @ApiModelProperty(value = "系统评定结果（1：灰名单 2：白名单 3：黑名单）")
	private Integer xtpdjg;
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
    @ApiModelProperty(value = "系统评定说明")
	private String xtpdsm;

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
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**xgr*/
	@Excel(name = "xgr", width = 15)
    @ApiModelProperty(value = "xgr")
	private String xgr;
	/**xgsj*/
	@Excel(name = "xgsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "xgsj")
	private Date xgsj;
	/**资产类型*/
	@Excel(name = "资产类型", width = 15)
    @ApiModelProperty(value = "资产类型")
	private String zclx;
	/**资产评估价*/
	@Excel(name = "资产评估价", width = 15)
    @ApiModelProperty(value = "资产评估价")
	private String zcpgj;
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
	/**有效日期*/
	@Excel(name = "有效日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效日期")
	private Date yxrq;
	/**贷款需求*/
	@Excel(name = "贷款需求", width = 15)
    @ApiModelProperty(value = "贷款需求")
	private String dkxq;
	/**贷款需求额度(万元)*/
	@Excel(name = "贷款需求额度(万元)", width = 15)
    @ApiModelProperty(value = "贷款需求额度(万元)")
	private java.math.BigDecimal dkxqed;
	/**企业人数*/
	@Excel(name = "企业人数", width = 15)
    @ApiModelProperty(value = "企业人数")
	private Integer qyrs;
	/**企业经营收入*/
	@Excel(name = "企业经营收入", width = 15)
    @ApiModelProperty(value = "企业经营收入")
	private java.math.BigDecimal qyjysr;
	/**他行业务情况*/
	@Excel(name = "他行业务情况", width = 15)
    @ApiModelProperty(value = "他行业务情况")
	private String thywqk;
	/**实收资本*/
	@Excel(name = "实收资本(万元)", width = 15)
    @ApiModelProperty(value = "实收资本(万元)")
	private String sszb;
	/**企业现地址*/
	@Excel(name = "企业现地址", width = 15)
    @ApiModelProperty(value = "企业现地址")
	private String qyxdz;
	/**注册到期日*/
	@Excel(name = "注册到期日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "注册到期日")
	private Date zcdqr;
	/**基本账户是否在我行*/
	@Excel(name = "基本账户是否在我行", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "基本账户是否在我行")
	@Dict(dicCode = "sfbz")
	private Integer jbzhsfzwh;
	/**是否存续*/
	@Excel(name = "是否存续", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否存续")
	@Dict(dicCode = "sfbz")
	private Integer sfcx;
	/**经验场地所有权*/
	@Excel(name = "经验场地所有权", width = 15,dicCode = "jycdsyq")
    @ApiModelProperty(value = "经验场地所有权")
	@Dict(dicCode = "jycdsyq")
	private String jycdsyq;
	/**经营场地面积*/
	@Excel(name = "经营场地面积", width = 15)
    @ApiModelProperty(value = "经营场地面积")
	private String jycdmj;
	/**企业资产*/
	@Excel(name = "企业资产", width = 15)
    @ApiModelProperty(value = "企业资产")
	private java.math.BigDecimal qyzc;
	/**企业负债*/
	@Excel(name = "企业负债", width = 15)
    @ApiModelProperty(value = "企业负债")
	private java.math.BigDecimal qyfz;
	/**企业经营利润*/
	@Excel(name = "企业经营利润", width = 15)
    @ApiModelProperty(value = "企业经营利润")
	private java.math.BigDecimal qyjylr;
	/**主营业务/产品*/
	@Excel(name = "主营业务/产品", width = 15)
    @ApiModelProperty(value = "主营业务/产品")
	private String zyywcp;
	/**企业注册等级类型*/
	@Excel(name = "企业注册等级类型", width = 15,dicCode = "qyzcdjlx")
    @ApiModelProperty(value = "企业注册等级类型")
	@Dict(dicCode = "qyzcdjlx")
	private String qyzcdjlx;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**更多联系方式*/
	@Excel(name = "更多联系方式", width = 15)
	@ApiModelProperty(value = "更多联系方式")
	private String gdlxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;
	/**是否走访*/
	@Excel(name = "是否走访", width = 15)
	@ApiModelProperty(value = "是否走访")
	private String sfzf;
	/**是否走访*/
	@Excel(name = "是否有效走访", width = 15)
	@ApiModelProperty(value = "是否有效走访")
	private String sfyxzf;
}
