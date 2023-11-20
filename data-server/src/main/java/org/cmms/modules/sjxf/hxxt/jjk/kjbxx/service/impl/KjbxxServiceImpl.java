package org.cmms.modules.sjxf.hxxt.jjk.kjbxx.service.impl;

import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.mapper.KjbxxMapper;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.service.IKjbxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 卡基本信息
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Service
public class KjbxxServiceImpl extends ServiceImpl<KjbxxMapper, Kjbxx> implements IKjbxxService {

    @Override
    public List<Kjbxx> getBmkxxHive(String zjhm) {
        return baseMapper.getBmkxxHive(zjhm);
    }

    @Override
    public List<Kjbxx> getBmkxxOracle(String zjhm) {
        return baseMapper.getBmkxxHive(zjhm);
    }
}
