package org.cmms.modules.xddagl.tjfx.xddaglygmrscs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xddagl.tjfx.xddaglygmrscs.entity.Xddaglygmrscs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 员工每日上传数
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
public interface XddaglygmrscsMapper extends BaseMapper<Xddaglygmrscs> {
    void pYgmrscs(@Param("tjrqBegin")String tjrqBegin, @Param("tjrqEnd")String tjrqEnd, @Param("username")String username);
}
