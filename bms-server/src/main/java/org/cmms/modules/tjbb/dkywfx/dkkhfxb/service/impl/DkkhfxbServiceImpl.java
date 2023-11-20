package org.cmms.modules.tjbb.dkywfx.dkkhfxb.service.impl;

import org.cmms.modules.tjbb.dkywfx.dkkhfxb.entity.Dkkhfxb;
import org.cmms.modules.tjbb.dkywfx.dkkhfxb.mapper.DkkhfxbMapper;
import org.cmms.modules.tjbb.dkywfx.dkkhfxb.service.IDkkhfxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款客户分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-24
 * @Version: V1.0
 */
@Service
public class DkkhfxbServiceImpl extends ServiceImpl<DkkhfxbMapper, Dkkhfxb> implements IDkkhfxbService {

    @Override
    public void pDkkhfxb(String tjyf) {
        baseMapper.pDkkhfxb(tjyf);
    }
}
