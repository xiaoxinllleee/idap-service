package org.cmms.modules.tjbb.dkywfx.dkffmx.service.impl;

import org.cmms.modules.tjbb.dkywfx.dkffmx.entity.Dkffmx;
import org.cmms.modules.tjbb.dkywfx.dkffmx.mapper.DkffmxMapper;
import org.cmms.modules.tjbb.dkywfx.dkffmx.service.IDkffmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款发放明细
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Service
public class DkffmxServiceImpl extends ServiceImpl<DkffmxMapper, Dkffmx> implements IDkffmxService {

    @Override
    public void pDkffmx(String ksrq, String jsrq) {
        baseMapper.pDkffmx(ksrq,jsrq);
    }
}
