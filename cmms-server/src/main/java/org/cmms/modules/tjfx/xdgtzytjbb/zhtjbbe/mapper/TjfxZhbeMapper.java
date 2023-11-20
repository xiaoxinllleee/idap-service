package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行统计报表二
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface TjfxZhbeMapper extends BaseMapper<TjfxZhbe> {

    public void extract(Map<String,String>sql);
}
