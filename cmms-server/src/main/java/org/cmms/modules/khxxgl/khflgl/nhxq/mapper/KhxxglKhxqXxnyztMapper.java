package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.AntSelectOptions;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglKhxqXxnyzt;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztTjfx2;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztYxzfVo;

/**
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2022-12-03
 * @Version: V1.0
 */
public interface KhxxglKhxqXxnyztMapper extends BaseMapper<KhxxglKhxqXxnyzt> {

    IPage<XxnyztTjfx2> getTjfx2(Page page,@Param("dao") KhxxglKhxqXxnyzt khxxglKhxqXxnyzt);
    List<XxnyztTjfx2> getTjfx2exp(@Param("dao") KhxxglKhxqXxnyzt khxxglKhxqXxnyzt);

    List<AntSelectOptions> getAllGhzrr();
    void xxnyztYxzfInit(@Param("id")String id,@Param("yggh")String yggh);

    List<XxnyztYxzfVo> getZfzbxxByXxnyztId(@Param("id") String id);

    IPage<XxnyztTjfx2> getTjfx2Qy(Page page,@Param("dao") KhxxglKhxqXxnyzt khxxglKhxqXxnyzt);
    List<XxnyztTjfx2> getTjfx2expQy(@Param("dao") KhxxglKhxqXxnyzt khxxglKhxqXxnyzt);

}
