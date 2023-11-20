package org.cmms.modules.sjxf.qtxt.khxxst.khxxb.service.impl;

import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity.Khxxb;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.mapper.KhxxbMapper;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.service.IKhxxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Service
public class KhxxbServiceImpl extends ServiceImpl<KhxxbMapper, Khxxb> implements IKhxxbService {

    @Override
    public Khxxb getKhxxbHive(String zjhm) {
        return baseMapper.getKhxxbHive(zjhm);
    }

    @Override
    public Khxxb getKhxxbOracle(String zjhm) {
        return baseMapper.getKhxxbOracle(zjhm);
    }
}
