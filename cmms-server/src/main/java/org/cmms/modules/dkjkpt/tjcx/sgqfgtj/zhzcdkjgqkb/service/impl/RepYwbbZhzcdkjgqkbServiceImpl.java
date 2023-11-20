package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.service.impl;

import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.entity.RepYwbbZhzcdkjgqkb;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.mapper.RepYwbbZhzcdkjgqkbMapper;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.service.IRepYwbbZhzcdkjgqkbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 支行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Service
public class RepYwbbZhzcdkjgqkbServiceImpl extends ServiceImpl<RepYwbbZhzcdkjgqkbMapper, RepYwbbZhzcdkjgqkb> implements IRepYwbbZhzcdkjgqkbService {
    @Autowired
    private RepYwbbZhzcdkjgqkbMapper repYwbbZhzcdkjgqkbMapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        repYwbbZhzcdkjgqkbMapper.extract(tjyf);
    }
}
