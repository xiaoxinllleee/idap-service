package org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.entity.Yglcjxmx;

/**
 * @Description: 员工揽储绩效明细
 * @Author: jeecg-boot
 * @Date:   2021-10-27
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IYglcjxmxService extends IService<Yglcjxmx> {
    void pYglcjxmx(String tjyf);
}
