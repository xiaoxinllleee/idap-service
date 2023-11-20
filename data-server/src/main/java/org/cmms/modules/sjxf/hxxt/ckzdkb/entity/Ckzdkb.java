package org.cmms.modules.sjxf.hxxt.ckzdkb.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 存款主档宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbs_invm_base")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_invm_base对象", description="存款主档宽表")
public class Ckzdkb {

	/**dataDate*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**loadDate*/
	@Excel(name = "加载日期", width = 15)
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**accNo*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String accNo;
	/**acctOpenDt*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String acctOpenDt;
	/**acctType*/
	@Excel(name = "产品大类", width = 15)
    @ApiModelProperty(value = "产品大类")
	private String acctType;
	/**intCat*/
	@Excel(name = "产品子类", width = 15)
    @ApiModelProperty(value = "产品子类")
	private String intCat;
	/**productCode*/
	@Excel(name = "产品代码", width = 15)
    @ApiModelProperty(value = "产品代码")
	private String productCode;
	/**productName*/
	@Excel(name = "账号产品名称", width = 15)
    @ApiModelProperty(value = "账号产品名称")
	private String productName;
	/**mastAcct*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String mastAcct;
	/**subAcctNo*/
	@Excel(name = "子账号", width = 15)
    @ApiModelProperty(value = "子账号")
	private String subAcctNo;
	/**custName*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String custName;
	/**identType*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String identType;
	/**identNo*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**subAcctCode*/
	@Excel(name = "子账号编码", width = 15)
    @ApiModelProperty(value = "子账号编码")
	private String subAcctCode;
	/**subAcctCurr*/
	@Excel(name = "子账号币别", width = 15)
    @ApiModelProperty(value = "子账号币别")
	private String subAcctCurr;
	/**subAcctCurrType*/
	@Excel(name = "子账号类型", width = 15)
    @ApiModelProperty(value = "子账号类型")
	private String subAcctCurrType;
	/**mcaInd*/
	@Excel(name = "账户类型标识", width = 15)
    @ApiModelProperty(value = "账户类型标识")
	private String mcaInd;
	/**fromTab*/
	@Excel(name = "来源tab标识", width = 15)
    @ApiModelProperty(value = "来源tab标识")
	private String fromTab;
	/**voucherType*/
	@Excel(name = "凭证类型", width = 15)
    @ApiModelProperty(value = "凭证类型")
	private String voucherType;
	/**voucherNo*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String voucherNo;
	/**pbVolumeNo*/
	@Excel(name = "册号", width = 15)
    @ApiModelProperty(value = "册号")
	private String pbVolumeNo;
	/**pbSequenceNo*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String pbSequenceNo;
	/**defaultBal*/
	@Excel(name = "累计余额", width = 15)
    @ApiModelProperty(value = "累计余额")
	private java.math.BigDecimal defaultBal;
	/**branchNo*/
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String branchNo;
	/**branchName*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String branchName;
	/**currStatus*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	@Dict(dicCode = "ckzhzt")
	private String currStatus;
	/**currency*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currency;
	/**currBal*/
	@Excel(name = "当前余额", width = 15)
    @ApiModelProperty(value = "当前余额")
	private java.math.BigDecimal currBal;
	/**customerNo*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String customerNo;
	/**belongCusttype*/
	@Excel(name = "所属客户类型码", width = 15)
    @ApiModelProperty(value = "所属客户类型码")
	private String belongCusttype;
	/**belongCustdesc*/
	@Excel(name = "所属客户类型描述", width = 15)
    @ApiModelProperty(value = "所属客户类型描述")
	private String belongCustdesc;
	/**savingType*/
	@Excel(name = "定活期类型码", width = 15)
    @ApiModelProperty(value = "定活期类型码")
	private String savingType;
	/**savinDesc*/
	@Excel(name = "定活期类型描述", width = 15)
    @ApiModelProperty(value = "定活期类型描述")
	private String savinDesc;
	/**productNo*/
	@Excel(name = "账户类型码", width = 15)
    @ApiModelProperty(value = "账户类型码")
	private String productNo;
	/**acctName*/
	@Excel(name = "账户类型描述", width = 15)
    @ApiModelProperty(value = "账户类型描述")
	private String acctName;
	/**lstFinDt*/
	@Excel(name = "最近一笔金融交易的发生日期", width = 15)
    @ApiModelProperty(value = "最近一笔金融交易的发生日期")
	private String lstFinDt;
	/**crStoreRate*/
	@Excel(name = "保存对当前户有效的贷记利率值", width = 15)
    @ApiModelProperty(value = "保存对当前户有效的贷记利率值")
	private String crStoreRate;
	/**acctPctMgnRate*/
	@Excel(name = "账户层边际利率百分比", width = 15)
    @ApiModelProperty(value = "账户层边际利率百分比")
	private String acctPctMgnRate;
	/**subjNo*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String subjNo;
	/**subjName*/
	@Excel(name = "科目号名称", width = 15)
    @ApiModelProperty(value = "科目号名称")
	private String subjName;
	/**glClassCode*/
	@Excel(name = "核算码", width = 15)
    @ApiModelProperty(value = "核算码")
	private String glClassCode;
	/**intFrmDt*/
	@Excel(name = "本期起息日", width = 15)
    @ApiModelProperty(value = "本期起息日")
	private String intFrmDt;
	/**intToDt*/
	@Excel(name = "本期截至日", width = 15)
    @ApiModelProperty(value = "本期截至日")
	private String intToDt;
	/**rate*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private String rate;
	/**subAcctOut*/
	@Excel(name = "输出子账号", width = 15)
    @ApiModelProperty(value = "输出子账号")
	private String subAcctOut;
	/**endDate*/
	@Excel(name = "到期日", width = 15)
    @ApiModelProperty(value = "到期日")
	private String endDate;
	/**contractBaseId*/
	@Excel(name = "协议存款产品利率基准ID", width = 15)
    @ApiModelProperty(value = "协议存款产品利率基准ID")
	private String contractBaseId;
	/**contractRateId*/
	@Excel(name = "协议存款产品利率ID", width = 15)
    @ApiModelProperty(value = "协议存款产品利率ID")
	private String contractRateId;
	/**acNo*/
	@Excel(name = "推荐人", width = 15)
    @ApiModelProperty(value = "推荐人")
	private String acNo;
	/**acctEfctDate*/
	@Excel(name = "账户启用日期", width = 15)
    @ApiModelProperty(value = "账户启用日期")
	private String acctEfctDate;
	/**annlReviewDate*/
	@Excel(name = "年审日期", width = 15)
    @ApiModelProperty(value = "年审日期")
	private String annlReviewDate;
	/**aprlNo*/
	@Excel(name = "核准号", width = 15)
    @ApiModelProperty(value = "核准号")
	private String aprlNo;
	/**breakRuleInd*/
	@Excel(name = "是否违约标示", width = 15)
    @ApiModelProperty(value = "是否违约标示")
	private String breakRuleInd;
	/**cashWdlExpDate*/
	@Excel(name = "取现有效期", width = 15)
    @ApiModelProperty(value = "取现有效期")
	private String cashWdlExpDate;
	/**expDateAutoFrz*/
	@Excel(name = "对公账户自动冻结到期日", width = 15)
    @ApiModelProperty(value = "对公账户自动冻结到期日")
	private String expDateAutoFrz;
	/**fcyCode*/
	@Excel(name = "外币代码", width = 15)
    @ApiModelProperty(value = "外币代码")
	private String fcyCode;
	/**firstDepAmt*/
	@Excel(name = "首次存入金额", width = 15)
    @ApiModelProperty(value = "首次存入金额")
	private java.math.BigDecimal firstDepAmt;
	/**greenAcctInd*/
	@Excel(name = "绿色账户标识", width = 15)
    @ApiModelProperty(value = "绿色账户标识")
	private String greenAcctInd;
	/**greenExpiryDt*/
	@Excel(name = "绿色账户到期日", width = 15)
    @ApiModelProperty(value = "绿色账户到期日")
	private String greenExpiryDt;
	/**newBranchNo*/
	@Excel(name = "变更开户行后新的分行号", width = 15)
    @ApiModelProperty(value = "变更开户行后新的分行号")
	private String newBranchNo;
	/**ngbAcctInd*/
	@Excel(name = "国债账户标示", width = 15)
    @ApiModelProperty(value = "国债账户标示")
	private String ngbAcctInd;
	/**noOfStopDep*/
	@Excel(name = "止存数目", width = 15)
    @ApiModelProperty(value = "止存数目")
	private Integer noOfStopDep;
	/**noOfStopWdl*/
	@Excel(name = "止取数目", width = 15)
    @ApiModelProperty(value = "止取数目")
	private Integer noOfStopWdl;
	/**orinMatDt*/
	@Excel(name = "账户原到期日", width = 15)
    @ApiModelProperty(value = "账户原到期日")
	private String orinMatDt;
	/**partialWdlAmt*/
	@Excel(name = "部提金额", width = 15)
    @ApiModelProperty(value = "部提金额")
	private java.math.BigDecimal partialWdlAmt;
	/**partialWdlCnt*/
	@Excel(name = "部提次数", width = 15)
    @ApiModelProperty(value = "部提次数")
	private String partialWdlCnt;
	/**realDepCnt*/
	@Excel(name = "实存次数", width = 15)
    @ApiModelProperty(value = "实存次数")
	private Integer realDepCnt;
	/**tempAcctExpDt*/
	@Excel(name = "临时户到期日", width = 15)
    @ApiModelProperty(value = "临时户到期日")
	private String tempAcctExpDt;
	/**tfrRestFlag*/
	@Excel(name = "特殊转账限制标示", width = 15)
    @ApiModelProperty(value = "特殊转账限制标示")
	private String tfrRestFlag;
	/**acctTypeChgFlag*/
	@Excel(name = "标识该账户是否修改过产品类型", width = 15)
    @ApiModelProperty(value = "标识该账户是否修改过产品类型")
	private String acctTypeChgFlag;
	/**atmAcctFlag*/
	@Excel(name = "标识是否是ATM账户", width = 15)
    @ApiModelProperty(value = "标识是否是ATM账户")
	private String atmAcctFlag;
	/**chrExemProfile*/
	@Excel(name = "费用豁免设置", width = 15)
    @ApiModelProperty(value = "费用豁免设置")
	private String chrExemProfile;
	/**crTierGrpId*/
	@Excel(name = "贷记分层利率的分组ID", width = 15)
    @ApiModelProperty(value = "贷记分层利率的分组ID")
	private String crTierGrpId;
	/**freezeExpDate*/
	@Excel(name = "冻结到期日", width = 15)
    @ApiModelProperty(value = "冻结到期日")
	private String freezeExpDate;
	/**holdVal*/
	@Excel(name = "冻结金额", width = 15)
    @ApiModelProperty(value = "冻结金额")
	private java.math.BigDecimal holdVal;
	/**intAvailable*/
	@Excel(name = "累计利息金额", width = 15)
    @ApiModelProperty(value = "累计利息金额")
	private java.math.BigDecimal intAvailable;
	/**typeIntAccr*/
	@Excel(name = "定期累计的期内贷记利息", width = 15)
    @ApiModelProperty(value = "定期累计的期内贷记利息")
	private java.math.BigDecimal typeIntAccr;
	/**termManRollNo*/
	@Excel(name = "转存约定的标示", width = 15)
    @ApiModelProperty(value = "转存约定的标示")
	private String termManRollNo;
	/**noOfHolds*/
	@Excel(name = "已发生的冻结数目", width = 15)
    @ApiModelProperty(value = "已发生的冻结数目")
	private Integer noOfHolds;
	/**noOfStops*/
	@Excel(name = "账户止付数目", width = 15)
    @ApiModelProperty(value = "账户止付数目")
	private Integer noOfStops;
	/**odArea*/
	@Excel(name = "大字段", width = 15)
    @ApiModelProperty(value = "大字段")
	private String odArea;
	/**odLinkInd*/
	@Excel(name = "标识该透支户是否链接有活期户", width = 15)
    @ApiModelProperty(value = "标识该透支户是否链接有活期户")
	private String odLinkInd;
	/**odVisaArea*/
	@Excel(name = "大字段", width = 15)
    @ApiModelProperty(value = "大字段")
	private String odVisaArea;
	/**sweepAcctFlag*/
	@Excel(name = "是否使用SWEEP处理", width = 15)
    @ApiModelProperty(value = "是否使用SWEEP处理")
	private String sweepAcctFlag;
	/**telrAcctProd*/
	@Excel(name = "标识该户是否为柜员专用账户", width = 15)
    @ApiModelProperty(value = "标识该户是否为柜员专用账户")
	private String telrAcctProd;
	/**trfAcctNo*/
	@Excel(name = "转账账户", width = 15)
    @ApiModelProperty(value = "转账账户")
	private String trfAcctNo;
	/**varIntRate*/
	@Excel(name = "可变利率", width = 15)
    @ApiModelProperty(value = "可变利率")
	private java.math.BigDecimal varIntRate;
	/**withTaxType*/
	@Excel(name = "利息税类型", width = 15)
    @ApiModelProperty(value = "利息税类型")
	private String withTaxType;
	/**fcyCashTrf*/
	@Excel(name = "钞汇标志", width = 15)
    @ApiModelProperty(value = "钞汇标志")
	private String fcyCashTrf;
	/**lcyAcctType*/
	@Excel(name = "本币账户属性", width = 15)
    @ApiModelProperty(value = "本币账户属性")
	private String lcyAcctType;
	/**odIndicator*/
	@Excel(name = "透支标志", width = 15)
    @ApiModelProperty(value = "透支标志")
	private String odIndicator;
	/**productInd*/
	@Excel(name = "产品标识", width = 15)
    @ApiModelProperty(value = "产品标识")
	private String productInd;
	/**termBasis*/
	@Excel(name = "定期基期", width = 15)
    @ApiModelProperty(value = "定期基期")
	private String termBasis;
	/**termDaysMax*/
	@Excel(name = "定期最大存期", width = 15)
    @ApiModelProperty(value = "定期最大存期")
	private String termDaysMax;
	/**acctGrp*/
	@Excel(name = "账户组别", width = 15)
    @ApiModelProperty(value = "账户组别")
	@Dict(dicCode = "zhzb")
	private String acctGrp;
	/**sIntFrmDt*/
	@Excel(name = "上期起息日", width = 15)
    @ApiModelProperty(value = "上期起息日")
	private String sIntFrmDt;
	/**acctDesc*/
	@Excel(name = "账户处理标示", width = 15)
	@ApiModelProperty(value = "账户处理标示")
	@Dict(dicCode = "zhclbz")
	private String acctDesc;
	/**inoutFlag*/
	@Excel(name = "境内外标志", width = 15)
    @ApiModelProperty(value = "境内外标志")
	private String inoutFlag;
	/**drIntAdjustment*/
	@Excel(name = "借记利息调整金额", width = 15)
    @ApiModelProperty(value = "借记利息调整金额")
	private java.math.BigDecimal drIntAdjustment;
	/**fccCiAdjAccr*/
	@Excel(name = "累计Commitment利息调整", width = 15)
    @ApiModelProperty(value = "累计Commitment利息调整")
	private java.math.BigDecimal fccCiAdjAccr;
	/**trmIntAccr*/
	@Excel(name = "定期利息调整", width = 15)
    @ApiModelProperty(value = "定期利息调整")
	private java.math.BigDecimal trmIntAccr;
	/**contractIntIncr*/
	@Excel(name = "利息增量", width = 15)
    @ApiModelProperty(value = "利息增量")
	private java.math.BigDecimal contractIntIncr;
	/**acctDescDepp*/
	@Excel(name = "产品定活处理", width = 15)
    @ApiModelProperty(value = "产品定活处理")
	private String acctDescDepp;
	/**crSopDate*/
	@Excel(name = "贷记本期开始日期", width = 15)
    @ApiModelProperty(value = "贷记本期开始日期")
	private String crSopDate;
	/**drEopDate*/
	@Excel(name = "贷记本期结束日期", width = 15)
    @ApiModelProperty(value = "贷记本期结束日期")
	private String drEopDate;
	/**crIdDet*/
	@Excel(name = "产品编码", width = 15)
    @ApiModelProperty(value = "产品编码")
	private String crIdDet;
	/**lstMntDt*/
	@Excel(name = "最近账户维护日期", width = 15)
    @ApiModelProperty(value = "最近账户维护日期")
	private String lstMntDt;
	/**lstCustCrDt*/
	@Excel(name = "最近一次贷方金融交易的发生日期", width = 15)
    @ApiModelProperty(value = "最近一次贷方金融交易的发生日期")
	private Integer lstCustCrDt;
	/**lstCustDrDt*/
	@Excel(name = "最近一次借方金融交易的发生日期", width = 15)
    @ApiModelProperty(value = "最近一次借方金融交易的发生日期")
	private Integer lstCustDrDt;
	/**netAvBalYtd*/
	@Excel(name = "年度平均余额（从开户日或上一总账年末日期开始）", width = 15)
    @ApiModelProperty(value = "年度平均余额（从开户日或上一总账年末日期开始）")
	private java.math.BigDecimal netAvBalYtd;
	/**netAvNoYtd*/
	@Excel(name = "计算年度平均余额的天数", width = 15)
    @ApiModelProperty(value = "计算年度平均余额的天数")
	private Integer netAvNoYtd;
	/**legalNo*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;

}
