package org.cmms.modules.khgl.ckkh.entity;

import lombok.Data;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.enums.DesensitizeRuleEnums;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date 2022/4/8
 * @Created by eran
 */
@Data
public class ZhlbVO {
    @Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
    private String ckzh;
    //是定期还是获取  定期存了多久
    private String ckxx;
    private BigDecimal qmye;
    private BigDecimal nrp3;
    private String khrq;
    private String endDate;
    @Dict(dicCode = "yxlx")
    private String yxlx;
    private String yxbl;
    private String yxry;
}
