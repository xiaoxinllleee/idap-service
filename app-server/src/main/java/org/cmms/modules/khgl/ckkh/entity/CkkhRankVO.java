package org.cmms.modules.khgl.ckkh.entity;

import lombok.Data;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.enums.DesensitizeRuleEnums;

import java.math.BigDecimal;

/**
 * 存款客户排名
 * @Date 2022/3/9
 * @Created by eran
 */
@Data
public class CkkhRankVO {
    @Desensitize(rule = DesensitizeRuleEnums.RSAENCRYPT)
    private String zjhm;
    @Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
    private String khmc;
    private Integer sex;
    //期末余额
    private BigDecimal qmye;
    //较年初
    private BigDecimal yejnc;
    //日均
    private BigDecimal rj;
    //日均较年初
    private BigDecimal rjjnc;
    //是否关注用户 1是 2否
    private String isGz = "2";
}
