package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.service.impl;

import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.entity.TjfxJrphsjKhjl;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.mapper.Tjfx_Jrphsj_KhjlMapper;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphjs_khjl.service.ITjfx_Jrphsj_KhjlService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.mapper.TjfxZhbyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 客户经理金融普汇
 * @Author: jeecg-boot
 * @Date:   2020-09-07
 * @Version: V1.0
 */
@Service
public class Tjfx_Jrphsj_KhjlServiceImpl extends ServiceImpl<Tjfx_Jrphsj_KhjlMapper, TjfxJrphsjKhjl> implements ITjfx_Jrphsj_KhjlService {

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
