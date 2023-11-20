package org.cmms.modules.ywgl.cdkfx.zhcdktj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.zhcdktj.entity.ErpYljcZhcdktj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IErpYljcZhcdktjService extends IService<ErpYljcZhcdktj> {
    void pWdcdktj(String jgdm, String tjyf, String username);
}
