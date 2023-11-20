package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztTjfx;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.XxnyztTjfxMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IXxnyztTjfxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 新型农业主体-统计分析
 * @Author: jeecg-boot
 * @Date:   2022-12-25
 * @Version: V1.0
 */
@Service
public class XxnyztTjfxServiceImpl extends ServiceImpl<XxnyztTjfxMapper, XxnyztTjfx> implements IXxnyztTjfxService {

    @Override
    public IPage<XxnyztTjfx> tjfx1(Page page, String yggh) {
        return baseMapper.tjfx1(page,yggh);
    }
    @Override
    public List<XxnyztTjfx> tjfx1(String yggh) {
        return baseMapper.tjfx1(yggh);
    }

    @Override
    public IPage<XxnyztTjfx> tjfx2And4(Page page, String sszh) {
        return baseMapper.tjfx2And4(page,sszh);
    }
    @Override
    public List<XxnyztTjfx> tjfx2And4(String sszh) {
        return baseMapper.tjfx2And4(sszh);
    }

    @Override
    public IPage<XxnyztTjfx> tjfx3(Page page,String sszh) {
        return baseMapper.tjfx3(page,sszh);
    }
    @Override
    public List<XxnyztTjfx> tjfx3(String sszh) {
        return baseMapper.tjfx3(sszh);
    }

    @Override
    public IPage<XxnyztTjfx> tjfx4(Page page) {
        return baseMapper.tjfx4(page);
    }

    @Override
    public List<XxnyztTjfx> tjfx4() {
        return baseMapper.tjfx4();
    }

    @Override
    public void updateClje() {
        baseMapper.updateClje();
    }

    @Override
    public void updateClje1() {
        baseMapper.updateClje1();
    }

    @Override
    public void updateClje2() {
        baseMapper.updateClje2();
    }

    @Override
    public void updateClje3() {
        baseMapper.updateClje3();
    }

    @Override
    public void updateClje4() {
        baseMapper.updateClje4();
    }

    @Override
    public void updateClje5() {
        baseMapper.updateClje5();
    }

    @Override
    public void updateClje6() {
        baseMapper.updateClje6();
    }

    @Override
    public void initSfysx() {
        baseMapper.initSfysx();
    }

    @Override
    public void xxnyztTjfx() {
        baseMapper.xxnyztTjfx();
    }
}
