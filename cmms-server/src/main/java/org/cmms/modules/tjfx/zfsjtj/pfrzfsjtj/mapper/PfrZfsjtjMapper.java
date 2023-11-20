package org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfMxVo;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfsjtj;

/**
 * @Description: 陪访人走访数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-29
 * @Version: V1.0
 */
public interface PfrZfsjtjMapper extends BaseMapper<PfrZfsjtj> {
    IPage<PfrZfMxVo> getPfrZfMxNhPage(Page page, String lx, String tjrq, String gwbz, String yggh,String weekFristDay);
    IPage<PfrZfMxVo> getPfrZfMxShPage(Page page, String lx, String tjrq, String gwbz, String yggh,String weekFristDay);
}
