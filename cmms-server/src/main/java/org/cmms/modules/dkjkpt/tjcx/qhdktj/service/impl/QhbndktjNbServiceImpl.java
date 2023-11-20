package org.cmms.modules.dkjkpt.tjcx.qhdktj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.entity.QhbndktjNb;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.mapper.QhbndktjNbMapper;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.service.IQhbndktjNbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 全行表内贷款统计(年报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Service
@DS("dkjkpt")
public class QhbndktjNbServiceImpl extends ServiceImpl<QhbndktjNbMapper, QhbndktjNb> implements IQhbndktjNbService {

    @Autowired
    QhbndktjNbMapper qhbndktjNbMapper;

    @Override
    @Transactional
    public void extract(Map<String,String> sql){
        qhbndktjNbMapper.extract(sql);
    }
}
