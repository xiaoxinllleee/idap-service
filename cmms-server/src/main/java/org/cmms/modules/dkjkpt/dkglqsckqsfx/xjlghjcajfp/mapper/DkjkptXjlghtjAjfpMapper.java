package org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.entity.DkjkptXjlghtjAjfp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 现金流归行监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
public interface DkjkptXjlghtjAjfpMapper extends BaseMapper<DkjkptXjlghtjAjfp> {

    public void extract(Map<String,String> sql);
}
