package org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.service.impl;

import org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.entity.Bwdkshmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.mapper.BwdkshmxMapper;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.service.IBwdkshmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 表外贷款收回明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
@Service
public class BwdkshmxServiceImpl extends ServiceImpl<BwdkshmxMapper, Bwdkshmx> implements IBwdkshmxService {

    @Override
    public void pBwdkshmx(String rzsjB, String rzsjE) {
        baseMapper.pBwdkshmx(rzsjB,rzsjE);
    }

    @Override
    public List<Bwdkshmx> queryBwdkshmxOracle(String rzwd, String shrq) {
        return baseMapper.queryBwdkshmxOracle(rzwd,shrq);
    }

    @Override
    public List<Bwdkshmx> queryBwdkshmxImpala(String rzwd, String shrq) {
        return baseMapper.queryBwdkshmxImpala(rzwd,shrq);
    }
}
