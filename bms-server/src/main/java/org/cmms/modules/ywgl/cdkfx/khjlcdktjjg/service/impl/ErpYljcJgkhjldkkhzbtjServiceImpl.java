package org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.entity.ErpYljcJgkhjldkkhzbtj;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.mapper.ErpYljcJgkhjldkkhzbtjMapper;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.service.IErpYljcJgkhjldkkhzbtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 机构客户经理指标统计(机构)
 * @Author: jeecg-boot
 * @Date:   2021-06-15
 * @Version: V1.0
 */
@Service
public class ErpYljcJgkhjldkkhzbtjServiceImpl extends ServiceImpl<ErpYljcJgkhjldkkhzbtjMapper, ErpYljcJgkhjldkkhzbtj> implements IErpYljcJgkhjldkkhzbtjService {

    @Override
    public void pAutoExec(String jgdm, String tjyf, String username) {
        baseMapper.pAutoExec(jgdm, tjyf, username);
    }

    @Override
    public IPage<ErpYljcJgkhjldkkhzbtj> getList(Page<ErpYljcJgkhjldkkhzbtj> page, QueryWrapper<ErpYljcJgkhjldkkhzbtj> queryWrapper) {
        return baseMapper.getList(page,queryWrapper);
    }
}
