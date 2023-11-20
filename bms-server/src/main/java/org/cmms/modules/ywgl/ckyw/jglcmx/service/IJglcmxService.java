package org.cmms.modules.ywgl.ckyw.jglcmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.ckyw.jglcmx.entity.Jglcmx;

/**
 * @Description: 机关揽储明细
 * @Author: jeecg-boot
 * @Date:   2021-10-11
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IJglcmxService extends IService<Jglcmx> {
    void pJglcmx(String tjyf);

}
