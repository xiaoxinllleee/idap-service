package org.cmms.modules.pad.pyxx.service;

import org.cmms.modules.pad.pyxx.entity.Pydjcs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * @Description: 评议得分对应等级额度
 * @Author: jeecg-boot
 * @Date:   2020-07-31
 * @Version: V1.0
 */
public interface IPydjcsService extends IService<Pydjcs> {
   public Pydjcs getPddjAndJysxde(BigDecimal pydf);
}
