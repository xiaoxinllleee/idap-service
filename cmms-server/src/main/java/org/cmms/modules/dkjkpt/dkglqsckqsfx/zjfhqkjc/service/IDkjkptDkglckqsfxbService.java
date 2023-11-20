package org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjc.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjc.entity.DkjkptDkglckqsfxb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 资金返还情况监测
 * @Author: cmms
 * @Date:   2019-10-08
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptDkglckqsfxbService extends IService<DkjkptDkglckqsfxb> {
    public void extract(String tjyf);
}
