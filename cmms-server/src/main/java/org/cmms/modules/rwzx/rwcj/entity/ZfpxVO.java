package org.cmms.modules.rwzx.rwcj.entity;

import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;

import java.math.BigDecimal;

/**
 * @Date 2023/5/12
 * @Created by eran
 */
@Data
public class ZfpxVO {
    @Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    private String khjl;
    @Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    private String sszh;
    private BigDecimal cg;
    private BigDecimal sb;
    private BigDecimal wzf;
    private BigDecimal rs;
    private BigDecimal zfl;
    private BigDecimal cgl;
}
