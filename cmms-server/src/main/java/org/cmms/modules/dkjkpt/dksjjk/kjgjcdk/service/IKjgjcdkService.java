package org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.entity.Kjgjcdk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 跨机构交叉贷款
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IKjgjcdkService extends IService<Kjgjcdk> {

    void InitData();

}
