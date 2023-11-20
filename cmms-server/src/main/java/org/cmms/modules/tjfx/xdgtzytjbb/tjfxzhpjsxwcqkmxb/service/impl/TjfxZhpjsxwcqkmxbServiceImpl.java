package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service.impl;

import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxZhpjsxwcqkmxb;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.mapper.TjfxZhpjsxwcqkmxbMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service.ITjfxZhpjsxwcqkmxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 支行评级授信完成情况明细表
 * @Author: cmms
 * @Date:   2019-12-11
 * @Version: V1.0
 */
@Service
public class TjfxZhpjsxwcqkmxbServiceImpl extends ServiceImpl<TjfxZhpjsxwcqkmxbMapper, TjfxZhpjsxwcqkmxb> implements ITjfxZhpjsxwcqkmxbService {

    @Autowired
    TjfxZhpjsxwcqkmxbMapper tjfxZhpjsxwcqkmxbMapper;

    @Override
    @Transactional
    public  void extract(Map<String,String> sql){
        tjfxZhpjsxwcqkmxbMapper.extract(sql);
    }

}
