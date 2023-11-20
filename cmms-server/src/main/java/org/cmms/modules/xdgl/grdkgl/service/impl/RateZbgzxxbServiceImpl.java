package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;
import org.cmms.modules.xdgl.grdkgl.mapper.RateZbgzxxbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IRateZbgzxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 指标工资信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Service
public class RateZbgzxxbServiceImpl extends ServiceImpl<RateZbgzxxbMapper, RateZbgzxxb> implements IRateZbgzxxbService {

    @Autowired
    RateZbgzxxbMapper rateZbgzxxbMapper;

    @Override
    public RateZbgzxxb queryzbgzxxb(String qydm,String zbid,String zbgzid){
        return rateZbgzxxbMapper.queryzbgzxxb(qydm,zbid,zbgzid);
    }

}
