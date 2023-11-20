package org.cmms.modules.khxxgl.khjbzl.service.impl;

import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.mapper.KhjbzlMapper;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户画像
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Service
public class KhjbzlServiceImpl extends ServiceImpl<KhjbzlMapper, Khjbzl> implements IKhjbzlService {
    @Override
    public void extract(String tjrq) {
        baseMapper.extract(tjrq);
    }

    @Override
    public Integer getTodayBirthDayMans(String wgbh) {
        return baseMapper.getTodayBirthDayMans(wgbh);
    }

    @Override
    public int syncYesKhjbzl() {
        return baseMapper.syncYesKhjbzl();
    }
}
