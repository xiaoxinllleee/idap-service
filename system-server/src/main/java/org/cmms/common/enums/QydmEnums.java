package org.cmms.common.enums;

import lombok.Data;

/**
 * @Date 2022/8/10
 * @Created by eran
 */
public enum QydmEnums {
    //浏阳
    LIUYANG("020","湖南浏阳农村商业银行股份有限公司","浏阳"),
    //江华
    JIANGHUA("415","湖南江华农村商业银行股份有限公司营业部","江华"),
    //慈利
    CILI("545","湖南慈利农村商业银行股份有限公司营业部","慈利"),
    //新田
    XINTIAN("425","湖南新田农村商业银行股份有限公司营业部","新田"),
    //蓝山
    LANSHAN("420","蓝山县农村信湖南蓝山农村商业银行股份有限公司营业部","蓝山"),
    //祁阳
    QIYANG("390","湖南祁阳农村商业银行股份有限公司营业部","祁阳"),
    //天易
    TIANYI("095","湖南湘潭天易农村商业银行股份有限公司","天易"),
    //双峰
    SHUANGFENG("310","湖南双峰农村商业银行股份有限公司营业部","双峰"),
    //湘乡
    XIANGXIANG("100","湖南湘乡农村商业银行股份有限公司营业部","湘乡"),
    LINWU("365","湖南临武农村商业银行股份有限公司营业部","临武"),
    ZHANGJIAJIE("535","张家界农村商业银行股份有限公司","张家界"),
    //祁东
    QIDONG("130","祁东县农村信用合作联社","祁东"),
    //湖南涟源农村商业银行股份有限公司营业部
    LIANYUAN("315","湖南涟源农村商业银行股份有限公司营业部","涟源"),
    //永兴
    YONGXING("350","湖南永兴农村商业银行股份有限公司营业部","永兴");
    private String qydmCode;
    private String qydmText;
    private String qydmTextShort;

    QydmEnums(String qydmCode, String qydmText, String qydmTextShort) {
        this.qydmCode = qydmCode;
        this.qydmText = qydmText;
        this.qydmTextShort = qydmTextShort;
    }
    public String getQydmCode() {
        return qydmCode;
    }

    public String getQydmText() {
        return qydmText;
    }

    public String getQydmTextShort() {
        return qydmTextShort;
    }
}
