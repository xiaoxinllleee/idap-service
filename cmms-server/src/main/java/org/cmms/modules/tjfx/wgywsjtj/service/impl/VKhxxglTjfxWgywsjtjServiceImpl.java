package org.cmms.modules.tjfx.wgywsjtj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import org.cmms.modules.tjfx.wgywsjtj.entity.VKhxxglTjfxWgywsjtj;
import org.cmms.modules.tjfx.wgywsjtj.mapper.VKhxxglTjfxWgywsjtjMapper;
import org.cmms.modules.tjfx.wgywsjtj.service.IVKhxxglTjfxWgywsjtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 网格业务数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
@Service
public class VKhxxglTjfxWgywsjtjServiceImpl extends ServiceImpl<VKhxxglTjfxWgywsjtjMapper, VKhxxglTjfxWgywsjtj> implements IVKhxxglTjfxWgywsjtjService {

    @Override
    public IPage<Kjbxx> getKjbxxListByWgbh(Page page, String wgbh) {
        return baseMapper.getKjbxxListByWgbh(page,wgbh);
    }

    @Override
    public List<Kjbxx> getKjbxxListByWgbh(String wgbh) {
        return baseMapper.getKjbxxListByWgbh(wgbh);
    }
}
