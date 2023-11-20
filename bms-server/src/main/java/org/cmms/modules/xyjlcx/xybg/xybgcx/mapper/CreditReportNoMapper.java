package org.cmms.modules.xyjlcx.xybg.xybgcx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xyjlcx.xybg.xybgcx.entity.CreditReportNo;

public interface CreditReportNoMapper extends BaseMapper<CreditReportNo> {

    /**
     * 获取当日最大序号 Oracle
     * @param areaCode 区域代码
     * @param today    当天日期 yyyyMMdd
     * @return
     */
    String getSerialNumByAreaCodeAndCurrentDate(String areaCode, String today);

    /**
     * 获取当日最大序号 Hive
     * @param qydm 区域代码
     * @param today    当天日期 yyyyMMdd
     * @return
     */
    String getXhByQydmAndDate(String qydm, String today);
}
