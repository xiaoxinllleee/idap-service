package org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjlcdktjjg.entity.ErpYljcJgkhjldkkhzbtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 机构客户经理指标统计(机构)
 * @Author: jeecg-boot
 * @Date:   2021-06-15
 * @Version: V1.0
 */
public interface ErpYljcJgkhjldkkhzbtjMapper extends BaseMapper<ErpYljcJgkhjldkkhzbtj> {
    void pAutoExec(@Param("jgdm")String jgdm,@Param("tjyf")String tjyf,@Param("username")String username);

    IPage<ErpYljcJgkhjldkkhzbtj> getList(Page<ErpYljcJgkhjldkkhzbtj> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
