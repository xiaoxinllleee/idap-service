package org.cmms.modules.dkjkpt.cssz.dqqkjccssz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.entity.Dkjkpt_dkqxcssz;
import org.cmms.modules.dkjkpt.cssz.dqqkjccssz.entity.Dkjkpt_dqqkjccssz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 到期情况监测参数设置
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkpt_dqqkjccsszService extends IService<Dkjkpt_dqqkjccssz> {
    public Integer deleteByCsbh(String csbh);

    public Integer updateByCsbh(Dkjkpt_dkqxcssz cs);
}
