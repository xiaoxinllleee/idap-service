package org.cmms.modules.tjbb.dkywfx.dkffmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dkffmx.entity.Dkffmx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款发放明细
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IDkffmxService extends IService<Dkffmx> {
    void pDkffmx(String ksrq, String jsrq);
}
