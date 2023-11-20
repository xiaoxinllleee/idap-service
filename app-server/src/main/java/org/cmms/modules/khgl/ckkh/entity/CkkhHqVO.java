package org.cmms.modules.khgl.ckkh.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2022/3/4
 * @Created by eran
 */
@Data
public class CkkhHqVO {

    private String ckzh;
    private String khrq;
    private String yggh;
    private String ygghVal;
    private String yxbl = "100";
    private String yxlxVal;
    private String yxlx;
    private String intFrmDt;
    private String endDate;
    private String cq;




    private BigDecimal qmye = new BigDecimal(0);
    private BigDecimal clye = new BigDecimal(0);
    private BigDecimal nrp3 = new BigDecimal(0);
    private BigDecimal clNrp3 = new BigDecimal(0);

    private BigDecimal qmyejnc = new BigDecimal(0);
    private BigDecimal qmyejyc = new BigDecimal(0);
    private BigDecimal qmrjjnc = new BigDecimal(0);
    private BigDecimal qmrjjyc = new BigDecimal(0);

    public void jsResult(BigDecimal ycckye,BigDecimal ycckrj){
        if (clye != null){
            qmyejnc = qmye.subtract(clye);
        }else {
            qmyejnc = qmye;
        }
        if (clNrp3 != null){
            qmrjjnc = nrp3.subtract(clNrp3);
        }else {
            qmrjjnc = nrp3;
        }

        if (ycckye != null){
            qmyejyc = qmye.subtract(ycckye);
        }else {
            qmyejyc = qmye;
        }

        if (ycckrj != null){
            qmrjjyc = nrp3.subtract(ycckrj);
        }else {
            qmrjjyc = nrp3;
        }
    }

}
