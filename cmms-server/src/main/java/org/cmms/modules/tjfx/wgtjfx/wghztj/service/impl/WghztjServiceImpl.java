package org.cmms.modules.tjfx.wgtjfx.wghztj.service.impl;

import org.cmms.modules.tjfx.wgtjfx.wghztj.entity.Wghztj;
import org.cmms.modules.tjfx.wgtjfx.wghztj.mapper.WghztjMapper;
import org.cmms.modules.tjfx.wgtjfx.wghztj.service.IWghztjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 网格汇总统计
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Service
public class WghztjServiceImpl extends ServiceImpl<WghztjMapper, Wghztj> implements IWghztjService {
    @Autowired
    private WghztjMapper wghztjMapper;
    @Override
    public Wghztj getLatestInfo(String wgbh) {
        return wghztjMapper.getLatestInfo(wgbh);
    }

}
