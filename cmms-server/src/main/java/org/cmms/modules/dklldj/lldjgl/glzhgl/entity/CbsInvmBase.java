package org.cmms.modules.dklldj.lldjgl.glzhgl.entity;

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
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Data
@TableName("Cbs_invm_base")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_invm_base对象", description="1")
public class CbsInvmBase {
	
	/**账户启用日期*/
	@Excel(name = "账户启用日期", width = 15)
    @ApiModelProperty(value = "账户启用日期")
	private String acctEfctDate;
	/**年审日期*/
	@Excel(name = "年审日期", width = 15)
    @ApiModelProperty(value = "年审日期")
	private String annlReviewDate;
	/**核准号*/
	@Excel(name = "核准号", width = 15)
    @ApiModelProperty(value = "核准号")
	private String aprlNo;
	/**是否违约标示*/
	@Excel(name = "是否违约标示", width = 15)
    @ApiModelProperty(value = "是否违约标示")
	private String breakRuleInd;
	/**取现有效期*/
	@Excel(name = "取现有效期", width = 15)
    @ApiModelProperty(value = "取现有效期")
	private String cashWdlExpDate;
	/**对公账户自动冻结到期日*/
	@Excel(name = "对公账户自动冻结到期日", width = 15)
    @ApiModelProperty(value = "对公账户自动冻结到期日")
	private String expDateAutoFrz;
	/**外币代码*/
	@Excel(name = "外币代码", width = 15)
    @ApiModelProperty(value = "外币代码")
	private String fcyCode;
	/**首次存入金额*/
	@Excel(name = "首次存入金额", width = 15)
    @ApiModelProperty(value = "首次存入金额")
	private java.math.BigDecimal firstDepAmt;
	/**绿色账户标识*/
	@Excel(name = "绿色账户标识", width = 15)
    @ApiModelProperty(value = "绿色账户标识")
	private String greenAcctInd;
	/**绿色账户到期日*/
	@Excel(name = "绿色账户到期日", width = 15)
    @ApiModelProperty(value = "绿色账户到期日")
	private String greenExpiryDt;
	/**变更开户行后新的分行号*/
	@Excel(name = "变更开户行后新的分行号", width = 15)
    @ApiModelProperty(value = "变更开户行后新的分行号")
	private String newBranchNo;
	/**国债账户标示*/
	@Excel(name = "国债账户标示", width = 15)
    @ApiModelProperty(value = "国债账户标示")
	private String ngbAcctInd;
	/**止存数目*/
	@Excel(name = "止存数目", width = 15)
    @ApiModelProperty(value = "止存数目")
	private Integer noOfStopDep;
	/**止取数目*/
	@Excel(name = "止取数目", width = 15)
    @ApiModelProperty(value = "止取数目")
	private Integer noOfStopWdl;
	/**账户原到期日*/
	@Excel(name = "账户原到期日", width = 15)
    @ApiModelProperty(value = "账户原到期日")
	private String orinMatDt;
	/**部提金额*/
	@Excel(name = "部提金额", width = 15)
    @ApiModelProperty(value = "部提金额")
	private java.math.BigDecimal partialWdlAmt;
	/**部提次数*/
	@Excel(name = "部提次数", width = 15)
    @ApiModelProperty(value = "部提次数")
	private String partialWdlCnt;
	/**实存次数*/
	@Excel(name = "实存次数", width = 15)
    @ApiModelProperty(value = "实存次数")
	private Integer realDepCnt;
	/**临时户到期日*/
	@Excel(name = "临时户到期日", width = 15)
    @ApiModelProperty(value = "临时户到期日")
	private String tempAcctExpDt;
	/**特殊转账限制标示*/
	@Excel(name = "特殊转账限制标示", width = 15)
    @ApiModelProperty(value = "特殊转账限制标示")
	private String tfrRestFlag;
	/**标识该账户是否修改过产品类型*/
	@Excel(name = "标识该账户是否修改过产品类型", width = 15)
    @ApiModelProperty(value = "标识该账户是否修改过产品类型")
	private String acctTypeChgFlag;
	/**标识是否是ATM账户*/
	@Excel(name = "标识是否是ATM账户", width = 15)
    @ApiModelProperty(value = "标识是否是ATM账户")
	private String atmAcctFlag;
	/**费用豁免设置*/
	@Excel(name = "费用豁免设置", width = 15)
    @ApiModelProperty(value = "费用豁免设置")
	private String chrExemProfile;
	/**贷记分层利率的分组ID*/
	@Excel(name = "贷记分层利率的分组ID", width = 15)
    @ApiModelProperty(value = "贷记分层利率的分组ID")
	private String crTierGrpId;
	/**冻结到期日*/
	@Excel(name = "冻结到期日", width = 15)
    @ApiModelProperty(value = "冻结到期日")
	private String freezeExpDate;
	/**冻结金额*/
	@Excel(name = "冻结金额", width = 15)
    @ApiModelProperty(value = "冻结金额")
	private java.math.BigDecimal holdVal;
	/**累计利息金额*/
	@Excel(name = "累计利息金额", width = 15)
    @ApiModelProperty(value = "累计利息金额")
	private java.math.BigDecimal intAvailable;
	/**定期累计的期内贷记利息*/
	@Excel(name = "定期累计的期内贷记利息", width = 15)
    @ApiModelProperty(value = "定期累计的期内贷记利息")
	private java.math.BigDecimal typeIntAccr;
	/**转存约定的标示*/
	@Excel(name = "转存约定的标示", width = 15)
    @ApiModelProperty(value = "转存约定的标示")
	private String termManRollNo;
	/**已发生的冻结数目*/
	@Excel(name = "已发生的冻结数目", width = 15)
    @ApiModelProperty(value = "已发生的冻结数目")
	private Integer noOfHolds;
	/**账户止付数目*/
	@Excel(name = "账户止付数目", width = 15)
    @ApiModelProperty(value = "账户止付数目")
	private Integer noOfStops;
	/**大字段*/
	@Excel(name = "大字段", width = 15)
    @ApiModelProperty(value = "大字段")
	private String odArea;
	/**标识该透支户是否链接有活期户*/
	@Excel(name = "标识该透支户是否链接有活期户", width = 15)
    @ApiModelProperty(value = "标识该透支户是否链接有活期户")
	private String odLinkInd;
	/**大字段*/
	@Excel(name = "大字段", width = 15)
    @ApiModelProperty(value = "大字段")
	private String odVisaArea;
	/**是否使用SWEEP处理*/
	@Excel(name = "是否使用SWEEP处理", width = 15)
    @ApiModelProperty(value = "是否使用SWEEP处理")
	private String sweepAcctFlag;
	/**标识该户是否为柜员专用账户*/
	@Excel(name = "标识该户是否为柜员专用账户", width = 15)
    @ApiModelProperty(value = "标识该户是否为柜员专用账户")
	private String telrAcctProd;
	/**转账账户*/
	@Excel(name = "转账账户", width = 15)
    @ApiModelProperty(value = "转账账户")
	private String trfAcctNo;
	/**可变利率*/
	@Excel(name = "可变利率", width = 15)
    @ApiModelProperty(value = "可变利率")
	private java.math.BigDecimal varIntRate;
	/**利息税类型*/
	@Excel(name = "利息税类型", width = 15)
    @ApiModelProperty(value = "利息税类型")
	private String withTaxType;
	/**钞汇标志*/
	@Excel(name = "钞汇标志", width = 15)
    @ApiModelProperty(value = "钞汇标志")
	private String fcyCashTrf;
	/**本币账户属性*/
	@Excel(name = "本币账户属性", width = 15)
    @ApiModelProperty(value = "本币账户属性")
	private String lcyAcctType;
	/**透支标志*/
	@Excel(name = "透支标志", width = 15)
    @ApiModelProperty(value = "透支标志")
	private String odIndicator;
	/**产品标识*/
	@Excel(name = "产品标识", width = 15)
    @ApiModelProperty(value = "产品标识")
	private String productInd;
	/**定期基期*/
	@Excel(name = "定期基期", width = 15)
    @ApiModelProperty(value = "定期基期")
	private String termBasis;
	/**定期最大存期*/
	@Excel(name = "定期最大存期", width = 15)
    @ApiModelProperty(value = "定期最大存期")
	private String termDaysMax;
	/**账户组别*/
	@Excel(name = "账户组别", width = 15)
    @ApiModelProperty(value = "账户组别")
	private String acctGrp;
	/**账户处理标示*/
	@Excel(name = "账户处理标示", width = 15)
    @ApiModelProperty(value = "账户处理标示")
	private String acctDesc;
	/**上期起息日*/
	@Excel(name = "上期起息日", width = 15)
    @ApiModelProperty(value = "上期起息日")
	private String sIntFrmDt;
	/**境内外标志*/
	@Excel(name = "境内外标志", width = 15)
    @ApiModelProperty(value = "境内外标志")
	private String inoutFlag;
	/**借记利息调整金额*/
	@Excel(name = "借记利息调整金额", width = 15)
    @ApiModelProperty(value = "借记利息调整金额")
	private java.math.BigDecimal drIntAdjustment;
	/**累计Commitment利息调整*/
	@Excel(name = "累计Commitment利息调整", width = 15)
    @ApiModelProperty(value = "累计Commitment利息调整")
	private java.math.BigDecimal fccCiAdjAccr;
	/**定期利息调整*/
	@Excel(name = "定期利息调整", width = 15)
    @ApiModelProperty(value = "定期利息调整")
	private java.math.BigDecimal trmIntAccr;
	/**利息增量*/
	@Excel(name = "利息增量", width = 15)
    @ApiModelProperty(value = "利息增量")
	private java.math.BigDecimal contractIntIncr;
	/**产品定活处理*/
	@Excel(name = "产品定活处理", width = 15)
    @ApiModelProperty(value = "产品定活处理")
	private String acctDescDepp;
	/**贷记本期开始日期*/
	@Excel(name = "贷记本期开始日期", width = 15)
    @ApiModelProperty(value = "贷记本期开始日期")
	private String crSopDate;
	/**贷记本期结束日期*/
	@Excel(name = "贷记本期结束日期", width = 15)
    @ApiModelProperty(value = "贷记本期结束日期")
	private String drEopDate;
	/**产品编码*/
	@Excel(name = "产品编码", width = 15)
    @ApiModelProperty(value = "产品编码")
	private String crIdDet;
	/**最近账户维护日期*/
	@Excel(name = "最近账户维护日期", width = 15)
    @ApiModelProperty(value = "最近账户维护日期")
	private String lstMntDt;
	/**最近一次贷方金融交易的发生日期*/
	@Excel(name = "最近一次贷方金融交易的发生日期", width = 15)
    @ApiModelProperty(value = "最近一次贷方金融交易的发生日期")
	private Integer lstCustCrDt;
	/**最近一次借方金融交易的发生日期*/
	@Excel(name = "最近一次借方金融交易的发生日期", width = 15)
    @ApiModelProperty(value = "最近一次借方金融交易的发生日期")
	private Integer lstCustDrDt;
	/**年度平均余额（从开户日或上一总账年末日期开始）*/
	@Excel(name = "年度平均余额（从开户日或上一总账年末日期开始）", width = 15)
    @ApiModelProperty(value = "年度平均余额（从开户日或上一总账年末日期开始）")
	private java.math.BigDecimal netAvBalYtd;
	/**计算年度平均余额的天数*/
	@Excel(name = "计算年度平均余额的天数", width = 15)
    @ApiModelProperty(value = "计算年度平均余额的天数")
	private Integer netAvNoYtd;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String accNo;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String acctOpenDt;
	/**产品大类*/
	@Excel(name = "产品大类", width = 15)
    @ApiModelProperty(value = "产品大类")
	private String acctType;
	/**产品子类*/
	@Excel(name = "产品子类", width = 15)
    @ApiModelProperty(value = "产品子类")
	private String intCat;
	/**产品代码*/
	@Excel(name = "产品代码", width = 15)
    @ApiModelProperty(value = "产品代码")
	private String productCode;
	/**账号产品名称*/
	@Excel(name = "账号产品名称", width = 15)
    @ApiModelProperty(value = "账号产品名称")
	private String productName;
	/**主账号*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String mastAcct;
	/**子账号*/
	@Excel(name = "子账号", width = 15)
    @ApiModelProperty(value = "子账号")
	private String subAcctNo;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String custName;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String identType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**子账号编码*/
	@Excel(name = "子账号编码", width = 15)
    @ApiModelProperty(value = "子账号编码")
	private String subAcctCode;
	/**子账号币别*/
	@Excel(name = "子账号币别", width = 15)
    @ApiModelProperty(value = "子账号币别")
	private String subAcctCurr;
	/**子账号类型*/
	@Excel(name = "子账号类型", width = 15)
    @ApiModelProperty(value = "子账号类型")
	private String subAcctCurrType;
	/**账户类型标识*/
	@Excel(name = "账户类型标识", width = 15)
    @ApiModelProperty(value = "账户类型标识")
	private String mcaInd;
	/**来源tab标识*/
	@Excel(name = "来源tab标识", width = 15)
    @ApiModelProperty(value = "来源tab标识")
	private String fromTab;
	/**凭证类型*/
	@Excel(name = "凭证类型", width = 15)
    @ApiModelProperty(value = "凭证类型")
	private String voucherType;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String voucherNo;
	/**册号*/
	@Excel(name = "册号", width = 15)
    @ApiModelProperty(value = "册号")
	private String pbVolumeNo;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String pbSequenceNo;
	/**累计余额*/
	@Excel(name = "累计余额", width = 15)
    @ApiModelProperty(value = "累计余额")
	private java.math.BigDecimal defaultBal;
	/**开户机构号*/
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	private String branchNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String branchName;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String currStatus;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currency;
	/**当前余额*/
	@Excel(name = "当前余额", width = 15)
    @ApiModelProperty(value = "当前余额")
	private java.math.BigDecimal currBal;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String customerNo;
	/**所属客户类型码*/
	@Excel(name = "所属客户类型码", width = 15)
    @ApiModelProperty(value = "所属客户类型码")
	private String belongCusttype;
	/**所属客户类型描述*/
	@Excel(name = "所属客户类型描述", width = 15)
    @ApiModelProperty(value = "所属客户类型描述")
	private String belongCustdesc;
	/**定活期类型码*/
	@Excel(name = "定活期类型码", width = 15)
    @ApiModelProperty(value = "定活期类型码")
	private String savingType;
	/**定活期类型描述*/
	@Excel(name = "定活期类型描述", width = 15)
    @ApiModelProperty(value = "定活期类型描述")
	private String savinDesc;
	/**账户类型码*/
	@Excel(name = "账户类型码", width = 15)
    @ApiModelProperty(value = "账户类型码")
	private String productNo;
	/**账户类型描述*/
	@Excel(name = "账户类型描述", width = 15)
    @ApiModelProperty(value = "账户类型描述")
	private String acctName;
	/**最近一笔金融交易的发生日期*/
	@Excel(name = "最近一笔金融交易的发生日期", width = 15)
    @ApiModelProperty(value = "最近一笔金融交易的发生日期")
	private String lstFinDt;
	/**保存对当前户有效的贷记利率值*/
	@Excel(name = "保存对当前户有效的贷记利率值", width = 15)
    @ApiModelProperty(value = "保存对当前户有效的贷记利率值")
	private String crStoreRate;
	/**账户层边际利率百分比*/
	@Excel(name = "账户层边际利率百分比", width = 15)
    @ApiModelProperty(value = "账户层边际利率百分比")
	private String acctPctMgnRate;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String subjNo;
	/**科目号名称*/
	@Excel(name = "科目号名称", width = 15)
    @ApiModelProperty(value = "科目号名称")
	private String subjName;
	/**核算码*/
	@Excel(name = "核算码", width = 15)
    @ApiModelProperty(value = "核算码")
	private String glClassCode;
	/**本期起息日*/
	@Excel(name = "本期起息日", width = 15)
    @ApiModelProperty(value = "本期起息日")
	private String intFrmDt;
	/**本期截至日*/
	@Excel(name = "本期截至日", width = 15)
    @ApiModelProperty(value = "本期截至日")
	private String intToDt;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private String rate;
	/**输出子账号*/
	@Excel(name = "输出子账号", width = 15)
    @ApiModelProperty(value = "输出子账号")
	private String subAcctOut;
	/**到期日*/
	@Excel(name = "到期日", width = 15)
    @ApiModelProperty(value = "到期日")
	private String endDate;
	/**协议存款产品利率基准ID*/
	@Excel(name = "协议存款产品利率基准ID", width = 15)
    @ApiModelProperty(value = "协议存款产品利率基准ID")
	private String contractBaseId;
	/**协议存款产品利率ID*/
	@Excel(name = "协议存款产品利率ID", width = 15)
    @ApiModelProperty(value = "协议存款产品利率ID")
	private String contractRateId;
	/**推荐人*/
	@Excel(name = "推荐人", width = 15)
    @ApiModelProperty(value = "推荐人")
	private String acNo;
}
