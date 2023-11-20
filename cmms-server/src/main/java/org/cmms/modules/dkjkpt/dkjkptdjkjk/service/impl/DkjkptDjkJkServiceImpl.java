package org.cmms.modules.dkjkpt.dkjkptdjkjk.service.impl;

import org.cmms.modules.dkjkpt.dkjkptdjkjk.entity.DkjkptDjkJk;
import org.cmms.modules.dkjkpt.dkjkptdjkjk.mapper.DkjkptDjkJkMapper;
import org.cmms.modules.dkjkpt.dkjkptdjkjk.service.IDkjkptDjkJkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 金卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
@Service
public class DkjkptDjkJkServiceImpl extends ServiceImpl<DkjkptDjkJkMapper, DkjkptDjkJk> implements IDkjkptDjkJkService {
    public void djkjkUpdate() {
        baseMapper.djkjkUpdate();
    }
}
