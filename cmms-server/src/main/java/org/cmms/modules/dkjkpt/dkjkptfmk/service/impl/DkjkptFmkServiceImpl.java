package org.cmms.modules.dkjkpt.dkjkptfmk.service.impl;

import org.cmms.modules.dkjkpt.dkjkptfmk.entity.DkjkptFmk;
import org.cmms.modules.dkjkpt.dkjkptfmk.mapper.DkjkptFmkMapper;
import org.cmms.modules.dkjkpt.dkjkptfmk.service.IDkjkptFmkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 福民卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
@Service
public class DkjkptFmkServiceImpl extends ServiceImpl<DkjkptFmkMapper, DkjkptFmk> implements IDkjkptFmkService {
    public void fmkUpdate() {
        baseMapper.fmkUpdate();
    }
}
