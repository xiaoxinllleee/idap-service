package org.cmms.modules.dkjkpt.tjcx.zhajdktj.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity.ZhajdktjYb;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.mapper.ZhajdktjYbMapper;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.service.IZhajdktjYbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 支行按揭贷款统计_月报
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@Service
@DS("dkjkpt")
public class ZhajdktjYbServiceImpl extends ServiceImpl<ZhajdktjYbMapper, ZhajdktjYb> implements IZhajdktjYbService {

    @Autowired
    private ZhajdktjYbMapper zhajdktjYbMapper;

    @Override
    @Transactional
    public void extract(Map<String, String> sql) {
        zhajdktjYbMapper.extract(sql);
    }
}
