package org.cmms.modules.xyjlcx.xybg.xybgcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity.Grkhb;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity.Khxxb;
import org.cmms.modules.xyjlcx.xybg.cxjltz.entity.Cxjltz;
import org.cmms.modules.xyjlcx.xybg.qxmx.entity.Qxmx;
import org.cmms.modules.xyjlcx.xybg.xybgcx.mapper.CreditReportQueryMapper;
import org.cmms.modules.xyjlcx.xybg.xybgcx.service.ICreditReportQueryService;
import org.cmms.modules.xyjlcx.xybg.xybgcx.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CreditReportQueryImpl extends ServiceImpl<CreditReportQueryMapper, Cxjltz> implements ICreditReportQueryService {
    @Autowired
    private CreditReportQueryMapper mapper;

    @Override
    public List<TmpCreditDksjVO> getDksjmxHive(String zjhm) {
        return mapper.getDksjmxHive(zjhm);
    }

    @Override
    public List<TmpCreditDksjVO> getDksjmxOracle(String zjhm) {
        return mapper.getDksjmxOracle(zjhm);
    }

    @Override
    public List<TmpCreditDksjVO> getDksjmx(String zjhm) {
        return baseMapper.getDksjmx(zjhm);
    }

    @Override
    public void CreditInitExtract(String zjhm) {
        mapper.CreditInitExtract(zjhm);
    }

    @Override
    public void CreditInitExtractIdap(String zjhm) {
        baseMapper.CreditInitExtract(zjhm);
    }

    @Override
    public List<TmpCreditDbxxVO> getdbxxOracle(String zjhm) {
        return baseMapper.getdbxxOracle(zjhm);
    }

    @Override
    public List<TmpCreditXytssjVO> getXytssjOracle(String zjhm) {
        return baseMapper.getXytssjOracle(zjhm);
    }

    @Override
    public void initZx() {
        baseMapper.initZx();
    }

    @Override
    public List<Qxmx> getqxmxOracle(String zjhm) {
        return baseMapper.getqxmxOracle(zjhm);
    }

    @Override
    public List<JbxxSfxx> getJbxxSfxx(String zjhm) {
        return baseMapper.getJbxxSfxx(zjhm);
    }

    @Override
    public List<JbxxGlrxx> getJbxxGlrxx(String zjhm, String hhbm) {
        return baseMapper.getJbxxGlrxx(zjhm, hhbm);
    }
}
