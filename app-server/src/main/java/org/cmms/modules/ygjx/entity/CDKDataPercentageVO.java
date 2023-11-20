package org.cmms.modules.ygjx.entity;

import lombok.Data;
import org.cmms.common.enums.QydmEnums;

import java.math.BigDecimal;

/**
 * @Date 2022/6/30
 * @Created by eran
 */
@Data
public class CDKDataPercentageVO {
    private BigDecimal dkye = new BigDecimal(0);
    private BigDecimal zjjzczldkye = new BigDecimal(0);
    private BigDecimal dkyecl = new BigDecimal(0);

    private String sjrq;
    private Integer dkyeclzb = 50;

    private Integer dkyejczb = 50;

    private BigDecimal dkyejc;

    private BigDecimal ck = new BigDecimal(0);
    private BigDecimal hqck =  new BigDecimal(0);
    private Integer hqzb;
    //剩余存款
    private Integer syckzb;

    private Integer dkkh = 0;
    //贷款客户男
    private Integer dkkh1 = 0;
    private Integer dkkh1zb = 50;
    //贷款客户女
    private Integer dkkh2 = 0;
    private Integer dkkh2zb = 50;

    private BigDecimal bnbl = new BigDecimal(0);
    private Integer bnblzb = 0;
    private BigDecimal bnblzbZjj = new BigDecimal(0);
    //年初
    private BigDecimal bnblnc = new BigDecimal(0);
    private Integer bnblnczb = 0;
    private BigDecimal bnblnczbZjj = new BigDecimal(0);

    private Integer bnbljnczb = 0;
    private BigDecimal bnbljnczbZjj = new BigDecimal(0);

    //压降
    private BigDecimal bnblyj = new BigDecimal(0);

    public void jszcqk(){
        if (this.dkye.compareTo(new BigDecimal(0)) > 0){
            BigDecimal divide = this.bnbl.divide(this.dkye,2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            this.bnblzb = divide.intValue();

            BigDecimal divide1 = this.bnblnc.divide(this.dkye,2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            this.bnblnczb = divide1.intValue();

            this.bnbljnczb = this.bnblnczb - this.bnblzb;
            this.bnblyj = this.bnbl.subtract(this.bnblnc);
        }
    }
            //张家界占比
    public void jszczlZjj(){
        if (this.dkye.compareTo(new BigDecimal(0)) > 0){
            //表内不良/现在的贷款余额*100 不四舍五入取4位
            BigDecimal divide = this.bnbl.divide(this.zjjzczldkye,4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));
            this.bnblzbZjj = divide;
            //去年末数据/现在的贷款余额*100 不四舍五入取4位
            BigDecimal divide1 = this.bnblnc.divide(this.zjjzczldkye,4, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));
            this.bnblnczbZjj = divide1;

            this.bnbljnczbZjj = this.bnblnczbZjj.subtract(this.bnblzbZjj) ;
            this.bnblyj = this.bnbl.subtract(this.bnblnc);
        }
    }
    public void jsdkkh(){
        if (this.dkkh > 0){
            BigDecimal multiply = new BigDecimal(this.dkkh1).divide(new BigDecimal(this.dkkh), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            this.dkkh1zb = multiply.intValue();
            this.dkkh2 = this.dkkh - this.dkkh1;
            this.dkkh2zb = 100 - this.dkkh1zb;
        }
    }

    public void jsdkjc(){

        this.dkyejc = this.dkye.subtract(this.dkyecl);
        if (this.dkye.compareTo(new BigDecimal(0)) == 0){
            this.dkyejczb = 100;
            this.dkyeclzb = 0;
        }else {
            BigDecimal divide = this.dkyejc.divide(this.dkye, 2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            int i = divide.intValue();
            this.dkyejczb = i;
            this.dkyeclzb = (100 - i);
        }

    }

    public void jdckzb(){
        if (this.ck.compareTo(new BigDecimal(0)) == 0){
            this.hqzb = 100;
            this.syckzb = 0;
        }else {
            System.out.println("1111111");
            System.out.println(this.hqck);
            System.out.println(this.ck);
            BigDecimal divide = this.hqck.divide(this.ck, 2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            System.out.println(dkkh.intValue());
            this.hqzb = divide.intValue();
            this.syckzb = (100 - this.hqzb);
            System.out.println(this.syckzb);
            System.out.println("2222");
        }
    }

}
