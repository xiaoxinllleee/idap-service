package org.cmms.modules.ygjx.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2022/3/2
 * @Created by eran
 */
@Data
public class JbgzVO {
    //全部工资
    private String title;
    private BigDecimal extra = new BigDecimal(0);
}
