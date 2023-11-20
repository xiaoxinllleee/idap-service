package org.cmms.modules.khgl.khhmc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.khhmc.entity.KhfjxxglExp;

import java.util.List;

/**
 * @Description: 客户附加信息管理
 * @Author: jeecg-boot
 * @Date:   2020-03-27
 * @Version: V1.0
 */
public interface KhfjxxglMapper extends BaseMapper<Khfjxxgl> {
    public void updateKhhmc();
    public void updateywwl();
    public void updateHzxx();

    IPage<Khfjxxgl> getByWgbh(Page page,@Param("wgbh") String wgbh,@Param("type") String type);

    public List<KhfjxxglExp> getFjxxByWgbh(@Param("wgbh")String wgbh,@Param("yggh")String yggh);
}
