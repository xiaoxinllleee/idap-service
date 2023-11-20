package org.cmms.modules.dkjkpt.dkjkptfmk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dkjkptfmk.entity.DkjkptFmk;

/**
 * @Description: 福民卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptFmkService extends IService<DkjkptFmk> {
    public void fmkUpdate();
}
