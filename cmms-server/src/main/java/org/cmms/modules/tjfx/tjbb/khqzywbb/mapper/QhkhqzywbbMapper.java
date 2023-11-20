package org.cmms.modules.tjfx.tjbb.khqzywbb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjbb.khqzywbb.entity.Qhkhqzywbb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户潜在业务报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
public interface QhkhqzywbbMapper extends BaseMapper<Qhkhqzywbb> {
    public void extract();
}
