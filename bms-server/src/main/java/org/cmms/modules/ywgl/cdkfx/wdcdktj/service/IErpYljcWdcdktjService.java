package org.cmms.modules.ywgl.cdkfx.wdcdktj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.wdcdktj.entity.ErpYljcWdcdktj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 网点存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IErpYljcWdcdktjService extends IService<ErpYljcWdcdktj> {
    void pWdcdktj(String jgdm, String tjyf, String username);

    String querySubjectNo(@Param("cfgcode") String cfgcode);
}
