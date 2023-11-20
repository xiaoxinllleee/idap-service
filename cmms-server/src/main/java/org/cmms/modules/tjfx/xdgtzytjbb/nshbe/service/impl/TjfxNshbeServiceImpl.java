package org.cmms.modules.tjfx.xdgtzytjbb.nshbe.service.impl;

import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.entity.TjfxNshbe;
import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.mapper.TjfxNshbeMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.service.ITjfxNshbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 农商行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Service
public class TjfxNshbeServiceImpl extends ServiceImpl<TjfxNshbeMapper, TjfxNshbe> implements ITjfxNshbeService {

    @Autowired
    private TjfxNshbeMapper tjfxNshbeMapper;

    @Override
    @Transactional
    public void extract(String tjyf){
        tjfxNshbeMapper.extract(tjyf);
    }

}
