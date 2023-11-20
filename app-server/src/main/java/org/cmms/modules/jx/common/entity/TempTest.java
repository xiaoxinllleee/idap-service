package org.cmms.modules.jx.common.entity;

import java.math.BigDecimal;

public class TempTest {
    public static void main(String[] args) {

        double round = Math.round(1200000.2233 / 10000 * 100) /  100;
        double round2 = Math.round(1200000.2233 / 100) /  100;
        double round3 = Math.round(1200000.2233 / 10000);
        double round4 = Math.round(new BigDecimal(1200000.2233).divide(new BigDecimal(10000)).doubleValue());
        System.out.println(round);
        System.out.println(round2);
        System.out.println(round3);
        System.out.println(round4);
    }
}
