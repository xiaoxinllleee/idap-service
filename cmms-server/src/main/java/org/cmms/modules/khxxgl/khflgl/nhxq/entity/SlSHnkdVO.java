package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @Date 2022/9/21
 * @Created by eran
 */
public class SlSHnkdVO {
    //客户姓名
    private String CustName;
    //客户身份证号
    private String CertNo;
    /*
     额度单位为元
     */
    private String CreditValue;
    //基点

    /*
       农户小额信用贷款利率按1年期LPR加267.5个基点执行
   一星级信用村客户按1年期LPR加257.5个基点执行；
   二星级信用村客户按1年期LPR加247.5个基点执行；
   三星级信用村客户按1年期LPR加237.5个基点执行。
   （二）家庭众创贷不享受信用村利率优惠，各子产品具体执行利率如下：
   1."初创贷"贷款利率按1年期LPR加287.5个基点执行；
   2."商户贷"贷款利率按1年期LPR加277.5个基点执行；
   3."新农贷"贷款利率按1年期LPR加267.5个基点执行；
   4."青年贷"贷款利率按1年期LPR加257.5个基点执行；
   5."巾帼贷"贷款利率按1年期LPR加247.5个基点执行。
   （三）网格长贷不享受信用村利率优惠，具体执行利率如下：
   1."网格长贷"贷款利率按1年期LPR加195个基点执行；
   2."百佳微网格"贷款利率按1年期LPR加165个基点执行。
        */
    private String AppRate="267.5";
    //授信期限（月）
    private String AppTerm="36";
    //客户分群
    //01-公职人员
    //02-个体工商户
    //03-私企人员
    //04-城乡居民
    private String CustSort="04";
    //客户经理工号
    private String CustManagerId;
    //民族
    private String Nationality="1";
    //客户类型1
    private String CstTp1="01";
    //客户类型2
    private String CstTp2="11";
    //乡镇
    private String TwnNm;
    //有无子女
    private String ChlInd="2";
    //健康状况
    private String HltSt="1";
    //婚姻状况
    private String MrrgSt="4";
    //配偶姓名
    private String AuthorizeCustName;
    //配偶身份证号码
    private String AuthorizeCertType;
    //家庭人口
    private String FamNum="1";
    //劳动能力
    private String LbrSt="1";
    //居住年限
    private String RsdnYrTm="4";
    //居住状况
    private String RsdnSt="1";
    //最高学历
    private String HighEdctDgrCd="70";
    //常住地址
    private String ComAdr;
    //通讯地址
    private String CtcAdr;
    //行政区划代码
    private String AdmnDivCd="430181";
    //住地邮政编码
    private String PstNo="430181";
    //手机号码
    private String MblNo;
    //是否户主
    private String HsHldrInd="2";
    //是否发送短信提醒
    private String MsgNtcInd="1";
    //现工作单位
    private String WrkUnitNm="未知单位";
    //现担任职务
    private String DutyCd="3";
    //行业
    private String IdyClTp="A0111";
    //经营情况是否正常
    private String OprStRglrInd="1";
    //产品市场需求情况
    private String PdMktRqmSt="1";
    //借款人还款意愿
    private String RepyWillTp="1";
    //职业
    private String OcpCd="5";
    //职称
    private String ProfTtlCd="3";
    //从业年限
    private String WrkYrTm="4";
    //综合评价标志
    private String CmprhEvlInd="4";
    //个体工商户名称
    private String IdvMrchNm;
    //统一社会信用代码
    private String SocCrCd;
    //家庭年收入
    private String FamEcnmIncmAmt;
    //个人年收入
    private String PrsnlEcnmIncmAmt;
    //循环标志
    private String RvlvInd="1";
    //信用等级
    private String CrLvl="1";
    //户籍地址
    private String HouseAdr="湖南长沙浏阳市";
    //户籍所在地行政区划
    private String DomcAreaAdr="430181";
    //通讯地址邮编
    private String CtcAdrPstNo="430181";
    //就业状况
    private String EmpySt="27";
    //单位性质
    private String UnitCharTp="99";
    //最高学位
    private String HighDgrCd="0";
    //新市民
    private String NewCtzn="2";

    //责任人信息数组
    private List<ChrgPrsnArry> ChrgPrsnArry;
    //审批轨迹数组
    private List<ApproavlTrailArry> ApproavlTrailArry;

    @XmlElement(name = "CustName")
    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    @XmlElement(name = "CertNo")
    public String getCertNo() {
        return CertNo;
    }

    public void setCertNo(String certNo) {
        CertNo = certNo;
    }

    @XmlElement(name = "CreditValue")
    public String getCreditValue() {
        return CreditValue;
    }

    public void setCreditValue(String creditValue) {
        CreditValue = creditValue;
    }

    @XmlElement(name = "AppRate")
    public String getAppRate() {
        return AppRate;
    }

    public void setAppRate(String appRate) {
        AppRate = appRate;
    }

    @XmlElement(name = "AppTerm")
    public String getAppTerm() {
        return AppTerm;
    }

    public void setAppTerm(String appTerm) {
        AppTerm = appTerm;
    }

    @XmlElement(name = "CustSort")
    public String getCustSort() {
        return CustSort;
    }

    public void setCustSort(String custSort) {
        CustSort = custSort;
    }

    @XmlElement(name = "CustManagerId")
    public String getCustManagerId() {
        return CustManagerId;
    }

    public void setCustManagerId(String custManagerId) {
        CustManagerId = custManagerId;
    }

    @XmlElement(name = "Nationality")
    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    @XmlElement(name = "CstTp1")
    public String getCstTp1() {
        return CstTp1;
    }

    public void setCstTp1(String cstTp1) {
        CstTp1 = cstTp1;
    }

    @XmlElement(name = "CstTp2")
    public String getCstTp2() {
        return CstTp2;
    }

    public void setCstTp2(String cstTp2) {
        CstTp2 = cstTp2;
    }

    @XmlElement(name = "TwnNm")
    public String getTwnNm() {
        return TwnNm;
    }

    public void setTwnNm(String twnNm) {
        TwnNm = twnNm;
    }

    @XmlElement(name = "ChlInd")
    public String getChlInd() {
        return ChlInd;
    }

    public void setChlInd(String chlInd) {
        ChlInd = chlInd;
    }

    @XmlElement(name = "HltSt")
    public String getHltSt() {
        return HltSt;
    }

    public void setHltSt(String hltSt) {
        HltSt = hltSt;
    }

    @XmlElement(name = "MrrgSt")
    public String getMrrgSt() {
        return MrrgSt;
    }

    public void setMrrgSt(String mrrgSt) {
        MrrgSt = mrrgSt;
    }

    @XmlElement(name = "AuthorizeCustName")
    public String getAuthorizeCustName() {
        return AuthorizeCustName;
    }

    public void setAuthorizeCustName(String authorizeCustName) {
        AuthorizeCustName = authorizeCustName;
    }

    @XmlElement(name = "AuthorizeCertType")
    public String getAuthorizeCertType() {
        return AuthorizeCertType;
    }

    public void setAuthorizeCertType(String authorizeCertType) {
        AuthorizeCertType = authorizeCertType;
    }

    @XmlElement(name = "FamNum")
    public String getFamNum() {
        return FamNum;
    }

    public void setFamNum(String famNum) {
        FamNum = famNum;
    }

    @XmlElement(name = "LbrSt")
    public String getLbrSt() {
        return LbrSt;
    }

    public void setLbrSt(String lbrSt) {
        LbrSt = lbrSt;
    }

    @XmlElement(name = "RsdnYrTm")
    public String getRsdnYrTm() {
        return RsdnYrTm;
    }

    public void setRsdnYrTm(String rsdnYrTm) {
        RsdnYrTm = rsdnYrTm;
    }

    @XmlElement(name = "RsdnSt")
    public String getRsdnSt() {
        return RsdnSt;
    }

    public void setRsdnSt(String rsdnSt) {
        RsdnSt = rsdnSt;
    }

    @XmlElement(name = "HighEdctDgrCd")
    public String getHighEdctDgrCd() {
        return HighEdctDgrCd;
    }

    public void setHighEdctDgrCd(String highEdctDgrCd) {
        HighEdctDgrCd = highEdctDgrCd;
    }

    @XmlElement(name = "ComAdr")
    public String getComAdr() {
        return ComAdr;
    }

    public void setComAdr(String comAdr) {
        ComAdr = comAdr;
    }

    @XmlElement(name = "CtcAdr")
    public String getCtcAdr() {
        return CtcAdr;
    }

    public void setCtcAdr(String ctcAdr) {
        CtcAdr = ctcAdr;
    }

    @XmlElement(name = "AdmnDivCd")
    public String getAdmnDivCd() {
        return AdmnDivCd;
    }

    public void setAdmnDivCd(String admnDivCd) {
        AdmnDivCd = admnDivCd;
    }

    @XmlElement(name = "PstNo")
    public String getPstNo() {
        return PstNo;
    }

    public void setPstNo(String pstNo) {
        PstNo = pstNo;
    }

    @XmlElement(name = "MblNo")
    public String getMblNo() {
        return MblNo;
    }

    public void setMblNo(String mblNo) {
        MblNo = mblNo;
    }

    @XmlElement(name = "HsHldrInd")
    public String getHsHldrInd() {
        return HsHldrInd;
    }

    public void setHsHldrInd(String hsHldrInd) {
        HsHldrInd = hsHldrInd;
    }

    @XmlElement(name = "MsgNtcInd")
    public String getMsgNtcInd() {
        return MsgNtcInd;
    }

    public void setMsgNtcInd(String msgNtcInd) {
        MsgNtcInd = msgNtcInd;
    }

    @XmlElement(name = "WrkUnitNm")
    public String getWrkUnitNm() {
        return WrkUnitNm;
    }

    public void setWrkUnitNm(String wrkUnitNm) {
        WrkUnitNm = wrkUnitNm;
    }

    @XmlElement(name = "DutyCd")
    public String getDutyCd() {
        return DutyCd;
    }

    public void setDutyCd(String dutyCd) {
        DutyCd = dutyCd;
    }

    @XmlElement(name = "IdyClTp")
    public String getIdyClTp() {
        return IdyClTp;
    }

    public void setIdyClTp(String idyClTp) {
        IdyClTp = idyClTp;
    }

    @XmlElement(name = "OprStRglrInd")
    public String getOprStRglrInd() {
        return OprStRglrInd;
    }

    public void setOprStRglrInd(String oprStRglrInd) {
        OprStRglrInd = oprStRglrInd;
    }

    @XmlElement(name = "PdMktRqmSt")
    public String getPdMktRqmSt() {
        return PdMktRqmSt;
    }

    public void setPdMktRqmSt(String pdMktRqmSt) {
        PdMktRqmSt = pdMktRqmSt;
    }

    @XmlElement(name = "RepyWillTp")
    public String getRepyWillTp() {
        return RepyWillTp;
    }

    public void setRepyWillTp(String repyWillTp) {
        RepyWillTp = repyWillTp;
    }

    @XmlElement(name = "OcpCd")
    public String getOcpCd() {
        return OcpCd;
    }

    public void setOcpCd(String ocpCd) {
        OcpCd = ocpCd;
    }

    @XmlElement(name = "ProfTtlCd")
    public String getProfTtlCd() {
        return ProfTtlCd;
    }

    public void setProfTtlCd(String profTtlCd) {
        ProfTtlCd = profTtlCd;
    }

    @XmlElement(name = "WrkYrTm")
    public String getWrkYrTm() {
        return WrkYrTm;
    }

    public void setWrkYrTm(String wrkYrTm) {
        WrkYrTm = wrkYrTm;
    }

    @XmlElement(name = "CmprhEvlInd")
    public String getCmprhEvlInd() {
        return CmprhEvlInd;
    }

    public void setCmprhEvlInd(String cmprhEvlInd) {
        CmprhEvlInd = cmprhEvlInd;
    }

    @XmlElement(name = "IdvMrchNm")
    public String getIdvMrchNm() {
        return IdvMrchNm;
    }

    public void setIdvMrchNm(String idvMrchNm) {
        IdvMrchNm = idvMrchNm;
    }

    @XmlElement(name = "SocCrCd")
    public String getSocCrCd() {
        return SocCrCd;
    }

    public void setSocCrCd(String socCrCd) {
        SocCrCd = socCrCd;
    }

    @XmlElement(name = "FamEcnmIncmAmt")
    public String getFamEcnmIncmAmt() {
        return FamEcnmIncmAmt;
    }

    public void setFamEcnmIncmAmt(String famEcnmIncmAmt) {
        FamEcnmIncmAmt = famEcnmIncmAmt;
    }

    @XmlElement(name = "PrsnlEcnmIncmAmt")
    public String getPrsnlEcnmIncmAmt() {
        return PrsnlEcnmIncmAmt;
    }

    public void setPrsnlEcnmIncmAmt(String prsnlEcnmIncmAmt) {
        PrsnlEcnmIncmAmt = prsnlEcnmIncmAmt;
    }

    @XmlElement(name = "RvlvInd")
    public String getRvlvInd() {
        return RvlvInd;
    }

    public void setRvlvInd(String rvlvInd) {
        RvlvInd = rvlvInd;
    }

    @XmlElement(name = "CrLvl")
    public String getCrLvl() {
        return CrLvl;
    }

    public void setCrLvl(String crLvl) {
        CrLvl = crLvl;
    }

    @XmlElement(name = "HouseAdr")
    public String getHouseAdr() {
        return HouseAdr;
    }

    public void setHouseAdr(String houseAdr) {
        HouseAdr = houseAdr;
    }

    @XmlElement(name = "DomcAreaAdr")
    public String getDomcAreaAdr() {
        return DomcAreaAdr;
    }

    public void setDomcAreaAdr(String domcAreaAdr) {
        DomcAreaAdr = domcAreaAdr;
    }

    @XmlElement(name = "CtcAdrPstNo")
    public String getCtcAdrPstNo() {
        return CtcAdrPstNo;
    }

    public void setCtcAdrPstNo(String ctcAdrPstNo) {
        CtcAdrPstNo = ctcAdrPstNo;
    }

    @XmlElement(name = "EmpySt")
    public String getEmpySt() {
        return EmpySt;
    }

    public void setEmpySt(String empySt) {
        EmpySt = empySt;
    }

    @XmlElement(name = "UnitCharTp")
    public String getUnitCharTp() {
        return UnitCharTp;
    }

    public void setUnitCharTp(String unitCharTp) {
        UnitCharTp = unitCharTp;
    }

    @XmlElement(name = "HighDgrCd")
    public String getHighDgrCd() {
        return HighDgrCd;
    }

    public void setHighDgrCd(String highDgrCd) {
        HighDgrCd = highDgrCd;
    }

    @XmlElement(name = "NewCtzn")
    public String getNewCtzn() {
        return NewCtzn;
    }

    public void setNewCtzn(String newCtzn) {
        NewCtzn = newCtzn;
    }

    @XmlElement(name = "ChrgPrsnArry")
    public List<ChrgPrsnArry> getChrgPrsnArry() {
        return ChrgPrsnArry;
    }

    public void setChrgPrsnArry(List<ChrgPrsnArry> chrgPrsnArry) {
        ChrgPrsnArry = chrgPrsnArry;
    }

    @XmlElement(name = "ApproavlTrailArry")
    public List<ApproavlTrailArry> getApproavlTrailArry() {
        return ApproavlTrailArry;
    }

    public void setApproavlTrailArry(List<ApproavlTrailArry> approavlTrailArry) {
        ApproavlTrailArry = approavlTrailArry;
    }
}
