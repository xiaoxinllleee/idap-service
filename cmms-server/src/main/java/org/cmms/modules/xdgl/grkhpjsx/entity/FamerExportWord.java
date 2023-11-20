package org.cmms.modules.xdgl.grkhpjsx.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 小额农贷输出文档
 * @author
 *
 */
public class FamerExportWord {
	private String townVal= "";
	private String villageVal= "";
	private String groupVal= "";
	private String name= "";
	private String sexVal= "";
	private String birthday= "";
	private String idn= "";
	private String marriageVal= "";
	private String professionVal= "";
	private String mobile= "";
	private String fmember = "";
	private String frelation= "";
	private String fbirthday= "";
	private String fprofessionVal= "";
	private String fidn= "";
	private String fmobile= "";
	private String smember= "";
	private String srelation= "";
	private String sbirthday= "";
	private String sprofessionVal= "";
	private String sidn = "";
	private String smobile = ""; 
	private String tmember = "";
	private String trelation= "";
	private String tbirthday= "";
	private String tprofessionVal= "";
	private String tidn= "";
	private String tmobile= "";
	private String umember = "";
	private String urelation= "";
	private String ubirthday= "";
	private String uprofessionVal= "";
	private String uidn= "";
	private String umobile= "";
	private String vmember = "";
	private String vrelation= "";
	private String vbirthday= "";
	private String vprofessionVal= "";
	private String vidn= "";
	private String vmobile= "";
	private String xmember = "";
	private String xrelation= "";
	private String xbirthday= "";
	private String xprofessionVal= "";
	private String xidn= "";
	private String xmobile= "";
	private String houseCount = "";
	private String houseArea = "";
	private String housePrice = "";
	private String capOther= "";
	private String capTotal= "";
	private String cashDeposit= "";
	private String circOther= "";
	private String receivable= "";
	private String circleTotal= "";
	private String loanOwnBank= "";
	private String loanOtherBank= "";
	private String loanOther= "";
	private String other= "";
	private String debtTotal= "";
	private String netAsset= "";
	private String inTotal= "";
	private String production= "";
	private String outOther= "";
	private String outTotal= "";
	private String netIncome= "";
	private String creditFinal= "";
	private String rateInit= "";
	private String rate= "";
	private String rateFinal= "";
	private String isBreakLaw = ""; 
	private String behaviour= "" ;
	private String credit = "";
	private String debtOther="";
	private String crop = "";
	private String breed = "";
	private String labour = "";
	private String commerce = "";
	private String inOther = "";
	private String education = "";
	private String medical = "";
	private String pension = "";
	private String producation = "";
	private String living = "";
	private String remain = "";
	private String avgday = "";
	private String reRate = "";
	private String payable = "";
	private String otherGuarantee = "";
	private String isSave = "";
	private String isOpen = "";
	private String saveAmount = "";
	private String id = "";

	/**
	 * 家庭成员
	 */
	private List<FamilyMember> familyMember = Lists.newArrayList();
	/**
	 * 模型等级
	 */
	private String rateAi = "";
	
	public FamerExportWord() {
		
	}

	public FamerExportWord(String townVal, String villageVal, String groupVal, String name, String sexVal,
                           String birthday, String idn, String marriageVal, String professionVal, String mobile, String fmember,
                           String frelation, String fbirthday, String fprofessionVal, String fidn, String fmobile, String smember,
                           String srelation, String sbirthday, String sprofessionVal, String sidn, String smobile, String tmember,
                           String trelation, String tbirthday, String tprofessionVal, String tidn, String tmobile, String umember,
                           String urelation, String ubirthday, String uprofessionVal, String uidn, String umobile, String vmember,
                           String vrelation, String vbirthday, String vprofessionVal, String vidn, String vmobile, String xmember,
                           String xrelation, String xbirthday, String xprofessionVal, String xidn, String xmobile, String houseCount,
                           String houseArea, String housePrice, String capOther, String capTotal, String cashDeposit, String circOther,
                           String receivable, String circleTotal, String loanOwnBank, String loanOtherBank, String loanOther,
                           String other, String debtTotal, String netAsset, String inTotal, String production, String outOther,
                           String outTotal, String netIncome, String creditFinal, String rateInit, String rate, String rateFinal,
                           String isBreakLaw, String behaviour, String credit, String debtOther, String crop, String breed,
                           String labour, String commerce, String inOther, String education, String medical, String pension,
                           String producation, String living, String remain, String avgday, String reRate, String payable,
                           String otherGuarantee, String isSave, String isOpen, String saveAmount) {
		super();
		this.townVal = townVal;
		this.villageVal = villageVal;
		this.groupVal = groupVal;
		this.name = name;
		this.sexVal = sexVal;
		this.birthday = birthday;
		this.idn = idn;
		this.marriageVal = marriageVal;
		this.professionVal = professionVal;
		this.mobile = mobile;
		this.fmember = fmember;
		this.frelation = frelation;
		this.fbirthday = fbirthday;
		this.fprofessionVal = fprofessionVal;
		this.fidn = fidn;
		this.fmobile = fmobile;
		this.smember = smember;
		this.srelation = srelation;
		this.sbirthday = sbirthday;
		this.sprofessionVal = sprofessionVal;
		this.sidn = sidn;
		this.smobile = smobile;
		this.tmember = tmember;
		this.trelation = trelation;
		this.tbirthday = tbirthday;
		this.tprofessionVal = tprofessionVal;
		this.tidn = tidn;
		this.tmobile = tmobile;
		this.umember = umember;
		this.urelation = urelation;
		this.ubirthday = ubirthday;
		this.uprofessionVal = uprofessionVal;
		this.uidn = uidn;
		this.umobile = umobile;
		this.vmember = vmember;
		this.vrelation = vrelation;
		this.vbirthday = vbirthday;
		this.vprofessionVal = vprofessionVal;
		this.vidn = vidn;
		this.vmobile = vmobile;
		this.xmember = xmember;
		this.xrelation = xrelation;
		this.xbirthday = xbirthday;
		this.xprofessionVal = xprofessionVal;
		this.xidn = xidn;
		this.xmobile = xmobile;
		this.houseCount = houseCount;
		this.houseArea = houseArea;
		this.housePrice = housePrice;
		this.capOther = capOther;
		this.capTotal = capTotal;
		this.cashDeposit = cashDeposit;
		this.circOther = circOther;
		this.receivable = receivable;
		this.circleTotal = circleTotal;
		this.loanOwnBank = loanOwnBank;
		this.loanOtherBank = loanOtherBank;
		this.loanOther = loanOther;
		this.other = other;
		this.debtTotal = debtTotal;
		this.netAsset = netAsset;
		this.inTotal = inTotal;
		this.production = production;
		this.outOther = outOther;
		this.outTotal = outTotal;
		this.netIncome = netIncome;
		this.creditFinal = creditFinal;
		this.rateInit = rateInit;
		this.rate = rate;
		this.rateFinal = rateFinal;
		this.isBreakLaw = isBreakLaw;
		this.behaviour = behaviour;
		this.credit = credit;
		this.debtOther = debtOther;
		this.crop = crop;
		this.breed = breed;
		this.labour = labour;
		this.commerce = commerce;
		this.inOther = inOther;
		this.education = education;
		this.medical = medical;
		this.pension = pension;
		this.producation = producation;
		this.living = living;
		this.remain = remain;
		this.avgday = avgday;
		this.reRate = reRate;
		this.payable = payable;
		this.otherGuarantee = otherGuarantee;
		this.isSave = isSave;
		this.isOpen = isOpen;
		this.saveAmount = saveAmount;
	}

	public String getTownVal() {
		return townVal;
	}

	public void setTownVal(String townVal) {
		this.townVal = townVal;
	}

	public String getVillageVal() {
		return villageVal;
	}

	public void setVillageVal(String villageVal) {
		this.villageVal = villageVal;
	}

	public String getGroupVal() {
		return groupVal;
	}

	public void setGroupVal(String groupVal) {
		this.groupVal = groupVal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSexVal() {
		return sexVal;
	}

	public void setSexVal(String sexVal) {
		this.sexVal = sexVal;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdn() {
		return idn;
	}

	public void setIdn(String idn) {
		this.idn = idn;
	}

	public String getMarriageVal() {
		return marriageVal;
	}

	public void setMarriageVal(String marriageVal) {
		this.marriageVal = marriageVal;
	}

	public String getProfessionVal() {
		return professionVal;
	}

	public void setProfessionVal(String professionVal) {
		this.professionVal = professionVal;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFmember() {
		return fmember;
	}

	public void setFmember(String fmember) {
		this.fmember = fmember;
	}

	public String getFrelation() {
		return frelation;
	}

	public void setFrelation(String frelation) {
		this.frelation = frelation;
	}

	public String getFbirthday() {
		return fbirthday;
	}

	public void setFbirthday(String fbirthday) {
		this.fbirthday = fbirthday;
	}

	public String getFprofessionVal() {
		return fprofessionVal;
	}

	public void setFprofessionVal(String fprofessionVal) {
		this.fprofessionVal = fprofessionVal;
	}

	public String getFidn() {
		return fidn;
	}

	public void setFidn(String fidn) {
		this.fidn = fidn;
	}

	public String getFmobile() {
		return fmobile;
	}

	public void setFmobile(String fmobile) {
		this.fmobile = fmobile;
	}

	public String getSmember() {
		return smember;
	}

	public void setSmember(String smember) {
		this.smember = smember;
	}

	public String getSrelation() {
		return srelation;
	}

	public void setSrelation(String srelation) {
		this.srelation = srelation;
	}

	public String getSbirthday() {
		return sbirthday;
	}

	public void setSbirthday(String sbirthday) {
		this.sbirthday = sbirthday;
	}

	public String getSprofessionVal() {
		return sprofessionVal;
	}

	public void setSprofessionVal(String sprofessionVal) {
		this.sprofessionVal = sprofessionVal;
	}

	public String getSidn() {
		return sidn;
	}

	public void setSidn(String sidn) {
		this.sidn = sidn;
	}

	public String getSmobile() {
		return smobile;
	}

	public void setSmobile(String smobile) {
		this.smobile = smobile;
	}

	public String getTmember() {
		return tmember;
	}

	public void setTmember(String tmember) {
		this.tmember = tmember;
	}

	public String getTrelation() {
		return trelation;
	}

	public void setTrelation(String trelation) {
		this.trelation = trelation;
	}

	public String getTbirthday() {
		return tbirthday;
	}

	public void setTbirthday(String tbirthday) {
		this.tbirthday = tbirthday;
	}

	public String getTprofessionVal() {
		return tprofessionVal;
	}

	public void setTprofessionVal(String tprofessionVal) {
		this.tprofessionVal = tprofessionVal;
	}

	public String getTidn() {
		return tidn;
	}

	public void setTidn(String tidn) {
		this.tidn = tidn;
	}

	public String getTmobile() {
		return tmobile;
	}

	public void setTmobile(String tmobile) {
		this.tmobile = tmobile;
	}

	public String getUmember() {
		return umember;
	}

	public void setUmember(String umember) {
		this.umember = umember;
	}

	public String getUrelation() {
		return urelation;
	}

	public void setUrelation(String urelation) {
		this.urelation = urelation;
	}

	public String getUbirthday() {
		return ubirthday;
	}

	public void setUbirthday(String ubirthday) {
		this.ubirthday = ubirthday;
	}

	public String getUprofessionVal() {
		return uprofessionVal;
	}

	public void setUprofessionVal(String uprofessionVal) {
		this.uprofessionVal = uprofessionVal;
	}

	public String getUidn() {
		return uidn;
	}

	public void setUidn(String uidn) {
		this.uidn = uidn;
	}

	public String getUmobile() {
		return umobile;
	}

	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}

	public String getVmember() {
		return vmember;
	}

	public void setVmember(String vmember) {
		this.vmember = vmember;
	}

	public String getVrelation() {
		return vrelation;
	}

	public void setVrelation(String vrelation) {
		this.vrelation = vrelation;
	}

	public String getVbirthday() {
		return vbirthday;
	}

	public void setVbirthday(String vbirthday) {
		this.vbirthday = vbirthday;
	}

	public String getVprofessionVal() {
		return vprofessionVal;
	}

	public void setVprofessionVal(String vprofessionVal) {
		this.vprofessionVal = vprofessionVal;
	}

	public String getVidn() {
		return vidn;
	}

	public void setVidn(String vidn) {
		this.vidn = vidn;
	}

	public String getVmobile() {
		return vmobile;
	}

	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}

	public String getXmember() {
		return xmember;
	}

	public void setXmember(String xmember) {
		this.xmember = xmember;
	}

	public String getXrelation() {
		return xrelation;
	}

	public void setXrelation(String xrelation) {
		this.xrelation = xrelation;
	}

	public String getXbirthday() {
		return xbirthday;
	}

	public void setXbirthday(String xbirthday) {
		this.xbirthday = xbirthday;
	}

	public String getXprofessionVal() {
		return xprofessionVal;
	}

	public void setXprofessionVal(String xprofessionVal) {
		this.xprofessionVal = xprofessionVal;
	}

	public String getXidn() {
		return xidn;
	}

	public void setXidn(String xidn) {
		this.xidn = xidn;
	}

	public String getXmobile() {
		return xmobile;
	}

	public void setXmobile(String xmobile) {
		this.xmobile = xmobile;
	}

	public String getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(String houseCount) {
		this.houseCount = houseCount;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	public String getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(String housePrice) {
		this.housePrice = housePrice;
	}

	public String getCapOther() {
		return capOther;
	}

	public void setCapOther(String capOther) {
		this.capOther = capOther;
	}

	public String getCapTotal() {
		return capTotal;
	}

	public void setCapTotal(String capTotal) {
		this.capTotal = capTotal;
	}

	public String getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(String cashDeposit) {
		this.cashDeposit = cashDeposit;
	}

	public String getCircOther() {
		return circOther;
	}

	public void setCircOther(String circOther) {
		this.circOther = circOther;
	}

	public String getReceivable() {
		return receivable;
	}

	public void setReceivable(String receivable) {
		this.receivable = receivable;
	}

	public String getCircleTotal() {
		return circleTotal;
	}

	public void setCircleTotal(String circleTotal) {
		this.circleTotal = circleTotal;
	}

	public String getLoanOwnBank() {
		return loanOwnBank;
	}

	public void setLoanOwnBank(String loanOwnBank) {
		this.loanOwnBank = loanOwnBank;
	}

	public String getLoanOtherBank() {
		return loanOtherBank;
	}

	public void setLoanOtherBank(String loanOtherBank) {
		this.loanOtherBank = loanOtherBank;
	}

	public String getLoanOther() {
		return loanOther;
	}

	public void setLoanOther(String loanOther) {
		this.loanOther = loanOther;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getDebtTotal() {
		return debtTotal;
	}

	public void setDebtTotal(String debtTotal) {
		this.debtTotal = debtTotal;
	}

	public String getNetAsset() {
		return netAsset;
	}

	public void setNetAsset(String netAsset) {
		this.netAsset = netAsset;
	}

	public String getInTotal() {
		return inTotal;
	}

	public void setInTotal(String inTotal) {
		this.inTotal = inTotal;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getOutOther() {
		return outOther;
	}

	public void setOutOther(String outOther) {
		this.outOther = outOther;
	}

	public String getOutTotal() {
		return outTotal;
	}

	public void setOutTotal(String outTotal) {
		this.outTotal = outTotal;
	}

	public String getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}

	public String getCreditFinal() {
		return creditFinal;
	}

	public void setCreditFinal(String creditFinal) {
		this.creditFinal = creditFinal;
	}

	public String getRateInit() {
		return rateInit;
	}

	public void setRateInit(String rateInit) {
		this.rateInit = rateInit;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRateFinal() {
		return rateFinal;
	}

	public void setRateFinal(String rateFinal) {
		this.rateFinal = rateFinal;
	}

	public String getIsBreakLaw() {
		return isBreakLaw;
	}

	public void setIsBreakLaw(String isBreakLaw) {
		this.isBreakLaw = isBreakLaw;
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getDebtOther() {
		return debtOther;
	}

	public void setDebtOther(String debtOther) {
		this.debtOther = debtOther;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getLabour() {
		return labour;
	}

	public void setLabour(String labour) {
		this.labour = labour;
	}

	public String getCommerce() {
		return commerce;
	}

	public void setCommerce(String commerce) {
		this.commerce = commerce;
	}

	public String getInOther() {
		return inOther;
	}

	public void setInOther(String inOther) {
		this.inOther = inOther;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMedical() {
		return medical;
	}

	public void setMedical(String medical) {
		this.medical = medical;
	}

	public String getPension() {
		return pension;
	}

	public void setPension(String pension) {
		this.pension = pension;
	}

	public String getProducation() {
		return producation;
	}

	public void setProducation(String producation) {
		this.producation = producation;
	}

	public String getLiving() {
		return living;
	}

	public void setLiving(String living) {
		this.living = living;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	public String getAvgday() {
		return avgday;
	}

	public void setAvgday(String avgday) {
		this.avgday = avgday;
	}

	public String getReRate() {
		return reRate;
	}

	public void setReRate(String reRate) {
		this.reRate = reRate;
	}

	public String getPayable() {
		return payable;
	}

	public void setPayable(String payable) {
		this.payable = payable;
	}

	public String getOtherGuarantee() {
		return otherGuarantee;
	}

	public void setOtherGuarantee(String otherGuarantee) {
		this.otherGuarantee = otherGuarantee;
	}

	public String getIsSave() {
		return isSave;
	}

	public void setIsSave(String isSave) {
		this.isSave = isSave;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getSaveAmount() {
		return saveAmount;
	}

	public void setSaveAmount(String saveAmount) {
		this.saveAmount = saveAmount;
	}

	public List<FamilyMember> getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(List<FamilyMember> familyMember) {
		this.familyMember = familyMember;
	}

	public String getRateAi() {
		return rateAi;
	}

	public void setRateAi(String rateAi) {
		this.rateAi = rateAi;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FamerExportWord [townVal=" + townVal + ", villageVal=" + villageVal + ", groupVal=" + groupVal
				+ ", name=" + name + ", sexVal=" + sexVal + ", birthday=" + birthday + ", idn=" + idn + ", marriageVal="
				+ marriageVal + ", professionVal=" + professionVal + ", mobile=" + mobile + ", fmember=" + fmember
				+ ", frelation=" + frelation + ", fbirthday=" + fbirthday + ", fprofessionVal=" + fprofessionVal
				+ ", fidn=" + fidn + ", fmobile=" + fmobile + ", smember=" + smember + ", srelation=" + srelation
				+ ", sbirthday=" + sbirthday + ", sprofessionVal=" + sprofessionVal + ", sidn=" + sidn + ", smobile="
				+ smobile + ", tmember=" + tmember + ", trelation=" + trelation + ", tbirthday=" + tbirthday
				+ ", tprofessionVal=" + tprofessionVal + ", tidn=" + tidn + ", tmobile=" + tmobile + ", umember="
				+ umember + ", urelation=" + urelation + ", ubirthday=" + ubirthday + ", uprofessionVal="
				+ uprofessionVal + ", uidn=" + uidn + ", umobile=" + umobile + ", vmember=" + vmember + ", vrelation="
				+ vrelation + ", vbirthday=" + vbirthday + ", vprofessionVal=" + vprofessionVal + ", vidn=" + vidn
				+ ", vmobile=" + vmobile + ", xmember=" + xmember + ", xrelation=" + xrelation + ", xbirthday="
				+ xbirthday + ", xprofessionVal=" + xprofessionVal + ", xidn=" + xidn + ", xmobile=" + xmobile
				+ ", houseCount=" + houseCount + ", houseArea=" + houseArea + ", housePrice=" + housePrice
				+ ", capOther=" + capOther + ", capTotal=" + capTotal + ", cashDeposit=" + cashDeposit + ", circOther="
				+ circOther + ", receivable=" + receivable + ", circleTotal=" + circleTotal + ", loanOwnBank="
				+ loanOwnBank + ", loanOtherBank=" + loanOtherBank + ", loanOther=" + loanOther + ", other=" + other
				+ ", debtTotal=" + debtTotal + ", netAsset=" + netAsset + ", inTotal=" + inTotal + ", production="
				+ production + ", outOther=" + outOther + ", outTotal=" + outTotal + ", netIncome=" + netIncome
				+ ", creditFinal=" + creditFinal + ", rateInit=" + rateInit + ", rate=" + rate + ", rateFinal="
				+ rateFinal + ", isBreakLaw=" + isBreakLaw + ", behaviour=" + behaviour + ", credit=" + credit
				+ ", debtOther=" + debtOther + ", crop=" + crop + ", breed=" + breed + ", labour=" + labour
				+ ", commerce=" + commerce + ", inOther=" + inOther + ", education=" + education + ", medical="
				+ medical + ", pension=" + pension + ", producation=" + producation + ", living=" + living + ", remain="
				+ remain + ", avgday=" + avgday + ", reRate=" + reRate + ", payable=" + payable + ", otherGuarantee="
				+ otherGuarantee + ", isSave=" + isSave + ", isOpen=" + isOpen + ", saveAmount=" + saveAmount + "]";
	}
	
}