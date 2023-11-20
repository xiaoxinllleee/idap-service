package org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dksjjk.dkhsdjb.entity.Dkhsdjbmx;

/**
 * @Description: 贷款回收登记簿明细
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkhsdjbmxService extends IService<Dkhsdjbmx> {
    public void init();

}
