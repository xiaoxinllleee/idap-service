package org.cmms.modules.dkjkpt.dkcsgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dkcsgl.entity.Dkjkpt_dkcsgl;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkpt_dkcsglService extends IService<Dkjkpt_dkcsgl> {
    public void extract();
}
