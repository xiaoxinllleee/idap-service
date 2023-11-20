package org.cmms.modules.ywgl.dkyw.lsdksjgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;

/**
 * @Description: 历史贷款数据管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@DS("cdkyw")
public interface ILsdksjglService extends IService<Lsdksjgl> {
    String getByName(String ygxm);
}
