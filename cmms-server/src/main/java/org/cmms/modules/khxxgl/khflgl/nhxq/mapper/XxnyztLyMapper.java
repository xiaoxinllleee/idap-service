package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.LyXxnyztTjfxJCBVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.LyXxnyztTjfxVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztLy;

/**
 * @Description: 新型农业主体浏阳
 * @Author: jeecg-boot
 * @Date:   2023-06-30
 * @Version: V1.0
 */
public interface XxnyztLyMapper extends BaseMapper<XxnyztLy> {

    IPage<LyXxnyztTjfxVO> tjfx(Page page,@Param("sszh") String sszh,@Param("cxlx")String cxlx);
    List<LyXxnyztTjfxVO> tjfxList(@Param("sszh") String sszh,@Param("cxlx")String cxlx);
    IPage<LyXxnyztTjfxJCBVO> tjfx2(Page page, @Param("dao")LyXxnyztTjfxJCBVO jcbvo);
    List<LyXxnyztTjfxJCBVO> tjfx2List(@Param("dao")LyXxnyztTjfxJCBVO jcbvo);

    void tq();

    void updateSxbscsj(String id);
}
