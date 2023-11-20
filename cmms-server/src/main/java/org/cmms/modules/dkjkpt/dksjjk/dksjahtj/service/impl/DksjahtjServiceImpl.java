package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.Dksjahtj;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.mapper.DksjahtjMapper;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.service.IDksjahtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款数据按户统计
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Service
public class DksjahtjServiceImpl extends ServiceImpl<DksjahtjMapper, Dksjahtj> implements IDksjahtjService {

    @Autowired
    private DksjahtjMapper mapper;

    @Override
    public void InitData(String tjyf) {
        mapper.InitData(tjyf);
    }
}
