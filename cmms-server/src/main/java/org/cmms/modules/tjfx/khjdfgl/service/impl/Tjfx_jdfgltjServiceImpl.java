package org.cmms.modules.tjfx.khjdfgl.service.impl;

import org.cmms.modules.tjfx.khjdfgl.entity.Tjfx_jdfgltj;
import org.cmms.modules.tjfx.khjdfgl.mapper.Tjfx_jdfgltjMapper;
import org.cmms.modules.tjfx.khjdfgl.service.ITjfx_jdfgltjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.transaction.Transactional;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-09
 * @Version: V1.0
 */
@Service
public class Tjfx_jdfgltjServiceImpl extends ServiceImpl<Tjfx_jdfgltjMapper, Tjfx_jdfgltj> implements ITjfx_jdfgltjService {
    @Autowired
    private Tjfx_jdfgltjMapper tjfxkhnlfctjMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        tjfxkhnlfctjMapper.extract(tjyf);
    }
}
