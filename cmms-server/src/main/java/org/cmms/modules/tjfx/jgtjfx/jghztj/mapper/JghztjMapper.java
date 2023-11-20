package org.cmms.modules.tjfx.jgtjfx.jghztj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.jgtjfx.jghztj.entity.Jghztj;

/**
 * @Description: 机构汇总统计
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface JghztjMapper extends BaseMapper<Jghztj> {
    Jghztj getLatestInfo(String jgdm);
}
