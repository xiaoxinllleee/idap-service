package org.cmms.modules.tjfx.zftjysb.xzcysb.service.impl;

import org.cmms.modules.tjfx.zftjysb.xzcysb.entity.ZftjysbXzc;
import org.cmms.modules.tjfx.zftjysb.xzcysb.mapper.ZftjysbXzcMapper;
import org.cmms.modules.tjfx.zftjysb.xzcysb.service.IZftjysbXzcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 走访统计验收表-行政村
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
@Service
public class ZftjysbXzcServiceImpl extends ServiceImpl<ZftjysbXzcMapper, ZftjysbXzc> implements IZftjysbXzcService {
    @Autowired
    private ZftjysbXzcMapper zftjysbXzcMapper;

    @Override
    public Date getMaxDate() {
        return zftjysbXzcMapper.getMaxDate();
    }
    @Override
    public void init() {
        zftjysbXzcMapper.init();
    }
}
