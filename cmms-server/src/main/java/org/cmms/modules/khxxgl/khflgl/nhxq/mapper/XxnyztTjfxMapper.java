package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztTjfx;

/**
 * @Description: 新型农业主体-统计分析
 * @Author: jeecg-boot
 * @Date:   2022-12-25
 * @Version: V1.0
 */
public interface XxnyztTjfxMapper extends BaseMapper<XxnyztTjfx> {

    IPage<XxnyztTjfx> tjfx1(Page page,@Param("yggh") String yggh);
    List<XxnyztTjfx> tjfx1(@Param("yggh") String yggh);//导出
    IPage<XxnyztTjfx> tjfx2And4(Page page,@Param("sszh") String sszh);
    List<XxnyztTjfx> tjfx2And4(@Param("sszh") String sszh);//导出
    IPage<XxnyztTjfx> tjfx3(Page page,@Param("sszh") String sszh);
    List<XxnyztTjfx> tjfx3(@Param("sszh") String sszh);//导出
    IPage<XxnyztTjfx> tjfx4(Page page);
    List<XxnyztTjfx> tjfx4();//导出
    void updateClje();
    void updateClje1();
    void updateClje2();
    void updateClje3();
    void updateClje4();
    void updateClje5();
    void updateClje6();
    void initSfysx();
    void xxnyztTjfx();
}
