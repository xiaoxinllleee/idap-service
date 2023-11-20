package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.AntSelectOptions;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglKhxqXxnyzt;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztTjfx2;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztYxzfVo;

import java.util.List;

/**
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2022-12-03
 * @Version: V1.0
 */
public interface IKhxxglKhxqXxnyztService extends IService<KhxxglKhxqXxnyzt> {

    IPage<XxnyztTjfx2> getTjfx2(Page page,KhxxglKhxqXxnyzt khxxglKhxqXxnyzt);
    List<XxnyztTjfx2> getTjfx2(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt);

    List<AntSelectOptions> getAllGhzrr();

    void xxnyztYxzfInit(String id,String yggh);

    List<XxnyztYxzfVo> getZfzbxxByXxnyztId(@Param("id") String id);

    IPage<XxnyztTjfx2> getTjfx2Qy(Page page,KhxxglKhxqXxnyzt khxxglKhxqXxnyzt);
    List<XxnyztTjfx2> getTjfx2Qy(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt);
}
