package org.cmms.modules.tjfx.plpymxb.service.impl;

import org.cmms.modules.tjfx.plpymxb.entity.TjfxPlpymxb;
import org.cmms.modules.tjfx.plpymxb.mapper.TjfxPlpymxbMapper;
import org.cmms.modules.tjfx.plpymxb.service.ITjfxPlpymxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 批量评议明细表
 * @Author: jeecg-boot
 * @Date:   2023-06-28
 * @Version: V1.0
 */
@Service
public class TjfxPlpymxbServiceImpl extends ServiceImpl<TjfxPlpymxbMapper, TjfxPlpymxb> implements ITjfxPlpymxbService {

    @Override
    public void init() {
        baseMapper.init();
    }
}
