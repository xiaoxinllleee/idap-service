package org.cmms.modules.word.entity;

import lombok.Data;

@Data
public class SmallLoanDTO {
    private String bank;
    private String name;
    private String idn;
    //家庭电话号码
    private String fPhone;
    private String phone;
    //配偶姓名
    private String sName;
    private String sIdn;
    private String sPhone;
    private String sZz;


    private String town;
    private String vi;
    private String gp;
    //借款用途
    private String uLoan;
    private String lCredit;
    private String loanTime;
    private String payWay;
    //借款代表
    private String loanMan;
    private String yhzgx;





}
