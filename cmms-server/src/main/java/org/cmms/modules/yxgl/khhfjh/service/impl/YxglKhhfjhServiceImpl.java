package org.cmms.modules.yxgl.khhfjh.service.impl;

import org.cmms.modules.yxgl.khhfjh.entity.YxglKhhfjh;
import org.cmms.modules.yxgl.khhfjh.mapper.YxglKhhfjhMapper;
import org.cmms.modules.yxgl.khhfjh.service.IYxglKhhfjhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 客户回访计划
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
@Service
public class YxglKhhfjhServiceImpl extends ServiceImpl<YxglKhhfjhMapper, YxglKhhfjh> implements IYxglKhhfjhService {

    @Autowired
    YxglKhhfjhMapper yxglKhhfjhMapper;

    @Override
    public void extract(Map<String,String> sql){
        yxglKhhfjhMapper.extract(sql);
    }

}
