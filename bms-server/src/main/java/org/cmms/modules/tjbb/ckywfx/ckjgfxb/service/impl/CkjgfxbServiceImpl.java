package org.cmms.modules.tjbb.ckywfx.ckjgfxb.service.impl;

import org.cmms.modules.tjbb.ckywfx.ckjgfxb.entity.Ckjgfxb;
import org.cmms.modules.tjbb.ckywfx.ckjgfxb.mapper.CkjgfxbMapper;
import org.cmms.modules.tjbb.ckywfx.ckjgfxb.service.ICkjgfxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Service
public class CkjgfxbServiceImpl extends ServiceImpl<CkjgfxbMapper, Ckjgfxb> implements ICkjgfxbService {
    @Override
    public void pCkjgfx(String tjrq) {
        baseMapper.pCkjgfx(tjrq);
    }
}
