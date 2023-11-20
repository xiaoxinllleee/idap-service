package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.Dkjkptbwblxt;

/**
 * @Description: 贷款监控平台表外不良_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptbwblxtService extends IService<Dkjkptbwblxt> {
    public void init();
}
