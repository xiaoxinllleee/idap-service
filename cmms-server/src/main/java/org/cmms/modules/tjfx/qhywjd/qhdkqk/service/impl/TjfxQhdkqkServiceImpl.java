package org.cmms.modules.tjfx.qhywjd.qhdkqk.service.impl;

import org.cmms.modules.tjfx.qhywjd.qhdkqk.entity.TjfxQhdkqk;
import org.cmms.modules.tjfx.qhywjd.qhdkqk.mapper.TjfxQhdkqkMapper;
import org.cmms.modules.tjfx.qhywjd.qhdkqk.service.ITjfxQhdkqkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 全行贷款情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Service
public class TjfxQhdkqkServiceImpl extends ServiceImpl<TjfxQhdkqkMapper, TjfxQhdkqk> implements ITjfxQhdkqkService {

    @Override
    public void initData(String sjrq,String yggh) {
        baseMapper.initData(sjrq,yggh);
    }

    @Override
    public Date getMaxDate() {
        return baseMapper.getMaxDate();
    }

    @Override
    public TjfxQhdkqk getHjDate(String sjrq, String sszh) {
        return baseMapper.getHjDate(sjrq,sszh);
    }
}
