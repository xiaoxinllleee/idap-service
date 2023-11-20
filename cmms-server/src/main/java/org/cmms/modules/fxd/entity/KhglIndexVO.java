package org.cmms.modules.fxd.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2023/5/3
 * @Created by eran
 */
@Data
public class KhglIndexVO {
    private BigDecimal rs = new BigDecimal("0");
    private BigDecimal hs = new BigDecimal("0");
    private BigDecimal ck = new BigDecimal("0");
    //存款单位
    private String ckdw = "";
    //贷款单位
    private String dkdw = "";

    private BigDecimal dk = new BigDecimal("0");

    public void jsCDK(){
        //判断是否大于10000
        if (this.ck.compareTo(new BigDecimal("0")) > 0 && this.ck.compareTo(new BigDecimal("10000")) <= 0){
            this.ckdw = "元";
        }

        if (this.ck.compareTo(new BigDecimal("10000")) > 0 && this.ck.compareTo(new BigDecimal("100000000")) <= 0){
            this.ck = this.ck.divide(new BigDecimal("10000"),BigDecimal.ROUND_UP);
            this.ckdw = "万";
        }

        if (this.ck.compareTo(new BigDecimal("100000000")) > 0){
            this.ck = this.ck.divide(new BigDecimal("100000000"),BigDecimal.ROUND_UP);
            this.ckdw = "亿";
        }


        if (this.dk.compareTo(new BigDecimal("0")) > 0 && this.dk.compareTo(new BigDecimal("10000")) <= 0){
            this.dkdw = "元";
        }

        if (this.dk.compareTo(new BigDecimal("10000")) > 0 && this.dk.compareTo(new BigDecimal("100000000")) <= 0){
            this.dk = this.dk.divide(new BigDecimal("10000"),BigDecimal.ROUND_UP);
            this.dkdw = "万";
        }

        if (this.dk.compareTo(new BigDecimal("100000000")) > 0){
            this.dk = this.dk.divide(new BigDecimal("100000000"),BigDecimal.ROUND_UP);
            this.dkdw = "亿";
        }




    }
}
