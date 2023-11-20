package org.cmms.modules.dklldj.jbxxgl.glrxxgl.service.impl;

import org.cmms.modules.dklldj.jbxxgl.glrxxgl.entity.Rate_khglrxxb;
import org.cmms.modules.dklldj.jbxxgl.glrxxgl.mapper.Rate_khglrxxbMapper;
import org.cmms.modules.dklldj.jbxxgl.glrxxgl.service.IRate_khglrxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.transaction.Transactional;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-06
 * @Version: V1.0
 */
@Service
public class Rate_khglrxxbServiceImpl extends ServiceImpl<Rate_khglrxxbMapper, Rate_khglrxxb> implements IRate_khglrxxbService {
    @Autowired
    private Rate_khglrxxbMapper khglrxxbMapper;


    @Override
    @Transactional
    public void extract() {
        khglrxxbMapper.extract();
    }
}
