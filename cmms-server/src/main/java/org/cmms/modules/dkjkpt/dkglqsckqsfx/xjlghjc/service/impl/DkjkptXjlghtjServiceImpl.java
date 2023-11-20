package org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjc.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjc.entity.DkjkptXjlghtj;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjc.mapper.DkjkptXjlghtjMapper;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjc.service.IDkjkptXjlghtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 现金流归行监测
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Service
public class DkjkptXjlghtjServiceImpl extends ServiceImpl<DkjkptXjlghtjMapper, DkjkptXjlghtj> implements IDkjkptXjlghtjService {
    @Autowired
    private DkjkptXjlghtjMapper dkjkptXjlghtjMapper;

    @Override
    @Transactional
    public void extract(Map<String,String> sql) {

        dkjkptXjlghtjMapper.extract(sql);
    }
}
