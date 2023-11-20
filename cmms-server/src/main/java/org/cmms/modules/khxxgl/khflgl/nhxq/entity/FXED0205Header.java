package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * @Date 2022/9/21
 * @Created by eran
 */
@ToString
public class FXED0205Header {
    private String BankNo = "02001";
    //发给我
    private String ChannelId = "105";
    private String Encrypt = "0";
    //流水号保证唯一
    private String ExternalReference = System.currentTimeMillis()+ "" +RandomUtil.randomInt(10000);
    private String OriginalChannelId = "1";
    private String OriginalReference = "2";
    private String RequestBranchCode = "990101";
    private String RequestOperatorId = "9901815";
    private String RequestOperatorType = "3";
    private String RequestTime = System.currentTimeMillis()+ "";
    private String RequestType = "0";
    private String ServiceCode = "FXED0205";
    private String TermNo = "00000";
    private String TermType = "0000000000";
    private String TradeDate=DateUtil.format(new Date(),"yyyyMMdd");
    private String Version = "1.0";

    @XmlElement(name = "BankNo")
    public String getBankNo() {
        return BankNo;
    }

    public void setBankNo(String bankNo) {
        BankNo = bankNo;
    }

    @XmlElement(name = "ChannelId")
    public String getChannelId() {
        return ChannelId;
    }

    public void setChannelId(String channelId) {
        ChannelId = channelId;
    }

    @XmlElement(name = "Encrypt")
    public String getEncrypt() {
        return Encrypt;
    }

    public void setEncrypt(String encrypt) {
        Encrypt = encrypt;
    }

    @XmlElement(name = "ExternalReference")
    public String getExternalReference() {
        return ExternalReference;
    }

    public void setExternalReference(String externalReference) {
        ExternalReference = externalReference;
    }

    @XmlElement(name = "OriginalChannelId")
    public String getOriginalChannelId() {
        return OriginalChannelId;
    }

    public void setOriginalChannelId(String originalChannelId) {
        OriginalChannelId = originalChannelId;
    }

    @XmlElement(name = "OriginalReference")
    public String getOriginalReference() {
        return OriginalReference;
    }

    public void setOriginalReference(String originalReference) {
        OriginalReference = originalReference;
    }

    @XmlElement(name = "RequestBranchCode")
    public String getRequestBranchCode() {
        return RequestBranchCode;
    }

    public void setRequestBranchCode(String requestBranchCode) {
        RequestBranchCode = requestBranchCode;
    }

    @XmlElement(name = "RequestOperatorId")
    public String getRequestOperatorId() {
        return RequestOperatorId;
    }

    public void setRequestOperatorId(String requestOperatorId) {
        RequestOperatorId = requestOperatorId;
    }

    @XmlElement(name = "RequestOperatorType")
    public String getRequestOperatorType() {
        return RequestOperatorType;
    }

    public void setRequestOperatorType(String requestOperatorType) {
        RequestOperatorType = requestOperatorType;
    }

    @XmlElement(name = "RequestTime")
    public String getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(String requestTime) {
        RequestTime = requestTime;
    }

    @XmlElement(name = "RequestType")
    public String getRequestType() {
        return RequestType;
    }

    public void setRequestType(String requestType) {
        RequestType = requestType;
    }

    @XmlElement(name = "ServiceCode")
    public String getServiceCode() {
        return ServiceCode;
    }

    public void setServiceCode(String serviceCode) {
        ServiceCode = serviceCode;
    }

    @XmlElement(name = "TermNo")
    public String getTermNo() {
        return TermNo;
    }

    public void setTermNo(String termNo) {
        TermNo = termNo;
    }

    @XmlElement(name = "TermType")
    public String getTermType() {
        return TermType;
    }

    public void setTermType(String termType) {
        TermType = termType;
    }

    @XmlElement(name = "TradeDate")
    public String getTradeDate() {
        return TradeDate;
    }

    public void setTradeDate(String tradeDate) {
        TradeDate = tradeDate;
    }

    @XmlElement(name = "Version")
    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
