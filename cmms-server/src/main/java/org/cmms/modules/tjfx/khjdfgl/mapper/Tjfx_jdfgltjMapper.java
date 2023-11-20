package org.cmms.modules.tjfx.khjdfgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.khjdfgl.entity.Tjfx_jdfgltj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-09
 * @Version: V1.0
 */
public interface Tjfx_jdfgltjMapper extends BaseMapper<Tjfx_jdfgltj> {
    public void extract(String tjyf);
}
