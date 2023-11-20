package org.cmms.modules.xddaglxt.dksjgl.dksjjktz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.Dksjjktz;

/**
 * @Description: 贷款数据监控台账
 * @Author: jeecg-boot
 * @Date:   2021-11-22
 * @Version: V1.0
 */
@DS("eweb")
public interface IDksjjktzService extends IService<Dksjjktz> {
    void pDksjjktz();
}
