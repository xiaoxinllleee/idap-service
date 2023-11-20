package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.mapper;

import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBsy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行不良贷款反弹监控（比上月）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
public interface DkjkptZhbldkftjkBsyMapper extends BaseMapper<DkjkptZhbldkftjkBsy> {

    public void initData(String tjyf);

}
