package org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zjfhqkjc.entity.DkjkptDkglckqsfxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 资金返还情况监测
 * @Author: cmms
 * @Date:   2019-10-08
 * @Version: V1.0
 */
public interface DkjkptDkglckqsfxbMapper extends BaseMapper<DkjkptDkglckqsfxb> {
    public void extract(String tjyf);
}
