package org.cmms.modules.ywgl.cdkfx.dkdqshlmx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.cdkfx.dkdqshlmx.entity.MidDmpmDkyebmxqktjT;
import org.cmms.modules.ywgl.cdkfx.dkdqshlmx.mapper.MidDmpmDkyebmxqktjTMapper;
import org.cmms.modules.ywgl.cdkfx.dkdqshlmx.service.IMidDmpmDkyebmxqktjTService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款到期收回率明细
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
@Service
public class MidDmpmDkyebmxqktjTServiceImpl extends ServiceImpl<MidDmpmDkyebmxqktjTMapper, MidDmpmDkyebmxqktjT> implements IMidDmpmDkyebmxqktjTService {

    @Override
    public IPage<MidDmpmDkyebmxqktjT> getList(Page<MidDmpmDkyebmxqktjT> page, Wrapper wrapper, String hxrq) {
       // baseMapper.getList(page,wrapper,)
        return baseMapper.getList(page,wrapper,hxrq);
    }
}
