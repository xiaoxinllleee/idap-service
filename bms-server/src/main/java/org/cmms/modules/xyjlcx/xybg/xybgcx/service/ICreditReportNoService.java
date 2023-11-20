package org.cmms.modules.xyjlcx.xybg.xybgcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xyjlcx.xybg.xybgcx.entity.CreditReportNo;

@DS("zx") // zx
public interface ICreditReportNoService extends IService<CreditReportNo> {

    String getSerialNumByAreaCodeAndCurrentDate(String areaCode, String today);

    String getXhByQydmAndDate(String qydm, String today);
}
