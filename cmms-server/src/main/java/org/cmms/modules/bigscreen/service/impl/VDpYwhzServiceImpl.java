package org.cmms.modules.bigscreen.service.impl;

import org.cmms.modules.bigscreen.entity.VDpYwhz;
import org.cmms.modules.bigscreen.entity.Xzzcsxgzjd;
import org.cmms.modules.bigscreen.entity.Xzzcsxgzjdcun;
import org.cmms.modules.bigscreen.mapper.VDpYwhzMapper;
import org.cmms.modules.bigscreen.service.IVDpYwhzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 大屏业务汇总视图
 * @Author: jeecg-boot
 * @Date:   2023-10-07
 * @Version: V1.0
 */
@Service
public class VDpYwhzServiceImpl extends ServiceImpl<VDpYwhzMapper, VDpYwhz> implements IVDpYwhzService {

    @Override
    public List<Xzzcsxgzjd> getMaxList() {
        return baseMapper.getMaxList();
    }

    @Override
    public List<Xzzcsxgzjdcun> getCunList(String wgbh) {
        return baseMapper.getCunList(wgbh);
    }
}
