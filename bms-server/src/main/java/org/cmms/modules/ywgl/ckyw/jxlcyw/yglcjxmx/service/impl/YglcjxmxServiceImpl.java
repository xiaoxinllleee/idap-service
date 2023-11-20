package org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.service.impl;

import org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.entity.Yglcjxmx;
import org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.mapper.YglcjxmxMapper;
import org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.service.IYglcjxmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工揽储绩效明细
 * @Author: jeecg-boot
 * @Date:   2021-10-27
 * @Version: V1.0
 */
@Service
public class YglcjxmxServiceImpl extends ServiceImpl<YglcjxmxMapper, Yglcjxmx> implements IYglcjxmxService {

    @Override
    public void pYglcjxmx(String tjyf) {
        baseMapper.pYglcjxmx(tjyf);
    }
}
