package org.cmms.modules.tjfx.xdgtzytjbb.cscqzhbe.service.impl;

import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhbe.entity.TjfxCscqzhbe;
import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhbe.mapper.TjfxCscqzhbeMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhbe.service.ITjfxCscqzhbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 村社村前统计支行表二
 * @Author: cmms
 * @Date:   2019-12-14
 * @Version: V1.0
 */
@Service
public class TjfxCscqzhbeServiceImpl extends ServiceImpl<TjfxCscqzhbeMapper, TjfxCscqzhbe> implements ITjfxCscqzhbeService {

    @Autowired
    private TjfxCscqzhbeMapper tjfxCscqzhbeMapper;

    @Override
    @Transactional
    public void extract(String tjyf){
        tjfxCscqzhbeMapper.extract(tjyf);
    }

}
