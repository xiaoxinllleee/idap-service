package org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfMxVo;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfsjtj;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.mapper.PfrZfsjtjMapper;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.service.IPfrZfsjtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 陪访人走访数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-29
 * @Version: V1.0
 */
@Service
public class PfrZfsjtjServiceImpl extends ServiceImpl<PfrZfsjtjMapper, PfrZfsjtj> implements IPfrZfsjtjService {

    @Override
    public IPage<PfrZfMxVo> getPfrZfMxNhPage(Page page, String lx, String tjrq, String gwbz, String yggh,String weekFristDay) {
        return baseMapper.getPfrZfMxNhPage(page,lx,tjrq,gwbz,yggh,weekFristDay);
    }

    @Override
    public IPage<PfrZfMxVo> getPfrZfMxShPage(Page page,String lx, String tjrq, String gwbz, String yggh,String weekFristDay) {
        return baseMapper.getPfrZfMxShPage(page,lx,tjrq,gwbz,yggh,weekFristDay);
    }
}
