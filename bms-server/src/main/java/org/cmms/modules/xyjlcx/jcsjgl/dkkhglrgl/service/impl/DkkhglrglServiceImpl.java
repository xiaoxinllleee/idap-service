package org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.service.impl;

import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity.Dkkhglrgl;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.mapper.DkkhglrglMapper;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.service.IDkkhglrglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 贷款客户关联人管理
 * @Author: jeecg-boot
 * @Date:   2021-08-10
 * @Version: V1.0
 */
@Service
public class DkkhglrglServiceImpl extends ServiceImpl<DkkhglrglMapper, Dkkhglrgl> implements IDkkhglrglService {
    @Autowired
    private DkkhglrglMapper mapper;

    @Override
    public void pDkkhglrgl() {
        baseMapper.pDkkhglrgl();
    }

    @Override
    public List<Dkkhglrgl> QueryRelatedPartyInfoOne(String zjhm) {
        return mapper.QueryRelatedPartyInfoOne(zjhm);
    }

    @Override
    public List<Dkkhglrgl> QueryRelatedPartyInfoTwo(String zjhm) {
        return mapper.QueryRelatedPartyInfoTwo(zjhm);
    }

    @Override
    public List<Dkkhglrgl> queryRelatedPersonInfo(String zjhm) {
        return mapper.queryRelatedPersonInfo(zjhm);
    }
}
