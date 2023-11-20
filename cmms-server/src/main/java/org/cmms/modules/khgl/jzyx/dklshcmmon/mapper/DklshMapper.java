package org.cmms.modules.khgl.jzyx.dklshcmmon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.jzyx.dklshcmmon.entity.Dklsh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款流失户
 * @Author: jeecg-boot
 * @Date:   2023-07-10
 * @Version: V1.0
 */
public interface DklshMapper extends BaseMapper<Dklsh> {
    public void extract();
}
