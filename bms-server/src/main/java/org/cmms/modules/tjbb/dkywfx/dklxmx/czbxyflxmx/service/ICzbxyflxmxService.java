package org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.tjbb.dkywfx.dklxmx.czbxyflxmx.entity.Czbxyflxmx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 财政保险应付利息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface ICzbxyflxmxService extends IService<Czbxyflxmx> {
    void pCzbxyflxmx(String ksrq, String jsrq);
}
