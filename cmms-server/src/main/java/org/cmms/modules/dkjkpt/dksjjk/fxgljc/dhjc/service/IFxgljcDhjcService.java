package org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.entity.FxgljcDhjc;

/**
 * @Description: 贷后检查
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IFxgljcDhjcService extends IService<FxgljcDhjc> {
    public void init();
}
