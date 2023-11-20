package org.cmms.modules.tjfx.wgtjfx.wgywtj.service.impl;

import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.DzyhByjzVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.PyxxVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.WgxxVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.Wgywtj;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.mapper.WgywtjMapper;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.service.IWgywtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 网格业务统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@Service
public class WgywtjServiceImpl extends ServiceImpl<WgywtjMapper, Wgywtj> implements IWgywtjService {
    @Autowired
    private WgywtjMapper wgywtjMapper;

    public Wgywtj getWgywxxByWgbh(String wgbh) {
        return wgywtjMapper.getWgywxxByWgbh(wgbh);
    }

    public Wgywtj getWgywxxByWgbhTy(List<String> wgbhList) {
        return wgywtjMapper.getWgywxxByWgbhTy(wgbhList);
    }

    @Override
    public int getFxezf(List<String> wgbhList) {
        return wgywtjMapper.getFxezf(wgbhList);
    }

    @Override
    public DzyhByjzVo getDzyhByjz(List<String> wgbhList) {
        return wgywtjMapper.getDzyhByjz(wgbhList);
    }

    @Override
    public PyxxVo getPyxx(List<String> wgbhList) {
        return wgywtjMapper.getPyxx(wgbhList);
    }

    @Override
    public Integer getZnzdHs(String tableName,List<String> wgbhList) {
        return wgywtjMapper.getZnzdHs(tableName,wgbhList);
    }

    @Override
    public List<WgxxVo> getWgxxToNum(List<String> wgbhList) {
        return wgywtjMapper.getWgxxToNum(wgbhList);
    }

    @Override
    public Integer getCjdxrsToWgxx(List<String> wgbhList) {
        return wgywtjMapper.getCjdxrsToWgxx(wgbhList);
    }
}
