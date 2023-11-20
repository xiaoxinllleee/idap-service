package org.cmms.modules.rwzx.rwwcmx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.rwzx.rwwcmx.entity.TjfxTaskWcmxLs;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.rwzx.zfyxmx.entity.TaskZfyxmx;

import java.util.Date;

/**
 * @Description: 统计分析-任务完成明细_蓝山
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
public interface ITjfxTaskWcmxLsService extends IService<TjfxTaskWcmxLs> {
    Date getMaxDate();
    void initRwwcqk(String tjrq);
    IPage<TaskZfyxmx> getRwwcmx(Page page, String lx, String yggh, String sszh, String rwlx, String tjrq, String tjwd);
}
