package org.cmms.modules.khjg.zbmx.service.impl;

import org.cmms.modules.khjg.zbmx.entity.ZbmxSet;
import org.cmms.modules.khjg.zbmx.mapper.ZbmxSetMapper;
import org.cmms.modules.khjg.zbmx.service.IZbmxSetService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 指标明细设置
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
@Service
public class ZbmxSetServiceImpl extends ServiceImpl<ZbmxSetMapper, ZbmxSet> implements IZbmxSetService {

    @Override
    public List<LinkedHashMap> execZbmx(String sqlStr) {
        return baseMapper.execZbmx(sqlStr);
    }

    @Override
    public Long execCount(String sqlStr) {
        return baseMapper.execCount(sqlStr);
    }
}
