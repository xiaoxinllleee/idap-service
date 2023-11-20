package org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjcajfp.entity.DkjkptDkglckqsfxbAjfp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 资金返还情况监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
public interface DkjkptDkglckqsfxbAjfpMapper extends BaseMapper<DkjkptDkglckqsfxbAjfp> {
    public void extract(String tjyf);
}
