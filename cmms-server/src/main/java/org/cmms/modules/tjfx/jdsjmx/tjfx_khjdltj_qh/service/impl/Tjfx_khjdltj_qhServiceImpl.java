package org.cmms.modules.tjfx.jdsjmx.tjfx_khjdltj_qh.service.impl;

import org.cmms.modules.tjfx.jdsjmx.tjfx_khjdltj_qh.entity.Tjfx_khjdltj_qh;
import org.cmms.modules.tjfx.jdsjmx.tjfx_khjdltj_qh.mapper.Tjfx_khjdltj_qhMapper;
import org.cmms.modules.tjfx.jdsjmx.tjfx_khjdltj_qh.service.ITjfx_khjdltj_qhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-20
 * @Version: V1.0
 */
@Service
public class Tjfx_khjdltj_qhServiceImpl extends ServiceImpl<Tjfx_khjdltj_qhMapper, Tjfx_khjdltj_qh> implements ITjfx_khjdltj_qhService {
    @Autowired
    private Tjfx_khjdltj_qhMapper tjfxNshbeMapper;


    @Override
    public void extract(String tjyf) {
        tjfxNshbeMapper.extract(tjyf);
}
}
