package org.cmms.modules.rwzx.rwwcmx.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.rwzx.rwwcmx.entity.TjfxTaskWcmx;
import org.cmms.modules.rwzx.rwwcmx.mapper.TjfxTaskWcmxMapper;
import org.cmms.modules.rwzx.rwwcmx.service.ITjfxTaskWcmxService;
import org.cmms.modules.rwzx.zfyxmx.entity.TaskZfyxmx;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @Description: 统计分析-任务完成明细
 * @Author: jeecg-boot
 * @Date:   2023-08-09
 * @Version: V1.0
 */
@Service
public class TjfxTaskWcmxServiceImpl extends ServiceImpl<TjfxTaskWcmxMapper, TjfxTaskWcmx> implements ITjfxTaskWcmxService {

    @Override
    public void initRwwcqk(String tjrq) {
        baseMapper.initRwwcqk(tjrq);
    }

    @Override
    public Date getMaxDate() {
        return baseMapper.getMaxDate();
    }

    @Override
    public IPage<TaskZfyxmx> getRwwcmx(Page page,String lx, String yggh, String sszh, String rwlx, String tjrq,String tjwd) {
        return baseMapper.getRwwcmx(page,lx,yggh,sszh,rwlx,tjrq,tjwd);
    }
}
