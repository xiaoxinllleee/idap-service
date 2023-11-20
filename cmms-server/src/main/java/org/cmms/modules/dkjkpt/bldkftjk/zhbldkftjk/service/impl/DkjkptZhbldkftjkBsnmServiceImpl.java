package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service.impl;

import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBsnm;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.mapper.DkjkptZhbldkftjkBsnmMapper;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service.IDkjkptZhbldkftjkBsnmService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description: 支行不良贷款反弹监控比上年末
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Service
public class DkjkptZhbldkftjkBsnmServiceImpl extends ServiceImpl<DkjkptZhbldkftjkBsnmMapper, DkjkptZhbldkftjkBsnm> implements IDkjkptZhbldkftjkBsnmService {

    @Resource
    private DkjkptZhbldkftjkBsnmMapper mapper;

    @Override
    @Transactional
    public void initData(String tjyf) {
        mapper.initData(tjyf);
    }
}
