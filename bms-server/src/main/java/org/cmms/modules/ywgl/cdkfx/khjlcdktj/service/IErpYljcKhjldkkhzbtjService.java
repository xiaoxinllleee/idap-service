package org.cmms.modules.ywgl.cdkfx.khjlcdktj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjlcdktj.entity.ErpYljcKhjldkkhzbtj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理指标统计
 * @Author: jeecg-boot
 * @Date:   2021-06-15
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IErpYljcKhjldkkhzbtjService extends IService<ErpYljcKhjldkkhzbtj> {

    void pAutoExec(String jgdm, String tjyf, String username);

    String getSystemConfigParamValue(@Param("cfgcode") String cfgcode);

    String getSystemConfigParamValueNumber(@Param("cfgcode") String cfgcode);
    @DS("eweb")
    String getDailyLoanLimit(@Param("csbm") String csbm);
}
