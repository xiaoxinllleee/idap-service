package org.cmms.modules.dkjkpt.tjcx.zhdktj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjYb;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.mapper.ZhbndktjYbMapper;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.service.IZhbndktjYbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 支行表内贷款统计(月报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Service
@DS("dkjkpt")
public class ZhbndktjYbServiceImpl extends ServiceImpl<ZhbndktjYbMapper, ZhbndktjYb> implements IZhbndktjYbService {

    @Autowired
    ZhbndktjYbMapper zhbndktjYbMapper;

    @Override
    @Transactional
    public void extract(Map<String,String>sql){
        zhbndktjYbMapper.extract(sql);
    }

  /*  @Override
    @Transactional
    public void extract(Map<Object,Object>sql){
        zhbndktjYbMapper.extract(sql);
    }*/
}
