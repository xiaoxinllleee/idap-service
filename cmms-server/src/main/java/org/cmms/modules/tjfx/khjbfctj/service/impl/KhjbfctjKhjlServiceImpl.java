package org.cmms.modules.tjfx.khjbfctj.service.impl;

import org.cmms.modules.tjfx.khjbfctj.service.IKhjbfctjKhjlService;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjKhjl;
import org.cmms.modules.tjfx.khjbfctj.mapper.KhjbfctjKhjlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 客户级别分层统计_客户经理
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Service
public class KhjbfctjKhjlServiceImpl extends ServiceImpl<KhjbfctjKhjlMapper, KhjbfctjKhjl> implements IKhjbfctjKhjlService {

    @Autowired
    private KhjbfctjKhjlMapper mapper;

    @Override
    public void extract(Map<String, String> sql) {
        mapper.extract(sql);
    }
}
