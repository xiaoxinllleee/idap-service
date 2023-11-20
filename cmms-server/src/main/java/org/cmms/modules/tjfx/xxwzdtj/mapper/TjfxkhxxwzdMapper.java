package org.cmms.modules.tjfx.xxwzdtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xxwzdtj.entity.Tjfxkhxxwzd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户信息完整度统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface TjfxkhxxwzdMapper extends BaseMapper<Tjfxkhxxwzd> {

    public void extract(String tjyf);

}
