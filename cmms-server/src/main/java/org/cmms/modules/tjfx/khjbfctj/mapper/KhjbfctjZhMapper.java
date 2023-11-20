package org.cmms.modules.tjfx.khjbfctj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjZh;

/**
 * @Description: 客户级别分层统计_支行
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface KhjbfctjZhMapper extends BaseMapper<KhjbfctjZh> {

    /**
     * 支行级别分层统计
     * @param sql
     */
    public void extract(Map<String, String> sql);

}
