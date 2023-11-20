package org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.impl;

import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.mapper.BwdksjmxMapper;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.IBwdksjmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 表外贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
@Service
public class BwdksjmxServiceImpl extends ServiceImpl<BwdksjmxMapper, Bwdksjmx> implements IBwdksjmxService {

    @Override
    public void pBwdksjmx() {
        baseMapper.pBwdksjmx();
    }

    /*@Override
    public List<Bwdksjmx> queryContainsBwtmdk(String name, String csnf, String age) {
        return baseMapper.queryContainsBwtmdk(name, csnf, age);
    }*/

    @Override
    public List<Bwdksjmx> queryBwtmdkHive(String name, String csnf, String age) {
        return baseMapper.queryBwtmdkHive(name, csnf, age);
    }

    @Override
    public List<Bwdksjmx> queryBwtmdkOracle(String name, String csnf, String age) {
        return baseMapper.queryBwtmdkOracle(name,csnf,age);
    }
}
