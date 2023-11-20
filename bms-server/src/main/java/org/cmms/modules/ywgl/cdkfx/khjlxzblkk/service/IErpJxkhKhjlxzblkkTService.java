package org.cmms.modules.ywgl.cdkfx.khjlxzblkk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.entity.ErpJxkhKhjlxzblkkT;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理新增不良扣款
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IErpJxkhKhjlxzblkkTService extends IService<ErpJxkhKhjlxzblkkT> {
    void pJxkhKhjlxzblkkTwo(String tjyf);
}
