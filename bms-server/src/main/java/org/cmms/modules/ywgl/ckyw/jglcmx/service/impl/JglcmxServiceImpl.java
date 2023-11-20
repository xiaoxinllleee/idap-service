package org.cmms.modules.ywgl.ckyw.jglcmx.service.impl;

import org.cmms.modules.ywgl.ckyw.jglcmx.entity.Jglcmx;
import org.cmms.modules.ywgl.ckyw.jglcmx.mapper.JglcmxMapper;
import org.cmms.modules.ywgl.ckyw.jglcmx.service.IJglcmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 机关揽储明细
 * @Author: jeecg-boot
 * @Date:   2021-10-11
 * @Version: V1.0
 */
@Service
public class JglcmxServiceImpl extends ServiceImpl<JglcmxMapper, Jglcmx> implements IJglcmxService {

    @Override
    public void pJglcmx(String tjyf) {
        baseMapper.pJglcmx(tjyf);
    }
}
