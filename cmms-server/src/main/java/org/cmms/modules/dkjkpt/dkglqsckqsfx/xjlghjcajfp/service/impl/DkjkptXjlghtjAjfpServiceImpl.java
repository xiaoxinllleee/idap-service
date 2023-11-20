package org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.entity.DkjkptXjlghtjAjfp;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.mapper.DkjkptXjlghtjAjfpMapper;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.service.IDkjkptXjlghtjAjfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 现金流归行监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@Service
public class DkjkptXjlghtjAjfpServiceImpl extends ServiceImpl<DkjkptXjlghtjAjfpMapper, DkjkptXjlghtjAjfp> implements IDkjkptXjlghtjAjfpService {
    @Autowired
    private DkjkptXjlghtjAjfpMapper dkjkptXjlghtjAjfpMapper;

    @Override
    @Transactional
    public void extract(Map<String,String> sql) {
        dkjkptXjlghtjAjfpMapper.extract(sql);
    }
}
