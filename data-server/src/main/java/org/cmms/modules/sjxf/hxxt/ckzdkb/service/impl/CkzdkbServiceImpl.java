package org.cmms.modules.sjxf.hxxt.ckzdkb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.mapper.CkzdkbMapper;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款主档宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Service
public class CkzdkbServiceImpl extends ServiceImpl<CkzdkbMapper, Ckzdkb> implements ICkzdkbService {
    @Override
    public String queryMaxDataDate() {
        return baseMapper.queryMaxDataDate();
    }

    @Override
    public String getMaxLoadDate() {
        return baseMapper.getMaxLoadDate();
    }
}
