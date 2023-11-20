package org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity;

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
 * @Description: 个人客户表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("ECIF_PERSON")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ECIF_PERSON对象", description="个人客户表")
public class Grkhb {
    
	/**删除标示*/
	@Excel(name = "删除标示", width = 15)
    @ApiModelProperty(value = "删除标示")
	private String dataFlag;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**个人客户类型*/
	@Excel(name = "个人客户类型", width = 15)
    @ApiModelProperty(value = "个人客户类型")
	private String perCustType;
	/**客户姓氏*/
	@Excel(name = "客户姓氏", width = 15)
    @ApiModelProperty(value = "客户姓氏")
	private String surName;
	/**客户名字*/
	@Excel(name = "客户名字", width = 15)
    @ApiModelProperty(value = "客户名字")
	private String personalName;
	/**联名户标志*/
	@Excel(name = "联名户标志", width = 15)
    @ApiModelProperty(value = "联名户标志")
	private String jointMemberFlag;
	/**拼音名称*/
	@Excel(name = "拼音名称", width = 15)
    @ApiModelProperty(value = "拼音名称")
	private String pinyinName;
	/**拼音缩写*/
	@Excel(name = "拼音缩写", width = 15)
    @ApiModelProperty(value = "拼音缩写")
	private String pinyinAbbr;
	/**客户称谓*/
	@Excel(name = "客户称谓", width = 15)
    @ApiModelProperty(value = "客户称谓")
	private String personTitle;
	/**客户昵称*/
	@Excel(name = "客户昵称", width = 15)
    @ApiModelProperty(value = "客户昵称")
	private String nickName;
	/**曾用名*/
	@Excel(name = "曾用名", width = 15)
    @ApiModelProperty(value = "曾用名")
	private String usedName;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String gender;
	/**生日是否农历*/
	@Excel(name = "生日是否农历", width = 15)
    @ApiModelProperty(value = "生日是否农历")
	private String isCnBirthday;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15)
    @ApiModelProperty(value = "出生日期")
	private String birthday;
	/**出生地点*/
	@Excel(name = "出生地点", width = 15)
    @ApiModelProperty(value = "出生地点")
	private String birthlocale;
	/**国籍*/
	@Excel(name = "国籍", width = 15)
    @ApiModelProperty(value = "国籍")
	private String citizenship;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String nationality;
	/**籍贯*/
	@Excel(name = "籍贯", width = 15)
    @ApiModelProperty(value = "籍贯")
	private String nativeplace;
	/**是否通过联网核查*/
	@Excel(name = "是否通过联网核查", width = 15)
    @ApiModelProperty(value = "是否通过联网核查")
	private String isIdentityVerify;
	/**户口所在地*/
	@Excel(name = "户口所在地", width = 15)
    @ApiModelProperty(value = "户口所在地")
	private String hukouPlace;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String marriage;
	/**居住状况*/
	@Excel(name = "居住状况", width = 15)
    @ApiModelProperty(value = "居住状况")
	private String residence;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
	private String health;
	/**宗教信仰*/
	@Excel(name = "宗教信仰", width = 15)
    @ApiModelProperty(value = "宗教信仰")
	private String religiousBelief;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15)
    @ApiModelProperty(value = "政治面貌")
	private String politicalFace;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String mobilePhone;
	/**电子邮件地址*/
	@Excel(name = "电子邮件地址", width = 15)
    @ApiModelProperty(value = "电子邮件地址")
	private String email;
	/**主页*/
	@Excel(name = "主页", width = 15)
    @ApiModelProperty(value = "主页")
	private String homepage;
	/**微博*/
	@Excel(name = "微博", width = 15)
    @ApiModelProperty(value = "微博")
	private String weibo;
	/**微信*/
	@Excel(name = "微信", width = 15)
    @ApiModelProperty(value = "微信")
	private String weixin;
	/**QQ*/
	@Excel(name = "QQ", width = 15)
    @ApiModelProperty(value = "QQ")
	private String qq;
	/**星座*/
	@Excel(name = "星座", width = 15)
    @ApiModelProperty(value = "星座")
	private String starSign;
	/**住宅地址*/
	@Excel(name = "住宅地址", width = 15)
    @ApiModelProperty(value = "住宅地址")
	private String homeAddr;
	/**住宅邮编*/
	@Excel(name = "住宅邮编", width = 15)
    @ApiModelProperty(value = "住宅邮编")
	private String homeZipcode;
	/**住宅电话*/
	@Excel(name = "住宅电话", width = 15)
    @ApiModelProperty(value = "住宅电话")
	private String homeTel;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15)
    @ApiModelProperty(value = "最高学历")
	private String highestSchooling;
	/**最高学位*/
	@Excel(name = "最高学位", width = 15)
    @ApiModelProperty(value = "最高学位")
	private String highestDegree;
	/**毕业学校*/
	@Excel(name = "毕业学校", width = 15)
    @ApiModelProperty(value = "毕业学校")
	private String graduateSchool;
	/**所学专业*/
	@Excel(name = "所学专业", width = 15)
    @ApiModelProperty(value = "所学专业")
	private String major;
	/**毕业时间*/
	@Excel(name = "毕业时间", width = 15)
    @ApiModelProperty(value = "毕业时间")
	private String graduationDate;
	/**职业状况*/
	@Excel(name = "职业状况", width = 15)
    @ApiModelProperty(value = "职业状况")
	private String careerStat;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
	private String careerType;
	/**从事行业*/
	@Excel(name = "从事行业", width = 15)
    @ApiModelProperty(value = "从事行业")
	private String profession;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
	private String unitName;
	/**单位性质*/
	@Excel(name = "单位性质", width = 15)
    @ApiModelProperty(value = "单位性质")
	private String unitChar;
	/**单位地址*/
	@Excel(name = "单位地址", width = 15)
    @ApiModelProperty(value = "单位地址")
	private String unitAddr;
	/**单位邮编*/
	@Excel(name = "单位邮编", width = 15)
    @ApiModelProperty(value = "单位邮编")
	private String unitZipcode;
	/**单位电话*/
	@Excel(name = "单位电话", width = 15)
    @ApiModelProperty(value = "单位电话")
	private String unitTel;
	/**传真号码*/
	@Excel(name = "传真号码", width = 15)
    @ApiModelProperty(value = "传真号码")
	private String unitFex;
	/**通讯地址*/
	@Excel(name = "通讯地址", width = 15)
    @ApiModelProperty(value = "通讯地址")
	private String postAddr;
	/**通讯编码*/
	@Excel(name = "通讯编码", width = 15)
    @ApiModelProperty(value = "通讯编码")
	private String postZipcode;
	/**职务*/
	@Excel(name = "职务", width = 15)
    @ApiModelProperty(value = "职务")
	private String duty;
	/**参加工作时间*/
	@Excel(name = "参加工作时间", width = 15)
    @ApiModelProperty(value = "参加工作时间")
	private String careerStartDate;
	/**年收入范围*/
	@Excel(name = "年收入范围", width = 15)
    @ApiModelProperty(value = "年收入范围")
	private String annualIncomeScope;
	/**年收入*/
	@Excel(name = "年收入", width = 15)
    @ApiModelProperty(value = "年收入")
	private java.math.BigDecimal annualIncome;
	/**年支出*/
	@Excel(name = "年支出", width = 15)
    @ApiModelProperty(value = "年支出")
	private java.math.BigDecimal annualExpenditure;
	/**参加本单位日期*/
	@Excel(name = "参加本单位日期", width = 15)
    @ApiModelProperty(value = "参加本单位日期")
	private String currCareerStartDate;
	/**是否有执业资格*/
	@Excel(name = "是否有执业资格", width = 15)
    @ApiModelProperty(value = "是否有执业资格")
	private String hasQualification;
	/**资格证书名称*/
	@Excel(name = "资格证书名称", width = 15)
    @ApiModelProperty(value = "资格证书名称")
	private String qualification;
	/**职称*/
	@Excel(name = "职称", width = 15)
    @ApiModelProperty(value = "职称")
	private String careerTitle;
	/**不良记录*/
	@Excel(name = "不良记录", width = 15)
    @ApiModelProperty(value = "不良记录")
	private String familyAdverseRecords;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remarks;
	/**最后更新系统*/
	@Excel(name = "最后更新系统", width = 15)
    @ApiModelProperty(value = "最后更新系统")
	private String lastUpdateSys;
	/**最后更新人*/
	@Excel(name = "最后更新人", width = 15)
    @ApiModelProperty(value = "最后更新人")
	private String lastUpdateUser;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 15)
    @ApiModelProperty(value = "最后更新时间")
	private String lastUpdateTm;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private String txSeqNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;

//	/**天入库表编号-对不同的表名唯一*/
//	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
//    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
//	private Integer dtnum;
//	/**dttime*/
//	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "dttime")
//	private Date dttime;
}
