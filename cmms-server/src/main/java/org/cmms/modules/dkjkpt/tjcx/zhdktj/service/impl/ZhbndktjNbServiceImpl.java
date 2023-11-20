package org.cmms.modules.dkjkpt.tjcx.zhdktj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjNb;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.mapper.ZhbndktjNbMapper;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.service.IZhbndktjNbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description: 支行表内贷款统计(年报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Service
@DS("dkjkpt")
public class ZhbndktjNbServiceImpl extends ServiceImpl<ZhbndktjNbMapper, ZhbndktjNb> implements IZhbndktjNbService {

    @Autowired
    ZhbndktjNbMapper zhbndktjNbMapper;

    @Override
    @Transactional
    public void extract(Map<String,String> sql){
        zhbndktjNbMapper.extract(sql);
    }

}
