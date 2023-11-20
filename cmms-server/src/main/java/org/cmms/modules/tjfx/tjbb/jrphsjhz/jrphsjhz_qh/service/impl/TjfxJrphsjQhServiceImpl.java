package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.service.impl;

import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.entity.TjfxJrphsjQh;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.mapper.TjfxJrphsjQhMapper;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.service.ITjfxJrphsjQhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 金融普惠数据汇总
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
@Service
public class TjfxJrphsjQhServiceImpl extends ServiceImpl<TjfxJrphsjQhMapper, TjfxJrphsjQh> implements ITjfxJrphsjQhService {

    @Autowired
    private TjfxJrphsjQhMapper tjfxJrphsjQhMapper;

    @Override
    public void extract(String tjyf) {
        tjfxJrphsjQhMapper.extract(tjyf);
    }
}
