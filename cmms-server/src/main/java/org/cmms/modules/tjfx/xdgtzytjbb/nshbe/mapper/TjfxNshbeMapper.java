package org.cmms.modules.tjfx.xdgtzytjbb.nshbe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.nshbe.entity.TjfxNshbe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 农商行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface TjfxNshbeMapper extends BaseMapper<TjfxNshbe> {

    public void extract(String tjyf);

}
