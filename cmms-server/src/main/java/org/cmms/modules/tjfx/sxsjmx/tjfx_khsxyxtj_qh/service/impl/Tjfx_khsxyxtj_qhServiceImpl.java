package org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.service.impl;

import org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.entity.Tjfx_khsxyxtj_qh;
import org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.mapper.Tjfx_khsxyxtj_qhMapper;
import org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_qh.service.ITjfx_khsxyxtj_qhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Service
public class Tjfx_khsxyxtj_qhServiceImpl extends ServiceImpl<Tjfx_khsxyxtj_qhMapper, Tjfx_khsxyxtj_qh> implements ITjfx_khsxyxtj_qhService {
    @Autowired
    private Tjfx_khsxyxtj_qhMapper tjfxNshbeMapper;


    @Override
    public void extract(String tjyf) {
        tjfxNshbeMapper.extract(tjyf);
    }
}
