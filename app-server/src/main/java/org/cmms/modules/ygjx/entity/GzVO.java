package org.cmms.modules.ygjx.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Date 2022/3/2
 * @Created by eran
 */
@Data
public class GzVO {
    private String title;
    private BigDecimal extra = new BigDecimal(0);
    List<JbgzVO> jbgzVOS;

}
