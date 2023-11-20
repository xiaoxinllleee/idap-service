package org.cmms.modules.tjfx.plpyczhzb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.plpyczhzb.entity.TjfxPlpyczhzb;

/**
 * @Description: 批量评议村组汇总表
 * @Author: jeecg-boot
 * @Date:   2023-07-07
 * @Version: V1.0
 */
public interface TjfxPlpyczhzbMapper extends BaseMapper<TjfxPlpyczhzb> {
    void init();
}
