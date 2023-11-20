package org.cmms.modules.dkjkpt.tjcx.zhajdktj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity.ZhajdktjYb;

/**
 * @Description: 支行按揭贷款统计_月报
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
public interface ZhajdktjYbMapper extends BaseMapper<ZhajdktjYb> {
    public void extract (Map<String,String> sql);
}
