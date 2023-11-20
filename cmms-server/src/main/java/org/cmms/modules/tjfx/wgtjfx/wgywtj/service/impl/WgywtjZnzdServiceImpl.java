package org.cmms.modules.tjfx.wgtjfx.wgywtj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.DzyhByjzVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.PyxxVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.Wgywtj;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.ZnzdVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.mapper.WgywtjMapper;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.service.IWgywtjService;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.service.IWgywtjZnzdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 网格业务统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@Service
public class WgywtjZnzdServiceImpl extends ServiceImpl<WgywtjMapper, Wgywtj> implements IWgywtjZnzdService {
    @Autowired
    private WgywtjMapper wgywtjMapper;

    @Override
    public Integer getZnzdHs(String tableName,List<String> wgbhList) {
        return wgywtjMapper.getZnzdHs(tableName,wgbhList);
    }

    @Override
    public String getNnzdTableName() {
        return wgywtjMapper.getNnzdTableName();
    }

    @Override
    public IPage<ZnzdVo> getZnzdInfoByWgbh(Page page,String tableName, List<String> wgbhList) {
        return wgywtjMapper.getZnzdInfoByWgbh(page,tableName,wgbhList);
    }
}
