package org.cmms.modules.khgl.dkkh.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.enums.DesensitizeRuleEnums;

import java.math.BigDecimal;

/**
 * @Date 2022/4/12
 * @Created by eran
 */
@Data
public class BwblVO {

    @Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
    private String dkzh;
    private BigDecimal dkje;
    private BigDecimal dkye;
    private String ffrq;
    private String dqrq;
    private String ghr;
    private String bsr;

    public void init(TbDkYghhdksjmxYhx dao){
        if (StringUtils.isNotBlank(dao.getDkzh())){
            this.dkzh =  dao.getDkzh();
        }

        if (dao.getDkje() != null){
            this.dkje = dao.getDkje();
        }
        if (dao.getDkye() != null){
            this.dkye = dao.getDkye();
        }
        if (dao.getFfrq() != null){
            this.ffrq = DateUtil.format(dao.getFfrq(), DatePattern.NORM_DATE_PATTERN);
        }
        if (dao.getDqrq() != null){
            this.dqrq = DateUtil.format(dao.getDqrq(), DatePattern.NORM_DATE_PATTERN);
        }

    }
}
