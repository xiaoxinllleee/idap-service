package org.cmms.modules.xdgl.nsb.service.impl;

import org.cmms.modules.xdgl.nsb.entity.NsbTjfx;
import org.cmms.modules.xdgl.nsb.mapper.NsbTjfxMapper;
import org.cmms.modules.xdgl.nsb.service.INsbTjfxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 年审统计分析
 * @Author: jeecg-boot
 * @Date:   2023-07-04
 * @Version: V1.0
 */
@Service
public class NsbTjfxServiceImpl extends ServiceImpl<NsbTjfxMapper, NsbTjfx> implements INsbTjfxService {

    @Override
    public void tq() {
        baseMapper.tq();
    }
}
