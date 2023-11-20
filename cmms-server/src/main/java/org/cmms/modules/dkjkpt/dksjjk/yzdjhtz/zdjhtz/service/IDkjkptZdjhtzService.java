package org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtz.entity.DkjkptZdjhtz;

/**
 * @Description: 已制定计划台账
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptZdjhtzService extends IService<DkjkptZdjhtz> {
    public void init(String tjrq);
}
