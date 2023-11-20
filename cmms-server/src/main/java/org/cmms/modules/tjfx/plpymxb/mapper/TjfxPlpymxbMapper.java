package org.cmms.modules.tjfx.plpymxb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.plpymxb.entity.TjfxPlpymxb;

/**
 * @Description: 批量评议明细表
 * @Author: jeecg-boot
 * @Date:   2023-06-28
 * @Version: V1.0
 */
public interface TjfxPlpymxbMapper extends BaseMapper<TjfxPlpymxb> {
    void init();
}
