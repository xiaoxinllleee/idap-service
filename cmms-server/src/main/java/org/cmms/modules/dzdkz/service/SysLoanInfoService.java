package org.cmms.modules.dzdkz.service;

import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;

import java.math.BigDecimal;
import java.util.List;

/**
 *  电子贷款证服务类
 */
public interface SysLoanInfoService {
    /**
     * 电子贷款证更新服务
     * @param list
     * */
    void updateDzdkzData(List<Grpjsxspjl> list);

    void comDzdkzUpdate(String khmc, String zjhm, String fpdj, BigDecimal je, String type,
                        String khjl, String khjlsjhm,String zzjc);
}
