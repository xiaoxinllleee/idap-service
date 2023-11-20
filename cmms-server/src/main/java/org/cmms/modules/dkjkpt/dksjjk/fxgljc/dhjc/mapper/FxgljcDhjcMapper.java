package org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.mapper;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.entity.FxgljcDhjc;

/**
 * @Description: 贷后检查
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */

public interface FxgljcDhjcMapper extends BaseMapper<FxgljcDhjc> {
    public void init();
}
