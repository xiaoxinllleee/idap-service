package org.cmms.modules.rwzx.rwwcmxzh.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.rwzx.rwwcmxzh.entity.TjfxTaskWcmxZh;

import java.util.Date;

/**
 * @Description: 统计分析-任务完成明细_支行
 * @Author: jeecg-boot
 * @Date:   2023-11-01
 * @Version: V1.0
 */
public interface TjfxTaskWcmxZhMapper extends BaseMapper<TjfxTaskWcmxZh> {
    public Date getMaxDate();
}
