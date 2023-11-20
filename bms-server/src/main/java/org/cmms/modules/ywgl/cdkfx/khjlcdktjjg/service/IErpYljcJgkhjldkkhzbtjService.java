package org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.entity.ErpYljcJgkhjldkkhzbtj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 机构客户经理指标统计(机构)
 * @Author: jeecg-boot
 * @Date:   2021-06-15
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IErpYljcJgkhjldkkhzbtjService extends IService<ErpYljcJgkhjldkkhzbtj> {
    void pAutoExec(String jgdm, String tjyf, String username);

    IPage<ErpYljcJgkhjldkkhzbtj> getList(Page<ErpYljcJgkhjldkkhzbtj> page, QueryWrapper<ErpYljcJgkhjldkkhzbtj> queryWrapper);
}
