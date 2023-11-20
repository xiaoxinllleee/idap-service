package org.cmms.modules.tjfx.khjbfctj.service.impl;

import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjZh;
import org.cmms.modules.tjfx.khjbfctj.mapper.KhjbfctjZhMapper;
import org.cmms.modules.tjfx.khjbfctj.service.IKhjbfctjZhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 客户级别分层统计_支行
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Service
public class KhjbfctjZhServiceImpl extends ServiceImpl<KhjbfctjZhMapper, KhjbfctjZh> implements IKhjbfctjZhService {

    @Autowired
    private KhjbfctjZhMapper mapper;

    @Override
    public void extract(Map<String, String> sql) {
        mapper.extract(sql);
    }
}
