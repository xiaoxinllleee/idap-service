package org.cmms.modules.tjfx.tjbb.wdkq.hfkqbb.service.impl;

import org.cmms.modules.tjfx.tjbb.wdkq.hfkqbb.entity.Tjfx_tjbb_khjlhfkqtj;
import org.cmms.modules.tjfx.tjbb.wdkq.hfkqbb.mapper.Tjfx_tjbb_khjlhfkqtjMapper;
import org.cmms.modules.tjfx.tjbb.wdkq.hfkqbb.service.ITjfx_tjbb_khjlhfkqtjService;
import org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.mapper.Tjfx_tjbb_khjlkqdktjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-18
 * @Version: V1.0
 */
@Service
public class Tjfx_tjbb_khjlhfkqtjServiceImpl extends ServiceImpl<Tjfx_tjbb_khjlhfkqtjMapper, Tjfx_tjbb_khjlhfkqtj> implements ITjfx_tjbb_khjlhfkqtjService {
    @Autowired
    private Tjfx_tjbb_khjlhfkqtjMapper ckjkptZhckpldglMapper;
    @Override
    public void extract(String tjyf) {
        ckjkptZhckpldglMapper.extract(tjyf);
    }
}
