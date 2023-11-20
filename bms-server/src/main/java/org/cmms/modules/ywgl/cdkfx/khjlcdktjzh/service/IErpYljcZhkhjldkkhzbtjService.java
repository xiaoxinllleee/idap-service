package org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.entity.ErpYljcZhkhjldkkhzbtj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理指标统计(支行)
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IErpYljcZhkhjldkkhzbtjService extends IService<ErpYljcZhkhjldkkhzbtj> {
    IPage<ErpYljcZhkhjldkkhzbtj> getList(Page<ErpYljcZhkhjldkkhzbtj> page, QueryWrapper<ErpYljcZhkhjldkkhzbtj> queryWrapper);

    void pAutoExec(String jgdm,String tjyf,String username);
}
