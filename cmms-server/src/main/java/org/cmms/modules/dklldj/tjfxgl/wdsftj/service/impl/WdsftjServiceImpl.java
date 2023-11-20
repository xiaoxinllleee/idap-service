package org.cmms.modules.dklldj.tjfxgl.wdsftj.service.impl;

import org.cmms.modules.dklldj.tjfxgl.wdsftj.entity.Wdsftj;
import org.cmms.modules.dklldj.tjfxgl.wdsftj.mapper.WdsftjMapper;
import org.cmms.modules.dklldj.tjfxgl.wdsftj.service.IWdsftjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 网点上浮统计
 * @Author: jeecg-boot
 * @Date:   2020-03-06
 * @Version: V1.0
 */
@Service
public class WdsftjServiceImpl extends ServiceImpl<WdsftjMapper, Wdsftj> implements IWdsftjService {

    @Autowired
    private WdsftjMapper mapper;

    /**
     * 网点上浮数据统计
     */
    @Override
    public void init() {
        mapper.init();
    }
}
