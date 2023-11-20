package org.cmms.modules.tjfx.xdgtzytjbb.nshby.mapper;

import java.util.Map;

import org.cmms.modules.tjfx.xdgtzytjbb.nshby.entity.TjfxNshby;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 农商行统计表一
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface TjfxNshbyMapper extends BaseMapper<TjfxNshby> {

    public  void extract(Map<String,String>sql);


}
