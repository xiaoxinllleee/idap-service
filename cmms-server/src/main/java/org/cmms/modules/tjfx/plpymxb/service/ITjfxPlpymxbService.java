package org.cmms.modules.tjfx.plpymxb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.plpymxb.entity.TjfxPlpymxb;

/**
 * @Description: 批量评议明细表
 * @Author: jeecg-boot
 * @Date:   2023-06-28
 * @Version: V1.0
 */
public interface ITjfxPlpymxbService extends IService<TjfxPlpymxb> {
    void init();
}
