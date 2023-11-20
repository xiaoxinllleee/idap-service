package org.cmms.common.util;

import java.math.BigDecimal;

/**
 * @Description //TODO
 * @Date 2021/3/19 23:19
 **/
@SuppressWarnings("all")
public class MoneyUtil {

    public static String format(BigDecimal money) {
        if (money == null) {
            return "0元";
        }
        String moneyString = money.toString();
        System.out.println("moneyString="+moneyString);
        /*判断小数后缀*/
        int i = moneyString.indexOf(".");
        if (i > 0) {
            moneyString = moneyString.substring(0, i);
        }
        /*执行核心逻辑*/
        StringBuilder stringBuilder = new StringBuilder();
        if (moneyString.length() < 5) {//万以下的单位
            stringBuilder.append(moneyString);
        } else if (moneyString.length() >= 9) {//亿为单位
            String yi = moneyString.substring(0, moneyString.length() - 8);
            stringBuilder.append(yi);
            stringBuilder.append("亿");
            String wan = moneyString.substring(yi.length(), moneyString.length() - 4);
            stringBuilder.append(wan);
            stringBuilder.append("万");
            stringBuilder.append(moneyString.substring(wan.length() + yi.length(), moneyString.length()));
        } else {//万为单位
            String wan = moneyString.substring(0, moneyString.length() - 4);
            stringBuilder.append(wan);
            stringBuilder.append("万");
            stringBuilder.append(moneyString.substring(wan.length(), moneyString.length()));
        }
        stringBuilder.append("元");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(format(new BigDecimal(44)));
    }
}
