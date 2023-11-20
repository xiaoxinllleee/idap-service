package org.cmms.modules.pad.shxxgl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.pad.shxxgl.entity.Fxezh;
import org.cmms.modules.pad.shxxgl.mapper.FxezhMapper;
import org.cmms.modules.pad.shxxgl.service.IFxezhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 福祥E支付
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Service
public class FxezhServiceImpl extends ServiceImpl<FxezhMapper, Fxezh> implements IFxezhService {

    @Override
    public IPage<Fxezh> getByWgbh(Page page, String wgbh) {
        return baseMapper.getByWgbh(page,wgbh);
    }

    @Override
    public List<Fxezh> getByWgbhList(String wgbh) {
        return baseMapper.getByWgbhList(wgbh);
    }

    @Override
    public IPage<Fxezh> getByWgbhTy(Page page, List<String> wgbhList) {
        return baseMapper.getByWgbhTy(page,wgbhList);
    }
}


