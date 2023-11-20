package org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.entity.DkjkptDkglckqsfxbAjfp;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.mapper.DkjkptDkglckqsfxbAjfpMapper;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.service.IDkjkptDkglckqsfxbAjfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 资金返还情况监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Service
public class DkjkptDkglckqsfxbAjfpServiceImpl extends ServiceImpl<DkjkptDkglckqsfxbAjfpMapper, DkjkptDkglckqsfxbAjfp> implements IDkjkptDkglckqsfxbAjfpService {
    @Autowired
    private DkjkptDkglckqsfxbAjfpMapper DkjkptDkglckqsfxbAjfpMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        DkjkptDkglckqsfxbAjfpMapper.extract(tjyf);
    }
}
