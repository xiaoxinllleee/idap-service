package org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.entity.TjfxZcsxjdbXzdk;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.entity.Zcsxczjdb;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.mapper.ZcsxczjdbMapper;
import org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.service.IZcsxczjdbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 整村授信村组进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
@Service
public class ZcsxczjdbServiceImpl extends ServiceImpl<ZcsxczjdbMapper, Zcsxczjdb> implements IZcsxczjdbService {

    @Override
    public IPage<TjfxHnkd> getHnkdDrxxPage(Page page, String wgbh,String lx,String type,String sszh,String sjrq,String nf,String pc) {
        return baseMapper.getHnkdDrxxPage(page,wgbh,lx,type,sszh,sjrq,nf,pc);
    }

    @Override
    public List<TjfxHnkd> getHnkdDrxxList(String wgbh,String lx,String type,String sszh,String sjrq,String nf,String pc) {
        return baseMapper.getHnkdDrxxList(wgbh,lx,type,sszh,sjrq,nf,pc);
    }


    @Override
    public IPage<TjfxZcsxjdbXzdk> getHnkdQyxxPage(Page page, String wgbh, String sjrq,String type,String sszh,String nf,String pc) {
        return baseMapper.getHnkdQyxxPage(page,wgbh,sjrq,type,sszh,nf,pc);
    }

    @Override
    public List<TjfxZcsxjdbXzdk> getHnkdQyxxList(String wgbh, String sjrq,String type,String sszh,String nf,String pc) {
        return baseMapper.getHnkdQyxxList(wgbh,sjrq,type,sszh,nf,pc);
    }

    @Override
    public IPage<TjfxZcsxjdbXzdk> getDkhtxxPage(Page page, String wgbh, String sjrq, String otherlx,String type,String sszh,String nf,String pc) {
        return baseMapper.getDkhtxxPage(page,wgbh,sjrq,otherlx,type,sszh,nf,pc);
    }

    @Override
    public List<TjfxZcsxjdbXzdk> getDkhtxxList(String wgbh, String sjrq, String otherlx,String type,String sszh,String nf,String pc) {
        return baseMapper.getDkhtxxList(wgbh,sjrq,otherlx,type,sszh,nf,pc);
    }

    @Override
    public IPage<TjfxZcsxjdbXzdk> getYXPage(Page page, String wgbh, String sjrq, String otherlx,String type,String sszh,String nf,String pc) {
        return baseMapper.getYXPage(page,wgbh,sjrq,otherlx,type,sszh,nf,pc);
    }

    @Override
    public List<TjfxZcsxjdbXzdk> getYxList(String wgbh, String sjrq, String otherlx,String type,String sszh,String nf,String pc) {
        return baseMapper.getYxList(wgbh,sjrq,otherlx,type,sszh,nf,pc);
    }
}
