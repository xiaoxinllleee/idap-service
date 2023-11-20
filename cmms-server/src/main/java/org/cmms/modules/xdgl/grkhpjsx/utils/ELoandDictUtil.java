package org.cmms.modules.xdgl.grkhpjsx.utils;

import org.apache.commons.lang3.StringUtils;
import org.cmms.common.enums.QydmEnums;

import java.util.HashMap;
import java.util.Map;

public class ELoandDictUtil {
    static Map<String,String> mzMap = null;
    static Map<String,String> xzMap = null;
    static Map<String,String> xzMap310 = null;
    static Map<String,String> xzMap100 = null;
    static Map<String,String> xzMap365 = null;
    static Map<String,String> jkMap = null;
    static Map<String,String> jzzkMap = null;
    static Map<String,String> zgxlMap = null;
    static Map<String,String> zwMap = null;

    public static void main(String[] args) {
        String mzs[]={
                "10-研究生",
                "20-大学本科",
                "30-大学专科和专科学校",
                "40-中等专业或技术学校",
                "50-技术学校",
                "60-高中",
                "70-初中",
                "80-小学",
                "90-文盲或半文盲",
                "99-未知"
                //"1-自置","2-按揭","3-亲属楼宇","4-集体宿舍","5-租房","6-共有住宅","7-其他","8-未知"
                //"01-集里办事处","02-荷花办事处","03-关口办事处","04-溪江乡","05-古港镇","06-三口乡","07-沿溪镇","08-永和镇","09-七宝山乡","10-官渡镇","11-达浒镇","12-大围山镇","13-张坊镇","14-小河乡","15-高坪镇","16-大平桥镇","17-枨冲镇","18-葛家乡","19-镇头镇","20-柏嘉镇","21-普迹镇","22-官桥乡","23-金刚镇","24-大瑶镇","25-杨花乡","26-澄潭江镇","27-文家市镇","28-中和镇","29-洞阳镇","30-蕉溪乡","31-淮川办事处","32-淳口镇","33-龙伏镇","34-社港镇","35-沙市镇","36-永安镇","37-北盛镇"
                //"1-汉族","10-壮族","11-满族","12-彝族","13-土族","14-水族","16-蒙古族","17-朝鲜族","18-瑶族","19-白族","2-回族","20-藏族","21-维吾尔族","22-苗族","23-布依族","24-侗族","25-土家族","26-哈尼族","27-哈萨克族","28-傣族","29-黎族","3-俄罗斯族","30-傈僳族","31-佤族","32-畲族","33-高山族","34-拉祜族","35-东乡族","36-纳西族","37-景颇族","38-柯尔克孜族","39-达斡尔族","40-仫佬族","41-羌族","42-布朗族","43-撒拉族","44-毛南族","45-仡佬族","46-锡伯族","47-阿昌族","48-普米族","49-塔吉克族","50-怒族","51-乌孜别克族","52-鄂温克族","53-德昂族","54-保安族","55-裕固族","56-京族","57-塔塔尔族","58-独龙族","59-鄂伦春族","60-赫哲族","61-门巴族","62-珞巴族","63-基诺族","64-其他"
        };

        for (int i = 0; i < mzs.length; i++) {
            String mz = mzs[i];
            String[] split = mz.split("-");
            System.out.println("zgxlMap.put(\""+split[0]+"\""+",\""+mz+"\");");

        }
    }


    public static String getMz(String mz){
        if (mzMap == null){
            mzMap = new HashMap<>();
            mzMap.put("1","1-汉族");
            mzMap.put("10","10-壮族");
            mzMap.put("11","11-满族");
            mzMap.put("12","12-彝族");
            mzMap.put("13","13-土族");
            mzMap.put("14","14-水族");
            mzMap.put("16","16-蒙古族");
            mzMap.put("17","17-朝鲜族");
            mzMap.put("18","18-瑶族");
            mzMap.put("19","19-白族");
            mzMap.put("2","2-回族");
            mzMap.put("20","20-藏族");
            mzMap.put("21","21-维吾尔族");
            mzMap.put("22","22-苗族");
            mzMap.put("23","23-布依族");
            mzMap.put("24","24-侗族");
            mzMap.put("25","25-土家族");
            mzMap.put("26","26-哈尼族");
            mzMap.put("27","27-哈萨克族");
            mzMap.put("28","28-傣族");
            mzMap.put("29","29-黎族");
            mzMap.put("3","3-俄罗斯族");
            mzMap.put("30","30-傈僳族");
            mzMap.put("31","31-佤族");
            mzMap.put("32","32-畲族");
            mzMap.put("33","33-高山族");
            mzMap.put("34","34-拉祜族");
            mzMap.put("35","35-东乡族");
            mzMap.put("36","36-纳西族");
            mzMap.put("37","37-景颇族");
            mzMap.put("38","38-柯尔克孜族");
            mzMap.put("39","39-达斡尔族");
            mzMap.put("40","40-仫佬族");
            mzMap.put("41","41-羌族");
            mzMap.put("42","42-布朗族");
            mzMap.put("43","43-撒拉族");
            mzMap.put("44","44-毛南族");
            mzMap.put("45","45-仡佬族");
            mzMap.put("46","46-锡伯族");
            mzMap.put("47","47-阿昌族");
            mzMap.put("48","48-普米族");
            mzMap.put("49","49-塔吉克族");
            mzMap.put("50","50-怒族");
            mzMap.put("51","51-乌孜别克族");
            mzMap.put("52","52-鄂温克族");
            mzMap.put("53","53-德昂族");
            mzMap.put("54","54-保安族");
            mzMap.put("55","55-裕固族");
            mzMap.put("56","56-京族");
            mzMap.put("57","57-塔塔尔族");
            mzMap.put("58","58-独龙族");
            mzMap.put("59","59-鄂伦春族");
            mzMap.put("60","60-赫哲族");
            mzMap.put("61","61-门巴族");
            mzMap.put("62","62-珞巴族");
            mzMap.put("63","63-基诺族");
            mzMap.put("64","64-其他");
        }
        return StringUtils.isNoneBlank(mzMap.get(mz))?mzMap.get(mz): StringUtils.EMPTY;
    }
    public static String getXz(String xz){
        if (xzMap == null){
            xzMap = new HashMap<>();
            /*xzMap.put("集里办事处","01-集里办事处");
            xzMap.put("荷花办事处","02-荷花办事处");
            xzMap.put("关口办事处","03-关口办事处");
            xzMap.put("溪江乡","04-溪江乡");
            xzMap.put("古港镇","05-古港镇");
            xzMap.put("三口乡","06-三口乡");
            xzMap.put("沿溪镇","07-沿溪镇");
            xzMap.put("永和镇","08-永和镇");
            xzMap.put("七宝山乡","09-七宝山乡");
            xzMap.put("官渡镇","10-官渡镇");
            xzMap.put("达浒镇","11-达浒镇");
            xzMap.put("大围山镇","12-大围山镇");
            xzMap.put("张坊镇","13-张坊镇");
            xzMap.put("小河乡","14-小河乡");
            xzMap.put("高坪镇","15-高坪镇");
            xzMap.put("大平桥镇","16-大平桥镇");
            xzMap.put("枨冲镇","17-枨冲镇");
            xzMap.put("葛家乡","18-葛家乡");
            xzMap.put("镇头镇","19-镇头镇");
            xzMap.put("柏嘉镇","20-柏嘉镇");
            xzMap.put("普迹镇","21-普迹镇");
            xzMap.put("官桥乡","22-官桥乡");
            xzMap.put("金刚镇","23-金刚镇");
            xzMap.put("大瑶镇","24-大瑶镇");
            xzMap.put("杨花乡","25-杨花乡");
            xzMap.put("澄潭江镇","26-澄潭江镇");
            xzMap.put("文家市镇","27-文家市镇");
            xzMap.put("中和镇","28-中和镇");
            xzMap.put("洞阳镇","29-洞阳镇");
            xzMap.put("蕉溪乡","30-蕉溪乡");
            xzMap.put("淮川办事处","31-淮川办事处");
            xzMap.put("淳口镇","32-淳口镇");
            xzMap.put("龙伏镇","33-龙伏镇");
            xzMap.put("社港镇","34-社港镇");
            xzMap.put("沙市镇","35-沙市镇");
            xzMap.put("永安镇","36-永安镇");
            xzMap.put("北盛镇","37-北盛镇");*/

            xzMap.put("集里办事处","1041435");
            xzMap.put("荷花办事处","1041574");
            xzMap.put("关口办事处","1041943");
            xzMap.put("溪江乡","1042129");
            xzMap.put("古港镇","1042388");
            xzMap.put("三口乡","1042776");
            xzMap.put("沿溪镇","1043014");
            xzMap.put("永和镇","1043254");
            xzMap.put("七宝山乡","1043432");
            xzMap.put("官渡镇","1043567");
            xzMap.put("达浒镇","1043946");
            xzMap.put("大围山镇","1044221");
            xzMap.put("张坊镇","1044589");
            xzMap.put("小河乡","1044721");
            xzMap.put("高坪镇","1044889");
            xzMap.put("太平桥镇","1045418");
            xzMap.put("枨冲镇","1045628");
            xzMap.put("葛家乡","1046032");
            xzMap.put("镇头镇","1046174");
            xzMap.put("柏嘉镇","1046688");
            xzMap.put("普迹镇","1046879");
            xzMap.put("官桥乡","1047300");
            xzMap.put("金刚镇","1047556");
            xzMap.put("大瑶镇","1047857");
            xzMap.put("杨花乡","1048047");
            xzMap.put("澄潭江镇","1048297");
            xzMap.put("文家市镇","1048682");
            xzMap.put("中和镇","1048810");
            xzMap.put("洞阳镇","1048983");
            xzMap.put("蕉溪乡","1049239");
            xzMap.put("淮川办事处","1049408");
            xzMap.put("淳口镇","1049550");
            xzMap.put("龙伏镇","1049837");
            xzMap.put("社港镇","1050147");
            xzMap.put("沙市镇","1050570");
            xzMap.put("永安镇","1051109");
            xzMap.put("北盛镇","1051440");
        }
        return StringUtils.isNoneBlank(xzMap.get(xz))?xzMap.get(xz): StringUtils.EMPTY;
    }


    public static String getXz310(String xz){
        if (xzMap310 == null){
            xzMap310 = new HashMap<>();
            xzMap310.put("走马街镇","2603932");
            xzMap310.put("梓门桥镇","2603866");
            xzMap310.put("印塘乡","2603753");
            xzMap310.put("永丰镇","2603801");
            xzMap310.put("花门镇","2603256");
            xzMap310.put("洪山殿镇","2603209");
            xzMap310.put("井字镇","2603327");
            xzMap310.put("石牛乡","2603577");
            xzMap310.put("沙塘乡","2603487");
            xzMap310.put("蛇形山镇","2603519");
            xzMap310.put("青树坪镇","2603365");
            xzMap310.put("锁石镇","2603638");
            xzMap310.put("甘棠镇","2603061");
            xzMap310.put("荷叶镇","2603145");
            xzMap310.put("三塘铺镇","2603434");
            xzMap310.put("杏子铺镇","2603678");
            xzMap310.put("金开街道办","2603318");
            xzMap310.put("永丰街道办","2603318");
        }
        return StringUtils.isNoneBlank(xzMap310.get(xz))?xzMap310.get(xz): StringUtils.EMPTY;
    }


    public static String getXzByQydm(String xz,String qydm){
        if (QydmEnums.LIUYANG.getQydmCode().equals(qydm)){
            if (xzMap == null){
                xzMap = new HashMap<>();
                xzMap.put("集里办事处","1041435");
                xzMap.put("荷花办事处","1041574");
                xzMap.put("关口办事处","1041943");
                xzMap.put("溪江乡","1042129");
                xzMap.put("古港镇","1042388");
                xzMap.put("三口乡","1042776");
                xzMap.put("沿溪镇","1043014");
                xzMap.put("永和镇","1043254");
                xzMap.put("七宝山乡","1043432");
                xzMap.put("官渡镇","1043567");
                xzMap.put("达浒镇","1043946");
                xzMap.put("大围山镇","1044221");
                xzMap.put("张坊镇","1044589");
                xzMap.put("小河乡","1044721");
                xzMap.put("高坪镇","1044889");
                xzMap.put("太平桥镇","1045418");
                xzMap.put("枨冲镇","1045628");
                xzMap.put("葛家乡","1046032");
                xzMap.put("镇头镇","1046174");
                xzMap.put("柏嘉镇","1046688");
                xzMap.put("普迹镇","1046879");
                xzMap.put("官桥乡","1047300");
                xzMap.put("金刚镇","1047556");
                xzMap.put("大瑶镇","1047857");
                xzMap.put("杨花乡","1048047");
                xzMap.put("澄潭江镇","1048297");
                xzMap.put("文家市镇","1048682");
                xzMap.put("中和镇","1048810");
                xzMap.put("洞阳镇","1048983");
                xzMap.put("蕉溪乡","1049239");
                xzMap.put("淮川办事处","1049408");
                xzMap.put("淳口镇","1049550");
                xzMap.put("龙伏镇","1049837");
                xzMap.put("社港镇","1050147");
                xzMap.put("沙市镇","1050570");
                xzMap.put("永安镇","1051109");
                xzMap.put("北盛镇","1051440");
            }
            return StringUtils.isNoneBlank(xzMap.get(xz))?xzMap.get(xz): StringUtils.EMPTY;
        }else if (QydmEnums.SHUANGFENG.equals(qydm)){
            if (xzMap310 == null){
                xzMap310 = new HashMap<>();
                xzMap310.put("走马街镇","2603932");
                xzMap310.put("梓门桥镇","2603866");
                xzMap310.put("印塘乡","2603753");
                xzMap310.put("永丰镇","2603801");
                xzMap310.put("花门镇","2603256");
                xzMap310.put("洪山殿镇","2603209");
                xzMap310.put("井字镇","2603327");
                xzMap310.put("石牛乡","2603577");
                xzMap310.put("沙塘乡","2603487");
                xzMap310.put("蛇形山镇","2603519");
                xzMap310.put("青树坪镇","2603365");
                xzMap310.put("锁石镇","2603638");
                xzMap310.put("甘棠镇","2603061");
                xzMap310.put("荷叶镇","2603145");
                xzMap310.put("三塘铺镇","2603434");
                xzMap310.put("杏子铺镇","2603678");
                xzMap310.put("金开街道办","2603318");
                xzMap310.put("永丰街道办","2603318");
            }
            return StringUtils.isNoneBlank(xzMap310.get(xz))?xzMap310.get(xz): StringUtils.EMPTY;
        }else if (QydmEnums.XIANGXIANG.getQydmCode().equals(qydm)){
            if (xzMap100 == null){
                xzMap100 = new HashMap<>();
                xzMap100.put("白田镇","1964307");
                xzMap100.put("东郊乡","1957358");
                xzMap100.put("中沙镇","1960316");
                xzMap100.put("金薮乡","1963916");
                xzMap100.put("潭市镇","1960553");
                xzMap100.put("梅桥镇","1958161");
                xzMap100.put("龙洞乡","1957830");
                xzMap100.put("翻江镇","1962515");
                xzMap100.put("虞塘镇","1965784");
                xzMap100.put("金石镇","1964947");
                xzMap100.put("月山镇","1963169");
                xzMap100.put("山枣镇","1959817");
                xzMap100.put("壶天镇","1961957");
                xzMap100.put("泉塘镇","1958722");
                xzMap100.put("育段乡","1959357");
                xzMap100.put("东山办事处","1965411");
                xzMap100.put("昆仑桥办事处","1965528");
                xzMap100.put("栗山镇","1966139");
                xzMap100.put("新湘路办事处","1965656");
                xzMap100.put("望春门办事处","1965719");
                xzMap100.put("其他","1966411");
                xzMap100.put("其他","1966412");
                xzMap100.put("棋梓镇","1961081");
                xzMap100.put("毛田镇","1961509");
            }
            return StringUtils.isNoneBlank(xzMap100.get(xz))?xzMap100.get(xz): StringUtils.EMPTY;
        }else if (QydmEnums.LINWU.equals(qydm)){
            if (xzMap365 == null){
                xzMap365 = new HashMap<>();
                xzMap365.put("江西省","2621360");
                xzMap365.put("山东省莱州市柞村镇","765761");
                xzMap365.put("蓝山县","215323");
                xzMap365.put("郴州市","178661");
                xzMap365.put("大冲乡","180878");
                xzMap365.put("株洲市","216061");
                xzMap365.put("广东省","215405");
                xzMap365.put("麦市乡","182331");
                xzMap365.put("汝城县","190298");
                xzMap365.put("岚桥镇","181026");
                xzMap365.put("嘉禾县","186320");
                xzMap365.put("镇南乡","188997");
                xzMap365.put("广宜乡","189078");
                xzMap365.put("安仁县","186287");
                xzMap365.put("三合乡","180550");
                xzMap365.put("桂阳县","186263");
                xzMap365.put("宜章县","186258");
                xzMap365.put("资兴市","186304");
                xzMap365.put("汾市乡","173537");
                xzMap365.put("武水镇","173521");
                xzMap365.put("花塘乡","173527");
                xzMap365.put("南强乡","173531");
                xzMap365.put("永兴县","190307");
                xzMap365.put("同益乡","173541");
                xzMap365.put("香花岭镇","179640");
                xzMap365.put("西瑶乡","174552");
                xzMap365.put("土地乡","174831");
                xzMap365.put("水东乡","173967");
                xzMap365.put("万水乡","175606");
                xzMap365.put("金江镇","173544");
                xzMap365.put("接龙乡","175579");
                xzMap365.put("武源乡","175782");
                xzMap365.put("楚江乡","174943");
                xzMap365.put("舜峰镇","149914");
                xzMap365.put("舜峰镇","149929");
            }
            return StringUtils.isNoneBlank(xzMap365.get(xz))?xzMap365.get(xz): StringUtils.EMPTY;

        }
        return  StringUtils.EMPTY;
    }








    public static String getJK(String jk){
        if (jkMap == null){
            jkMap = new HashMap<>();
            jkMap.put("1","1-好");
            jkMap.put("2","2-较好");
            jkMap.put("3","3-一般");
            jkMap.put("4","4-差");
        }
        return StringUtils.isNoneBlank(jkMap.get(jk))?jkMap.get(jk): StringUtils.EMPTY;
    }

    public static String getJzzk(String jzzk){
        if (jzzkMap == null){
            jzzkMap = new HashMap<>();
            jzzkMap.put("1","1-自置");
            jzzkMap.put("2","2-按揭");
            jzzkMap.put("3","3-亲属楼宇");
            jzzkMap.put("4","4-集体宿舍");
            jzzkMap.put("5","5-租房");
            jzzkMap.put("6","6-共有住宅");
            jzzkMap.put("7","7-其他");
            jzzkMap.put("8","8-未知");
        }
        return StringUtils.isNoneBlank(jzzkMap.get(jzzk))?jzzkMap.get(jzzk): StringUtils.EMPTY;
    }

    public static String getZgxl(String zgxl){
        if (zgxlMap == null){
            zgxlMap = new HashMap<>();
            zgxlMap.put("10","10-研究生");
            zgxlMap.put("20","20-大学本科");
            zgxlMap.put("30","30-大学专科和专科学校");
            zgxlMap.put("40","40-中等专业或技术学校");
            zgxlMap.put("50","50-技术学校");
            zgxlMap.put("60","60-高中");
            zgxlMap.put("70","70-初中");
            zgxlMap.put("80","80-小学");
            zgxlMap.put("90","90-文盲或半文盲");
            zgxlMap.put("99","99-未知");
        }
        return StringUtils.isNoneBlank(zgxlMap.get(zgxl))?zgxlMap.get(zgxl): StringUtils.EMPTY;
    }

    public static String getZw(String zw){
        if (zwMap == null){
            zwMap = new HashMap<>();
            zwMap.put("1","1-高级领导");
            zwMap.put("2","2-中级领导");
            zwMap.put("3","3-一般员工");
            zwMap.put("4","4-其他");
            zwMap.put("9","9-未知");
        }
        return StringUtils.isNoneBlank(zwMap.get(zw))?zwMap.get(zw): StringUtils.EMPTY;
    }

    public static String getCszy(String cszy){
        if (cszy.startsWith("0"))
            return "0-国家机关、党群组织、企业、事业单位负责人、公务员、本行员工";
        if (cszy.startsWith("1"))
            return "1-专业技术人员";
        if (cszy.startsWith("3"))
            return "3-办事人员和有关人员";
        if (cszy.startsWith("4"))
            return "4-商业、服务业人员";
        if (cszy.startsWith("5"))
            return "5-农、林、牧、渔、水利业生产人员";
        if (cszy.startsWith("6"))
            return "6-生产、运输设备操作人员及有关人员";
        if (cszy.startsWith("x"))
            return "x-军人";
        if (cszy.startsWith("y"))
            return "y-不便分类的其他从业人员";
        return "z-未知";
    }


    //有无子女
    public static String ywzn(String ywzn){
        if ("1".equals(ywzn))
            return "1-有";
        return "2-无";
    }


    public static String jkzk(String jkzk){
        if ("1".equals(jkzk))
            return "4-差";
        if ("2".equals(jkzk))
            return "3-一般";
        if ("3".equals(jkzk))
            return "2-较好";
        if ("4".equals(jkzk))
            return "1-好";
        return "1-好";
    }

    public static String ldnl(String ldnl){
        if ("1".equals(ldnl))
            return "3-差";
        if ("2".equals(ldnl))
            return "2-一般";
        return "1-好";
    }

    public static String jznx(String jznx){
        if ("1".equals(jznx))
            return "0-1年以下（含1年）";
        if ("2".equals(jznx))
            return "2-1-3年（含3年）";
        if ("3".equals(jznx))
            return "3-3-10年（含10年）";
        return "4-10年以上";
    }

    public static String jzzt(String jznx){
        if ("1".equals(jznx))
            return "1-自置";
        if ("2".equals(jznx))
            return "2-按揭";
        if ("3".equals(jznx))
            return "3-亲属楼宇";
        if ("4".equals(jznx))
            return "4-集体宿舍";
        if ("5".equals(jznx))
            return "5-租房";
        if ("6".equals(jznx))
            return "6-共用住宅";
        if ("7".equals(jznx))
            return "7-其他";
        if ("8".equals(jznx))
            return "8-未知";
        return "8-未知";
    }

    public static String zgxl(String xl){
        if ("1".equals(xl) || "2".equals(xl) || "3".equals(xl))
            return "10-研究生";
        if ("4".equals(xl))
            return "20-大学本科";
        if ("5".equals(xl))
            return "30-大学专科和专科学校";
        if ("6".equals(xl))
            return "60-高中";
        if ("7".equals(xl))
            return "80-小学";
        return "99-未知";
    }

    public static String sfbz(String sfbz){
        if ("1".equals(sfbz))
            return "1-是";
        return "2-否";
    }

    //行业分类前缀
    public static String hyflqz(String hy){
        if (hy.length() >  2){
            String substring = hy.substring(0, 2);
            Integer integer = Integer.valueOf(substring);
            if (integer <= 5 )
                return "A"+hy+"-";
            if (integer > 5 && integer <= 12 )
                return "B"+hy+"-";
            if (integer > 12 && integer <= 43 )
                return "C"+hy+"-";
            if (integer > 43 && integer <= 46 )
                return "D"+hy+"-";
            if (integer > 46 && integer <= 50 )
                return "E"+hy+"-";
            if (integer > 50 && integer <= 52 )
                return "F"+hy+"-";
            if (integer > 52 && integer <= 60 )
                return "G"+hy+"-";
            if (integer > 60 && integer <= 62 )
                return "H"+hy+"-";
            if (integer > 62 && integer <= 65 )
                return "I"+hy+"-";
            if (integer > 65 && integer <= 69 )
                return "J"+hy+"-";
            if (integer == 70 )
                return "k"+hy+"-";
            if (integer > 70 && integer <= 72 )
                return "L"+hy+"-";
            if (integer > 72 && integer <= 75 )
                return "M"+hy+"-";
            if (integer > 75 && integer <= 78 )
                return "N"+hy+"-";
            if (integer > 78 && integer <= 81 )
                return "O"+hy+"-";
            if (integer > 81 && integer <= 83 )
                return "P"+hy+"-";
            if (integer == 84 )
                return "Q"+hy+"-";
            if (integer > 85 && integer <= 89 )
                return "R"+hy+"-";
            if (integer > 89 && integer <= 95 )
                return "S"+hy+"-";
            if (integer == 96 )
                return "T"+hy+"-";
        }
        return "Z-其他";
    }

    public static String scxu(String scxu){
        if ("1".equals(scxu))
            return "1-需求旺盛";
        if ("2".equals(scxu))
            return "1-需求一般";
        if ("3".equals(scxu))
            return "1-需求萎缩";
        return "1-需求旺盛";
    }

    public static String hkyy(String hkyy){
        if ("1".equals(hkyy))
            return "1-强烈";
        if ("2".equals(hkyy))
            return "1-一般";
        if ("3".equals(hkyy))
            return "1-较差";
        return "1-强烈";
    }

    public static String cszy(String cszy){
        if (cszy.startsWith("1"))
            return "0-国家机关、党群组织、企业、事业单位负责人、公务员、本行员工";
        if (cszy.startsWith("2"))
            return "1-专业技术人员";
        if (cszy.startsWith("3"))
            return "3-办事人员和有关人员";
        if (cszy.startsWith("4"))
            return "4-商业、服务业人员";
        if (cszy.startsWith("5"))
            return "5-农、林、牧、渔、水利业生产人员";
        if (cszy.startsWith("6"))
            return "6-生产、运输设备操作人员及有关人员";
        if (cszy.startsWith("7"))
            return "x-军人";
        if (cszy.startsWith("8"))
            return "y-不便分类的其他从业人员";
        return "z-未知";
    }

    public static String zc(String zc){
        if ("1".equals(zc))
            return "1-高级";
        if ("2".equals(zc))
            return "2-中级";
        if ("3".equals(zc))
            return "3-初级";
        if ("4".equals(zc))
            return "9-未知";
        return "0-无";
    }

    public static String cynx(String cynx){
        if ("1".equals(cynx))
            return "4-10年以上";
        if ("2".equals(cynx))
            return "3-5-10年";
        if ("3".equals(cynx))
            return "2-3-5年";
        if ("4".equals(cynx))
            return "1-1-3年";
        return "0-1年以下";
    }
    public static String pjbz(String pjbz){
        if ("1".equals(pjbz))
            return "1-差";
        if ("2".equals(pjbz))
            return "2-一般";
        if ("3".equals(pjbz))
            return "3-较好";
        if ("4".equals(pjbz))
            return "4-好";
        return "4-好";
    }

    public static String xydj(String xydj){
        if ("1".equals(xydj))
            return "1-1级";
        if ("2".equals(xydj))
            return "2-2级";
        if ("3".equals(xydj))
            return "3-3级";
        if ("4".equals(xydj))
            return "4-4级";
        return "1-1级";
    }

    public static String jtsr(String jtsr){
        if ("0".equals(jtsr))
            return "0";
        if ("1".equals(jtsr)){
            return "50000";
        }
        if ("2".equals(jtsr))
            return "100000";
        if ("3".equals(jtsr))
            return "150000";
        return "0";
    }

    public static String jyzk(String jyzk){
        if ("11".equals(jyzk)){
            return "11-国家公务员";
        }
        if ("13".equals(jyzk)){
            return "13-专业技术人员";
        }
        if ("17".equals(jyzk)){
            return "17-职员";
        }
        if ("21".equals(jyzk)){
            return "21-企业管理人员";
        }
        if ("24".equals(jyzk)){
            return "24-工人";
        }
        if ("27".equals(jyzk)){
            return "27-农民";
        }
        if ("31".equals(jyzk)){
            return "31-学生";
        }
        if ("37".equals(jyzk)){
            return "37-现役军人";
        }
        if ("51".equals(jyzk)){
            return "51-自由职业者";
        }
        if ("54".equals(jyzk)){
            return "54-个体经营者";
        }
        if ("70".equals(jyzk)){
            return "70-无业人员";
        }
        if ("80".equals(jyzk)){
            return "80-退(离)休人员";
        }
        if ("90".equals(jyzk)){
            return "90-其他";
        }
        if ("91".equals(jyzk)){
            return "91-在职";
        }
        return "99-未知";
    }

    public static String dwxz(String dwxz){
        if ("10".equals(dwxz)){
            return "10-机关、事业单位";
        }
        if ("20".equals(dwxz)){
            return "20-国有企业";
        }
        if ("30".equals(dwxz)){
            return "30-外资企业";
        }
        if ("40".equals(dwxz)){
            return "40-个体、私营企业";
        }
        if ("50".equals(dwxz)){
            return "50-其他（包括三资企业、民营企业、民间团体等）";
        }
        return "99-未知";
    }

    public static String zgxw(String zgxw){
        if ("1".equals(zgxw)){
            return "1-名誉博士";
        }
        if ("1".equals(zgxw)){
            return "2-博士";
        }
        if ("1".equals(zgxw)){
            return "3-硕士";
        }
        if ("1".equals(zgxw)){
            return "4-学士";
        }
        return "0-其他";
    }


    /**
     * 下面都是省联社下发表转换成惠农快贷字典
     * */
    public static String jkzhZh(String dict){
        if (StringUtils.isBlank(dict))
            return "1";
        if (dict.equals("00"))
            return "1";
        if (dict.equals("01"))
            return "2";
        if (dict.equals("02"))
            return "3";
        return "4";
    }

    public static String zgxlZh(String dict){
        if (StringUtils.isBlank(dict))
            return "70";
        if ("01".equals(dict) || "02".equals(dict) || "03".equals(dict))
            return "10";
        if ("04".equals(dict))
            return "20";
        if ("05".equals(dict))
            return "30";
        if ("06".equals(dict))
            return "40";
        if ("07".equals(dict))
            return "50";
        if ("08".equals(dict))
            return "60";
        if ("09".equals(dict))
            return "70";
        if ("10".equals(dict))
            return "80";
        if ("11".equals(dict))
            return "90";
        if ("99".equals(dict))
            return "99";
        return "70";
    }

    public static String zcZh(String dict){
        if (StringUtils.isBlank(dict))
            return "3";
        if ("00".equals(dict))
            return "0";
        if ("10".equals(dict) || "11".equals(dict) || "12".equals(dict))
            return "1";
        if ("20".equals(dict) || "21".equals(dict))
            return "2";
        if ("30".equals(dict) || "31".equals(dict) || "32".equals(dict))
            return "3";
        if ("99".equals(dict))
            return "9";
        return "3";
    }
}
