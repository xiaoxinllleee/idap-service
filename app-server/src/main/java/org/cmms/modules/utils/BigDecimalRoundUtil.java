package org.cmms.modules.utils;

import java.math.BigDecimal;

public class BigDecimalRoundUtil {
    public static BigDecimal round(BigDecimal bigDecimal){
        BigDecimal yw = new BigDecimal(10000);
        BigDecimal divide = bigDecimal.divide(yw);
        return new BigDecimal(Math.round(divide.doubleValue()));
    }
}
