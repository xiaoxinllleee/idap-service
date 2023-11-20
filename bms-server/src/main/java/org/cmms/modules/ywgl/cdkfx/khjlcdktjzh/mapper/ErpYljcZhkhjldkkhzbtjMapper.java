package org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjzh.entity.ErpYljcZhkhjldkkhzbtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理指标统计(支行)
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
public interface ErpYljcZhkhjldkkhzbtjMapper extends BaseMapper<ErpYljcZhkhjldkkhzbtj> {
    void pAutoExec(@Param("jgdm")String jgdm,@Param("tjyf")String tjyf,@Param("username")String username);

    IPage<ErpYljcZhkhjldkkhzbtj> getList(Page<ErpYljcZhkhjldkkhzbtj> page, @Param(Constants.WRAPPER)Wrapper wrapper);

}
