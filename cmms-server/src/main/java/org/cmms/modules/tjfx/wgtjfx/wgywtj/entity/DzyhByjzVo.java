package org.cmms.modules.tjfx.wgtjfx.wgywtj.entity;

import lombok.Data;

/**
 * App首页-网格信息-本月净增
 *
 * @author 龚辉
 * @date 2023/6/28 10:08 周三
 */
@Data
public class DzyhByjzVo {
    //电子银行业务信息
    private Integer sjyhjzkhs;
    private Integer wsyhjzkhs;
    private Integer etcjzkhs;
    private Integer xykjzkhs;
    private Integer sbkjzkhs;
    private Integer fxetfjzkhs;

    //存贷款信息
    private Integer dkkhsbyjz;
    private java.math.BigDecimal dkyebyjz;
    private Integer ckkhsbyjz;
    private java.math.BigDecimal ckyebyjz;
    private Integer bnbldkkhsbyjz;
    private java.math.BigDecimal bnbldkyebyjz;
    private Integer bwbldkkhsbyjz;
    private java.math.BigDecimal bwbldkyebyjz;
}
