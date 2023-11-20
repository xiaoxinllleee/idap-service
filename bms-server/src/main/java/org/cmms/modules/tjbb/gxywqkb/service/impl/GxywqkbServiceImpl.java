package org.cmms.modules.tjbb.gxywqkb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.tjbb.gxywqkb.entity.Gxywqkb;
import org.cmms.modules.tjbb.gxywqkb.mapper.GxywqkbMapper;
import org.cmms.modules.tjbb.gxywqkb.service.IGxywqkbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 各项业务情况表
 * @Author: Penghr
 * @Date:   2022-12-12
 * @Version: V1.0
 */
@Service
public class GxywqkbServiceImpl extends ServiceImpl<GxywqkbMapper, Gxywqkb> implements IGxywqkbService {

    @Override
    public Gxywqkb queryByDatadateAndXh(String data_date, String xh) {
        return baseMapper.queryByDatadateAndXh(data_date, xh);
    }
}
