package org.cmms.modules.ywgl.cdkfx.xzblkkmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.cdkfx.xzblkkmx.entity.ErpJxkhKhjlxzblkk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 新增不良扣款明细
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IErpJxkhKhjlxzblkkService extends IService<ErpJxkhKhjlxzblkk> {
    void  pJxkhKhjlxzblkk(String tjyf,String username);
}
