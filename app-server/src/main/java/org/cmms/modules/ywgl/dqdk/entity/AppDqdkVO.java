package org.cmms.modules.ywgl.dqdk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.enums.DesensitizeRuleEnums;

import java.util.Date;

/**
 * @Date 2022/8/7
 * @Created by eran
 */
@Data
public class AppDqdkVO {
    @Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
    private String dkzh;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date dqrq;
    private String dkje;
    private String dkye;
    private String dqts;
    //@Dict(dicCode = "zjhm",dictTable = "KHGXGL_KHZLGL_GRKH",dicText = "khbh" ,ds = "eweb")
    @Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
    private String zjhm;
    @Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
    private String khbh;
    @Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
    private String sjhm;
    //@Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
    private String zjhm2;
    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String custName;
    private String sex = "1";
    @Dict(dicCode = "wjflbz")
    private String fiveClassType;
    private String nl;
}
