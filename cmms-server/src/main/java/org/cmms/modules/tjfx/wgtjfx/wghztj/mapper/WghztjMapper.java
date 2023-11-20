package org.cmms.modules.tjfx.wgtjfx.wghztj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.wgtjfx.wghztj.entity.Wghztj;

/**
 * @Description: 网格汇总统计
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface WghztjMapper extends BaseMapper<Wghztj> {
    Wghztj getLatestInfo(String wgbh);
}
