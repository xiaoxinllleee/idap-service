package org.cmms.modules.tjfx.jcsjgl.dkye.service.impl;

import org.cmms.modules.tjfx.jcsjgl.dkye.entity.TjfxXddkyebXdxtsj;
import org.cmms.modules.tjfx.jcsjgl.dkye.mapper.TjfxXddkyebXdxtsjMapper;
import org.cmms.modules.tjfx.jcsjgl.dkye.service.ITjfxXddkyebXdxtsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-10
 * @Version: V1.0
 */
@Service
public class TjfxXddkyebXdxtsjServiceImpl extends ServiceImpl<TjfxXddkyebXdxtsjMapper, TjfxXddkyebXdxtsj> implements ITjfxXddkyebXdxtsjService {

    @Autowired
    private TjfxXddkyebXdxtsjMapper tjfxXddkyebXdxtsjMapper;
    @Override
    public TjfxXddkyebXdxtsj queryMsg( String zjhm, String jgdm, String tjyf) {
        return tjfxXddkyebXdxtsjMapper.queryMsg(zjhm, jgdm, tjyf);
    }

    @Override
    public int deleteMsg( String tjyf) {
        return tjfxXddkyebXdxtsjMapper.deleteMsg( tjyf);
    }


    @Override
    public void deletemain(Date tjyf){
        tjfxXddkyebXdxtsjMapper.deletemain(tjyf);
    }

}
