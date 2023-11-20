package org.cmms.modules.xddagl.tjfx.dkdalrqkb.service.impl;

import org.cmms.modules.xddagl.tjfx.dkdalrqkb.entity.Dkdalrqkb;
import org.cmms.modules.xddagl.tjfx.dkdalrqkb.mapper.DkdalrqkbMapper;
import org.cmms.modules.xddagl.tjfx.dkdalrqkb.service.IDkdalrqkbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款档案录入情况表
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Service
public class DkdalrqkbServiceImpl extends ServiceImpl<DkdalrqkbMapper, Dkdalrqkb> implements IDkdalrqkbService {

    @Override
    public void pDkdalrqkb() {
        baseMapper.pDkdalrqkb();
    }
}
