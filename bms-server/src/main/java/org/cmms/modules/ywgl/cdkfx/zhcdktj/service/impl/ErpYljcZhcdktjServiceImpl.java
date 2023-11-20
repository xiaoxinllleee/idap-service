package org.cmms.modules.ywgl.cdkfx.zhcdktj.service.impl;

import org.cmms.modules.ywgl.cdkfx.zhcdktj.entity.ErpYljcZhcdktj;
import org.cmms.modules.ywgl.cdkfx.zhcdktj.mapper.ErpYljcZhcdktjMapper;
import org.cmms.modules.ywgl.cdkfx.zhcdktj.service.IErpYljcZhcdktjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 支行存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@Service
public class ErpYljcZhcdktjServiceImpl extends ServiceImpl<ErpYljcZhcdktjMapper, ErpYljcZhcdktj> implements IErpYljcZhcdktjService {

    @Override
    public void pWdcdktj(String jgdm, String tjyf, String username) {
        baseMapper.pWdcdktj(jgdm, tjyf, username);
    }
}
