package org.cmms.modules.khgl.dkkh.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.enums.DesensitizeRuleEnums;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date 2022/4/7
 * @Created by eran
 */
@Data
public class HtlbVO {
    private String hth;
    @Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
    private String hthjm;
    @Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    private String jgdm;
    private String jgdmVal;
    @Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    private String yggh;
    private String ghr;
    private String bsr;
    private BigDecimal dkye;
    private BigDecimal htje;
    private Date ffrq;
    private String ffrqVal;
    private Date dqrq;
    private String dqrqVal;
    @Dict(dicCode = "wjflbz")
    private String wjfl;
    private String wjflVal;
    private String cpxx;

    public void formatDate(){
        if (this.ffrq != null){
            this.ffrqVal = DateUtil.format(this.ffrq, DatePattern.NORM_DATE_PATTERN);
        }
        if (this.dqrq != null){
            this.dqrqVal = DateUtil.format(this.dqrq, DatePattern.NORM_DATE_PATTERN);
        }
    }
}
