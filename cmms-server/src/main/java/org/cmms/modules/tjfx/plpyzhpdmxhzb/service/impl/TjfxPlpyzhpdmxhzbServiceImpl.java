package org.cmms.modules.tjfx.plpyzhpdmxhzb.service.impl;

import org.cmms.modules.tjfx.plpyzhpdmxhzb.entity.TjfxPlpyzhpdmxhzb;
import org.cmms.modules.tjfx.plpyzhpdmxhzb.mapper.TjfxPlpyzhpdmxhzbMapper;
import org.cmms.modules.tjfx.plpyzhpdmxhzb.service.ITjfxPlpyzhpdmxhzbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 批量评议综合评定明细汇总表
 * @Author: jeecg-boot
 * @Date:   2023-06-28
 * @Version: V1.0
 */
@Service
public class TjfxPlpyzhpdmxhzbServiceImpl extends ServiceImpl<TjfxPlpyzhpdmxhzbMapper, TjfxPlpyzhpdmxhzb> implements ITjfxPlpyzhpdmxhzbService {

    @Override
    public void init() {
        baseMapper.init();
    }
}
