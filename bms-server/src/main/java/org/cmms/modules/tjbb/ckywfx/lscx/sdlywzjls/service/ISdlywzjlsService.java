package org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.entity.Sdlywzjls;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 收单类业务资金流水
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface ISdlywzjlsService extends IService<Sdlywzjls> {
    void pSdlywzjls(String ksrq, String jsrq);
}
