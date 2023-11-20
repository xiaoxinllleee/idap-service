package org.cmms.modules.ygjx.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Date 2022/6/29
 * @Created by eran
 */
@Data
public class CDKLineChartVO {
    private List<String> categories;
    private List<Integer> ck;
    private List<Integer> dk;

}
