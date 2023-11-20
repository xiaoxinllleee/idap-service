package org.cmms.modules.dkjkpt.tjcx.zhdktj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjXb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行表内贷款统计(旬报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
public interface ZhbndktjXbMapper extends BaseMapper<ZhbndktjXb> {

    public void extract(Map<String,String>sql);
}
