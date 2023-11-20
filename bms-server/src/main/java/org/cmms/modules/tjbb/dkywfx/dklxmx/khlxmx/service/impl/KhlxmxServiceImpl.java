package org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.entity.Khlxmx;
import org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.mapper.KhlxmxMapper;
import org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.service.IKhlxmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户利息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Service
public class KhlxmxServiceImpl extends ServiceImpl<KhlxmxMapper, Khlxmx> implements IKhlxmxService {

    @Override
    public void pKhlxmx(String ksrq, String jsrq) {
        baseMapper.pKhlxmx(ksrq, jsrq);
    }
}
