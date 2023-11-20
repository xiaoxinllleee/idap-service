package org.cmms.modules.khgl.dkkh.entity;

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
public class BmkVO {

    @Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
    private String businessNo;
    private String jgdm;
    private String jgdmVal;
    private BigDecimal dkye;
    private BigDecimal dkje;
    private String qxDate;
    private String endDate;
    private String ghr;
    private String bsr;

    public void setCopy(CbsBormBase cbsBormBase){
        if (StringUtils.isNotBlank(cbsBormBase.getBusinessNo())){
            this.businessNo = cbsBormBase.getBusinessNo();
        }
        if (StringUtils.isNotBlank(cbsBormBase.getBrNo())){
            this.jgdm = cbsBormBase.getBrNo();
        }
        if (StringUtils.isNotBlank(cbsBormBase.getBrName())){
            this.jgdmVal = cbsBormBase.getBrName();
        }
        if (cbsBormBase.getLoanBal() != null){
            this.dkye = cbsBormBase.getLoanBal();
        }
        if (cbsBormBase.getApplicAmount() != null){
            this.dkje = cbsBormBase.getApplicAmount();
        }
        if (StringUtils.isNotBlank(cbsBormBase.getQxDate())){
            this.qxDate = cbsBormBase.getQxDate();
        }
        if (StringUtils.isNotBlank(cbsBormBase.getEndDate())){
            this.endDate = cbsBormBase.getEndDate();
        }
    }
}
