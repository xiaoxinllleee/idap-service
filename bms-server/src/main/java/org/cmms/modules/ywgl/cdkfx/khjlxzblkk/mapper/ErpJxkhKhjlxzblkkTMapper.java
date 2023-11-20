package org.cmms.modules.ywgl.cdkfx.khjlxzblkk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.entity.ErpJxkhKhjlxzblkkT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理新增不良扣款
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
public interface ErpJxkhKhjlxzblkkTMapper extends BaseMapper<ErpJxkhKhjlxzblkkT> {
    void pJxkhKhjlxzblkkTwo(String tjyf);
}
