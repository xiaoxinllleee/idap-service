package org.cmms.modules.tjfx.khjdwzd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.khjdwzd.entity.Tjfx_jdwzdtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-07
 * @Version: V1.0
 */
public interface Tjfx_jdwzdtjMapper extends BaseMapper<Tjfx_jdwzdtj> {
    public void extract(String tjyf);
}
