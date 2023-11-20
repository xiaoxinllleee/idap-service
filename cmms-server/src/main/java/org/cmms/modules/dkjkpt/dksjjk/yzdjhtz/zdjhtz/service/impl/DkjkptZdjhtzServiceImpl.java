package org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.service.impl;

import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.entity.DkjkptZdjhtz;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.mapper.DkjkptZdjhtzMapper;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.service.IDkjkptZdjhtzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 已制定计划台账
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Service
public class DkjkptZdjhtzServiceImpl extends ServiceImpl<DkjkptZdjhtzMapper, DkjkptZdjhtz> implements IDkjkptZdjhtzService {

    @Override
    public void init(String tjrq) {
        baseMapper.init(tjrq);
    }
}
