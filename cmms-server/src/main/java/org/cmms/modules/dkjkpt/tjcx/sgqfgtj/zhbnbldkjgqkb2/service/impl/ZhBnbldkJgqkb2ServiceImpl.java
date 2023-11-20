package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb2.service.impl;

import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb2.entity.ZhBnbldkJgqkb2;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb2.mapper.ZhBnbldkJgqkb2Mapper;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb2.service.IZhBnbldkJgqkb2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 支行表内不良贷款结构情况表2
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Service
public class ZhBnbldkJgqkb2ServiceImpl extends ServiceImpl<ZhBnbldkJgqkb2Mapper, ZhBnbldkJgqkb2> implements IZhBnbldkJgqkb2Service {
    @Autowired
    private ZhBnbldkJgqkb2Mapper zhBnbldkJgqkb2Mapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        zhBnbldkJgqkb2Mapper.extract(tjyf);
    }

}
