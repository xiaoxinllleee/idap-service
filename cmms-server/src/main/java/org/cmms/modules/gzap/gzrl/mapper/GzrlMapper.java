package org.cmms.modules.gzap.gzrl.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.zhcdksjmx.entity.Zhcksjmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行存款数据明细
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
public interface GzrlMapper extends BaseMapper<Zhcksjmx> {
    public List<Map> getgzrlxx(@Param("dx") String dx);




}
