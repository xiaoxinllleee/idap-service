package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.service.impl;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbe;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.mapper.TjfxZhbeMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.service.ITjfxZhbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 支行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Service
public class TjfxZhbeServiceImpl extends ServiceImpl<TjfxZhbeMapper, TjfxZhbe> implements ITjfxZhbeService {

    @Autowired
    private TjfxZhbeMapper tjfxZhbeMapper;

    @Override
    @Transactional
    public void extract(java.util.Map<String,String> sql){
        tjfxZhbeMapper.extract(sql);
    }

}
