package org.cmms.modules.dklldj.tjfxgl.qylldb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.tjfxgl.qylldb.entity.Qylldb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 签约利率对比
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
public interface QylldbMapper extends BaseMapper<Qylldb> {

    void init(Map<String, String> sql);

}
