package org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjgz.service.impl;

import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjgz.entity.DkjkptByxzgl;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjgz.mapper.DkjkptByxzglMapper;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjgz.service.IDkjkptByxzglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 本月新进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-30
 * @Version: V1.0
 */
@Service
public class DkjkptByxzglServiceImpl extends ServiceImpl<DkjkptByxzglMapper, DkjkptByxzgl> implements IDkjkptByxzglService {

    @Override
    public void extract(String tjyf) {
        baseMapper.extract(tjyf);
    }
}
