package org.cmms.modules.dkjkpt.tjcx.zhdktj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjYb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行表内贷款统计(月报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
public interface ZhbndktjYbMapper extends BaseMapper<ZhbndktjYb> {

    public void extract (Map<String,String>sql);

}