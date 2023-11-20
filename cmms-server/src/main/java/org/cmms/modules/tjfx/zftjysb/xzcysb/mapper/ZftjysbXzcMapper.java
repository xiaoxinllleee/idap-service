package org.cmms.modules.tjfx.zftjysb.xzcysb.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.zftjysb.xzcysb.entity.ZftjysbXzc;

/**
 * @Description: 走访统计验收表-行政村
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
public interface ZftjysbXzcMapper extends BaseMapper<ZftjysbXzc> {
    Date getMaxDate();
    void init();
}
