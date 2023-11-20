package org.cmms.modules.khgl.dkkh.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date 2022/3/12
 * @Created by eran
 */
@Data
public class DkhtVO {
    private String khbh;
    private String khmc;
    private String fiveClassType;
    private String fiveClassTypeVal;

    private BigDecimal dkje = new BigDecimal(0);
    private BigDecimal dkye = new BigDecimal(0);
    //发放日期
    private Date ffrq;
    //到期日期
    private Date dqrq;
    //贷款类型 01个人 02是对公
    private String dklx;
    private String dklxVal;
    private String zjhm;
    private String hth;
    //贷款种类  便民卡 类似
    private String dkzl;
    private String dkzlVal;
    private BigDecimal ll;
    //担保方式 2保证 2质押 3抵押 4信用 5其他
    private String dbfs;
    private String dbfsVal;
    private String ghr;
    private String bsr;
    private String sjhm;
    private String zz;

    private Date qyrq;


    public void initGrxx(KhgxglKhzlglGrkh khgxglKhzlglGrkh){
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getKhbh()))
            this.khbh = khgxglKhzlglGrkh.getKhbh();
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getSjhm()))
            this.sjhm = khgxglKhzlglGrkh.getSjhm();
        if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getZz()))
            this.zz = khgxglKhzlglGrkh.getZz();
    }

    public void initDkzhsjmx(TbDkYgdkzhsjmx tbDkYgdkzhsjmx){
        if (StringUtils.isNotBlank(tbDkYgdkzhsjmx.getFiveClassType())){
            this.fiveClassType = tbDkYgdkzhsjmx.getFiveClassType();
        }
        if (StringUtils.isNotBlank(tbDkYgdkzhsjmx.getHth()))
            this.hth = tbDkYgdkzhsjmx.getHth();
        if (StringUtils.isNotBlank(tbDkYgdkzhsjmx.getZjhm()))
            this.zjhm = tbDkYgdkzhsjmx.getZjhm();
        if (tbDkYgdkzhsjmx.getFfrq() != null)
            this.ffrq = tbDkYgdkzhsjmx.getFfrq();
        if (tbDkYgdkzhsjmx.getDqrq() != null)
            this.dqrq = tbDkYgdkzhsjmx.getDqrq();
        if (tbDkYgdkzhsjmx.getQyrq() != null)
            this.qyrq = tbDkYgdkzhsjmx.getQyrq();
        if (tbDkYgdkzhsjmx.getDkje() != null)
            this.dkje = tbDkYgdkzhsjmx.getDkje();
        if (tbDkYgdkzhsjmx.getDkye() != null)
            this.dkye = tbDkYgdkzhsjmx.getDkye();
        if (tbDkYgdkzhsjmx.getLl() != null)
            this.ll = tbDkYgdkzhsjmx.getLl();
        if (StringUtils.isNotBlank(tbDkYgdkzhsjmx.getCustType()))
            this.dklx = tbDkYgdkzhsjmx.getCustType();
        if (StringUtils.isNotBlank(tbDkYgdkzhsjmx.getCustName()))
            this.khmc = tbDkYgdkzhsjmx.getCustName();
    }
}
