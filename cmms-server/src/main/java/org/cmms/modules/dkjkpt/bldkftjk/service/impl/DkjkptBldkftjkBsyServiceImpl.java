package org.cmms.modules.dkjkpt.bldkftjk.service.impl;

import org.cmms.modules.dkjkpt.bldkftjk.entity.DkjkptBldkftjkBsy;
import org.cmms.modules.dkjkpt.bldkftjk.mapper.DkjkptBldkftjkBsyMapper;
import org.cmms.modules.dkjkpt.bldkftjk.service.IDkjkptBldkftjkBsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 不良贷款反弹监控比上月
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Service
public class DkjkptBldkftjkBsyServiceImpl extends ServiceImpl<DkjkptBldkftjkBsyMapper, DkjkptBldkftjkBsy> implements IDkjkptBldkftjkBsyService {

    @Autowired
    private DkjkptBldkftjkBsyMapper dkjkptBldkftjkBsyMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        dkjkptBldkftjkBsyMapper.extract(tjyf);
    }

}
