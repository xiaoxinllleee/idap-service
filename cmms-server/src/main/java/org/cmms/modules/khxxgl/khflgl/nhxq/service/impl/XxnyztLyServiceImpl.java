package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.LyXxnyztTjfxJCBVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.LyXxnyztTjfxVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztLy;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.XxnyztLyMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IXxnyztLyService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 新型农业主体浏阳
 * @Author: jeecg-boot
 * @Date:   2023-06-30
 * @Version: V1.0
 */
@Service
public class XxnyztLyServiceImpl extends ServiceImpl<XxnyztLyMapper, XxnyztLy> implements IXxnyztLyService {

    @Override
    public IPage<LyXxnyztTjfxVO> tjfx(Page page, String sszh,String cxlx) {
        return baseMapper.tjfx(page, sszh,cxlx);
    }

    @Override
    public List<LyXxnyztTjfxVO> tjfxList( String sszh,String cxlx) {
        return baseMapper.tjfxList(sszh,cxlx);
    }

    @Override
    public IPage<LyXxnyztTjfxJCBVO> tjfx2(Page page, LyXxnyztTjfxJCBVO jcbvo) {
        return baseMapper.tjfx2(page, jcbvo);
    }

    @Override
    public List<LyXxnyztTjfxJCBVO> tjfx2List(LyXxnyztTjfxJCBVO jcbvo) {
        return baseMapper.tjfx2List(jcbvo);
    }

    @Override
    public void tq() {
        baseMapper.tq();
    }

    @Override
    public void updateSxbscsj(String id) {
        baseMapper.updateSxbscsj(id);
    }
}
