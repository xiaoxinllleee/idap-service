package org.cmms.modules.sjxf.xdxt.dyzywhtxx.entity;

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
 * @Description: 抵押质押物合同信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_guaranty_detail")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_guaranty_detail对象", description="抵押质押物合同信息")
public class Dyzywhtxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**被保险人*/
	@Excel(name = "被保险人", width = 15)
    @ApiModelProperty(value = "被保险人")
	private String insurant;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
	private String insureBeginDate;
	/**保单号码*/
	@Excel(name = "保单号码", width = 15)
    @ApiModelProperty(value = "保单号码")
	private String insureCertNo;
	/**抵押物保险单编号*/
	@Excel(name = "抵押物保险单编号", width = 15)
    @ApiModelProperty(value = "抵押物保险单编号")
	private String insureChangeDate;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String insureCurrency;
	/**责任情况(66*3)*/
	@Excel(name = "责任情况(66*3)", width = 15)
    @ApiModelProperty(value = "责任情况(66*3)")
	private String insureDescribe;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String insureEndDate;
	/**保险金额*/
	@Excel(name = "保险金额", width = 15)
    @ApiModelProperty(value = "保险金额")
	private String insureSum;
	/**保险品种*/
	@Excel(name = "保险品种", width = 15)
    @ApiModelProperty(value = "保险品种")
	private String insureType;
	/**公司代码*/
	@Excel(name = "公司代码", width = 15)
    @ApiModelProperty(value = "公司代码")
	private String insurerCode;
	/**公司名称*/
	@Excel(name = "公司名称", width = 15)
    @ApiModelProperty(value = "公司名称")
	private String insurerName;
	/**是否变更*/
	@Excel(name = "是否变更", width = 15)
    @ApiModelProperty(value = "是否变更")
	private String isChange;
	/**是否核保*/
	@Excel(name = "是否核保", width = 15)
    @ApiModelProperty(value = "是否核保")
	private String isCheck;
	/**是否评估*/
	@Excel(name = "是否评估", width = 15)
    @ApiModelProperty(value = "是否评估")
	private String isEval;
	/**是否登记*/
	@Excel(name = "是否登记", width = 15)
    @ApiModelProperty(value = "是否登记")
	private String isGuaranty;
	/**是否保险*/
	@Excel(name = "是否保险", width = 15)
    @ApiModelProperty(value = "是否保险")
	private String isInsure;
	/**是否监管*/
	@Excel(name = "是否监管", width = 15)
    @ApiModelProperty(value = "是否监管")
	private String isMonitor;
	/**是否公正*/
	@Excel(name = "是否公正", width = 15)
    @ApiModelProperty(value = "是否公正")
	private String isNotary;
	/**权属情况,他人质权*/
	@Excel(name = "权属情况,他人质权", width = 15)
    @ApiModelProperty(value = "权属情况,他人质权")
	private String isOtherSignee;
	/**登记证入库*/
	@Excel(name = "登记证入库", width = 15)
    @ApiModelProperty(value = "登记证入库")
	private String isRegister;
	/**是否租赁*/
	@Excel(name = "是否租赁", width = 15)
    @ApiModelProperty(value = "是否租赁")
	private String isTenancy;
	/**抵押期限质押期限*/
	@Excel(name = "抵押期限质押期限", width = 15)
    @ApiModelProperty(value = "抵押期限质押期限")
	private String maturity;
	/**监管单位名称*/
	@Excel(name = "监管单位名称", width = 15)
    @ApiModelProperty(value = "监管单位名称")
	private String monitorDeptName;
	/**监管描述*/
	@Excel(name = "监管描述", width = 15)
    @ApiModelProperty(value = "监管描述")
	private String monitorDesc;
	/**监管编号*/
	@Excel(name = "监管编号", width = 15)
    @ApiModelProperty(value = "监管编号")
	private String monitorNo;
	/**公证人*/
	@Excel(name = "公证人", width = 15)
    @ApiModelProperty(value = "公证人")
	private String notary;
	/**公正日期*/
	@Excel(name = "公正日期", width = 15)
    @ApiModelProperty(value = "公正日期")
	private String notaryDate;
	/**公正机构*/
	@Excel(name = "公正机构", width = 15)
    @ApiModelProperty(value = "公正机构")
	private String notaryOrg;
	/**其它约定*/
	@Excel(name = "其它约定", width = 15)
    @ApiModelProperty(value = "其它约定")
	private String otherAssumpsit;
	/**权属所有人编号*/
	@Excel(name = "权属所有人编号", width = 15)
    @ApiModelProperty(value = "权属所有人编号")
	private String othermanId;
	/**注销日期*/
	@Excel(name = "注销日期", width = 15)
    @ApiModelProperty(value = "注销日期")
	private String outputDate;
	/**缴款开始日期*/
	@Excel(name = "缴款开始日期", width = 15)
    @ApiModelProperty(value = "缴款开始日期")
	private String payBeginDate;
	/**缴款结束日*/
	@Excel(name = "缴款结束日", width = 15)
    @ApiModelProperty(value = "缴款结束日")
	private String payEndDate;
	/**缴款方*/
	@Excel(name = "缴款方", width = 15)
    @ApiModelProperty(value = "缴款方")
	private String payMethod;
	/**缴款期限*/
	@Excel(name = "缴款期限", width = 15)
    @ApiModelProperty(value = "缴款期限")
	private String payTerm;
	/**投保人*/
	@Excel(name = "投保人", width = 15)
    @ApiModelProperty(value = "投保人")
	private String policyHolder;
	/**占有份额*/
	@Excel(name = "占有份额", width = 15)
    @ApiModelProperty(value = "占有份额")
	private java.math.BigDecimal possessRate;
	/**开始时间,买入日期,起始日期*/
	@Excel(name = "开始时间,买入日期,起始日期", width = 15)
    @ApiModelProperty(value = "开始时间,买入日期,起始日期")
	private String purchaseDate;
	/**登记金额*/
	@Excel(name = "登记金额", width = 15)
    @ApiModelProperty(value = "登记金额")
	private java.math.BigDecimal registerValue;
	/**暂末用*/
	@Excel(name = "暂末用", width = 15)
    @ApiModelProperty(value = "暂末用")
	private String relativeContract;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**出质人名称*/
	@Excel(name = "出质人名称", width = 15)
    @ApiModelProperty(value = "出质人名称")
	private String signeeName;
	/**抵押方式*/
	@Excel(name = "抵押方式", width = 15)
    @ApiModelProperty(value = "抵押方式")
	private String signeeType;
	/**担保关系*/
	@Excel(name = "担保关系", width = 15)
    @ApiModelProperty(value = "担保关系")
	private String sureRelation;
	/**担保保证期限*/
	@Excel(name = "担保保证期限", width = 15)
    @ApiModelProperty(value = "担保保证期限")
	private String suretyTerm;
	/**每期缴款金额*/
	@Excel(name = "每期缴款金额", width = 15)
    @ApiModelProperty(value = "每期缴款金额")
	private java.math.BigDecimal termPaySum;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String userId;
	/**已使用金额*/
	@Excel(name = "已使用金额", width = 15)
    @ApiModelProperty(value = "已使用金额")
	private java.math.BigDecimal vouchValue;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**风险预警比例*/
	@Excel(name = "风险预警比例", width = 15)
    @ApiModelProperty(value = "风险预警比例")
	private java.math.BigDecimal forecastRiskRatio;
	/**抵质押方式1*/
	@Excel(name = "抵质押方式1", width = 15)
    @ApiModelProperty(value = "抵质押方式1")
	private String guarantyDetail1;
	/**抵质押方式2*/
	@Excel(name = "抵质押方式2", width = 15)
    @ApiModelProperty(value = "抵质押方式2")
	private String guarantyDetail2;
	/**争议解决方式*/
	@Excel(name = "争议解决方式", width = 15)
    @ApiModelProperty(value = "争议解决方式")
	private String jjfs;
	/**仲裁机构全称*/
	@Excel(name = "仲裁机构全称", width = 15)
    @ApiModelProperty(value = "仲裁机构全称")
	private String zcjg;
	/**其他事项*/
	@Excel(name = "其他事项", width = 15)
    @ApiModelProperty(value = "其他事项")
	private String qtsx;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
	private String qyrq;
	/**签约地点*/
	@Excel(name = "签约地点", width = 15)
    @ApiModelProperty(value = "签约地点")
	private String qydd;
	/**合同份数*/
	@Excel(name = "合同份数", width = 15)
    @ApiModelProperty(value = "合同份数")
	private String fs;
	/**持票人,收益人,受益人*/
	@Excel(name = "持票人,收益人,受益人", width = 15)
    @ApiModelProperty(value = "持票人,收益人,受益人")
	private String beneficiary;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**评估机构资质证结止日*/
	@Excel(name = "评估机构资质证结止日", width = 15)
    @ApiModelProperty(value = "评估机构资质证结止日")
	private String certificateEnddate;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
	private String checkAssureDesc;
	/**核保人编号*/
	@Excel(name = "核保人编号", width = 15)
    @ApiModelProperty(value = "核保人编号")
	private String checkAssureId;
	/**变现能力*/
	@Excel(name = "变现能力", width = 15)
    @ApiModelProperty(value = "变现能力")
	private String chgCashAble;
	/**评估价格,质押物金额*/
	@Excel(name = "评估价格,质押物金额", width = 15)
    @ApiModelProperty(value = "评估价格,质押物金额")
	private java.math.BigDecimal confirmValue;
	/**抵押合同号*/
	@Excel(name = "抵押合同号", width = 15)
    @ApiModelProperty(value = "抵押合同号")
	private String contractNo;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**登记证存放地*/
	@Excel(name = "登记证存放地", width = 15)
    @ApiModelProperty(value = "登记证存放地")
	private String depositary;
	/**出质人现地址*/
	@Excel(name = "出质人现地址", width = 15)
    @ApiModelProperty(value = "出质人现地址")
	private String describe1;
	/**出质人电话*/
	@Excel(name = "出质人电话", width = 15)
    @ApiModelProperty(value = "出质人电话")
	private String describe2;
	/**委托书编号*/
	@Excel(name = "委托书编号", width = 15)
    @ApiModelProperty(value = "委托书编号")
	private String describe3;
	/**委托人*/
	@Excel(name = "委托人", width = 15)
    @ApiModelProperty(value = "委托人")
	private String describe4;
	/**受托人*/
	@Excel(name = "受托人", width = 15)
    @ApiModelProperty(value = "受托人")
	private String describe5;
	/**委托日期*/
	@Excel(name = "委托日期", width = 15)
    @ApiModelProperty(value = "委托日期")
	private String describe6;
	/**内容描述*/
	@Excel(name = "内容描述", width = 15)
    @ApiModelProperty(value = "内容描述")
	private String describe7;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String describe8;
	/**评估编号*/
	@Excel(name = "评估编号", width = 15)
    @ApiModelProperty(value = "评估编号")
	private String evalCardNo;
	/**评估时间*/
	@Excel(name = "评估时间", width = 15)
    @ApiModelProperty(value = "评估时间")
	private String evalDate;
	/**评估方式*/
	@Excel(name = "评估方式", width = 15)
    @ApiModelProperty(value = "评估方式")
	private String evalMethod;
	/**评估机构*/
	@Excel(name = "评估机构", width = 15)
    @ApiModelProperty(value = "评估机构")
	private String evalName;
	/**抵押物价值,评估净值,质物价值*/
	@Excel(name = "抵押物价值,评估净值,质物价值", width = 15)
    @ApiModelProperty(value = "抵押物价值,评估净值,质物价值")
	private java.math.BigDecimal evalNetValue;
	/**评估机构代码*/
	@Excel(name = "评估机构代码", width = 15)
    @ApiModelProperty(value = "评估机构代码")
	private String evalOrgId;
	/**评估机构名称*/
	@Excel(name = "评估机构名称", width = 15)
    @ApiModelProperty(value = "评估机构名称")
	private String evalOrgName;
	/**评估有效期*/
	@Excel(name = "评估有效期", width = 15)
    @ApiModelProperty(value = "评估有效期")
	private String evalTerm;
	/**认定价值*/
	@Excel(name = "认定价值", width = 15)
    @ApiModelProperty(value = "认定价值")
	private java.math.BigDecimal evalValue;
	/**评估机构资质*/
	@Excel(name = "评估机构资质", width = 15)
    @ApiModelProperty(value = "评估机构资质")
	private String evalorgLevel;
	/**解除担保时间*/
	@Excel(name = "解除担保时间", width = 15)
    @ApiModelProperty(value = "解除担保时间")
	private String freeSureDate;
	/**是否冻结*/
	@Excel(name = "是否冻结", width = 15)
    @ApiModelProperty(value = "是否冻结")
	private String freezed;
	/**担保物数量*/
	@Excel(name = "担保物数量", width = 15)
    @ApiModelProperty(value = "担保物数量")
	private String guarantyAmount;
	/**物品性质*/
	@Excel(name = "物品性质", width = 15)
    @ApiModelProperty(value = "物品性质")
	private String guarantyAttribute;
	/**抵/质押代码*/
	@Excel(name = "抵/质押代码", width = 15)
    @ApiModelProperty(value = "抵/质押代码")
	private String guarantyCode;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String guarantyCurrency;
	/**担保物简述*/
	@Excel(name = "担保物简述", width = 15)
    @ApiModelProperty(value = "担保物简述")
	private String guarantyDescribe;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String guarantyDetailId;
	/**暂末用*/
	@Excel(name = "暂末用", width = 15)
    @ApiModelProperty(value = "暂末用")
	private String guarantyId;
	/**存放地点*/
	@Excel(name = "存放地点", width = 15)
    @ApiModelProperty(value = "存放地点")
	private String guarantyLocation;
	/**抵押物名称,质物名称,质押物名称*/
	@Excel(name = "抵押物名称,质物名称,质押物名称", width = 15)
    @ApiModelProperty(value = "抵押物名称,质物名称,质押物名称")
	private String guarantyName;
	/**公证书编号*/
	@Excel(name = "公证书编号", width = 15)
    @ApiModelProperty(value = "公证书编号")
	private String guarantyNdNo;
	/**抵押物编号,质押物编号*/
	@Excel(name = "抵押物编号,质押物编号", width = 15)
    @ApiModelProperty(value = "抵押物编号,质押物编号")
	private String guarantyNo;
	/**抵押率,质押率,担保率,折扣率*/
	@Excel(name = "抵押率,质押率,担保率,折扣率", width = 15)
    @ApiModelProperty(value = "抵押率,质押率,担保率,折扣率")
	private java.math.BigDecimal guarantyRate;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String guarantyRegDate;
	/**登记编号*/
	@Excel(name = "登记编号", width = 15)
    @ApiModelProperty(value = "登记编号")
	private String guarantyRegNo;
	/**登记机构*/
	@Excel(name = "登记机构", width = 15)
    @ApiModelProperty(value = "登记机构")
	private String guarantyRegOrg;
	/**抵押物类型*/
	@Excel(name = "抵押物类型", width = 15)
    @ApiModelProperty(value = "抵押物类型")
	private String guarantySort;
	/**暂末用*/
	@Excel(name = "暂末用", width = 15)
    @ApiModelProperty(value = "暂末用")
	private String guarantyStatus;
	/**抵/质押期限*/
	@Excel(name = "抵/质押期限", width = 15)
    @ApiModelProperty(value = "抵/质押期限")
	private String guarantyTerm;
	/**账面价值*/
	@Excel(name = "账面价值", width = 15)
    @ApiModelProperty(value = "账面价值")
	private java.math.BigDecimal guarantyTxtSum;
	/**抵押物种类,质物种类*/
	@Excel(name = "抵押物种类,质物种类", width = 15)
    @ApiModelProperty(value = "抵押物种类,质物种类")
	private String guarantyType;
	/**担保总价值*/
	@Excel(name = "担保总价值", width = 15)
    @ApiModelProperty(value = "担保总价值")
	private java.math.BigDecimal guarantyValue;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String guarantyWoDate;
	/**抵押期限,质押期限*/
	@Excel(name = "抵押期限,质押期限", width = 15)
    @ApiModelProperty(value = "抵押期限,质押期限")
	private String holdTerm;
	/**抵押金额,质物金额*/
	@Excel(name = "抵押金额,质物金额", width = 15)
    @ApiModelProperty(value = "抵押金额,质物金额")
	private java.math.BigDecimal implValue;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String inputDate;
	/**抵质押人类型*/
	@Excel(name = "抵质押人类型", width = 15)
    @ApiModelProperty(value = "抵质押人类型")
	private String signeeCustType;
	/**通知书号*/
	@Excel(name = "通知书号", width = 15)
    @ApiModelProperty(value = "通知书号")
	private String sid;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String flag;
	/**已出库金额*/
	@Excel(name = "已出库金额", width = 15)
    @ApiModelProperty(value = "已出库金额")
	private java.math.BigDecimal outputValue;
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
