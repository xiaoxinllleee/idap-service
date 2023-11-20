package org.cmms.modules.dkjkpt.dksjjk.dedkjc.service.impl;

import org.cmms.modules.dkjkpt.dksjjk.dedkjc.entity.DkjkptDedkjc;
import org.cmms.modules.dkjkpt.dksjjk.dedkjc.mapper.DkjkptDedkjcMapper;
import org.cmms.modules.dkjkpt.dksjjk.dedkjc.service.IDkjkptDedkjcService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 大额贷款监测
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Service
public class DkjkptDedkjcServiceImpl extends ServiceImpl<DkjkptDedkjcMapper, DkjkptDedkjc> implements IDkjkptDedkjcService {

    @Override
    public void extract(String tjyf) {
        baseMapper.extract(tjyf);
    }
}
