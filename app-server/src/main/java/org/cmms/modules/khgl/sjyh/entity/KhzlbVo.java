package org.cmms.modules.khgl.sjyh.entity;

import lombok.Data;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.enums.DesensitizeRuleEnums;

@Data
public class KhzlbVo {


    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String surname;
    @Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
    private String cardNbr;
    @Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
    private String busiPhone;
    @Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
    private String moPhone;
    @Desensitize(rule = DesensitizeRuleEnums.EMAIL)
    private String emailAddr;
    private String cutoffDay;
    private Double cardFees;
    private Double credLimit;
    private String dayOpened;
    @Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
    private String custrNbr;//证件号
    private String issueDay;//发卡日期
    private String xtitle;//发卡日期
    private Integer xb;



}
