package org.cmms.modules.dkjkpt.bldkftjk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.bldkftjk.entity.DkjkptBldkftjkBsr;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 不良贷款反弹监控比昨日
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptBldkftjkBsrService extends IService<DkjkptBldkftjkBsr> {

    public void extract(String tjyf);
}
