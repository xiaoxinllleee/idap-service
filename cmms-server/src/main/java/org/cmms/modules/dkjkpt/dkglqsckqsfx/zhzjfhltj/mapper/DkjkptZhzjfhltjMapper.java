package org.cmms.modules.dkjkpt.dkglqsckqsfx.zhzjfhltj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.zhzjfhltj.entity.DkjkptZhzjfhltj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行资金返还率统计
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
public interface DkjkptZhzjfhltjMapper extends BaseMapper<DkjkptZhzjfhltj> {
    public void extract(String tjyf);
}
