package org.cmms.modules.ywgl.cdkfx.xzblkkmx.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.xzblkkmx.entity.ErpJxkhKhjlxzblkk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 新增不良扣款明细
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */

public interface ErpJxkhKhjlxzblkkMapper extends BaseMapper<ErpJxkhKhjlxzblkk> {
    void  pJxkhKhjlxzblkk(@Param("tjyf")String tjyf,@Param("username")String username);
}
