package org.cmms.modules.rwzx.rwwcmxzh.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.rwzx.rwwcmxzh.entity.TjfxTaskWcmxZhLs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 统计分析-任务完成明细_支行_蓝山
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
public interface TjfxTaskWcmxZhLsMapper extends BaseMapper<TjfxTaskWcmxZhLs> {
    public Date getMaxDate();
}
