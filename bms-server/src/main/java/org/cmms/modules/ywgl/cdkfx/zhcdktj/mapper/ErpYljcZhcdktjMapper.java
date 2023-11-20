package org.cmms.modules.ywgl.cdkfx.zhcdktj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.zhcdktj.entity.ErpYljcZhcdktj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
public interface ErpYljcZhcdktjMapper extends BaseMapper<ErpYljcZhcdktj> {
    void pWdcdktj(@Param("jgdm")String jgdm,@Param("tjyf")String tjyf,@Param("username")String username);
}
