package org.cmms.modules.sjxf.hxxt.dkzdkb.service.impl;

import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.mapper.DkzdkbMapper;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 贷款主档宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Service
public class DkzdkbServiceImpl extends ServiceImpl<DkzdkbMapper, Dkzdkb> implements IDkzdkbService {

    @Override
    public String queryMaxDataDate() {
        return baseMapper.queryMaxDataDate();
    }

    @Override
    public double queryDkye(String zjhm) {
        return baseMapper.queryDkye(zjhm);
    }
}
