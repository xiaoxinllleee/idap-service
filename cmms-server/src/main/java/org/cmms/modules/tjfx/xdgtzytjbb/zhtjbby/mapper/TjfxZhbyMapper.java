package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhby;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行统计表一
 * @Author: cmms
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface TjfxZhbyMapper extends BaseMapper<TjfxZhby> {
    public  void extract(Map<String,String> sql);

    public String queryTableDictTextByKey(@Param("table") String table,@Param("text") String text,@Param("code") String code,@Param("key") String key);

}
