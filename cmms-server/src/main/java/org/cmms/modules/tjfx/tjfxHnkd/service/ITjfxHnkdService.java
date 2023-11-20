package org.cmms.modules.tjfx.tjfxHnkd.service;

import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 提统计分析惠农快贷
 * @Author: Penghr
 * @Date:   2022-12-29
 * @Version: V1.0
 */
public interface ITjfxHnkdService extends IService<TjfxHnkd> {
    List<TjfxHnkd> getByHhbm(String hhbm);
}
