package org.cmms.modules.tjbb.ckywfx.qhckjgfxb.service.impl;

import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.entity.Qhckjgfxb;
import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.mapper.QhckjgfxbMapper;
import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.service.IQhckjgfxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 全行存款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-10-20
 * @Version: V1.0
 */
@Service
public class QhckjgfxbServiceImpl extends ServiceImpl<QhckjgfxbMapper, Qhckjgfxb> implements IQhckjgfxbService {

    @Override
    public void pQhckjgfxb(String tjrq) {
        baseMapper.pQhckjgfxb(tjrq);
    }
}
