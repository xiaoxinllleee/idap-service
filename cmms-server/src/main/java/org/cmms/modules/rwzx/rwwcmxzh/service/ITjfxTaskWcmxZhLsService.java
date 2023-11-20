package org.cmms.modules.rwzx.rwwcmxzh.service;

import org.cmms.modules.rwzx.rwwcmxzh.entity.TjfxTaskWcmxZhLs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 统计分析-任务完成明细_支行_蓝山
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
public interface ITjfxTaskWcmxZhLsService extends IService<TjfxTaskWcmxZhLs> {
    public Date getMaxDate();
}
