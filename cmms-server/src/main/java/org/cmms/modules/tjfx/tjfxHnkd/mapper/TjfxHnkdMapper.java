package org.cmms.modules.tjfx.tjfxHnkd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 提统计分析惠农快贷
 * @Author: Penghr
 * @Date:   2022-12-29
 * @Version: V1.0
 */
public interface TjfxHnkdMapper extends BaseMapper<TjfxHnkd> {
    public List<TjfxHnkd> getByHhbm(String hhbm);
}
