package org.cmms.modules.xyjlcx.hmdgl.hmdgl.service.impl;


import org.cmms.modules.xyjlcx.hmdgl.hmdgl.entity.Hmdgl;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.mapper.HmdglMapper;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.service.IHmdglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 黑名单管理
 * @Author: jeecg-boot
 * @Date:   2021-08-04
 * @Version: V1.0
 */
@Service
public class HmdglServiceImpl extends ServiceImpl<HmdglMapper, Hmdgl> implements IHmdglService {
    @Override
    public void pHmdgl() {
        baseMapper.pHmdgl();
    }
}
