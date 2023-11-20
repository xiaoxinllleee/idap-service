package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.entity.RepYwbbQhzcdkjgqkb;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.mapper.RepYwbbQhzcdkjgqkbMapper;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.service.IRepYwbbQhzcdkjgqkbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 全行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Service
public class RepYwbbQhzcdkjgqkbServiceImpl extends ServiceImpl<RepYwbbQhzcdkjgqkbMapper, RepYwbbQhzcdkjgqkb> implements IRepYwbbQhzcdkjgqkbService {

    @Autowired
    private RepYwbbQhzcdkjgqkbMapper repYwbbQhzcdkjgqkbMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        repYwbbQhzcdkjgqkbMapper.extract(tjyf);
    }
}
