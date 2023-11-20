package org.cmms.modules.dkjkpt.tjcx.zhajdktj.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity.ZhajdktjNb;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.mapper.ZhajdktjNbMapper;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.service.IZhajdktjNbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 支行按揭贷款统计_年报
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@Service
@DS("dkjkpt")
public class ZhajdktjNbServiceImpl extends ServiceImpl<ZhajdktjNbMapper, ZhajdktjNb> implements IZhajdktjNbService {

    @Autowired
    private ZhajdktjNbMapper zhajdktjNbMapper;

    @Override
    @Transactional
    public void extract(Map<String, String> sql) {
        zhajdktjNbMapper.extract(sql);
    }
}
