package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 信用提示数据
 */

@Data
@ToString
public class TmpCreditXytssjVO {
    /**信用提示项目*/
    @Dict(dicCode = "xytsxm")
    private String xytsxm;
    /**证件号码*/
    private String zjhm;
    /**信贷往来机构数*/
    private Integer xdwljgs;
    /**历史业务次数累计*/
    private Integer lsywcslj;
    /**存量笔数*/
    private Integer clbs;
    /**存量首笔开户日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy.MM.dd")
    @DateTimeFormat(pattern="yyyy.MM.dd")
    private Date clsbkhrq;
    /**合同金额*/
    private java.math.BigDecimal htje;
    /**用信余额*/
    private java.math.BigDecimal yxye;
    /**历史本金逾期次数*/
    private Integer lsbjyqcs;
    /**历史利息违约次数*/
    private Integer lslxwycs;
    /**单月最高欠息总额*/
    private java.math.BigDecimal dyzgqxze;
    /**特殊交易次数*/
    private Integer tsjycs;
}
