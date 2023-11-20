package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.LyXxnyztTjfxJCBVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.LyXxnyztTjfxVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztLy;

import java.util.List;

/**
 * @Description: 新型农业主体浏阳
 * @Author: jeecg-boot
 * @Date:   2023-06-30
 * @Version: V1.0
 */
public interface IXxnyztLyService extends IService<XxnyztLy> {

    IPage<LyXxnyztTjfxVO> tjfx(Page page,String sszh,String cxlx);
    List<LyXxnyztTjfxVO> tjfxList( String sszh,String cxlx);
    IPage<LyXxnyztTjfxJCBVO> tjfx2(Page page, LyXxnyztTjfxJCBVO jcbvo);
    List<LyXxnyztTjfxJCBVO> tjfx2List(LyXxnyztTjfxJCBVO jcbvo);
    void tq();
    void updateSxbscsj(String id);
}
