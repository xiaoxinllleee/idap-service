package org.cmms.modules.dkjkpt.tjcx.zhajdktj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity.ZhajdktjNb;

import java.util.Map;

/**
 * @Description: 支行按揭贷款统计_年报
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
public interface ZhajdktjNbMapper extends BaseMapper<ZhajdktjNb> {

    public void  extract(Map<String,String> sql);
}
