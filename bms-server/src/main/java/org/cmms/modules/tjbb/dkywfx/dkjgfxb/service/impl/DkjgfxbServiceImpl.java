package org.cmms.modules.tjbb.dkywfx.dkjgfxb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.tjbb.dkywfx.dkjgfxb.entity.Dkjgfxb;
import org.cmms.modules.tjbb.dkywfx.dkjgfxb.mapper.DkjgfxbMapper;
import org.cmms.modules.tjbb.dkywfx.dkjgfxb.service.IDkjgfxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@Service
public class DkjgfxbServiceImpl extends ServiceImpl<DkjgfxbMapper, Dkjgfxb> implements IDkjgfxbService {

    @Override
    public void pDkjgfxb(String tjrq) {
        baseMapper.pDkjgfxb(tjrq);
    }
}
