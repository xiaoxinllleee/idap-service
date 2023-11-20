package org.cmms.modules.tjfx.zfsjmx.bldkhzfsjmx.service.impl;

import org.cmms.modules.tjfx.zfsjmx.bldkhzfsjmx.entity.BldkhZfsjmx;
import org.cmms.modules.tjfx.zfsjmx.bldkhzfsjmx.mapper.BldkhZfsjmxMapper;
import org.cmms.modules.tjfx.zfsjmx.bldkhzfsjmx.service.IBldkhZfsjmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 不良贷款户走访数据明细
 * @Author: jeecg-boot
 * @Date:   2022-06-29
 * @Version: V1.0
 */
@Service
public class BldkhZfsjmxServiceImpl extends ServiceImpl<BldkhZfsjmxMapper, BldkhZfsjmx> implements IBldkhZfsjmxService {
    @Autowired
    private BldkhZfsjmxMapper bldkhZfsjmxMapper;
    public void init(String tjrq) {
        bldkhZfsjmxMapper.init(tjrq);
    }
}
