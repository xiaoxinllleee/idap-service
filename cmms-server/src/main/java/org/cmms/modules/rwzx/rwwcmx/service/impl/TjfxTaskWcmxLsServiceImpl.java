package org.cmms.modules.rwzx.rwwcmx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.rwzx.rwwcmx.entity.TjfxTaskWcmxLs;
import org.cmms.modules.rwzx.rwwcmx.mapper.TjfxTaskWcmxLsMapper;
import org.cmms.modules.rwzx.rwwcmx.service.ITjfxTaskWcmxLsService;
import org.cmms.modules.rwzx.zfyxmx.entity.TaskZfyxmx;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 统计分析-任务完成明细_蓝山
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
@Service
public class TjfxTaskWcmxLsServiceImpl extends ServiceImpl<TjfxTaskWcmxLsMapper, TjfxTaskWcmxLs> implements ITjfxTaskWcmxLsService {

    @Override
    public Date getMaxDate() {
        return baseMapper.getMaxDate();
    }

    @Override
    public void initRwwcqk(String tjrq) {
        baseMapper.initRwwcqk(tjrq);
    }

    @Override
    public IPage<TaskZfyxmx> getRwwcmx(Page page, String lx, String yggh, String sszh, String rwlx, String tjrq, String tjwd) {
        return baseMapper.getRwwcmx(page,lx,yggh,sszh,rwlx,tjrq,tjwd);
    }
}
