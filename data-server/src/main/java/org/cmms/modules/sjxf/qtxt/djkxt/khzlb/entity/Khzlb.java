package org.cmms.modules.sjxf.qtxt.djkxt.khzlb.entity;

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
 * @Description: 客户资料表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ccd_custr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ccd_custr对象", description="客户资料表")
public class Khzlb {
    
	/**客户证件号码*/
	@Excel(name = "客户证件号码", width = 15)
    @ApiModelProperty(value = "客户证件号码")
	private String custrNbr;
	/**社保卡号*/
	@Excel(name = "社保卡号", width = 15)
    @ApiModelProperty(value = "社保卡号")
	private String securNbr;
	/**国籍*/
	@Excel(name = "国籍", width = 15)
    @ApiModelProperty(value = "国籍")
	private String nation;
	/**单位电话号码*/
	@Excel(name = "单位电话号码", width = 15)
    @ApiModelProperty(value = "单位电话号码")
	private String busiPhone;
	/**自购车辆购买时间*/
	@Excel(name = "自购车辆购买时间", width = 15)
    @ApiModelProperty(value = "自购车辆购买时间")
	private String carDate;
	/**自购车辆车牌号*/
	@Excel(name = "自购车辆车牌号", width = 15)
    @ApiModelProperty(value = "自购车辆车牌号")
	private String carId;
	/**自购车辆品牌*/
	@Excel(name = "自购车辆品牌", width = 15)
    @ApiModelProperty(value = "自购车辆品牌")
	private String carNam;
	/**自购车辆情况*/
	@Excel(name = "自购车辆情况", width = 15)
    @ApiModelProperty(value = "自购车辆情况")
	private String carCode;
	/**客户分类*/
	@Excel(name = "客户分类", width = 15)
    @ApiModelProperty(value = "客户分类")
	private String classCode;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
	private String compName;
	/**联系人1姓名*/
	@Excel(name = "联系人1姓名", width = 15)
    @ApiModelProperty(value = "联系人1姓名")
	private String conNam1;
	/**联系人2姓名*/
	@Excel(name = "联系人2姓名", width = 15)
    @ApiModelProperty(value = "联系人2姓名")
	private String conNam2;
	/**联系人1电话号码*/
	@Excel(name = "联系人1电话号码", width = 15)
    @ApiModelProperty(value = "联系人1电话号码")
	private String conTel1;
	/**联系人2电话号码*/
	@Excel(name = "联系人2电话号码", width = 15)
    @ApiModelProperty(value = "联系人2电话号码")
	private String conTel2;
	/**联系人1单位名称*/
	@Excel(name = "联系人1单位名称", width = 15)
    @ApiModelProperty(value = "联系人1单位名称")
	@TableField(value = "CONTR_NAM_1")
	private String contrNam1;
	/**联系人1关系*/
	@Excel(name = "联系人1关系", width = 15)
    @ApiModelProperty(value = "联系人1关系")
	@TableField(value = "CONTR_NAM_2")
	private String contrNam2;
	/**联系人2单位名称*/
	@Excel(name = "联系人2单位名称", width = 15)
    @ApiModelProperty(value = "联系人2单位名称")
	@TableField(value = "CONTR_NAM_3")
	private String contrNam3;
	/**联系人2关系*/
	@Excel(name = "联系人2关系", width = 15)
    @ApiModelProperty(value = "联系人2关系")
	@TableField(value = "CONTR_NAM_4")
	private String contrNam4;
	/**直系亲属关系*/
	@Excel(name = "直系亲属关系", width = 15)
    @ApiModelProperty(value = "直系亲属关系")
	@TableField(value = "CONTR_NAM_5")
	private String contrNam5;
	/**配偶单位名称*/
	@Excel(name = "配偶单位名称", width = 15)
    @ApiModelProperty(value = "配偶单位名称")
	@TableField(value = "CONTR_TEL_1")
	private String contrTel1;
	/**配偶证件号码*/
	@Excel(name = "配偶证件号码", width = 15)
    @ApiModelProperty(value = "配偶证件号码")
	@TableField(value = "CONTR_TEL_2")
	private String contrTel2;
	/**信用额度*/
	@Excel(name = "信用额度", width = 15)
    @ApiModelProperty(value = "信用额度")
	private String credLimit;
	/**美元帐户额度*/
	@Excel(name = "美元帐户额度", width = 15)
    @ApiModelProperty(value = "美元帐户额度")
	private String credlimX;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15)
    @ApiModelProperty(value = "出生日期")
	private String dayBirth;
	/**抚养人数*/
	@Excel(name = "抚养人数", width = 15)
    @ApiModelProperty(value = "抚养人数")
	private String dependents;
	/**教育程度*/
	@Excel(name = "教育程度", width = 15)
    @ApiModelProperty(value = "教育程度")
	private String educa;
	/**电子邮件地址*/
	@Excel(name = "电子邮件地址", width = 15)
    @ApiModelProperty(value = "电子邮件地址")
	private String emailAddr;
	/**部门*/
	@Excel(name = "部门", width = 15)
    @ApiModelProperty(value = "部门")
	private String emplyDept;
	/**员工编号*/
	@Excel(name = "员工编号", width = 15)
    @ApiModelProperty(value = "员工编号")
	private String emplyNbr;
	/**分机*/
	@Excel(name = "分机", width = 15)
    @ApiModelProperty(value = "分机")
	private String extension;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String gender;
	/**住房情况*/
	@Excel(name = "住房情况", width = 15)
    @ApiModelProperty(value = "住房情况")
	private String homeCode;
	/**住宅电话号码*/
	@Excel(name = "住宅电话号码", width = 15)
    @ApiModelProperty(value = "住宅电话号码")
	private String homePhone;
	/**配偶税前年收入*/
	@Excel(name = "配偶税前年收入", width = 15)
    @ApiModelProperty(value = "配偶税前年收入")
	private String incomeAn2;
	/**个人税前年收入*/
	@Excel(name = "个人税前年收入", width = 15)
    @ApiModelProperty(value = "个人税前年收入")
	private String incomeAnn;
	/**配偶主要收入来源*/
	@Excel(name = "配偶主要收入来源", width = 15)
    @ApiModelProperty(value = "配偶主要收入来源")
	private String incomeSr2;
	/**个人主要收入来源*/
	@Excel(name = "个人主要收入来源", width = 15)
    @ApiModelProperty(value = "个人主要收入来源")
	private String incomeSrc;
	/**传真号码*/
	@Excel(name = "传真号码", width = 15)
    @ApiModelProperty(value = "传真号码")
	private String irdN;
	/**语言代码*/
	@Excel(name = "语言代码", width = 15)
    @ApiModelProperty(value = "语言代码")
	private String langCode;
	/**可接受广告类别*/
	@Excel(name = "可接受广告类别", width = 15)
    @ApiModelProperty(value = "可接受广告类别")
	private String mailCode;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String marStatus;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String moPhone;
	/**分期付款额度*/
	@Excel(name = "分期付款额度", width = 15)
    @ApiModelProperty(value = "分期付款额度")
	private String mpLimit;
	/**英文性名*/
	@Excel(name = "英文性名", width = 15)
    @ApiModelProperty(value = "英文性名")
	private String mthrMname;
	/**公司性质*/
	@Excel(name = "公司性质", width = 15)
    @ApiModelProperty(value = "公司性质")
	private String occCatgry;
	/**行业类别代码*/
	@Excel(name = "行业类别代码", width = 15)
    @ApiModelProperty(value = "行业类别代码")
	private String occCode;
	/**职务*/
	@Excel(name = "职务", width = 15)
    @ApiModelProperty(value = "职务")
	private String posnEmply;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String raceCode;
	/**直系亲属姓名*/
	@Excel(name = "直系亲属姓名", width = 15)
    @ApiModelProperty(value = "直系亲属姓名")
	private String relNam;
	/**直系亲属电话号码*/
	@Excel(name = "直系亲属电话号码", width = 15)
    @ApiModelProperty(value = "直系亲属电话号码")
	private String relTel;
	/**前公司中文全称*/
	@Excel(name = "前公司中文全称", width = 15)
    @ApiModelProperty(value = "前公司中文全称")
	private String forecomp;
	/**前公司部门*/
	@Excel(name = "前公司部门", width = 15)
    @ApiModelProperty(value = "前公司部门")
	private String foredept;
	/**前公司职务*/
	@Excel(name = "前公司职务", width = 15)
    @ApiModelProperty(value = "前公司职务")
	private String forejob;
	/**前公司电话*/
	@Excel(name = "前公司电话", width = 15)
    @ApiModelProperty(value = "前公司电话")
	private String forebusi;
	/**前公司收入*/
	@Excel(name = "前公司收入", width = 15)
    @ApiModelProperty(value = "前公司收入")
	private String foreann;
	/**前公司工龄*/
	@Excel(name = "前公司工龄", width = 15)
    @ApiModelProperty(value = "前公司工龄")
	private String yrForecom;
	/**配偶姓名*/
	@Excel(name = "配偶姓名", width = 15)
    @ApiModelProperty(value = "配偶姓名")
	private String spuNam;
	/**配偶电话号码*/
	@Excel(name = "配偶电话号码", width = 15)
    @ApiModelProperty(value = "配偶电话号码")
	private String spuTel;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String surname;
	/**称谓*/
	@Excel(name = "称谓", width = 15)
    @ApiModelProperty(value = "称谓")
	private String xtitle;
	/**能否工作时间联系*/
	@Excel(name = "能否工作时间联系", width = 15)
    @ApiModelProperty(value = "能否工作时间联系")
	private String workCalls;
	/**配偶工龄*/
	@Excel(name = "配偶工龄", width = 15)
    @ApiModelProperty(value = "配偶工龄")
	private String yrInCom2;
	/**个人工龄*/
	@Excel(name = "个人工龄", width = 15)
    @ApiModelProperty(value = "个人工龄")
	private String yrInComp;
	/**居住年数*/
	@Excel(name = "居住年数", width = 15)
    @ApiModelProperty(value = "居住年数")
	private String yrThere;
	/**配偶手机号码*/
	@Excel(name = "配偶手机号码", width = 15)
    @ApiModelProperty(value = "配偶手机号码")
	private String spuMobile;
	/**月租金额/月还款额*/
	@Excel(name = "月租金额/月还款额", width = 15)
    @ApiModelProperty(value = "月租金额/月还款额")
	private String homeLoan;
	/**联系人1手机*/
	@Excel(name = "联系人1手机", width = 15)
    @ApiModelProperty(value = "联系人1手机")
	private String conMo1;
	/**联系人2手机*/
	@Excel(name = "联系人2手机", width = 15)
    @ApiModelProperty(value = "联系人2手机")
	private String conMo2;
	/**直系亲属手机*/
	@Excel(name = "直系亲属手机", width = 15)
    @ApiModelProperty(value = "直系亲属手机")
	private String relMobile;
	/**职务/岗位*/
	@Excel(name = "职务/岗位", width = 15)
    @ApiModelProperty(value = "职务/岗位")
	private String intTaxcod;
	/**身份核查结果*/
	@Excel(name = "身份核查结果", width = 15)
    @ApiModelProperty(value = "身份核查结果")
	private String idVerify;
	/**是否留存身份证复印件*/
	@Excel(name = "是否留存身份证复印件", width = 15)
    @ApiModelProperty(value = "是否留存身份证复印件")
	private String idcpYn;
	/**核实结果*/
	@Excel(name = "核实结果", width = 15)
    @ApiModelProperty(value = "核实结果")
	private String ivRst;
	/**无法核实原因*/
	@Excel(name = "无法核实原因", width = 15)
    @ApiModelProperty(value = "无法核实原因")
	private String ivRsn;
	/**处置方法*/
	@Excel(name = "处置方法", width = 15)
    @ApiModelProperty(value = "处置方法")
	private String ivDispo;
	/**身份证件有效期*/
	@Excel(name = "身份证件有效期", width = 15)
    @ApiModelProperty(value = "身份证件有效期")
	private String idExpdt;
	/**客户预留问题*/
	@Excel(name = "客户预留问题", width = 15)
    @ApiModelProperty(value = "客户预留问题")
	private String question;
	/**客户预留问题答案*/
	@Excel(name = "客户预留问题答案", width = 15)
    @ApiModelProperty(value = "客户预留问题答案")
	private String answer;
	/**催收评分*/
	@Excel(name = "催收评分", width = 15)
    @ApiModelProperty(value = "催收评分")
	private String octScore;
	/**国籍细分代码*/
	@Excel(name = "国籍细分代码", width = 15)
    @ApiModelProperty(value = "国籍细分代码")
	private String nationCd;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
