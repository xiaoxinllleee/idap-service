package org.cmms.modules.dkjkpt.dksjjk.dkqxyj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dkqxyj.entity.Dkqxyj;
import org.cmms.modules.dkjkpt.dksjjk.dkqxyj.mapper.DkqxyjMapper;
import org.cmms.modules.dkjkpt.dksjjk.dkqxyj.service.IDkqxyjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款欠息预警
 * @Author: jeecg-boot
 * @Date:   2022-09-15
 * @Version: V1.0
 */
@Service
public class DkqxyjServiceImpl extends ServiceImpl<DkqxyjMapper, Dkqxyj> implements IDkqxyjService {

    @Autowired
    private DkqxyjMapper dkqxyjMapper;
    @Override
    public void initData(String userName) {
        dkqxyjMapper.initData(userName);
    }
}
