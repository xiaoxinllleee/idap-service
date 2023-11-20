package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Date 2022/9/21
 * @Created by eran
 */
public class ChrgPrsnArry {
    /**
     * 说明 客户经理和第一责任人  是同一个人
     * 调查责任人1 和 调查责任人2 不能是同一个人
     * 所有人员在福祥E贷中必须存在
     * 所有责任人需要为同一法人行社
     *
     * 责任人类型
     *
     * 1:管理责任人
     * 3:审查责任人
     * 41:最终审批责任人
     * 5:调查责任人
     * 11:第一责任人
     * 导入责任人和责任比例信息不能为空 责任人比例综合必须为100
     * */
    private String ChrgPrsnTp;
    //责任人名称
    private String ChrgPrsnNm;
    //责任人身份证号
    private String ChrgPrsnIdntNo;
    //责任人比例  合计100
    private String ChrgPrsnPct;

    @XmlElement(name = "ChrgPrsnTp")
    public String getChrgPrsnTp() {
        return ChrgPrsnTp;
    }

    public void setChrgPrsnTp(String chrgPrsnTp) {
        ChrgPrsnTp = chrgPrsnTp;
    }

    @XmlElement(name = "ChrgPrsnNm")
    public String getChrgPrsnNm() {
        return ChrgPrsnNm;
    }

    public void setChrgPrsnNm(String chrgPrsnNm) {
        ChrgPrsnNm = chrgPrsnNm;
    }

    @XmlElement(name = "ChrgPrsnLoginName")
    public String getChrgPrsnIdntNo() {
        return ChrgPrsnIdntNo;
    }

    public void setChrgPrsnIdntNo(String chrgPrsnIdntNo) {
        ChrgPrsnIdntNo = chrgPrsnIdntNo;
    }

    @XmlElement(name = "ChrgPrsnPct")
    public String getChrgPrsnPct() {
        return ChrgPrsnPct;
    }

    public void setChrgPrsnPct(String chrgPrsnPct) {
        ChrgPrsnPct = chrgPrsnPct;
    }

}
