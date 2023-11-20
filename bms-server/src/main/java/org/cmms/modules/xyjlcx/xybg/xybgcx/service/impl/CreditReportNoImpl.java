package org.cmms.modules.xyjlcx.xybg.xybgcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.xyjlcx.xybg.xybgcx.entity.CreditReportNo;
import org.cmms.modules.xyjlcx.xybg.xybgcx.mapper.CreditReportNoMapper;
import org.cmms.modules.xyjlcx.xybg.xybgcx.service.ICreditReportNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditReportNoImpl extends ServiceImpl<CreditReportNoMapper, CreditReportNo> implements ICreditReportNoService {
    @Autowired
    private CreditReportNoMapper mapper;

    @Override
    public String getSerialNumByAreaCodeAndCurrentDate(String areaCode, String today) {
        return mapper.getSerialNumByAreaCodeAndCurrentDate(areaCode, today);
    }

    @Override
    public String getXhByQydmAndDate(String qydm, String today) {
        return mapper.getXhByQydmAndDate(qydm,today);
    }
}
