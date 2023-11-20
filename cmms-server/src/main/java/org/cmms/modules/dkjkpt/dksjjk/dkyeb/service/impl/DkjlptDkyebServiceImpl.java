package org.cmms.modules.dkjkpt.dksjjk.dkyeb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dkyeb.entity.DkjlptDkyeb;
import org.cmms.modules.dkjkpt.dksjjk.dkyeb.mapper.DkjlptDkyebMapper;
import org.cmms.modules.dkjkpt.dksjjk.dkyeb.service.IDkjlptDkyebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款余额表
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Service
public class DkjlptDkyebServiceImpl extends ServiceImpl<DkjlptDkyebMapper, DkjlptDkyeb> implements IDkjlptDkyebService {

    @Autowired
    private DkjlptDkyebMapper mapper;

    @Override
    public void InitData(String tjrq) {
        mapper.InitData(tjrq);
    }
}
