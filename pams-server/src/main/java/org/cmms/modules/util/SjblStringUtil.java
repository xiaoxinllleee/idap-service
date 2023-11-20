package org.cmms.modules.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class SjblStringUtil {

    public static String getLeftAndRightString(String s){
        int i = s.indexOf("[")+1;
        int i1 = s.indexOf("]");
        return s.substring(i,i1);
    }

    public static boolean isNumeric(String str) {
        try {
            new BigDecimal(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isNumeric("1.0"));
        System.out.println(new BigDecimal("1.0"));
    }
}
