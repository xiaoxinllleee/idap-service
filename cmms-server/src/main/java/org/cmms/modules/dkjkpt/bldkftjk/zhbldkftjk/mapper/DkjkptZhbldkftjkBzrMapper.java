package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.mapper;

import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBzr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * @Description: 支行不良贷款反弹监控（比昨日）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
public interface DkjkptZhbldkftjkBzrMapper extends BaseMapper<DkjkptZhbldkftjkBzr> {

    public void initData(String tjyf);

}
