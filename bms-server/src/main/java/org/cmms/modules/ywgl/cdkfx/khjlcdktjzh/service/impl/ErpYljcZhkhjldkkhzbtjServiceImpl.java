package org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.entity.ErpYljcZhkhjldkkhzbtj;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.mapper.ErpYljcZhkhjldkkhzbtjMapper;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.service.IErpYljcZhkhjldkkhzbtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理指标统计(支行)
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
@Service
public class ErpYljcZhkhjldkkhzbtjServiceImpl extends ServiceImpl<ErpYljcZhkhjldkkhzbtjMapper, ErpYljcZhkhjldkkhzbtj> implements IErpYljcZhkhjldkkhzbtjService {

    @Override
    public IPage<ErpYljcZhkhjldkkhzbtj> getList(Page<ErpYljcZhkhjldkkhzbtj> page, QueryWrapper<ErpYljcZhkhjldkkhzbtj> queryWrapper) {
        return baseMapper.getList(page,queryWrapper);
    }

    @Override
    public void pAutoExec(String jgdm, String tjyf, String username) {
        baseMapper.pAutoExec(jgdm, tjyf, username);
    }
}
