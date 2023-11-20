package org.cmms.modules.dkjkpt.tjcx.zhdktj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjXb;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.mapper.ZhbndktjXbMapper;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.service.IZhbndktjXbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 支行表内贷款统计(旬报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Service
@DS("dkjkpt")
public class ZhbndktjXbServiceImpl extends ServiceImpl<ZhbndktjXbMapper, ZhbndktjXb> implements IZhbndktjXbService {

    @Autowired
    ZhbndktjXbMapper zhbndktjXbMapper;

    @Override
    @Transactional
    public void extract(Map<String,String>sql){
        zhbndktjXbMapper.extract(sql);
    }

}
