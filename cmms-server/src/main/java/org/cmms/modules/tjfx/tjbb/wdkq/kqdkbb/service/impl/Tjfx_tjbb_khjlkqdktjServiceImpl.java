package org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.service.impl;

import org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.entity.Tjfx_tjbb_khjlkqdktj;
import org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.mapper.Tjfx_tjbb_khjlkqdktjMapper;
import org.cmms.modules.tjfx.tjbb.wdkq.kqdkbb.service.ITjfx_tjbb_khjlkqdktjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-04-17
 * @Version: V1.0
 */
@Service
public class Tjfx_tjbb_khjlkqdktjServiceImpl extends ServiceImpl<Tjfx_tjbb_khjlkqdktjMapper, Tjfx_tjbb_khjlkqdktj> implements ITjfx_tjbb_khjlkqdktjService {
    @Autowired
    private Tjfx_tjbb_khjlkqdktjMapper ckjkptZhckpldglMapper;
    @Override
    public void extract(String tjyf) {
        ckjkptZhckpldglMapper.extract(tjyf);
    }
}
