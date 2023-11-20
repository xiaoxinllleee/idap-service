package org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.entity.ModDkfxJgkhjldkdqshlkhM;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理贷款到期收回率统计
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IModDkfxJgkhjldkdqshlkhMService extends IService<ModDkfxJgkhjldkdqshlkhM> {
    String getByName(String ygxm);
}
