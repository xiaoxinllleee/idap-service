package org.cmms.modules.tjfx.zcsxtj.zcsxzhjdb.service.impl;

import org.cmms.modules.tjfx.zcsxtj.zcsxzhjdb.entity.Zcsxzhjdb;
import org.cmms.modules.tjfx.zcsxtj.zcsxzhjdb.mapper.ZcsxzhjdbMapper;
import org.cmms.modules.tjfx.zcsxtj.zcsxzhjdb.service.IZcsxzhjdbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 整村授信支行进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
@Service
public class ZcsxzhjdbServiceImpl extends ServiceImpl<ZcsxzhjdbMapper, Zcsxzhjdb> implements IZcsxzhjdbService {
    public void init(String tjrq) {
        baseMapper.init(tjrq);
    }
}
