package org.cmms.modules.tjfx.tjfxHnkd.service.impl;

import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.tjfxHnkd.mapper.TjfxHnkdMapper;
import org.cmms.modules.tjfx.tjfxHnkd.service.ITjfxHnkdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 提统计分析惠农快贷
 * @Author: Penghr
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Service
public class TjfxHnkdServiceImpl extends ServiceImpl<TjfxHnkdMapper, TjfxHnkd> implements ITjfxHnkdService {
    @Autowired
    private TjfxHnkdMapper tjfxHnkdMapper;

    public List<TjfxHnkd> getByHhbm(String hhbm) {
        return tjfxHnkdMapper.getByHhbm(hhbm);
    }
}
