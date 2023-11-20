package org.cmms.modules.sjxf.qtxt.khxxst.grkhb.service.impl;

import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity.Grkhb;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.mapper.GrkhbMapper;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.service.IGrkhbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 个人客户表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Service
public class GrkhbServiceImpl extends ServiceImpl<GrkhbMapper, Grkhb> implements IGrkhbService {

    @Override
    public Grkhb getGrkhbHive(String custid) {
        return baseMapper.getGrkhbHive(custid);
    }

    @Override
    public Grkhb getGrkhbOracle(String custid) {
        return baseMapper.getGrkhbOracle(custid);
    }

}
