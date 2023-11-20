package org.cmms.modules.ywgl.ywl.ywlfpday.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 龚辉
 * @date 2023/10/27 9:50 周五
 */
@Data
public class YwlfpVo {
    /** 1:分配 2：按月分配到全行 3：按月分配到支行 */
    private String lx;
    private String zzbz;
    private String fprq;
    private List<String> ygghList;
    private String ygghListString;
    private List<String> gwxxList;
    private String fpid;
    private java.math.BigDecimal atmxjll;
    private java.math.BigDecimal atmywbs;
    private java.math.BigDecimal qtxjll;
    private java.math.BigDecimal qtywbs;
    private String fpczy;
}
