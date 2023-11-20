package org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.service.impl;

import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.entity.DkjkptByxjbl;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.mapper.DkjkptByxjblMapper;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.service.IDkjkptByxjblService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 本月新进不良
 * @Author: jeecg-boot
 * @Date:   2023-08-30
 * @Version: V1.0
 */
@Service
public class DkjkptByxjblServiceImpl extends ServiceImpl<DkjkptByxjblMapper, DkjkptByxjbl> implements IDkjkptByxjblService {

    @Override
    public void extract(String tjyf) {
        baseMapper.extract(tjyf);
    }
}
