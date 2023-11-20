package org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.entity.Khlxmx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户利息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IKhlxmxService extends IService<Khlxmx> {
    void pKhlxmx(String ksrq, String jsrq);
}
