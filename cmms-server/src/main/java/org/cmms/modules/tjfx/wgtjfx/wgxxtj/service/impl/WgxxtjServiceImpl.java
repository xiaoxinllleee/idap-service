package org.cmms.modules.tjfx.wgtjfx.wgxxtj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.Wgxxtj;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.WgxxtjVo;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.ZfcjxxVo;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.mapper.WgxxtjMapper;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.service.IWgxxtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 网格信息统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@Service
public class WgxxtjServiceImpl extends ServiceImpl<WgxxtjMapper, Wgxxtj> implements IWgxxtjService {

    public Wgxxtj getWgxxByWgbh(String wgbh) {
        return baseMapper.getWgxxByWgbh(wgbh);
    }

    public Wgxxtj getWgxxByWgbhTy(List<String> wgbhList) {
        return baseMapper.getWgxxByWgbhTy(wgbhList);
    }

    @Override
    public IPage<WgxxtjVo> jbxxCs(Page page, String cs,List<String> wgbhList) {
        return baseMapper.jbxxCs(page,cs,wgbhList);
    }

    @Override
    public IPage<WgxxtjVo> wbsbk(Page page, List<String> wgbhList) {
        return baseMapper.wbsbk(page, wgbhList);
    }

    @Override
    public List<WgxxtjVo> wbsbk(List<String> wgbhList) {
        return baseMapper.wbsbk(wgbhList);
    }


    @Override
    public IPage<WgxxtjVo> tpjjch(Page page, List<String> wgbhList) {
        return baseMapper.tpjjch(page, wgbhList);
    }

    @Override
    public   IPage<WgxxtjVo> getSarsByWgbh(Page page,List<String> wgbhList) {
        return baseMapper.getSarsByWgbh(page,wgbhList);
    }

    @Override
    public List<ZfcjxxVo> getPyxxPh(List<String> wgbhList,String sxtlx) {
        return baseMapper.getPyxxPh(wgbhList,sxtlx);
    }
}
