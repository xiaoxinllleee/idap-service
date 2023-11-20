package org.cmms.modules.dkjkpt.tjcx.qhdktj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.entity.QhbndktjXb;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.mapper.QhbndktjXbMapper;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.service.IQhbndktjXbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 全行表内贷款统计(旬报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Service
@DS("dkjkpt")
public class QhbndktjXbServiceImpl extends ServiceImpl<QhbndktjXbMapper, QhbndktjXb> implements IQhbndktjXbService {

    @Autowired
    QhbndktjXbMapper qhbndktjXbMapper;

    @Override
    @Transactional
    public void extract(Map<String,String> sql){
        qhbndktjXbMapper.extract(sql);
    }

}
