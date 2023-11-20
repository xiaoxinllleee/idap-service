package org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.service.impl;

import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.entity.TjfxCscqzhby;
import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.mapper.TjfxCscqzhbyMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.service.ITjfxCscqzhbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 村社村前统计支行表一
 * @Author: cmms
 * @Date:   2019-12-14
 * @Version: V1.0
 */
@Service
public class TjfxCscqzhbyServiceImpl extends ServiceImpl<TjfxCscqzhbyMapper, TjfxCscqzhby> implements ITjfxCscqzhbyService {

    @Autowired
    TjfxCscqzhbyMapper tjfxCscqzhbyMapper;

    public void extract(Map<String,String> sql){
        tjfxCscqzhbyMapper.extract(sql);
    }


}
