package org.cmms.modules.dkjkpt.dksjjk.dedkjc.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dksjjk.dedkjc.entity.DkjkptDedkjc;

/**
 * @Description: 大额贷款监测
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptDedkjcService extends IService<DkjkptDedkjc> {
    public void extract(String tjyf);
}
