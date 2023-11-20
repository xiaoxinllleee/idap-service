package org.cmms.modules.khgl.dkkh.entity;

import lombok.Data;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.enums.DesensitizeRuleEnums;

import java.math.BigDecimal;

/**
 * @Date 2022/3/14
 * @Created by eran
 */
@Data
public class DkxtjcVO {
    @Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
    private String dkzh;
    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String khmc;
    private BigDecimal dkye;
    private BigDecimal dkje;
    @Dict(dicCode = "wjflbz")
    private Integer qmfl;
    @Dict(dicCode = "wjflbz")
    private Integer clfl;
    @Dict(dicCode = "wjflbz")
    private Integer ycfl;
    @Dict(dicCode = "wjflbz")
    private Integer zrfl;
    @Dict(dicCode = "wjflbz")
    private Integer jcfl;

    private Integer jnc;
    private Integer jjc;
    private Integer jyc;
    private Integer jzr;

//    @Dict(dictTable = "KHGXGL_KHZLGL_GRKH",dicText = "khbh",dicCode = "zjhm",ds = "eweb")
//    private String khbh;

}
