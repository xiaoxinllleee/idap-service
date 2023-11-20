package org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.service.impl;

import org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.entity.Czbxyflxmx;
import org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.mapper.CzbxyflxmxMapper;
import org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.service.ICzbxyflxmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 财政保险应付利息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Service
public class CzbxyflxmxServiceImpl extends ServiceImpl<CzbxyflxmxMapper, Czbxyflxmx> implements ICzbxyflxmxService {

    @Override
    public void pCzbxyflxmx(String ksrq, String jsrq) {
        baseMapper.pCzbxyflxmx(ksrq, jsrq);
    }
}
