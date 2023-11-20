package org.cmms.modules.ywgl.cdkfx.khjlcdkytj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.khjlcdkytj.entity.ModDkfxJgkhjlzhcdkyetjM;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理存贷款月统计
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IModDkfxJgkhjlzhcdkyetjMService extends IService<ModDkfxJgkhjlzhcdkyetjM> {
    String getByName(String ygxm);
}
