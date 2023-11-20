package org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.service.impl;

import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity.KhjlZfsjtjDr;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity.KhjlZfsjtjDrVo;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.mapper.KhjlZfsjtjDrMapper;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.service.IKhjlZfsjtjDrService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理走访统计-当日
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
@Service
public class KhjlZfsjtjDrServiceImpl extends ServiceImpl<KhjlZfsjtjDrMapper, KhjlZfsjtjDr> implements IKhjlZfsjtjDrService {

    @Override
    public void initKhjlZf(String yggh) {
        baseMapper.initKhjlZf(yggh);
    }

    @Override
    public Integer getWxzfxx(String yggh) {
        return baseMapper.getWxzfxx(yggh);
    }

    @Override
    public KhjlZfsjtjDrVo getBthzfxx(String sszh) {
        return baseMapper.getBthzfxx(sszh);
    }

    @Override
    public KhjlZfsjtjDrVo getYxzfHj(String yggh) {
        return baseMapper.getYxzfHj(yggh);
    }
}
