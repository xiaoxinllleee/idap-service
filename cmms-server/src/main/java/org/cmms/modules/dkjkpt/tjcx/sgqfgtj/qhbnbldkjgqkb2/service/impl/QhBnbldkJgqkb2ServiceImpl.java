package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb2.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb2.entity.QhBnbldkJgqkb2;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb2.mapper.QhBnbldkJgqkb2Mapper;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb2.service.IQhBnbldkJgqkb2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 全行表内不良贷款结构情况表2
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Service
public class QhBnbldkJgqkb2ServiceImpl extends ServiceImpl<QhBnbldkJgqkb2Mapper, QhBnbldkJgqkb2> implements IQhBnbldkJgqkb2Service {

    @Autowired
    private QhBnbldkJgqkb2Mapper qhBnbldkJgqkb2Mapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        qhBnbldkJgqkb2Mapper.extract(tjyf);
    }
}
