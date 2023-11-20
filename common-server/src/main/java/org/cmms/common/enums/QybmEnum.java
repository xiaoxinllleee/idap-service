package org.cmms.common.enums;

public enum QybmEnum {
    LIUYANG("020", "浏阳"),
    TIANYI("095", "天易"),
    XIANGXIANG("100", "湘乡"),
    QIDONG("130", "祁东"),
    LEIYANG("150", "耒阳"),
    NANYUE("155", "南岳"),
    SHUANGFENG("310", "双峰"),
    XINHUA("320", "新化"),
    YONGXING("350", "永兴"),
    LINWU("365", "临武"),
    RUCHENG("375", "汝城"),
    QIYANG("390", "祁阳"),
    DONGAN("395", "东安"),
    NINGYUAN("405", "宁远"),
    JIANGHUA("415", "江华"),
    XINTIAN("425", "新田"),
    LANSHAN("420", "蓝山"),
    DAOXIAN("400", "道县");

    private String qybm;

    private String qymc;

    private QybmEnum(String qybm, String qymc) {
        this.qybm = qybm;
        this.qymc = qymc;
    }

    public String getQybm() {
        return qybm;
    }

    public void setQybm(String qybm) {
        this.qybm = qybm;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }
}
