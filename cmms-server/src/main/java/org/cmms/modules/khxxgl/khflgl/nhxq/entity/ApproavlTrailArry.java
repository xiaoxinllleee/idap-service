package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import cn.hutool.core.date.DateUtil;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * @Date 2022/9/21
 * @Created by eran
 */
public class ApproavlTrailArry {
    //审批人工号
    private String ApproavlId;
    //审批类型  初审  终审  复审
    private String ApproavlType;
    //审批结果
    private String ApproavlResult="1";
    //审批意见
    private String ApproavlDetail = "2022年审通过";
    //审批时间 yyyyMMdd
    private String ApproavlTime = DateUtil.format(new Date(),"yyyyMMdd");

    @XmlElement(name = "ApproavlId")
    public String getApproavlId() {
        return ApproavlId;
    }

    public void setApproavlId(String approavlId) {
        ApproavlId = approavlId;
    }

    @XmlElement(name = "ApproavlType")
    public String getApproavlType() {
        return ApproavlType;
    }

    public void setApproavlType(String approavlType) {
        ApproavlType = approavlType;
    }

    @XmlElement(name = "ApproavlResult")
    public String getApproavlResult() {
        return ApproavlResult;
    }

    public void setApproavlResult(String approavlResult) {
        ApproavlResult = approavlResult;
    }

    @XmlElement(name = "ApproavlDetail")
    public String getApproavlDetail() {
        return ApproavlDetail;
    }

    public void setApproavlDetail(String approavlDetail) {
        ApproavlDetail = approavlDetail;
    }

    @XmlElement(name = "ApproavlTime")
    public String getApproavlTime() {
        return ApproavlTime;
    }

    public void setApproavlTime(String approavlTime) {
        ApproavlTime = approavlTime;
    }
}