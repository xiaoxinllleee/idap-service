package org.cmms.modules.ygjx.entity;

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
 * @Description: 涟源基本工资管理
 * @Author: jeecg-boot
 * @Date:   2022-10-27
 * @Version: V1.0
 */
@Data
@TableName("ERP_WAGE_JBGZGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_WAGE_JBGZGL对象", description="涟源基本工资管理")
public class ErpWageJbgzgl {

	/**工作单位（所在支行）*/
	@Excel(name = "工作单位（所在支行）", width = 15)
    @ApiModelProperty(value = "工作单位（所在支行）")
	private String workunit;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String name;
	/**职务/岗位*/
	@Excel(name = "职务/岗位", width = 15)
    @ApiModelProperty(value = "职务/岗位")
	private String worker;
	/**银行帐号*/
	@Excel(name = "银行帐号", width = 15)
    @ApiModelProperty(value = "银行帐号")
	private String bankaccount;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	private String idnumber;
	/** 技术职称*/
	@Excel(name = " 技术职称", width = 15)
    @ApiModelProperty(value = " 技术职称")
	private String technicaltitle;
	/**参加工作时间*/
	@Excel(name = "参加工作时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "参加工作时间")
	private Date joinworktime;
	/**截止时间*/
	@Excel(name = "截止时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "截止时间")
	private Date deadline;
	/**工龄*/
	@Excel(name = "工龄", width = 15)
    @ApiModelProperty(value = "工龄")
	private String workage;
	/**岗位系数*/
	@Excel(name = "岗位系数", width = 15)
    @ApiModelProperty(value = "岗位系数")
	private String postcoefficient;
	/**生活保障金*/
	@Excel(name = "生活保障金", width = 15)
    @ApiModelProperty(value = "生活保障金")
	private java.math.BigDecimal subsistenceallowance;
	/**岗位工资*/
	@Excel(name = "岗位工资", width = 15)
    @ApiModelProperty(value = "岗位工资")
	private java.math.BigDecimal postwage;
	/**年功津贴*/
	@Excel(name = "年功津贴", width = 15)
    @ApiModelProperty(value = "年功津贴")
	private java.math.BigDecimal yearsworkallowance;
	/**地区+审计特津贴*/
	@Excel(name = "地区+审计特津贴", width = 15)
    @ApiModelProperty(value = "地区+审计特津贴")
	private java.math.BigDecimal auditspecialallowance;
	/**技术职称津贴*/
	@Excel(name = "技术职称津贴", width = 15)
    @ApiModelProperty(value = "技术职称津贴")
	private java.math.BigDecimal technicaltitleallowance;
	/**二级支行负责人履职工资*/
	@Excel(name = "二级支行负责人履职工资", width = 15)
    @ApiModelProperty(value = "二级支行负责人履职工资")
	private java.math.BigDecimal secondarybranch;
	/**内勤主管履职工资*/
	@Excel(name = "内勤主管履职工资", width = 15)
    @ApiModelProperty(value = "内勤主管履职工资")
	private java.math.BigDecimal performanceofficesupervsior;
	/**调整部分*/
	@Excel(name = "调整部分", width = 15)
    @ApiModelProperty(value = "调整部分")
	private String adjusthepart;
	/**预发工资*/
	@Excel(name = "预发工资", width = 15)
    @ApiModelProperty(value = "预发工资")
	private java.math.BigDecimal pretestwage;
	/**其他加班值班补助*/
	@Excel(name = "其他加班值班补助", width = 15)
    @ApiModelProperty(value = "其他加班值班补助")
	private java.math.BigDecimal overtimedutyallowance;
	/**元旦加班工资*/
	@Excel(name = "元旦加班工资", width = 15)
    @ApiModelProperty(value = "元旦加班工资")
	private java.math.BigDecimal dayovertimepay;
	/**春节加班工资*/
	@Excel(name = "春节加班工资", width = 15)
    @ApiModelProperty(value = "春节加班工资")
	private java.math.BigDecimal springfestivalovertimepay;
	/**清明加班工资*/
	@Excel(name = "清明加班工资", width = 15)
    @ApiModelProperty(value = "清明加班工资")
	private java.math.BigDecimal qingmingovertimepay;
	/**五一加班工资*/
	@Excel(name = "五一加班工资", width = 15)
    @ApiModelProperty(value = "五一加班工资")
	private java.math.BigDecimal maydayovertimepay;
	/**端午加班工资*/
	@Excel(name = "端午加班工资", width = 15)
    @ApiModelProperty(value = "端午加班工资")
	private java.math.BigDecimal dragonboatfestivalovertimepay;
	/**中秋加班工资*/
	@Excel(name = "中秋加班工资", width = 15)
    @ApiModelProperty(value = "中秋加班工资")
	private java.math.BigDecimal autumnfestivalovertimepay;
	/**国庆加班工资*/
	@Excel(name = "国庆加班工资", width = 15)
    @ApiModelProperty(value = "国庆加班工资")
	private java.math.BigDecimal nationaldayovertimepay;
	/**产假提前上班补助*/
	@Excel(name = "产假提前上班补助", width = 15)
    @ApiModelProperty(value = "产假提前上班补助")
	private java.math.BigDecimal earlyworkallowance;
	/**实习工资*/
	@Excel(name = "实习工资", width = 15)
    @ApiModelProperty(value = "实习工资")
	private java.math.BigDecimal internshipsalary;
	/**稿酬*/
	@Excel(name = "稿酬", width = 15)
    @ApiModelProperty(value = "稿酬")
	private java.math.BigDecimal remuneration;
	/**补发工资*/
	@Excel(name = "补发工资", width = 15)
    @ApiModelProperty(value = "补发工资")
	private java.math.BigDecimal backpay;
	/**月应交企业年金个人部分*/
	@Excel(name = "月应交企业年金个人部分", width = 15)
    @ApiModelProperty(value = "月应交企业年金个人部分")
	private String mouthspaidmonthly;
	/** 月应交养保个人部分*/
	@Excel(name = " 月应交养保个人部分", width = 15)
    @ApiModelProperty(value = " 月应交养保个人部分")
	private String spaypersonalpart;
	/**留用工月交养老保险*/
	@Excel(name = "留用工月交养老保险", width = 15)
    @ApiModelProperty(value = "留用工月交养老保险")
	private String paypensioninsurance;
	/**月应交医保个人部分*/
	@Excel(name = "月应交医保个人部分", width = 15)
    @ApiModelProperty(value = "月应交医保个人部分")
	private String spaideverymouth;
	/**应交失业保险个人部分*/
	@Excel(name = "应交失业保险个人部分", width = 15)
    @ApiModelProperty(value = "应交失业保险个人部分")
	private java.math.BigDecimal payindividualpart;
	/**扣住房公积金个人部分*/
	@Excel(name = "扣住房公积金个人部分", width = 15)
    @ApiModelProperty(value = "扣住房公积金个人部分")
	private java.math.BigDecimal housingaccumindividpart;
	/**个税扣回*/
	@Excel(name = "个税扣回", width = 15)
    @ApiModelProperty(value = "个税扣回")
	private java.math.BigDecimal taxwages;
	/** 餐费扣回*/
	@Excel(name = " 餐费扣回", width = 15)
    @ApiModelProperty(value = " 餐费扣回")
	private java.math.BigDecimal mealswages;
	/**已发基本工资扣回*/
	@Excel(name = "已发基本工资扣回", width = 15)
    @ApiModelProperty(value = "已发基本工资扣回")
	private java.math.BigDecimal basicsalarydedcted;
	/**预发工资扣回*/
	@Excel(name = "预发工资扣回", width = 15)
    @ApiModelProperty(value = "预发工资扣回")
	private java.math.BigDecimal holdingadvancesalary;
	/**受处分应扣工资*/
	@Excel(name = "受处分应扣工资", width = 15)
    @ApiModelProperty(value = "受处分应扣工资")
	private java.math.BigDecimal sdeductedwage;
	/**已发奖金扣回*/
	@Excel(name = "已发奖金扣回", width = 15)
    @ApiModelProperty(value = "已发奖金扣回")
	private java.math.BigDecimal bonushasbeendeducted;
	/**法院扣款*/
	@Excel(name = "法院扣款", width = 15)
    @ApiModelProperty(value = "法院扣款")
	private java.math.BigDecimal courtdeductions;
	/**信贷管理部罚款*/
	@Excel(name = "信贷管理部罚款", width = 15)
    @ApiModelProperty(value = "信贷管理部罚款")
	private java.math.BigDecimal creditmanagedepartfine;
	/**风险管理部罚款*/
	@Excel(name = "风险管理部罚款", width = 15)
    @ApiModelProperty(value = "风险管理部罚款")
	private java.math.BigDecimal riskmanagmentdepartmentfine;
	/**乡村振兴金融部罚款*/
	@Excel(name = "乡村振兴金融部罚款", width = 15)
    @ApiModelProperty(value = "乡村振兴金融部罚款")
	private java.math.BigDecimal ruralrevitalizationfine;
	/**合规管理部罚款*/
	@Excel(name = "合规管理部罚款", width = 15)
    @ApiModelProperty(value = "合规管理部罚款")
	private java.math.BigDecimal compliancemanagdepartment;
	/**稽核审计部罚款*/
	@Excel(name = "稽核审计部罚款", width = 15)
    @ApiModelProperty(value = "稽核审计部罚款")
	private java.math.BigDecimal auditdepartmentfine;
	/**财务会计部罚款*/
	@Excel(name = "财务会计部罚款", width = 15)
    @ApiModelProperty(value = "财务会计部罚款")
	private java.math.BigDecimal accountdepartmentfine;
	/**信息科技部罚款*/
	@Excel(name = "信息科技部罚款", width = 15)
    @ApiModelProperty(value = "信息科技部罚款")
	private java.math.BigDecimal technologyfine;
	/**其他罚款*/
	@Excel(name = "其他罚款", width = 15)
    @ApiModelProperty(value = "其他罚款")
	private java.math.BigDecimal otherfine;
	/**主键id*/
	@Excel(name = "主键id", width = 15)
    @ApiModelProperty(value = "主键id")
	private String baseId;
	/**工资月份*/
	@Excel(name = "工资月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "工资月份")
	private Date gzyf;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	@TableId(type = IdType.ASSIGN_ID)
	private Long xh;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**基本工资合计*/
	@Excel(name = "基本工资合计", width = 15)
    @ApiModelProperty(value = "基本工资合计")
	private java.math.BigDecimal jbgzhj;
	/**其他工资合计*/
	@Excel(name = "其他工资合计", width = 15)
    @ApiModelProperty(value = "其他工资合计")
	private java.math.BigDecimal qtgzhj;
	/**代缴代扣合计*/
	@Excel(name = "代缴代扣合计", width = 15)
    @ApiModelProperty(value = "代缴代扣合计")
	private java.math.BigDecimal djdkhj;
	/**实发金额*/
	@Excel(name = "实发金额", width = 15)
    @ApiModelProperty(value = "实发金额")
	private java.math.BigDecimal sfje;
	private java.math.BigDecimal totalBasicSalary;
	private java.math.BigDecimal otherBasicSalary;
	private java.math.BigDecimal payBotherSum;
	private java.math.BigDecimal netAmount;

	@Excel(name = "自媒体推文考核", width = 15)
	@ApiModelProperty(value = "自媒体推文考核")
	private java.math.BigDecimal zmttwkh;
	@Excel(name = "乡村振兴经费", width = 15)
	@ApiModelProperty(value = "乡村振兴经费")
	private java.math.BigDecimal xccxjf;
	@Excel(name = "资质奖励", width = 15)
	@ApiModelProperty(value = "资质奖励")
	private java.math.BigDecimal zzjl;
	@Excel(name = "竞赛奖金", width = 15)
	@ApiModelProperty(value = "竞赛奖金")
	private java.math.BigDecimal jsjj;
	@Excel(name = "残疾人补助", width = 15)
	@ApiModelProperty(value = "残疾人补助")
	private java.math.BigDecimal cjrbz;
	@Excel(name = "防暑费", width = 15)
	@ApiModelProperty(value = "防暑费")
	private java.math.BigDecimal fsf;
	@Excel(name = "安全奖+案防奖", width = 15)
	@ApiModelProperty(value = "安全奖+案防奖")
	private java.math.BigDecimal aqjafj;
	@Excel(name = "工会经费", width = 15)
	@ApiModelProperty(value = "工会经费")
	private java.math.BigDecimal ghjf;
	@Excel(name = "劳动保护费", width = 15)
	@ApiModelProperty(value = "劳动保护费")
	private java.math.BigDecimal ldbhf;
	@Excel(name = "信息宣传考核奖", width = 15)
	@ApiModelProperty(value = "信息宣传考核奖")
	private java.math.BigDecimal xxxckhj;

}
