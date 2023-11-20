package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.impl;

import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhby;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.mapper.TjfxZhbyMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 支行统计表一
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Service
public class TjfxZhbyServiceImpl extends ServiceImpl<TjfxZhbyMapper, TjfxZhby> implements ITjfxZhbyService {

@Autowired
TjfxZhbyMapper tjfxZhbyMapper;

    @Override
    @Transactional
    public  void extract(Map<String,String> sql){
        tjfxZhbyMapper.extract(sql);
    }

    @Override
    public String queryTableDictTextByKey(String table,String text,String code, String key) {
        return tjfxZhbyMapper.queryTableDictTextByKey(table,text,code,key);
    }

}
