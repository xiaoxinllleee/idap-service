package org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.entity.DkjkptDkglckqsfxbAjfp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 资金返还情况监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptDkglckqsfxbAjfpService extends IService<DkjkptDkglckqsfxbAjfp> {
    public void extract(String tjyf);
}
