package org.cmms.common.util;

import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.system.vo.DictModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/5/18
 * @Created by eran
 */
public class DictTextToValusUtil {


    public static String yhzgx(String yhzgx){
        if (StringUtils.isBlank(yhzgx))
            return "8";
        if (yhzgx.contains("户主") || yhzgx.contains("本人"))
            return "1";
        if (yhzgx.contains("配偶"))
            return "2";
        if (yhzgx.contains("父母"))
            return "3";
        if (yhzgx.contains("孙"))
            return "7";
        if (yhzgx.contains("子") || yhzgx.contains("女") || yhzgx.contains("婿") || yhzgx.contains("媳"))
            return "3";
        return "8";
    }

    //为浏阳导入户籍数据
    public static String yhzgx2(String yhzgx){
        if (StringUtils.isBlank(yhzgx))
            return "8";
        if (yhzgx.contains("户主") || yhzgx.contains("本人"))
            return "1";
        if (yhzgx.contains("配偶") || yhzgx.contains("妻") || yhzgx.contains("夫"))
            return "2";
        if (yhzgx.contains("父母") || yhzgx.contains("母") || yhzgx.contains("父") || yhzgx.contains("婆婆") || yhzgx.contains("公公"))
            return "3";
        if (yhzgx.contains("孙") || yhzgx.contains("祖"))
            return "7";
        if (yhzgx.contains("子") || yhzgx.contains("女") || yhzgx.contains("婿") || yhzgx.contains("媳"))
            return "3";
        if (yhzgx.contains("姐") || yhzgx.contains("妹") || yhzgx.contains("嫂"))
            return "6";
        if (yhzgx.contains("兄") || yhzgx.contains("弟"))
            return "5";
        return "8";
    }

    public static String yhzgxnsb(String yhzgx){
        if (StringUtils.isBlank(yhzgx))
            return "8";
        if (yhzgx.contains("户主") || yhzgx.contains("本人"))
            return "1";
        if (yhzgx.contains("配偶"))
            return "2";
        if (yhzgx.contains("父与子"))
            return "3";
        if (yhzgx.contains("母与子"))
            return "4";
        if (yhzgx.contains("兄弟"))
            return "5";
        if (yhzgx.contains("姐妹"))
            return "6";
        if (yhzgx.contains("祖父母与孙子女"))
            return "7";
        return "8";
    }

    public static String sfljqk(String sfljqk){
        if (StringUtils.isBlank(sfljqk))
            return "2";
        if (sfljqk.contains("是"))
            return "1";
        return "2";
    }

    public static String ywcl(String ywcl){
        if (StringUtils.isBlank(ywcl))
            return "";
        if (ywcl.contains("无"))
            return "2";
        if (ywcl.contains("有"))
            return "1";
        return "";
    }

    public static String ywcl_xinhua(String ywcl){
        if (StringUtils.isBlank(ywcl)) {
            return "";
        } else if (ywcl.contains("30万以上")) {
            return "1";
        } else if (ywcl.contains("10万-30万元")) {
            return "2";
        } else if (ywcl.contains("10万以下")) {
            return "3";
        } else if (ywcl.contains("无")) {
            return "4";
        }
        return "";
    }

    public static String sfzbd(String sfzbd){
        if (StringUtils.isBlank(sfzbd))
            return "";
        if (sfzbd.contains("本村"))
            return "1";
        else if (sfzbd.contains("乡镇"))
            return "2";
        else if (sfzbd.contains("本县"))
            return "3";
        else if (sfzbd.contains("本市"))
            return "4";
        else if (sfzbd.contains("省内"))
            return "5";
        else if (sfzbd.contains("省外"))
            return "6";
        else if (sfzbd.contains("国外"))
            return "7";
        return "";
    }

    public static String gzlx(String gzlx){
        if (StringUtils.isBlank(gzlx))
            return "";
        if (gzlx.contains("经营类"))
            return "1";
        else if (gzlx.contains("公职人员"))
            return "2";
        else if (gzlx.contains("务工"))
            return "3";
        return "";
    }

    public static String jtsr(String jtsr){
        if (StringUtils.isBlank(jtsr))
            return "";
        if (jtsr.contains("1-5"))
            return "1";
        if (jtsr.contains("5-10"))
            return "2";
        if (jtsr.contains("10-15"))
            return "3";
        if (jtsr.contains("15万以上"))
            return "4";
        return "";
    }

    public static String jtsr2(String jtsr){
        if (StringUtils.isBlank(jtsr))
            return "";
        if (jtsr.contains("1-5"))
            return "1";
        if (jtsr.contains("5-10"))
            return "2";
        if (jtsr.contains("10万以上"))
            return "3";
        return "";
    }

    public static String jtsr_xinhua(String jtsr){
        if (StringUtils.isBlank(jtsr))
            return "";
        if (jtsr.contains("1-5"))
            return "1";
        if (jtsr.contains("5-10"))
            return "2";
        if (jtsr.contains("10-20"))
            return "3";
        if (jtsr.contains("20万以上"))
            return "4";
        return "";
    }

    public static String jkzk(String jkzk) {
        if (StringUtils.isEmpty(jkzk)) {
            return "";
        }
        if (jkzk.contains("很好")) {
            return "1";
        }
        if (jkzk.contains("较好")) {
            return "2";
        }
        if (jkzk.contains("一般")) {
            return "3";
        }
        return "";
    }

    public static String khxyd(String khxyd) {
        if (StringUtils.isEmpty(khxyd)) {
            return "";
        }
        if (khxyd.contains("很好")) {
            return "1";
        }
        if (khxyd.contains("较好")) {
            return "2";
        }
        if (khxyd.contains("一般")) {
            return "3";
        }
        return "";
    }

    public static String jtldlrs(String jtldlrs) {
        if (StringUtils.isEmpty(jtldlrs)) {
            return "";
        }
        if (jtldlrs.contains("4人")) {
            return "1";
        }
        if (jtldlrs.contains("3人")) {
            return "2";
        }
        if (jtldlrs.contains("2人")) {
            return "3";
        }
        return "";
    }

    public static String fwjz(String fwjz) {
        if (StringUtils.isEmpty(fwjz)) {
            return "";
        }
        if (fwjz.contains("10万元以下")) {
            return "1";
        }
        if (fwjz.contains("10万-50万元")) {
            return "2";
        }
        if (fwjz.contains("50万元以上")) {
            return "3";
        }
        return "";
    }

    public static String fwjz_xinhua(String fwjz) {
        if (StringUtils.isEmpty(fwjz)) {
            return "";
        } else if (fwjz.contains("20万元以下")) {
            return "1";
        } else if (fwjz.contains("20万-60万元")) {
            return "2";
        } else if (fwjz.contains("60万-100万元")) {
            return "3";
        } else if (fwjz.contains("100万-150万元")) {
            return "4";
        } else if (fwjz.contains("150万元以上")) {
            return "5";
        }
        return "";
    }


    public static  int jcmxcs(String ncfcxx,String cqywfc,String ywcl,String gzlx,String jtsr){
        int result = 0;
        if (ncfcxx.contains("良好"))
            result += 30000;
        if (ncfcxx.contains("一般"))
            result += 20000;
        if (cqywfc.contains("有"))
            result += 10000;
        if (ywcl.contains("有"))
            result += 10000;
        if ("经营类".equals(gzlx))
            result += 10000;
        if (jtsr.contains("1-5"))
            result += 10000;
        if (jtsr.contains("5-10"))
            result += 20000;
        if (jtsr.contains("10-15"))
            result += 30000;
        if (jtsr.contains("15万以上"))
            result += 40000;
        return result;
    }

    public static Map<String, Integer> jcmxcs_xinhua(BigDecimal jtckrphj, String jkzkqk, String jtldrs, String jtsr,
                                                     String fwjzqk, String ywcl, String xyzk, String jtywgzry, String jtywdxs) {
        int result = 0;
        int pydf = 0;
        if (jtckrphj.compareTo(new BigDecimal(50000)) >= 0) {
            pydf += 10;
        } else if(jtckrphj.compareTo(new BigDecimal(40000)) >= 0) {
            pydf += 7;
        } else if(jtckrphj.compareTo(new BigDecimal(30000)) >= 0) {
            pydf += 5;
        } else if(jtckrphj.compareTo(new BigDecimal(0)) > 0) {
            pydf += 3;
        }
        if (jkzkqk.contains("很好")) {
            pydf += 10;
        } else if (jkzkqk.contains("较好")) {
            pydf += 7;
        } else if(jkzkqk.contains("一般")) {
            pydf += 5;
        }

        if (jtldrs.contains("4人")) {
            pydf += 10;
        } else if (jkzkqk.contains("3人")) {
            pydf += 7;
        } else if(jkzkqk.contains("2人")) {
            pydf += 5;
        }

        if (jtsr.contains("1-5")) {
            pydf += 5;
        } else if (jtsr.contains("5-10")) {
            pydf += 10;
        } else if(jtsr.contains("10-20")) {
            pydf += 15;
        } else if(jtsr.contains("20万以上")) {
            pydf += 20;
        }

        if (fwjzqk.contains("20万元以下")) {
            pydf += 10;
        } else if (fwjzqk.contains("20万-60万")) {
            pydf += 14;
        } else if(fwjzqk.contains("60万-100万")) {
            pydf += 16;
        } else if(fwjzqk.contains("100万-150万")) {
            pydf += 18;
        } else if(fwjzqk.contains("150万元以上")) {
            pydf += 20;
        }

        if (ywcl.contains("30万以上")) {
            pydf += 10;
        } else if (ywcl.contains("10万-30万")) {
            pydf += 8;
        } else if(ywcl.contains("10万以下")) {
            pydf += 5;
        }

        if (xyzk.contains("很好")) {
            pydf += 15;
        } else if (xyzk.contains("较好")) {
            pydf += 10;
        } else if(xyzk.contains("一般")) {
            pydf += 7;
        }

        if (jtywgzry.contains("有")) {
            pydf += 5;
        }

        if (jtywdxs.contains("有")) {
            pydf += 5;
        }
        if(pydf >= 90) {
            result = 200000;
        } else if (pydf >= 80) {
            result = 150000;
        } else if (pydf >= 70) {
            result = 100000;
        } else if (pydf >= 60) {
            result = 80000;
        } else if (pydf >= 50) {
            result = 60000;
        }else {
            result = 40000;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("pydf", pydf);
        map.put("result", result);
        return map;
    }

    public static String bysxqx(String bysxqx){
        if (StringUtils.isBlank(bysxqx))
            return "";
        if (bysxqx.contains("1"))
            return "1";
        if (bysxqx.contains("2"))
            return "2";
        if (bysxqx.contains("3"))
            return "3";
        if (bysxqx.contains("4"))
            return "4";
        if (bysxqx.contains("5"))
            return "5";
        if (bysxqx.contains("6"))
            return "6";
        if (bysxqx.contains("7"))
            return "7";
        if (bysxqx.contains("8"))
            return "8";
        if (bysxqx.contains("9"))
            return "9";
        if (bysxqx.contains("10"))
            return "10";
        if (bysxqx.contains("11"))
            return "11";
        if (bysxqx.contains("12"))
            return "12";
        if (bysxqx.contains("13"))
            return "13";
        if (bysxqx.contains("14"))
            return "14";
        if (bysxqx.contains("15"))
            return "15";
        return "";
    }

    public static String bysxqxByDict(List<DictModel> dictModels, String bysxqx) {
        try {
            DictModel dictModel = dictModels.stream().filter(item -> item.getText().equals(bysxqx)).findFirst().get();
            return dictModel.getValue();
        } catch (Throwable throwable) {
            return "";
        }
    }

    public static void main(String[] args) {
        String s ="第一轮评议表";
        String s2 ="第十轮评议表";
        String s3 ="第十二轮评议表";
        System.out.println(parsePyls(s));
        System.out.println(parsePyls(s2));
        System.out.println(parsePyls(s3));
    }
    public static int parsePyls(String sheetName){
        int di = sheetName.indexOf("第")+1;
        int lun = sheetName.indexOf("轮");
        String substring = sheetName.substring(di, lun);
        if ("一".equals(substring) || "1".equals(substring))
            return 1;
        if ("二".equals(substring) || "2".equals(substring))
            return 2;
        if ("三".equals(substring) || "3".equals(substring))
            return 3;
        if ("四".equals(substring) || "4".equals(substring))
            return 4;
        if ("五".equals(substring) || "5".equals(substring))
            return 5;
        if ("六".equals(substring) || "6".equals(substring))
            return 6;
        if ("七".equals(substring) || "7".equals(substring))
            return 7;
        if ("八".equals(substring) || "8".equals(substring))
            return 8;
        if ("九".equals(substring) || "9".equals(substring))
            return 9;
        if ("十".equals(substring) || "10".equals(substring))
            return 10;
        if ("十一".equals(substring) || "11".equals(substring))
            return 11;
        if ("十二".equals(substring) || "12".equals(substring))
            return 12;
        if ("十三".equals(substring) || "13".equals(substring))
            return 13;
        if ("十四".equals(substring) || "14".equals(substring))
            return 14;
        if ("十五".equals(substring) || "15".equals(substring))
            return 15;
        if ("十六".equals(substring) || "16".equals(substring))
            return 16;
        if ("十七".equals(substring) || "17".equals(substring))
            return 17;
        if ("十八".equals(substring) || "18".equals(substring))
            return 18;
        if ("十九".equals(substring) || "19".equals(substring))
            return 19;
        if ("二十".equals(substring) || "20".equals(substring))
            return 20;
        return 0;
    }

    public static String xb(String xb) {
        if (StringUtils.isBlank(xb))
            return "";
        if ("1".equals(xb)) {
            return "男";
        } else if ("2".equals(xb)) {
            return "女";
        } else {
            return xb;
        }
    }

    public static String ywbz(String ywbz) {
        if (StringUtils.isBlank(ywbz))
            return "";
        if ("有".equals(ywbz)) {
            return "1";
        } else if ("无".equals(ywbz)) {
            return "2";
        } else {
            return "";
        }
    }

    public static String ncfcqk(String ncfcqk) {
        if (StringUtils.isEmpty(ncfcqk)) {
            return "";
        }
        if (ncfcqk.contains("良好")) {
            return "1";
        }
        else if (ncfcqk.contains("一般")) {
            return "2";
        }
        else if (ncfcqk.contains("无")) {
            return "3";
        }
        return "";
    }

    public static String sfbz(String sfbz) {
        if (StringUtils.isBlank(sfbz))
            return "";
        if ("是".equals(sfbz)) {
            return "1";
        } else if ("否".equals(sfbz)) {
            return "2";
        } else {
            return "";
        }
    }
}
