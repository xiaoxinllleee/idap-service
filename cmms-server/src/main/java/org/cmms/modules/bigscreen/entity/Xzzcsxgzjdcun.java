package org.cmms.modules.bigscreen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Xzzcsxgzjdcun {
    @Dict(dicCode ="wgbh",dictTable="YXDYGL_MAIN",dicText="WGMC")
    private String wgbh;
    @Dict(dicCode ="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    private String zkhjl;
    private BigDecimal zhs;
    private BigDecimal bmdhs;
    private BigDecimal zhsdBmdhs;
    private BigDecimal zhsdEdhz;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy年MM月dd日")
    private Date tjrq;
    private BigDecimal hnkdDrhs;
    private BigDecimal hnkdEdhz;
    private BigDecimal dkhtXzkhs;
    private BigDecimal dkhtQyedhz;
    private BigDecimal dkyxXzkhs;
    private BigDecimal dkyxEdhz;

}
