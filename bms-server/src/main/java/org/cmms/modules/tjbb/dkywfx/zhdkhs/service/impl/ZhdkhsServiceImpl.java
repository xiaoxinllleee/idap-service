package org.cmms.modules.tjbb.dkywfx.zhdkhs.service.impl;

import org.cmms.modules.tjbb.dkywfx.zhdkhs.entity.Zhdkhs;
import org.cmms.modules.tjbb.dkywfx.zhdkhs.mapper.ZhdkhsMapper;
import org.cmms.modules.tjbb.dkywfx.zhdkhs.service.IZhdkhsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 支行贷款户数
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Service
public class ZhdkhsServiceImpl extends ServiceImpl<ZhdkhsMapper, Zhdkhs> implements IZhdkhsService {

    @Override
    public void pZhdkhs(String tjyf) {
        baseMapper.pZhdkhs(tjyf);
    }
}
