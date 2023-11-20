package org.cmms.modules.tjbb.ckywfx.dqfdtj.service.impl;

import org.cmms.modules.tjbb.ckywfx.dqfdtj.entity.Dqfdtj;
import org.cmms.modules.tjbb.ckywfx.dqfdtj.mapper.DqfdtjMapper;
import org.cmms.modules.tjbb.ckywfx.dqfdtj.service.IDqfdtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 定期分段统计
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Service
public class DqfdtjServiceImpl extends ServiceImpl<DqfdtjMapper, Dqfdtj> implements IDqfdtjService {

    @Override
    public void pDqfdtj(String tjyf) {
        baseMapper.pDqfdtj(tjyf);
    }
}
