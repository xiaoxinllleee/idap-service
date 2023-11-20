package org.cmms.modules.khgl.ckkh.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2022/3/4
 * @Created by eran
 */
@Data
public class CkkhGyResultVO {

    private BigDecimal qmye = new BigDecimal(0);
    private BigDecimal qmrj = new BigDecimal(0);
    private BigDecimal hqye = new BigDecimal(0);
    private BigDecimal dqye = new BigDecimal(0);
    private BigDecimal hqrj = new BigDecimal(0);
    private BigDecimal dqrj = new BigDecimal(0);

    private BigDecimal qmyejnc = new BigDecimal(0);
    private BigDecimal qmrjjnc = new BigDecimal(0);
    private BigDecimal hqyejnc = new BigDecimal(0);
    private BigDecimal dqyejnc = new BigDecimal(0);
    private BigDecimal hqrjjnc = new BigDecimal(0);
    private BigDecimal dqrjjnc = new BigDecimal(0);


    private BigDecimal qmyejyc = new BigDecimal(0);
    private BigDecimal qmrjjyc = new BigDecimal(0);
    private BigDecimal hqyejyc = new BigDecimal(0);
    private BigDecimal dqyejyc = new BigDecimal(0);
    private BigDecimal hqrjjyc = new BigDecimal(0);
    private BigDecimal dqrjjyc = new BigDecimal(0);


    public void jsResult(CkkhGyVO qm, CkkhGyVO yc, CkkhGyVO nc) {
        if (qm != null) {
            qmye = qm.getQmye();
            qmrj = qm.getQmrj();
            hqye = qm.getHqye();
            dqye = qm.getDqye();
            hqrj= qm.getHqrj();
            dqrj = qm.getDqrj();
        }

        if (nc != null) {
            qmyejnc = qmye.subtract(nc.getQmye());
            qmrjjnc = qmrj.subtract(nc.getQmrj());
            hqyejnc = hqye.subtract(nc.getHqye());
            dqyejnc = dqye.subtract(nc.getDqye());
            hqrjjnc = hqrj.subtract(nc.getHqrj());
            dqrjjnc = dqrj.subtract(nc.getDqrj());
        }else {
            qmyejnc = qmye;
            qmrjjnc = qmrj;
            hqyejnc = hqye;
            dqyejnc = dqye;
            hqrjjnc = hqrj;
            dqrjjnc = dqrj;
        }

        if (yc != null) {
            qmyejyc = qmye.subtract(yc.getQmye());
            qmrjjyc = qmrj.subtract(yc.getQmrj());
            hqyejyc = hqye.subtract(yc.getHqye());
            dqyejyc = dqye.subtract(yc.getDqye());
            hqrjjyc = hqrj.subtract(yc.getHqrj());
            dqrjjyc = dqrj.subtract(yc.getDqrj());
        }else {
            qmyejyc = qmye;
            qmrjjyc = qmrj;
            hqyejyc = hqye;
            dqyejyc = dqye;
            hqrjjyc = hqrj;
            dqrjjyc = dqrj;
        }
    }

}
