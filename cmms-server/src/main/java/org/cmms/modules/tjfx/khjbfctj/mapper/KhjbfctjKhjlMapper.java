package org.cmms.modules.tjfx.khjbfctj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjKhjl;

import java.util.Map;

/**
 * @Description: 客户级别分层统计_客户经理
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface KhjbfctjKhjlMapper extends BaseMapper<KhjbfctjKhjl> {

    /**
     * 客户经理所属客户级别分层统计
     * @param sql
     */
    public void extract(Map<String, String> sql);

}
