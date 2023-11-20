package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service.impl;

import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBsy;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.mapper.DkjkptZhbldkftjkBsyMapper;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service.IDkjkptZhbldkftjkBsyService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description: 支行不良贷款反弹监控（比上月）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Service
public class DkjkptZhbldkftjkBsyServiceImpl extends ServiceImpl<DkjkptZhbldkftjkBsyMapper, DkjkptZhbldkftjkBsy> implements IDkjkptZhbldkftjkBsyService {

    @Resource
    private DkjkptZhbldkftjkBsyMapper mapper;

    @Override
    @Transactional
    public void initData(String tjyf) {
        mapper.initData(tjyf);
    }
}
