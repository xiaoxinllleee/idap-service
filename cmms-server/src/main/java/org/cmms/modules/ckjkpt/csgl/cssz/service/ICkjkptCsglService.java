package org.cmms.modules.ckjkpt.csgl.cssz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.csgl.cssz.entity.CkjkptCsgl;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptCsglService extends IService<CkjkptCsgl> {

    /**
     * 通过参数编码获取参数值
     * @param csbm
     * @return
     */
    @DS("ckjkpt")//ckjkpt
    String queryParamValue(String csbm);
}
