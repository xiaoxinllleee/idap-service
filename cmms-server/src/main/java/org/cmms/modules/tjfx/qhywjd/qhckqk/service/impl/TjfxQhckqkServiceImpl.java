package org.cmms.modules.tjfx.qhywjd.qhckqk.service.impl;

import org.cmms.modules.tjfx.qhywjd.qhckqk.entity.TjfxQhckqk;
import org.cmms.modules.tjfx.qhywjd.qhckqk.mapper.TjfxQhckqkMapper;
import org.cmms.modules.tjfx.qhywjd.qhckqk.service.ITjfxQhckqkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 全行存款情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Service
public class TjfxQhckqkServiceImpl extends ServiceImpl<TjfxQhckqkMapper, TjfxQhckqk> implements ITjfxQhckqkService {

    @Override
    public void initData(String sjrq,String yggh) {
        baseMapper.initData(sjrq,yggh);
    }

    @Override
    public Date getMaxDate() {
        return baseMapper.getMaxDate();
    }

    @Override
    public TjfxQhckqk getHjDate(String sjrq,String sszh) {
        return baseMapper.getHjDate(sjrq,sszh);
    }
}
