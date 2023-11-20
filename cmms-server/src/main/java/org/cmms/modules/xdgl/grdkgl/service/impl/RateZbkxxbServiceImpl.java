package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.RateZbkxxb;
import org.cmms.modules.xdgl.grdkgl.mapper.RateZbkxxbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IRateZbkxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 指标库信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Service
public class RateZbkxxbServiceImpl extends ServiceImpl<RateZbkxxbMapper, RateZbkxxb> implements IRateZbkxxbService {

    @Autowired
    RateZbkxxbMapper rateZbkxxbMapper;

    @Override
    public List<RateZbkxxb> queryzbkxxb(String qydm){
        return rateZbkxxbMapper.queryzbkxxb(qydm);
    }

}
