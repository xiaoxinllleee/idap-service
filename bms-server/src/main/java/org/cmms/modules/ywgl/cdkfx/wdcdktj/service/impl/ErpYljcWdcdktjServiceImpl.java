package org.cmms.modules.ywgl.cdkfx.wdcdktj.service.impl;

import org.cmms.modules.ywgl.cdkfx.wdcdktj.entity.ErpYljcWdcdktj;
import org.cmms.modules.ywgl.cdkfx.wdcdktj.mapper.ErpYljcWdcdktjMapper;
import org.cmms.modules.ywgl.cdkfx.wdcdktj.service.IErpYljcWdcdktjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 网点存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@Service
public class ErpYljcWdcdktjServiceImpl extends ServiceImpl<ErpYljcWdcdktjMapper, ErpYljcWdcdktj> implements IErpYljcWdcdktjService {

    @Override
    public void pWdcdktj(String jgdm, String tjyf, String username) {
        baseMapper.pWdcdktj(jgdm, tjyf, username);
    }

    @Override
    public String querySubjectNo(String cfgcode) {
        return baseMapper.querySubjectNo(cfgcode);
    }
}
