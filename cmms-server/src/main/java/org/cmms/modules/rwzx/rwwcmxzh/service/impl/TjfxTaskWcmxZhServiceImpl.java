package org.cmms.modules.rwzx.rwwcmxzh.service.impl;


import org.cmms.modules.rwzx.rwwcmxzh.entity.TjfxTaskWcmxZh;
import org.cmms.modules.rwzx.rwwcmxzh.mapper.TjfxTaskWcmxZhMapper;
import org.cmms.modules.rwzx.rwwcmxzh.service.ITjfxTaskWcmxZhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 统计分析-任务完成明细_支行
 * @Author: jeecg-boot
 * @Date:   2023-11-01
 * @Version: V1.0
 */
@Service
public class TjfxTaskWcmxZhServiceImpl extends ServiceImpl<TjfxTaskWcmxZhMapper, TjfxTaskWcmxZh> implements ITjfxTaskWcmxZhService {

    @Override
    public Date getMaxDate() {
        return baseMapper.getMaxDate();
    }
}
