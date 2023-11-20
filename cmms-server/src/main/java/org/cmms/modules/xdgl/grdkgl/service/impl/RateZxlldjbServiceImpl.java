package org.cmms.modules.xdgl.grdkgl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.xdgl.grdkgl.entity.RateZxlldjb;
import org.cmms.modules.xdgl.grdkgl.mapper.RateZxlldjbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IRateZxlldjbService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 执行利率定价表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Service
public class RateZxlldjbServiceImpl extends ServiceImpl<RateZxlldjbMapper, RateZxlldjb> implements IRateZxlldjbService {


    @Override
    public  RateZxlldjb queryzxlldjb(String zjhm, Date djnf){
        return baseMapper.queryzxlldjb(zjhm,djnf);
    }

    @Override
    public int updateSpzt(String djid, String spzt, String note) {
        return baseMapper.updateSpzt(djid,spzt,note);
    }

    @Override
    @Cacheable(value = "rateZxlldjb" ,key = "#queryWrapper.toString()")
    public List<RateZxlldjb> listByWrapper(QueryWrapper queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }
}
