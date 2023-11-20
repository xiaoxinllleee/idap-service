package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 贷记卡信息
 */

@Data
@ToString
public class CreditCardInfoVO {
    /**发卡日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy年MM月dd日")
    @DateTimeFormat(pattern="yyyy年MM月dd日")
    private Date fkrq;
    /**业务机构*/
    @Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    private String ywjg;
    /**卡号*/
    private String kh;
    /**卡种类*/
    @Dict(dicCode = "kzl")
    private String kzl;
    /**员工工号*/
    @Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
    private String yggh;
    /**卡状态标识*/
    @Dict(dicCode = "djkzl")
    private String kztbz;
    /**授信金额*/
    private Double sxje;
    /**透支余额截止日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy年MM月dd日")
    @DateTimeFormat(pattern="yyyy年MM月dd日")
    private Date tzyeLrsj;
    /**透支余额*/
    private Double tzye;
    /**逾期期数*/
    private Integer yqqs;
    /**逾期期数截止日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy年MM月dd日")
    @DateTimeFormat(pattern="yyyy年MM月dd日")
    private Date yqqsLrsj;
}
