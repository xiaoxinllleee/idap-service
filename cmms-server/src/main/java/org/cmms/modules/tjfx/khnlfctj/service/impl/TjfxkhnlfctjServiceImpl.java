package org.cmms.modules.tjfx.khnlfctj.service.impl;

import org.cmms.modules.gzap.rwxf.mapper.GzapRwxfJxzMapper;
import org.cmms.modules.tjfx.khnlfctj.entity.Tjfxkhnlfctj;
import org.cmms.modules.tjfx.khnlfctj.mapper.TjfxkhnlfctjMapper;
import org.cmms.modules.tjfx.khnlfctj.service.ITjfxkhnlfctjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 客户年龄分层统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Service
public class TjfxkhnlfctjServiceImpl extends ServiceImpl<TjfxkhnlfctjMapper, Tjfxkhnlfctj> implements ITjfxkhnlfctjService {

    @Autowired
    private TjfxkhnlfctjMapper tjfxkhnlfctjMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        tjfxkhnlfctjMapper.extract(tjyf);
    }


}
