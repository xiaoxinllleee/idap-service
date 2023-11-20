package org.cmms.modules.dklldj.lldjgl.lldjjs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.dklldj.lldjgl.lldjjs.entity.RateZxlldjxx;
import org.cmms.modules.dklldj.lldjgl.lldjjs.mapper.RateZxlldjxxMapper;
import org.cmms.modules.dklldj.lldjgl.lldjjs.service.IRateZxlldjxxService;
import org.cmms.modules.sjxf.hxxt.zzzhzb.entity.Zzzhzb;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RateZxlldjxxServiceImpl extends ServiceImpl<RateZxlldjxxMapper, RateZxlldjxx> implements IRateZxlldjxxService {

    @Override
    public String getMaxDjidHive() {
        return baseMapper.getMaxDjidHive();
    }

}
