package org.cmms.modules.dkjkpt.bldkftjk.service.impl;

import org.cmms.modules.dkjkpt.bldkftjk.entity.DkjkptBldkftjkBsr;
import org.cmms.modules.dkjkpt.bldkftjk.mapper.DkjkptBldkftjkBsrMapper;
import org.cmms.modules.dkjkpt.bldkftjk.service.IDkjkptBldkftjkBsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 不良贷款反弹监控比昨日
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Service
public class DkjkptBldkftjkBsrServiceImpl extends ServiceImpl<DkjkptBldkftjkBsrMapper, DkjkptBldkftjkBsr> implements IDkjkptBldkftjkBsrService {

    @Autowired
    private DkjkptBldkftjkBsrMapper dkjkptBldkftjkBsrMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        dkjkptBldkftjkBsrMapper.extract(tjyf);
    }

}
