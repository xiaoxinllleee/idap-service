package org.cmms.modules.tjfx.khjdwzd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.tjfx.khjdwzd.entity.Tjfx_jdwzdtj;
import org.cmms.modules.tjfx.khjdwzd.mapper.Tjfx_jdwzdtjMapper;
import org.cmms.modules.tjfx.khjdwzd.service.ITjfx_jdwzdtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-07
 * @Version: V1.0
 */
@Service
public class Tjfx_jdwzdtjServiceImpl extends ServiceImpl<Tjfx_jdwzdtjMapper, Tjfx_jdwzdtj> implements ITjfx_jdwzdtjService {
    @Autowired
    private Tjfx_jdwzdtjMapper tjfxkhnlfctjMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        tjfxkhnlfctjMapper.extract(tjyf);
    }
}
