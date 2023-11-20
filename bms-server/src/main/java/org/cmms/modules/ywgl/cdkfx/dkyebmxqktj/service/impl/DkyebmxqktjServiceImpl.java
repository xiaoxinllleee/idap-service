package org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.service.impl;

import org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.entity.Dkyebmxqktj;
import org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.mapper.DkyebmxqktjMapper;
import org.cmms.modules.ywgl.cdkfx.dkyebmxqktj.service.IDkyebmxqktjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 新增不良贷款明细
 * @Author: jeecg-boot
 * @Date:   2021-06-23
 * @Version: V1.0
 */
@Service
public class DkyebmxqktjServiceImpl extends ServiceImpl<DkyebmxqktjMapper, Dkyebmxqktj> implements IDkyebmxqktjService {

    @Override
    public String getByName(String custidName) {
        return baseMapper.getByName(custidName);
    }
}
