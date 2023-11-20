package org.cmms.modules.ygjx.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2022/6/28
 * @Created by eran
 */
@Data
public class JgDataVO {
    private String zzbz;
    private String zzbzVal;
    //预估绩效 支行总绩效
    private String ygjx;
    //支行人数
    private String zhrs;
    //支行人均
    private String zhrj;
    //人均排名
    private String rjpm;
    //全行人均
    private String qhrj;
    //全行人数
    private String qhrs;
    private String qhjx;
    //占比
    private String zb;

    public void jszb(String rj){
        try {
            BigDecimal bigDecimal = new BigDecimal(rj);
            BigDecimal bigDecimal1 = new BigDecimal(this.qhrs);
            BigDecimal bigDecimal2 = new BigDecimal(this.qhjx);
            if (bigDecimal.compareTo(new BigDecimal(0)) > 0 && bigDecimal1.compareTo(new BigDecimal(0)) > 0){
                BigDecimal divide = bigDecimal2.divide(bigDecimal.add(bigDecimal1), BigDecimal.ROUND_UP);
                this.zb = divide.toString() + "%";
            }
        }catch (Exception e){
            e.printStackTrace();
            this.zb = "100%";
        }
    }



}
