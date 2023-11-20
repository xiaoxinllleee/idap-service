package org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.service.impl;

import org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.entity.Qhdkjgfxb;
import org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.mapper.QhdkjgfxbMapper;
import org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.service.IQhdkjgfxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 全行贷款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-10-20
 * @Version: V1.0
 */
@Service
public class QhdkjgfxbServiceImpl extends ServiceImpl<QhdkjgfxbMapper, Qhdkjgfxb> implements IQhdkjgfxbService {

    @Override
    public void pDkjgfxb(String tjrq) {
        baseMapper.pDkjgfxb(tjrq);
    }
}
