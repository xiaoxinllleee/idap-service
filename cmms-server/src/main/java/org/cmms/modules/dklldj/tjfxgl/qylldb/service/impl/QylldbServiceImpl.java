package org.cmms.modules.dklldj.tjfxgl.qylldb.service.impl;

import org.cmms.modules.dklldj.tjfxgl.qylldb.entity.Qylldb;
import org.cmms.modules.dklldj.tjfxgl.qylldb.mapper.QylldbMapper;
import org.cmms.modules.dklldj.tjfxgl.qylldb.service.IQylldbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 签约利率对比
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Service
public class QylldbServiceImpl extends ServiceImpl<QylldbMapper, Qylldb> implements IQylldbService {

    @Autowired
    private QylldbMapper mapper;

    @Override
    public void init(Map<String, String> sql) {
        mapper.init(sql);
    }
}
