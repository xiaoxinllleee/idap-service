package org.cmms.modules.tjfx.khhxxzc.service.impl;

import org.cmms.modules.tjfx.khhxxzc.entity.KhhxXzc;
import org.cmms.modules.tjfx.khhxxzc.mapper.KhhxXzcMapper;
import org.cmms.modules.tjfx.khhxxzc.service.IKhhxXzcService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户画像_行政村
 * @Author: jeecg-boot
 * @Date:   2022-07-06
 * @Version: V1.0
 */
@Service
public class KhhxXzcServiceImpl extends ServiceImpl<KhhxXzcMapper, KhhxXzc> implements IKhhxXzcService {
    @Override
    public void init(){
        baseMapper.init();
    }
}
