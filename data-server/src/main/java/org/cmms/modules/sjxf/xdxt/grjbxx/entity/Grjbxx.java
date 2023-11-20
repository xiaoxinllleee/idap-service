package org.cmms.modules.sjxf.xdxt.grjbxx.entity;

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
 * @Description: 个人基本信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_ind_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_ind_info对象", description="个人基本信息")
public class Grjbxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**开户行名字*/
	@Excel(name = "开户行名字", width = 15)
    @ApiModelProperty(value = "开户行名字")
	private String loanOrg;
	/**社会保险*/
	@Excel(name = "社会保险", width = 15)
    @ApiModelProperty(value = "社会保险")
	private String isSocialInsurance;
	/**人身保险*/
	@Excel(name = "人身保险", width = 15)
    @ApiModelProperty(value = "人身保险")
	private String isPsnInsurance;
	/**社会保险开始日期*/
	@Excel(name = "社会保险开始日期", width = 15)
    @ApiModelProperty(value = "社会保险开始日期")
	private String socialInsStrDate;
	/**人身保险开始日期*/
	@Excel(name = "人身保险开始日期", width = 15)
    @ApiModelProperty(value = "人身保险开始日期")
	private String psnInsStrDate;
	/**财产保险开始日期*/
	@Excel(name = "财产保险开始日期", width = 15)
    @ApiModelProperty(value = "财产保险开始日期")
	private String propertyInsStrDate;
	/**社会保险总额*/
	@Excel(name = "社会保险总额", width = 15)
    @ApiModelProperty(value = "社会保险总额")
	private java.math.BigDecimal socialInsSum;
	/**人身保险保费*/
	@Excel(name = "人身保险保费", width = 15)
    @ApiModelProperty(value = "人身保险保费")
	private java.math.BigDecimal psnInsFee;
	/**人身保险保险额*/
	@Excel(name = "人身保险保险额", width = 15)
    @ApiModelProperty(value = "人身保险保险额")
	private java.math.BigDecimal psnInsSum;
	/**财产保险保额*/
	@Excel(name = "财产保险保额", width = 15)
    @ApiModelProperty(value = "财产保险保额")
	private java.math.BigDecimal propertyInsSum;
	/**财产保险保费*/
	@Excel(name = "财产保险保费", width = 15)
    @ApiModelProperty(value = "财产保险保费")
	private java.math.BigDecimal propertyInsFee;
	/**财产保险*/
	@Excel(name = "财产保险", width = 15)
    @ApiModelProperty(value = "财产保险")
	private String isPropertyInsurance;
	/**有无违法或法律纠纷记录*/
	@Excel(name = "有无违法或法律纠纷记录", width = 15)
    @ApiModelProperty(value = "有无违法或法律纠纷记录")
	private String isIllegalHis;
	/**发生日期*/
	@Excel(name = "发生日期", width = 15)
    @ApiModelProperty(value = "发生日期")
	private String illegalDate;
	/**事件描述及原因*/
	@Excel(name = "事件描述及原因", width = 15)
    @ApiModelProperty(value = "事件描述及原因")
	private String illegalDetail;
	/**公积金余额*/
	@Excel(name = "公积金余额", width = 15)
    @ApiModelProperty(value = "公积金余额")
	private java.math.BigDecimal afaBalance;
	/**公积金月缴交额*/
	@Excel(name = "公积金月缴交额", width = 15)
    @ApiModelProperty(value = "公积金月缴交额")
	private java.math.BigDecimal afaPayment;
	/**寻呼号码*/
	@Excel(name = "寻呼号码", width = 15)
    @ApiModelProperty(value = "寻呼号码")
	private String beepPager;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15)
    @ApiModelProperty(value = "出生日期")
	private String birthDay;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**描述1*/
	@Excel(name = "描述1", width = 15)
    @ApiModelProperty(value = "描述1")
	private String describe1;
	/**描述2*/
	@Excel(name = "描述2", width = 15)
    @ApiModelProperty(value = "描述2")
	private String describe2;
	/**描述3*/
	@Excel(name = "描述3", width = 15)
    @ApiModelProperty(value = "描述3")
	private String describe3;
	/**描述4*/
	@Excel(name = "描述4", width = 15)
    @ApiModelProperty(value = "描述4")
	private String describe4;
	/**担任职务*/
	@Excel(name = "担任职务", width = 15)
    @ApiModelProperty(value = "担任职务")
	private String duty;
	/**最高学位*/
	@Excel(name = "最高学位", width = 15)
    @ApiModelProperty(value = "最高学位")
	private String eduDegree;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15)
    @ApiModelProperty(value = "最高学历")
	private String eduExperience;
	/**电子邮件地址*/
	@Excel(name = "电子邮件地址", width = 15)
    @ApiModelProperty(value = "电子邮件地址")
	private String emailAddr;
	/**工作单位*/
	@Excel(name = "工作单位", width = 15)
    @ApiModelProperty(value = "工作单位")
	private String employment;
	/**工作简历*/
	@Excel(name = "工作简历", width = 15)
    @ApiModelProperty(value = "工作简历")
	private String employmenuResume;
	/**企业客户编号*/
	@Excel(name = "企业客户编号", width = 15)
    @ApiModelProperty(value = "企业客户编号")
	private String entInfoId;
	/**家庭住址*/
	@Excel(name = "家庭住址", width = 15)
    @ApiModelProperty(value = "家庭住址")
	private String familyAddr;
	/**家庭人口*/
	@Excel(name = "家庭人口", width = 15)
    @ApiModelProperty(value = "家庭人口")
	private String familySize;
	/**家庭情况*/
	@Excel(name = "家庭情况", width = 15)
    @ApiModelProperty(value = "家庭情况")
	private String familyState;
	/**住宅电话*/
	@Excel(name = "住宅电话", width = 15)
    @ApiModelProperty(value = "住宅电话")
	private String familyTel;
	/**住宅邮编*/
	@Excel(name = "住宅邮编", width = 15)
    @ApiModelProperty(value = "住宅邮编")
	private String familyZip;
	/**曾用名(别名)*/
	@Excel(name = "曾用名(别名)", width = 15)
    @ApiModelProperty(value = "曾用名(别名)")
	private String fullName;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String gender;
	/**毕业学校*/
	@Excel(name = "毕业学校", width = 15)
    @ApiModelProperty(value = "毕业学校")
	private String graduateSchool;
	/**毕业年份*/
	@Excel(name = "毕业年份", width = 15)
    @ApiModelProperty(value = "毕业年份")
	private String graduateYear;
	/**最高学位*/
	@Excel(name = "最高学位", width = 15)
    @ApiModelProperty(value = "最高学位")
	private String highestEducationDegree;
	/**业余兴趣爱好*/
	@Excel(name = "业余兴趣爱好", width = 15)
    @ApiModelProperty(value = "业余兴趣爱好")
	private String hobby;
	/**荣誉称号*/
	@Excel(name = "荣誉称号", width = 15)
    @ApiModelProperty(value = "荣誉称号")
	private String honorTitle;
	/**电子信箱*/
	@Excel(name = "电子信箱", width = 15)
    @ApiModelProperty(value = "电子信箱")
	private String indEmail;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String indInfoId;
	/**是否董事会成员*/
	@Excel(name = "是否董事会成员", width = 15)
    @ApiModelProperty(value = "是否董事会成员")
	private String isDirectorater;
	/**是否我行职工*/
	@Excel(name = "是否我行职工", width = 15)
    @ApiModelProperty(value = "是否我行职工")
	private String isEmployee;
	/**是否关联*/
	@Excel(name = "是否关联", width = 15)
    @ApiModelProperty(value = "是否关联")
	private String isRelating;
	/**是否我行股东*/
	@Excel(name = "是否我行股东", width = 15)
    @ApiModelProperty(value = "是否我行股东")
	private String isSh;
	/**通讯地址邮政编码*/
	@Excel(name = "通讯地址邮政编码", width = 15)
    @ApiModelProperty(value = "通讯地址邮政编码")
	private String mailAddrZip;
	/**通讯地址*/
	@Excel(name = "通讯地址", width = 15)
    @ApiModelProperty(value = "通讯地址")
	private String mailingAddress;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String marriage;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String mobileTel;
	/**月收入*/
	@Excel(name = "月收入", width = 15)
    @ApiModelProperty(value = "月收入")
	private java.math.BigDecimal monthlyWages;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String nationality;
	/**籍贯*/
	@Excel(name = "籍贯", width = 15)
    @ApiModelProperty(value = "籍贯")
	private String nativePlace;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
	private String occupation;
	/**单位电话*/
	@Excel(name = "单位电话", width = 15)
    @ApiModelProperty(value = "单位电话")
	private String officeTel;
	/**其他企业兼职*/
	@Excel(name = "其他企业兼职", width = 15)
    @ApiModelProperty(value = "其他企业兼职")
	private String otherEntSideline;
	/**个人公积金账号*/
	@Excel(name = "个人公积金账号", width = 15)
    @ApiModelProperty(value = "个人公积金账号")
	private String personalAfa;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15)
    @ApiModelProperty(value = "政治面貌")
	private String politicalFace;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**补充公积金余额*/
	@Excel(name = "补充公积金余额", width = 15)
    @ApiModelProperty(value = "补充公积金余额")
	private java.math.BigDecimal safaBalance;
	/**补充公积金月缴交额*/
	@Excel(name = "补充公积金月缴交额", width = 15)
    @ApiModelProperty(value = "补充公积金月缴交额")
	private java.math.BigDecimal safaPayment;
	/**在我行担任职务*/
	@Excel(name = "在我行担任职务", width = 15)
    @ApiModelProperty(value = "在我行担任职务")
	private String selfBankSideline;
	/**社会保险账号*/
	@Excel(name = "社会保险账号", width = 15)
    @ApiModelProperty(value = "社会保险账号")
	private String siNo;
	/**社会兼职*/
	@Excel(name = "社会兼职", width = 15)
    @ApiModelProperty(value = "社会兼职")
	private String societySideline;
	/**所学专业*/
	@Excel(name = "所学专业", width = 15)
    @ApiModelProperty(value = "所学专业")
	private String speciality;
	/**最高职称*/
	@Excel(name = "最高职称", width = 15)
    @ApiModelProperty(value = "最高职称")
	private String techPost;
	/**更改日期*/
	@Excel(name = "更改日期", width = 15)
    @ApiModelProperty(value = "更改日期")
	private String updateDate;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String userId;
	/**电子邮箱*/
	@Excel(name = "电子邮箱", width = 15)
    @ApiModelProperty(value = "电子邮箱")
	private String emailAddress;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
	private String familyZipcode;
	/**许可证号码*/
	@Excel(name = "许可证号码", width = 15)
    @ApiModelProperty(value = "许可证号码")
	private String xukezhengCode;
	/**居住地址*/
	@Excel(name = "居住地址", width = 15)
    @ApiModelProperty(value = "居住地址")
	private String juzhuAddress;
	/**存款日均*/
	@Excel(name = "存款日均", width = 15)
    @ApiModelProperty(value = "存款日均")
	private java.math.BigDecimal cunkuanRijun;
	/**存款号*/
	@Excel(name = "存款号", width = 15)
    @ApiModelProperty(value = "存款号")
	private String cunkuanCode;
	/**包片人*/
	@Excel(name = "包片人", width = 15)
    @ApiModelProperty(value = "包片人")
	private String baopianren;
	/**业务员*/
	@Excel(name = "业务员", width = 15)
    @ApiModelProperty(value = "业务员")
	private String yewuyuan;
	/**暂末用*/
	@Excel(name = "暂末用", width = 15)
    @ApiModelProperty(value = "暂末用")
	private String man;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**部门ID*/
	@Excel(name = "部门ID", width = 15)
    @ApiModelProperty(value = "部门ID")
	private String deptId;
	/**调查人*/
	@Excel(name = "调查人", width = 15)
    @ApiModelProperty(value = "调查人")
	private String checkId;
	/**调查时间*/
	@Excel(name = "调查时间", width = 15)
    @ApiModelProperty(value = "调查时间")
	private String checkDate;
	/**户主及家庭成员社会诚信,家庭及邻里关系,道德品等综合评价*/
	@Excel(name = "户主及家庭成员社会诚信,家庭及邻里关系,道德品等综合评价", width = 15)
    @ApiModelProperty(value = "户主及家庭成员社会诚信,家庭及邻里关系,道德品等综合评价")
	private String isCantonal;
	/**劳动力人口数*/
	@Excel(name = "劳动力人口数", width = 15)
    @ApiModelProperty(value = "劳动力人口数")
	private String workSize;
	/**劳动能力*/
	@Excel(name = "劳动能力", width = 15)
    @ApiModelProperty(value = "劳动能力")
	private String workPower;
	/**贷款结算帐号*/
	@Excel(name = "贷款结算帐号", width = 15)
    @ApiModelProperty(value = "贷款结算帐号")
	private String loanAccount;
	/**国标行业*/
	@Excel(name = "国标行业", width = 15)
    @ApiModelProperty(value = "国标行业")
	private String industryType;
	/**国标行业1*/
	@Excel(name = "国标行业1", width = 15)
    @ApiModelProperty(value = "国标行业1")
	private String industryType1;
	/**国标行业2*/
	@Excel(name = "国标行业2", width = 15)
    @ApiModelProperty(value = "国标行业2")
	private String industryType2;
	/**国标行业3*/
	@Excel(name = "国标行业3", width = 15)
    @ApiModelProperty(value = "国标行业3")
	private String industryType3;
	/**是否发送短信*/
	@Excel(name = "是否发送短信", width = 15)
    @ApiModelProperty(value = "是否发送短信")
	private String isSendMess;
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
