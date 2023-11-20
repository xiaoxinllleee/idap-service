package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.entity.QhBnbldkJgqkb1;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.mapper.QhBnbldkJgqkb1Mapper;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.service.IQhBnbldkJgqkb1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 全行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Service
public class QhBnbldkJgqkb1ServiceImpl extends ServiceImpl<QhBnbldkJgqkb1Mapper, QhBnbldkJgqkb1> implements IQhBnbldkJgqkb1Service {

    @Autowired
    private QhBnbldkJgqkb1Mapper qhBnbldkJgqkb1Mapper;

    @Override
    @Transactional
    public void extract(String tjyf) {
        qhBnbldkJgqkb1Mapper.extract(tjyf);
    }

}
