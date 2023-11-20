package org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjc.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjc.entity.DkjkptDkglckqsfxb;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjc.mapper.DkjkptDkglckqsfxbMapper;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjc.service.IDkjkptDkglckqsfxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 资金返还情况监测
 * @Author: cmms
 * @Date:   2019-10-08
 * @Version: V1.0
 */
@Service
public class DkjkptDkglckqsfxbServiceImpl extends ServiceImpl<DkjkptDkglckqsfxbMapper, DkjkptDkglckqsfxb> implements IDkjkptDkglckqsfxbService {
    @Autowired
    private DkjkptDkglckqsfxbMapper dkjkptDkglckqsfxbMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        dkjkptDkglckqsfxbMapper.extract(tjyf);
    }
}
