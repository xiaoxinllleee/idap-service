package org.cmms.modules.tjfx.wgtjfx.wgywtj.entity;

import lombok.Data;

/**
 * @author 龚辉
 * @date 2023/7/11 16:42 周二
 */
@Data
public class PyxxVo {

    /** 采集户数 */
    private Integer cjhs;
    /** 初评户数 */
    private Integer cphs;
    /** 初评授信人数 */
    private Integer bkbcpSxdxrs;
    /** 初评金额 */
    private java.math.BigDecimal cpje;
    /** 复评户数 */
    private Integer fphs;
    /** 复评金额 */
    private java.math.BigDecimal fpje;
    /** 审定户数 */
    private Integer sdhs;
    /** 审定金额 */
    private java.math.BigDecimal sdje;

    private Integer bkbfpSxdxrs;
    private Integer zhsdSxdxrs;
    private Integer hnkddrhs;
    private java.math.BigDecimal hnkdedhz;
    private Integer hnkdqyhs;
    private java.math.BigDecimal hnkdqyedhz;
    private Integer dkhtxzkhs;
    private java.math.BigDecimal dkhtqyedhz;

    private Integer bmdhs;
    private Integer hmdhs;
    private Integer huimdhs;
    private Integer clhs;
}
