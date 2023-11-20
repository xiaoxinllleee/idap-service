package org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.service.impl;

import org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.entity.TjfxSfdpjsxwcqkmxb;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.mapper.TjfxSfdpjsxwcqkmxbMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.service.ITjfxSfdpjsxwcqkmxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 示范点评级授信完成情况明细表
 * @Author: cmms
 * @Date:   2019-12-11
 * @Version: V1.0
 */
@Service
public class TjfxSfdpjsxwcqkmxbServiceImpl extends ServiceImpl<TjfxSfdpjsxwcqkmxbMapper, TjfxSfdpjsxwcqkmxb> implements ITjfxSfdpjsxwcqkmxbService {

    @Autowired
    TjfxSfdpjsxwcqkmxbMapper tjfxSfdpjsxwcqkmxbMapper;

    @Override
    @Transactional
    public  void extract(Map<String,String> sql){
        tjfxSfdpjsxwcqkmxbMapper.extract(sql);
    }

}
