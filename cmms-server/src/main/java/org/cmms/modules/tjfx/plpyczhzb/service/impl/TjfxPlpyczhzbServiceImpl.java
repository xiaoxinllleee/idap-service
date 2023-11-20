package org.cmms.modules.tjfx.plpyczhzb.service.impl;

import org.cmms.modules.tjfx.plpyczhzb.entity.TjfxPlpyczhzb;
import org.cmms.modules.tjfx.plpyczhzb.mapper.TjfxPlpyczhzbMapper;
import org.cmms.modules.tjfx.plpyczhzb.service.ITjfxPlpyczhzbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 批量评议村组汇总表
 * @Author: jeecg-boot
 * @Date:   2023-07-07
 * @Version: V1.0
 */
@Service
public class TjfxPlpyczhzbServiceImpl extends ServiceImpl<TjfxPlpyczhzbMapper, TjfxPlpyczhzb> implements ITjfxPlpyczhzbService {

    @Override
    public void init() {
        baseMapper.init();
    }
}
