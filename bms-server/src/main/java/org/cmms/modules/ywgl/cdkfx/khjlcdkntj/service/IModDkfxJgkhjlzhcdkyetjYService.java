package org.cmms.modules.ywgl.cdkfx.khjlcdkntj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.entity.ModDkfxJgkhjlzhcdkyetjY;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理存贷款年统计
 * @Author: jeecg-boot
 * @Date:   2021-06-28
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IModDkfxJgkhjlzhcdkyetjYService extends IService<ModDkfxJgkhjlzhcdkyetjY> {
    String getByName(String ygxm);

}
