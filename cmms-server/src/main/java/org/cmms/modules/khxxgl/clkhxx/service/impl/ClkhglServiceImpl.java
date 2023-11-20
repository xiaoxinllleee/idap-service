package org.cmms.modules.khxxgl.clkhxx.service.impl;

import org.cmms.modules.khxxgl.clkhxx.entity.Clkhgl;
import org.cmms.modules.khxxgl.clkhxx.mapper.ClkhglMapper;
import org.cmms.modules.khxxgl.clkhxx.service.IClkhglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存量客户管理
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Service
public class ClkhglServiceImpl extends ServiceImpl<ClkhglMapper, Clkhgl> implements IClkhglService {
    @Override
    public void extract() {
        baseMapper.extract();
    }

    @Override
    public int syncYesClkhxx() {
        return baseMapper.syncYesClkhxx();
    }

}
