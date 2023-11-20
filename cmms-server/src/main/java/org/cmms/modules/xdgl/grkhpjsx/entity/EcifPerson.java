package org.cmms.modules.xdgl.grkhpjsx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* 个人客户表
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ECIF_PERSON")
public class EcifPerson extends Model<EcifPerson> {

    private static final long serialVersionUID = 1L;

    /**
     * 删除标示
     */
    @TableField("DATA_FLAG")
    private String dataFlag;

    /**
     * 客户编号
     */
    @TableId(value = "CUST_ID", type = IdType.ASSIGN_ID)
    private String custId;

    /**
     * 个人客户类型
     */
    @TableField("PER_CUST_TYPE")
    private String perCustType;

    /**
     * 客户姓氏
     */
    @TableField("SUR_NAME")
    private String surName;

    /**
     * 客户名字
     */
    @TableField("PERSONAL_NAME")
    private String personalName;

    /**
     * 联名户标志
     */
    @TableField("JOINT_MEMBER_FLAG")
    private String jointMemberFlag;

    /**
     * 拼音名称
     */
    @TableField("PINYIN_NAME")
    private String pinyinName;

    /**
     * 拼音缩写
     */
    @TableField("PINYIN_ABBR")
    private String pinyinAbbr;

    /**
     * 客户称谓
     */
    @TableField("PERSON_TITLE")
    private String personTitle;

    /**
     * 客户昵称
     */
    @TableField("NICK_NAME")
    private String nickName;

    /**
     * 曾用名
     */
    @TableField("USED_NAME")
    private String usedName;

    /**
     * 性别
     */
    @TableField("GENDER")
    private String gender;

    /**
     * 生日是否农历
     */
    @TableField("IS_CN_BIRTHDAY")
    private String isCnBirthday;

    /**
     * 出生日期
     */
    @TableField("BIRTHDAY")
    private String birthday;

    /**
     * 出生地点
     */
    @TableField("BIRTHLOCALE")
    private String birthlocale;

    /**
     * 国籍
     */
    @TableField("CITIZENSHIP")
    private String citizenship;

    /**
     * 民族
     */
    @TableField("NATIONALITY")
    private String nationality;

    /**
     * 籍贯
     */
    @TableField("NATIVEPLACE")
    private String nativeplace;

    /**
     * 是否通过联网核查
     */
    @TableField("IS_IDENTITY_VERIFY")
    private String isIdentityVerify;

    /**
     * 户口所在地
     */
    @TableField("HUKOU_PLACE")
    private String hukouPlace;

    /**
     * 婚姻状况
     */
    @TableField("MARRIAGE")
    private String marriage;

    /**
     * 居住状况
     */
    @TableField("RESIDENCE")
    private String residence;

    /**
     * 健康状况
     */
    @TableField("HEALTH")
    private String health;

    /**
     * 宗教信仰
     */
    @TableField("RELIGIOUS_BELIEF")
    private String religiousBelief;

    /**
     * 政治面貌
     */
    @TableField("POLITICAL_FACE")
    private String politicalFace;

    /**
     * 手机号码
     */
    @TableField("MOBILE_PHONE")
    private String mobilePhone;

    /**
     * 电子邮件地址
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 主页
     */
    @TableField("HOMEPAGE")
    private String homepage;

    /**
     * 微博
     */
    @TableField("WEIBO")
    private String weibo;

    /**
     * 微信
     */
    @TableField("WEIXIN")
    private String weixin;

    /**
     * QQ
     */
    @TableField("QQ")
    private String qq;

    /**
     * 星座
     */
    @TableField("STAR_SIGN")
    private String starSign;

    /**
     * 住宅地址
     */
    @TableField("HOME_ADDR")
    private String homeAddr;

    /**
     * 住宅邮编
     */
    @TableField("HOME_ZIPCODE")
    private String homeZipcode;

    /**
     * 住宅电话
     */
    @TableField("HOME_TEL")
    private String homeTel;

    /**
     * 最高学历
     */
    @TableField("HIGHEST_SCHOOLING")
    private String highestSchooling;

    /**
     * 最高学位
     */
    @TableField("HIGHEST_DEGREE")
    private String highestDegree;

    /**
     * 毕业学校
     */
    @TableField("GRADUATE_SCHOOL")
    private String graduateSchool;

    /**
     * 所学专业
     */
    @TableField("MAJOR")
    private String major;

    /**
     * 毕业时间
     */
    @TableField("GRADUATION_DATE")
    private String graduationDate;

    /**
     * 职业状况
     */
    @TableField("CAREER_STAT")
    private String careerStat;

    /**
     * 职业
     */
    @TableField("CAREER_TYPE")
    private String careerType;

    /**
     * 从事行业
     */
    @TableField("PROFESSION")
    private String profession;

    /**
     * 单位名称
     */
    @TableField("UNIT_NAME")
    private String unitName;

    /**
     * 单位性质
     */
    @TableField("UNIT_CHAR")
    private String unitChar;

    /**
     * 单位地址
     */
    @TableField("UNIT_ADDR")
    private String unitAddr;

    /**
     * 单位邮编
     */
    @TableField("UNIT_ZIPCODE")
    private String unitZipcode;

    /**
     * 单位电话
     */
    @TableField("UNIT_TEL")
    private String unitTel;

    /**
     * 传真号码
     */
    @TableField("UNIT_FEX")
    private String unitFex;

    /**
     * 通讯地址（默认为住宅地址,可不同）
     */
    @TableField("POST_ADDR")
    private String postAddr;

    /**
     * 通讯编码（默认为住宅邮编,可不同）
     */
    @TableField("POST_ZIPCODE")
    private String postZipcode;

    /**
     * 职务
     */
    @TableField("DUTY")
    private String duty;

    /**
     * 参加工作时间
     */
    @TableField("CAREER_START_DATE")
    private String careerStartDate;

    /**
     * 年收入范围
     */
    @TableField("ANNUAL_INCOME_SCOPE")
    private String annualIncomeScope;

    /**
     * 年收入
     */
    @TableField("ANNUAL_INCOME")
    private Double annualIncome;

    /**
     * 年支出
     */
    @TableField("ANNUAL_EXPENDITURE")
    private Double annualExpenditure;

    /**
     * 参加本单位日期
     */
    @TableField("CURR_CAREER_START_DATE")
    private String currCareerStartDate;

    /**
     * 是否有执业资格
     */
    @TableField("HAS_QUALIFICATION")
    private String hasQualification;

    /**
     * 资格证书名称
     */
    @TableField("QUALIFICATION")
    private String qualification;

    /**
     * 职称
     */
    @TableField("CAREER_TITLE")
    private String careerTitle;

    /**
     * 不良记录
     */
    @TableField("FAMILY_ADVERSE_RECORDS")
    private String familyAdverseRecords;

    /**
     * 备注
     */
    @TableField("REMARKS")
    private String remarks;

    /**
     * 最后更新系统
     */
    @TableField("LAST_UPDATE_SYS")
    private String lastUpdateSys;

    /**
     * 最后更新人
     */
    @TableField("LAST_UPDATE_USER")
    private String lastUpdateUser;

    /**
     * 最后更新时间
     */
    @TableField("LAST_UPDATE_TM")
    private String lastUpdateTm;

    /**
     * 交易流水号
     */
    @TableField("TX_SEQ_NO")
    private String txSeqNo;

    /**
     * 数据开始日期
     */
    @TableField("S_DATE")
    private String sDate;

    /**
     * 数据结束日期
     */
    @TableField("E_DATE")
    private String eDate;

    /**
     * 加载时间
     */
    @TableField("LOAD_DATE")
    private Date loadDate;

    /**
     * 法人标识
     */
    @TableField("LEGAL_NO")
    private String legalNo;

   /* *//**
     * 天入库表编号-对不同的表名唯一
     *//*
    @TableField("DTNUM__")
    private Integer dtnum;

    @TableField("DTTIME__")
    private Date dttime;*/

    @Override
    public Serializable pkVal() {
        return this.custId;
    }
}
