package org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity;

import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;

/**
 * @author 龚辉
 * @date 2023/8/23 23:40 周三
 */
@Data
public class PfrZfMxVo {

    /** 回访日期 */
    private String hfrq;

    /** 走访时间 */
    private String hfsj;

    /** 客户名称 */
    private String khmc;

    /** 客户类型 */
    @Dict(dicCode = "khlx")
    private String khlx;

    /** 走访人 */
    @Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    private String zfr;

    /** 走访支行 */
    @Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    private String zfzh;

    /** 所属网格 */
    @Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    private String sswg;

    /** 客户所属支行 */
    @Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    private String khsszh;
}
