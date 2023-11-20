package org.cmms.modules.ywgl.cdkfx.xzbldkmx.service.impl;

import org.cmms.modules.ywgl.cdkfx.xzbldkmx.entity.MidDmpmDkyebmxqktj;
import org.cmms.modules.ywgl.cdkfx.xzbldkmx.mapper.MidDmpmDkyebmxqktjMapper;
import org.cmms.modules.ywgl.cdkfx.xzbldkmx.service.IMidDmpmDkyebmxqktjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 新增不良贷款明细
 * @Author: jeecg-boot
 * @Date:   2021-06-23
 * @Version: V1.0
 */
@Service
public class MidDmpmDkyebmxqktjServiceImpl extends ServiceImpl<MidDmpmDkyebmxqktjMapper, MidDmpmDkyebmxqktj> implements IMidDmpmDkyebmxqktjService {

    @Override
    public String getByName(String custidName) {
        return baseMapper.getByName(custidName);
    }
}
