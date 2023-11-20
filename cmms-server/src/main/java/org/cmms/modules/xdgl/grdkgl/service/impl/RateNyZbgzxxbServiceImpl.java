package org.cmms.modules.xdgl.grdkgl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;
import org.cmms.modules.xdgl.grdkgl.mapper.RateZbgzxxbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IRateNyZbgzxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 指标工资信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Service
public class RateNyZbgzxxbServiceImpl extends ServiceImpl<RateZbgzxxbMapper, RateZbgzxxb> implements IRateNyZbgzxxbService {

    @Autowired
    RateZbgzxxbMapper rateZbgzxxbMapper;

    @Override
    public RateZbgzxxb queryzbgzxxb(String qydm,String zbid,String zbgzid){
        return rateZbgzxxbMapper.queryzbgzxxb(qydm,zbid,zbgzid);
    }

    @Override
    public List<RateZbgzxxb> selectList(QueryWrapper<RateZbgzxxb> queryWrapper2) {


        return  rateZbgzxxbMapper.selectList(queryWrapper2);
    }

}
