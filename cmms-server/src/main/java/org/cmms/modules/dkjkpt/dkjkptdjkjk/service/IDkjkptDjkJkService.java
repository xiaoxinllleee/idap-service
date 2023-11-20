package org.cmms.modules.dkjkpt.dkjkptdjkjk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dkjkptdjkjk.entity.DkjkptDjkJk;

/**
 * @Description: 金卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptDjkJkService extends IService<DkjkptDjkJk> {
    public void djkjkUpdate();
}
