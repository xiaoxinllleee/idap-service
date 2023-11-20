package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.service.impl;

import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.entity.ZhBnbldkJgqkb1;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.mapper.ZhBnbldkJgqkb1Mapper;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.service.IZhBnbldkJgqkb1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 支行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Service
public class ZhBnbldkJgqkb1ServiceImpl extends ServiceImpl<ZhBnbldkJgqkb1Mapper, ZhBnbldkJgqkb1> implements IZhBnbldkJgqkb1Service {
    @Autowired
    private ZhBnbldkJgqkb1Mapper zhBnbldkJgqkb1Mapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        zhBnbldkJgqkb1Mapper.extract(tjyf);
    }
}
