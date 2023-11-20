package org.cmms.modules.tjfx.zfsjmx.qhsjmx.service.impl;

import org.cmms.modules.tjfx.zfsjmx.qhsjmx.entity.ZfsjmxQh;
import org.cmms.modules.tjfx.zfsjmx.qhsjmx.mapper.ZfsjmxQhMapper;
import org.cmms.modules.tjfx.zfsjmx.qhsjmx.service.IZfsjmxQhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 全行走访数据明细统计
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Service
public class ZfsjmxQhServiceImpl extends ServiceImpl<ZfsjmxQhMapper, ZfsjmxQh> implements IZfsjmxQhService {

    @Autowired
    private ZfsjmxQhMapper mapper;

    @Override
    public void InitDataToQh(Map<String, String> sql) {
        mapper.InitDataToQh(sql);
    }
}
