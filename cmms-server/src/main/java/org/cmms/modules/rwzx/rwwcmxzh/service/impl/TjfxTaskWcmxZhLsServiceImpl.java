package org.cmms.modules.rwzx.rwwcmxzh.service.impl;

import org.cmms.modules.rwzx.rwwcmxzh.entity.TjfxTaskWcmxZhLs;
import org.cmms.modules.rwzx.rwwcmxzh.mapper.TjfxTaskWcmxZhLsMapper;
import org.cmms.modules.rwzx.rwwcmxzh.service.ITjfxTaskWcmxZhLsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 统计分析-任务完成明细_支行_蓝山
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
@Service
public class TjfxTaskWcmxZhLsServiceImpl extends ServiceImpl<TjfxTaskWcmxZhLsMapper, TjfxTaskWcmxZhLs> implements ITjfxTaskWcmxZhLsService {

    @Override
    public Date getMaxDate() {
        return baseMapper.getMaxDate();
    }
}
