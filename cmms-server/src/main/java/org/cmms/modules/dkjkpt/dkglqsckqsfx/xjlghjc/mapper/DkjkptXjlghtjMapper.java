package org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjc.entity.DkjkptXjlghtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 现金流归行监测
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
public interface DkjkptXjlghtjMapper extends BaseMapper<DkjkptXjlghtj> {
    public void extract(Map<String,String> sql);
}
