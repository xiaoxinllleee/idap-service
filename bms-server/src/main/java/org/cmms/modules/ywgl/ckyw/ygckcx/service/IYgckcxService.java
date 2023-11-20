package org.cmms.modules.ywgl.ckyw.ygckcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.ckyw.ygckcx.entity.Ygckcx;

/**
 * @Description: 员工存款查询
 * @Author: jeecg-boot
 * @Date:   2021-10-09
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IYgckcxService extends IService<Ygckcx> {
    void pYgckcx(String tjyf);
}
