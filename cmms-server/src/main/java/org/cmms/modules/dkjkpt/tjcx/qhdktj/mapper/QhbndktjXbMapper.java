package org.cmms.modules.dkjkpt.tjcx.qhdktj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.entity.QhbndktjXb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行表内贷款统计(旬报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
public interface QhbndktjXbMapper extends BaseMapper<QhbndktjXb> {
    public void  extract(Map<String,String> sql);
}
