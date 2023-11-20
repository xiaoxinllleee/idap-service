package org.cmms.modules.dictcache;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.math.BigDecimal;

public interface IDictValueQuery {
    void initMaps();
    String getCszyValue(String zy);
    String getHyzkValue(String hyzk);
    String getYearMonthValue(String zjhm);
    String getYzhgxValue(String yhzgx);
    String getPddjValue(String xtpddj);
    String getyjValue(String sjyxdy);
    String getejValue(String sjyxdy);
    String getsjValue(String sjyxdy);
    String getGrdkZllxValue(String sjyxdy);
    String getZcValue(String zc);
    String getRoleValueByWorkNo(String workNO);
    String getNameByUsername(String username);
    String getSszhByUsername(String username);
    String getNameBySszh(String sszh);

    BigDecimal getZbfzByZbgid(String zbgid);
    String getSeqRateZxlldjbDjidNextval(String seq);

    String getSeqNextval(String seq);
    //判断是否是浏阳农商行
    boolean isLy();

    String sjzzbz(String zzbz);
}
