package org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.entity.Dkhsdjbmx;
import org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.mapper.DkhsdjbmxMapper;
import org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.service.IDkhsdjbmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: 贷款回收登记簿明细
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Service
public class DkhsdjbmxServiceImpl extends ServiceImpl<DkhsdjbmxMapper, Dkhsdjbmx> implements IDkhsdjbmxService {

    @Resource
    private DkhsdjbmxMapper mapper;


    @Override
    public void init() {
        mapper.init();
    }
}
