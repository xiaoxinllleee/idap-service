package org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.mapper;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.entity.Wgpyqktj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评议情况统计(网格)
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
public interface WgpyqktjMapper extends BaseMapper<Wgpyqktj> {
    void initPyxx(@Param("tjyf") String tjyf);
}
