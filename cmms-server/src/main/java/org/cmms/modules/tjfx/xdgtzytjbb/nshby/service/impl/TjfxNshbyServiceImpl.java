package org.cmms.modules.tjfx.xdgtzytjbb.nshby.service.impl;

import org.cmms.modules.tjfx.xdgtzytjbb.nshby.entity.TjfxNshby;
import org.cmms.modules.tjfx.xdgtzytjbb.nshby.mapper.TjfxNshbyMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.nshby.service.ITjfxNshbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 农商行统计表一
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Service
public class TjfxNshbyServiceImpl extends ServiceImpl<TjfxNshbyMapper, TjfxNshby> implements ITjfxNshbyService {

    @Autowired
    TjfxNshbyMapper tjfxNshbyMapper;

    @Override
    @Transactional
    public  void extract(Map<String,String> sql){
        tjfxNshbyMapper.extract(sql);
    }


}
