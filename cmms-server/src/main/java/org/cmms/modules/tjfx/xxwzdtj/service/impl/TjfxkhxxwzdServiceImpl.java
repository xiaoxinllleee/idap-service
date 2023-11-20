package org.cmms.modules.tjfx.xxwzdtj.service.impl;

import org.cmms.modules.tjfx.xxwzdtj.entity.Tjfxkhxxwzd;
import org.cmms.modules.tjfx.xxwzdtj.mapper.TjfxkhxxwzdMapper;
import org.cmms.modules.tjfx.xxwzdtj.service.ITjfxkhxxwzdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 客户信息完整度统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Service
public class TjfxkhxxwzdServiceImpl extends ServiceImpl<TjfxkhxxwzdMapper, Tjfxkhxxwzd> implements ITjfxkhxxwzdService {
    @Autowired
    private TjfxkhxxwzdMapper tjfxkhxxwzdMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        tjfxkhxxwzdMapper.extract(tjyf);
    }
}
