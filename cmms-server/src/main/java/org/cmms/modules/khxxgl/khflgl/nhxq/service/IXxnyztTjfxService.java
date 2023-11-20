package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztTjfx;

import java.util.List;

/**
 * @Description: 新型农业主体-统计分析
 * @Author: jeecg-boot
 * @Date:   2022-12-25
 * @Version: V1.0
 */
public interface IXxnyztTjfxService extends IService<XxnyztTjfx> {

    IPage<XxnyztTjfx> tjfx1(Page page,String yggh);
    List<XxnyztTjfx> tjfx1(String yggh);
    IPage<XxnyztTjfx> tjfx2And4(Page page,String sszh);
    List<XxnyztTjfx> tjfx2And4(String sszh);
    IPage<XxnyztTjfx> tjfx3(Page page,String sszh);
    List<XxnyztTjfx> tjfx3(String sszh);
    IPage<XxnyztTjfx> tjfx4(Page page);
    List<XxnyztTjfx> tjfx4();
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
