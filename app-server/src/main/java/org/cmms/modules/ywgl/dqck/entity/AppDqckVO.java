package org.cmms.modules.ywgl.dqck.entity;

import lombok.Data;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.enums.DesensitizeRuleEnums;

/**
 * @Date 2022/8/7
 * @Created by eran
 */
@Data
public class AppDqckVO {
    @Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
    private String ckzh;
    private String intToDt;
    private String cq;
    private String qmye;
    private String dqts;
    @Dict(dicCode = "zjhm",dictTable = "KHGXGL_KHZLGL_GRKH",dicText = "khbh" ,ds = "eweb")
    @Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
    private String zjhm;
    @Dict(dicCode = "zjhm",dictTable = "KHGXGL_KHZLGL_GRKH",dicText = "sjhm" ,ds = "eweb")
    @Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
    private String zjhm2;
    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String custName;
    private String nl = "0";
    private String sex = "1";
}
