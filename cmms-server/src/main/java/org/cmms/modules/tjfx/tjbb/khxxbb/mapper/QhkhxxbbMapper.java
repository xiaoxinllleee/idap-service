package org.cmms.modules.tjfx.tjbb.khxxbb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjbb.khxxbb.entity.Qhkhxxbb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户信息报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
public interface QhkhxxbbMapper extends BaseMapper<Qhkhxxbb> {
    public void extract(String tjyf);
}
