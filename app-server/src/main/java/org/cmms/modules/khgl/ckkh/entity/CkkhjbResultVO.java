package org.cmms.modules.khgl.ckkh.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2022/3/4
 * @Created by eran
 */
@Data
public class CkkhjbResultVO {

    private BigDecimal qmghs = new BigDecimal(0);
    private BigDecimal qmckye = new BigDecimal(0);
    private BigDecimal qmckrj = new BigDecimal(0);
    private BigDecimal hqckrj = new BigDecimal(0);
    private BigDecimal dqckrj = new BigDecimal(0);

    private BigDecimal qmghsjnc = new BigDecimal(0);
    private BigDecimal qmckyejnc = new BigDecimal(0);
    private BigDecimal qmckrjjnc = new BigDecimal(0);
    private BigDecimal hqckrjjnc = new BigDecimal(0);
    private BigDecimal dqckrjjnc = new BigDecimal(0);

    private BigDecimal qmghsjyc = new BigDecimal(0);
    private BigDecimal qmckyejyc = new BigDecimal(0);
    private BigDecimal qmckrjjyc = new BigDecimal(0);
    private BigDecimal hqckrjjyc = new BigDecimal(0);
    private BigDecimal dqckrjjyc = new BigDecimal(0);


    public void jsResult(CkkhjbVO qm, CkkhjbVO yc, CkkhjbVO nc) {
        if (qm != null) {
            qmghs = qm.getQmghs();
            qmckye = qm.getQmckye();
            qmckrj = qm.getQmckrj();
            hqckrj = qm.getHqckrj();
            dqckrj = qm.getDqckrj();
        }

        if (nc != null) {
            qmghsjnc = qmghs.subtract(nc.getQmghs());
            qmckyejnc = qmckye.subtract(nc.getQmckye());
            qmckrjjnc = qmckrj.subtract(nc.getQmckrj());
            hqckrjjnc = hqckrj.subtract(nc.getHqckrj());
            dqckrjjnc = dqckrj.subtract(nc.getDqckrj());
        }

        if (yc != null) {
            qmghsjyc = qmghs.subtract(yc.getQmghs());
            qmckyejyc = qmckye.subtract(yc.getQmckye());
            qmckrjjyc = qmckrj.subtract(yc.getQmckrj());
            hqckrjjyc = hqckrj.subtract(yc.getHqckrj());
            dqckrjjyc = dqckrj.subtract(yc.getDqckrj());
        }
    }

}
