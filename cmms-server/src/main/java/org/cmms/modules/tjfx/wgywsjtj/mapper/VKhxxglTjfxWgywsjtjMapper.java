package org.cmms.modules.tjfx.wgywsjtj.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import org.cmms.modules.tjfx.wgywsjtj.entity.VKhxxglTjfxWgywsjtj;

/**
 * @Description: 网格业务数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
public interface VKhxxglTjfxWgywsjtjMapper extends BaseMapper<VKhxxglTjfxWgywsjtj> {

    public IPage<Kjbxx> getKjbxxListByWgbh(Page page,@Param("wgbh") String wgbh);
    public List<Kjbxx> getKjbxxListByWgbh(@Param("wgbh") String wgbh);
}
