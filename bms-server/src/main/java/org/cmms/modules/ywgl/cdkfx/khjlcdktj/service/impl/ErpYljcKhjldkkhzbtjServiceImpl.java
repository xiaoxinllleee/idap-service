package org.cmms.modules.ywgl.cdkfx.khjlcdktj.service.impl;

import org.cmms.modules.ywgl.cdkfx.khjlcdktj.entity.ErpYljcKhjldkkhzbtj;
import org.cmms.modules.ywgl.cdkfx.khjlcdktj.mapper.ErpYljcKhjldkkhzbtjMapper;
import org.cmms.modules.ywgl.cdkfx.khjlcdktj.service.IErpYljcKhjldkkhzbtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理指标统计
 * @Author: jeecg-boot
 * @Date:   2021-06-15
 * @Version: V1.0
 */
@Service
public class ErpYljcKhjldkkhzbtjServiceImpl extends ServiceImpl<ErpYljcKhjldkkhzbtjMapper, ErpYljcKhjldkkhzbtj> implements IErpYljcKhjldkkhzbtjService {

    @Override
    public void pAutoExec(String jgdm, String tjyf, String username) {
        baseMapper.pAutoExec(jgdm, tjyf, username);
    }

    @Override
    public String getSystemConfigParamValue(String cfgcode) {
        return baseMapper.getSystemConfigParamValue(cfgcode);
    }

    @Override
    public String getSystemConfigParamValueNumber(String cfgcode) {
        return baseMapper.getSystemConfigParamValueNumber(cfgcode);
    }

    @Override
    public String getDailyLoanLimit(String csbm) {
        return baseMapper.getDailyLoanLimit(csbm);
    }
}
