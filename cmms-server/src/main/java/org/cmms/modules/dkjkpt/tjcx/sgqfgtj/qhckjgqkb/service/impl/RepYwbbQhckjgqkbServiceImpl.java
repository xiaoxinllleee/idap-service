package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhckjgqkb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhckjgqkb.entity.RepYwbbQhckjgqkb;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhckjgqkb.mapper.RepYwbbQhckjgqkbMapper;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhckjgqkb.service.IRepYwbbQhckjgqkbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 全行存款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Service
public class RepYwbbQhckjgqkbServiceImpl extends ServiceImpl<RepYwbbQhckjgqkbMapper, RepYwbbQhckjgqkb> implements IRepYwbbQhckjgqkbService {

    @Autowired
    private RepYwbbQhckjgqkbMapper repYwbbQhckjgqkbMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        repYwbbQhckjgqkbMapper.extract(tjyf);
    }

}
