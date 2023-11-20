package org.cmms.modules.dkjkpt.bldkftjk.service.impl;

import org.cmms.modules.dkjkpt.bldkftjk.entity.DkjkptBldkftjkBsnm;
import org.cmms.modules.dkjkpt.bldkftjk.mapper.DkjkptBldkftjkBsnmMapper;
import org.cmms.modules.dkjkpt.bldkftjk.mapper.DkjkptBldkftjkBsrMapper;
import org.cmms.modules.dkjkpt.bldkftjk.service.IDkjkptBldkftjkBsnmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 不良贷款反弹监控比上年末
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Service
public class DkjkptBldkftjkBsnmServiceImpl extends ServiceImpl<DkjkptBldkftjkBsnmMapper, DkjkptBldkftjkBsnm> implements IDkjkptBldkftjkBsnmService {

    @Autowired
    private DkjkptBldkftjkBsnmMapper dkjkptBldkftjkBsnmMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        dkjkptBldkftjkBsnmMapper.extract(tjyf);
    }

}
