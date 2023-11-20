package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.service.impl;

import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.entity.RepYwbbZhckjgqkb;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.mapper.RepYwbbZhckjgqkbMapper;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.service.IRepYwbbZhckjgqkbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 支行存款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Service
public class RepYwbbZhckjgqkbServiceImpl extends ServiceImpl<RepYwbbZhckjgqkbMapper, RepYwbbZhckjgqkb> implements IRepYwbbZhckjgqkbService {

    @Autowired
    private RepYwbbZhckjgqkbMapper repYwbbZhckjgqkbMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        repYwbbZhckjgqkbMapper.extract(tjyf);
    }
}
