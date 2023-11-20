package org.cmms.modules.dkjkpt.cssz.dkqxcssz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.entity.Dkjkpt_dkqxcssz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款期限参数设置
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkpt_dkqxcsszService extends IService<Dkjkpt_dkqxcssz> {
    public Dkjkpt_dkqxcssz selectByCsbh(String csbh);

    public Integer deleteByCsbh(String csbh);

    public Integer updateByCsbh(Dkjkpt_dkqxcssz cs);
}
