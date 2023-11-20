package org.cmms.modules.sjxf.qtxt.djkxt.khzlb.service.impl;

import org.cmms.modules.sjxf.qtxt.djkxt.khzlb.entity.Khzlb;
import org.cmms.modules.khgl.sjyh.entity.KhzlbVo;
import org.cmms.modules.sjxf.qtxt.djkxt.khzlb.mapper.KhzlbMapper;
import org.cmms.modules.sjxf.qtxt.djkxt.khzlb.service.IKhzlbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户资料表
 * @Author: jeecg-boot
 * @Date: 2021-12-21
 * @Version: V1.0
 */
@Service

public class KhzlbServiceImpl extends ServiceImpl<KhzlbMapper, Khzlb> implements IKhzlbService {


    @Override
    public List<Khzlb> getXykListByKhmc(String khmc, String jgdm) {
        return baseMapper.getXykListByKhmc(khmc, jgdm);
    }
}

