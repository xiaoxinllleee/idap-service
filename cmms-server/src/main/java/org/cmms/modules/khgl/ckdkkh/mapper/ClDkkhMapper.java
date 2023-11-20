package org.cmms.modules.khgl.ckdkkh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.ckdkkh.entity.ClDkkh;

/**
 * @Description: 存量贷款客户
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
public interface ClDkkhMapper extends BaseMapper<ClDkkh> {
    public void pClDkkh();
}
