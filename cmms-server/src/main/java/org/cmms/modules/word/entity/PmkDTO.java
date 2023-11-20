package org.cmms.modules.word.entity;

import lombok.Data;

@Data
public class PmkDTO {
    private String bank;
    private String name;
    private String nl;
    private String idn;
    //家庭电话号码
    private String phone;
    private String jtrs;
    private String zz;
    //配偶姓名
    private String sName;
    private String sIdn;
    private String sPhone;
    private String sZz;
    //借款用途
    private String uLoan;
    private String lCredit;
    private String loanTime;
    private String payWay;
    private String jxfs;
    private String hkbz;
    //借款代表
    private String yhzgx;
    private String lxr;
    private String zy;
}
