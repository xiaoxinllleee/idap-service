package org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhby.entity.TjfxCscqzhby;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 村社村前统计支行表一
 * @Author: cmms
 * @Date:   2019-12-14
 * @Version: V1.0
 */
public interface TjfxCscqzhbyMapper extends BaseMapper<TjfxCscqzhby> {

    public void extract(Map<String,String>sql);

}
