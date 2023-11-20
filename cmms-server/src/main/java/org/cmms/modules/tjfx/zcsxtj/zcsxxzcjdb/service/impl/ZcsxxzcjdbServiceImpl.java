package org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.service.impl;

import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.entity.Zcsxxzcjdb;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.mapper.ZcsxxzcjdbMapper;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.service.IZcsxxzcjdbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 整村授信行政村进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
@Service
public class ZcsxxzcjdbServiceImpl extends ServiceImpl<ZcsxxzcjdbMapper, Zcsxxzcjdb> implements IZcsxxzcjdbService {

    @Override
    public List<Zcsxxzcjdb> getCunList() {
        return baseMapper.getCunList();
    }
}
