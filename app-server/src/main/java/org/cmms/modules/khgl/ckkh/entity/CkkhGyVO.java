package org.cmms.modules.khgl.ckkh.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2022/3/4
 * @Created by eran
 */
@Data
public class CkkhGyVO {

    private BigDecimal qmye = new BigDecimal(0);
    private BigDecimal qmrj = new BigDecimal(0);
    private BigDecimal hqye = new BigDecimal(0);
    private BigDecimal dqye = new BigDecimal(0);
    private BigDecimal hqrj = new BigDecimal(0);
    private BigDecimal dqrj = new BigDecimal(0);

}

