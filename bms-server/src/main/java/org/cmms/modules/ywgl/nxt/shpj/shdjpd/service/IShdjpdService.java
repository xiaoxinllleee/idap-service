package org.cmms.modules.ywgl.nxt.shpj.shdjpd.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity.Shdjpd;

import java.util.Date;
import java.util.Map;

/**
 * @Description: 商户等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IShdjpdService extends IService<Shdjpd> {
//    void pShdjpd(String pdlx, String pdzq, String username);
      void pShdjpd(Map<String, String> sql, String username);
}
