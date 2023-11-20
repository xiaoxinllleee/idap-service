package org.cmms.modules.ckjkpt.jcyj.debdcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.jcyj.debdcx.entity.CkjkptDebdcx;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptDebdcxService extends IService<CkjkptDebdcx> {
    public void extract(String tjyf);
}
