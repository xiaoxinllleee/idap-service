package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service.impl;

import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBzr;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.mapper.DkjkptZhbldkftjkBzrMapper;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service.IDkjkptZhbldkftjkBzrService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description: 支行不良贷款反弹监控（比昨日）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Service
public class DkjkptZhbldkftjkBzrServiceImpl extends ServiceImpl<DkjkptZhbldkftjkBzrMapper, DkjkptZhbldkftjkBzr> implements IDkjkptZhbldkftjkBzrService {

    @Resource
    private DkjkptZhbldkftjkBzrMapper mapper;

    @Override
    @Transactional
    public void initData(String tjyf) {
        mapper.initData(tjyf);
    }
}
